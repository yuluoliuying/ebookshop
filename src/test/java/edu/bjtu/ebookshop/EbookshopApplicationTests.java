package edu.bjtu.ebookshop;

import edu.bjtu.ebookshop.controller.showController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
class EbookshopApplicationTests {
    @Autowired
//    DataSource dataSource;
    private showController show;

    @Test
    void contextLoads() throws SQLException {
//        System.out.println(dataSource.getClass());
//        System.out.println(dataSource.getConnection());
        String s=show.demo();
        System.out.println(s);
    }

}
