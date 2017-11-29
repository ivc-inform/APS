(function() {
  var ref;

  if (isc.module_RealtimeMessaging == null) {
    isc.module_RealtimeMessaging = 1;
    isc.defineClass("MessagingSS").addClassProperties({
      fireCallback: isc.Class.fireCallback,
      _maxRecentIDLength: 20,
      _subscribeReconnectDelay: 100,
      eventStream: function() {
        var res;
        res = !!window.EventSource;
        if (res == null) {
          isc.error("Ваш браузер не поддерживает технологию SSE, что делает невозможным автоматическое получение сообщений от сервера. (Данная задача находится в доработке.)");
        }
        return res;
      },
      _channels: {},
      _recentIDList: [],
      _handleEventSourceError: function(e) {
        return void 0;
      },
      getSubscribedChannels: function() {
        return isc.getKeys(this._channels);
      },
      subscribe: function(channels, callback, subscribeCallback, target, event) {
        var executed;
        if (channels != null) {
          if (isc.isA.Function(subscribeCallback) === false && isc.isA.Object(subscribeCallback) === true && (target == null)) {
            target = subscribeCallback;
          }
          if (isc.isA.Array(channels) === false) {
            channels = [channels];
          }
          executed = false;
          channels.forEach((function(_this) {
            return function(channel) {
              _this._channels[channel] = {
                isChannel: true,
                target: target,
                callback: callback,
                event: event
              };
              executed = true;
            };
          })(this));
          if (executed === true) {
            this._reconnect(event);
            if (subscribeCallback != null) {
              isc.MessagingSS.fireCallback(subscribeCallback);
            }
          }
        }
      },
      unsubscribe: function(channels, unSubscribeCallback, event) {
        if (channels != null) {
          if (isc.isA.Array(channels) === false) {
            channels = [channels];
          }
          channels.forEach((function(_this) {
            return function(channel) {
              if (_this._channels[channel] != null) {
                delete _this._channels[channel];
              }
            };
          })(this));
          if (isc.isA.emptyObject(this._channels) === true) {
            this.disconnect(event);
          }
          if (unSubscribeCallback != null) {
            isc.MessagingSS.fireCallback(unSubscribeCallback);
          }
        }
      },
      connected: function() {
        return isc.getKeys(this._channels != null).length > 0;
      },
      _event: "message",
      _cleanup: function(event) {
        if (event == null) {
          event = this._event;
        }
        if (this._es != null) {
          this._es.removeEventListener(event, this._message, false);
          this._es.close();
          delete this._es;
        }
      },
      disconnect: function(event) {
        this._cleanup(event);
      },
      _reconnect: function(event) {
        var _connect, _reconnect;
        if (isc.Page.isLoaded() !== true) {
          if (this._setLoadEventHandler == null) {
            _reconnect = function() {
              return isc.MessagingSS._reconnect(callback);
            };
            isc.Page.setEvent("load", _reconnect);
            this._setLoadEventHandler = true;
          }
        }
        if (this._subscribeReconnectTimer == null) {
          _connect = function() {
            isc.MessagingSS._connect(event);
          };
          this._subscribeReconnectTimer = isc.Timer.setTimeout(_connect, this._subscribeReconnectDelay, isc.Timer.MSEC);
        }
      },
      checkSimpleSysContextPath: function() {
        if ((this.simpleSysContextPath == null) && (simpleSyS.simpleSysContextPath == null)) {
          Log.logError("simpleSysContextPath undefined");
        }
      },
      messagingSubscribeURL: function() {
        this.checkSimpleSysContextPath();
        return (this.simpleSysContextPath != null ? this.simpleSysContextPath : simpleSyS.simpleSysContextPath) + "Message/Subscribe";
      },
      messagingSendURL: function() {
        this.checkSimpleSysContextPath();
        return (this.simpleSysContextPath != null ? this.simpleSysContextPath : simpleSyS.simpleSysContextPath) + "Message/Send";
      },
      _connect: function(event) {
        var _url, json, uriBuilder;
        if (event == null) {
          event = this._event;
        }
        isc.Timer.clear(this._subscribeReconnectTimer);
        this._subscribeReconnectTimer = null;
        this._cleanup();
        _url = this.messagingSubscribeURL();
        json = isc.JSONSS.encode(this._channels);

        /*console.log "json (cs): #{json}" */
        uriBuilder = isc.URIBuilder.create(isc.Page.getURL(_url));
        uriBuilder.setQueryParam("subscribedChannels", json);
        uriBuilder.setQueryParam("eventStream", "true");
        if (uriBuilder.uri.length > 2000) {
          Log.logWarn("URI: " + uriBuilder.uri);
          Log.logWarn("URI, БОЛЕЕ 2000 символов, это может быть опвсно");

          /*return */
        }

        /*todo В случае необходимости подключения большого кол-ва каналов, необходимо разбиение их на несколько EventSource */

        /*console.log "uriBuilder.uri (cs): #{uriBuilder.uri}" */
        this._es = new EventSource(uriBuilder.uri);
        this._es.onerror = this._handleEventSourceError;

        /*@_es.addEventListener event, @_message, false */
        this._es.addEventListener(event, this._message);
      },
      _message: function(message) {
        var channels, data, id;
        data = isc.JSONSS.decode(message.data);
        channels = data.channels;
        if (isc.isA.Array(channels) === false) {
          channels = [channels];
        }
        id = message.lastEventId;
        if ((id != null) && id !== strEmpty) {
          if (isc.MessagingSS._recentIDList.contains(id) === true) {
            return void 0;
          }
          isc.MessagingSS._recentIDList.push(id);
          if (isc.MessagingSS._recentIDList.length > isc.MessagingSS._maxRecentIDLength) {
            isc.MessagingSS._recentIDList.shift();
          }
        }
        channels.forEach((function(_this) {
          return function(channel) {
            var callback;
            if (isc.MessagingSS._channels[channel] != null) {
              channel = isc.MessagingSS._channels[channel];
              callback = channel.callback;
              if (callback != null) {
                isc.MessagingSS.fireCallback(callback, "data", [data], channel);
              }
            }
          };
        })(this));
      },
      send: function(channels, data, callback) {
        if (isc.isA.Array(channels) === false) {
          channels = [channels];
        }
        isc.RPCManagerSS.sendRequest({
          actionURL: this.messagingSendURL(),
          data: {
            data: data,
            channels: channels
          },
          callback: callback
        });
      }
    });
  } else {
    if (isc.isA.Function(typeof isc !== "undefined" && isc !== null ? (ref = isc.Log) != null ? ref.logError : void 0 : void 0) === true) {
      isc.Log.logError("Duplicate load of module 'RealtimeMessaging'.");
    }
  }

}).call(this);
