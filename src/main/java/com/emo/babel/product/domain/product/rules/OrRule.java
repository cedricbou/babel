package com.emo.babel.product.domain.product.rules;

import com.emo.babel.product.domain.instance.InstanceSummary;
import com.emo.babel.product.domain.product.InclusionRule;

public class OrRule extends CompositeInclusionRule {

	private InclusionRule[] rules;

	protected OrRule(final InclusionRule... rules) {
		this.rules = rules;
	}

	@Override
	public boolean isSatisfiedBy(InstanceSummary summary) {
		boolean result = false;
		for (final InclusionRule rule : this.rules) {
			result |= rule.isSatisfiedBy(summary);
		}
		return result;
	}

}
