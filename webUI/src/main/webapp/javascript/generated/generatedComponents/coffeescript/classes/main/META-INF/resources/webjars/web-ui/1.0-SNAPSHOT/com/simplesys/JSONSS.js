(function() {
  isc.JSON.addClassProperties({
    decode: function(jsonString, reviver) {
      var e;
      if ((jsonString == null) || jsonString === "") {
        jsonString = null;
      }
      try {
        return JSON.parse(jsonString, reviver);
      } catch (error) {
        e = error;
        console.error(e.toString());
        return {};
      }
    },
    encode: function(object, settings) {
      var e, spacer;
      if ((settings != null ? settings.prettyPrint : void 0) === true) {
        spacer = 4;
      }
      try {
        return JSON.stringify(object, (function(_this) {
          return function(key, value) {
            switch (key) {
              case "ns":
                return void 0;
              case "__proto__":
                return void 0;
              default:
                if (isc.isA.Array(value)) {
                  return value;
                } else {
                  if (isc.isA.Function(value)) {
                    return void 0;
                  } else {
                    if (isc.isA.Object(value)) {
                      if (isc.isA.Menu(value)) {
                        return void 0;
                      } else if (value.unserialize === true) {
                        return void 0;
                      } else {
                        return value;
                      }
                    } else {
                      return value;
                    }
                  }
                }
            }
          };
        })(this), spacer);
      } catch (error) {
        e = error;
        console.error(e.toString());
        return "";
      }
    },
    printObject: function(obj) {
      var e;
      try {
        console.log(isc.JSON.encode(obj));
      } catch (error) {
        e = error;
        console.log(e.toString());
      }
    }
  });

  isc.defineClass("JSONSS", isc.JSON).addClassProperties({
    tryDecode: function(jsonString, reviver) {
      isc.JSON.decode(jsonString, reviver);
    }

    /*encode: (object, settings)->
    		settings ?= prettyPrint: false
    		isc.JSON.encode object, settings
     */
  });

}).call(this);
