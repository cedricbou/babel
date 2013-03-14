package com.emo.babel.product.domain.feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.emo.babel.product.domain.feature.Schema.Definition;
import com.emo.babel.product.domain.feature.Schema.Mapping;

public class MergedFeature extends BaseFeature {

	private final Feature[] features;
	private final Map<FeatureCode, Map<String, String>> mappings = new HashMap<FeatureCode, Map<String, String>>();

	public MergedFeature(final FeatureCode code, final Schema schema,
			final Mapping[] mappings, final Feature... features) {
		super(code, schema);

		this.features = features;
		
		final List<FeatureCode> featureCodes = new ArrayList<FeatureCode>(features.length);
		for(final Feature f : features) {
			featureCodes.add(f.code());
		}

		for (final Mapping mapping : mappings) {
			if(!featureCodes.contains(mapping.code)) {
				throw new IllegalArgumentException("mapping should only reference features from the composition, found feature " + mapping.code);
			}
			
			if (!this.mappings.containsKey(mapping.code)) {
				this.mappings.put(mapping.code, new HashMap<String, String>());
			}

			this.mappings.get(mapping.code).put(mapping.dest, mapping.source);
		}
	}

	public MergedFeature(final FeatureCode code, final Schema schema, final Feature... features) {
		this(code, schema, schema.autoMapping(features), features);
	}
	
	public MergedFeature(final FeatureCode code, final Feature... features) {
		this(code, Schema.fromFeatures(features), features);
	}
	
	public List<Configuration> doCompile(final Configuration config) {
		List<Configuration> configurations = new LinkedList<Configuration>();

		for (final Feature feature : features) {
			Configuration subConfig = new Configuration(feature.code());
			for (final Definition definition : feature.schema().definitions()) {
				if(mappings.get(feature.code()).containsKey(definition.name)) {
					final String src = mappings.get(feature.code()).get(
							definition.name);
					subConfig.put(definition.name, config.get(src));
				}
			}

			configurations.addAll(feature.compile(subConfig));
		}

		return Collections.unmodifiableList(configurations);
	}

}
