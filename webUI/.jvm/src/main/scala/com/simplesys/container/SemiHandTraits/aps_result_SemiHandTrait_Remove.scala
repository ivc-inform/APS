// This file is generated automatically (at 11.10.2017 10:12:14), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.aps

import com.simplesys.app.SessionContextSupport
import com.simplesys.servlet.isc.{GetData, ServletActor}
import com.simplesys.isc.dataBinging._
import com.simplesys.common.Strings._
import com.simplesys.jdbc.control.clob._
import akka.actor.Actor
import ru.simplesys.defs.bo.aps._
import io.circe.generic.auto._
import io.circe.syntax._
import com.simplesys.circe.Circe._
import com.simplesys.jdbc.control.SessionStructures.transaction
import com.simplesys.jdbc.control.ValidationEx
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.messages.Message
import io.circe.Json
import io.circe.Json.arr

import scala.collection.mutable.ArrayBuffer
import scalaz.{Failure, Success}

trait aps_result_SemiHandTrait_Remove extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    logger debug s"Request for Remove: ${newLine + requestData.asJson.toPrettyString}"

    val dataSet = ResultDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                import com.simplesys.messages.ActorConfig._

                var _transactionNum: Option[Int] = None

                logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"

                val listResponse = ArrayBuffer.empty[Json]
                val delete: ValidationEx[Array[Int]] = requestData.transaction
                  .getOrElse(Transaction())
                  .transactionNum match {
                    case None => {
                        val data = requestData.data.getOrElse(Json.Null)

                        logger debug (s"data: ${newLine + data.toPrettyString}")
                        val idresult = data.getLong("idresult")

                        listResponse append DSResponse(status = RPCResponse.statusSuccess, data = arr()).asJson
                        dataSet.delete(where = Where(dataSet.idresultResult === idresult))
                    }
                    case Some(transactionNum) =>
                        transaction(dataSet.dataSource) { connection =>
                            _transactionNum = Some(transactionNum)
                            requestData.transaction
                              .getOrElse(Transaction())
                              .operations
                              .flatMap {
                                  operation =>
                                      val data = operation.getJsonObjectOpt("data")
                                      logger debug (s"data: ${newLine + data.toPrettyString}")

                                      val idresult = data.getLong("idresult")

                                      listResponse append DSResponse(
                                          status = RPCResponse.statusSuccess,
                                          data = arr()).asJson
                                      
                                      SendMessage(Message(channels = s"ListElements_Remove_$transactionNum"))

                                      dataSet.deleteWithoutCommit(
                                          connection = connection,
                                          where = Where(dataSet.idresultResult === idresult))
                              }
                              .toArray
                        }
                }

                Out(out = delete result match {
                    case Success(res) => {
                        res foreach (x => logger debug (s"Deleted: $x line(s)."))

                        arr(listResponse: _*)
                    }
                    case Failure(_) =>
                        DSResponseFailureEx(ErrorData(delete.printException.get.message,delete.printException.get.stackTrace).asJson).asJson
                })

                if (_transactionNum.isDefined)
                    SendMessage(Message(channels = s"ListElements_EndRemove_${_transactionNum.get}"))

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    )

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
