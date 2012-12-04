package com.emo.babel.product.domain.product.rules;

import com.emo.babel.product.domain.instance.InstanceSummary;

public class TrueRule extends CompositeInclusionRule {

	public final static TrueRule TRUE = new TrueRule();
	
	protected TrueRule() {
		
	}
	
	@Override
	public boolean isSatisfiedBy(InstanceSummary summary) {
		return true;
	}
}
