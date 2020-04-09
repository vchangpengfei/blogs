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
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

class CsvProvider implements TestTemplateInvocationContextProvider {

	/**
	 * 找自定义注解
	 * @param context
	 * @return
	 */
	@Override
	public boolean supportsTestTemplate(ExtensionContext context) {
		return findAnnotation(context.getTestMethod(), CsvTest.class).isPresent();
	}

	/**
	 * 测试执行次数
	 * @param context
	 * @return
	 */
	@Override
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		String[] sets = computeSets(context.getRequiredTestMethod());

		List<TestTemplateInvocationContext> list=new ArrayList<>();
		for(String s:sets){
			list.add(new CsvContext(s));
		}
		return list.stream();
	}

	private String[] computeSets(Method testMethod) {
		String[] value = findAnnotation(testMethod, CsvTest.class)
				.orElseThrow(() -> new AssertionError("@CsvTest not found"))
				.value();
		return value;
	}





}
