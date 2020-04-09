/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package cha.pao.fan.blogs.extentions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

class CsvResolver implements ParameterResolver {

	private final String[] parameters;

	CsvResolver(String parameters[]) {
		this.parameters = parameters;
	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
//		return parameterContext.getIndex() < parameters.size();
		return true;
	}

	/**
	 * 对测试的方法签名中参数赋值
	 * @param parameterContext
	 * @param extensionContext
	 * @return
	 */
	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return parameters;
	}

}
