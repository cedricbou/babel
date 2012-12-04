package com.emo.babel.product.domain.feature;

public class FeatureCode {
	public final String code;

	public FeatureCode(final String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FeatureCode) {
			return code.equals(((FeatureCode)obj).code);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return code.hashCode();
	}
}
