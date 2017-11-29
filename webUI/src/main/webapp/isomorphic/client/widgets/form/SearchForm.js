
/*

  SmartClient Ajax RIA system
  Version v11.1p_2017-07-03/LGPL Deployment (2017-07-03)

  Copyright 2000 and beyond Isomorphic Software, Inc. All rights reserved.
  "SmartClient" is a trademark of Isomorphic Software, Inc.

  LICENSE NOTICE
     INSTALLATION OR USE OF THIS SOFTWARE INDICATES YOUR ACCEPTANCE OF
     ISOMORPHIC SOFTWARE LICENSE TERMS. If you have received this file
     without an accompanying Isomorphic Software license file, please
     contact licensing@isomorphic.com for details. Unauthorized copying and
     use of this software is a violation of international copyright law.

  DEVELOPMENT ONLY - DO NOT DEPLOY
     This software is provided for evaluation, training, and development
     purposes only. It may include supplementary components that are not
     licensed for deployment. The separate DEPLOY package for this release
     contains SmartClient components that are licensed for deployment.

  PROPRIETARY & PROTECTED MATERIAL
     This software contains proprietary materials that are protected by
     contract and intellectual property law. You are expressly prohibited
     from attempting to reverse engineer this software or modify this
     software for human readability.

  CONTACT ISOMORPHIC
     For more information regarding license rights and restrictions, or to
     report possible license violations, please contact Isomorphic Software
     by email (licensing@isomorphic.com) or web (www.isomorphic.com).

*/
//>	@class	SearchForm
//
// A SearchForm is a DynamicForm specialized for a user to enter search criteria.
// <P>
// All DynamicForm properties and methods work on SearchForm.  SearchForm extends and
// specializes DynamicForm for searching; for example, SearchForm sets
// <code>hiliteRequiredFields</code> false by default because fields are typically not 
// required in a search.
// 
// @see class:DynamicForm
//
// @inheritsFrom DynamicForm
// @treeLocation Client Reference/Forms
// @visibility external
//<


// create the form as a descendant of the DynamicForm
isc.ClassFactory.defineClass("SearchForm", "DynamicForm");

// add constants
isc.SearchForm.addProperties({
    //> @attr SearchForm.canEditFieldAttribute (String : "canFilter" : IRA)
    // This property is overridden in SearchForm to allow editing of dataSource fields marked as
    // <code>canFilter:true</code> by default.
    // @see dataBoundComponent.canEditFieldAttribute
    // @include dataBoundComponent.canEditFieldAttribute
    // @visibility external
    //<
    canEditFieldAttribute:"canFilter",
    
    
    isSearchForm:true,
    
    // hiliteRequiredFields - false
    // Don't hilight required fields in bold by default.
    hiliteRequiredFields:false,

    // if there are operation-specific schema on a DataSource we're binding to, use the fetch
    // schema
    operationType:"fetch",
    
    // This flag allows editing of canSave:false fields
    _canEditUnsaveableFields:true,

    // set this to false to cause select-items not to show a blank entry
    // (used this way by filter-clauses in FilterBuilder)
    allowEmptyValues: true,
    
    // set storeAtomicValues to true. Search forms are for editing criteria.
    // If we have a field whose type is an opaque simpleType we don't expect to
    // be passed values of that raw data type, nor to be creating criteria with
    // values of that raw type -- instead we work with the atomic type in our criteria.
    storeAtomicValues:true
});

isc.SearchForm.addMethods({
    // When creating DateItems, show the text field by default (unless the definition block
    // explicitly says otherwise)
    
    _$DateItem:"DateItem",
    createItem : function (item, type, a,b,c) {
        var ds = this.getDataSource(),
            isDSField = ds ? ds.getField(item[this.fieldIdProperty]) != null : false;
        
        // If we're looking at a dataSource field, ensure the user can always enter a null value
        // (Allows searching for all entries in boolean / date / valueMapped fields)
        if (isDSField) {
            // convert from a simple object into a FormItem
            var className = isc.FormItemFactory.getItemClassName(item, type, this),
                classObject = isc.FormItemFactory.getItemClass(className);
            
            if (classObject == isc.DateItem && item && (item.useTextField == null)) 
                item.useTextField = true;
            
            // Default to this.allowEmptyValues (plural), unless the singular version is 
            // explicitly set on the item
            if (item.allowEmptyValue == null) {
                item.allowEmptyValue = this.allowEmptyValues;
            }
        }
        
        return this.invokeSuper(isc.SearchForm, "createItem", item, type, a,b,c);
    },
    
    submitValues : function (values, form) {
        if (this.search != null) {
            return this.search(this.getValuesAsCriteria(), this);
        }
    },

    validate : function (a, b, c) {
        if (this.validateTypeOnly) {
            return this.invokeSuper(isc.SearchForm, "validate", a, b, true);
        } else {
            return this.invokeSuper(isc.SearchForm, "validate", a, b, c);
        }
    },
    
    // override getEditorType() so we can default date fields to using the DateRangeItem
    defaultDateEditorType:"DateRangeItem",
    getEditorType : function (field) {
        // support field.filterEditorType and field.editorType being specified directly
        var editorType = field.filterEditorType || field.editorType;

    
        // items originating in SGWT may have FormItem as editorType - ignore
        if (editorType == isc.DynamicForm._$formItem) {
            editorType = null;
        }
        
        if (editorType != null) return editorType;
        
        var type = field.type;
        if (type && isc.SimpleType.inheritsFrom(type, "date")) {
            return this.defaultDateEditorType;
        }

        var isFileType = type == isc.SearchForm._$binary || type == isc.SearchForm._$file ||
                         type == isc.SearchForm._$imageFile;
        if (isFileType) return "StaticTextItem";
        
        return this.Super("getEditorType", arguments);
    }

    
});

isc.SearchForm.addProperties({
    //> @attr searchForm.showFilterFieldsOnly (Boolean : true : IRWA)
    // @include dataBoundComponent.showFilterFieldsOnly
    // @visibility external
    //<
    showFilterFieldsOnly:true,
    
    //> @attr searchForm.validateTypeOnly (boolean : true : IRWA)
    // If true (the default), calls to the <code>SearchForm</code>'s <code>validate()</code> 
    // method will validate only field types (ie, is the value a valid string, a valid number,
    // or whatever); any other validations are skipped.
    //
    // @visibility internal
    //<
    
    validateTypeOnly:true
});

isc.SearchForm.registerStringMethods ({
	//>	@method SearchForm.search()
    // Triggered when a SubmitItem is included in the form is submitted and gets pressed.
    // 
    // @param	criteria  (Criteria)      the search criteria from the form
    // @param	form      (SearchForm)    the form being submitted
    // @group submitting
    // @see method:dynamicForm.submit()
    // @see method:dynamicForm.submitValues()
    // @visibility external
	//<
    search : "criteria,form"
});

