package com.emo.babel.product.domain.product.rules;

import com.emo.babel.product.domain.instance.InstanceSummary;

public class FalseRule extends CompositeInclusionRule {

	public final static FalseRule FALSE = new FalseRule();
	
	protected FalseRule() {
		
	}
	
	
	@Override
	public boolean isSatisfiedBy(InstanceSummary summary) {
		return false;
	}
	
}
