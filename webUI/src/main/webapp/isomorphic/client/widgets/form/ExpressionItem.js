
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
isc.defineClass("ExpressionItem", "PopUpTextAreaItem").addProperties({
    multiple: true,

    mapValueToDisplay : function (value) {
        if (isc.isAn.Array(value)) {
            var displayVals = [];
            for (var i = 0; i < value.length; ++i) {
                var val = value[i];
                if (isc.isA.StringMethod(val)) {
                    displayVals.add(val.getDisplayValue());
                } else if (isc.isAn.Object(val)) {
                    displayVals.add(this.creator.builder.getActionTitle(val.target, val.name));
                }
            }
            return displayVals.join(", ");
        } else if (isc.isA.StringMethod(value)) {
            return value.getDisplayValue();
        } else if (isc.isA.Function(value)) {
            if (value.iscAction) {
                var builder = this.creator.builder;
                if (isc.isAn.Array(value.iscAction)) {
                    return value.iscAction.map(function (action) {
                        return builder.getActionTitle(action.target, action.name);
                    }).join(", ");
                }
                return builder.getActionTitle(value.iscAction.target, value.iscAction.name);
            }
            return isc.Func.getBody(value);
        }
        else return this.Super("mapValueToDisplay", arguments);
    },

    getValue : function () {
        var value = this.Super("getValue");
        if (isc.isA.Function(value)) return isc.Func.getBody(value);
        else return value;
    },
    textAreaWidth:400,

    // ---------------------------------------------------------------------------------------

    showActionIcon:true,
    actionIconSrc:"[SKIN]/actions/add.png",
    actionIconWidth:20,
    actionIconHeight:20,
    
    // Where should the actionIcon show up in the icons array?
    
    actionIconPosition:1,
    _setUpIcons : function () {
        this.Super("_setUpIcons", arguments);
        if (this.showActionIcon) {
            if (this.icons == null) this.icons = [];
            var position = this.actionIconPosition;
            this.icons.addAt({
                name:"action",
                src:this.actionIconSrc,
                showOver:false,
                width:this.actionIconWidth,
                height:this.actionIconHeight,
                // return false from click handler so we don't fire standard cellclick
                // which would show the pop up (unless we also set popUpOnAnyClick to false)
                click : function (form, item) {
                    item.showActionMenu();
                    return false;
                }
            }, position);

            // have to explicitly set the required icon properties - usually handled by
            // setUpIcons, but that's already run at this point.
            this._setUpIcon(this.icons[position]);
        }
    },

    // Override updateAppearance to refresh our element value. Required since
    // the element value isn't edited directly.
    updateAppearance : function (newValue) {    
        this.setElementValue(this.mapValueToDisplay(newValue));
    },

    showActionMenu : function () {
        var currentStringMethods = [],
            value = this.getValue();
        if (isc.isA.Function(value) && value.iscAction != null) {
            currentStringMethods.add(isc.StringMethod.create({value: value.iscAction}));
        } else if (isc.isA.StringMethod(value)) {
            currentStringMethods.add(value);
        } else if (isc.isAn.Array(value)) {
            for (var i = 0; i < value.length; ++i) {
                var val = value[i];
                if (isc.isA.Function(val) && val.iscAction != null) {
                    currentStringMethods.add(isc.StringMethod.create({value: val.iscAction}));
                } else if (isc.isA.StringMethod(val)) {
                    currentStringMethods.add(val);
                } else if (isc.isAn.Object(val)) {
                    currentStringMethods.add(isc.StringMethod.create({value: val}));
                }
            }
        } else if (isc.isAn.Object(value)) {
            currentStringMethods.add(isc.StringMethod.create({value: value}));
        }

        var menu = this.actionMenu;
        if (menu == null) {
            var item = this;
            
            menu = this.actionMenu = this.createAutoChild("actionMenu", {
                builder: this.creator.builder,
                sourceComponent: this.form.currentComponent,
                sourceMethod: this.name,
                components: this.form.allComponents,
                bindingComplete : function (bindings) {
                    // `bindings' may be null - if the user selected top level "[None]" option
                    item._updateValue(bindings);
                }
            }, "ActionMenu");
        }

        menu.currentStringMethods = currentStringMethods;

        // Need to draw the menu first or placeNear() might not have the correct dimensions
        menu.show();
        var iconRect = this.getIconPageRect(this.icons[1]);
        menu.placeNear(iconRect[0] + iconRect[2],
                       iconRect[1] + iconRect[3]);
    }

});
