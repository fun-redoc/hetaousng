package rsh.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application {

  private final static Logger log = LoggerFactory.getLogger(Application.class);
	@Autowired CustomerRepository repository;

  public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
  }

  public void run(String... args) {
    log.info("HALLO ---------------------");
		Customer stefan = new Customer("Stefan", "Lassard");
		Customer result = repository.save(stefan);
    int a = 1;
    int b = 2 / (a-1);
  }

  // preset the DB with test data
  @Bean
  public CommandLineRunner demo(CustomerRepository repository) {
    return (args) -> {
      // save a couple of customers
      repository.save(new Customer("Jack", "Bauer"));
      repository.save(new Customer("Chloe", "O'Brian"));
      repository.save(new Customer("Kim", "Bauer"));
      repository.save(new Customer("David", "Palmer"));
      repository.save(new Customer("Michelle", "Dessler"));

      // fetch all customers
      //log.info("Customers found with findAll():");
      //log.info("-------------------------------");
      //for (Customer customer : repository.findAll()) {
      //  log.info(customer.toString());
      //}
      //log.info("");

      //// fetch an individual customer by ID
      //Customer customer = repository.findOne(1L);
      //log.info("Customer found with findOne(1L):");
      //log.info("--------------------------------");
      //log.info(customer.toString());
      //log.info("");

      //// fetch customers by last name
      //log.info("Customer found with findByLastName('Bauer'):");
      //log.info("--------------------------------------------");
      //for (Customer bauer : repository.findByLastName("Bauer")) {
      //  log.info(bauer.toString());
      //}
      //log.info("");
    };
  }
} 
