package com.emo.babel.product.domain.product;

import com.emo.babel.product.domain.family.FamilyCode;
import com.emo.babel.product.domain.feature.FeatureCode;
import com.emo.babel.product.domain.instance.InstanceSummary;
import com.emo.babel.product.domain.product.rules.ManyRule;
import com.emo.babel.product.domain.product.rules.StrictInclusionRule;
import com.emo.babel.product.domain.product.rules.SubsetRule;

public class Product {
	
	private static enum State {
		DRAFT, DEFINED;
	}
	
	private final FamilyCode familyCode;

	private InclusionRule rule = InclusionRule.TRUE;
	
	private FeatureSet exhaustiveFeatures = new FeatureSet();
	
	private State state = State.DRAFT;
	
	/**
	 * A product must be part of a family.
	 * @param familyCode
	 */
	public Product(final FamilyCode familyCode) {
		this.familyCode = familyCode;
	}
	
	public void featuring(final FeatureCode feature) {
		assertModifiable();
		rule = rule.and(new ManyRule(feature, 1, 1));
		exhaustiveFeatures.put(feature);
	}
	
	public void featuring(final FeatureCode feature, final int maxOccurences) {
		assertModifiable();
		rule = rule.and(new ManyRule(feature, 1, maxOccurences));
		exhaustiveFeatures.put(feature);
	}

	public void featuring(final FeatureCode feature, final int minOccurences, final int maxOccurences) {
		assertModifiable();
		rule = rule.and(new ManyRule(feature, minOccurences, maxOccurences));
		exhaustiveFeatures.put(feature);
	}
	
	public void featuring(final FeatureSet features) {
		assertModifiable();
		rule = rule.and(new SubsetRule(features, 1, 1));
		exhaustiveFeatures.merge(features);
	}
	
	public void featuring(final FeatureSet features, final int minOccurences, final int maxOccurences) {
		assertModifiable();
		rule = rule.and(new SubsetRule(features, minOccurences, maxOccurences));
		exhaustiveFeatures.merge(features);
	}
	
	private void assertModifiable() {
		if(!isDraft()) {
			throw new IllegalStateException("product can only be modified in draft state");
		}
	}
	
	private void assertDefined() {
		if(!isDefined()) {
			throw new IllegalStateException("product must be defined");
		}
	}
	
	public boolean validate(final InstanceSummary summary) {
		assertDefined();			
		return rule.isSatisfiedBy(summary);
	}
	
	private boolean isDraft() {
		return state == State.DRAFT;
	}
	
	private boolean isDefined() {
		return state == State.DEFINED;
	}
	
	public void define() {
		assertModifiable();
		rule = rule.and(new StrictInclusionRule(exhaustiveFeatures));
		state = State.DEFINED;
	}
}
