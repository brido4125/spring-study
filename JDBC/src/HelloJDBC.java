import java.sql.*;

public class HelloJDBC {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException : " + e.getMessage());
        }

        try {

            //Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott", "tiger");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@database-1.cdh4acucb1ka.ap-northeast-2.rds.amazonaws.com:1521:orcl", "brido", "ckdtjq12");


            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select deptno, avg(grade) from student group by deptno order by deptno");
            while (rset.next()) {
                System.out.println("Department : " + rset.getInt("deptno") + ", " + "Average grade: " + rset.getFloat(2));
            }

            PreparedStatement query = conn.prepareStatement("select sid, sname, deptno from student where deptno = ?");
            query.setInt(1, 10);
            rset = query.executeQuery();
            while (rset.next()) {
                System.out.println("Student : " + rset.getInt("sid") + ", " + "Name: " + rset.getString("sname"));
            }
        } catch (SQLException sqle) {
            System.err.println("SQLException : " + sqle);
        }

    }

}
