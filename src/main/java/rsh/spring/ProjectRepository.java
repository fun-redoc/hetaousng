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
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>,
		QueryDslPredicateExecutor<Project> {

	List<Project> findAll();

	@Transactional(timeout = 10)
	<S extends Project> S save(S entity);

	Project findByName(@Param("name") String name);
}
