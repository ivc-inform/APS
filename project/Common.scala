import io.circe.syntax._
import io.circe._

import scala.collection.mutable.ArrayBuffer

case class Info(libName: String, libVersion: String) {
    def toJson = this.asJson
    override def toString: String = toJson.spaces2
}

object Common {
    val list = ArrayBuffer.empty[Info]
    override def toString: String = list.asJson.spaces2
}
