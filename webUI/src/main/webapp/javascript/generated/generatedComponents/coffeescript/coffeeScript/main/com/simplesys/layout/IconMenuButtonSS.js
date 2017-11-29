(function() {
  isc.defineClass("IconMenuButtonSS", isc.IconMenuButton).addProperties({
    "autoDraw": false,
    "initWidget": function() {
      var _items;
      this.Super("initWidget", arguments);
      if ((this.menu != null) && isc.isA.Array(this.menu.items)) {
        _items = this.menu.items;
        this.menu = isc.MenuSS.create({
          items: _items
        });
      }
    }
  });

}).call(this);
