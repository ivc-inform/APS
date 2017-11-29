(function() {
  isc.ClassFactory.defineClass("HTMLPaneItem", isc.CanvasItem).addProperties({
    height: "*",
    width: "*",
    autoDraw: false,
    showEdges: true,
    createCanvas: function(form, item) {
      if (this.pane == null) {
        this.pane = isc.HTMLPaneSS.create({
          autoDraw: this.autoDraw,
          width: this.width,
          height: this.height,
          showEdges: this.showEdges
        });
      }
      return this.pane;
    }
  });

}).call(this);
