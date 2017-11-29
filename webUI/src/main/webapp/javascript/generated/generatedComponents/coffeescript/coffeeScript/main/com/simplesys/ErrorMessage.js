(function() {
  var simpleSyS;

  simpleSyS = this.simpleSyS;

  isc.defineClass("MessageWindow1", isc.WindowSS).addProperties({
    "autoDraw": false,
    "isModal": true,
    "destroy": function() {
      if (isc.isA.Function(this.callback)) {
        this.callback();
      }
      this.Super("destroy", arguments);
    },
    "showMinimizeButton": false,
    "showMaximizeButton": false,
    "autoSize": true,

    /*"autoPosition"       : true */
    "canDragReposition": true,
    "showHeaderIcon": false,
    "title": "\u041E\u0448\u0438\u0431\u043A\u0430 ...",
    "showStatusBar": false,
    "height": 50,
    "dismissOnEscape": true,
    "initWidget": function() {
      if (isc.isA.Boolean(this.callback)) {
        this.autoPosition = this.callback;
      }
      if (isc.isA.Boolean(this.identifier)) {
        this.autoPosition = this.identifier;
        this.identifier = void 0;
      }
      if (isc.isA.Function(this.identifier)) {
        this.callback = this.identifier;
        this.identifier = void 0;
      }
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
                    "src": this.iconPath,
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
              isc.IButtonSS.create({
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
              })
            ],
            "align": "center",
            "height": 35
          })
        ],
        "height": "100%",
        "width": "100%"
      }));
    }
  });

  isc.defineClass("MessageWindow2", isc.WindowSS).addProperties({
    "autoDraw": false,
    "isModal": true,
    "iconPath": "error.png",
    "destroy": function() {
      if (isc.isA.Function(this.callback)) {
        this.callback();
      }
      this.Super("destroy", arguments);
    },
    "showMinimizeButton": true,
    "showMaximizeButton": false,
    "canDragResize": true,
    "autoSize": false,

    /*"autoPosition"       : true */
    "canDragReposition": true,
    "showHeaderIcon": false,
    "title": "\u041E\u0448\u0438\u0431\u043A\u0438 ...",
    "showStatusBar": false,
    "height": 300,
    "width": 600,
    "dismissOnEscape": true,
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
                    "src": this.iconPath,
                    "height": 32,
                    "width": 32
                  })
                ],
                "align": "center",
                "width": 50
              }), isc.ListGrid.create({
                "showRecordComponentsByCell": true,
                "showRecordComponents": true,
                "data": this.errors,
                "fields": [
                  {
                    "canEdit": false,
                    "name": "message",
                    "title": "\u041E\u0448\u0438\u0431\u043A\u0430 (\u043A\u0440\u0430\u0442\u043A\u043E)",
                    "width": 300
                  }, {
                    "canEdit": false,
                    "name": "stackTraceBtn",
                    "align": "center",
                    "title": "\u041E\u0448\u0438\u0431\u043A\u0430 (Stack)",
                    "width": 120
                  }, {
                    "hidden": true,
                    "name": "stackTrace"
                  }, {
                    "hidden": true,
                    "name": "stackTraceWindow"
                  }
                ],
                "createRecordComponent": function(record, colNum) {
                  var fieldName;
                  fieldName = this.getFieldName(colNum);
                  if (fieldName === "stackTraceBtn") {
                    return isc.IButton.create({
                      height: 18,
                      width: 130,
                      icon: "Detail.png",
                      title: "Подробности...",
                      click: function() {
                        isc.error(record.stackTrace, "54366BDA-5F30-9772-82E6-CDF31AA36410");
                      }
                    });
                  }
                },
                "width": "*"
              })
            ]
          }), isc.HLayoutSS.create({
            "defaultLayoutAlign": "center",
            "members": [
              isc.IButtonSS.create({
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
              })
            ],
            "align": "center",
            "height": 35
          })
        ],
        "height": "100%",
        "width": "100%"
      }));
    }
  });

  isc.defineClass("MessageWindow3", isc.WindowSS).addProperties({
    "autoDraw": false,
    "isModal": true,
    "iconPath": "info.png",
    "destroy": function() {
      if (isc.isA.Function(this.callback)) {
        this.callback();
      }
      this.Super("destroy", arguments);
    },
    "showMinimizeButton": false,
    "showMaximizeButton": false,
    "showCloseButton": true,
    "canDragResize": true,
    "autoSize": false,

    /*"autoPosition"       : true */
    "canDragReposition": true,
    "showHeaderIcon": false,
    "title": "\u041E\u0448\u0438\u0431\u043A\u0438 ...",
    "showStatusBar": false,
    "height": 300,
    "width": 600,
    "dismissOnEscape": true,
    "initWidget": function() {
      this.Super("initWidget", arguments);
      if (this.message == null) {
        this.message = "&nbsp;";
      }
      this.grid = isc.ListGridEditor.create(this.gridProperties);
      this.addItem(isc.VLayoutSS.create({
        "members": [
          isc.HLayoutSS.create({
            "defaultLayoutAlign": "center",
            "members": [
              isc.VLayoutSS.create({
                "defaultLayoutAlign": "center",
                "members": [
                  isc.Img.create({
                    "src": this.iconPath,
                    "height": 32,
                    "width": 32
                  })
                ],
                "align": "center",
                "width": 50
              }), this.grid
            ]
          }), isc.HLayoutSS.create({
            "defaultLayoutAlign": "center",
            "members": [
              isc.IButtonSS.create({
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
              })
            ],
            "align": "center",
            "height": 35
          })
        ],
        "height": "100%",
        "width": "100%"
      }));
    }
  });

  isc.addGlobal("error", function(message, identifier, callback) {
    var messageWindow;
    messageWindow = isc.MessageWindow1.create({
      "iconPath": "error.png",
      "callback": callback,
      "identifier": identifier,
      "message": message,
      "width": simpleSyS.getWidthMessageWindow(message)
    });
    messageWindow.show();
    return messageWindow;
  });

  isc.addGlobal("info", function(message, identifier, callback) {
    var messageWindow;
    messageWindow = isc.MessageWindow1.create({
      "iconPath": "info.png",
      "callback": callback,
      "identifier": identifier,
      "message": message,
      "title": "\u0418\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044F...",
      "width": simpleSyS.getWidthMessageWindow(message)
    });
    messageWindow.show();
    return messageWindow;
  });

  isc.addGlobal("infos", function(gridProperties, identifier, callback) {
    var messageWindow;
    messageWindow = isc.MessageWindow3.create({
      "iconPath": "info.png",
      "callback": callback,
      "identifier": identifier,
      "gridProperties": gridProperties,
      "title": "\u0418\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044F..."
    });
    messageWindow.show();
    return messageWindow;
  });

  isc.addGlobal("ok", function(message, identifier, callback) {
    var messageWindow;
    messageWindow = isc.MessageWindow1.create({
      "iconPath": "ok.png",
      "callback": callback,
      "identifier": identifier,
      "message": message,
      "title": "\u0412\u044B\u043F\u043E\u043B\u043D\u0435\u043D\u043E ...",
      "width": simpleSyS.getWidthMessageWindow(message)
    });
    messageWindow.show();
    return messageWindow;
  });

  isc.addGlobal("errors", function(errors, identifier, callback) {
    var messageWindow;
    messageWindow = isc.MessageWindow2.create({
      "callback": callback,
      "identifier": identifier,
      "errors": errors
    });
    messageWindow.show();
    return messageWindow;
  });

}).call(this);
