package com.emo.babel.product.domain.product.rules;

import com.emo.babel.product.domain.product.InclusionRule;

public abstract class CompositeInclusionRule implements InclusionRule {

	@Override
	public InclusionRule and(InclusionRule r) {
		return new AndRule(this, r);
	}
	
	@Override
	public InclusionRule or(InclusionRule r) {
		return new OrRule(this, r);
	}
	
	@Override
	public InclusionRule xor(InclusionRule r) {
		return new XorRule(this, r);
	}
	
	@Override
	public InclusionRule not() {
		return new NotRule(this);
	}
}
