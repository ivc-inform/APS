package com.simplesys.container.scala

import java.sql.Connection

import com.simplesys.container.java.JOrdDoc
import com.simplesys.db.pool.PoolDataSource
import com.simplesys.jdbc.control.SessionStructures.prepareStatement
import com.simplesys.oracle.pool.OraclePoolDataSource
import oracle.jdbc.{OracleConnection, OracleResultSet}

object GetAttFile {
    def getOrdDoc(id: BigDecimal)(implicit oraclePool: PoolDataSource): Option[OrdDoc] = {

        val selectSQL = "select ATTFILE from ARX_ATTATCH where ID = ?"

        prepareStatement(oraclePool.getConnection(), selectSQL) {
            preparedStatement ⇒
                preparedStatement.setBigDecimal(1, id.bigDecimal)

                val ors = preparedStatement.executeQuery().asInstanceOf[OracleResultSet]

                if (ors.next())
                    Option(ors.getObject(1, JOrdDoc.getOracleDataFactory()).asInstanceOf[JOrdDoc]).map(ordJDoc ⇒ ordJDoc: OrdDoc)
                else
                    None
        }
    }

    def getOrdDocs(ids: BigDecimal*)(implicit oraclePool: OraclePoolDataSource): Seq[OrdDoc] = ids.map(getOrdDoc).filter(_.nonEmpty).map(_.get)
}
