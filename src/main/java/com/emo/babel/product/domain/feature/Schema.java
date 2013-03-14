package com.emo.babel.product.domain.feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

public class Schema {
	private final static Mapping[] EMPTY_MAPPING = new Mapping[] {};
	
	public static class Mapping {
		public final String source;
		public final FeatureCode code;
		public final String dest;
		
		public Mapping(final String source, final FeatureCode code, final String dest) {
			this.source = source;
			this.code = code;
			this.dest = dest;
		}
	}

	public static class Default {
		public final String name;
		public final String value;
		
		public Default(final String name, final String value) {
			this.name = name;
			this.value = value;
		}
	}
	
	public static interface Declaration {
		public boolean isSatisfiedBy(final String value);
		
		public final static Declaration INTEGER = new IntegerDeclaration();
		public final static Declaration DOUBLE = new DoubleDeclaration();
		public final static Declaration TEXT = new TextDeclaration();

	}
	
	public static class IntegerDeclaration implements Declaration {
		@Override
		public boolean isSatisfiedBy(String value) {
			try {
				Integer.parseInt(value);
				return true;
			}
			catch(final Exception e) {
				return false;
			}
		}
	}

	public static class DoubleDeclaration implements Declaration {
		@Override
		public boolean isSatisfiedBy(String value) {
			try {
				Double.parseDouble(value);
				return true;
			}
			catch(final Exception e) {
				return false;
			}
		}
	}
	
	public static class TextDeclaration implements Declaration {
		@Override
		public boolean isSatisfiedBy(String value) {
			try {
				Assert.hasText(value);
				return true;
			}
			catch(final Exception e) {
				return false;
			}
		}
	}

	public static class Definition {
		public final String name;
		public final Declaration declaration;
		public final Constraint constraint;
		
		public static enum Constraint {
			Required(false), Optional(true);
			
			private final boolean optional;
			
			private Constraint(final boolean optional) {
				this.optional = optional;
			}
			
			public boolean optional() {
				return optional;
			}
		}
		
		public Definition(final String name, final Declaration declaration) {
			this.name = name;
			this.declaration = declaration;
			this.constraint = Constraint.Required;
		}
		
		public Definition(final String name, final Declaration declaration, final Constraint constraint) {
			this.name = name;
			this.declaration = declaration;
			this.constraint = constraint;
		}

		public boolean optional() {
			return this.constraint.optional();
		}
		
		@Override
		public String toString() {
			return name + " : " + declaration.getClass().getSimpleName();
		}
	}
	
	private final List<Definition> definitions;
	
	public Schema(final Definition... definitions) {
		this.definitions = Collections.unmodifiableList(Arrays.asList(definitions));
	}
	
	public Schema(final Schema... fromThose) {
		this(merge(fromThose));
	}
	
	private static Definition[] merge(final Schema... schemas) {
		int length = 0;
		
		for(final Schema schema : schemas) {
			length += schema.definitions.size();
		}
		
		final Definition[] definitions = new Definition[length];
		
		int index = 0;
		for(final Schema schema : schemas) {
			System.arraycopy(schema.definitions().toArray(), 0, definitions, index, schema.definitions().size());
			index += schema.definitions().size();
		}
		
		return definitions;
	}
	
	public List<Definition> definitions() {
		return definitions;
	}
	
	public boolean isSatisfiedBy(final Configuration config) {
		boolean satisfied = true;
		for(final Definition def : this.definitions()) {
			if(config.contains(def.name) && config.get(def.name) != null) {
				satisfied &= def.declaration.isSatisfiedBy(config.get(def.name));
			}
			else {
				satisfied &= def.optional();
			}
		}
		return satisfied;
	}
	
	public Schema substractDefaults(final Default... defaults) {
		final List<Definition> keeped = new ArrayList<Definition>(definitions.size());
		
		for(final Definition def : definitions) {
			boolean keep = true;
			for(final Default d : defaults) {
				keep &= !def.name.equals(d.name);
			}
			if(keep) {
				keeped.add(def);
			}
		}
		
		return new Schema(keeped.toArray(new Definition[] {}));
	}
	
	public static Schema fromFeatures(final Feature... features) {
		final Schema[] schemas = new Schema[features.length];
		for(int i = 0; i < features.length; ++i) {
			schemas[i] = features[i].schema();
		}
		return new Schema(schemas);
	}
	
	public Mapping[] autoMapping(final Feature... features) {
		final List<Mapping> mappings = new ArrayList<Mapping>(definitions.size());
		
		for(final Feature f : features) {
			for(final Definition def : definitions) {
				if(f.schema().contains(def.name)) {
					mappings.add(new Mapping(def.name, f.code(), def.name));
				}
			}
		}
		
		return mappings.toArray(EMPTY_MAPPING);
	}
	
	public boolean contains(final String name) {
		for(final Definition d : definitions) {
			if(d.name.equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "schema : \n" + StringUtils.join(definitions, "\n");
	}
}
