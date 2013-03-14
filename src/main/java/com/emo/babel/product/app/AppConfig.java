package com.emo.babel.product.app;

import org.apache.commons.lang.StringUtils;

import com.emo.babel.product.domain.driver.Driver;
import com.emo.babel.product.domain.driver.InternetDriver;
import com.emo.babel.product.domain.driver.VoipDriver;
import com.emo.babel.product.domain.family.Family;
import com.emo.babel.product.domain.feature.AliasedFeature;
import com.emo.babel.product.domain.feature.MappedFeature;
import com.emo.babel.product.domain.feature.Configuration;
import com.emo.babel.product.domain.feature.Feature;
import com.emo.babel.product.domain.feature.FeatureCode;
import com.emo.babel.product.domain.feature.Schema;
import com.emo.babel.product.domain.feature.Schema.Default;

public class AppConfig {

	public AppConfig() {

		final Driver voip = new VoipDriver();
		final Driver internet = new InternetDriver();

		final Family telephony = new Family("telephony");
		final Family isp = new Family("isp");

		// voip.dn : dn : text
		// voip.plan : plan : local|worldwide
		// internet.plan : plan : ftth|adsl20|adsl10
		// internet.qosonvoip : enabled : boolean

		// isp.voip => voip.dn + voip.plan + internet.qosonvoip
		// isp.voip => dn : text + plan : local|worldwide + enabled : true

		final Feature ispVoip = new MappedFeature(new FeatureCode("voip"),
				voip.feature("dn"), voip.feature("plan"), new AliasedFeature(
						new FeatureCode("qos"), internet.feature("qosonvoip"),
						new Default("enabled", "true")));

		Configuration c = new Configuration(ispVoip.code());
		c.put("dn", "0312345678");
		c.put("plan", "worldwide");
		System.out.println(StringUtils.join(ispVoip.compile(c), "\n"));

	}

	public static void main(String[] args) {
		new AppConfig();
	}
}
