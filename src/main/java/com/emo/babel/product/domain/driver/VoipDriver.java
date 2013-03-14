package com.emo.babel.product.domain.driver;

import com.emo.babel.product.domain.feature.BasicFeature;
import com.emo.babel.product.domain.feature.FeatureCode;
import com.emo.babel.product.domain.feature.Schema;
import com.emo.babel.product.domain.feature.Schema.Declaration;
import com.emo.babel.product.domain.feature.Schema.Definition;

public class VoipDriver extends Driver {

	public VoipDriver() {
		super(new DriverCode());
		addFeature(new BasicFeature(new FeatureCode("dn"), new Schema(new Definition("dn", Declaration.TEXT))));
		addFeature(new BasicFeature(new FeatureCode("plan"), new Schema(new Definition("plan", Declaration.TEXT))));
	}

}
