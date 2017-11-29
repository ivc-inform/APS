(function() {
  isc.ListGrid.addProperties({
    "canDragSelectText": true,
    "getRowNumSelectedGridRecord": function() {
      var cell, colNum, grid, rowNum;
      grid = this;
      if (grid.canSelectCells === true && grid.editByCell === true) {
        cell = grid.getFocusCell();
        if (cell[0] >= 0) {
          rowNum = cell[0];
        }
        if (cell[1] >= 0) {
          colNum = cell[1];
        }
      } else {
        rowNum = grid.getFocusRow();
        if (rowNum < 0) {
          rowNum = null;
        }
      }
      return {
        "rowNum": rowNum,
        "colNum": colNum
      };
    },
    "setSelectionChanged": function(func) {
      var exists;
      exists = (function(_this) {
        return function() {
          if (_this.selectionChangedQueue == null) {
            _this.selectionChangedQueue = [];
          }
          return _this.selectionChangedQueue.filter(function(item) {
            return item === func;
          }).length > 0;
        };
      })(this);
      if (isc.isA.Function(func) && !exists()) {
        this.selectionChangedQueue.add(func);
      }
    },
    "unsetSelectionChanged": function(func) {
      var res;
      if ((func != null) && isc.isA.Function(func)) {
        res = this.selectionChangedQueue.remove(func);
        if (!res) {
          this.logWarn("Function: " + (func.toString()) + " not removed.");
        }
      }
    },
    "setSelectionUpdated": function(func) {
      var exists;
      exists = (function(_this) {
        return function() {
          if (_this.selectionUpdatedQueue == null) {
            _this.selectionUpdatedQueue = [];
          }
          return _this.selectionUpdatedQueue.filter(function(item) {
            return item === func;
          }).length > 0;
        };
      })(this);
      if (isc.isA.Function(func) && !exists()) {
        this.selectionUpdatedQueue.add(func);
      }
    },
    "unsetSelectionUpdated": function(func) {
      var res;
      if ((func != null) && isc.isA.Function(func)) {
        res = this.selectionUpdatedQueue.remove(func);
        if (!res) {
          this.logWarn("Function: " + (func.toString()) + " not removed.");
        }
      }
    },
    "selectFirstRecord": function() {
      var rec;
      rec = this.getRecord(0);
      if (rec != null) {
        this.selectSingleRecord(rec);
      }
      return this.getSelectedRecord();
    },
    "setMasterGrid": function(grid, pkFieldNames) {

      /*console.log "setMasterGrid (grid: #{grid.getID()})" */
      var base, forignKeyFields, thisGrid;
      if (isc.isA.ListGridEditor(grid) || isc.isA.TreeGridEditor(grid)) {
        grid = grid.grid;
      }
      thisGrid = this;
      if (isc.isA.ListGrid(grid)) {
        this.masterGrid = grid;
        if (isc.isA.DataSource(this.dataSource)) {
          forignKeyFields = this.dataSource.getForignKeyFields();
          if (typeof (base = this.masterGrid).setSelectionChanged === "function") {
            base.setSelectionChanged((function(_this) {
              return function(record, state) {
                var arrayRes, criteria, field, masterGridField, masterSelectedRecords, value;
                if (!state) {
                  return;
                }
                masterSelectedRecords = _this.masterGrid.getSelectedRecords();
                _this.discardAllEdits();
                criteria = {};
                criteria.ts = simpleSyS.timeStamp();
                if ((pkFieldNames != null) && isc.isA.Object(pkFieldNames) && (pkFieldNames.masterGridField != null) && pkFieldNames.detailGridField) {
                  pkFieldNames = [pkFieldNames];
                }
                if (isc.isA.Array(pkFieldNames) && pkFieldNames.length > 0) {
                  pkFieldNames.forEach(function(item) {
                    var arrayRes;
                    arrayRes = masterSelectedRecords.filter(function(rec) {
                      return rec[item.masterGridField] != null;
                    }).map(function(rec) {
                      return rec[item.masterGridField];
                    });
                    if (isc.isA.Array(arrayRes) && arrayRes.length > 0) {
                      if (arrayRes.length === 1) {
                        return criteria[item.detailGridField] = arrayRes[0];
                      } else {
                        return criteria[item.detailGridField] = arrayRes;
                      }
                    }
                  });
                  if (!isc.isA.emptyObject(criteria)) {
                    thisGrid.criteria = criteria;
                    _this.fetchData(criteria, _this.selectFirstRecordAfterFetch === true ? function() {
                      _this.selectFirstRecord();
                    } : void 0);
                  } else if (masterSelectedRecords.length > 0) {
                    _this.logWarn1("Criteria for MaterGrid not found", _this.getClassName());
                  }
                } else {
                  for (field in forignKeyFields) {
                    value = forignKeyFields[field];
                    if (forignKeyFields[field].foreignKey.indexOf('.') !== -1) {
                      masterGridField = forignKeyFields[field].foreignKey.substring(forignKeyFields[field].foreignKey.lastIndexOf('.') + 1);
                    } else {
                      masterGridField = forignKeyFields[field];
                    }
                    arrayRes = masterSelectedRecords.filter(function(rec) {
                      return rec[masterGridField] != null;
                    }).map(function(rec) {
                      return rec[masterGridField];
                    });
                    if (isc.isA.Array(arrayRes) && arrayRes.length > 0) {
                      if (arrayRes.length === 1) {
                        criteria[field] = arrayRes[0];
                      } else {
                        criteria[field] = arrayRes;
                      }
                    }
                  }
                  if (!isc.isA.emptyObject(criteria)) {
                    thisGrid.criteria = criteria;
                    _this.fetchData(criteria, _this.selectFirstRecordAfterFetch === true ? function() {
                      _this.selectFirstRecord();
                    } : void 0);
                  } else if (masterSelectedRecords.length > 0) {
                    _this.logWarn1("Criteria for MaterGrid not found", _this.getClassName());
                  }
                }
              };
            })(this));
          }
        }
      }
    },
    "_getEditorWindow": function(obj, fields, callback, requestProperties) {
      var canvas, field, forignKeyFields, form, masterFields, masterGridField, masterPKFields, masterSelectedRecords, okCancelPanel, setedFields, updatedOperation, value, window;
      if (fields == null) {
        fields = this.editingFields;
      }
      if (requestProperties == null) {
        if (isc.isA.Object(obj)) {
          requestProperties = {
            operationType: "add",
            data: obj
          };
        } else {
          requestProperties = {
            operationType: "add"
          };
        }
      }
      if (!requestProperties.operationType) {
        requestProperties.operationType = "add";
      }
      updatedOperation = requestProperties.operationType === "update";
      form = isc.DynamicFormSS.create({
        identifier: this.identifier + "_DynamicForm_" + requestProperties.operationType + "_" + (requestProperties.componentId != null ? requestProperties.componentId : ""),
        dataSource: this.dataSource,
        numCols: 2,
        height: "*",
        colWidths: [100, "*"],
        fields: fields
      });
      okCancelPanel = isc.OkCancelFunctionPanel.create({
        padding: 5,
        okCaption: "Сохранить",
        ownerDestroy: false,
        ownerHide: false,
        okFunction: function() {
          var _save;
          if (form.validate(false)) {
            _save = (function(_this) {
              return function() {
                _this.owner.markForDestroy();
                _this.okBtn.setDisabled(true);
              };
            })(this);
            form.saveData(_save, requestProperties);
          }
        },
        functionButtonTitle: "Очистить все поля",
        functionFunction: function() {
          form.clearErrors(true);
          form.clearValues();
          form.cancelEditing();
        }
      });
      okCancelPanel.okBtn.showDisabledIcon = false;
      setedFields = [];
      if (!updatedOperation && (this.masterGrid != null)) {
        forignKeyFields = this.dataSource.getForignKeyFields();
        masterFields = this.masterGrid.dataSource.getFieldNames(false);
        masterPKFields = this.masterGrid.dataSource.getPrimaryKeyFieldNames();
        masterSelectedRecords = this.masterGrid.getSelectedRecords();
        if (masterSelectedRecords.length !== 1) {
          okCancelPanel.okBtn.setDisabled(true);
        } else {
          okCancelPanel.okBtn.setDisabled(false);
          for (field in forignKeyFields) {
            value = forignKeyFields[field];
            if (forignKeyFields[field].foreignKey.indexOf('.') !== -1) {
              masterGridField = forignKeyFields[field].foreignKey.substring(forignKeyFields[field].foreignKey.lastIndexOf('.') + 1);
            } else {
              masterGridField = forignKeyFields[field];
            }
          }
          if (form.getField(field)) {
            form.setValue(field, masterSelectedRecords[0][masterGridField]);
            setedFields.push(field);
          }
          masterFields.forEach(function(field) {
            value = masterSelectedRecords[0][field];
            if (!masterPKFields.contains(field) && form.getField(field)) {
              form.setValue(field, value);
              setedFields.push(field);
            }
          });
          form.getItems().forEach(function(formItem) {
            if (!setedFields.contains(formItem.name)) {
              formItem.clearValue();
            }
          });
        }
      }
      canvas = isc.ChainMasterDetail.create({
        vertical: true,
        members: [form, okCancelPanel]
      });
      window = isc.WindowSS.create(isc.addProperties({}, {
        isModal: true,
        dismissOnEscape: true,
        showMaximizeButton: false,
        showMinimizeButton: false,
        identifier: this.identifier + "_Window_" + requestProperties.operationType + "_" + (requestProperties.componentId != null ? requestProperties.componentId : ""),
        title: updatedOperation ? "Редактирование записи..." : "Новая запись...",
        initWidget: function() {
          this.headerIconPath = updatedOperation ? "edit.png" : "insert.png";
          this.Super("initWidget", arguments);
          this.addItem(canvas);
        }
      }, this.editWindowProperties));
      okCancelPanel.owner = window;
    },
    "startEditingNewInForm": function(obj, fields, callback, requestProperties) {
      this._getEditorWindow(obj, fields, callback, requestProperties);
    },
    "startEditingInForm": function(obj, fields, callback, requestProperties) {
      this._getEditorWindow(obj, fields, callback, requestProperties);
    }
  });

  isc.ClassFactory.defineInterface("GridEditorInterface").addInterfaceProperties({
    "selectFirstRecord": function() {
      this.grid.selectFirstRecord();
    },
    "getDataSource": function() {
      return this.grid.dataSource;
    },
    "setDataSource": function(dataSource) {
      this.grid.setDataSource(dataSource);
    },
    "setSelectionChanged": function(func) {
      this.grid.setSelectionChanged(func);
    },
    "unsetSelectionChanged": function(func) {
      this.grid.unsetSelectionChanged(func);
    },
    "setSelectionUpdated": function(func) {
      this.grid.setSelectionUpdated(func);
    },
    "unsetSelectionUpdated": function(func) {
      this.grid.unsetSelectionUpdated(func);
    },
    "setForignFieldFields": function(grid, masterGrid) {
      var _masterGrid, field, fk, fks, foreignKey, lk, lks, pkFields;
      if (masterGrid != null) {
        _masterGrid = masterGrid;
      } else {
        _masterGrid = grid;
      }
      pkFields = _masterGrid.dataSource.getPrimaryKeyFieldsArray();
      fks = grid.dataSource.getForignKeyFields();
      for (field in fks) {
        fk = fks[field].foreignKey;
        if (fk.indexOf('.') !== -1) {
          foreignKey = fk.substring(fk.indexOf('.') + 1);
        } else {
          foreignKey = fk;
        }
        if ((foreignKey != null) && pkFields.contains(foreignKey)) {
          if (!grid.dataSource.wildRecordJS) {
            grid.dataSource.wildRecordJS = {};
          }
          grid.dataSource.wildRecordJS[field] = _masterGrid.getSelectedRecord()[foreignKey];
          lks = grid.dataSource.getLookupFields(field);
          for (lk in lks) {
            grid.dataSource.wildRecordJS[lk] = _masterGrid.getSelectedRecord()[lk];
          }
        }
      }
    },
    "selectRecordsByKey": function(keyValues, newState, callback) {
      var i, keyValue, len, results;
      if (!isc.isA.Array(keyValues)) {
        keyValues = [keyValues];
      }
      results = [];
      for (i = 0, len = keyValues.length; i < len; i++) {
        keyValue = keyValues[i];
        results.push(typeof this.selectSingleRecordByKey === "function" ? this.selectSingleRecordByKey(keyValue, newState, callback) : void 0);
      }
      return results;
    },
    "setSelectionChanged": function(func) {
      this.grid.setSelectionChanged(func);
    },
    "getRecordIndex": function(record) {
      return this.grid.getRecordIndex(record);
    },
    "selectRecordsSS": function(records, newState) {
      var getPkObj, keysObj, pkFields;
      if (!isc.isA.Array(records)) {
        records = [records];
      }
      pkFields = this.grid.dataSource.getPrimaryKeyFieldsArray();
      getPkObj = function(pkFields, records) {
        return records.map(function(record) {
          var res;
          res = {};
          pkFields.forEach(function(key) {
            res[key] = record[key];
          });
          return res;
        });
      };
      keysObj = getPkObj(pkFields, records);
      keysObj.forEach((function(_this) {
        return function(key) {
          var _selectedRecord;
          _selectedRecord = _this.grid.findByKey(key);
          if (_selectedRecord != null) {
            switch (_this.grid.selectionType) {
              case "multiple":
                _this.grid.selectRecord(_selectedRecord, newState);
                break;
              case "simple":
              case "single":
                if (_this.grid.selection != null) {
                  _this.grid.selectSingleRecord(_selectedRecord, newState);
                } else {
                  _this.grid.selectRecord(_selectedRecord, newState);
                }
                break;
              default:
                void 0;
            }
          }
        };
      })(this));
    },
    "initAdvancedFilter": function() {
      var toolStrip;
      if (this.showAdvancedFilter) {
        this.filterBuilder = isc.FilterBuilderSS.create({
          "allowEmpty": true,
          "animateMembers": true,
          "criteria": this.getCriteria(),
          "showResizeBar": true,
          "animateMemberTime": 500,
          "dataSource": this.dataSource,
          "showShadow": true
        });
        toolStrip = isc.ToolStrip.create({
          "members": [
            isc.ToolStripButton.create({
              "click": (function(_this) {
                return function() {
                  var a, b, c;
                  a = _this.filterBuilder.getCriteria();
                  b = _this.grid.getFilterEditorCriteria(true);
                  c = isc.DataSourceSS.combineCriteriaSS(a, b, "left");

                  /*Чисто для отладки */

                  /*jsa = isc.JSONSS.encode a
                  							jsb = isc.JSONSS.encode b
                  							jsc = isc.JSONSS.encode c
                   */
                  _this.grid.fetchData(c);
                };
              })(this),
              "icon": "filter.png"
            })
          ],
          "width": 30
        });
        this.filterBuilder.addMember(toolStrip);
        this.addMember(this.filterBuilder);
        this.addMember(isc.SpacerItem.create({
          "height": 1,
          "width": "100%"
        }));
      }
      this.addMember(this.grid);
    },
    "startEditingNewInForm": function(obj, fields, callback, requestProperties) {
      this.grid.startEditingNewInForm(obj, fields, callback, requestProperties);
    },
    "startEditingInForm": function(obj, fields, callback, requestProperties) {
      this.grid.startEditingInForm(obj, fields, callback, requestProperties);
    }
  });

}).call(this);
