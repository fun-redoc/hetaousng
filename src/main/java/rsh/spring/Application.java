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
	@Autowired PersonRepository personsRepo;
	@Autowired ProjectRepository projectsRepo;
	@Autowired ProjectStaffRepository projectStaffRepo;

  public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
  }

  // preset the DB with test data
  @Bean
  public CommandLineRunner demo(PersonRepository personsRepo,
                                ProjectRepository projectRepo,
                                ProjectStaffRepository projectStaffRepo) {
    return (args) -> {
      // some persons
      Person pers1 = new Person("Person1", "Name1", new EmailAddress("hallo1@hallo.com"));
      Person pers2 = new Person("Person2", "Name2", new EmailAddress("hallo2@hallo.com"));
      Person pers3 = new Person("Person3", "Name3", new EmailAddress("hallo3@hallo.com"));
      Person pers4 = new Person("Person4", "Name4", new EmailAddress("hallo4@hallo.com"));
      Person pers5 = new Person("Person5", "Name5", new EmailAddress("hallo5@hallo.com"));

      // some projects
      Project proj1 = new Project("Project1");
      Project proj2 = new Project("Project2");

      // some project staff
      ProjectStaff projectStaff = new ProjectStaff("Projekt1 Person1 first slot");

      projectStaff.setProject(proj1);
      projectStaff.setPerson(pers1);
      
      personsRepo.save(pers1);
      personsRepo.save(pers2);
      personsRepo.save(pers3);
      personsRepo.save(pers4);
      personsRepo.save(pers5);

      projectRepo.save(proj1);
      projectRepo.save(proj2);

      projectStaffRepo.save(projectStaff);
    };
  }

  @Bean
  AppEventHandler appEventHandler() {
      return new AppEventHandler();
  }

} 
