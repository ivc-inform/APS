import io.circe._
import io.circe.syntax._
import io.circe.generic.semiauto._


import scala.collection.mutable.ArrayBuffer

object Info{
    implicit val infoDecoder: Decoder[Info] = deriveDecoder[Info]
    implicit val infoEncoder: Encoder[Info] = deriveEncoder[Info]
}

case class Info(libName: String, libVersion: String) {
    def toJson = this.asJson
    override def toString: String = toJson.spaces2
}

object Common {
    val list = ArrayBuffer.empty[Info]
    override def toString: String = list.asJson.spaces2
}
