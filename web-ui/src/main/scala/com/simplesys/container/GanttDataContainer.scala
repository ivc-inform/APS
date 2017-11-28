//package com.simplesys.container
//
//import com.simplesys.app.SessionContextSupport
//import com.simplesys.app.http.StartPage
//import com.simplesys.common.Strings.newLine
//import com.simplesys.isc.dataBinging.DSRequest
//import com.simplesys.servlet.ServletContext
//import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
//import com.simplesys.servlet.isc.{GetData, ServletActor}
//import io.circe.Json
//import io.circe.Json._
//import io.circe.{Json, JsonObject}
//import io.circe.generic.auto._
//import io.circe.syntax._
//import com.simplesys.circe.Circe._
//import ru.simplesys.defs.bo.aps.Result_itemsDS
//
//class GanttDataContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor with SessionContextSupport{
//
//    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))
//    val dataSet = Result_itemsDS(oraclePool)
//
//    def receive = {
//
//        case GetData => {
//            logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"
//
//            val data = requestData.data.getOrElse(Json.Null)
//            logger debug s"data: ${newLine + data.toPrettyString}"
//            Out()
//
//            selfStop()
//        }
//        case x =>
//            throw new RuntimeException(s"Bad branch $x")
//    }
//}
