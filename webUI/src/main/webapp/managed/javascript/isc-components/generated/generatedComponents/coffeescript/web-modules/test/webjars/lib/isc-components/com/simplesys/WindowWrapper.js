(function() {
  isc.defineClass("WindowWrapper", isc.WindowSS).addProperties({
    height: 700,
    width: 500,
    isModal: true,
    showMaximizeButton: false,
    showMinimizeButton: false,
    canDragResize: true,
    autoPosition: true,
    initWidget: function() {
      var ed;
      this.Super("initWidget", arguments);
      ed = isc.OkCancelFunctionPanel.create({
        height: 35,
        owner: this,
        functionFunction: (function(_this) {
          return function() {
            var ref, ref1;
            return (ref = _this.wrappedComponent) != null ? (ref1 = ref.contextMenu) != null ? typeof ref1.showContextMenu === "function" ? ref1.showContextMenu() : void 0 : void 0 : void 0;
          };
        })(this)
      });
      this.addItems([this.wrappedComponent, ed]);
    }
  });

}).call(this);
