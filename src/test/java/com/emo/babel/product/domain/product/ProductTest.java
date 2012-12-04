package com.emo.babel.product.domain.product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.emo.babel.product.domain.family.FamilyCode;
import com.emo.babel.product.domain.feature.FeatureCode;
import com.emo.babel.product.domain.instance.InstanceSummary;

public class ProductTest {

	final static InstanceSummary summaryEmpty = new InstanceSummary(Arrays.asList(new FeatureCode[] {}));
	final static InstanceSummary summaryA = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature")));
	final static InstanceSummary summaryAA = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature"), new FeatureCode("a-feature")));
	final static InstanceSummary summaryAB = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature"), new FeatureCode("b-feature")));
	final static InstanceSummary summaryABB = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature"), new FeatureCode("b-feature"), new FeatureCode("b-feature")));
	final static InstanceSummary summaryAABB = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature"), new FeatureCode("a-feature"), new FeatureCode("b-feature"), new FeatureCode("b-feature")));
	final static InstanceSummary summaryAAD = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature"), new FeatureCode("a-feature"), new FeatureCode("d-feature")));
	final static InstanceSummary summaryAACD = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature"), new FeatureCode("a-feature"), new FeatureCode("c-feature"), new FeatureCode("d-feature")));
	final static InstanceSummary summaryAACDE = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature"), new FeatureCode("a-feature"), new FeatureCode("c-feature"), new FeatureCode("d-feature"), new FeatureCode("e-feature")));
	final static InstanceSummary summaryAABC = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature"), new FeatureCode("a-feature"), new FeatureCode("b-feature"), new FeatureCode("c-feature")));
	final static InstanceSummary summaryAABCD = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature"), new FeatureCode("a-feature"), new FeatureCode("b-feature"), new FeatureCode("c-feature"), new FeatureCode("d-feature")));
	final static InstanceSummary summaryAABBCD = new InstanceSummary(Arrays.asList(new FeatureCode("a-feature"), new FeatureCode("a-feature"), new FeatureCode("b-feature"), new FeatureCode("b-feature"), new FeatureCode("c-feature"), new FeatureCode("d-feature")));
	final static InstanceSummary summaryBCDE = new InstanceSummary(Arrays.asList(new FeatureCode("b-feature"), new FeatureCode("b-feature"), new FeatureCode("c-feature"), new FeatureCode("d-feature"), new FeatureCode("e-feature")));
	final static InstanceSummary summaryCE = new InstanceSummary(Arrays.asList(new FeatureCode("c-feature"), new FeatureCode("e-feature")));

	
	@Test
	public void noFeatureProductDefinition() {
		final Product p = new Product(new FamilyCode("Family"));
		p.define();

		assertFalse(p.validate(summaryA));
		assertTrue(p.validate(summaryEmpty));
		assertFalse(p.validate(summaryAA));
		assertFalse(p.validate(summaryAB));
	}

	@Test
	public void singleFeatureProductDefinition() {
		final Product p = new Product(new FamilyCode("Family"));
		
		p.featuring(new FeatureCode("a-feature"));
		p.define();
		
		assertTrue(p.validate(summaryA));
		assertFalse(p.validate(summaryEmpty));
		assertFalse(p.validate(summaryAA));
		assertFalse(p.validate(summaryAB));
	}

	@Test
	public void severalFeaturesProductDefinition() {
		final Product p = new Product(new FamilyCode("Family"));
		
		p.featuring(new FeatureCode("a-feature"));
		p.featuring(new FeatureCode("b-feature"));
		p.define();
		
		assertFalse(p.validate(summaryA));
		assertFalse(p.validate(summaryEmpty));
		assertFalse(p.validate(summaryAA));
		assertTrue(p.validate(summaryAB));
		assertFalse(p.validate(summaryABB));
		assertFalse(p.validate(summaryAABB));
	}

	@Test
	public void severalOptionalFeaturesProductDefinition() {
		final Product p = new Product(new FamilyCode("Family"));
		
		p.featuring(new FeatureCode("a-feature"), 2);
		p.featuring(new FeatureCode("b-feature"), 0, 10);
		p.define();
		
		assertTrue(p.validate(summaryA));
		assertFalse(p.validate(summaryEmpty));
		assertTrue(p.validate(summaryAA));
		assertTrue(p.validate(summaryAB));
		assertTrue(p.validate(summaryABB));
		assertTrue(p.validate(summaryAABB));
	}

	
	@Test
	public void severalOptionalAndSetFeaturesProductDefinition() {
		final Product p = new Product(new FamilyCode("Family"));
		
		p.featuring(new FeatureCode("a-feature"), 2);
		p.featuring(new FeatureCode("b-feature"), 0, 10);
		p.featuring(new FeatureSet(Arrays.asList(new FeatureCode("c-feature"), new FeatureCode("d-feature"), new FeatureCode("e-feature"))), 1, 2);
		p.define();
		
		assertFalse(p.validate(summaryA));
		assertFalse(p.validate(summaryEmpty));
		assertFalse(p.validate(summaryAA));
		assertFalse(p.validate(summaryAB));
		assertFalse(p.validate(summaryAABB));
		assertTrue(p.validate(summaryAABC));
		assertTrue(p.validate(summaryAACD));
		assertFalse(p.validate(summaryBCDE));
		assertTrue(p.validate(summaryAABCD));
		assertTrue(p.validate(summaryAABBCD));
		assertTrue(p.validate(summaryAABBCD));
	}

	
}
