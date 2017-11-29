(function() {
  isc.ClassFactory.defineClass("ComboboxItemWithButtons", isc.CanvasItem).addProperties({
    canFocus: true,
    getValue: function() {
      return this.comboboxItem.getValue();
    },
    setValue: function(value) {
      if (value !== void 0) {
        this.comboboxItem.setValue(value);
      }
    },
    getClientPickListData: function() {
      return this.comboboxItem.getClientPickListData();
    },
    fetchData: function(callback, requestProperties) {
      this.comboboxItem.fetchData(callback, requestProperties);
    },
    _visiblityButton: function(identifier, action) {
      var button;
      button = (this.buttons.filter(function(button) {
        return button.identifier === identifier;
      })).map(function(button) {
        button.setCanFocus(false);
        return button;
      });
      if (button.length > 0) {
        switch (action) {
          case "show":
            button[0].show();
            break;
          case "hide":
            button[0].hide();
        }
      }
    },
    showButton: function(identifier) {
      this._visiblityButton(identifier, "show");
    },
    hideButton: function(identifier) {
      this._visiblityButton(identifier, "hide");
    },
    setValueMap: function(valueMap) {
      this.comboboxItem.setValueMap(valueMap);
    },
    createCanvas: function() {
      var autoFitWidthApproach, df, ref, res;
      res = isc.HLayoutSS.create({
        height: 20,
        members: [
          autoFitWidthApproach = this.autoFitWidthApproach ? this.autoFitWidthApproach : "both", df = isc.DynamicFormSS.create({
            cellPadding: 0,
            owner: this,
            width: "*",
            minColWidth: 0,
            colWidths: [0, "*"],
            items: [
              {
                colSpan: 2,
                name: this.name,
                width: "*",
                showTitle: false,
                onButtonClearClick: this.onButtonClearClick,
                addUnknownValues: this.addUnknownValues,
                optionDataSource: this.optionDataSource,
                _constructor: this.constructor,
                valueMap: this.valueMap,
                value: this.value,
                defaultValue: this.defaultValue,
                displayField: this.displayField,
                valueField: this.valueField,
                emptyPickListMessage: this.emptyPickListMessage,
                pickListFields: this.pickListFields,
                specialValues: this.specialValues,
                separateSpecialValues: this.separateSpecialValues,
                pickListWidth: this.pickListWidth,
                pickListMaxWidth: this.pickListMaxWidth,
                pickListProperties: this.pickListProperties,
                showRowNumbers: this.showRowNumbers,
                autoFitWidthApproach: autoFitWidthApproach,
                changed: (function(_this) {
                  return function(form, item, value) {
                    if (_this.changed != null) {
                      _this.changed(form, item, value);
                    }
                  };
                })(this)
              }
            ]
          })
        ]
      });
      this.comboboxItem = df.getItems()[0];
      this.buttons = (ref = this.buttonsProperties) != null ? ref.map((function(_this) {
        return function(props) {
          return isc.IButtonSS.create(isc.addProperties(props, {
            combobox: _this.comboboxItem
          }));
        };
      })(this)) : void 0;
      res.addMembers(this.buttons);
      res.members = res.members.filter(function(item) {
        return item != null;
      });
      return res;
    }
  });

}).call(this);
