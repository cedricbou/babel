package com.emo.babel.product.domain.instance;

import com.emo.babel.product.domain.feature.FeatureCode;
import com.emo.babel.product.domain.product.ProductCode;

public class Instance {

	private static enum State {
		DRAFT, READY;
	}
	
	private State state = State.DRAFT;
	
	private final InstanceCode code;
	
	private final ProductCode productCode;

	private final InstanceValidation validation;
	
	public Instance(final InstanceCode code, final ProductCode productCode, final InstanceValidation validation) {
		this.code = code;
		this.productCode = productCode;
		this.validation = validation;
	}
	
	public void featuring(final FeatureCode code) {
		assertModifiableState();
		
		// TODO : add feature
	}
	
	private void assertModifiableState() {
		if(state != State.DRAFT) {
			throw new IllegalStateException("expected instance in DRAFT state");
		}
	}
	
	private InstanceSummary summary() {
		return null;
	}
	
	public void go() {
		// check constraints.
		if(!validation.validate(summary(), productCode)) {
			throw new IllegalStateException("product instance is not valid"); // TODO : Get better error reporting :)
		}
		
		// mark ready
		state = State.READY;
	}
}
