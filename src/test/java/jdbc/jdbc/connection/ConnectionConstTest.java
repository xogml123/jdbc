package jdbc.jdbc.connection;

import static jdbc.jdbc.connection.ConnectionConst.*;
import static org.junit.jupiter.api.Assertions.*;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.sql.init.dependency.AbstractBeansOfTypeDatabaseInitializerDetector;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
class ConnectionConstTest {
    @Test
    public void driverManager() throws Exception{
    //given
        Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection connection2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        log.info("connection={}, class={}", connection1, connection1.getClass());
        log.info("connection={}, class={}", connection2, connection2.getClass());

        //when

    //then
    }
    @Test
    public void dataSourceDriverManager() throws Exception{
    //given
        DataSource driverManagerDataSource = new DriverManagerDataSource(URL, USERNAME,
            PASSWORD);
        useDataSource(driverManagerDataSource);

        //when

    //then
    }

    @Test
    public void dataSourceHikari() throws Exception{
    //given
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);
        Thread.sleep(1000);
        //when

    //then
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();

        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());


    }

}