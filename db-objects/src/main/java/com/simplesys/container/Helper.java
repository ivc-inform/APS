package com.simplesys.container;

import net.sf.saxon.serialize.charcode.CharacterSetFactory;
import oracle.jdbc.OracleClob;
import oracle.sql.CHAR;
import oracle.sql.CharacterSet;
import oracle.sql.NUMBER;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import static oracle.sql.CharacterSet.CL8MSWIN1251_CHARSET;

public class Helper {
  public static String clobToString(OracleClob data) {
    if (data == null)
      return null;
    else {
      StringBuilder sb = new StringBuilder();
      try {
        BufferedReader br = new BufferedReader(data.getCharacterStream());

        String line;
        while (null != (line = br.readLine())) {
          sb.append(line);
        }
        br.close();
      } catch (SQLException e) {
        // handle this exception
      } catch (IOException e) {
        // handle this exception
      }
      String res = sb.toString();
      if (res.isEmpty())
        return null;
      else
        return sb.toString();
    }
  }

  public static String asString(CHAR ch) throws SQLException {
    if (ch == null)
      return null;
    else
      return ch.getString();
  }

  public static CHAR asCHAR(String str) throws SQLException {
    if (str == null)
      return null;
    else
      return new CHAR(str, CharacterSet.make(CL8MSWIN1251_CHARSET));
  }

  public static BigDecimal asBigDecimal(NUMBER n) throws SQLException {
    if (n == null)
      return null;
    else
      return NUMBER.toBigDecimal(n.toBytes());
  }

  public static NUMBER asNumber(BigDecimal bigDecimal) throws SQLException {
    if (bigDecimal == null)
      return null;
    else
      return new NUMBER(bigDecimal);
  }
}
