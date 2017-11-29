(function() {
  var simpleSyS;

  simpleSyS = this.simpleSyS;

  isc.defineClass("TreeListGridEditor", isc.HLayoutSS).addProperties({
    "getTreeSelectedRecord": function() {
      return this.treeGrid.getSelectedRecord();
    },
    "getListSelection": function() {
      return this.listGrid.getSelection();
    },
    "findByKeyList": function(keyValue) {
      return this.listGrid.findByKey(keyValue);
    },
    "findByKeyTree": function(keyValue) {
      return this.treeGrid.findByKey(keyValue);
    },
    "discardListAllEdits": function() {
      this.listGrid.discardAllEdits();
    },
    "discardTreeAllEdits": function() {
      this.treeGrid.discardAllEdits();
    },
    "updateListData": function(updatedRecord, callback, requestProperties) {
      this.listGrid.updateData(updatedRecord, callback, requestProperties);
    },
    "updateTreeData": function(updatedRecord, callback, requestProperties) {
      this.treeGrid.updateData(updatedRecord, callback, requestProperties);
    },
    "removeListSelectedData": function() {
      this.listGrid.removeSelectedData();
    },
    "removeTreeSelectedData": function() {
      this.treeGrid.removeSelectedData();
    },
    "dataArrivedTree": function(startRecord, endRecord) {
      void 0;
    },
    "dataArrivedList": function(startRecord, endRecord) {
      void 0;
    },
    "widthTree": "300",
    "widthList": "*",
    "fetchData": function(criteria, callback, requestProperties) {
      this.treeGrid.fetchData(criteria, callback, requestProperties);
    },
    "fetchTreeData": function(criteria, callback, requestProperties) {
      this.treeGrid.fetchData(criteria, callback, requestProperties);
    },
    "fetchListData": function(criteria, callback, requestProperties) {
      this.listGrid.fetchData(criteria, callback, requestProperties);
    },
    "fetchTreeDelay": 500,
    "fetchListDelay": 500,
    "showTreeRecordComponents": false,
    "showListRecordComponents": false,
    "selectListSingleRecordByKey": function(keyValue, newStyle, callback) {
      this.listGrid.selectSingleRecordByKey(keyValue, newStyle, callback);
    },
    "selectTreeSingleRecordByKey": function(keyValue, newStyle, callback) {
      this.treeGrid.selectSingleRecordByKey(keyValue, newStyle, callback);
    },
    "editByCellTree": false,
    "autoSaveListEdits": true,
    "autoSaveTreeEdits": true,
    "editByCellList": false,
    "getListSelectedRecord": function() {
      return this.listGrid.getSelectedRecord();
    },
    "enableChangeSelectionTree": true,
    "showAllChild": false,
    "selectionTypeTree": "single",
    "selectionTypeList": "single",
    "getTreeSelection": function() {
      return this.treeGrid.getSelection();
    },
    "autoDraw": false,
    "showTreeFilterEditor": true,
    "cancelTreeEditing": function() {
      this.treeGrid.cancelEditing();
    },
    "canAcceptDroppedRecordsTree": false,
    "getFuncMenu": function() {
      return this.funcMenu;
    },
    "getTreeCriteria": function() {
      return this.treeGrid.getCriteria();
    },
    "autoFetchData": true,
    "startListEditing": function() {
      this.listGrid.startEditing();
    },
    "setTreeFields": function(fields) {
      this.treeGrid.setFields(fields);
    },
    "showTreeAdvancedFilter": false,
    "startTreeEditingNew": function() {
      this.treeGrid.startEditingNew();
    },
    "saveListAllEdits": function(rows, saveCallback) {
      this.listGrid.saveAllEdits(rows, saveCallback);
    },
    "cancelEditingConfirmationMessageList": "Выход из режима редактирования повлечет к утере измененных(введенных) данных. Продолжить ?",
    "cancelEditingConfirmationMessageTree": "Выход из режима редактирования повлечет к утере измененных(введенных) данных. Продолжить ?",
    "showListAdvancedFilter": false,
    "saveTreeAllEdits": function(rows, saveCallback) {
      this.treeGrid.saveAllEdits(rows, saveCallback);
    },
    "canAcceptDropTree": false,
    "drawAheadRatioList": 1.3,
    "setListSelectedState": function(selectedState) {
      this.listGrid.setSelectedState(selectedState);
    },
    "getListSelectedState": function() {
      return this.listGrid.getSelectedState();
    },
    "drawAheadRatioTree": 1.3,
    "startListEditingNew": function(newValues, suppressFocus) {

      /*selection = @getTreeSelection()
      		if selection.getLength() is 0
      			isc.error "Сначала выбирите группу."
      		else if selection.getLength() inst 1
      			isc.error "Должна быть выбрана одна группа."
      		else
       */
      this.listGrid.startEditingNew(newValues, suppressFocus);
    },
    "setFuncMenu": function(funcMenu) {
      this.funcMenu = funcMenu;
    },
    "showResizeBarList": false,
    "fullTreeRefresh": function() {
      this.treeGrid.fullRefresh();
    },
    "getListEditRecord": function() {
      return this.listGrid.getRecord(this.listGrid.getEditRow());
    },
    "heightTree": "100%",
    "heightList": "100%",
    "setTreeSelectedState": function(selectedState) {
      this.treeGrid.setSelectedState(selectedState);
    },
    "getTreeSelectedState": function() {
      return this.treeGrid.getSelectedState();
    },
    "canSelectCellsList": false,
    "showTreeRecordComponentsByCell": false,
    "canSelectCellsTree": false,
    "emptyMessageTree": "<h1>Данных нет.</h1>",
    "showResizeBarTree": true,
    "emptyMessageList": "<h1>Данных нет.</h1>",
    "showListFilterEditor": true,
    "setTreeDataSource": function(dataSource) {
      this.treeGrid.setDataSource(dataSource);
    },
    "canReparentNodesTree": true,
    "filterTreeOnKeypress": true,
    "cancelListEditing": function() {
      this.listGrid.cancelEditing();
    },
    "dataPageSizeTree": 75,
    "dataPageSizeList": 75,
    "setListDataSource": function(dataSource) {
      this.listGrid.setDataSource(dataSource);
    },
    "showOpenIconsTree": true,
    "height": "100%",
    "autoFetchTextMatchStyleTree": "substring",
    "loadDataOnDemandTree": false,
    "autoFetchTextMatchStyleList": "substring",
    "startTreeEditing": function() {
      this.treeGrid.startEditing(treeGrid.getRowNumSelectedGridRecord());
    },
    "width": "100%",
    "canEditTree": true,
    "canEditList": true,
    "setContextMenuTreeGridEditor": function(menu) {
      this.contextMenuTreeGridEditor = menu.setOwner(this.treeGrid);
      this.treeGrid.setContextMenu(menu);
      return this.contextMenuTreeGridEditor;
    },
    "setContextMenuListGridEditor": function(menu) {
      this.contextMenuListGridEditor = menu.setOwner(this.listGrid);
      this.listGrid.setContextMenu(menu);
      return this.contextMenuListGridEditor;
    },
    "closedIconSuffix": "",
    "dataFetchModeList": "paged",
    "dataFetchModeTree": "basic",
    "canDragRecordsOutList": false,
    "canDragRecordsOutTree": false,
    "refreshDataList": function(callback) {
      this.listGrid.refreshData(callback);
    },
    "refreshDataTree": function(callback) {
      this.treeGrid.refreshData(callback);
    },
    "refreshRecordComponentTree": function(rowNum, colNum) {
      this.treeGrid.refreshRecordComponent(recordDate, colNum);
    },
    "refreshRecordComponentList": function(rowNum, colNum) {
      this.listGrid.refreshRecordComponent(recordDate, colNum);
    },
    "initWidget": function() {
      var ch;
      this.Super("initWidget", arguments);
      this.treeGrid = isc.TreeGridEditor.create({
        "showRecordComponentsByCell": this.showTreeRecordComponentsByCell,
        "folderDrop": this.folderDropTree,
        "loadDataOnDemand": this.loadDataOnDemandTree,
        "showRecordComponents": this.showTreeRecordComponents,
        "drawAheadRatio": this.drawAheadRatioTree,
        "autoDraw": false,
        "initialSort": this.initialSortTree,
        "canEdit": this.canEditTree,
        "selectionType": this.selectionTypeTree,
        "autoFetchData": this.autoFetchData,
        "folderIcon": this.folderIconTree,
        "showAdvancedFilter": this.showTreeAdvancedFilter,
        "contextMenu": this.contextMenuTreeGridEditor,
        "identifier": (this.getIdentifier()) + "_treeGrid",
        "owner": this,
        "emptyMessage": this.emptyMessageTree,
        "wrapCells": this.wrapTreeCells,
        "canAcceptDroppedRecords": this.canAcceptDroppedRecordsTree,
        "fields": this.fieldsTree,
        "defaultFields": this.defaultFieldsTree,
        "showResizeBar": true,
        "dataSource": this.dataSourceTree,
        "canReparentNodes": this.canReparentNodesTree,
        "canSelectCells": this.canSelectCellsTree,
        "folderDropImage": this.folderDropImageTree,
        "height": this.heightTree,
        "nodeIcon": this.nodeIconTree,
        "width": "30%",
        "cancelEditingConfirmationMessage": this.cancelEditingConfirmationMessageTree,
        "editByCell": this.editByCellTree,
        "showOpenIcons": this.showOpenIconsTree,
        "showFilterEditor": this.showTreeFilterEditor,
        "dataPageSize": this.dataPageSizeTree,
        "closedIconSuffix": this.closedIconSuffix,
        "newRequestProperties": this.newTreeRequestProperties,
        "editRequestProperties": this.editTreeRequestProperties,
        "editingFields": this.editingTreeFields,
        "autoSaveEdits": this.autoSaveTreeEdits,
        "saveByCell": this.saveByTreeCell,
        "dataFetchMode": this.dataFetchModeTree,
        "editWindowProperties": this.editWindowPropertiesTree,
        "canDragSelectText": this.canDragSelectText,
        "canDragRecordsOut": this.canDragRecordsOutTree,
        "trackerImage": this.trackerImageTree,
        "selectionAppearance": this.selectionAppearanceTree,
        "selectFirstRecordAfterFetch": this.selectFirstRecordAfterFetchTree,
        "createRecordComponent": this.createTreeRecordComponent,
        "updateRecordComponent": this.updateTreeRecordComponent,
        "recordComponentPoolingMode": this.recordTreeComponentPoolingMode,
        "dataArrived": this.dataArrivedTree,
        "resized": function() {
          if (this.isDrawn() === true) {
            isc.OfflineSS.putNumber((this.getIdentifier()) + ".width", this.getWidth());
          }
        }
      });
      this.treeGrid.setWidth(isc.OfflineSS.getNumber((this.treeGrid.getIdentifier()) + ".width", this.treeGrid.getWidth()));

      /*simpleSyS._initMenus @treeGrid
      		simpleSyS._RecordComponent @treeGrid, "create"
      		simpleSyS._RecordComponent @treeGrid, "update"
       */
      this.listGrid = isc.ListGridEditor.create({
        "showRecordComponentsByCell": this.showListRecordComponentsByCell,
        "showRecordComponents": this.showListRecordComponents,
        "autoDraw": false,
        "drawAheadRatio": this.drawAheadRatioList,
        "initialSort": this.initialSortList,
        "canEdit": this.canEditList,
        "filterOnKeypress": this.filterListOnKeypress,
        "selectionType": this.selectionTypeList,
        "autoFetchData": this.autoFetchData,
        "masterGrid": this.treeGrid,
        "showAdvancedFilter": this.showListAdvancedFilter,
        "contextMenu": this.contextMenuListGridEditor,
        "identifier": (this.getIdentifier()) + "_listGrid",
        "owner": this,
        "emptyMessage": this.emptyMessageList,
        "wrapCells": this.wrapListCells,
        "autoFetchTextMatchStyle": this.autoFetchTextMatchStyleList,
        "fields": this.fieldsList,
        "defaultFields": this.defaultFieldsList,
        "showResizeBar": false,
        "dataSource": this.dataSourceList,
        "createRecordComponent": this.createListRecordComponent,
        "updateRecordComponent": this.updateListRecordComponent,
        "canSelectCells": this.canSelectCellsList,
        "height": this.heightList,
        "cancelEditingConfirmationMessage": this.cancelEditingConfirmationMessageList,
        "width": "*",
        "fetchDelay": this.fetchListDelay,
        "editByCell": this.editByCellList,
        "dataPageSize": this.dataPageSizeList,
        "showFilterEditor": this.showListFilterEditor,
        "newRequestProperties": this.newListRequestProperties,
        "editRequestProperties": this.editListRequestProperties,
        "editingFields": this.editingListFields,
        "autoSaveEdits": this.autoSaveListEdits,
        "saveByCell": this.saveByListCell,
        "dataFetchMode": this.dataFetchModeList,
        "editWindowProperties": this.editWindowPropertiesList,
        "canDragSelectText": this.canDragSelectText,
        "canDragRecordsOut": this.canDragRecordsOutList,
        "trackerImage": this.trackerImageList,
        "selectionAppearance": this.selectionAppearanceList,
        "selectFirstRecordAfterFetch": this.selectFirstRecordAfterFetchList,
        "recordComponentPoolingMode": this.recordListComponentPoolingMode,
        "dataArrived": this.dataArrivedList
      });

      /*simpleSyS._initMenus @listGrid
      		simpleSyS._RecordComponent @listGrid, "create"
      		simpleSyS._RecordComponent @listGrid, "update"
       */

      /*@treeGrid.grid.contextMenu.log()
      		@listGrid.grid.contextMenu.log()
       */
      this.addMember(ch = isc.ChainMasterDetail.create({
        members: [this.treeGrid, this.listGrid]
      }));
      ch.getViewState();
      if ((this.dataSourceTree != null) && (this.dataSourceList != null) && this.autoFetchData === true) {
        this.fetchData(this.getTreeCriteria(), (function(_this) {
          return function(dsResponse, data, dsRequest) {
            _this.treeGrid.selectFirstRecord();
          };
        })(this));
      }
    },
    "fullListRefresh": function() {
      this.listGrid.fullRefresh();
    },
    "filterListOnKeypress": true,
    "setListFields": function(fields) {
      this.listGrid.setFields(fields);
    },
    "showListRecordComponentsByCell": false,
    "setTreeCanReparentNodes": function(value) {
      this.treeGrid.canReparentNodes = value;
    },
    "getListCriteria": function() {
      return this.listGrid.getCriteria();
    },
    "getListGrid": function() {
      return this.listGrid;
    },
    "getTreeGrid": function() {
      return this.treeGrid;
    },
    "getViewState": function() {
      this.listGrid.getViewState();
      this.treeGrid.getViewState();
    }
  });

}).call(this);
