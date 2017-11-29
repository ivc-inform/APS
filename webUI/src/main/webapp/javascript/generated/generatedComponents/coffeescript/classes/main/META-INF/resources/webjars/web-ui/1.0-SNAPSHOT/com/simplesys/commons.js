(function() {
  var isc, simpleSyS, top,
    slice = [].slice;

  top = this;

  if (top.simpleSyS == null) {
    top.simpleSyS = {};
  }

  if (top.isc == null) {
    top.isc = {};
  }

  simpleSyS = top.simpleSyS;

  isc = top.isc;

  simpleSyS._getGrid = function(that) {
    if (isc.isA.ListGrid(that.grid) === true) {
      return that.grid;
    } else if (isc.isA.ListGrid(that) === true) {
      return that;
    }
  };

  simpleSyS._createRecordComponent = (function(_this) {
    return function(that) {
      var grid;
      grid = simpleSyS._getGrid(that);
      if ((grid != null ? grid.showRecordComponents : void 0) === true && (grid != null ? grid.showRecordComponentsByCell : void 0) === true) {
        grid.createRecordComponent = function(record, colNum) {
          var _lookupFields, fieldName, foreignField, foreignFields, i, len, lookupFields, result;
          fieldName = grid.getFieldName(colNum);
          foreignFields = grid.dataSource.getForeignFields();
          result = null;
          for (i = 0, len = foreignFields.length; i < len; i++) {
            foreignField = foreignFields[i];
            lookupFields = grid.dataSource.getLookupFileds(foreignField.name).map(function(field) {
              return {
                field_name: field.name,
                field_captionClassLookup: field.captionClassLookup
              };
            });
            if (lookupFields != null) {
              _lookupFields = lookupFields.map(function(item) {
                return item.field_name;
              });
              if (_lookupFields.contains(fieldName)) {
                result = isc.LookupEditor.create({
                  editorIdentifier: that.getIdentifier() + fieldName,
                  record: record,
                  owner: top,
                  foreignField: foreignField,
                  lookupFields: lookupFields
                });
                break;
              }
            }
          }
          return result;
        };
      }
    };
  })(this);

  simpleSyS._updateRecordComponent = (function(_this) {
    return function(that) {
      var grid;
      grid = simpleSyS._getGrid(that);
      if ((grid != null ? grid.showRecordComponents : void 0) === true && (grid != null ? grid.showRecordComponentsByCell : void 0) === true) {
        grid.updateRecordComponent = function(record, colNum, component, recordChanged) {
          component.record = record;
        };
      }
    };
  })(this);

  simpleSyS._initMenus = function(that) {
    that.Super("initWidget", arguments);
  };

  simpleSyS._RecordComponent = function(that, mode) {
    var e, funcName;
    funcName = "simpleSyS._" + mode + "RecordComponent";
    if (top.isFunction(funcName) === true) {
      try {
        that.evaluate(funcName + "(that)", {
          "that": that
        });
      } catch (error) {
        e = error;
        that.logError(e);
      }
    }
  };

  simpleSyS._enableDeleteFromTree = function(that) {
    var dataSource, i, len, node, owner, pk, pkFields, record, records, res, tree;
    owner = that.owner;
    if (simpleSyS.checkOwner(owner) != null) {
      records = owner.getSelectedRecords();
      res = owner.getSelectedRecords().length > 0;
      dataSource = owner.dataSource;
      pkFields = dataSource.getPrimaryKeyFieldNames();
      tree = owner.grid.data;
      for (i = 0, len = records.length; i < len; i++) {
        record = records[i];
        pk = pkFields.map(function(field) {
          return record[field];
        });
        node = tree.findById(pk);
        if (tree.isFolder(node)) {
          res = false;
          break;
        }
      }
    }
    return res;
  };

  simpleSyS._openFolders = function(that) {
    var _openFolders1, dataSource, i, len, node, nodes, owner, pk, pkFields, record, records, tree;
    owner = that.owner;
    if (simpleSyS.checkOwner(owner) != null) {
      records = owner.getSelectedRecords();
      dataSource = owner.dataSource;
      pkFields = dataSource.getPrimaryKeyFieldNames();
      tree = owner.grid.data;
      nodes = [];
      _openFolders1 = function(nodes) {
        nodes.forEach(function(node) {
          var nodes1;
          nodes1 = tree.getChildren(node);
          if (nodes1 != null) {
            _openFolders1(nodes1);
          }
          tree.openFolder(node);
        });
      };
      if (records.length > 0) {
        for (i = 0, len = records.length; i < len; i++) {
          record = records[i];
          pk = pkFields.map(function(field) {
            return record[field];
          });
          node = tree.findById(pk);
          if (tree.isFolder(node) === true) {
            nodes.add(node);
          }
        }
        _openFolders1(nodes);
      }
    }
  };

  simpleSyS.getGuid = function() {
    isc.say(simpleSyS.guid());
  };

  simpleSyS.underConstruction = function() {
    isc.info("Sorry, this under consruction.");
  };

  simpleSyS.notImplementation = function() {
    isc.info("Sorry, implementation not found.");
  };

  isc.debugTrap = function() {
    var obj;
    obj = 1 <= arguments.length ? slice.call(arguments, 0) : [];
    if ((obj != null) && obj.length > 0) {
      return obj[0];
    } else {
      return [];
    }
  };

  isc.debugTrac = function() {
    var json, obj;
    obj = 1 <= arguments.length ? slice.call(arguments, 0) : [];
    json = isc.JSON.encode(obj, {
      prettyPrint: true
    });
    console.log("obj: " + json);
  };

  isc.deletePrivateProps = function(obj) {
    var prop;
    for (prop in obj) {
      if (prop.charAt(0) === "_") {
        delete obj[prop];
      }
    }
    return obj;
  };

  isc.getPropValue = function(object, name) {
    try {
      return object[name];
    } catch (error) {
      return void 0;
    }
  };

  isc.setPropValue = function(object, name, value) {
    try {
      object[name] = value;
      return value;
    } catch (error) {
      return void 0;
    }
  };

  isc.deleteProp = function(object, propName) {
    try {
      delete object[propName];
    } catch (error) {
      void 0;
    }
  };

  isc.getWindowObject = function(name) {
    try {
      return window[name];
    } catch (error) {
      return void 0;
    }
  };

  isc.js_beautify = typeof js_beautify !== "undefined" && js_beautify !== null ? js_beautify : function(str) {
    return str;
  };

  isc.setArrayItem = function(array, index, value) {
    if (isc.isA.Array(array)) {
      array[index] = value;
    }
  };

  isc.createClass = function(className, args) {
    var creater;
    creater = isc[className].create;
    return creater(args);
  };

}).call(this);
