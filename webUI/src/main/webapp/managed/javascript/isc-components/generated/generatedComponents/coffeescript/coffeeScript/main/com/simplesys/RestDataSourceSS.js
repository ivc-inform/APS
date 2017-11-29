(function() {
  isc.defineClass("RestDataSourceSS", isc.RestDataSource).addProperties({
    updateData: function(updatedRecord, callback, requestProperties) {
      if (isc.isA.Object(requestProperties) === false) {
        requestProperties = {
          useSimpleHttp: true
        };
      } else {
        requestProperties.useSimpleHttp = true;
      }
      if (isc.isA.Function(callback) === false) {
        callback = function() {
          return void 0;
        };
      }
      this.Super("updateData", [updatedRecord, callback, requestProperties]);
    },
    removeData: function(updatedRecord, callback, requestProperties) {
      if (isc.isA.Object(requestProperties) === false) {
        requestProperties = {
          useSimpleHttp: true
        };
      } else {
        requestProperties.useSimpleHttp = true;
      }
      if (isc.isA.Function(callback) === false) {
        callback = function() {
          return void 0;
        };
      }
      this.Super("removeData", [updatedRecord, callback, requestProperties]);
    },
    transformRequest: function(dsRequest) {
      if (dsRequest.operationType === "update") {
        dsRequest.data = isc.addProperties({}, dsRequest.oldValues, dsRequest.data);
      }
      return this.Super("transformRequest", dsRequest);
    },
    getLookupFileds: isc.getLookupFileds,
    getForeignFields: isc.getForeignFields
  });

}).call(this);
