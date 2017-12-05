package com.simplesys.container

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.request.{CalculateRequest, RequestResult}
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.generic.auto._
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.common.array._
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
import com.simplesys.isc.dataBinging.{DSResponse, DSResponseFailureEx, RPCResponse}
import com.simplesys.jdbc.control.classBO.{OrderBy, Where}
import com.simplesys.request.RequestResult
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.Json._
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.aps.{OrdersDS, ResultDS, Result_itemsDS}


@RSTransfer(urlPattern = "/logic/calculateTask")
class CalculateTaskContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor with SessionContextSupport {
    val EOL = util.Properties.lineSeparator

    def receive = {

        case GetData => {
            val requestData = request.JSONData.as[CalculateRequest]

            requestData match {
                case Right(value) ⇒
                    logger debug s"request: ${newLine + value.asJson.toPrettyString}"

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
