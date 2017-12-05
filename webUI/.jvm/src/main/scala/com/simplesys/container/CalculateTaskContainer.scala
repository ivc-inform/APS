package com.simplesys.container

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings.newLine
import com.simplesys.isc.dataBinging.{DSResponse, DSResponseFailureEx, RPCResponse}
import com.simplesys.request.CalculateRequest
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.Json.arr
import io.circe.generic.auto._
import io.circe.syntax._


@RSTransfer(urlPattern = "/logic/calculateTask")
class CalculateTaskContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor with SessionContextSupport {
    val EOL = util.Properties.lineSeparator

    def receive = {

        case GetData => {
            val requestData = request.JSONData.as[CalculateRequest]

            requestData match {
                case Right(value) ⇒
                    logger debug s"request: ${newLine + value.asJson.toPrettyString}"

                    ru.simplesys.aps.dataprovider.Processor.runIt(value.idTask, 5)
                    Out(
                        DSResponse(
                            data = ,
                            status = RPCResponse.statusSuccess
                        )
                    )

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
