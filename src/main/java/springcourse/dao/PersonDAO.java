package springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springcourse.model.Person;

import java.util.List;
import java.util.logging.Logger;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    private final Logger log = Logger.getLogger(PersonDAO.class.getName());

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        List<Person> people = jdbcTemplate.query("SELECT * FROM people;", (resultSet, i) -> {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            return person;
        });

        return people;
    }

    public Person getPersonById(int id) {
        List<Person> people = getPeople();
        return people.stream().filter(person -> id == person.getId()).findAny().orElse(null);
    }

    public void save(Person person) {
        List<Person> people = getPeople();

        int count = PersonOperations.calculateEmptyIndex(people);
        jdbcTemplate.update("INSERT INTO people VALUES(?,?);", count, person.getName());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM people WHERE id = ?;", id);
    }

    public void update(int id, Person updatedPerson) {
        String namePerson = updatedPerson.getName();
        jdbcTemplate.update("UPDATE people SET name= ? WHERE id = ?;", namePerson, id);
    }
}
