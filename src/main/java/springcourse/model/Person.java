package springcourse.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max = 30,message = "Name should be between 2 and 30 chars")
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
