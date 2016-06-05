package rsh.spring;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>,
		QueryDslPredicateExecutor<Customer> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Customer> findAll();

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Customer> S save(S entity);

	/**
	 * Returns the customer with the given {@link EmailAddress}.
	 * 
	 * @param emailAddress the {@link EmailAddress} to search for.
	 * @since today
	 * @return
	 */
	Customer findByEmailAddress(@Param("emailAddress") EmailAddress emailAddress);
}
