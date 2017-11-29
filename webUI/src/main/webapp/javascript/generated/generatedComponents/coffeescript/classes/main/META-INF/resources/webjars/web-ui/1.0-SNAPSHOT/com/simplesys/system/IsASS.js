(function() {
  isc.addMethods(isc.isA, {
    notEmptyArray: function(object) {
      return isc.isAn.Array(object) && object.length !== 0;
    }
  });

}).call(this);
