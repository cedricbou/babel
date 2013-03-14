package com.emo.babel.product.domain.feature;

import java.util.List;

public interface Feature {
	public Schema schema();
	public FeatureCode code();
	public List<Configuration> compile(final Configuration config);
}
