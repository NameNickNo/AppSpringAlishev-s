package springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springcourse.dao.PersonDAO;
import springcourse.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model)  {
        model.addAttribute("people", personDAO.getPeople());

        return "people/index";
    }

    @GetMapping("/{idNumber}")
    public String show(@PathVariable("idNumber") int id, Model model) {
        model.addAttribute("pers", personDAO.getPerson(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        System.out.println("People was creating with name - " + person.getName());
        return "redirect:/people";
    }

    @GetMapping("/delete/{id}")
    public String removePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

//    @PostMapping()
//    public String delete(@RequestParam("name") String name) {
//        System.out.println(name);
//        personDAO.delete(name);
//        return "redirect:/people";
//    }

}
