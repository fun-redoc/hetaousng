package rsh.spring;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@DirtiesContext
public class CustomerRepositoryTransactionReconfigurationIntegrationTest {

	@Autowired CustomerRepository repository;

	@Test
	public void executesRedeclaredMethodWithCustomTransactionConfiguration() {

		Customer customer = new Customer("Dave", "Matthews");
		Customer result = repository.save(customer);

		assertThat(result, is(notNullValue()));
		assertThat(result.getId(), is(notNullValue()));
		assertThat(result.getFirstname(), is("Dave"));
		assertThat(result.getLastname(), is("Matthews"));
	}
}
