package coms309.Person;

public class Person {
   // @RestController
   // @RequestMapping("/api/people")
    public class PeopleController {

        private final Map<String, Person> peopleList = new HashMap<>();

        @PostMapping
        public @ResponseBody String createPerson(@RequestBody Person person) {
            peopleList.put(person.getFirstName(), person);
            return "New person " + person.getFirstName() + " saved";
        }

        @GetMapping("/{firstName}")
        public @ResponseBody Person getPerson(@PathVariable String firstName) {
            return peopleList.get(firstName);
        }
    }
}