package com.simplesys.SmartClient.Forms

import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait DynamicFormSS extends DynamicForm {
    def addField(field: FormItem, pos: Int): void
    def setPropertyOnSelection[T](property: String, value: JSUndefined[T])
}

@js.native
@JSGlobal
abstract class AbstractDynamicFormSSCompanion extends AbstractDynamicFormCompanion

