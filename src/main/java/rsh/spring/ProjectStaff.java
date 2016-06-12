package rsh.spring;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

import org.springframework.util.Assert;

@Entity
public class ProjectStaff extends AbstractEntity {

  private String someInformation;

  @ManyToOne
  private Project project;

  @ManyToOne
  private Person person;

  public ProjectStaff(String someInformation) {

    Assert.hasText(someInformation);

    this.setSomeInformation(someInformation);
  }

  protected ProjectStaff() {

  }

  public Person getPerson() {
    return this.person;
  }
  public void setPerson(Person person) {
    this.person = person;
 //   this.person.addProjectStaff(this);
  }

  public String getSomeInformation() {
    return someInformation;
  }
  public void setSomeInformation(String someInformation) {
    this.someInformation = someInformation;
  }

  public Project getProject() {
    return project;
  }
  public void setProject(Project project) {
    this.project = project;
//    this.project.addProjectStaff(this);
  }
}
