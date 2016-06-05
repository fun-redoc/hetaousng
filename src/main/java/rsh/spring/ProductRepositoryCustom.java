package rsh.spring;

import java.math.BigDecimal;

interface ProductRepositoryCustom {

	/**
	 * Removes all {@link Product}s with a price greater than the given one.
	 * 
	 * @param price
	 */
	void removeProductsMoreExpensiveThan(BigDecimal price);
}
