(function() {
  isc.defineClass("ListGridItem", isc.CanvasItem).addProperties({
    height: "*",
    width: "*",
    autoFetchData: true,
    fixedRecordHeights: true,
    wrapCells: false,
    autoFitWidthApproach: "title",
    canEdit: true,
    autoDraw: false,
    selectionAppearance: "rowStyle",
    canResizeFields: true,
    findByKey: function(keyValue) {
      return this.grid.findByKey(keyValue);
    },
    isSelected: function(record) {
      return this.grid.isSelected(record);
    },
    deselectRecord: function(record) {
      this.grid.deselectRecord(record);
    },
    deselectRecords: function(records) {
      this.grid.deselectRecords(records);
    },
    deselectAllRecords: function() {
      this.grid.deselectAllRecords();
    },
    selectRecord: function(record) {
      this.grid.selectRecord(record);
    },
    selectRecords: function(records) {
      this.grid.selectRecords(records);
    },
    getSelectedRecord: function() {
      return this.grid.getSelectedRecord();
    },
    getSelectedRecords: function() {
      return this.grid.getSelectedRecords();
    },
    getSelection: function() {
      return this.grid.getSelection();
    },
    getDataSource: function() {
      return this.grid.getDataSource();
    },
    createCanvas: function(form, item) {
      if (this.grid == null) {
        this.grid = isc.ListGridEditor.create({
          canEdit: this.canEdit,
          autoDraw: this.autoDraw,
          width: this.width,
          height: this.height,
          dataSource: this.dataSource,
          fields: this.fields,
          autoFetchData: this.autoFetchData,
          fixedRecordHeights: this.fixedRecordHeights,
          autoFitWidthApproach: this.autoFitWidthApproach,
          cellChanged: this.cellChanged,
          selectionUpdated: this.selectionUpdated,
          selectionChanged: this.selectionChanged,
          wrapCells: this.wrapCells,
          canResizeFields: this.canResizeFields,
          selectionType: this.selectionType,
          selectionAppearance: this.selectionAppearance,
          initialSort: this.initialSort
        });
        this.unsetSelectionUpdated = (function(_this) {
          return function(func) {
            _this.grid.unsetSelectionUpdated(func);
          };
        })(this);
        this.setSelectionUpdated = (function(_this) {
          return function(func) {
            _this.grid.setSelectionUpdated(func);
          };
        })(this);
        this.unsetSelectionChanged = (function(_this) {
          return function(func) {
            _this.grid.unsetSelectionChanged(func);
          };
        })(this);
        this.setSelectionChanged = (function(_this) {
          return function(func) {
            _this.grid.setSelectionChanged(func);
          };
        })(this);
        this.fetchData = (function(_this) {
          return function(criteria, callback, requestProperties) {
            _this.grid.fetchData(criteria, callback, requestProperties);
          };
        })(this);
      }
      if (form.gridItem == null) {
        form.gridItem = this.grid;
      } else if (isc.isA.Array(form.gridItem) === false) {
        form.gridItem.add(this.grid);
      } else {
        form.gridItem = [form.gridItem];
        form.gridItem.add(this.grid);
      }
      if (this.dataFetchMode != null) {
        this.grid.dataFetchMode = this.dataFetchMode;
      }
      return this.grid;
    }
  });

}).call(this);
