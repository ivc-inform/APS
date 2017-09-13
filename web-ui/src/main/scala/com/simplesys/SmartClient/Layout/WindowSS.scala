package com.simplesys.SmartClient.Layout

import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait WindowSS extends Window {
    var headerIconPath: String
    var autoPosition: Boolean
    var autoDestroy: JSUndefined[Boolean]
}

@js.native
abstract trait AbstractWindowSSCompanion extends AbstractWindowCompanion {
}

