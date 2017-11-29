(function() {
  isc.Window.addProperties({
    "calcXY_Window": function() {
      var left, top;
      left = isc.EventHandler.getX();
      top = isc.EventHandler.getY();
      if (left + this.width > isc.Page.getWidth()) {
        this.setLeft(left - this.width);
      } else {
        this.setLeft(left);
      }
      if (top + this.height > isc.Page.getHeight()) {
        this.setTop(top - this.height);
      } else {
        this.setTop(top);
      }
    },
    "autoPosition": false,
    "savePosition": true,
    "saveSize": true,
    "dismissOnEscape": true,
    "vertical": true,

    /*"keyDown" : ->
    		switch isc.EventHandler.getKey()
    			when "Escape"
    				@markForDestroy() if @dismissOnEscape
    		true
     */
    "saveParams": function() {
      this._save();
    },
    "_save": function() {
      if (this.items != null) {
        this.items.forEach(function(item) {
          if (item != null) {
            if (typeof item._save === "function") {
              item._save();
            }
          }
        });
      }
      if (this.autoCenter !== true && this.autoPosition !== true) {
        if (this.savePosition === true) {
          isc.OfflineSS.putNumber((this.getIdentifier()) + "left", this.getLeft());
          isc.OfflineSS.putNumber((this.getIdentifier()) + "top", this.getTop());
        }
      }
      if (this.saveSize === true) {
        isc.OfflineSS.putNumber((this.getIdentifier()) + "width", this.getWidth());
        isc.OfflineSS.putNumber((this.getIdentifier()) + "height", this.getHeight());
      }
    }
  });

  isc.defineClass("WindowSS", isc.Window).addProperties({
    "animateMembers": true,
    "keepInParentRect": true,
    "headerIconDefaults": {
      "height": 14,
      "width": 16
    },
    "animateMinimize": true,
    "canDragResize": true,

    /*"resized" : ->
    		return
     */
    "destroy": function() {
      if (typeof this._save === "function") {
        this._save();
      }
      if (typeof this.onDestroy === "function") {
        this.onDestroy(this.typeButton);
      }
      this.Super("destroy", arguments);
    },
    "showMaximizeButton": true,
    "closeClick": function() {
      if (typeof this._save === "function") {
        this._save();
      }
      if (typeof this.onDestroy === "function") {
        this.onDestroy(this.typeButton);
      }
      return this.Super("closeClick", arguments);
    },
    "initWidget": function() {
      var height, left, parent, top, width;
      this.Super("initWidget", arguments);
      this.headerIconDefaults.src = this.headerIconPath;
      if (this.autoCenter !== true && this.savePosition === true) {
        if ((isc.OfflineSS.get((this.getIdentifier()) + "top") != null) && (isc.OfflineSS.get((this.getIdentifier()) + "left") != null)) {
          top = isc.OfflineSS.getNumber((this.getIdentifier()) + "top", this.top);
          left = isc.OfflineSS.getNumber((this.getIdentifier()) + "left", this.left);
          this.moveTo(Math.max(left, 0), Math.max(top, 0));
        } else {
          this.centerInPage();
        }
      }

      /*top = isc.Page.getHeight() / 2 - @getHeight() / 2
      		left = isc.Page.getWidth() / 2 - @getWidth() / 2
       */
      if (this.autoSize === false && this.saveSize === true) {
        width = isc.OfflineSS.getNumber((this.getIdentifier()) + "width", this.width);
        height = isc.OfflineSS.getNumber((this.getIdentifier()) + "height", this.height);
        parent = this.parentElement != null ? this.parentElement : isc.Page;
        if (this.getLeft() + width > parent.getWidth()) {
          width = parent.getWidth() - this.getLeft();
        }
        if (this.getTop() + height > parent.getHeight()) {
          height = parent.getHeight() - this.getTop();
        }
        this.setWidth(width);
        this.setHeight(height);
      }
      if (this.autoPosition === true) {
        if (typeof this.calcXY_Window === "function") {
          this.calcXY_Window();
        }
      }
      this.observe(this, "resized", this._save);
      this.observe(this, "moved", this._save);
    },
    show: function() {
      this.Super("show", arguments);
      this.bringToFront();
    }
  });

}).call(this);
