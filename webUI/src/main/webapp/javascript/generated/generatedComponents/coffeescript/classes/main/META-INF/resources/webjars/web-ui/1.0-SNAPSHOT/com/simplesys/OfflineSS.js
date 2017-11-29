(function() {
  isc.defineClass("OfflineSS", isc.Offline).addClassMethods({
    "put": function(key, value, recycleEntries) {
      if (key == null) {
        Log.logWarn("key: " + key);
      } else if (value == null) {
        Log.logWarn("value: " + value);
        isc.Offline.remove(key);
      } else {
        isc.Offline.put(key, value, recycleEntries);
      }
    },
    "get": function(key, defaultValue) {
      var res;
      res = isc.Offline.get(key);
      return res || defaultValue;
    },
    "getFromBase": function(key, defaultValue, _callback) {
      var ts;
      ts = simpleSyS.timeStamp();
      isc.RPCManagerSS.sendRequest({
        actionURL: simpleSysContextPath + "accessor/Fetch",
        data: {
          guid: key,
          ts: ts
        },
        callback: function(rpcResponse, data, rpcRequest) {
          var ref, ref1;
          if (isc.isA.Function(_callback) && (data != null ? (ref = data.response) != null ? (ref1 = ref.data) != null ? ref1.properties4Storage : void 0 : void 0 : void 0) && isc.isA.String(data.response.data.properties4Storage)) {
            this.fireCallback(_callback, "result", [data.response.data.properties4Storage === strEmpty ? [] : isc.JSON.decode(data.response.data.properties4Storage)]);
          }
        }
      });
    },
    "getBoolean": function(key, defaultValue) {
      var res;
      res = isc.Offline.get(key);
      if (isc.isA.String(res) === false) {
        return false;
      }
      res = res.bool;
      if (isc.isA.Boolean(defaultValue) === true) {
        return res || defaultValue;
      } else {
        return res || false;
      }
    },
    "putToBase": function(key, value, callback) {
      isc.RPCManagerSS.sendRequest({
        actionURL: simpleSysContextPath + "accessor/Update",
        data: {
          guid: key,
          json: value,
          callback: callback
        }
      });
    },
    "getNumber": function(key, defaultValue) {
      var res;
      res = Number(isc.Offline.get(key));
      if (isc.isA.Number(defaultValue) === true) {
        return res || defaultValue;
      } else {
        return res;
      }
    },
    "putBoolean": function(key, value, recycleEntries) {
      if (isc.isA.Boolean(value) === true) {
        isc.Offline.put(key, value, recycleEntries);
      } else if (isc.isA.String(value) === true) {
        isc.Offline.put(key, value.bool, recycleEntries);
      } else {
        isc.Offline.put(key, false, recycleEntries);
      }
    },
    "putNumber": function(key, value, recycleEntries) {
      isc.Offline.put(key, Number(value), recycleEntries);
    },
    "putArray": function(key, values, recycleEntries) {
      var i, index, len, value;
      if (!isc.isA.Array(values)) {
        values = [values];
      }
      this.removeArray(key);
      index = 0;
      for (i = 0, len = values.length; i < len; i++) {
        value = values[i];
        isc.OfflineSS.put(key + index.toString(), value, recycleEntries);
        index++;
      }
    },
    "getArray": function(key) {
      var _res, index, res;
      res = [];
      index = 0;
      _res = isc.OfflineSS.get(key + index.toString());
      res.add(_res);
      while (_res) {
        index++;
        _res = isc.OfflineSS.get(key + index.toString());
        if (_res != null) {
          res.add(_res);
        }
      }
      return res;
    },
    "removeArray": function(key) {
      var _key, _res, index;
      index = 0;
      _res = isc.OfflineSS.get(key + index.toString());
      while (_res) {
        index++;
        _key = key + index.toString();
        _res = isc.OfflineSS.get(_key);
        if (_res != null) {
          isc.OfflineSS.remove(_key);
        }
      }
    }
  });

}).call(this);
