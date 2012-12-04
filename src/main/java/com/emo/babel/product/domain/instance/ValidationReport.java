package com.emo.babel.product.domain.instance;

import java.util.LinkedList;
import java.util.List;

import com.emo.babel.product.domain.feature.FeatureCode;

public class ValidationReport {
	
	private static class Entry {
		private final FeatureCode feature;
		private final String message;
		
		public Entry(final FeatureCode feature, final String message) {
			this.message = message;
			this.feature = feature;
		}
	}
	
	private final List<Entry> errors = new LinkedList<Entry>();
	
	public void error(final FeatureCode feature, final String message) {
		this.errors.add(new Entry(feature, message));
	}
	
	public boolean hasErrors() {
		return errors.size() > 0;
	}
}
