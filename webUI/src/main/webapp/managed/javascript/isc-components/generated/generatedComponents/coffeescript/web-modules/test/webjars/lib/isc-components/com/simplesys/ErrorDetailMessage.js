(function() {
  var errDet;

  isc.defineClass("MessageWindow", isc.WindowSS).addProperties({
    "autoDraw": false,
    "isModal": true,
    "showMinimizeButton": false,
    "showMaximizeButton": false,
    "canDragReposition": true,
    "autoSize": true,
    "showHeaderIcon": false,
    "showStatusBar": false,

    /*"autoPosition"       : true */
    "title": "\u041E\u0448\u0438\u0431\u043A\u0430 ...",
    "height": 50,
    "dismissOnEscape": true,
    "initWidget": function() {
      var detailButton;
      this.Super("initWidget", arguments);
      if (this.message == null) {
        this.message = "&nbsp;";
      }
      if (this.detailMessage == null) {
        this.detailMessage = "&nbsp;";
      }
      detailButton = isc.IButtonSS.create({
        "identifier": detailButton,
        "click": (function(_this) {
          return function() {
            var detailMessageWindow;
            detailMessageWindow = isc.DetailMessageWindow.create({
              "identifier": _this.detailIdentifier,
              "message": _this.detailMessage,
              "onDestroy": function() {
                detailButton.focus();
              },
              "width": simpleSyS.getWidthMessageWindow(_this.detailMessage)
            });
            detailMessageWindow.show();
          };
        })(this),
        "title": "\u041F\u043E\u0434\u0440\u043E\u0431\u043D\u043E\u0441\u0442\u0438 ...",
        "icon": "Detail.png",
        "height": "*",
        "width": 120
      });
      this.addItem(isc.VLayoutSS.create({
        "members": [
          isc.HLayoutSS.create({
            "defaultLayoutAlign": "center",
            "members": [
              isc.VLayoutSS.create({
                "defaultLayoutAlign": "center",
                "members": [
                  isc.Img.create({
                    "src": "error.png",
                    "height": 32,
                    "width": 32
                  })
                ],
                "align": "center",
                "width": 50
              }), isc.HTMLFlow.create({
                "contents": this.message,
                "width": "*"
              })
            ]
          }), isc.HLayoutSS.create({
            "defaultLayoutAlign": "center",
            "members": [
              isc.LayoutSpacerSS.create({
                "width": "*"
              }), isc.IButtonSS.create({
                "focused": true,
                "escapeKeyPress": (function(_this) {
                  return function() {
                    _this.markForDestroy();
                  };
                })(this),
                "click": (function(_this) {
                  return function() {
                    _this.markForDestroy();
                  };
                })(this),
                "title": "\u0417\u0430\u043A\u0440\u044B\u0442\u044C ...",
                "icon": "ok.png",
                "height": "*",
                "width": 100
              }), isc.LayoutSpacerSS.create({
                "width": "*"
              }), detailButton, isc.LayoutSpacerSS.create({
                "width": "*"
              })
            ],
            "height": 35
          })
        ],
        "height": "100%",
        "width": "100%"
      }));
    }
  });

  isc.defineClass("DetailMessageWindow", isc.WindowSS).addProperties({
    "autoDraw": false,
    "isModal": true,
    "showMinimizeButton": false,
    "showMaximizeButton": false,
    "canDragReposition": true,
    "autoSize": true,
    "showHeaderIcon": false,
    "showStatusBar": false,
    "title": "\u041F\u043E\u0434\u0440\u043E\u0431\u043D\u043E\u0441\u0442\u0438 ...",
    "dismissOnEscape": true,
    "height": 50,
    "initWidget": function() {
      this.Super("initWidget", arguments);
      if (this.message == null) {
        this.message = "&nbsp;";
      }
      this.addItem(isc.VLayoutSS.create({
        "members": [
          isc.HLayoutSS.create({
            "defaultLayoutAlign": "center",
            "members": [
              isc.VLayoutSS.create({
                "defaultLayoutAlign": "center",
                "members": [
                  isc.Img.create({
                    "src": "error.png",
                    "height": 32,
                    "width": 32
                  })
                ],
                "align": "center",
                "width": 50
              }), isc.HTMLFlow.create({
                "contents": this.message,
                "width": "*"
              })
            ]
          }), isc.HLayoutSS.create({
            "defaultLayoutAlign": "center",
            "layoutAlign": "center",
            "members": [
              isc.LayoutSpacerSS.create({
                "width": "*"
              }), isc.IButtonSS.create({
                "focused": true,
                "click": (function(_this) {
                  return function() {
                    _this.markForDestroy();
                  };
                })(this),
                "title": "\u0417\u0430\u043A\u0440\u044B\u0442\u044C ...",
                "icon": "ok.png",
                "height": "*",
                "width": 100
              }), isc.LayoutSpacerSS.create({
                "width": "*"
              }), isc.IButtonSS.create({
                "click": (function(_this) {
                  return function() {};
                })(this),
                "title": "\u041E\u0442\u043E\u0441\u043B\u0430\u0442\u044C ...",
                "icon": "send.png",
                "height": "*",
                "width": 100
              }), isc.LayoutSpacerSS.create({
                "width": "*"
              })
            ],
            "height": 35,
            "width": "100%"
          })
        ],
        "height": "100%",
        "width": "100%"
      }));
    }
  });

  errDet = function(message, detailMessage, identifier, detailIdentifier) {
    var messageWindow;
    messageWindow = MessageWindow.create({
      "identifier": identifier,
      "detailIdentifier": detailIdentifier,
      "detailMessage": detailMessage,
      "message": message,
      "width": simpleSyS.getWidthMessageWindow(message)
    });
    messageWindow.show();
  };

  isc.addGlobal("ErrorDetail", errDet);

  isc.addGlobal("errorDetail", errDet);

}).call(this);
