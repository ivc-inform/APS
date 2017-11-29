(function() {
  isc.defineClass("LookupEditor", isc.HLayoutSS).addProperties({
    "autoDraw": false,
    "height": "100%",
    "width": "100%",
    headerIconPath: "condition.png",
    "initWidget": function() {
      this.Super("initWidget", arguments);
      this.addMembers([
        {
          "width": "*",
          "canEdit": false,
          click: (function(_this) {
            return function() {
              var ref, ref1;
              if (((ref = _this.owner) != null ? (ref1 = ref.grid) != null ? ref1.selectionType : void 0 : void 0) !== "multiple" || (!isc.EventHandler.ctrlKeyDown() && !isc.EventHandler.shiftKeyDown())) {
                _this.owner.grid.deselectAllRecords();
              }
              _this.owner.grid.selectRecord(_this.record);
              return false;
            };
          })(this)
        }, isc.IButtonSS.create({
          "iconAlign": "center",
          "click": (function(_this) {
            return function() {
              _this._lookupEditor = _this.foreignField.foreignKey.substring(0, _this.foreignField.foreignKey.lastIndexOf('.'));
              _this._lookupEditor = _this._lookupEditor.substring(0, _this._lookupEditor.lastIndexOf('_'));
              _this._pk = _this.foreignField.foreignKey.substring(_this.foreignField.foreignKey.lastIndexOf('.') + 1);
              _this._wrappedComponent = _this.evaluate("isc.Editor" + _this._lookupEditor + ".create({identifier: identifier, selectionType: selectionType})", {
                identifier: _this.editorIdentifier + "wrappedComponent",
                selectionType: "single"
              });
              if (isc.isA.TreeGridEditor(_this._wrappedComponent)) {
                _this._wrappedComponent.grid.dataArrived = function() {
                  _this._wrappedComponent.selectSingleRecordByKey(_this.record[_this.foreignField.name]);
                };
              } else if (isc.isA.ListGridEditor(_this._wrappedComponent)) {
                _this._wrappedComponent.grid.dataArrived = function() {
                  _this._wrappedComponent.selectSingleRecordByKey(_this.record[_this.foreignField.name]);
                };
              } else {
                isc.Log.logError("Bad branch of " + (_this._wrappedComponent.getClassName()));
              }
              isc.WindowWrapper.create({
                identifier: _this.editorIdentifier,
                record: _this.record,
                headerIconPath: _this.headerIconPath,
                wrappedComponent: _this._wrappedComponent,
                title: _this.lookupFields.length > 0 ? _this.lookupFields[0].field_captionClassLookup : void 0,
                okFunction: function() {
                  _this.record[_this.foreignField.name] = _this._wrappedComponent.getSelectedRecord()[_this._pk];
                  _this.lookupFields.forEach(function(field) {
                    _this.record[field.field_name] = _this._wrappedComponent.getSelectedRecord()[field.field_name];
                  });
                  _this.owner.updateData(_this.record);
                }
              });
            };
          })(this),
          "icon": this.headerIconPath,
          "width": 25
        })
      ]);
    }
  });

}).call(this);
