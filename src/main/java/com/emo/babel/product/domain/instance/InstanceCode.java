package com.emo.babel.product.domain.instance;

import java.util.UUID;

public class InstanceCode {

	public final String code = UUID.randomUUID().toString();
	
	public InstanceCode() {

	}
}
