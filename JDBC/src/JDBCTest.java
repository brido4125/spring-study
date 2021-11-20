import java.sql.*;
import java.util.Scanner;

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

            Scanner sc = new Scanner(System.in);
            System.out.print("학번을 입력하세요 : ");
            int sid = sc.nextInt();
            System.out.print("학과 번호를 입력하세요 : ");
            int deptNo = sc.nextInt();

            PreparedStatement query1 = conn.prepareStatement("select * from student where sid = ?");
            query1.setInt(1, sid);
            ResultSet rset = query1.executeQuery();
            while (rset.next()) {
                System.out.println(
                                "학번 : " + rset.getInt("sid") + ", " +
                                        "이름 " + rset.getString("sname") + ", " +
                                        "학과번호: " + rset.getInt("deptno") + ", " +
                                        "지도교수번호: " + rset.getInt("advisor") + ", " +
                                        "성별: " + rset.getString("gen") + ", " +
                                        "주소: " + rset.getString("addr") + ", " +
                                        "생년월일: " + rset.getDate("birthdate") + ", " +
                                        "학점: " + rset.getFloat("grade") + "입니다."
                );
            }

            Statement stmt = conn.createStatement();
            rset = stmt.executeQuery("select deptno, avg(grade) from student group by deptno order by deptno");
            while (rset.next()) {
                System.out.println("학과번호 : " + rset.getInt("deptno") + ", " + "Average grade: " + rset.getFloat(2));
            }

            System.out.println("=============================================================================");

            PreparedStatement query2 = conn.prepareStatement("update student set grade = grade + 0.01 where deptno = ?");
            query2.setInt(1, deptNo);
            rset = query2.executeQuery();


            stmt = conn.createStatement();
            rset = stmt.executeQuery("select deptno, avg(grade) from student group by deptno order by deptno");
            while (rset.next()) {
                System.out.println("학과번호 : " + rset.getInt("deptno") + ", " + "Average grade: " + rset.getFloat(2));
            }

            System.out.println("=============================================================================");

            try {
                conn.rollback();
            } catch (SQLException ignored) {}

            stmt = conn.createStatement();
            rset = stmt.executeQuery("select deptno, sid, sname, grade from student order by deptno, sid");
            int i = 1;
            while (rset.next()) {
                System.out.println(
                                i+ " 번째 학생의 정보 -> " + "학과번호: " + rset.getInt("deptno") + ", " +
                                i+ " 번째 학생의 정보 -> " + "학번 : " + rset.getInt("sid") + ", " +
                                i+ " 번째 학생의 정보 -> " + "이름 " + rset.getString("sname") + ", " +
                                i+ " 번째 학생의 정보 -> " + "성적: " + rset.getFloat("grade") + "입니다."
                );
                i++;
            }
        } catch (SQLException sqle) {
            System.err.println("SQLException : " + sqle);
        }

    }

}
