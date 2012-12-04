package com.emo.babel.product.domain.instance;

import java.util.Collection;
import java.util.Collections;

import com.emo.babel.product.domain.feature.FeatureCode;

public class InstanceSummary {

	private final Collection<FeatureCode> features;
	
	public InstanceSummary(final Collection<FeatureCode> features) {
		this.features = Collections.unmodifiableCollection(features);
	}
	
	public int count(final FeatureCode expectedFeature) {
		int count = 0;
		
		for(final FeatureCode feature : features) {
			if(feature.equals(expectedFeature)) {
				count++;
			}
		}
		
		return count;
	}
	
	public Collection<FeatureCode> features() {
		return features;
	}
	
}
