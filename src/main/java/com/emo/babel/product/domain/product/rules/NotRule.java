package com.emo.babel.product.domain.product.rules;

import com.emo.babel.product.domain.instance.InstanceSummary;
import com.emo.babel.product.domain.product.InclusionRule;

public class NotRule extends CompositeInclusionRule {

	private final InclusionRule rule;

	protected NotRule(final InclusionRule rule) {
		this.rule = rule;
	}

	@Override
	public boolean isSatisfiedBy(InstanceSummary summary) {
		return !rule.isSatisfiedBy(summary);
	}

}
