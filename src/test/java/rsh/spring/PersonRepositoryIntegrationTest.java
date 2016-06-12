package rsh.spring;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.mysema.query.types.expr.BooleanExpression;

public class PersonRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired PersonRepository repository;

	@Test
	public void findsPersonById() {

		Person person = repository.findOne(1L);

		assertThat(person, is(notNullValue()));
		assertThat(person.getFirstname(), is("Dave"));
		assertThat(person.getLastname(), is("Matthews"));
	}

	@Test
	public void savesNewPerson() {

		Person stefan = new Person("Stefan", "Lassard");
		Person result = repository.save(stefan);

		assertThat(result, is(notNullValue()));
		assertThat(result.getId(), is(notNullValue()));
		assertThat(result.getFirstname(), is("Stefan"));
		assertThat(result.getLastname(), is("Lassard"));
	}

	@Test
	public void savesExistingPerson() {

		Person dave = repository.findOne(1L);
		dave.setEmailAddress(new EmailAddress("davematthews@dmband.com"));
		repository.save(dave);

		Person result = repository.findOne(1L);

		assertThat(result, is(notNullValue()));
		assertThat(result.getId(), is(notNullValue()));
		assertThat(result.getFirstname(), is("Dave"));
		assertThat(result.getEmailAddress(), is(new EmailAddress("davematthews@dmband.com")));
	}

	@Test
	public void findsPersonsByEmailAddress() {

		Person result = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));

		assertThat(result, is(notNullValue()));
		assertThat(result.getFirstname(), is("Dave"));
		assertThat(result.getLastname(), is("Matthews"));
	}

	@Test
	public void findsAllPersons() {

		List<Person> persons = repository.findAll();
		assertThat(persons, hasSize(3));
	}

	@Test
	public void deletesPerson() {

		repository.delete(1L);
		assertThat(repository.findOne(1L), is(nullValue()));
	}

	@Test
	public void accessesPersonsPageByPage() {

		Page<Person> result = repository.findAll(new PageRequest(1, 1));

		assertThat(result, is(notNullValue()));
		assertThat(result.isFirst(), is(false));
		assertThat(result.isLast(), is(false));
		assertThat(result.getNumberOfElements(), is(1));
	}

	@Test
	public void executesQuerydslPredicate() {

		Person dave = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
		Person carter = repository.findByEmailAddress(new EmailAddress("carter@dmband.com"));

		QPerson person = QPerson.person;

		BooleanExpression firstnameStartsWithDa = person.firstname.startsWith("Da");
		BooleanExpression lastnameContainsEau = person.lastname.contains("eau");

		Iterable<Person> result = repository.findAll(firstnameStartsWithDa.or(lastnameContainsEau));

		assertThat(result, is(Matchers.<Person> iterableWithSize(2)));
		assertThat(result, hasItems(dave, carter));
	}
}
