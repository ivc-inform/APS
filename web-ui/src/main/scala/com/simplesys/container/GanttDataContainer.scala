package com.simplesys.container

import com.simplesys.SQL.Gen.AscOrderBy
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings.newLine
import com.simplesys.gantt.Group
import com.simplesys.gantt.JVM.TaskItemExt
import com.simplesys.isc.dataBinging.{DSRequest, DSResponse, DSResponseFailureEx, RPCResponse}
import com.simplesys.jdbc.control.classBO.{OrderBy, Where}
import com.simplesys.request.RequestResult
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.Json
import io.circe.Json._
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.aps.{ResultDS, Result_itemsDS}
import com.simplesys.gantt.TaskCssClass.ggroupblack

import scalaz.{Failure, Success}

class GanttDataContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor with SessionContextSupport {

    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))
    val dataSetResultItems = Result_itemsDS(oraclePool)
    val dataSetResult = ResultDS(oraclePool)
    val EOL = util.Properties.lineSeparator

    def receive = {

        case GetData => {
            logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"

            val data = requestData.data.getOrElse(Json.Null)
            logger debug s"data: ${newLine + data.toPrettyString}"
            val resultRequest = data.as[RequestResult] match {
                case Right(value) ⇒
                    dataSetResult.selectPOne(where = Where(dataSetResult.idresultResult === value.idResult)).result match {
                        case Success(result) ⇒
                            val taskGroupItem = TaskItemExt(
                                pID = result.idresultResult,
                                pName = result.scodeResult,
                                pClass = ggroupblack,
                                pGroup = Some(Group.standardGroupTask)
                            )
                            dataSetResultItems.selectPList(where = Where(dataSetResultItems.id_resultResult_items === value.idResult), orderBy = OrderBy(dataSetResultItems.posResult_items, AscOrderBy)).result match {
                                case Success(resultItems) ⇒
                                    Out(
                                        DSResponse(
                                            data = arr(
                                                TaskItemExt(
                                                    pID = result.idresultResult,
                                                    pName = "",
                                                    pClass = ggroupblack
                                                ).asJson
                                            ),
                                            status = RPCResponse.statusSuccess.hashCode()
                                        )
                                    )
                                case
                                    Failure(failure) ⇒
                                    Out(
                                        DSResponseFailureEx(
                                            failure.getMessage,
                                            failure.getStackTrace().mkString("", EOL, EOL)).asJson
                                    )
                            }

                        case Failure(failure) ⇒
                            Out(
                                DSResponseFailureEx(
                                    failure.getMessage,
                                    failure.getStackTrace().mkString("", EOL, EOL)).asJson
                            )
                    }
                case Left(failure) ⇒
                    Out(DSResponseFailureEx(
                        failure.message,
                        failure.getStackTrace().mkString("", EOL, EOL)).asJson
                    )
            }

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
