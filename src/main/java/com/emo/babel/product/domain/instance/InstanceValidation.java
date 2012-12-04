package com.emo.babel.product.domain.instance;

import org.springframework.util.Assert;

import com.emo.babel.product.domain.product.Product;
import com.emo.babel.product.domain.product.ProductCode;
import com.emo.babel.product.domain.product.ProductRepository;

public class InstanceValidation {

	private final ProductRepository productRepository;
	
	public InstanceValidation(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public boolean validate(final InstanceSummary summary, final ProductCode productCode) {
		// TODO : transactional for session management.
		final Product product = productRepository.findByCode(productCode);
		Assert.notNull(product);

		return product.validate(summary);
	}
	
}
