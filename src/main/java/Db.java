import java.sql.*;

public class Db {
    private Connection conn;

    public Db() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc", "root", "");

        } catch (SQLException se1) {
            se1.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void create() {
        executeQ(
                "CREATE TABLE BBS (\n" +
                        "  bbsTitle CHAR(30) NOT NULL,\n" +
                        "  bbsContent CHAR(255) NOT NULL,\n" +
                        "  bbsID int NOT NULL,\n" +
                        "  CONSTRAINT PLAYER_PK PRIMARY KEY (bbsID)\n" +
                        ");"
        );
    }

    public void insertSample(int idx, String title, String content) {
        System.out.println("update() called with: idx = [" + idx + "], title = [" + title + "], content3 = [" + content + "]");
        String sql = "insert into BBS(bbsTitle, bbsContent, bbsId) values (?,?,?)";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setInt(3, idx);

            int resultCode = preparedStatement.executeUpdate();
            System.out.println("insertSamples : sql=" + sql + " resultCode=" + resultCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update(String sql, int idx, String title, String content) {
        System.out.println("update() called with: idx = [" + idx + "], title = [" + title + "], content3 = [" + content + "]");

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setInt(3, idx);
            int resultCode = preparedStatement.executeUpdate();

            System.out.println("update : sql=" + sql + " resultCode=" + resultCode);
        } catch (SQLException e) {

        }
    }
    public void updateWithoutWhiteSpace(int idx, String title, String content) {
        System.out.println("updateWithoutWhiteSpace() called with: idx = [" + idx + "], title = [" + title + "], content3 = [" + content + "]");
        String sql = "UPDATE BBS SET bbsTitle=?, bbsContent=? WHERE bbsID=?";
        update(sql, idx, title, content);
    }

    public void updateWithWhiteSpace(int idx, String title, String content) {
        System.out.println("updateWithWhiteSpace() called with: idx = [" + idx + "], title = [" + title + "], content3 = [" + content + "]");
        String sql = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
        update(sql, idx, title, content);
    }

    public void retrieve() {
        String sql = "select * FROM BBS;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    String bbsTitle = resultSet.getString("bbsTitle");
                    String bbsContent = resultSet.getString("bbsContent");
                    String bbsId = resultSet.getString("bbsId");
                    System.out.println("bbsTitle: " + bbsTitle + " bbsContent " + bbsContent + " bbsId = " + bbsId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearSamples() {
        String sql = "DELETE FROM BBS";
        executeQ(sql);
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeQ(String sql) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql);
            int resultCode = preparedStatement.executeUpdate();
            System.out.println("executeQ : sql=" + sql + " resultCode=" + resultCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
