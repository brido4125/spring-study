package hello.jdbc.repository;

import hello.jdbc.donmain.Member;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface MemberRepositoryEx {
    Member save(Member member) throws SQLException;

    Member findById(String memberId) throws SQLException;

    void update(String memberId, int money) throws SQLException;

    void delete(String memberId) throws SQLException;

}
