(function() {
  isc.defineClass("IButtonSS", isc.IButton).addProperties({
    "canFocus": true,
    "autoDraw": false,

    /*"keyPress": ->
    		key = isc.Event.getKey()
    		switch key
    			when "Escape"
    				if @parentElement.showCloseButton is true
    					@escapeKeyPress?()
    
    			else
    				@Super "keyPress", arguments
     */
    "draw": function() {
      this.Super("draw", arguments);
      if (this.focused === true) {
        this.focus();
      }
    },
    "focused": false
  });

}).call(this);
