package com.emo.babel.product.domain.driver;

import com.emo.babel.product.domain.feature.BasicFeature;
import com.emo.babel.product.domain.feature.FeatureCode;
import com.emo.babel.product.domain.feature.Schema;
import com.emo.babel.product.domain.feature.Schema.Declaration;
import com.emo.babel.product.domain.feature.Schema.Definition;

public class InternetDriver extends Driver {

	public InternetDriver() {
		super(new DriverCode());
		addFeature(new BasicFeature(new FeatureCode("qosonvoip"), new Schema(new Definition("enabled", Declaration.TEXT))));
		addFeature(new BasicFeature(new FeatureCode("plan"), new Schema(new Definition("plan", Declaration.TEXT))));
	}
}
