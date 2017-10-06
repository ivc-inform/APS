package com.simplesys.js.components.io.udash.wrappers.jqueryui

import scala.scalajs.js

@js.native
trait TabsEvents extends js.Object {
  var activate: TabsEvent[TabsActivationUIParams] = js.native
  var beforeActivate: TabsEvent[TabsActivationUIParams] = js.native
  var beforeLoad: TabsEvent[TabsBeforeLoadUIParams] = js.native
  var load: TabsEvent[TabsCreateOrLoadUIParams] = js.native
  var create: TabsEvent[TabsCreateOrLoadUIParams] = js.native
}
