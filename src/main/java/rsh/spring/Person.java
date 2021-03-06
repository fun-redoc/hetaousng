package rsh.spring;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.util.Assert;

@Entity
public class Person extends AbstractEntity {

  private String firstname, lastname;

  @Column(unique = true, name="email")
  @Embedded
  private EmailAddress emailAddress;

  @OneToMany(mappedBy="person", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Address> addresses; // = new HashSet<Address>();

  @OneToMany(mappedBy="person", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProjectStaff> projectStaff; 

  public Person(String firstname, String lastname, EmailAddress emailAddress) {

    Assert.hasText(firstname);
    Assert.hasText(lastname);
    Assert.notNull(emailAddress);

    this.firstname = firstname;
    this.lastname = lastname;
    this.setEmailAddress(emailAddress);
  }

  public Person(String firstname, String lastname) {

    Assert.hasText(firstname);
    Assert.hasText(lastname);

    this.firstname = firstname;
    this.lastname = lastname;
  }
  protected Person() {

  }


  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public EmailAddress getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(EmailAddress emailAddress) {
    this.emailAddress = emailAddress;
  }

//  public void addAddress(Address address) {
//    Assert.notNull(address);
//    this.addresses.add(address);
//  }
//  public Set<Address> getAddresses() {
//    return Collections.unmodifiableSet(addresses);
//  }
}
