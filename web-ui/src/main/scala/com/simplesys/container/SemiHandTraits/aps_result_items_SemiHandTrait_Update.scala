// This file is generated automatically (at 11.10.2017 10:12:14), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.aps

// import de.heikoseeberger.akkahttpcirce.CirceEnum._ Необходим для правильного отображения Enum типа case object from sealed trait; в общем случае это имеет вид {"name":{}}
import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging._
import com.simplesys.jdbc.control.ValidationEx
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.jdbc.control.clob._
import com.simplesys.servlet.isc.{GetData, ServletActor}
import com.simplesys.jdbc.control.SessionStructures._
import io.circe.Json._
import io.circe.generic.auto._
import io.circe.syntax._
import io.circe.{Json, JsonObject}
import ru.simplesys.defs.bo.aps.{Result_items, Result_itemsDS}

import scala.collection.mutable.ArrayBuffer
import scalaz.{Failure, Success}

trait aps_result_items_SemiHandTrait_Update extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequest(request.JSONData)

    logger debug s"Request for Update: ${newLine + requestData.toPrettyString}"

    val dataSet = Result_itemsDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                logger debug s"request: ${newLine + requestData.toPrettyString}"

                val listResponse = ArrayBuffer.empty[Json]

                val update: ValidationEx[Array[Int]] = requestData.transaction
                  .getOrElse(Transaction())
                  .transactionNum match {
                    case None => {
                        val data = requestData.oldValues ++ requestData.request
                          .getJsonObject("data")

                        logger debug s"data: ${newLine + data.toPrettyString}"
                        val result_itemsData =
                            Result_items(
                                id_item = data.getLong("id_item"),
                                pos = data.getLongOpt("pos"),
                                opertimestart = data.getLocalDateTimeOpt("opertimestart"),
                                opertimeend = data.getLocalDateTimeOpt("opertimeend"),
                                duration = data.getDoubleOpt("duration"),
                                id_result = data.getLong("id_result"),
                                idrc = data.getLong("idrc"),
                                id_orders = data.getLongOpt("id_orders"),
                                id_task = data.getLongOpt("id_task"),
                                id_changeover = data.getLongOpt("id_changeover")
                            )

                        listResponse append DSResponse(
                            status = RPCResponse.statusSuccess,
                            data = fromJsonObject(
                                JsonObject.fromIterable(Seq(
                                    "id_item" -> result_itemsData.id_item,
                                    "pos" -> result_itemsData.pos,
                                    "opertimestart" -> result_itemsData.opertimestart,
                                    "opertimeend" -> result_itemsData.opertimeend,
                                    "duration" -> result_itemsData.duration,
                                    "id_result" -> result_itemsData.id_result,
                                    "idrc" -> result_itemsData.idrc,
                                    "id_orders" -> result_itemsData.id_orders,
                                    "id_task" -> result_itemsData.id_task,
                                    "id_changeover" -> result_itemsData.id_changeover,
                                    "scode_Id_result" -> data.getStringOpt("scode_Id_result"),
                                    "scode_rc_Idrc" -> data.getString("scode_rc_Idrc"),
                                    "code_orders_Id_orders" -> data.getStringOpt(
                                        "code_orders_Id_orders"),
                                    "code_task_Id_task" -> data.getString("code_task_Id_task"),
                                    "idchangeover_Id_changeover" -> data.getLongOpt(
                                        "idchangeover_Id_changeover"),
                                    "duration_Id_changeover" -> data.getDoubleOpt(
                                        "duration_Id_changeover"),
                                    "idrc_Id_changeover" -> data.getLongOpt(
                                        "idrc_Id_changeover"),
                                    "from_type_Id_changeover" -> data.getLongOpt(
                                        "from_type_Id_changeover"),
                                    "to_type_Id_changeover" -> data.getLongOpt(
                                        "to_type_Id_changeover"),
                                    "id_task_Id_changeover" -> data.getLongOpt(
                                        "id_task_Id_changeover")
                                )))
                        ).asJson

                        dataSet.updateP(
                            values = result_itemsData,
                            where = Where(
                                dataSet.id_itemResult_items === result_itemsData.id_item))
                    }
                    case _ =>
                        transaction(dataSet.dataSource) { connection =>
                            requestData.transaction
                              .getOrElse(Transaction())
                              .operations
                              .flatMap { operation => {
                                  val data = operation.getJsonObjectOpt("oldValues") ++ operation
                                    .getJsonObjectOpt("data")
                                  logger debug (s"data: ${newLine + data.toPrettyString}")

                                  val result_itemsData =
                                      Result_items(
                                          id_item = data.getLong("id_item"),
                                          pos = data.getLongOpt("pos"),
                                          opertimestart =
                                            data.getLocalDateTimeOpt("opertimestart"),
                                          opertimeend = data.getLocalDateTimeOpt("opertimeend"),
                                          duration = data.getDoubleOpt("duration"),
                                          id_result = data.getLong("id_result"),
                                          idrc = data.getLong("idrc"),
                                          id_orders = data.getLongOpt("id_orders"),
                                          id_task = data.getLongOpt("id_task"),
                                          id_changeover = data.getLongOpt("id_changeover")
                                      )

                                  listResponse append DSResponse(
                                      status = RPCResponse.statusSuccess,
                                      data = fromJsonObject(JsonObject.fromIterable(Seq(
                                          "id_item" -> result_itemsData.id_item,
                                          "pos" -> result_itemsData.pos,
                                          "opertimestart" -> result_itemsData.opertimestart,
                                          "opertimeend" -> result_itemsData.opertimeend,
                                          "duration" -> result_itemsData.duration,
                                          "id_result" -> result_itemsData.id_result,
                                          "idrc" -> result_itemsData.idrc,
                                          "id_orders" -> result_itemsData.id_orders,
                                          "id_task" -> result_itemsData.id_task,
                                          "id_changeover" -> result_itemsData.id_changeover,
                                          "scode_Id_result" -> data.getStringOpt(
                                              "scode_Id_result"),
                                          "scode_rc_Idrc" -> data.getString("scode_rc_Idrc"),
                                          "code_orders_Id_orders" -> data.getStringOpt(
                                              "code_orders_Id_orders"),
                                          "code_task_Id_task" -> data.getString(
                                              "code_task_Id_task"),
                                          "idchangeover_Id_changeover" -> data.getLongOpt(
                                              "idchangeover_Id_changeover"),
                                          "duration_Id_changeover" -> data.getDoubleOpt(
                                              "duration_Id_changeover"),
                                          "idrc_Id_changeover" -> data.getLongOpt(
                                              "idrc_Id_changeover"),
                                          "from_type_Id_changeover" -> data.getLongOpt(
                                              "from_type_Id_changeover"),
                                          "to_type_Id_changeover" -> data.getLongOpt(
                                              "to_type_Id_changeover"),
                                          "id_task_Id_changeover" -> data.getLongOpt(
                                              "id_task_Id_changeover")
                                      )))
                                  ).asJson
                                  dataSet.updatePWithoutCommit(
                                      connection = connection,
                                      values = result_itemsData,
                                      where = Where(
                                          dataSet.id_itemResult_items === result_itemsData.id_item))
                              }
                              }
                              .toArray
                        }
                }

                Out(out = update result match {
                    case Success(res) => {
                        res foreach (x => logger debug (s"Updated: $x line(s)."))

                        arr(listResponse: _*)
                    }
                    case Failure(_) =>
                        DSResponseFailureEx(update.printException.get.message,
                            update.printException.get.stackTrace).asJson
                })

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    )

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
