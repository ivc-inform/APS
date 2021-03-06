package com.simplesys.container

import com.simplesys.SQL.Gen.AscOrderBy
import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings.newLine
import com.simplesys.common.array._
import com.simplesys.gantt.GanttChartCommon._
import com.simplesys.gantt.TaskItemExt
import com.simplesys.gantt.TaskCssClass._
import com.simplesys.gantt.{Group, Opening}
import com.simplesys.isc.dataBinging.{DSResponse, ErrorData, RPCResponse}
import com.simplesys.isc.dataBinging.DSResponse._
import com.simplesys.jdbc.control.classBO.{OrderBy, Where}
import com.simplesys.request.RequestResult
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.Json._
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.aps.{OrdersDS, ResultDS, Result_itemsDS}
//import com.simplesys.gantt.TaskItemExt._ //!! Must be
import com.simplesys.gantt.TaskItemExt._ //!! Must be
import scalaz.{Failure, Success}

@RSTransfer(urlPattern = "/logic/getGanttData")
class GanttDataContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor with SessionContextSupport {

    val dataSetResultItems = Result_itemsDS(oraclePool)
    val dataSetResult = ResultDS(oraclePool)
    val dataSetOrders = OrdersDS(oraclePool)

    val EOL = util.Properties.lineSeparator

    def getOperation(idOrder: Array[Long]): String = {
        idOrder.headOption match {
            case None ⇒ "Переналадка"
            case Some(idOrder) ⇒
                dataSetOrders.selectPOne(where = Where(dataSetOrders.idordersOrders === idOrder)).result match {
                    case Success(item) ⇒
                        s"Операция: '${item.code_operstypeOpers_type_Oper_type}'"

                    case Failure(_) ⇒
                        "Тип операции не определен (("
                }
        }
    }

    def receive = {

        case GetData => {
            val requestData = request.JSONData.as[RequestResult]

            requestData match {
                case Right(value) ⇒
                    logger debug s"request: ${newLine + value.asJson.toPrettyString}"

                    dataSetResult.selectPOne(where = Where(dataSetResult.idresultResult === value.idResult)).result match {
                        case Success(result) ⇒

                            var prevID: Long = 0

                            dataSetResultItems.selectPList(where = Where(dataSetResultItems.id_resultResult_items === value.idResult), orderBy = OrderBy(dataSetResultItems.posResult_items, AscOrderBy)).result match {
                                case Success(resultItems) ⇒
                                    val tasks = resultItems.map {
                                        resultItem ⇒
                                            val _pStart = resultItem.opertimestartResult_items.map(item ⇒ com.simplesys.gantt.time.Time.localDateTime2Str(item)).getOrElse("")
                                            val _pEnd = resultItem.opertimeendResult_items.map(item ⇒ com.simplesys.gantt.time.Time.localDateTime2Str(item)).getOrElse("")

                                            /*println("///////////////////////////////////////////////////////////////////////////////////////////////////")
                                            println(s"_pStart : ${_pStart}, _pEnd: ${_pEnd}")
                                            println("///////////////////////////////////////////////////////////////////////////////////////////////////")*/

                                            val res = TaskItemExt(
                                                pID = resultItem.id_itemResult_items,
                                                pStart = resultItem.opertimestartResult_items.map(item ⇒ com.simplesys.gantt.time.Time.localDateTime2Str(item)).getOrElse(""),
                                                pEnd = resultItem.opertimeendResult_items.map(item ⇒ com.simplesys.gantt.time.Time.localDateTime2Str(item)).getOrElse(""),
                                                pName = getOperation(resultItem.id_ordersResult_items),
                                                pRes = Some(result.scodeResult),
                                                pComp = 0,
                                                pOpen = Opening.open,
                                                pClass = if (resultItem.idchangeoverChangeover_Id_changeover.isEmpty) gtaskblue else gtaskyellow,
                                                pDepend = if (prevID == 0) None else Some(Seq(prevID.FS))
                                            )

                                            prevID = res.pID
                                            res
                                    }

                                    val parentID: Long = {
                                        val ids = tasks.map(_.pID)
                                        if (ids.length == 0)
                                            1
                                        else
                                            ids.max + 1
                                    }

                                    val taskGroupItem = TaskItemExt(
                                        pID = parentID,
                                        pName = result.scodeResult,
                                        pRes = Some(result.scodeResult),
                                        pClass = ggroupblack,
                                        pGroup = Some(Group.standardGroupTask)
                                    )

                                    Out(
                                        DSResponse(
                                            data = arr(Seq(taskGroupItem.asJson) ++ tasks.map(item ⇒ item.copy(pParent = parentID).asJson): _*),
                                            status = RPCResponse.statusSuccess
                                        )
                                    )
                                case
                                    Failure(failure) ⇒
                                    Out(
                                        DSResponseFailureEx(
                                            failure.getMessage,
                                            failure.getStackTrace.mkString("", EOL, EOL)).asJson
                                    )
                            }

                        case Failure(failure) ⇒
                            Out(
                                DSResponseFailureEx(
                                    failure.getMessage,
                                    failure.getStackTrace.mkString("", EOL, EOL)).asJson
                            )
                    }
                case Left(failure) ⇒
                    Out(DSResponseFailureEx(
                        failure.message,
                        failure.getStackTrace.mkString("", EOL, EOL)).asJson
                    )
            }

            selfStop
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
