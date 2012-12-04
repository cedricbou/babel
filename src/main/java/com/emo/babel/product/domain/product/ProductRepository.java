package com.emo.babel.product.domain.product;

public interface ProductRepository {

	public Product findByCode(final ProductCode code);
}
