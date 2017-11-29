(function() {
  var simpleSyS;

  simpleSyS = this.simpleSyS;

  isc.defineClass("OkCancelFunctionPanel", isc.HPanelSS).addProperties({
    "defaultLayoutAlign": "center",
    "autoDraw": false,
    "ownerDestroy": true,
    "functionButtonTitle": "Операции ...",
    "okButtonTitle": "Выполнить ...",
    "cancelButtonTitle": "Отменить ...",
    "setDisabledOk": function(value) {
      this.okBtn.setDisabled(value);
    },
    "initWidget": function() {
      this.Super("initWidget", arguments);
      this.addMembers([
        isc.LayoutSpacerSS.create({
          "width": "*"
        }), this.okBtn = isc.IButtonSS.create({
          click: (function(_this) {
            return function() {
              var ref, ref1;
              if (typeof _this.okFunction === "function") {
                _this.okFunction();
              }
              if (simpleSyS != null) {
                if (typeof simpleSyS.checkOwner === "function") {
                  simpleSyS.checkOwner(_this.owner);
                }
              }
              if ((ref = _this.owner) != null) {
                if (typeof ref.okFunction === "function") {
                  ref.okFunction(_this.filterBuilder);
                }
              }
              if (_this.ownerDestroy === true) {
                if ((ref1 = _this.owner) != null) {
                  if (typeof ref1.markForDestroy === "function") {
                    ref1.markForDestroy();
                  }
                }
              }

              /*else
              						            @owner?.hide?()
               */
            };
          })(this),
          "title": this.okButtonTitle,
          "prompt": this.okButtonTitle,
          "icon": "ok.png",
          "width": 100,
          "showDisabledIcon": true
        }), isc.LayoutSpacerSS.create({
          "width": "*"
        }), isc.IButtonSS.create({
          "click": (function(_this) {
            return function() {
              var ref;
              if (typeof _this.functionFunction === "function") {
                _this.functionFunction();
              }
              simpleSyS.checkOwner(_this.owner);
              if ((ref = _this.owner) != null) {
                if (typeof ref.functionFunction === "function") {
                  ref.functionFunction(_this.filterBuilder);
                }
              }
            };
          })(this),
          "title": this.functionButtonTitle,
          "prompt": this.functionButtonTitle,
          "icon": "function-selection.png",
          "width": 100,
          "showDisabledIcon": true
        }), isc.LayoutSpacerSS.create({
          "width": "*"
        }), isc.IButtonSS.create({
          "click": (function(_this) {
            return function() {
              var ref, ref1;
              if (typeof _this.cancelFunction === "function") {
                _this.cancelFunction();
              }
              if (simpleSyS != null) {
                if (typeof simpleSyS.checkOwner === "function") {
                  simpleSyS.checkOwner(_this.owner);
                }
              }
              if ((ref = _this.owner) != null) {
                if (typeof ref.cancelFunction === "function") {
                  ref.cancelFunction(_this.filterBuilder);
                }
              }
              if ((ref1 = _this.owner) != null) {
                if (typeof ref1.markForDestroy === "function") {
                  ref1.markForDestroy();
                }
              }
            };
          })(this),
          "title": this.cancelButtonTitle,
          "prompt": this.cancelButtonTitle,
          "icon": "cancel.png",
          "width": 100,
          "showDisabledIcon": true
        }), isc.LayoutSpacerSS.create({
          "width": "*"
        })
      ]);
    }
  });

}).call(this);
