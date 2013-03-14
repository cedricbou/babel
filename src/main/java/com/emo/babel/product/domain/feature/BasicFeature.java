package com.emo.babel.product.domain.feature;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasicFeature extends BaseFeature {

	public BasicFeature(FeatureCode code, Schema schema) {
		super(code, schema);
	}

	@Override
	protected List<Configuration> doCompile(final Configuration config) {
		// TODO : find real immutable and cheap collections for java...
		return Collections.unmodifiableList(Arrays.asList(config));
	}
	
}
