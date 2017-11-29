(function() {
  isc.defineClass("DrawItemTileCf", isc.SimpleTile).addProperties({
    initWidget: function() {
      this.Super("initWidget", arguments);
      this.drawPane = isc.DrawPane.create({
        autoDraw: false,
        width: "100%",
        height: "100%"
      });
      this.addChild(this.drawPane);
      this.setLogPriority(this.getClassName(), isc.Log.DEBUG);
    },
    getInnerHTML: function() {
      return "&nbsp;";
    },
    drawRecord: function(record) {
      var drawItem, tilePalette;
      tilePalette = this.tileGrid;
      drawItem = tilePalette.makeEditNode(record).liveObject;
      if (!isc.isAn.Instance(drawItem)) {
        if (drawItem._constructor != null) {
          drawItem = isc[drawItem._constructor].create(isc.addProperties({}, drawItem, {
            autoDraw: false
          }));
          this.drawPane.addDrawItem(drawItem);
        } else {
          this.logError("Record is not drawItem and unknown _constructor");
        }
      } else {
        this.drawPane.addDrawItem(drawItem);
      }
    },
    draw: function() {
      var record, ret;
      ret = this.Super("draw", arguments);
      record = this.getRecord();
      this.drawRecord(record);
      return ret;
    },
    redraw: function() {
      var drawPane, record;
      drawPane = this.drawPane;
      record = this.getRecord();
      if (record !== this.record) {
        drawPane.erase();
        this.drawRecord(record);
        this.record = record;
      }
      this.Super("redraw", arguments);
    }
  });

}).call(this);
