package springcourse;


import org.springframework.jdbc.core.JdbcTemplate;
import springcourse.dao.PersonDAO;
import springcourse.model.Person;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        int ide = 9;
        jdbcTemplate.update("DELETE FROM people WHERE id = ?", ide);
    }
}
