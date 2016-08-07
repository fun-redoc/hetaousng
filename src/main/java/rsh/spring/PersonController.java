package rsh.spring;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.Assert;
import java.util.logging.Logger;

@RestController
public class PersonController {

	@Autowired PersonRepository personsRepo;
	@Autowired AddressRepository addressRepo;
  private final static Logger LOGGER = Logger.getLogger(PersonController.class.getName()); 

    @RequestMapping("/api/personAndAdress")
    public HttpEntity<Person> newperson(
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "lastName",  required = true) String lastName,
            @RequestParam(value = "country",   required = true) String country,
            @RequestParam(value = "city",      required = true) String city,
            @RequestParam(value = "street",    required = true) String street
            ) 
    {
          Assert.hasText(firstName);
          Assert.hasText(lastName);
          Assert.hasText(country);
          Assert.hasText(city);
          Assert.hasText(street);
          Person person = new Person(firstName, lastName);
          Address address = new Address(street, city, country);
          address.setPerson(person);
          Assert.notNull(personsRepo);
          personsRepo.save(person);
          addressRepo.save(address);

        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }
}
