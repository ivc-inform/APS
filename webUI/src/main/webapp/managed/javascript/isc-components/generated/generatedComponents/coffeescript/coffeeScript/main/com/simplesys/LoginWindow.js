(function() {
  var simpleSyS;

  simpleSyS = this.simpleSyS;

  isc.defineClass("LoginWindow", isc.WindowSS).addProperties({
    "doLogin": function(form) {
      var login, password;
      if (form.validate() === true) {
        password = form.getItem("password").getValue();
        login = form.getItem("login").getValue();
        isc.RPCManagerSS.sendRequest({
          "timeout": 60000,
          "sendNoQueue": true,
          "callback": (function(_this) {
            return function(rpcResponse) {
              var _values, data, errorMessage, failure, loginItem, ref, status;
              data = rpcResponse.data;
              if (data == null) {
                _this.logWarn("rpcResponse: " + (isc.JSON.encode(rpcResponse, {
                  prettyPrint: true
                })));
              } else {
                failure = function() {
                  form.setValue("loginFailure", errorMessage);
                  form.showItem("loginFailure");
                  form.focusInItem("login");
                  if (form.loginSuccessProcedure != null) {
                    form.loginSuccessProcedure(false);
                  }
                };
                if ((data != null) && (data.data != null)) {
                  if (isc.isA.Object(data.data)) {
                    data = data.data;
                  }
                  status = data.status;
                  errorMessage = data.errorMessage;
                  if (status === isc.RPCResponse.STATUS_SUCCESS) {
                    isc.OfflineSS.put((_this.getIdentifier()) + "login", login);
                    loginItem = form.getItem("login");
                    if (loginItem.valueMap != null) {
                      if (!loginItem.valueMap.contains(login)) {
                        loginItem.valueMap.add(login);
                      }
                      _values = (loginItem.valueMap.filter(function(item) {
                        return item != null;
                      })).sort();
                      loginItem.setValueMap(_values);
                      isc.OfflineSS.putArray((_this.getIdentifier()) + "logins", _values);
                    }
                    if (_this.savePassword) {
                      isc.OfflineSS.put("" + (_this.getIdentifier()) + login + "password", password);
                    }
                    simpleSyS.app.login = login;
                    simpleSyS.app.password = password;
                    simpleSyS.app.userId = data.userId;
                    simpleSyS.app.codeGroup = data.codeGroup;
                    simpleSyS.app.captionUser = data.captionUser;
                    _this.simpleSysContextPath = data.simpleSysContextPath;
                    simpleSyS.simpleSysContextPath = data.simpleSysContextPath;
                    isc.OfflineSS.put((_this.getIdentifier()) + "codeGroup", simpleSyS.app.codeGroup);
                    form.hideItem("loginFailure");
                    _this.markForDestroy();
                    if (_this.reload === true) {
                      if (form.loginSuccessProcedure != null) {
                        form.loginSuccessProcedure(true, data.captionUser, data.codeGroup);
                      } else {
                        if ((ref = simpleSyS.app.ViewLoaderC) != null) {
                          if (typeof ref.setViewURL === "function") {
                            ref.setViewURL(_this.mainPageLogged);
                          }
                        }
                      }
                    }
                  } else {
                    failure();
                  }
                } else {
                  failure();
                }
              }
            };
          })(this),
          "actionURL": simpleSyS.app.logining.URILOGIN,
          "data": {
            "password": password,
            "login": login
          }
        });
      }
    },
    "isModal": true,
    "identifier": "4E716784-FD33-068A-1176-FCF2686908E5",
    "modalMaskOpacity": 70,
    "showMinimizeButton": false,
    "showMaximizeButton": false,
    "showCloseButton": false,
    "autoSize": true,
    "headerIconPath": "key-login-icon.png",
    "title": "Аутентификация...",
    "initWidget": function() {
      this.Super("initWidget", arguments);
      if (simpleSyS == null) {
        simpleSyS = {
          app: {},
          devGroup: function() {
            return true;
          }
        };
      } else if (!isc.isA.Function(simpleSyS.devGroup)) {
        simpleSyS.devGroup = function() {
          return true;
        };
      }
      this.login = isc.OfflineSS.get((this.getIdentifier()) + "login");
      this.codeGroup = isc.OfflineSS.get((this.getIdentifier()) + "codeGroup");
      this.savePassword = isc.OfflineSS.getBoolean("" + (this.getIdentifier()) + this.login + "savePassword", false);
      this.loginForm = isc.DynamicFormSS.create({
        "autoDraw": false,
        "autoFocus": true,
        "loginSuccessProcedure": this.loginSuccessProcedure,
        "fields": [
          {
            "colSpan": 2,
            "visible": false,
            "name": "loginFailure",
            "align": "center",
            "cellStyle": "formCellError",
            "titleStyle": "Error",
            "wrap": true,
            "width": 250,
            "editorType": "BlurbItem"
          }, {
            "required": true,
            "type": simpleSyS.devGroup(this.codeGroup) ? "ComboboxItemWithClearButton" : "text",
            changed: (function(_this) {
              return function(form, item, value) {
                var password;
                if (isc.isA.ComboBoxItem(item)) {
                  _this.loginForm.hideItem("loginFailure");
                  password = isc.OfflineSS.get("" + (_this.getIdentifier()) + value + "password");
                  _this.loginForm.setValue("password", password);
                }
              };
            })(this),
            "keyPress": function(item, form, keyName) {
              if (keyName === "Enter") {
                form.focusInItem("password");
                return false;
              }
              form.hideItem("loginFailure");
            },
            "name": "login",
            "title": "\u041B\u043E\u0433\u0438\u043D",
            "requiredMessage": "\u041F\u043E\u043B\u0435 '\u041B\u043E\u0433\u0438\u043D' \u0434\u043E\u043B\u0436\u043D\u043E \u0431\u044B\u0442\u044C \u0437\u0430\u043F\u043E\u043B\u043D\u0435\u043D\u043E.",
            "width": 250,
            "value": this.login,
            "_showValueAfterDraw": function() {
              void 0;
            },
            "onButtonClearClick": (function(_this) {
              return function(value) {
                var _values, login, loginItem;
                loginItem = _this.loginForm.getItem("login");
                login = value;
                (_this.loginForm.getItem("password")).clearValue();
                isc.OfflineSS.remove("" + (_this.getIdentifier()) + login + "password");
                if (loginItem.valueMap != null) {
                  loginItem.valueMap.remove(login);
                  _values = (loginItem.valueMap.filter(function(item) {
                    return item != null;
                  })).sort();
                  loginItem.setValueMap(_values);
                  isc.OfflineSS.putArray((_this.getIdentifier()) + "logins", _values);
                }
              };
            })(this),
            "valueMap": simpleSyS.devGroup(this.codeGroup) ? (isc.OfflineSS.getArray((this.getIdentifier()) + "logins")).sort() : void 0
          }, {
            "required": true,
            "keyPress": (function(_this) {
              return function(item, form, keyName, characterValue) {
                if (keyName === "Enter") {
                  _this.doLogin(form);
                  return false;
                }
                form.hideItem("loginFailure");
              };
            })(this),
            "name": "password",
            "title": "\u041F\u0430\u0440\u043E\u043B\u044C",
            "width": 250,
            "requiredMessage": "\u041F\u043E\u043B\u0435 '\u041F\u0430\u0440\u043E\u043B\u044C' \u0434\u043E\u043B\u0436\u043D\u043E \u0431\u044B\u0442\u044C \u0437\u0430\u043F\u043E\u043B\u043D\u0435\u043D\u043E.",
            "editorType": "PasswordItem",
            "value": (this.savePassword ? isc.OfflineSS.get("" + (this.getIdentifier()) + this.login + "password") : void 0)
          }, {
            visible: simpleSyS.devGroup(this.codeGroup),
            title: "Сохранить пароль",
            name: "savePassword",
            type: "checkbox",
            value: this.savePassword,
            changed: (function(_this) {
              return function(form, item, value) {
                isc.OfflineSS.putBoolean("" + (_this.getIdentifier()) + _this.login + "savePassword", value);
                _this.savePassword = value;
              };
            })(this)
          }
        ]
      });
      this.ed = isc.OkCancelPanelWithOutOwnerDestroy.create({
        owner: this,
        height: 35,
        width: 306,
        okCaption: "Войти ...",
        cancelCaption: "Отменить ...",
        ownerHide: false,
        okFunction: (function(_this) {
          return function() {
            _this.doLogin(_this.loginForm);
          };
        })(this),
        cancelFunction: function() {
          isc.RPCManagerSS.logoutRequired();
          this.owner.markForDestroy();
        }
      });
      this.addItems([this.loginForm, this.ed]);
      this.loginForm.focusInItem("login");
    },
    "showModalMask": true,
    "autoCenter": true
  });

}).call(this);
