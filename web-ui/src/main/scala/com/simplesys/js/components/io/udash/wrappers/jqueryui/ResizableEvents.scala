package io.udash.wrappers.jqueryui

import io.udash.wrappers.jqueryui.ResizableEvents.ResizableEvent
import org.scalajs.dom.raw.Event

import scala.scalajs.js

object ResizableEvents {
    type ResizableEvent = js.ThisFunction2[_ <: js.Any, Event, ResizableUIParams, _]
    //event: Event, ui: ResizableUIParams
}

trait ResizableEvents extends js.Object {
    val resize: js.UndefOr[ResizableEvent] = js.undefined
    val start: js.UndefOr[ResizableEvent] = js.undefined
    val stop: js.UndefOr[ResizableEvent] = js.undefined
    val create: js.UndefOr[ResizableEvent] = js.undefined
}
