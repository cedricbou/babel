package com.emo.babel.product.domain.product.rules;

import com.emo.babel.product.domain.instance.InstanceSummary;
import com.emo.babel.product.domain.product.InclusionRule;

public class XorRule extends CompositeInclusionRule {

	private InclusionRule[] rules;

	protected XorRule(final InclusionRule... rules) {
		this.rules = rules;
	}

	@Override
	public boolean isSatisfiedBy(InstanceSummary summary) {
		boolean result = rules[0].isSatisfiedBy(summary);
		for (int i = 1; i < rules.length; ++i) {
			result ^= rules[i].isSatisfiedBy(summary);
		}
		return result;
	}

}
