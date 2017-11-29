(function() {
  var simpleSyS;

  simpleSyS = this.simpleSyS;

  isc.defineClass("OkCancelPanel", isc.HPanelSS).addProperties({
    "defaultLayoutAlign": "center",
    "autoDraw": false,
    "height": 28,
    "okIcon": "ok.png",
    "cancelIcon": "cancel.png",
    "ownerDestroy": true,
    "ownerHide": true,
    "cancelCaption": "Отменить",
    "okCaption": "Выполнить",
    "timeoutClose": 1000,
    "setDisabledOk": function(value) {
      this.okBtn.setDisabled(value);
      return this;
    },
    "setOwner": function(owner) {
      this.owner = owner;
      return this;
    },
    "initWidget": function() {
      this.Super("initWidget", arguments);
      this.addMembers([
        isc.LayoutSpacerSS.create({
          "width": "*"
        }), this.okBtn = isc.IButtonSS.create({
          "autoFit": true,
          "click": (function(_this) {
            return function() {
              var base, ref, ref1;
              if (isc.isA.Function(_this.okFunction)) {
                _this.okFunction(_this);
              } else {
                if (_this.owner != null) {
                  if (simpleSyS != null) {
                    if (typeof simpleSyS.checkOwner === "function") {
                      simpleSyS.checkOwner(_this.owner);
                    }
                  }
                  if (typeof (base = _this.owner).okFunction === "function") {
                    base.okFunction(_this);
                  }
                  if (_this.ownerDestroy === true) {
                    if ((ref = _this.owner) != null) {
                      if (typeof ref.markForDestroy === "function") {
                        ref.markForDestroy();
                      }
                    }
                  } else if (_this.ownerHide === true) {
                    if ((ref1 = _this.owner) != null) {
                      if (typeof ref1.hide === "function") {
                        ref1.hide();
                      }
                    }
                  }
                }
              }
            };
          })(this),
          "title": this.okCaption,
          "prompt": this.okCaption,
          "icon": this.okIcon,
          "width": 100,
          "showDisabledIcon": false
        }), isc.LayoutSpacerSS.create({
          "width": "*"
        }), this.cancelButton = isc.IButtonSS.create({
          "autoFit": true,
          "click": (function(_this) {
            return function() {
              var ref, ref1, ref2;
              if (isc.isA.Function(_this.cancelFunction)) {
                _this.cancelFunction(_this);
              } else {
                if (_this.owner != null) {
                  if (simpleSyS != null) {
                    if (typeof simpleSyS.checkOwner === "function") {
                      simpleSyS.checkOwner(_this.owner);
                    }
                  }
                  if ((ref = _this.owner) != null) {
                    if (typeof ref.cancelFunction === "function") {
                      ref.cancelFunction(_this);
                    }
                  }
                  if (_this.ownerDestroy === true) {
                    if ((ref1 = _this.owner) != null) {
                      if (typeof ref1.markForDestroy === "function") {
                        ref1.markForDestroy();
                      }
                    }
                  } else if (_this.ownerHide === true) {
                    if ((ref2 = _this.owner) != null) {
                      if (typeof ref2.hide === "function") {
                        ref2.hide();
                      }
                    }
                  }
                }
              }
            };
          })(this),
          "title": this.cancelCaption,
          "prompt": this.cancelCaption,
          "icon": this.cancelIcon,
          "width": 100
        }), isc.LayoutSpacerSS.create({
          "width": "*"
        })
      ]);
    }
  });

  isc.defineClass("OkPanel", isc.HPanelSS).addProperties({
    "defaultLayoutAlign": "center",
    "autoDraw": false,
    "ownerDestroy": true,
    "ownerHide": true,
    "okCaption": "Выполнить",
    "okIcon": "ok.png",
    "timeoutClose": 1000,
    setDisabledOk: function(value) {
      this.okBtn.setDisabled(value);
      return this;
    },
    setOwner: function(owner) {
      this.owner = owner;
      return this;
    },
    "initWidget": function() {
      this.Super("initWidget", arguments);
      this.addMembers([
        isc.LayoutSpacerSS.create({
          "width": "*"
        }), this.okBtn = isc.IButtonSS.create({
          "autoFit": true,
          "click": (function(_this) {
            return function() {
              var ref, ref1, ref2;
              if (typeof _this.okFunction === "function") {
                _this.okFunction(_this);
              }
              if (_this.owner != null) {
                if (simpleSyS != null) {
                  if (typeof simpleSyS.checkOwner === "function") {
                    simpleSyS.checkOwner(_this.owner);
                  }
                }
                if ((ref = _this.owner) != null) {
                  if (typeof ref.okFunction === "function") {
                    ref.okFunction(_this);
                  }
                }
                if (_this.ownerDestroy === true) {
                  if ((ref1 = _this.owner) != null) {
                    if (typeof ref1.markForDestroy === "function") {
                      ref1.markForDestroy();
                    }
                  }
                } else if (_this.ownerHide === true) {
                  if ((ref2 = _this.owner) != null) {
                    if (typeof ref2.hide === "function") {
                      ref2.hide();
                    }
                  }
                }
              }
            };
          })(this),
          "title": this.okCaption,
          "icon": this.okIcon,
          "width": 100,
          "showDisabledIcon": false
        }), isc.LayoutSpacerSS.create({
          "width": "*"
        })
      ]);
    }
  });

}).call(this);
