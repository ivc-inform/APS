// This file is generated automatically (at 11.10.2017 10:12:14), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.aps

// import de.heikoseeberger.akkahttpcirce.CirceEnum._ Необходим для правильного отображения Enum типа case object from sealed trait; в общем случае это имеет вид {"name":{}}
import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging._
import com.simplesys.jdbc.control.clob._
import com.simplesys.servlet.isc.ServletActor
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.aps.Result_itemsDS
 
trait aps_result_items_SemiHandTrait_Add extends SessionContextSupport with ServletActor {
    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException ("Dont parsed Request JSON"))
    
    logger debug s"Request for Add: ${newLine + requestData.asJson.toPrettyString}"
    
    val dataSet = Result_itemsDS(oraclePool)    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    
     def receiveBase: Option[Actor.Receive] = None
    
     def wrapperBlobGetter(blob: Blob): String = blob.asString
}
