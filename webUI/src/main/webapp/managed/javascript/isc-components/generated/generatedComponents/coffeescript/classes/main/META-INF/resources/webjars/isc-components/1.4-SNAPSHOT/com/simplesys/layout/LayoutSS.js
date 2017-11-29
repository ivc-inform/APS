(function() {
  isc.defineClass("LayoutSS", isc.Layout).addProperties({
    "autoDraw": false,
    "getFuncMenu": function() {
      return this.funcMenu;
    }
  });

}).call(this);
