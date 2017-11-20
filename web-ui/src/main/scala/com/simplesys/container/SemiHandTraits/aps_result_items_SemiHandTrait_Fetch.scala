// This file is generated automatically (at 11.10.2017 10:12:14), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.aps

import java.time.LocalDateTime

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.{DSRequest, DSResponse, RPCResponse}
import com.simplesys.circe.Circe._
import com.simplesys.jdbc.control.DsRequest
import com.simplesys.jdbc.control.clob._
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.Json
import io.circe.Json._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{aps_changeover_code_operstype_From_type_NameStrong, aps_changeover_code_operstype_To_type_NameStrong}
import ru.simplesys.defs.bo.aps._

import scala.collection.mutable.ArrayBuffer
import scalaz.{Failure, Success}


trait aps_result_items_SemiHandTrait_Fetch extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequest(request.JSONData)

    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

    val dataSet = Result_itemsDS(oraclePool)
    val dataSetOpersType = Opers_typeDS(oraclePool)

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some({
        case GetData => {
            logger debug s"request: ${newLine + requestData.toPrettyString}"

            val data = requestData.data
            logger debug s"data: ${newLine + data.toPrettyString}"

            val qty: Int = {val res = requestData.endRow.getOrElse(0).toInt - requestData.startRow.getOrElse(0).toInt + 1; if (res < 0) 0 else res}

            val select = dataSet.Fetch(
                dsRequest = DsRequest(
                    sqlDialect = sessionContext.getSQLDialect,
                    startRow = requestData.startRow.getOrElse(0),
                    endRow = requestData.endRow.getOrElse(0),
                    sortBy = requestData.sortBy,
                    data = data,
                    textMatchStyle = requestData.textMatchStyle
                ))

            Out(out = select.result match {
                case Success(list) => {
                    val opersTypes: Seq[Opers_typeDSData] = dataSetOpersType.selectPList().result match {
                        case Success(list) ⇒ list
                        case Failure(_) ⇒ List()
                    }


                    val _data = ArrayBuffer.empty[Json]

                    list foreach {
                        case TupleSS24(
                        durationResult_items: Array[Double],
                        id_changeoverResult_items: Array[Long],
                        id_itemResult_items: Long,
                        id_ordersResult_items: Array[Long],
                        id_resultResult_items: Long,
                        id_taskResult_items: Array[Long],
                        idrcResult_items: Long,
                        opertimeendResult_items: Array[LocalDateTime],
                        opertimestartResult_items: Array[LocalDateTime],
                        posResult_items: Array[Long],
                        idchangeoverChangeover_Id_changeover: Array[Long],
                        durationChangeover_Id_changeover: Array[Double],
                        from_typeChangeover_Id_changeover: Array[Long],
                        id_taskChangeover_Id_changeover: Array[Long],
                        idrcChangeover_Id_changeover: Array[Long],
                        to_typeChangeover_Id_changeover: Array[Long],
                        idordersOrders_Id_orders: Array[Long],
                        code_ordersOrders_Id_orders: Array[String],
                        idresultResult_Id_result: Long,
                        scodeResult_Id_result: Array[String],
                        idtaskTasks_Id_task: Array[Long],
                        code_taskTasks_Id_task: String,
                        id_rcRc_Idrc: Long,
                        scode_rcRc_Idrc: String) =>
                            _data += JsonObject (
                                "id_item" -> id_itemResult_items,
                                "pos" -> posResult_items,
                                "opertimestart" -> opertimestartResult_items,
                                "opertimeend" -> opertimeendResult_items,
                                "duration" -> durationResult_items,
                                "id_result" -> id_resultResult_items,
                                "idrc" -> idrcResult_items,
                                "id_orders" -> id_ordersResult_items,
                                "id_task" -> id_taskResult_items,
                                "id_changeover" -> id_changeoverResult_items,
                                "scode_Id_result" -> scodeResult_Id_result,
                                "scode_rc_Idrc" -> scode_rcRc_Idrc,
                                "code_orders_Id_orders" -> (if (code_ordersOrders_Id_orders.length == 0) "Переналадка" else code_ordersOrders_Id_orders),
                                "code_task_Id_task" -> code_taskTasks_Id_task,
                                "idchangeover_Id_changeover" -> idchangeoverChangeover_Id_changeover,
                                "duration_Id_changeover" -> durationChangeover_Id_changeover,
                                "idrc_Id_changeover" -> idrcChangeover_Id_changeover,
                                "from_type_Id_changeover" -> from_typeChangeover_Id_changeover,
                                "to_type_Id_changeover" -> to_typeChangeover_Id_changeover,
                                "id_task_Id_changeover" -> id_taskChangeover_Id_changeover,
                                aps_changeover_code_operstype_From_type_NameStrong.name → opersTypes.filter(_.id_operstypeOpers_type.headOption == from_typeChangeover_Id_changeover.headOption).headOption.map(_.code_operstypeOpers_type),
                                aps_changeover_code_operstype_To_type_NameStrong.name → opersTypes.filter(_.id_operstypeOpers_type.headOption == to_typeChangeover_Id_changeover.headOption).headOption.map(_.code_operstypeOpers_type)
                            )
                        case x =>
                            new RuntimeException(s"mached as : $x")
                    }

                    logger debug s"_data: ${newLine + _data.toPrettyString}"

                    DSResponse {
                        status = RPCResponse.statusSuccess
                        data = _data
                        totalRows = requestData.StartRow.toInt + (if (qty == list.length) qty * 2 else list.length)
                    }
                }
                case Failure(_) =>
                    new DSResponseFailureEx(select)
            })

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    })

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
