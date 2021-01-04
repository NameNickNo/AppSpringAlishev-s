package springcourse.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springcourse.model.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PersonOperationsTest {

    @Test
    void shouldSaveTest() {
        List<Person> people = List.of(
                new Person(1, "Nicki"),
                new Person(3, "Git"),
                new Person(4, "Tim")
        );

        int checkingCount = PersonOperations.calculateEmptyIndex(people);
        assertEquals(2,checkingCount);

    }
}