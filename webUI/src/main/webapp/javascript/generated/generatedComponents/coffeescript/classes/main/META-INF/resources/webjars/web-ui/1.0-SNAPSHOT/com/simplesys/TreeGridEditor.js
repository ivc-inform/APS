(function() {
  isc.defineClass("TreeGridEditor", isc.VLayoutSS, isc.GridEditorInterface).addProperties({
    "hoverWidth": 300,
    "canDragSelectText": true,
    "showOpenIcons": true,
    "autoFitFieldWidths": false,
    "canAutoFitWidth": false,
    "findByKey": function(keyValue) {
      return this.grid.findByKey(keyValue);
    },
    "removeData": function(removeRecord, callback, requestProperties) {
      this.grid.removeData(removeRecord, callback, requestProperties);
    },
    "saveAllEdits": function(rows, saveCallback) {
      this.grid.saveAllEdits(rows, saveCallback);
    },
    "recordComponentPoolingMode": "viewport",
    "dragTrackerMode": "icon",
    "showAllRecords": false,
    "canAcceptDroppedRecords": false,
    "selectFirstRecordAfterFetch": true,
    "applyRecordData": function(recordDate) {
      this.grid.applyRecordData(recordDate);
    },
    "invalidateRecordComponents": function() {
      this.grid.invalidateRecordComponents();
    },
    "refreshRecordComponent": function(rowNum, colNum) {
      this.grid.refreshRecordComponent(recordDate, colNum);
    },
    "setEditValue": function(rowNum, colNum, value) {
      this.grid.refreshRecordComponent(rowNum, colNum, value);
    },
    "getEditFormItem": function(field) {
      return this.grid.getEditFormItem(field);
    },
    "fetchData": function(criteria, callback, requestProperties) {
      var _callback, ref;
      if (!criteria) {
        criteria = this.grid.criteria;
      }
      if (this.useClientFilteringSorting === false) {
        _callback = (function(_this) {
          return function(dsResponse, data, dsRequest) {
            if (_this.selectFirstRecordAfterFetch) {
              _this.grid.selectFirstRecord();
            }
            _this.fireCallback(callback);
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
            _this.fireCallback(callback);
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
    "getData": function() {
      return this.grid.getData();
    },
    "showRecordComponentsByCell": false,
    "loadDataOnDemand": false,
    "showRecordComponents": false,
    "showAllChild": false,
    "openedAll": false,
    "drawAheadRatio": 1.3,
    "autoSaveEdits": true,
    "autoDraw": false,
    "selectByIds": function(ids) {
      var data;
      if (ids == null) {
        return void 0;
      }
      if (isc.isA.Array(ids) === false) {
        ids = [ids];
      }
      data = this.getData();
      if (data != null) {
        ids.forEach(function(id) {
          return this.grid.selectRecord(data.findById(id));
        });
      }
    },
    "setSelectedRecordsAsFolders": function() {
      return this.setRecordsAsFolders(this.getSelectedRecords());
    },
    "setRecordsAsFolders": function(records) {
      var nodes;
      nodes = this.selectByIds(records);
      return nodes;
    },
    "cascadeSelection": false,
    "getFuncMenu": function() {
      return this.funcMenu;
    },
    "canEdit": false,
    "selectFirstRecord": function() {
      return this.grid.selectFirstRecord();
    },
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
    "selectionType": "single",
    "autoFetchData": true,
    "showRowNumbers": true,
    "autoSaveConfig": true,
    "selectSingleRecord": function(record) {
      if (this.grid.getSelectionLength() === 0) {
        this.grid.selectRecord(record);
      } else {
        this.grid.selectSingleRecord(record);
      }
    },
    "removeSelectedData": function(callback, requestProperties) {
      var parents;
      parents = this.grid.getSelectedRecords().map((function(_this) {
        return function(record) {
          return _this.grid.data.getParent(record);
        };
      })(this));
      this.grid.removeSelectedData((function(_this) {
        return function(dsResponse, data, dsRequest) {
          parents.forEach((function(node) {
            node.isFolder = _this.grid.data.hasChildren(node);
          }));
          _this.fireCallback(callback);
        };
      })(this), requestProperties);
    },
    "showAdvancedFilter": false,
    "showSelectedStyle": true,
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
    "selectSingleRecordByKey": function(keyValue, newState, callback) {
      var node, parents;
      node = this.grid.data.findById(keyValue);
      if (node != null) {
        parents = this.grid.data.getParents(node);
        parents.forEach((function(_this) {
          return function(parent) {
            _this.grid.data.openFolder(parent);
          };
        })(this));
        this.grid.selectRecord(node, newState);
        return isc.Class.fireCallback(callback);
      } else {
        return null;
      }
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
    "setData": function(newData) {
      this.grid.setData(newData);
    },
    "autoFitWidthApproach": "title",
    "startEditing": function(newLine) {
      var e, record;
      record = this.grid.getRowNumSelectedGridRecord();
      if (newLine === true) {
        try {
          this.grid.startEditing(record.rowNum + 1, record.colNum);
        } catch (error) {
          e = error;
          this.grid.startEditing(record.rowNum, record.colNum);
        }
      } else {
        this.grid.startEditing(record.rowNum, record.colNum);
      }
    },
    "canReparentNodes": false,
    "addData": function(newRecord, callback, requestProperties) {
      this.grid.addData(newRecord, callback, requestProperties);
    },
    "modalEditing": true,
    "selectionAppearance": "rowStyle",
    "openAll": function() {
      this.grid.filterData(this.grid.getCriteria(), function() {
        this.grid.getData().openAll();
      });
    },
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
    "fullRefresh": function(initialCriteria) {
      var criteria, selectedState;
      selectedState = this.grid.getSelectedState();
      criteria = isc.addProperties((this.grid.getCriteria() != null ? this.grid.getCriteria() : {}), initialCriteria);
      criteria.ts = simpleSyS.timeStamp();
      this.fetchData(criteria);
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
      this.grid.setViewState(savedState);
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
    "dropIconSuffix": "drop",
    "openIconSuffix": "open",
    "closedIconSuffix": "closed",
    "getContextMenu": function() {
      return this.grid.contextMenu;
    },
    "getExpansionComponent": function() {
      return null;
    },
    "getFieldName": function(colNum) {
      return this.grid.getFieldName(colNum);
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
      this.grid = isc.TreeGrid.create({
        "autoFetchData": false,
        "dataFetchMode": this.dataFetchMode,
        "identifier": this.identifier,
        "autoFitFieldWidths": this.autoFitFieldWidths,
        "dropIconSuffix": this.dropIconSuffix,
        "recordComponentPoolingMode": this.recordComponentPoolingMode,
        "showAllRecords": this.showAllRecords,
        "rowContextClick": this.rowContextClick,
        "dataProperties": this.dataProperties,
        "getBaseStyle": this.getBaseStyle,
        "clientOnly": this.clientOnly,
        "showRecordComponentsByCell": this.showRecordComponentsByCell,
        "loadDataOnDemand": this.loadDataOnDemand,
        "showRecordComponents": this.showRecordComponents,
        "drawAheadRatio": this.drawAheadRatio,
        "cellClick": this.cellClick,
        "detailField": this.detailField,
        "autoSaveEdits": this.autoSaveEdits,
        "canAcceptDroppedRecords": this.canAcceptDroppedRecords,
        "autoDraw": false,
        "initialSort": this.initialSort,
        "showOpenIcons": this.showOpenIcons,
        "cascadeSelection": this.cascadeSelection,
        "canEdit": this.canEdit,
        "canSort": this.canSort,
        "filterOnKeypress": this.filterOnKeypress,
        "selectionType": this.selectionType,
        "showRollOver": this.showRollOver,
        "folderIcon": this.folderIcon,
        "showRowNumbers": this.showRowNumbers,
        "canExpandRecords": this.canExpandRecords,
        "showSelectedStyle": this.showSelectedStyle,
        "data": this.data,
        "owner": this,
        "emptyMessage": this.emptyMessage,
        "wrapCells": this.wrapCells,
        "openIconSuffix": this.openIconSuffix,
        "autoFetchTextMatchStyle": this.autoFetchTextMatchStyle,
        "expansionMode": this.expansionMode,
        "fields": this.fields,
        "canDragSelectText": this.canDragSelectText,
        "dataSource": this.dataSource,
        "fixedRecordHeights": this.fixedRecordHeights,
        "autoFitWidthApproach": this.autoFitWidthApproach,
        "canReparentNodes": this.canReparentNodes,
        "modalEditing": this.modalEditing,
        "selectionAppearance": this.selectionAppearance,
        "createRecordComponent": this.createRecordComponent,
        "updateRecordComponent": this.updateRecordComponent,
        "canSelectText": this.canSelectText,
        "expansionDetailFieldProperties": {
          "canSelectText": this.canSelectTextExpandedField
        },
        "canSelectCells": this.canSelectCells,
        "nodeIcon": this.nodeIcon,
        "alternateRecordStyles": this.alternateRecordStyles,
        "closedIconSuffix": this.closedIconSuffix,
        "cancelEditingConfirmationMessage": this.cancelEditingConfirmationMessage,
        "focusChanged": function() {
          simpleSyS.setFuncMenu(this.funcMenu);
        },
        "fetchDelay": this.fetchDelay,
        "editByCell": this.editByCell,
        "editEvent": this.editEvent,
        "showDropIcons": this.showDropIcons,
        "showPartialSelection": this.showPartialSelection,
        "showFilterEditor": this.showFilterEditor,
        "dataPageSize": this.dataPageSize,
        "cellChanged": this.cellChanged,
        "getExpansionComponent": this.getExpansionComponent,
        "editComplete": this.editComplete,
        "defaultFields": this.defaultFields,
        "dragTrackerMode": this.dragTrackerMode,
        "canHover": this.canHover,
        "hoverWidth": this.hoverWidth,
        "dragDataAction": this.dragDataAction,
        "canDragRecordsOut": this.canDragRecordsOut,
        "canReorderRecords": this.canReorderRecords,
        "trackerImage": this.trackerImage,
        "newRequestProperties": this.newRequestProperties,
        "editRequestProperties": this.editRequestProperties,
        "editingFields": this.editingFields,
        "saveByCell": this.saveByCell,
        "editWindowProperties": this.editWindowProperties,
        "canDragRecordsOut": this.canDragRecordsOut,
        "dateFormatter": this.dateFormatter,
        "datetimeFormatter": this.datetimeFormatter,
        "selectFirstRecordAfterFetch": this.selectFirstRecordAfterFetch
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

      /*@grid.contextMenu.log() */
      if (this.masterGrid != null) {
        this.grid.setMasterGrid(this.masterGrid);
      }
      if ((this.dataSource != null) && this.autoFetchData) {
        if ((ref1 = this.filterBuilder) != null) {
          ref1.setCriteria(this.initialCriteria);
        }
        this.fetchData(this.initialCriteria, (function(_this) {
          return function() {
            _this.selectFirstRecord();
          };
        })(this));
      }
      this.grid.canReparentNodes = false;
      if (isc.isA.Function(this.canSelectRecord)) {
        this.grid.canSelectRecord = this.canSelectRecord;
      }
    },
    "fetchDelay": 500,
    "setMasterGrid": function(grid, pkFieldNames) {
      this.grid.setMasterGrid(grid, pkFieldNames);
    },
    "localEditingNew": true,
    "startEditingNew": function(newValues, suppressFocus) {
      var record;
      if (newValues != null) {
        this.grid.startEditingNew(newValues, suppressFocus);
      } else {
        record = this.getSelectedRecord();
        if (record != null) {
          this.setForignFieldFields(this.grid);
          if (this.grid.masterGrid != null) {
            this.setForignFieldFields(this.grid, this.grid.masterGrid);
          }
        }
        if (this.grid.getSelectedRecords().length === 0) {
          this.dataSource.wildRecordJS = void 0;
        }
        this.dataSource.addData(this.dataSource.wildRecordJS, (function(_this) {
          return function(dsResponse, data, dsRequest) {
            var _node, id, parent;
            id = data[0][_this.grid.data.idField];
            _node = _this.grid.data.findById(id);
            parent = _this.grid.data.getParent(_node);
            _this.grid.data.openFolder(parent, function(node) {
              _this.grid.selectSingleRecord(_node);
              _this.startEditing();
            });
          };
        })(this));
      }
    },
    "editEvent": "doubleClick",
    "getEditRecord": function() {
      return this.grid.getRecord(this.grid.getEditRow());
    },
    "showDropIcons": true,
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
    "getRecord": function(data) {
      var index;
      if (isc.isA.Object(data) === true) {
        index = this.grid.getRecordIndex(data);
        return this.grid.getRecord(index);
      } else if (isc.isA.Number(data) === true) {
        return this.grid.getRecord(data);
      }
    },
    "getSelectedState": function() {
      return this.grid.getSelectedState();
    },
    "showPartialSelection": false,
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
