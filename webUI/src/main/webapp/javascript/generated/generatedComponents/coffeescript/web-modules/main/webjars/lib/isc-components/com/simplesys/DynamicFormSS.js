(function() {
  isc.defineClass("DynamicFormSS", isc.DynamicForm).addProperties({
    "autoDraw": false,
    "addField": function(field, pos) {
      var fields;
      if (isc.isA.Object(field) && (field.name != null)) {
        fields = this.fields.filter(function(_field) {
          return field.name !== _field.name;
        });
        if (isc.isA.Number(pos)) {
          fields.addAt(field, pos);
        } else {
          fields.add(field);
        }
        this.setFields(fields);
      }
    },
    "saveData": function(callback, requestProperties) {
      var ref, values;
      if (!requestProperties) {
        requestProperties = {};
      }
      if (!requestProperties.data) {
        requestProperties.data = {};
      }
      values = this.getValues();
      this.getFields().forEach((function(item) {
        if ((item.value != null)) {
          values[item.name] = item.value;
        }
      }));
      isc.addProperties(requestProperties.data, values);
      if (((ref = this.dataSource) != null ? ref.wildRecordJS : void 0) != null) {
        isc.addProperties(requestProperties.data, this.dataSource.wildRecordJS);
      }
      this.Super("saveData", [callback, requestProperties]);
    }
  });

}).call(this);
