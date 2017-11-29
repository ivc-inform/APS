package test;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

public class Test {
  void insertBlobViaSelectForUpdate(final Connection conn, final String tableName, final int id, final byte value[]) throws SQLException, IOException {
    try (final PreparedStatement pstmt = conn.prepareStatement(String.format("INSERT INTO %s (ID, VALUE) VALUES (?, EMPTY_BLOB())", tableName))) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    }

    try (final PreparedStatement pstmt = conn.prepareStatement(String.format("SELECT VALUE FROM %s WHERE ID = ? FOR UPDATE", tableName))) {

      pstmt.setInt(1, id);
      try (final ResultSet rset = pstmt.executeQuery()) {
        while (rset.next()) {
          final Blob blob = rset.getBlob(1);
          try (final OutputStream out = new BufferedOutputStream(blob.setBinaryStream(1L))) {
            out.write(value);
          }
        }
      }
    }
  }
}
