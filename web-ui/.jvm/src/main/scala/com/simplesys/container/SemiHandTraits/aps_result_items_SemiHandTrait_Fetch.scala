// This file is generated automatically (at 11.10.2017 10:12:14), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.aps

import java.time.LocalDateTime

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging._
import com.simplesys.isc.dataBinging.DSResponse._
import com.simplesys.jdbc.control.DsRequest
import com.simplesys.jdbc.control.clob._
import com.simplesys.servlet.isc.{GetData, ServletActor}
import com.simplesys.tuple.{TupleSS21, TupleSS22, TupleSS24}
import io.circe.Json._
import io.circe.{Json, JsonObject}
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{aps_changeover_code_operstype_From_type_NameStrong, aps_changeover_code_operstype_To_type_NameStrong}
import ru.simplesys.defs.bo.aps._

import scala.collection.mutable.ArrayBuffer
import scalaz.{Failure, Success}
//import de.heikoseeberger.akkahttpcirce.CirceEnum._ Необходим для правильного отображения Enum типа case object from sealed trait; в общем случае это имеет вид {"name":{}}


trait aps_result_items_SemiHandTrait_Fetch extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    logger debug s"Request for Fetch: ${newLine + requestData.asJson.toPrettyString}"

    val dataSet = Result_itemsDS(oraclePool)
    val dataSetOpersType = Opers_typeDS(oraclePool)

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some({
        case GetData => {
            logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"

            val data = requestData.data.getOrElse(Json.Null)
            logger debug s"data: ${newLine + data.toPrettyString}"

            val qty: Int = {
                val res = requestData.endRow.getOrElse(0) - requestData.startRow.getOrElse(0) + 1;
                if (res < 0) 0 else res
            }

            val select = dataSet.Fetch(
                dsRequest = new DsRequest(
                    sqlDialect = sessionContext.getSQLDialect,
                    startRow = requestData.startRow.getOrElse(0),
                    endRow = requestData.endRow.getOrElse(0),
                    sortBy = requestData.sortBy.getOrElse(Vector.empty),
                    data = data,
                    textMatchStyle = requestData.textMatchStyle.getOrElse("exact")
                ))

            val out = select.result match {
                case Success(list) => {
                    val opersTypes: Seq[Opers_typeDSData] = dataSetOpersType.selectPList().result match {
                        case Success(list) ⇒ list
                        case Failure(_) ⇒ List()
                    }


                    val _data = ArrayBuffer.empty[Json]

                    list foreach {
                        case TupleSS21(
                        durationResult_items: Array[Double],
                        id_changeoverResult_items: Array[Long],
                        id_itemResult_items: Long,
                        id_ordersResult_items: Array[Long],
                        id_resultResult_items: Long,
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
                        scodeResult_Id_result: String,
                        id_rcRc_Idrc: Long,
                        scode_rcRc_Idrc: String) =>
                            _data += fromJsonObject(
                                JsonObject.fromIterable(Seq(
                                    "id_item" -> id_itemResult_items,
                                    "pos" -> posResult_items,
                                    "opertimestart" -> opertimestartResult_items,
                                    "opertimeend" -> opertimeendResult_items,
                                    "duration" -> durationResult_items,
                                    "id_result" -> id_resultResult_items,
                                    "idrc" -> idrcResult_items,
                                    "id_orders" -> id_ordersResult_items,
                                    "id_changeover" -> id_changeoverResult_items,
                                    "scode_Id_result" -> scodeResult_Id_result,
                                    "scode_rc_Idrc" -> scode_rcRc_Idrc,
                                    "code_orders_Id_orders" -> code_ordersOrders_Id_orders,
                                    "idchangeover_Id_changeover" -> idchangeoverChangeover_Id_changeover,
                                    "duration_Id_changeover" -> durationChangeover_Id_changeover,
                                    "idrc_Id_changeover" -> idrcChangeover_Id_changeover,
                                    "from_type_Id_changeover" -> from_typeChangeover_Id_changeover,
                                    "to_type_Id_changeover" -> to_typeChangeover_Id_changeover,
                                    "id_task_Id_changeover" -> id_taskChangeover_Id_changeover,
                                    aps_changeover_code_operstype_From_type_NameStrong.name → opersTypes.filter(_.id_operstypeOpers_type.headOption == from_typeChangeover_Id_changeover.headOption).headOption.map(_.code_operstypeOpers_type),
                                    aps_changeover_code_operstype_To_type_NameStrong.name → opersTypes.filter(_.id_operstypeOpers_type.headOption == to_typeChangeover_Id_changeover.headOption).headOption.map(_.code_operstypeOpers_type)
                                )))

                        case x =>
                            new RuntimeException(s"mached as : $x")
                    }

                    _data.foreach(x ⇒ logger debug s"_data: ${newLine + x.toPrettyString}")

                    DSResponse(
                        data = arr(_data: _*),
                        status = RPCResponse.statusSuccess,
                        totalRows = Some(requestData.startRow.getOrElse(0) + (if (qty == list.length) qty * 2 else list.length))
                    )
                }
                case Failure(_) =>
                    DSResponseFailureEx(select.printException.get.message, select.printException.get.stackTrace)
            }
            Out(out = out)

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    })

    def wrapperBlobGetter(blob: Blob): String = inputStream2Sting(blob)
}
