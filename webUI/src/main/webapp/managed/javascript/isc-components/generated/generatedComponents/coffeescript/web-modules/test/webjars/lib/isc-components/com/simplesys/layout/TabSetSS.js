(function() {
  isc.defineClass("TabSetSS", isc.TabSet).addProperties({
    "autoDraw": false,
    "afterRemoveTabs": function() {
      return void 0;
    },
    "beforeRemoveTabs": function(tab) {
      return void 0;
    },
    "filter4Visiblity": function(tabs) {
      var _tabs;
      if (tabs != null) {
        if (isc.isA.Array(tabs) === false) {
          tabs = [tabs];
        }
        _tabs = tabs.filter(function(item) {
          return (item.visibility == null) || (item.visibility !== false && item.visibility !== "hidden");
        });
        return _tabs;
      }
    },
    addTabs: function(tabs, position) {
      if (isc.isA.Array(tabs) === false) {
        tabs = [tabs];
      }
      tabs.forEach(function(tab) {
        if ((tab.name == null) && (tab.title != null)) {
          tab.name = tab.title;
        }
      });
      this.Super("addTabs", [tabs, position]);
      this.tabs.forEach((function(_this) {
        return function(tab) {
          tab.tabSet = _this;
        };
      })(this));
      this.tabs = this.filter4Visiblity(this.tabs);
    },
    addTab: function(tab, position) {
      this.addTabs(tab, position);
    },
    removeTab: function(tabs) {
      var base, tab;
      if (isc.isA.Number(tabs)) {
        tab = [this.tabs[tabs]];
        if (typeof (base = tab[0].pane).onClose === "function") {
          base.onClose();
        }
      } else if (isc.isA.Array(tabs)) {
        tab = tabs;
      } else if (isc.isA.Object(tabs)) {
        tab = [tabs];
      }
      tab.forEach((function(_this) {
        return function(item) {
          var ref;
          _this.beforeRemoveTabs(item);
          if ((ref = item.pane) != null) {
            if (typeof ref.beforeRemove === "function") {
              ref.beforeRemove();
            }
          }
        };
      })(this));
      this.Super("removeTab", arguments);
      this.afterRemoveTabs();
    },
    removeAllTabs: function() {
      this.removeTab(this.tabs);
    },
    "localID": function(tab) {
      return (this.getIdentifier()) + "_" + tab.name;
    },
    "_sortTabs": function() {
      if (isc.isA.Array(this.tabs)) {
        return this.tabs = (this.tabs.map((function(_this) {
          return function(tab) {
            tab.index = isc.OfflineSS.getNumber(_this.localID(tab), 0);
            return tab;
          };
        })(this))).sort(function(a, b) {
          return a.index - b.index;
        });
      }
    },
    "initWidget": function() {
      this._sortTabs();
      this.Super("initWidget", arguments);
    },
    getLength: function() {
      return this.tabs.length;
    },
    isEmpty: function() {
      return this.tabs.length === 0;
    },
    isNotEmpty: function() {
      return this.tabs.length !== 0;
    },
    reorderTab: function(tab, moveToPosition, savePos) {
      if (savePos == null) {
        savePos = true;
      }
      tab.index = moveToPosition;
      this.Super("reorderTab", [tab, moveToPosition]);
      if (savePos) {
        this.tabs.forEach((function(_this) {
          return function(tab) {
            isc.OfflineSS.putNumber(_this.localID(tab), _this.getTabNumber(tab));
          };
        })(this));
      }
    }
  });

}).call(this);
