(function() {
  Object.defineProperty(String.prototype, "isNotEmpty", {
    get: function() {
      return this && this.trim().length > 0;
    }
  });

  Object.defineProperty(String.prototype, "isEmpty", {
    get: function() {
      return !this.isNotEmpty;
    }
  });

}).call(this);
