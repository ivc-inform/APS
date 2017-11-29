(function() {
  isc.defineClass("ArraySS", isc.Array);

  isc.addMethods(Array.prototype, {
    getX: function() {
      if (this.length > 0) {
        return this[0];
      } else {
        return void 0;
      }
    },
    getY: function() {
      if (this.length > 1) {
        return this[1];
      } else {
        return void 0;
      }
    },
    setX: function(x) {
      this[0] = x;
      return x;
    },
    setY: function(y) {
      this[1] = y;
      return y;
    },
    Exists: (function(_this) {
      return function(index) {
        if (_this[index] != null) {
          return true;
        } else {
          return false;
        }
      };
    })(this),
    notExists: (function(_this) {
      return function(index) {
        return _this.exists(index);
      };
    })(this),
    getAppex: function() {
      return this;
    },
    getBoundingBox: function() {
      return [this[0][0], this[0][1], this[2][0], this[2][1]];
    },
    getRect: function() {
      return {
        left: this[0][0],
        top: this[0][1],
        width: this[2][0] - this[0][0],
        height: this[2][1] - this[0][1]
      };
    },
    getRect1: function() {
      return {
        left: this[0],
        top: this[1],
        width: this[2],
        height: this[3]
      };
    },
    remove: function(obj, pos, endPos, comparator) {
      var index;
      index = this.indexOf(obj, pos, endPos, comparator);
      if (index === -1) {
        return false;
      } else {
        this.removeAt(index);
        return true;
      }
    }
  });

}).call(this);
