package rsh.spring;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long>,
		QueryDslPredicateExecutor<Person> {

	List<Person> findAll();

	@Transactional(timeout = 10)
	<S extends Person> S save(S entity);

	Person findByEmailAddress(@Param("emailAddress") EmailAddress emailAddress);


  @Query("select op from Person op JOIN op.projectStaff ps JOIN ps.project pr  where pr.name like ?1%")
  List findByProjectLike(String project);
}
