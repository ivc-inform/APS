// This file is generated automatically (at 11.10.2017 10:12:14), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.aps

import com.simplesys.app.SessionContextSupport
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.common.Strings._
import com.simplesys.jdbc.control.clob._
import akka.actor.Actor
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.dataSource.RecordDyn
import com.simplesys.isc.grids.RecordsDynList
import com.simplesys.jdbc.control.DSRequest
import com.simplesys.servlet.GetData
import com.simplesys.tuple.{TupleSS10, TupleSS8}
import ru.simplesys.defs.bo.aps._

import scalaz.{Failure, Success}


trait aps_changeover_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

    val dataSet = ChangeoverDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = None

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
