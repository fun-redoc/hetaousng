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
    };
  }

  @Bean
  AppEventHandler appEventHandler() {
      return new AppEventHandler();
  }

} 
