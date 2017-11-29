(function() {
  var simpleSyS;

  simpleSyS = this.simpleSyS;

  isc.defineClass("MenuSS", isc.Menu).addProperties({
    "shadowDepth": 10,
    "autoDraw": false,
    "showShadow": true,
    "setOwner": function(owner) {
      this.owner = owner;
      if ((owner != null) && (this.items != null)) {
        this.items = this.items.map(function(item) {
          item.owner = owner;
          return item;
        });
      }
      return this;
    },
    "setTabSet": function(tabSet) {
      this.tabSet = tabSet;
      if ((typeof tabsSet !== "undefined" && tabsSet !== null) && (this.items != null)) {
        this.items = this.items.map(function(item) {
          if (item.owner != null) {
            item.owner.tabsSet = tabsSet;
            return item;
          }
        });
      }
      return this;
    },
    "log": function() {
      var i, item, len, ref;
      console.log("Component: " + (this.getIdentifier()));
      ref = this.items;
      for (i = 0, len = ref.length; i < len; i++) {
        item = ref[i];
        console.log("item: " + item.title + ", owner: " + (item.owner.getIdentifier()));
      }
    },
    "setItems": function(items) {
      this.Super("setItems", arguments);
      return this;
    },
    "getMergedContextMenu": function() {
      var _items, itemsSystemContextMenu, itemsSystemContextMenu2;
      itemsSystemContextMenu = [
        {
          "isSeparator": true,
          "identifier": "ControlSystemMenuSeparator1"
        }
      ];
      itemsSystemContextMenu2 = [
        {
          "title": "\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u043A\u043E\u043D\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044E",
          "click": (function(_this) {
            return function() {
              var base;
              if (_this.owner != null) {

                /*console.log "item: #{@title}, owner: #{@owner.getIdentifier()}" */
                if (typeof (base = _this.owner).saveViewState === "function") {
                  base.saveViewState(function() {
                    isc.ok("Сохранение выполнено.");
                  });
                }
              } else {
                simpleSyS.safeException("Not enabled owner pointer");
              }
            };
          })(this),
          "icon": "settings-icon.png",
          "identifier": "ControlSystemMenu.SaveViewState"
        }, {
          "title": "\u0412\u043E\u0441\u0441\u0442\u0430\u043D\u043E\u0432\u0438\u0442\u044C \u043A\u043E\u043D\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044E '\u043F\u043E-\u0443\u043C\u043E\u043B\u0447\u0430\u043D\u0438\u044E'",
          "click": (function(_this) {
            return function() {
              var base;
              if (_this.owner != null) {
                if (typeof (base = _this.owner).restoreViewState === "function") {
                  base.restoreViewState(function() {
                    if (_this.owner.autoSaveConfig != null) {
                      _this.owner.autoSaveConfig = false;
                    }
                    isc.ok("Восстановление выполнено. Необходима перезагрузка формы.");
                  });
                }
              } else {
                simpleSyS.safeException("Not enabled owner pointer");
              }
            };
          })(this),
          "icon": "restore.png",
          "identifier": "ControlSystemMenu.RestoreViewState"
        }
      ];
      if (this.items != null) {
        _items = itemsSystemContextMenu.map(function(item) {
          return item.identifier;
        });
        _items = this.items.filter(function(item) {
          return !_items.contains(item.identifier);
        });
        _items.addList(itemsSystemContextMenu);
        _items.addList(itemsSystemContextMenu2);
      } else {
        _items = itemsSystemContextMenu2;
      }
      return this.setItems(_items);
    },
    "addItem": function(item, pos) {
      var _item, q_exstty;
      _item = this.filter4Visiblity(item);
      if (isc.isA.Array(_item) && _item.length > 0) {
        _item = _item[0];
      }
      if (!isc.isA.Array(this.data)) {
        this.data = [];
      }
      q_exstty = this.data.filter(function(_item) {
        return (_item.identifier != null) && (item.identifier != null) && _item.identifier === item.identifier;
      });
      if (isc.isA.Array(q_exstty) && q_exstty.length === 0) {
        if (isc.isA.Number(pos)) {
          this.data.addAt(_item, pos);
        } else {
          this.data.add(_item);
        }
      }
    },
    "addItems": function(itemArray, pos) {
      var i, item, len;
      if (!isc.isA.Array(itemArray)) {
        itemArray = [itemArray];
      }
      for (i = 0, len = itemArray.length; i < len; i++) {
        item = itemArray[i];
        this.addItem(item, pos);
        if (isc.isA.Number(pos)) {
          pos++;
        }
      }
    },
    "addItemProperties": function(identifier, properties) {
      this.items.forEach(function(item) {
        if (item.identifier === identifier) {
          isc.addProperties(item, properties);
        }
      });
    },
    "removeItem": function(item) {
      if (isc.isA.Number(item) === true) {
        this.data.removeAt(item);
      } else if (isc.isA.Object(item) === true) {
        this.data.forEach((function(_this) {
          return function(_item) {
            if ((_item.identifier != null) && (item.identifier != null) && _item.identifier === item.identifier) {
              _this.data.remove(_item);
            }
          };
        })(this));
      } else if (isc.isA.String(item) === true) {
        this.data.forEach((function(_this) {
          return function(_item) {
            if ((_item.identifier != null) && _item.identifier === item) {
              _this.data.remove(_item);
            }
          };
        })(this));
      }
    },
    "removeItems": function(items) {
      if (!isc.isA.Array(items)) {
        items = [items];
      }
      items.forEach((function(_this) {
        return function(item) {
          return _this.removeItem(item);
        };
      })(this));
    },
    "replaceItems": function(oldItems, newItems) {
      this.removeItems(oldItems);
      this.addItems(newItems);
    },
    "setDynamicItems": function() {
      this.items = this.filter4Visiblity(this.items);
      this.Super("setDynamicItems", arguments);
    },
    "filter4Visiblity": function(items) {
      if (items != null) {
        if (!isc.isA.Array(items)) {
          items = [items];
        }
        return items.filter(function(item) {
          var _visibility;
          if (item.submenu != null) {
            if (isc.isA.Object(item.submenu)) {
              item.submenu = isc.MenuSS.create({
                items: item.submenu.items
              });
            } else if (isc.isA.Menu(item.submenu)) {
              item.submenu = isc.MenuSS.create({
                items: item.submenu.items
              });
            }
          }
          if ((item.visibilityIf != null) && isc.isA.Function(item.visibilityIf)) {
            _visibility = item.visibilityIf();
            return _visibility !== false && _visibility !== "hidden";
          } else {
            return (item.visibility == null) || (item.visibility !== false && item.visibility !== "hidden");
          }
        });
      }
    },
    "initWidget": function() {
      this.items = this.filter4Visiblity(this.items);
      if ((this.owner != null) && (this.items != null)) {
        this.items = this.items.map((function(_this) {
          return function(item) {
            item.owner = _this.owner;
            return item;
          };
        })(this));
      }
      if ((this.tabsSet != null) && (this.items != null)) {
        this.items = this.items.map((function(_this) {
          return function(item) {
            var ref;
            if ((ref = item.owner) != null) {
              ref.tabsSet = _this.tabsSet;
            }
            return item;
          };
        })(this));
      }
      this.Super("initWidget", arguments);
    },
    "setData": function(data) {
      var rv;
      if ((isc.Tree != null) && isc.isA.Tree(this.filter4Visiblity(data))) {
        this.setTreeData(data, true);
      } else {
        rv = this.invokeSuper(isc.Menu, "setData", this.filter4Visiblity(data));
      }
      delete this._heightCalculated;
      return this;
    },
    "getItem1": function(identifier) {
      var res;
      res = this.data.filter(function(item) {
        return item.identifier === identifier;
      });
      if (isc.isA.Array(res) && res.length === 0) {
        return {
          click: function() {
            return isc.error("Element '" + identifier + "' not found.");
          }
        };
      } else {
        return res[0];
      }
    }
  });

}).call(this);
