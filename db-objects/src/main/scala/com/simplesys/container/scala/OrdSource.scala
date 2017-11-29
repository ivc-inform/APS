package com.simplesys.container.scala

import java.sql.Timestamp
import java.time.LocalDateTime

import com.simplesys.container.Helper
import com.simplesys.container.java.JOrdSource
import oracle.jdbc.OracleBlob
import oracle.sql.NUMBER

object OrdSource {

    implicit def JOrdSource2SOrdSource(ordSource: JOrdSource): OrdSource = new OrdSource {
        override val srcName: Option[String] = Option(Helper.asString(ordSource.srcName))
        override val srcLocation: Option[String] = Option(Helper.asString(ordSource.srcLocation))
        override val updateTime: Option[LocalDateTime] = Option(ordSource.updateTime).map(_.toLocalDateTime)
        override val local: Option[BigDecimal] = Option(ordSource.local).map(Helper.asBigDecimal(_))
        override val srcType: Option[String] = Option(Helper.asString(ordSource.srcType))
        override val localData: Option[OracleBlob] = Option(ordSource.localData)
    }

    implicit def SOrdSource2JOrdSource(ordSource: OrdSource): JOrdSource = {
        val res = new JOrdSource {}
        ordSource.srcName.foreach(srcName ⇒ res.srcName = Helper.asCHAR(srcName))
        ordSource.srcLocation.foreach(srcLocation => res.srcLocation = Helper.asCHAR(srcLocation))
        ordSource.updateTime.foreach(localDateTime ⇒ res.updateTime = Timestamp.valueOf(localDateTime))
        ordSource.local.foreach(bigDeecimal ⇒ res.local = new NUMBER(bigDeecimal.bigDecimal))
        ordSource.srcType.foreach(srcType ⇒ res.srcType = Helper.asCHAR(srcType))
        ordSource.localData.foreach(res.localData = _)
        res
    }
}

trait OrdSource {
    val localData: Option[OracleBlob]
    val srcType: Option[String]
    val srcLocation: Option[String]
    val srcName: Option[String]
    val updateTime: Option[LocalDateTime]
    val local: Option[BigDecimal]

    override def toString: String = {
        val res = new StringBuilder
        res append s"localData: $localData ,"
        res append s"srcType: $srcType ,"
        res append s"srcLocation: $srcLocation ,"
        res append s"srcName: $srcName, "
        res append s"updateTime: $updateTime ,"
        res append s"local: $local"
        res.toString()
    }
}
