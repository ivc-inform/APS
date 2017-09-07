import io.circe._
//import io.circe.generic.semiauto._
//import io.circe.generic.auto._
import io.circe.syntax._

/*object Info {
    implicit val infoDecoder: Decoder[Info] = deriveDecoder
    implicit val infoEncoder: Encoder[Info] = deriveEncoder
}*/

case class Info(libName: String, libVersion: String) {
    import Common._
    
    def toJson = this.asJson
    override def toString: String = toJson.spaces2
}

object Common {
    var list = List.empty[Info]

    implicit val encodeListInfo: Encoder[List[Info]] = new Encoder[List[Info]] {
        final def apply(a: List[Info]): Json = Json.arr(a.map(item ⇒ item.asJson): _*)
    }

    implicit val encodeInfo: Encoder[Info] = new Encoder[Info] {
        final def apply(a: Info): Json = Json.obj(
            ("libName", Json.fromString(a.libName)),
            ("libVersion", Json.fromString(a.libVersion))
        )
    }

    def spaces2: String = list.asJson.spaces2
}
