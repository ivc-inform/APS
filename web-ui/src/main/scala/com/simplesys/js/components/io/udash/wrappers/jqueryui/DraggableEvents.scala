package io.udash.wrappers.jqueryui

import io.udash.wrappers.jqueryui.DraggableEvents.DraggableEvent
import org.scalajs.dom.raw.Event

import scala.scalajs.js

object DraggableEvents {
    type DraggableEvent = js.ThisFunction2[_ <: js.Any, Event, DraggableEventUIParams, _]
    //(event: Event, ui: DraggableEventUIParams)
}

trait DraggableEvents extends js.Object {
    var create: js.UndefOr[DraggableEvent] = js.undefined
    var start: js.UndefOr[DraggableEvent] = js.undefined
    var drag: js.UndefOr[DraggableEvent] = js.undefined
    var stop: js.UndefOr[DraggableEvent] = js.undefined
}
