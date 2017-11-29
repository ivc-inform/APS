(function() {
  var hasProp = {}.hasOwnProperty;

  isc.defineClass("DataSourceStructurer", isc.DataSourceSS).addProperties({
    data: [],
    addMenu: function(menu, parent, owner, separator_id, submenu_id, fromSubmenu) {
      if (isc.isA.Object(parent) === true) {
        parent = parent.title;
      }
      if (menu.data != null) {
        return menu.data.forEach((function(_this) {
          return function(menuItem) {
            var _id, _id1, _id2;
            if (menuItem.isSeparator === true) {
              _id2 = parent + "_seperator_" + separator_id;
              _this.addNewData(parent, _id2, "separator # " + separator_id + "_menuItem", owner);
              separator_id += 1;
            } else if (menuItem.submenu != null) {
              _id = parent + "_" + menuItem.title + "_menuItem";
              _this.addNewData(parent, _id, "menuItem: " + menuItem.title + "_menuItem", owner);
              _this.addMenu(menuItem.submenu, _id, owner, separator_id, submenu_id, true);
              submenu_id += 1;
            } else {
              if (fromSubmenu === false) {
                _id = parent + "_" + menuItem.title + "_menuItem";
              } else {
                _id1 = parent + "_" + menuItem.title + "_menuItem_submenu_" + submenu_id;
              }
              _this.addNewData(parent, _id1, "menuItem: " + menuItem.title + "_menuItem", owner);
            }
          };
        })(this));
      }
    },
    addNewData: function(parent, id, comment, canvas, owner) {
      var menu, message, newRec, prop, ref, separator_id, submenu_id, value;
      newRec = {
        identifier: id,
        pidentifier: parent,
        existing: false,
        comment: comment,
        canvas: canvas,
        owner: owner
      };
      if (isc.isA.Canvas(canvas) === true) {
        for (prop in canvas) {
          if (!hasProp.call(canvas, prop)) continue;
          value = canvas[prop];
          if (((canvas != null ? (ref = canvas[prop]) != null ? ref._constructor : void 0 : void 0) != null) === "Menu" || isc.isA.Menu(canvas[prop]) === true) {
            menu = canvas[prop];
            separator_id = 0;
            submenu_id = 0;
            this.addMenu(menu, id, owner, separator_id, submenu_id);
          }
        }
      }
      if (this.data.filter((function(item) {
        return item.identifier === newRec.identifier && item.pidentifier === newRec.pidentifier;
      }).length > 0)) {
        message = "Dublicate identifier: " + newRec.identifier + " pidentifier: " + newRec.pidentifier + " been omitted.";
        console.warn(message);
      } else {
        this.addData(newRec);
        this.data.add(newRec);
      }
    },
    clientOnly: true,
    dataFormat: "json",
    fields: [
      {
        title: "Родитель",
        name: "pidentifier",
        foreignKey: "identifier",
        canSort: false
      }, {
        title: "Идентификатор",
        primaryKey: true,
        name: "identifier",
        required: true,
        canSort: false
      }, {
        title: "Назначение / Класс",
        name: "comment",
        required: true,
        canSort: false
      }, {
        title: "Свойства",
        name: "propertiesView",
        canSort: false
      }, {
        name: "canvas",
        type: "any",
        required: true,
        hidden: true
      }, {
        name: "properties",
        type: "any",
        hidden: true
      }
    ]
  });

  isc.defineClass("Structurer", isc.WindowSS).addProperties({
    height: 500,
    width: 500,
    isModal: false,
    showMaximizeButton: true,
    showMinimizeButton: false,
    autoPosition: false,
    autoSize: false,
    canDragResize: true,
    identifier: "DE34D33A-5CEE-7313-F91C-47B1868636C3",
    autoCenter: false,
    headerIconPath: "structure.png",
    title: "Структура вида...",
    initWidget: function() {
      this.Super("initWidget", arguments);
      if (this.view != null) {
        this.title = "Структура " + (this.view.getIdentifier()) + " (" + (this.view.getComment()) + ")";
      }
      this._getParentCanvasIdentifier = function(canvas) {
        var parent;
        parent = canvas.getParentCanvas();
        if (parent != null) {
          return parent.getIdentifier();
        }
      };
      this._addSelfCanvas = function(child) {
        if (isc.isA.Canvas(child) === true) {
          this.dataSource.addNewData(this._getParentCanvasIdentifier(child), child.getIdentifier(), child.getComment(), child, this.treeGrid);
        }
      };
      this._addChildrenCanvas = function(canvas) {
        if ((canvas.children != null) && canvas.children.length > 0) {
          canvas.children.forEach(function(child) {
            this._addSelfCanvas(child);
            return this._addChildrenCanvas(child);
          });
        }
      };
      this._getTree = function() {
        var parent;
        if (isc.isA.Canvas(this.view) === true) {
          parent = this.view.getParentCanvas();
          while (parent) {
            this._addSelfCanvas(parent);
            parent = parent.getParentCanvas();
          }
          this._addSelfCanvas(this.view);
          this._addChildrenCanvas(this.view);
        }
      };
      this._menu = isc.MenuSS.create({
        "items": [
          {
            "keys": [
              {
                "ctrlKey": true,
                "keyName": "P"
              }
            ],
            "title": "Свойства для администрирования ...",
            "click": function() {
              simpleSyS.PropertiesEditor = this._menu.record;
            },
            "icon": "parameters.png",
            "keyTitle": "Ctrl+P",
            "enableIf": (function(_this) {
              return function() {
                return _this.treeGrid.getSelectedRecord();
              };
            })(this)
          }
        ],
        "autoDraw": false
      });
      this.treeGrid = isc.TreeGridEditor.create({
        identifier: "5758AE4B-EFC5-AD3D-D1F7-3FE4ABEF805F",
        selectionAppearance: "checkbox",
        showSelectedStyle: false,
        showPartialSelection: true,
        cascadeSelection: true,
        height: "100%",
        width: "100%",
        dataSource: this.dataSource,
        showFilterEditor: false,
        showDropIcons: false,
        editEvent: "click",
        nodeIcon: "part_widget.png",
        folderIcon: "widget.png",
        autoFetchData: true,
        contextMenu: this._menu,
        wrapCells: true,
        _restoreLastBorder: function() {
          if (this.lastBorderData != null) {
            window.clearInterval(this.lastBorderData.intervalID);
            this.lastBorderData.canvas.setBorder(this.lastBorderData.border);
            delete this.lastBorderData;
          }
        },
        rowClick: function(record) {
          this._restoreLastBorder();
          if (isc.EventHandler.shiftKeyDown() === true) {
            simpleSyS.PropertiesEditor = record;
          } else if (isc.isA.Canvas(record.canvas) === true) {
            treeGrid.lastBorderData = record.canvas.setBlinkBorder1("2px dotted red");
          }
        },
        rowContextClick: function(record, rowNum, colNum) {
          this._menu.record = record;
          this._restoreLastBorder();
        },
        dataProperties: {
          dataArrived: (function(_this) {
            return function() {
              var existsEnabledProperties;
              _this.openAll();
              existsEnabledProperties = function(properties) {
                return !isc.isA.emptyArray(properties) && (properties.filter(function(item) {
                  return simpleSyS.V4Admin[item];
                })).length > 0;
              };
              if (_this.result != null) {
                _this.result.forEach(function(item) {
                  var node;
                  node = that1.findById(item.identifier);
                  if (node != null) {
                    if (isc.isA.emptyArray(item.properties) === false) {
                      treeGrid.selectRecord(node);
                      node.properties = item.properties;
                      node.propertiesView = isc.JSON.encode(item.properties, {
                        prettyPrint: false
                      });
                    }
                  }
                  return {
                    fields: [
                      {
                        name: "pidentifier"
                      }, {
                        name: "identifier"
                      }, {
                        name: "comment",
                        length: 1000
                      }, {
                        name: "propertiesView",
                        length: 1000
                      }
                    ]
                  };
                });
              }
            };
          })(this)
        }
      });
      this.onDestroy = function() {
        this._restoreLastBorder();
        this.markForDestroy();
      };
      this._ed = isc.OkCancelPanel.create({
        height: 35,
        owner: this,
        disabledOk: true,
        cancelCaption: "Закрыть",
        okCaption: "Применить",
        okFunction: function() {
          var selectedIDS;
          selectedIDS = this.treeGrid.getSelectedRecords().filter(function(record) {
            return record.properties;
          }).map(function(record) {
            return {
              pidentifier: record.pidentifier,
              identifier: record.identifier,
              comment: record.comment,
              properties: record.properties
            };
          });
          isc.OfflineSS.putToBase(this.view.getIdentifier(), selectedIDS);
        }
      });
      this.addItems([this.treeGrid, this._ed]);
      this._getTree();
    }
  });

}).call(this);
