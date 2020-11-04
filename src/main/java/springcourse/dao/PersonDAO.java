package springcourse.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;
import springcourse.model.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Component
public class PersonDAO {
    private static JdbcTemplate jdbcTemplate;
    private static String URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;
    private static String SQLGET = null;
    private static String SQLCREATE = null;

    static {
        resourceGet();
        dataBaseConnection();
    }


    public List<Person> getPeople() {
        List<Person> people = jdbcTemplate.query(SQLGET, (resultSet, i) -> {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            return person;
        });

        return people;
    }

    public Person getPerson(int id) {
        List<Person> people = getPeople();
        return people.stream().filter(person -> id == person.getId()).findAny().orElse(null);
    }


    public void save(Person person) {
        List<Person> people = getPeople();
        int count = 1;
        for (int i = 1; i < 1001; i++) {
            if (people.get(i - 1).getId() != i) {
                count = i;
                break;
            }
        }
        jdbcTemplate.update(SQLCREATE, count, person.getName());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM people WHERE id = ?", id);
    }


    public static void resourceGet() {
        try (InputStream in = PersonDAO.class.getClassLoader()
                .getResourceAsStream("feature.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            URL = properties.getProperty("Url");
            USERNAME = properties.getProperty("UserName");
            PASSWORD = properties.getProperty("Password");
            SQLGET = properties.getProperty("SqlGet");
            SQLCREATE = properties.getProperty("SqlCreate");

            System.out.println("//Data received//");
        } catch (IOException e) {
            System.out.println("/////////No data received//////////////");
        }
    }

    public static void dataBaseConnection() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("Connection with DataBase");
    }

}
