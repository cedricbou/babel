package com.emo.babel.product.domain.feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

public class Configuration {
	private final Map<String, String> configured = new HashMap<String, String>();
	
	private final FeatureCode forFeature;
	
	public static class Entry {
		public final String name;
		public final String value;
		
		public Entry(final String name, final String value) {
			Assert.notNull(name);
			Assert.hasText(name);
			
			this.name = name;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return name + " : " + value;
		}
		
	}
	
	public Configuration(final FeatureCode forFeature, final Entry... entries) {
		Assert.notNull(forFeature);
		
		this.forFeature = forFeature;
		for(final Entry entry : entries) {
			this.put(entry.name, entry.value);
		}
	}
	
	public void put(final String name, final String value) {
		configured.put(name, value);
	}
	
	
	public String get(final String name) {
		return configured.get(name);
	}
	
	public boolean contains(final String name) {
		return configured.containsKey(name);
	}
	
	public Entry[] entries() {
		final ArrayList<String> keys = new ArrayList<String>(configured.keySet());
		Collections.sort(keys);
		
		final Entry[] entries = new Entry[keys.size()];
		for(int i = 0; i < keys.size(); ++i) {
			entries[i] = new Entry(keys.get(i), configured.get(keys.get(i)));
		}

		return entries;
	}
	
	public FeatureCode forFeature() {
		return this.forFeature;
	}
	
	@Override
	public String toString() {
		return "config(" + forFeature + ") : \n" + StringUtils.join(entries(), "\n");
	}
	
}
