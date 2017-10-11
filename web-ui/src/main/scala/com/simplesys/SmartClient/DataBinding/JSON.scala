package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class}
import com.simplesys.System.{JSDictionary, JSObject}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait JSON extends Class {
}

@js.native
abstract trait AbstractJSONCompanion extends AbstractClassCompanion {

    import scala.scalajs.js.|

    def decode[T <: JSObject](jsonString: String): T = js.native
    def encode(obj : JSObject, settings: JSONEncoder = js.native): String = js.native
    def printObject(obj: JSObject): String = js.native
}

@js.native
@JSGlobal
object JSON extends AbstractJSONCompanion

