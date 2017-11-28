package com.simplesys.container

import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings.newLine
import com.simplesys.isc.dataBinging.{DSRequest, DSResponseFailureEx}
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.request.RequestResult
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.Json
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.aps.{ResultDS, Result_itemsDS}

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
                            dataSetResultItems.selectPList(where = Where(dataSetResultItems.id_resultResult_items === value.idResult)).result match {
                                case Success(resultItems) ⇒
                                    
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
