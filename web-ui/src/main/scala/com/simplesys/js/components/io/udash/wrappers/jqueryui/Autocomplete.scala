package com.simplesys.js.components.io.udash.wrappers.jqueryui

import scala.scalajs.js

@js.native
trait Autocomplete extends Widget with AutocompleteOptions {
  var escapeRegex: js.Function1[String, String] = js.native
  var filter: js.Function2[js.Any, String, Any] = js.native
}
