package com.emo.babel.product.domain.product.rules;

import com.emo.babel.product.domain.instance.InstanceSummary;
import com.emo.babel.product.domain.product.FeatureSet;

public class SubsetRule extends CompositeInclusionRule {

	private final FeatureSet features;

	private final int min;

	private final int max;

	public SubsetRule(final FeatureSet features, final int min, final int max) {
		this.features = features;
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean isSatisfiedBy(InstanceSummary summary) {
		// find existing features corresponding to this set.
		final FeatureSet intersect = features.intersect(summary.features());
		// number of features from the set	
		final int occurences = intersect.size(); 						
		// if number of feature in this subset is in the allowed range
		return min <= occurences && occurences <= max;
	}

}
