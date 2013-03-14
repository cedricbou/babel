package com.emo.babel.product.domain.driver;

import java.util.HashMap;
import java.util.Map;

import com.emo.babel.product.domain.feature.Feature;
import com.emo.babel.product.domain.feature.FeatureCode;

public class Driver {

	private final DriverCode code;
	
	private Map<FeatureCode, Feature> features = new HashMap<FeatureCode, Feature>();
	
	public Driver(final DriverCode code) {
		this.code = code;
	}
	
	protected void addFeature(final Feature f) {
		features.put(f.code(), f);
	}
	
	public Feature[] features() {
		return features.values().toArray(new Feature[] {});
	}
	
	public Feature feature(final String code) {
		return feature(new FeatureCode(code));
	}
	
	public Feature feature(final FeatureCode code) {
		return features.get(code);
	}
}
