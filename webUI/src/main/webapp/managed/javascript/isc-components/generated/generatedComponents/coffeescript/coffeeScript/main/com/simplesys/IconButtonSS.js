(function() {
  isc.defineClass("IconButtonSS", isc.IconButton).addProperties({
    "canFocus": true,
    "autoDraw": false,
    "keyPress": function() {
      var key;
      key = isc.Event.getKey();
      switch (key) {
        case "Escape":
          if (this.parentElement.showCloseButton === true) {
            return typeof this.escapeKeyPress === "function" ? this.escapeKeyPress() : void 0;
          }
          break;
        default:
          return this.Super("keyPress", arguments);
      }
    },
    "draw": function() {
      this.Super("draw", arguments);
      if (this.focused === true) {
        this.focus();
      }
    },
    "focused": false
  });

}).call(this);
