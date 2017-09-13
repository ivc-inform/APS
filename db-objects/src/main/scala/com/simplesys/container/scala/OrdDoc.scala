package com.simplesys.container.scala

import com.simplesys.container.Helper
import com.simplesys.container.java.JOrdDoc
import oracle.jdbc.{OracleClob, OracleConnection}
import oracle.sql.{CLOB, NUMBER}

object OrdDoc {
    implicit def JOrdSource2SOrdSource(ordDoc: JOrdDoc): OrdDoc = new OrdDoc {
        override val source: Option[OrdSource] = Option(ordDoc.source).map(OrdSource.JOrdSource2SOrdSource(_))
        override val format: Option[String] = Option(ordDoc.format).map(Helper.asString(_))
        override val mimeType: Option[String] = Option(ordDoc.mimeType).map(Helper.asString(_))
        override val contentLength: Option[BigDecimal] = Option(ordDoc.contentLength).map(Helper.asBigDecimal(_))
        override val comments: Option[String] = Option(Helper.clobToString(ordDoc.comments))
    }

    implicit def SOrdSource2JOrdSource(ordDoc: OrdDoc)(implicit connection: OracleConnection): JOrdDoc = {
        val res = new JOrdDoc
        ordDoc.source.foreach (res.source = _)
        ordDoc.format.foreach(format ⇒ res.format = Helper.asCHAR(format))
        ordDoc.mimeType.foreach(mimeType ⇒ res.mimeType = Helper.asCHAR(mimeType))
        ordDoc.contentLength.foreach(contentLength ⇒ res.contentLength = new NUMBER(contentLength.bigDecimal))
        ordDoc.comments.foreach(comment ⇒ res.comments = {
            val clob = connection.createClob().asInstanceOf[OracleClob]
            clob.setString(1, comment)
            clob
        })
        res
    }
}

trait OrdDoc {
    val source: Option[OrdSource]
    val format: Option[String]
    val mimeType: Option[String]
    val contentLength: Option[BigDecimal]
    val comments: Option[String]
}
