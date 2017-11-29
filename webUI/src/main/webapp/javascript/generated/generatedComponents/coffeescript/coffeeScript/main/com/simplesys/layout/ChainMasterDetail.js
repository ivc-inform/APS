(function() {
  isc.defineClass("ChainMasterDetail", isc.LayoutSS).addProperties({
    vertical: false,
    "setFuncMenu": function(funcMenu) {
      this.funcMenu = funcMenu;
    },
    destroy: function() {

      /*@setViewState() */
      this.Super("destroy", arguments);
    },
    setViewState: function() {
      this.members.forEach((function(_this) {
        return function(member) {
          var id;
          if (_this.vertical === false) {
            id = (_this.getIdentifier()) + "." + (member.getIdentifier()) + "_width";

            /*console.log "#{id}: #{member.getWidth()}" */
            isc.OfflineSS.putNumber(id, member.getWidth());
          } else {
            id = (_this.getIdentifier()) + "." + (member.getIdentifier()) + "_height";

            /*console.log "#{id}: #{member.getHeight()}" */
            isc.OfflineSS.putNumber(id, member.getHeight());
          }
        };
      })(this));
      return this;
    },
    getViewState: function() {
      var i;
      i = 1;
      this.members.forEach((function(_this) {
        return function(member) {
          var height, id, width;
          if (_this.vertical === false) {
            if (_this.members.length === i) {
              member.setWidth("*");
            } else {
              id = (_this.getIdentifier()) + "." + (member.getIdentifier()) + "_width";
              width = isc.OfflineSS.getNumber(id, member.getWidth());

              /*console.log "#{id}: #{width}" */
              member.setWidth(width);
            }
          } else {
            if (_this.members.length === i) {
              member.setHeight("*");
            } else {
              id = (_this.getIdentifier()) + "." + (member.getIdentifier()) + "_height";
              height = isc.OfflineSS.getNumber(id, member.getWidth());

              /*console.log "#{id}: #{height}" */
              member.setHeight(id, height);
            }
          }
          i++;
        };
      })(this));
      return this;
    },
    "initWidget": function() {
      var i;
      this.Super("initWidget", arguments);
      i = 1;
      this.members.forEach((function(_this) {
        return function(member) {
          member.number = i;
          member.resized = function() {
            if (_this.isDrawn()) {
              _this.setViewState();
            }
          };
          return i++;
        };
      })(this));
    }
  });

}).call(this);
