(function() {
  isc.defineClass("PortletSS", isc.Portlet).addProperties({
    "headerIconPath": "",
    "autoDraw": false,
    "headerIconDefaults": {
      "height": 14,
      "width": 16
    },
    "setFuncMenu": function(funcMenu) {
      this.funcMenu = funcMenu;
    },
    "initWidget": function() {
      this.Super("initWidget", arguments);
      this.headerIconDefaults.src = this.headerIconPath;
    },
    "getFuncMenu": function() {
      return this.funcMenu;
    }
  });

}).call(this);
