package com.emo.babel.product.domain.product.rules;

import com.emo.babel.product.domain.feature.FeatureCode;
import com.emo.babel.product.domain.instance.InstanceSummary;
import com.emo.babel.product.domain.product.FeatureSet;

public class StrictInclusionRule extends CompositeInclusionRule {

	private final FeatureSet features;
	
	public StrictInclusionRule(final FeatureSet exhaustiveFeatures) {
		this.features = exhaustiveFeatures;
	}
	
	@Override
	public boolean isSatisfiedBy(final InstanceSummary summary) {
		if(features.size() == 0 && summary.features().size() > 0) {
			return false;
		}
		
		for(final FeatureCode oneOf : summary.features()) {
			if(!features.contains(oneOf)) {
				return false;
			}
		}
		
		return true;
	}
}
