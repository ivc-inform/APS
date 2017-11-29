(function() {
  isc.defineClass("ListGridEditor", isc.VLayoutSS, isc.GridEditorInterface).addProperties({
    "canDragSelectText": true,
    "autoFitFieldWidths": false,
    "canAutoFitWidth": false,
    "hoverWidth": 300,
    "dataFetchMode": "paged",
    "canResizeFields": true,
    "selectionType": "single",
    "findByKey": function(keyValue) {
      return this.grid.findByKey(keyValue);
    },
    "getLiveRecordComponent": function(record, fieldName, bodyID) {
      return this.grid.getLiveRecordComponent(record, fieldName, bodyID);
    },
    "removeData": function(removeRecord, callback, requestProperties) {
      this.grid.removeData(removeRecord, callback, requestProperties);
    },
    "saveAllEdits": function(rows, saveCallback) {
      this.grid.saveAllEdits(rows, saveCallback);
    },
    "recordComponentPoolingMode": "viewport",
    "selectAllRecords": function(visibleNodesOnly) {
      this.grid.selectAllRecords(visibleNodesOnly);
    },
    "showAllRecords": false,
    "refreshRecordComponent": function(rowNum, colNum) {
      this.grid.refreshRecordComponent(recordDate, colNum);
    },
    "applyRecordData": function(recordDate) {
      this.grid.applyRecordData(recordDate);
    },
    "invalidateRecordComponents": function() {
      this.grid.invalidateRecordComponents();
    },
    "selectSingleRecordByKey": function(keyValue, newStyle, callback) {
      var record;
      record = this.findByKey(keyValue, newStyle);
      if (record != null) {
        this.selectRecord(record);
        isc.Class.fireCallback(callback);
      } else {
        this.deselectAllRecords();
      }
      return record;
    },
    "selectFirstRecordAfterFetch": true,
    "fetchData": function(criteria, callback, requestProperties) {
      var _callback, ref;
      if (this.useClientFilteringSorting === false) {
        _callback = (function(_this) {
          return function(dsResponse, data, dsRequest) {
            if (_this.selectFirstRecordAfterFetch) {
              _this.grid.selectFirstRecord();
            }
            _this.fireCallback(callback, "dsResponse, data, dsRequest", [dsResponse, data, dsRequest]);
          };
        })(this);
        this.grid.filterData(criteria, _callback, requestProperties);
        if ((ref = this.filterBuilder) != null) {
          ref.setCriteria(criteria);
        }
      } else {
        _callback = (function(_this) {
          return function(dsResponse, data, dsRequest) {
            var resultSet;
            resultSet = isc.ResultSet.create({
              dataSource: _this.dataSource,
              allRows: data
            });
            _this.grid.setData(resultSet);
            _this.fireCallback(callback, "dsResponse, data, dsRequest", [dsResponse, data, dsRequest]);
          };
        })(this);
        this.dataSource.filterData(criteria, _callback, requestProperties);
      }
    },
    "hasChanges": function() {
      return this.grid.hasChanges();
    },
    "getDataLength": function() {
      return this.grid.data.getLength();
    },
    "showRecordComponentsByCell": false,
    "showRecordComponents": false,
    "drawAheadRatio": 1.3,
    "autoSaveEdits": true,
    "autoDraw": false,
    "selectSingleRecord": function(record) {
      if (this.grid.getSelectionLength() === 0) {
        this.grid.selectRecord(record);
      } else {
        this.grid.selectSingleRecord(record);
      }
    },
    "getFuncMenu": function() {
      return this.funcMenu;
    },
    "canEdit": false,
    "filterOnKeypress": true,
    "hasErrors": function() {
      return this.grid.hasErrors();
    },
    "getFieldByName": function(name) {
      var field, i, len, ref;
      ref = this.grid.fields;
      for (i = 0, len = ref.length; i < len; i++) {
        field = ref[i];
        if (field.name === name) {
          return field;
        }
      }
      return void 0;
    },
    "showRollOver": true,
    "autoFetchData": true,
    "showRowNumbers": true,
    "autoSaveConfig": true,
    "getExpansionComponent": function() {
      return null;
    },
    "removeSelectedData": function(callback, requestProperties) {
      this.grid.removeSelectedData(callback, requestProperties);
    },
    "showAdvancedFilter": false,
    "restoreViewState": function(callback) {
      isc.OfflineSS.remove(this.grid.getIdentifier());
      this.autoSaveConfig = false;
      this.fireCallback(callback);
    },
    "getCriteria": function() {
      return this.grid.getCriteria();
    },
    "setValueMap": function(fieldID, map) {
      this.grid.setValueMap(fieldID, map);
    },
    "setDataSource": function(dataSource) {
      this.grid.setDataSource(dataSource);
    },
    "setSelectedState": function(selectedState) {
      this.grid.setSelectedState(selectedState);
    },
    "cancelEditing": function() {
      this.grid.cancelEditing();
    },
    "emptyMessage": "<h1>\u0414\u0430\u043D\u043D\u044B\u0445 \u043D\u0435\u0442.</h1>",
    "updateData": function(updatedRecord, callback, requestProperties) {
      this.grid.updateData(updatedRecord, callback, requestProperties);
    },
    "setFuncMenu": function(funcMenu) {
      this.funcMenu = funcMenu;
    },
    "destroy": function() {
      this._save();
      this.Super("destroy", arguments);
    },
    "_save": function() {
      if (this.autoSaveConfig === true) {
        this.saveViewState();
      }
    },
    "wrapCells": false,
    "autoFetchTextMatchStyle": "substring",
    "getSelection": function() {
      return this.grid.getSelection();
    },
    "showResizeBar": false,
    "focus": function() {
      this.grid.focus();
      if (this.grid.getRecord(0) != null) {
        this.grid.selectRecord(this.grid.getRecord(0));
      }
    },
    "fixedRecordHeights": false,
    "dragTrackerMode": "icon",
    "setData": function(newData) {
      this.grid.setData(newData);
    },
    "autoFitWidthApproach": "title",
    "startEditing": function() {
      var record;
      record = this.grid.getRowNumSelectedGridRecord();
      this.grid.startEditing(record.rowNum, record.colNum);
    },
    "addData": function(newRecord, callback, requestProperties) {
      this.grid.addData(newRecord, callback, requestProperties);
    },
    "modalEditing": true,
    "selectionAppearance": "rowStyle",
    "selectRecords": function(records, newState) {
      this.grid.selectRecords(records, newState);
    },
    "canSelectCells": false,
    "setFields": function(fields) {
      this.grid.setFields(fields);
    },
    "selectRecord": function(record, newState) {
      this.grid.selectRecord(record, newState);
    },
    "useClientFilteringSorting": false,
    "height": "100%",
    "selectRecordByKey": function(keyValue, newState) {
      this.grid.selectRecord(this.grid.findByKey(keyValue), newState);
    },
    "fullRefresh": function(criteria, callback, requestProperties) {
      var res, selected;
      res = 0;
      selected = this.grid.getSelectedState();
      if (isc.isA.Function(criteria) === true) {
        callback = criteria;
        criteria = null;
      }
      if (criteria == null) {
        criteria = this.grid.getCriteria() != null ? this.grid.getCriteria() : {};
      }
      criteria.ts = simpleSyS.timeStamp();

      /*requestProperties ?= {}
      		requestProperties.textMatchStyle = "substring"
       */
      this.fetchData(criteria, (function(_this) {
        return function(dsResponse, data, dsRequest) {
          res = data != null ? data.length : 0;
          _this.grid.setSelectedState(selected);
          if (_this.grid.getSelectedRecords().length === 0) {
            _this.selectFirstRecord();
          }
          if (callback != null) {
            _this.fireCallback(callback);
          }
        };
      })(this));

      /*, requestProperties */
      return res;
    },
    "invalidateCache": function() {
      this.grid.invalidateCache();
    },
    "saveViewState": function(callback) {
      var data, dataStr, id;
      id = this.grid.getIdentifier();
      data = this.grid.getViewState();
      dataStr = isc.JSONSS.encode(data, {
        prettyPrint: false
      });

      /*console.log "Save component id: #{id} data: #{dataStr}" */
      isc.OfflineSS.put(id, dataStr);
      if (callback != null) {
        this.fireCallback(callback);
      }
    },
    "getViewState": function() {
      var dataStr, id, savedState;
      id = this.grid.getIdentifier();
      dataStr = isc.OfflineSS.get(this.grid.getIdentifier());

      /*console.log "Restore component id: #{id} data: #{dataStr}" */
      savedState = isc.JSONSS.decode(dataStr);
      return this.grid.setViewState(savedState);
    },
    "width": "100%",
    "cancelEditingConfirmationMessage": "Выход из режима редактирования повлечет к утере измененных(введенных) данных. Продолжить ?",
    "getAllRows": function() {
      return this.grid.data.getAllRows();
    },
    "setContextMenu": function(menu) {
      if (isc.isA.MenuSS(menu)) {
        this.grid.contextMenu = menu.getMergedContextMenu();
      } else if (isc.isA.Menu(menu)) {
        this.grid.contextMenu = (isc.MenuSS.create({
          items: menu.items,
          owner: this
        })).getMergedContextMenu();
      }
    },
    "getContextMenu": function() {
      return this.grid.contextMenu;
    },
    "setMasterGrid": function(grid, pkFieldNames) {
      this.grid.setMasterGrid(grid, pkFieldNames);
    },
    "refreshData": function(callback) {
      this.grid.refreshData(callback);
    },
    "initWidget": function() {
      var ref, ref1;
      if ((ref = this.fields) != null) {
        ref.forEach(function(field) {
          if (field.nameStrong != null) {
            field.name = field.nameStrong.name;
          }
        });
      }
      this.Super("initWidget", arguments);
      this.grid = isc.ListGrid.create({
        "autoFetchData": false,
        "canSelectText": this.canSelectText,
        "dataFetchMode": this.dataFetchMode,
        "identifier": this.identifier,
        "autoFitFieldWidths": this.autoFitFieldWidths,
        "recordComponentPoolingMode": this.recordComponentPoolingMode,
        "showAllRecords": this.showAllRecords,
        "rowContextClick": this.rowContextClick,
        "groupStartOpen": this.groupStartOpen,
        "getBaseStyle": this.getBaseStyle,
        "dataProperties": this.dataProperties,
        "clientOnly": this.clientOnly,
        "showRecordComponentsByCell": this.showRecordComponentsByCell,
        "showRecordComponents": this.showRecordComponents,
        "drawAheadRatio": this.drawAheadRatio,
        "detailField": this.detailField,
        "autoSaveEdits": this.autoSaveEdits,
        "autoDraw": false,
        "cellClick": this.cellClick,
        "initialSort": this.initialSort,
        "canEdit": this.canEdit,
        "filterOnKeypress": this.filterOnKeypress,
        "selectionType": this.selectionType,
        "showRollOver": this.showRollOver,
        "showRowNumbers": this.showRowNumbers,
        "canExpandRecords": this.canExpandRecords,
        "masterGrid": this.masterGrid,
        "showSelectedStyle": this.showSelectedStyle,
        "data": this.data,
        "owner": this,
        "emptyMessage": this.emptyMessage,
        "wrapCells": this.wrapCells,
        "autoFetchTextMatchStyle": this.autoFetchTextMatchStyle,
        "expansionMode": this.expansionMode,
        "fields": this.fields,
        "canDragSelectText": this.canDragSelectText,
        "dataSource": this.dataSource,
        "fixedRecordHeights": this.fixedRecordHeights,
        "autoFitWidthApproach": this.autoFitWidthApproach,
        "modalEditing": this.modalEditing,
        "selectionAppearance": this.selectionAppearance,
        "createRecordComponent": (function(_this) {
          return function(record, colNum) {
            return typeof _this.createRecordComponent === "function" ? _this.createRecordComponent(record, colNum) : void 0;
          };
        })(this),
        "updateRecordComponent": (function(_this) {
          return function(record, colNum, component, recordChanged) {
            return typeof _this.updateRecordComponent === "function" ? _this.updateRecordComponent(record, colNum, component, recordChanged) : void 0;
          };
        })(this),
        "expansionDetailFieldProperties": {
          "canSelectText": this.canSelectTextExpandedField
        },
        "canSelectCells": this.canSelectCells,
        "alternateRecordStyles": this.alternateRecordStyles,
        "groupByField": this.groupByField,
        "cancelEditingConfirmationMessage": this.cancelEditingConfirmationMessage,
        "focusChanged": function() {
          simpleSyS.setFuncMenu(this.funcMenu);
        },
        "fetchDelay": this.fetchDelay,
        "editByCell": this.editByCell,
        "editEvent": this.editEvent,
        "showFilterEditor": this.showFilterEditor,
        "dataPageSize": this.dataPageSize,
        "cellChanged": this.cellChanged,
        "getExpansionComponent": this.getExpansionComponent,
        "editComplete": this.editComplete,
        "defaultFields": this.defaultFields,
        "dragTrackerMode": this.dragTrackerMode,
        "canHover": this.canHover,
        "hoverWidth": this.hoverWidth,
        "canResizeFields": this.canResizeFields,
        "dragDataAction": this.dragDataAction,
        "canReorderRecords": this.canReorderRecords,
        "canAcceptDroppedRecords": this.canAcceptDroppedRecords,
        "trackerImage": this.trackerImage,
        "newRequestProperties": this.newRequestProperties,
        "editRequestProperties": this.editRequestProperties,
        "saveByCell": this.saveByCell,
        "editingFields": this.editingFields,
        "editWindowProperties": this.editWindowProperties,
        "canDragRecordsOut": this.canDragRecordsOut,
        "dateFormatter": this.dateFormatter,
        "datetimeFormatter": this.datetimeFormatter,
        "selectFirstRecordAfterFetch": this.selectFirstRecordAfterFetch,
        "expandRecord": this.expandRecord,
        "expandRecords": this.expandRecords
      });
      if ((this.rowClick != null) && isc.isA.Functtion(this.rowClick)) {
        this.grid.rowClick = this.rowClick;
      }
      if (isc.isA.Function(this.selectionChanged)) {
        this.setSelectionChanged(this.selectionChanged);
      }
      this.grid.selectionChanged = (function(_this) {
        return function(record, state) {
          var ref1;
          if ((ref1 = _this.grid.selectionChangedQueue) != null) {
            ref1.forEach(function(func) {
              if (isc.isA.Function(func) === true) {
                func(record, state);
              }
            });
          }
        };
      })(this);
      if (isc.isA.Function(this.selectionUpdated)) {
        this.setSelectionUpdated(this.selectionUpdated);
      }
      this.grid.selectionUpdated = (function(_this) {
        return function(record, recordList) {
          var ref1;
          if ((ref1 = _this.grid.selectionUpdatedQueue) != null) {
            ref1.forEach(function(func) {
              if (isc.isA.Function(func) === true) {
                func(record, recordList);
              }
            });
          }
        };
      })(this);
      this.initAdvancedFilter();
      if (this.masterGrid != null) {
        this.grid.setMasterGrid(this.masterGrid);
      }
      if (this.autoSaveConfig === true) {
        this.getViewState();
      }
      if (this.contextMenu) {
        if (this.saveItems && !isc.isA.Array(this.saveItems)) {
          this.saveItems = [this.saveItems];
        }
        if (this.autoSaveEdits && this.saveItems) {
          this.contextMenu.removeItems(this.saveItems);
        }
        this.grid.contextMenu = (isc.MenuSS.create({
          items: this.contextMenu.items,
          owner: this
        })).getMergedContextMenu();
        this.contextMenu = null;
      } else {
        this.grid.contextMenu = (isc.MenuSS.create({
          owner: this
        })).getMergedContextMenu();
      }
      this.grid.contextMenu.setOwner(this);
      if ((this.dataSource != null) && this.autoFetchData) {
        if ((ref1 = this.filterBuilder) != null) {
          ref1.setCriteria(this.initialCriteria);
        }
        this.fetchData(this.initialCriteria);
      }
      if (isc.isA.Function(this.canSelectRecord)) {
        this.grid.canSelectRecord = this.canSelectRecord;
      }
    },
    "fetchDelay": 500,
    "startEditingNew": function(newValues, suppressFocus) {
      if (this.grid.masterGrid != null) {
        this.setForignFieldFields(this.grid, this.grid.masterGrid);
      }
      if (newValues != null) {
        this.grid.startEditingNew(newValues, suppressFocus);
      } else {
        this.grid.startEditingNew(this.dataSource.wildRecordJS, suppressFocus);
      }
    },
    "editEvent": "doubleClick",
    "getEditRecord": function() {
      return this.grid.getRecord(this.grid.getEditRow());
    },
    "getSelectedRecord": function() {
      return this.grid.getSelectedRecord();
    },
    "getSelectedRecords": function() {
      return this.grid.getSelectedRecords();
    },
    "isSelected": function(record) {
      return this.grid.isSelected(record);
    },
    "canSelectTextExpandedField": true,
    "discardAllEdits": function() {
      this.grid.discardAllEdits();
    },
    "getSelectedState": function() {
      return this.grid.getSelectedState();
    },
    "getRecord": function(data) {
      var index;
      if (isc.isA.Object(data) === true) {
        index = this.grid.getRecordIndex(data);
        return this.grid.getRecord(index);
      } else if (isc.isA.Number(data) === true) {
        return this.grid.getRecord(data);
      }
    },
    "showFilterEditor": true,
    "dataPageSize": 75,
    "getEditorType": function(field, values) {
      return this.grid.Super("getEditorType", arguments);
    },
    "deselectRecord": function(record) {
      this.grid.deselectRecord(record);
    },
    "deselectRecords": function(records) {
      this.grid.deselectRecords(records);
    },
    "deselectAllRecords": function() {
      this.grid.deselectAllRecords();
    },
    "getFieldName": function(colNum) {
      return this.grid.getFieldName(colNum);
    },
    "getRowNum": function(record) {
      return this.grid.getRowNum(record);
    },
    "setSelectionAppearance": function(selectionAppearance) {
      this.grid.setSelectionAppearance(selectionAppearance);
    },
    "setSelectionType": function(selectionType) {
      this.grid.setSelectionType(selectionType);
    }
  });

}).call(this);
