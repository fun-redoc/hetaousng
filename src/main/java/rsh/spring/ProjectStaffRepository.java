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
public interface ProjectStaffRepository extends PagingAndSortingRepository<ProjectStaff, Long>,
		QueryDslPredicateExecutor<ProjectStaff> {

	List<ProjectStaff> findAll();

	@Transactional(timeout = 10)
	<S extends ProjectStaff> S save(S entity);

	//List<ProjectStaff> findByProject(@Param("project") Project project);
}
