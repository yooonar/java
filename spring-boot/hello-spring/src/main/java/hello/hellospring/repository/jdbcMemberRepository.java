package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcMemberRepository implements MemberRepository {

    /*
        DB 접근을 위해서는 dataSource 가 필요함(dataSource 는 스프링에게 주입 받아야함)
        스프링 부트가 application.properties 에 저장한 h2 DB의 접속 정보를 통해 dataSource 를 미리 만들어 놓음
     */
    private final DataSource dataSource;

    // 생성자
    public jdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        // dataSource.getConnection()
    }

    @Override
    public Member save(Member member) {
        String sql = "INSERT INTO member(name) VALUES(?)";

        // h2 db 연결
        Connection conn = null;

        // 문장 작성
        PreparedStatement pstmt = null;

        // 결과값
        ResultSet rs = null;

        try {
            conn = getConnection();

            // Statement.RETURN_GENERATED_KEYS : id 값 읽어오기
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // 데이터 작성 sql 변수의 ? 와 매칭됨
            pstmt.setString(1, member.getName());

            // 실제 쿼리 적용
            pstmt.executeUpdate();

            // 결과값 id 값을 뱉음
            rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
        } catch(Exception e) {
            throw new IllegalStateException(e);
        } finally {
            // 접속 종료
            close(conn, pstmt, rs);
        }


        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() { return DataSourceUtils.getConnection(dataSource); }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
