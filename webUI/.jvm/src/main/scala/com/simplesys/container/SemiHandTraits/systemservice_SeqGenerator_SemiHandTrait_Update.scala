// This file is generated automatically (at 18.07.2017 13:44:03), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.systemservice

import com.simplesys.app.SessionContextSupport
import com.simplesys.servlet.isc.ServletActor
import com.simplesys.isc.dataBinging.DSRequest
import com.simplesys.common.Strings._
import com.simplesys.jdbc.control.clob._
import akka.actor.Actor
import ru.simplesys.defs.bo.systemservice._
import io.circe.generic.auto._
import io.circe.syntax._
import com.simplesys.circe.Circe._
 
trait systemservice_SeqGenerator_SemiHandTrait_Update extends SessionContextSupport with ServletActor {
    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException ("Dont parsed Request JSON"))
    
    logger debug s"Request for Update: ${newLine + requestData.asJson.toPrettyString}"
    
    val dataSet = SeqGeneratorDS(oraclePool)
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    
     def receiveBase: Option[Actor.Receive] = None    
    
     def wrapperBlobGetter(blob: Blob): String = {
         import com.simplesys.common.JVM.Strings._
         blob.asString
     }
}
