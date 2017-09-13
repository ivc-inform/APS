package com.simplesys.container.java;

import com.simplesys.container.Helper;
import com.simplesys.container.scala.OrdSource;
import oracle.jdbc.OracleClob;
import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.internal.OracleStruct;
import oracle.sql.BLOB;
import oracle.sql.CHAR;
import oracle.sql.NUMBER;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;

public class JOrdDoc implements OracleData, OracleDataFactory {

  public JOrdSource source;
  public CHAR format;
  public CHAR mimeType;
  public NUMBER contentLength;
  public OracleClob comments;

  static final JOrdDoc _ordDoc = new JOrdDoc();

  @Override
  public String toString() {
    try {
      return "source: {" + source.toString() + "}, format: " + Helper.asString(format) + ", mimeType: " + Helper.asString(mimeType) + ", contentLength: " + Helper.asBigDecimal(contentLength).toString() + ", comments: " + Helper.clobToString(comments);
    } catch (SQLException e) {
      return e.getMessage();
    }

  }

  public JOrdSource getSource() {
    return this.source;
  }

  public static OracleDataFactory getOracleDataFactory() {
    return _ordDoc;
  }

  public JOrdDoc() {
  }

  public JOrdDoc(JOrdSource source,
                 CHAR format,
                 CHAR mimeType,
                 NUMBER contentLength,
                 OracleClob comments) {
    this.source = source;
    this.format = format;
    this.mimeType = mimeType;
    this.contentLength = contentLength;
    this.comments = comments;
  }


  @Override
  public Object toJDBCObject(Connection connection) throws SQLException {
    Object[] attributes = {source, format, mimeType, contentLength, comments};
    Struct struct = connection.createStruct("ORDDOC", attributes);
    return struct;
  }

  @Override
  public OracleData create(Object o, int i) throws SQLException {
    if (o == null) return null;

    Object[] attributes = ((OracleStruct) o).getOracleAttributes();
    Object[] attributesOrdSource = ((OracleStruct) attributes[0]).getAttributes();
    
    return new JOrdDoc(
            new JOrdSource(
                    (BLOB) attributesOrdSource[0],
                    Helper.asCHAR((String) attributesOrdSource[1]),
                    Helper.asCHAR((String) attributesOrdSource[2]),
                    Helper.asCHAR((String) attributesOrdSource[3]),
                    (Timestamp) attributesOrdSource[4],
                    (NUMBER) Helper.asNumber((BigDecimal) attributesOrdSource[5])
            ),
            (CHAR) attributes[1],
            (CHAR) attributes[2],
            (NUMBER) attributes[3],
            (OracleClob) attributes[4]
    );
  }
}
