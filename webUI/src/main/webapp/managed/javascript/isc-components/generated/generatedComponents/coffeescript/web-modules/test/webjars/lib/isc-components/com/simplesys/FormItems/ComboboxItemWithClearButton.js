(function() {
  isc.defineClass("ComboboxItemWithClearButton", isc.ComboboxItemWithButtons).addProperties({
    constructor: "ComboBoxItem",
    buttonsProperties: [
      {
        width: 20,
        icon: "cancel.png",
        identifier: "ButtonClear",
        click: function() {
          var ref, ref1, that;
          that = isc.isA.IButtonSS(this) ? this : isc.isA.ComboboxItemWithClearButton(this) ? this.buttons[0] : void 0;
          if (that != null) {
            if ((ref = that.combobox) != null) {
              if (typeof ref.onButtonClearClick === "function") {
                ref.onButtonClearClick(that.combobox.getValue());
              }
            }
          }
          if (that != null) {
            if ((ref1 = that.combobox) != null) {
              if (typeof ref1.clearValue === "function") {
                ref1.clearValue();
              }
            }
          }
          if (that != null) {
            if (typeof that.hide === "function") {
              that.hide();
            }
          }
        }
      }
    ],
    init: function() {
      this.Super("init", arguments);
      this.buttonClearClick = this.buttons[0].click;
      this.buttonClearHide = (function(_this) {
        return function() {
          _this.setValue(false);
        };
      })(this);
      if (this.changed != null) {
        this._changed = this.changed;
      }
      this.changed = (function(_this) {
        return function(form, item, value) {
          if (typeof _this._changed === "function") {
            _this._changed(form, item, value);
          }
          if (value !== strEmpty) {
            form.owner.showButton("ButtonClear");
          }
        };
      })(this);
    },
    setValue: function(value) {
      if (value !== void 0) {
        if (value != null) {
          this.showButton("ButtonClear");
        } else {
          this.hideButton("ButtonClear");
        }
        this.Super("setValue", [value]);
      }
    }
  });

}).call(this);
