package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/*
* 트랜잭션 매니저 사용
* DataSourceUtils.getConnection()
* DataSourceUtils.releaseConnection()
* */
@Slf4j
public class MemberRepositoryV3 {

    private final DataSource dataSource;

    public MemberRepositoryV3(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id,money) values (?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("DB error",e);
            throw e;
        } finally {
            /*
            * Resource 정리를 위한 closing 메서드
            * 항상 역순으로! con -> pstmt -> pstmt -> con
            * */
            closing(con,pstmt,null);
        }
    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("Member not founded memberId = " + memberId);
            }
        } catch (SQLException e) {
            log.error("DB error",e);
            throw e;
        } finally {
            closing(con,pstmt,rs);
        }

    }

    public void update(String memberId, int money) throws SQLException {
        String sql = "update member set money=? where member_id=?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            int resultSize = pstmt.executeUpdate();//쿼리를 실행하고나서 영향을 받은 row 수을 반환
            log.info("resultSize = {}", resultSize);
        } catch (SQLException e) {
            log.error("DB error",e);
            throw e;
        } finally {
            closing(con, pstmt, null);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("DB error",e);
            throw e;
        } finally {
            closing(con, pstmt, null);
        }
    }

    private void closing(Connection con, Statement stmt, ResultSet resultSet) {
        JdbcUtils.closeResultSet(resultSet);
        JdbcUtils.closeStatement(stmt);
        //트랜잭션 동기화를 사용하려면 DataSourceUtils 사용해야함
        DataSourceUtils.releaseConnection(con,dataSource);
    }
    private Connection getConnection() throws SQLException {
        //앞서 Connection을 Parameter로 넘겨서 유지하는 방식 대신
        // -> 트랜잭션 동기화 사용하려면 DataSourceUtils 사용
        Connection connection = DataSourceUtils.getConnection(dataSource);
        log.info("get connection = {}, class = {}", connection, connection.getClass());
        return connection;
    }

}
