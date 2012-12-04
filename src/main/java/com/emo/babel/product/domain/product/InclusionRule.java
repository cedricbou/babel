package com.emo.babel.product.domain.product;

import com.emo.babel.product.domain.instance.InstanceSummary;
import com.emo.babel.product.domain.product.rules.FalseRule;
import com.emo.babel.product.domain.product.rules.TrueRule;

public interface InclusionRule {

	public boolean isSatisfiedBy(final InstanceSummary summary);
	
	public InclusionRule or(final InclusionRule r);
	public InclusionRule and(final InclusionRule r);
	public InclusionRule not();	
	public InclusionRule xor(final InclusionRule r);

	public final static InclusionRule TRUE = TrueRule.TRUE;
	public final static InclusionRule FALSE = FalseRule.FALSE;
}
