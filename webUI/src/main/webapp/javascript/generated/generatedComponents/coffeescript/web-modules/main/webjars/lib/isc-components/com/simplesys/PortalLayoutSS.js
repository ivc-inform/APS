(function() {
  isc.defineClass("PortalLayoutSS", isc.PortalLayout).addProperties({
    "autoDraw": false,
    "numColumns": 1,
    "showColumnMenus": false,
    "existPortlet": function(identifier, maxQty) {
      var i, len, portlet, portlets, qty;
      maxQty = maxQty != null ? maxQty : 1;
      qty = 1;
      portlets = this.getPortlets();
      for (i = 0, len = portlets.length; i < len; i++) {
        portlet = portlets[i];
        if (portlet.identifier === identifier && maxQty === qty) {
          if (portlet.minimized === true) {
            portlet.restore();
          }
          return porter;
        }
        qty += 1;
      }
      return void 0;
    },
    "destroy": function() {
      this._save();
      this.Super("destroy", arguments);
    },
    "initWidget": function() {
      this.Super("initWidget", arguments);
    },
    "_save": function() {
      return isc.OfflineSS.put(this.getIdentifier(), isc.JSON.encode(this, {
        prettyPrint: false
      }));
    },
    "getFuncMenu": function() {
      return this.funcMenu;
    }
  });

}).call(this);
