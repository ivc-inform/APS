package test

import com.simplesys.container.scala.GetAttFile
import com.simplesys.oracle.pool.OraclePoolDataSource
import oracle.jdbc.OracleConnection

object TestApp2 {
    def main(args: Array[String]): Unit = {

        val bufferSize = 1024 * 1024 * 100

        implicit val oracelePool = new OraclePoolDataSource("db-connection-stack.docker.oraclcePoolDataSource")
        val connection: OracleConnection = oracelePool.getConnection()

        // Create Oracle DatabaseMetaData object
        val meta = connection.getMetaData

        // gets driver info:
        println(s"JDBC driver version is ${meta.getDriverVersion}")
        println(s"sqlDialect is ${oracelePool.sqlDialect}")

        val ordDoc = GetAttFile.getOrdDocs((157 to 157).map(BigDecimal(_)): _*)

        ordDoc.foreach(ordDoc ⇒ println(s"ordDoc: {source: ${ordDoc.source}, format: ${ordDoc.format}, mimeType: ${ordDoc.mimeType}, contentLength: ${ordDoc.contentLength}, comments: ${ordDoc.comments}"))
    }
}
