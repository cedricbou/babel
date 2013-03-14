package com.emo.babel.product.domain.feature;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.emo.babel.product.domain.feature.Configuration.Entry;
import com.emo.babel.product.domain.feature.Schema.Declaration;
import com.emo.babel.product.domain.feature.Schema.Default;
import com.emo.babel.product.domain.feature.Schema.Definition;
import com.emo.babel.product.domain.feature.Schema.Definition.Constraint;
import com.emo.babel.product.domain.feature.Schema.Mapping;

public class ComposedFeatureTest {

	private final FeatureCode A = new FeatureCode("a");
	private final FeatureCode B = new FeatureCode("b");
	private final FeatureCode C = new FeatureCode("c");
	private final FeatureCode D = new FeatureCode("d");
	private final FeatureCode E = new FeatureCode("e");
	
	private final Schema s1 = new Schema(
		new Definition("el1", Declaration.INTEGER),
		new Definition("el2", Declaration.DOUBLE),
		new Definition("el3", Declaration.TEXT, Constraint.Optional));

	private final Schema s2 = new Schema(
			new Definition("el4", Declaration.INTEGER),
			new Definition("el5", Declaration.DOUBLE),
			new Definition("el3", Declaration.TEXT, Constraint.Optional));

	private final Schema s3 = new Schema(
			new Definition("el6", Declaration.INTEGER),
			new Definition("el7", Declaration.DOUBLE),
			new Definition("el2", Declaration.TEXT));

	private final Schema s4 = new Schema(
			new Definition("el8", Declaration.INTEGER),
			new Definition("el9", Declaration.DOUBLE));

	private final Feature f1 = new BasicFeature(A, s1);
	private final Feature f2 = new BasicFeature(B, s2);
	private final Feature f3 = new BasicFeature(C, s3);
	
	@Test
	public void compileComposedFeatureTest() {
		
		final Feature af1 = new AliasedFeature(E, f1, new Default("el3", "hello"));
		
		final Feature c = new MappedFeature(D, s4, new Mapping[] {
				new Mapping("el8", E, "el1"),
				new Mapping("el9", E, "el2"),
				new Mapping("el8", B, "el4"),
				new Mapping("el9", B, "el5") }, 
				af1, f2);
		
		System.out.println(
			StringUtils.join(
				c.compile(new Configuration(
					D,
					new Entry("el8", "3"),
					new Entry("el9", "4.5"))), "\n"));
		
	}

}
