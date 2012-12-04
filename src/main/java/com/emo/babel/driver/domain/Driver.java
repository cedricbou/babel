package com.emo.babel.driver.domain;

import java.util.Collection;

public interface Driver {

	public static class Feature {
		public String name;
	}
	
	public static class FeatureConfigurationDescription {
		
	}
	
	/**
	 * Returns the list of available features.
	 * @return
	 */
	public Collection<Feature> availableFeatures();
	
	/**
	 * Returns the configuration description for a given feature.
	 * @param featureName
	 * @return
	 */
	public FeatureConfigurationDescription descriptionFor(final String featureName);
	
	
}
