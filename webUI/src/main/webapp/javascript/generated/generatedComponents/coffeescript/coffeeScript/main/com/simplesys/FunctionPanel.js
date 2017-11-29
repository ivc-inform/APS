(function() {
  isc.defineClass("FunctionPanel", isc.HPanelSS).addProperties({
    "defaultLayoutAlign": "center",
    autoDraw: false,
    "initWidget": function() {
      this.Super("initWidget", arguments);
      this.addMembers([
        isc.LayoutSpacerSS.create({
          "width": "*"
        }), isc.IButton.create({
          "click": (function(_this) {
            return function() {
              if (_this.owner.functionFunction != null) {
                _this.owner.functionFunction();
                return;
              }
              if (_this.functionFunction != null) {
                _this.functionFunction();
              }
            };
          })(this),
          "autoDraw": false,
          "title": this.buttonTitle != null ? this.buttonTitle : "Операции...",
          "icon": "function-selection.png",
          "width": 100,
          "showDisabledIcon": false
        }), isc.LayoutSpacerSS.create({
          "width": "*"
        })
      ]);
    }
  });

}).call(this);
