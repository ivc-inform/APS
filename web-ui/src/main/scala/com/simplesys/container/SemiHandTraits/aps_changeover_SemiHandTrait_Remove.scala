// This file is generated automatically (at 15.10.2017 10:25:46), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.aps

import com.simplesys.app.SessionContextSupport
import com.simplesys.isc.system.ServletActor
import com.simplesys.isc.dataBinging.DSRequest
import com.simplesys.common.Strings._
import com.simplesys.jdbc.control.clob._
import akka.actor.Actor
import ru.simplesys.defs.bo.aps._

 
trait aps_changeover_SemiHandTrait_Remove extends SessionContextSupport with ServletActor {
    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    val requestData = new DSRequest(request)
    
    logger debug s"Request for Remove: ${newLine + requestData.toPrettyString}"    
    
    val dataSet = ChangeoverDS(oraclePool)    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    
     def receiveBase: Option[Actor.Receive] = None    
    
     def wrapperBlobGetter(blob: Blob): String = blob.asString
}
