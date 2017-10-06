package io.udash.wrappers.jqueryui

import io.udash.wrappers.jquery.JQuery

import scala.scalajs.js
import org.scalajs.dom.raw._

import scala.scalajs.js.{Date, |}

import scala.language.implicitConversions

object JQueryUi {
  implicit def jquery2ui(jquery: JQuery): JQueryUi =
    jquery.asInstanceOf[JQueryUi]
}

@js.native
trait JQueryUi extends JQuery {
  def accordion(): JQueryUi = js.native
  def accordion(methodName: String): JQueryUi = js.native
  def accordion(options: AccordionOptions): JQueryUi = js.native
  def accordion(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def accordion(optionLiteral: String, options: AccordionOptions): js.Dynamic = js.native
  def accordion(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def autocomplete(): JQueryUi = js.native
  def autocomplete(methodName: String): JQueryUi = js.native
  def autocomplete(options: AutocompleteOptions): JQueryUi = js.native
  def autocomplete(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def autocomplete(optionLiteral: String, options: AutocompleteOptions): js.Dynamic = js.native
  def autocomplete(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def button(): JQueryUi = js.native
  def button(methodName: String): JQueryUi = js.native
  def button(options: ButtonOptions): JQueryUi = js.native
  def button(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def button(optionLiteral: String, options: ButtonOptions): js.Dynamic = js.native
  def button(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def buttonset(): JQueryUi = js.native
  def buttonset(methodName: String): JQueryUi = js.native
  def buttonset(options: ButtonOptions): JQueryUi = js.native
  def buttonset(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def buttonset(optionLiteral: String, options: ButtonOptions): js.Dynamic = js.native
  def buttonset(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  /**
   * Initialize a datepicker
   */
  def datepicker(): JQueryUi = js.native
  /**
   * Removes the datepicker functionality completely. This will return the element back to its pre-init state.
   *
   * @param methodName 'destroy'
   */
  def datepicker(methodName: String): JQueryUi = js.native
  /**
   * Opens the datepicker in a dialog box.
   *
   * @param methodName 'dialog'
   * @param date The initial date.
   * @param onSelect A callback function when a date is selected. The function receives the date text and date picker instance as parameters.
   * @param settings The new settings for the date picker.
   * @param pos The position of the top/left of the dialog as [x, y] or a MouseEvent that contains the coordinates. If not specified the dialog is centered on the screen.
   */
  def datepicker(methodName: String, date: Date, onSelect: js.Function0[Unit], settings: DatepickerOptions, pos: js.Array[Double]): JQueryUi = js.native
  /**
   * Opens the datepicker in a dialog box.
   *
   * @param methodName 'dialog'
   * @param date The initial date.
   * @param onSelect A callback function when a date is selected. The function receives the date text and date picker instance as parameters.
   * @param settings The new settings for the date picker.
   * @param pos The position of the top/left of the dialog as [x, y] or a MouseEvent that contains the coordinates. If not specified the dialog is centered on the screen.
   */
  def datepicker(methodName: String, date: Date, onSelect: js.Function0[Unit], settings: DatepickerOptions, pos: MouseEvent): JQueryUi = js.native
  /**
   * Opens the datepicker in a dialog box.
   *
   * @param methodName 'dialog'
   * @param date The initial date.
   * @param onSelect A callback function when a date is selected. The function receives the date text and date picker instance as parameters.
   * @param settings The new settings for the date picker.
   * @param pos The position of the top/left of the dialog as [x, y] or a MouseEvent that contains the coordinates. If not specified the dialog is centered on the screen.
   */
  def datepicker(methodName: String, date: String, onSelect: js.Function0[Unit], settings: DatepickerOptions, pos: js.Array[Double]): JQueryUi = js.native
  /**
   * Opens the datepicker in a dialog box.
   *
   * @param methodName 'dialog'
   * @param date The initial date.
   * @param onSelect A callback function when a date is selected. The function receives the date text and date picker instance as parameters.
   * @param settings The new settings for the date picker.
   * @param pos The position of the top/left of the dialog as [x, y] or a MouseEvent that contains the coordinates. If not specified the dialog is centered on the screen.
   */
  def datepicker(methodName: String, date: String, onSelect: js.Function0[Unit], settings: DatepickerOptions, pos: MouseEvent): JQueryUi = js.native
  /**
   * Returns the current date for the datepicker or null if no date has been selected.
   *
   * @param methodName 'getDate'
   */
  /**
   * Close a previously opened date picker.
   *
   * @param methodName 'hide'
   */
  /**
   * Determine whether a date picker has been disabled.
   *
   * @param methodName 'isDisabled'
   */
  /**
   * Redraw the date picker, after having made some external modifications.
   *
   * @param methodName 'refresh'
   */
  /**
   * Sets the date for the datepicker. The new date may be a Date object or a string in the current date format (e.g., "01/26/2009"), a number of days from today (e.g., +7) or a string of values and periods ("y" for years, "m" for months, "w" for weeks, "d" for days, e.g., "+1m +7d"), or null to clear the selected date.
   *
   * @param methodName 'setDate'
   * @param date The new date.
   */
  def datepicker(methodName: String, date: Date): JQueryUi = js.native
  /**
   * Sets the date for the datepicker. The new date may be a Date object or a string in the current date format (e.g., "01/26/2009"), a number of days from today (e.g., +7) or a string of values and periods ("y" for years, "m" for months, "w" for weeks, "d" for days, e.g., "+1m +7d"), or null to clear the selected date.
   *
   * @param methodName 'setDate'
   * @param date The new date.
   */
  def datepicker(methodName: String, date: String): JQueryUi = js.native
  /**
   * Open the date picker. If the datepicker is attached to an input, the input must be visible for the datepicker to be shown.
   *
   * @param methodName 'show'
   */
  /**
   * Returns a jQuery object containing the datepicker.
   *
   * @param methodName 'widget'
   */
  /**
   * Get the altField option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'altField'
   */
  /**
   * Set the altField option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'altField'
   * @param altFieldValue An input element that is to be updated with the selected date from the datepicker. Use the altFormat option to change the format of the date within this field. Leave as blank for no alternate field.
   */
  def datepicker(methodName: String, optionName: String, altFieldValue: String): JQueryUi = js.native
  /**
   * Set the altField option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'altField'
   * @param altFieldValue An input element that is to be updated with the selected date from the datepicker. Use the altFormat option to change the format of the date within this field. Leave as blank for no alternate field.
   */
  def datepicker(methodName: String, optionName: String, altFieldValue: JQueryUi): JQueryUi = js.native
  /**
   * Set the altField option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'altField'
   * @param altFieldValue An input element that is to be updated with the selected date from the datepicker. Use the altFormat option to change the format of the date within this field. Leave as blank for no alternate field.
   */
  def datepicker(methodName: String, optionName: String, altFieldValue: Element): JQueryUi = js.native
  /**
   * Get the altFormat option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'altFormat'
   */
  /**
   * Set the altFormat option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'altFormat'
   * @param altFormatValue The dateFormat to be used for the altField option. This allows one date format to be shown to the user for selection purposes, while a different format is actually sent behind the scenes. For a full list of the possible formats see the formatDate function
   */
  /**
   * Get the appendText option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'appendText'
   */
  /**
   * Set the appendText option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'appendText'
   * @param appendTextValue The text to display after each date field, e.g., to show the required format.
   */
  /**
   * Get the autoSize option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'autoSize'
   */
  /**
   * Set the autoSize option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'autoSize'
   * @param autoSizeValue Set to true to automatically resize the input field to accommodate dates in the current dateFormat.
   */
  def datepicker(methodName: String, optionName: String, autoSizeValue: Boolean): JQueryUi = js.native
  /**
   * Get the beforeShow option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'beforeShow'
   */
  /**
   * Set the beforeShow option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'beforeShow'
   * @param beforeShowValue A function that takes an input field and current datepicker instance and returns an options object to update the datepicker with. It is called just before the datepicker is displayed.
   */
  def datepicker(methodName: String, optionName: String, beforeShowValue: js.Function2[Element, js.Any, DatepickerOptions]): JQueryUi = js.native
  /**
   * Get the beforeShow option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'beforeShowDay'
   */
  /**
   * Set the beforeShow option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'beforeShowDay'
   * @param beforeShowDayValue A function that takes a date as a parameter and must return an array with:
   * [0]: true/false indicating whether or not this date is selectable
   * [1]: a CSS class name to add to the date's cell or "" for the default presentation
   * [2]: an optional popup tooltip for this date
   * The function is called for each day in the datepicker before it is displayed.
   */
  def datepicker(methodName: String, optionName: String, beforeShowDayValue: js.Function1[Date, js.Array[js.Any]]): JQueryUi = js.native
  /**
   * Get the buttonImage option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'buttonImage'
   */
  /**
   * Set the buttonImage option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'buttonImage'
   * @param buttonImageValue A URL of an image to use to display the datepicker when the showOn option is set to "button" or "both". If set, the buttonText option becomes the alt value and is not directly displayed.
   */
  /**
   * Get the buttonImageOnly option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'buttonImageOnly'
   */
  /**
   * Set the buttonImageOnly option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'buttonImageOnly'
   * @param buttonImageOnlyValue Whether the button image should be rendered by itself instead of inside a button element. This option is only relevant if the buttonImage option has also been set.
   */
  /**
   * Get the buttonText option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'buttonText'
   */
  /**
   * Get the autohide option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'autohide'
   */
  /**
   * Get the endDate after initialization
   *
   * @param methodName 'option'
   * @param optionName 'endDate'
   */
  /**
   * Set the buttonText option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'buttonText'
   * @param buttonTextValue The text to display on the trigger button. Use in conjunction with the showOn option set to "button" or "both".
   */
  /**
   * Get the calculateWeek option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'calculateWeek'
   */
  /**
   * Set the calculateWeek option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'calculateWeek'
   * @param calculateWeekValue A function to calculate the week of the year for a given date. The default implementation uses the ISO 8601 definition: weeks start on a Monday; the first week of the year contains the first Thursday of the year.
   */
  /**
   * Get the changeMonth option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'changeMonth'
   */
  /**
   * Set the changeMonth option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'changeMonth'
   * @param changeMonthValue Whether the month should be rendered as a dropdown instead of text.
   */
  /**
   * Get the changeYear option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'changeYear'
   */
  /**
   * Set the changeYear option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'changeYear'
   * @param changeYearValue Whether the year should be rendered as a dropdown instead of text. Use the yearRange option to control which years are made available for selection.
   */
  /**
   * Get the closeText option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'closeText'
   */
  /**
   * Set the closeText option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'closeText'
   * @param closeTextValue The text to display for the close link. Use the showButtonPanel option to display this button.
   */
  /**
   * Get the constrainInput option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'constrainInput'
   */
  /**
   * Set the constrainInput option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'constrainInput'
   * @param constrainInputValue When true, entry in the input field is constrained to those characters allowed by the current dateFormat option.
   */
  /**
   * Get the currentText option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'currentText'
   */
  /**
   * Set the currentText option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'currentText'
   * @param currentTextValue The text to display for the current day link. Use the showButtonPanel option to display this button.
   */
  /**
   * Get the dateFormat option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'dateFormat'
   */
  /**
   * Set the dateFormat option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'dateFormat'
   * @param dateFormatValue The format for parsed and displayed dates. For a full list of the possible formats see the formatDate function.
   */
  /**
   * Get the dayNames option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'dayNames'
   */
  /**
   * Set the dayNames option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'dayNames'
   * @param dayNamesValue The list of long day names, starting from Sunday, for use as requested via the dateFormat option.
   */
  def datepicker(methodName: String, optionName: String, dayNamesValue: js.Array[String]): JQueryUi = js.native
  /**
   * Get the dayNamesMin option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'dayNamesMin'
   */
  /**
   * Set the dayNamesMin option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'dayNamesMin'
   * @param dayNamesMinValue The list of minimised day names, starting from Sunday, for use as column headers within the datepicker.
   */
  /**
   * Get the dayNamesShort option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'dayNamesShort'
   */
  /**
   * Set the dayNamesShort option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'dayNamesShort'
   * @param dayNamesShortValue The list of abbreviated day names, starting from Sunday, for use as requested via the dateFormat option.
   */
  /**
   * Get the defaultDate option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'defaultDate'
   */
  /**
   * Set the defaultDate option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'defaultDate'
   * @param defaultDateValue A date object containing the default date.
   */
  def datepicker(methodName: String, optionName: String, defaultDateValue: Date): JQueryUi = js.native
  /**
   * Set the defaultDate option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'defaultDate'
   * @param defaultDateValue A number of days from today. For example 2 represents two days from today and -1 represents yesterday.
   */
  def datepicker(methodName: String, optionName: String, defaultDateValue: Double): JQueryUi = js.native
  /**
   * Set the defaultDate option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'defaultDate'
   * @param defaultDateValue A string in the format defined by the dateFormat option, or a relative date. Relative dates must contain value and period pairs; valid periods are "y" for years, "m" for months, "w" for weeks, and "d" for days. For example, "+1m +7d" represents one month and seven days from today.
   */
  /**
   * Get the duration option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'duration'
   */
  /**
   * Set the duration option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'duration'
   * @param durationValue Control the speed at which the datepicker appears, it may be a time in milliseconds or a string representing one of the three predefined speeds ("slow", "normal", "fast").
   */
  /**
   * Get the firstDay option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'firstDay'
   */
  /**
   * Set the firstDay option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'firstDay'
   * @param firstDayValue Set the first day of the week: Sunday is 0, Monday is 1, etc.
   */
  /**
   * Get the gotoCurrent option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'gotoCurrent'
   */
  /**
   * Set the gotoCurrent option, after initialization
   *
   * @param methodName 'option'
   * @param optionName 'gotoCurrent'
   * @param gotoCurrentValue When true, the current day link moves to the currently selected date instead of today.
   */
  /**
   * Gets the value currently associated with the specified optionName.
   *
   * @param methodName 'option'
   * @param optionName The name of the option to get.
   */
  def datepicker(methodName: String, optionName: String, otherParams: js.Any*): js.Dynamic = js.native
  // Used for getting and setting options
  def datepicker(methodName: String, otherParams: js.Any*): js.Dynamic = js.native
  /**
   * Initialize a datepicker with the given options
   */
  def datepicker(options: DatepickerOptions): JQueryUi = js.native
  def dialog(): JQueryUi = js.native
  def dialog(methodName: String): js.Any = js.native
  def dialog(options: DialogOptions): JQueryUi = js.native
  def dialog(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def dialog(optionLiteral: String, options: DialogOptions): js.Dynamic = js.native
  def dialog(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def draggable(): JQueryUi = js.native
  def draggable(methodName: String): js.Any = js.native
  def draggable(options: DraggableOptions): JQueryUi = js.native
  def draggable(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def draggable(optionLiteral: String, options: DraggableOptions): js.Dynamic = js.native
  def draggable(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def droppable(): JQueryUi = js.native
  def droppable(methodName: String): js.Any = js.native
  def droppable(options: DroppableOptions): JQueryUi = js.native
  def droppable(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def droppable(optionLiteral: String, options: DraggableOptions): js.Dynamic = js.native
  def droppable(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  var menu: js.Any = js.native
  def progressbar(): JQueryUi = js.native
  def progressbar(methodName: String): js.Any = js.native
  // number or boolean
  def progressbar(methodName: String, value: Double): Unit = js.native
  def progressbar(methodName: String, value: Boolean): Unit = js.native
  def progressbar(options: ProgressbarOptions): JQueryUi = js.native
  def progressbar(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def progressbar(optionLiteral: String, options: ProgressbarOptions): js.Dynamic = js.native
  def progressbar(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def resizable(): JQueryUi = js.native
  def resizable(methodName: String): Unit | JQueryUi = js.native
  def resizable(options: ResizableOptions): JQueryUi = js.native
  def resizable(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def resizable(optionLiteral: String, options: ResizableOptions): js.Dynamic = js.native
  def resizable(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def selectable(): JQueryUi = js.native
  def selectable(methodName: String): Unit | JQueryUi = js.native
  def selectable(options: SelectableOptions): JQueryUi = js.native
  def selectable(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def selectable(optionLiteral: String, options: SelectableOptions): js.Dynamic = js.native
  def selectable(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def selectmenu(): JQueryUi = js.native
  def selectmenu(methodName: String): JQueryUi | js.Dynamic = js.native
  def selectmenu(options: SelectMenuOptions): JQueryUi = js.native
  def selectmenu(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def selectmenu(optionLiteral: String, options: SelectMenuOptions): js.Dynamic = js.native
  def selectmenu(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def slider(): JQueryUi = js.native
  def slider(methodName: String): js.Any = js.native
  def slider(methodName: String, index: Double, value: Double): Unit = js.native
  def slider(methodName: String, values: js.Array[Double]): Unit = js.native
  def slider(options: SliderOptions): JQueryUi = js.native
  def slider(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def slider(optionLiteral: String, options: SliderOptions): js.Dynamic = js.native
  def slider(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def sortable(): JQueryUi = js.native
  def sortable(methodName: String): js.Any = js.native
  def sortable(options: SortableOptions): JQueryUi = js.native
  def sortable(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def sortable(methodName: String, options: js.Any): String = js.native
  def sortable(optionLiteral: String, options: SortableOptions): js.Dynamic = js.native
  def sortable(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def spinner(): JQueryUi = js.native
  def spinner(methodName: String): js.Any = js.native
  def spinner(methodName: String, pages: Double): Unit = js.native
  def spinner(options: SpinnerOptions): JQueryUi = js.native
  def spinner(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def spinner(optionLiteral: String, options: SpinnerOptions): js.Dynamic = js.native
  def spinner(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def tabs(): JQueryUi = js.native
  def tabs(methodName: String): js.Any = js.native
  def tabs(methodName: String, index: Double): Unit = js.native
  def tabs(options: TabsOptions): JQueryUi = js.native
  def tabs(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def tabs(optionLiteral: String, options: TabsOptions): js.Dynamic = js.native
  def tabs(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def tooltip(): JQueryUi = js.native
  def tooltip(methodName: String): js.Any = js.native
  def tooltip(options: TooltipOptions): JQueryUi = js.native
  def tooltip(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def tooltip(optionLiteral: String, options: TooltipOptions): js.Dynamic = js.native
  def tooltip(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def addClass(classNames: String, speed: Double, callback: js.Function): JQueryUi = js.native
  def addClass(classNames: String, speed: String, callback: js.Function): JQueryUi = js.native
  def addClass(classNames: String, speed: Double, easing: String, callback: js.Function): JQueryUi = js.native
  def addClass(classNames: String, speed: String, easing: String, callback: js.Function): JQueryUi = js.native
  def removeClass(classNames: String, speed: Double, callback: js.Function): JQueryUi = js.native
  def removeClass(classNames: String, speed: String, callback: js.Function): JQueryUi = js.native
  def removeClass(classNames: String, speed: Double, easing: String, callback: js.Function): JQueryUi = js.native
  def removeClass(classNames: String, speed: String, easing: String, callback: js.Function): JQueryUi = js.native
  def switchClass(removeClassName: String, addClassName: String, duration: Double, easing: String, complete: js.Function): JQueryUi = js.native
  def switchClass(removeClassName: String, addClassName: String, duration: String, easing: String, complete: js.Function): JQueryUi = js.native
  def toggleClass(className: String, duration: Double, easing: String, complete: js.Function): JQueryUi = js.native
  def toggleClass(className: String, duration: String, easing: String, complete: js.Function): JQueryUi = js.native
  def toggleClass(className: String, aswitch: Boolean, duration: Double, easing: String, complete: js.Function): JQueryUi = js.native
  def toggleClass(className: String, aswitch: Boolean, duration: String, easing: String, complete: js.Function): JQueryUi = js.native
  def effect(options: js.Any): JQueryUi = js.native
  def effect(effect: String, options: js.Any, duration: Double, complete: js.Function): JQueryUi = js.native
  def effect(effect: String, options: js.Any, duration: String, complete: js.Function): JQueryUi = js.native
  def hide(options: js.Any): JQueryUi = js.native
  def hide(effect: String, options: js.Any, duration: Double, complete: js.Function): JQueryUi = js.native
  def hide(effect: String, options: js.Any, duration: String, complete: js.Function): JQueryUi = js.native
  def show(options: js.Any): JQueryUi = js.native
  def show(effect: String, options: js.Any, duration: Double, complete: js.Function): JQueryUi = js.native
  def show(effect: String, options: js.Any, duration: String, complete: js.Function): JQueryUi = js.native
  def toggle(options: js.Any): JQueryUi = js.native
  def toggle(effect: String, options: js.Any, duration: Double, complete: js.Function): JQueryUi = js.native
  def toggle(effect: String, options: js.Any, duration: String, complete: js.Function): JQueryUi = js.native
  def position(options: JQueryPositionOptions): JQueryUi = js.native
  def enableSelection(): JQueryUi = js.native
  def disableSelection(): JQueryUi = js.native
  def focus(delay: Double, callback: js.Function = js.native): JQueryUi = js.native
  def uniqueId(): JQueryUi = js.native
  def removeUniqueId(): JQueryUi = js.native
  def scrollParent(): JQueryUi = js.native
  def zIndex(): Double = js.native
  def zIndex(zIndex: Double): JQueryUi = js.native
  var widget: Widget = js.native
  var jQuery: JQueryUiStatic = js.native
}
