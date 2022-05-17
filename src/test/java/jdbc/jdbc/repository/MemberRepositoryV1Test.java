package jdbc.jdbc.repository;

import static jdbc.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.zaxxer.hikari.HikariDataSource;
import java.util.NoSuchElementException;
import jdbc.jdbc.connection.ConnectionConst;
import jdbc.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
//
//        repository= new MemberRepositoryV1(dataSource);
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(URL);
        hikariDataSource.setUsername(USERNAME);
        hikariDataSource.setPassword(PASSWORD);

        repository = new MemberRepositoryV1(hikariDataSource);
    }
    @Test
    public void create() throws Exception {

        //given
        Member member = new Member("memberV8", 10000);
        //when
        repository.save(member);
        //then
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        assertThat(findMember).isEqualTo(member);

        repository.update(member.getMemberId(), 20000);
        assertThat(repository.findById(member.getMemberId()).getMoney()).isEqualTo(20000);
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId())).isInstanceOf(
            NoSuchElementException.class);
        Thread.sleep(1000);
    }
}