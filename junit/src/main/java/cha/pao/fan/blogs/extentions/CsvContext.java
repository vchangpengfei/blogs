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

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

import java.util.Collections;
import java.util.List;

class CsvContext implements TestTemplateInvocationContext {

	private String[] parameters;


	CsvContext(String parameters) {
		String[]_parameters = parameters.split(",");
		this.parameters=new String[_parameters.length];
		for(int i=0;i<_parameters.length;i++){
			this.parameters[i]=_parameters[i].trim();
		}
	}

	@Override
	public String getDisplayName(int invocationIndex) {
		return "验证场景:"+parameters[0];
	}

	@Override
	public List<Extension> getAdditionalExtensions() {
		return Collections.singletonList(new CsvResolver(parameters));
	}

}
