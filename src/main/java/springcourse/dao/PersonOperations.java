package springcourse.dao;

import springcourse.model.Person;

import java.util.List;

public class PersonOperations {
    public static int calculateEmptyIndex(List<Person> people) {
        int count = 1;
        for (int i = 1; i < 1001; i++) {
            try {
                people.get(i - 1);
            } catch (IndexOutOfBoundsException e) {
                count = i;
                break;
            }
        }
        return count;
    }
}
