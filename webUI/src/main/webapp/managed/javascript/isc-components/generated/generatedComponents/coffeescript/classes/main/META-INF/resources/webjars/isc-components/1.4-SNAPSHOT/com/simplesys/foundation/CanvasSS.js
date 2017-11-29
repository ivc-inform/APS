(function() {
  isc.Canvas.addProperties({
    setBlinkBorder: function(newBorder, intervalBlink, timeoutBlink) {
      var border, intervalID, step, timeOutID;
      if (intervalBlink == null) {
        intervalBlink = 500;
      }
      if (!timeoutBlink) {
        timeoutBlink = 5000;
      }
      border = this.border;
      step = 0;
      intervalID = window.setInterval(function() {
        if (step === 0) {
          this.setBorder(newBorder);
          step = 1;
        } else {
          this.setBorder(border);
          step = 0;
        }
      }, intervalBlink);
      timeOutID = window.setTimeout(function() {
        clearInterval(intervalID);
        clearTimeout(timeOutID);
        this.setBorder(border);
      }, timeoutBlink);
    },
    setBlinkBorder1: function(newBorder, intervalBlink) {
      var border, intervalID, step;
      if (intervalBlink == null) {
        intervalBlink = 500;
      }
      border = this.border;
      step = 0;
      intervalID = window.setInterval((function(_this) {
        return function() {
          if (step === 0) {
            _this.setBorder(newBorder);
            step = 1;
          } else {
            _this.setBorder(border);
            step = 0;
          }
        };
      })(this), intervalBlink);
      ({
        intervalID: intervalID,
        border: border,
        canvas: this
      });
    },
    getRootCanvas: function() {
      var res, result;
      if (this.getParentCanvas() == null) {
        return this;
      }
      res = this.getParentCanvas();
      while (res) {
        result = res;
        res = res.getParentCanvas();
      }
      return result;
    },
    initWidget: function() {
      isc.Class.addGlobalSS(this.getIdentifier(), this);
    },
    setShowResizeBar1: function(value) {
      this.setShowResizeBar(value);
      return this;
    },
    hide1: function() {
      this.hide();
      return this;
    }
  });


  /*destroy: ->
  	@beforeDestroy? @
  	@Super "destroy", arguments
  	return
   */

  isc.Canvas.addClassMethods({
    resizeControls: function (delta) {
        var resizeIcons = isc.Canvas.autoResizeIcons,
            resizeAutoChildAttributes = isc.Canvas.autoResizeAutoChildAttributes;

        var heightClasses = [
            isc.Button,
            isc.IButton,
            isc.SimpleButton,
            isc.IconButton,
            isc.IconButtonSS,
            isc.IButtonSS,
            isc.StretchImgButton,
            isc.ToolStrip,
            isc.ToolStripButton,
            isc.ToolStripMenuButton,
            isc.MenuButton,

            isc.FormItem,
            isc.TextItem,
            isc.TextItemSS,
            isc.TabSet,
            isc.TabSetSS,
            isc.SelectItem,
            isc.DateItem,
            isc.ComboBoxItem,
            isc.SpinnerItem,
            isc.ButtonItem,
            isc.SubmitItem,
            isc.Progressbar,
            isc.MenuItem,
            isc.MenuItemSS,
            isc.SectionItem,
            isc.RibbonBar,
            isc.RibbonBarSS,
            isc.Window,
            isc.WindowSS,
            isc.RibbonGroup,
            isc.RibbonGroupSS
        ];

        for (var i = 0; i < heightClasses.length; i++) {
            this.modifyProperty(heightClasses[i], delta);
        }

        // update both cellHeight and normalCellHeight to avoid incorrect use of tallBaseStyle
        this.modifyProperty(isc.ListGrid, delta, "cellHeight");
        this.modifyProperty(isc.ListGrid, delta, "normalCellHeight");
        this.modifyProperty(isc.ListGrid, delta, "headerHeight");
        this.modifyProperty(isc.ListGrid, delta, "summaryRowHeight");
        this.modifyProperty(isc.ListGrid, delta, "filterEditorHeight");

        this.modifyProperty(isc.DetailViewer, delta, "rowHeight");

        // update both cellHeight and normalCellHeight to avoid incorrect use of tallBaseStyle
        this.modifyProperty(isc.Menu, delta, "cellHeight");
        this.modifyProperty(isc.MenuSS, delta, "cellHeight");
        this.modifyProperty(isc.Menu, delta, "normalCellHeight");
        this.modifyProperty(isc.MenuSS, delta, "normalCellHeight");

        // update both cellHeight and normalCellHeight to avoid incorrect use of tallBaseStyle
        // defined on isc.PickList interface, but ends up copied to implementing classes
        this.modifyProperty(isc.SelectItem, delta, "pickListCellHeight");
        this.modifyProperty(isc.ComboBoxItem, delta, "pickListCellHeight");
        this.modifyProperty(isc.PickListMenu, delta, "normalCellHeight");

        this.modifyProperty(isc.Calendar, delta, "rowHeight");
        this.modifyProperty(isc.Calendar, delta, "eventHeaderHeight");

        this.modifyProperty(isc.TabSet, delta, "tabBarThickness");
        this.modifyProperty(isc.TabBar, delta, "breadth"); // note: doesn't seem necessary given tabBarThickness

        this.modifyProperty(isc.SectionStack, delta, "headerHeight");

        this.modifyProperty(isc.Toolbar, delta, "height");
        this.modifyProperty(isc.ToolStripGroup, delta, "rowHeight");

        this.modifyDefaultsProperty(isc.Dialog, "toolbarDefaults", "height", delta);
        this.modifyDefaultsProperty(isc.PickTreeItem, "buttonDefaults", "height", delta);
        this.modifyDefaultsProperty(isc.Window, "headerDefaults", "height", delta);

        isc.Canvas._currentSizeIncrease = delta;

        if (resizeIcons) {
            this.resizeIcons("controls", delta);
        }

        if (resizeAutoChildAttributes) {
            this.resizeAutoChildAttributes("controls", delta);
        }

    }
  });

}).call(this);
