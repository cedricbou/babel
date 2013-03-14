package com.emo.babel.product.domain.feature;

import java.util.List;

import org.springframework.util.Assert;

public abstract class BaseFeature implements Feature {
	private final FeatureCode code;
	private final Schema schema;
	
	public BaseFeature(final FeatureCode code, final Schema schema) {
		Assert.notNull(code);
		Assert.notNull(schema);
		
		this.code = code;
		this.schema = schema;
	}
	
	public Schema schema() {
		return schema;
	}
	
	public FeatureCode code() {
		return code;
	}	
	
	protected abstract List<Configuration> doCompile(Configuration config);
	
	@Override
	public final List<Configuration> compile(Configuration config) {
		Assert.isTrue(isSatisfiedBy(config), config + " -> does not satisfy feature(" + code + ") "
				+ schema());

		return doCompile(config);
	}
	
	protected boolean isSatisfiedBy(final Configuration config) {
		return code.equals(config.forFeature()) && schema.isSatisfiedBy(config);
	}
	

}
