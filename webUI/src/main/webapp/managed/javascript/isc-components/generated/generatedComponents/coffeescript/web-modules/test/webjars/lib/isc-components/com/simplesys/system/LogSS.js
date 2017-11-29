(function() {
  var _logMethods, simpleSyS;

  simpleSyS = this.simpleSyS;

  _logMethods = {
    _reportJSErrorStack: function(message) {
      this.logWarn1(message);
    },
    logMessage: function(priority, message, category, timestamp) {
      var idString, log;
      log = isc.Log;
      if (log != null) {
        if (!priority) {
          priority = log.defaultPriority;
        }
        if (priority <= log.stackTracePriority && (this.getStackTrace != null)) {
          message += "\nStack trace:\n" + (this.getStackTrace(arguments, 2));
        }
        if (!category) {
          category = this.Class;
        }
        idString = this.ID;
        if (isc.FormItem && isc.isA.FormItem(this) && (this.name != null)) {
          idString += "[" + this.name + "]";
        }
        log.log(priority, message, category, idString, this, timestamp);
        if (log.isEnabledFor(category, priority, this)) {
          return log._makeLogMessage(priority, message, category, idString, timestamp);
        }
      }
    },
    logDebug: function(message, category) {
      message = this.logMessage(isc.Log.DEBUG, message, category);
      if (message != null) {
        console.log(message);
      }
      return message;
    },
    logInfo: function(message, category) {
      message = this.logMessage(isc.Log.INFO, message, category);
      if (message != null) {
        console.info(message);
      }
      return message;
    },
    logWarn: function(message, category) {
      message = this.logMessage(isc.Log.WARN, message, category);
      if (message != null) {
        console.warn(message);
      }
      return message;
    },
    logWarn1: function(message, category) {
      message = this.logMessage(isc.Log.WARN, message, category);
      if (message != null) {
        console.error(message);
      }
      return message;
    },
    logError: function(message, category) {
      message = this.logMessage(isc.Log.ERROR, message, category);
      if (message != null) {
        console.error(message);
      }
      return message;
    },
    logFatal: function(message, category) {
      message = this.logMessage(isc.Log.FATAL, message, category);
      if (message != null) {
        console.error(message);
      }
      return message;
    },
    setLogPriority: function(category, priority) {
      isc.Log.setPriority(category, priority, this);
    },
    addToMasterLog: function() {
      var a;
      a = 0;
    }
  };

  isc.Class.addMethods(_logMethods);

  isc.Class.addClassMethods(_logMethods);

  isc.Log.addClassMethods({
    addToMasterLog: function (message) {

        //if (window.console != null) window.console.warn("*" + message);

        //!DONTOBFUSCATE
        // NOTE: we're not obfuscating so the "message" parameter will retain that name later
        // remember the message passed in
        this._messageCache[this._messageIndex] = message;

        // set up for the next message
        this._messageIndex++;

        // if we're beyond the appropriate number of messages to remember
        if (this._messageIndex > this.messageCount) {
            // roll over the messsageIndex to 0
            this._messageIndex = 0;
        }
        if (this.showInlineLogs) {
            this.updateInlineLogResults();
        }
    }
  });

}).call(this);
