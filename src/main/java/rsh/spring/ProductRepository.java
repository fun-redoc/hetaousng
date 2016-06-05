package rsh.spring;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends ReadOnlyRepository<Product>, ProductRepositoryCustom {

	/**
	 * Returns a {@link Page} of {@link Product}s having a description which contains the given snippet.
	 * 
	 * @param description
	 * @param pageable
	 * @return
	 */
	Page<Product> findByDescriptionContaining(String description, Pageable pageable);

	/**
	 * Returns all {@link Product}s having the given attribute.
	 * 
	 * @param attribute
	 * @return
	 */
	@Query("select p from Product p where p.attributes[?1] = ?2")
	List<Product> findByAttributeAndValue(String attribute, String value);
}
