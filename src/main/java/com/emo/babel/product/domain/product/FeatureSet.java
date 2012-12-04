package com.emo.babel.product.domain.product;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.emo.babel.product.domain.feature.FeatureCode;

public class FeatureSet {
	private final Set<FeatureCode> features = new HashSet<FeatureCode>();
	
	public FeatureSet() {
		super();
	}
	
	public FeatureSet(final Collection<FeatureCode> all) {
		super();
		features.addAll(all);
	}
	
	public boolean contains(final FeatureCode feature) {
		return features.contains(feature);
	}
	
	public int size() {
		return features.size();
	}
	
	@SuppressWarnings("unchecked")
	public FeatureSet intersect(final Collection<FeatureCode> features) {
		return new FeatureSet(CollectionUtils.intersection(this.features, features));
	}
	
	public void merge(final FeatureSet set) {
		this.features.addAll(set.features);
	}
	
	public void put(final FeatureCode feature) {
		features.add(feature);
	}
}
