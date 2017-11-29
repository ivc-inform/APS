(function() {
  isc.defineClass("JoinJSCanvas", isc.Canvas).addProperties({
    "suffix": "_joinJSCanvas",
    "redrawOnResize": false,
    "_getNewID": function() {
      return "" + (this.getID()) + this.suffix;
    },
    "getInnerHTML": function() {
      return "<div ID=" + (this._getNewID()) + "></div>";
    },
    "setJoinJSFunc": function(func) {
      this.joinJSFunc = func;
      return this;
    },
    "draw": function() {
      if (this.readyToDraw()) {
        this.Super("draw", arguments);
        if (isc.isA.Function(this.joinJSFunc)) {
          this.joinJSFunc(this._getNewID());
        } else if (isc.isA.String(this.joinJSFunc)) {
          this.evaluate(this.joinJSFunc, {
            "anchor": "#" + (this._getNewID())
          });
        }
        return this;
      } else {
        return this;
      }
    }
  });

}).call(this);
