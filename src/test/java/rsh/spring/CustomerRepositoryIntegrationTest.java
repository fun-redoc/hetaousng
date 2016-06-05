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

public class CustomerRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired CustomerRepository repository;

	@Test
	public void findsCustomerById() {

		Customer customer = repository.findOne(1L);

		assertThat(customer, is(notNullValue()));
		assertThat(customer.getFirstname(), is("Dave"));
		assertThat(customer.getLastname(), is("Matthews"));
	}

	@Test
	public void savesNewCustomer() {

		Customer stefan = new Customer("Stefan", "Lassard");
		Customer result = repository.save(stefan);

		assertThat(result, is(notNullValue()));
		assertThat(result.getId(), is(notNullValue()));
		assertThat(result.getFirstname(), is("Stefan"));
		assertThat(result.getLastname(), is("Lassard"));
	}

	@Test
	public void savesExistingCustomer() {

		Customer dave = repository.findOne(1L);
		dave.setEmailAddress(new EmailAddress("davematthews@dmband.com"));
		repository.save(dave);

		Customer result = repository.findOne(1L);

		assertThat(result, is(notNullValue()));
		assertThat(result.getId(), is(notNullValue()));
		assertThat(result.getFirstname(), is("Dave"));
		assertThat(result.getEmailAddress(), is(new EmailAddress("davematthews@dmband.com")));
	}

	@Test
	public void findsCustomersByEmailAddress() {

		Customer result = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));

		assertThat(result, is(notNullValue()));
		assertThat(result.getFirstname(), is("Dave"));
		assertThat(result.getLastname(), is("Matthews"));
	}

	@Test
	public void findsAllCustomers() {

		List<Customer> customers = repository.findAll();
		assertThat(customers, hasSize(3));
	}

	@Test
	public void deletesCustomer() {

		repository.delete(1L);
		assertThat(repository.findOne(1L), is(nullValue()));
	}

	@Test
	public void accessesCustomersPageByPage() {

		Page<Customer> result = repository.findAll(new PageRequest(1, 1));

		assertThat(result, is(notNullValue()));
		assertThat(result.isFirst(), is(false));
		assertThat(result.isLast(), is(false));
		assertThat(result.getNumberOfElements(), is(1));
	}

	@Test
	public void executesQuerydslPredicate() {

		Customer dave = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
		Customer carter = repository.findByEmailAddress(new EmailAddress("carter@dmband.com"));

		QCustomer customer = QCustomer.customer;

		BooleanExpression firstnameStartsWithDa = customer.firstname.startsWith("Da");
		BooleanExpression lastnameContainsEau = customer.lastname.contains("eau");

		Iterable<Customer> result = repository.findAll(firstnameStartsWithDa.or(lastnameContainsEau));

		assertThat(result, is(Matchers.<Customer> iterableWithSize(2)));
		assertThat(result, hasItems(dave, carter));
	}
}
