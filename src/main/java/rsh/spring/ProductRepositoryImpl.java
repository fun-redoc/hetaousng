package rsh.spring;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

public class ProductRepositoryImpl extends QueryDslRepositorySupport implements ProductRepositoryCustom {

	private static final QProduct product = QProduct.product;

	/**
	 * Creates a new instance of {@link ProductRepositoryImpl}.
	 */
	public ProductRepositoryImpl() {
		super(Product.class);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.olivergierke.deepdive.ProductRepositoryCustom#removeProductsMoreExpensiveThan(java.math.BigDecimal)
	 */
	@Override
	public void removeProductsMoreExpensiveThan(BigDecimal price) {
		delete(product).where(product.price.gt(price)).execute();
	}
}
