(function() {
  isc.defineClass("HTMLPaneSS", isc.HTMLPane).addProperties({
    "autoDraw": false,
    "clearContents": function() {
      this.setContents("&nbsp;");
    },
    "addContents": function(contents) {
      if (this.contents === "&nbsp;") {
        this.setContents("" + contents);
      } else {
        this.setContents(this.contents + "<p>" + contents);
      }
    }
  });

}).call(this);
