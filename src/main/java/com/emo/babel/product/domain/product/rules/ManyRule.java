package com.emo.babel.product.domain.product.rules;

import com.emo.babel.product.domain.feature.FeatureCode;
import com.emo.babel.product.domain.instance.InstanceSummary;

public class ManyRule extends CompositeInclusionRule {

	private final int min;
	private final int max;
	private final FeatureCode feature;
	
	public ManyRule(final FeatureCode feature, final int min, final int max) {
		this.max = max;
		this.min = min;
		this.feature = feature;
	}
	
	@Override
	public boolean isSatisfiedBy(InstanceSummary summary) {
		final int occurences = summary.count(feature);
		return min <= occurences && occurences <= max;		
	}
}
