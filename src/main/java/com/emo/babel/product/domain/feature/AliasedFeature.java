package com.emo.babel.product.domain.feature;

import java.util.List;

import com.emo.babel.product.domain.feature.Schema.Default;

public class AliasedFeature extends BaseFeature {

	private final Feature feature;
	private final Default[] defaults;
	
	public AliasedFeature(final FeatureCode code, final Feature feature) {
		super(code, feature.schema());
		this.feature = feature;
		this.defaults = new Default[] {};
	}
	
	public AliasedFeature(final FeatureCode code, final Feature feature, final Default... defaults) {
		super(code, feature.schema().substractDefaults(defaults));
		this.feature = feature;
		this.defaults = defaults;
		
		for(final Default d : defaults) {
			if(!feature.schema().contains(d.name)) {
				throw new IllegalArgumentException("default feature value refers to non existent declaration : " + d.name);
			}
		}
	}
	
	@Override
	public List<Configuration> doCompile(Configuration config) {
		final Configuration newConfig = new Configuration(feature.code(), config.entries());
		
		for(final Default d : defaults) {
			newConfig.put(d.name, d.value);
		}
		
		return feature.compile(newConfig);
	}
}
