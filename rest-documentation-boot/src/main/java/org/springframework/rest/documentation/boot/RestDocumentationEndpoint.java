/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.rest.documentation.boot;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.rest.documentation.model.Documentation;

@ConfigurationProperties(name = "endpoints.rest-documentation", ignoreUnknownFields = false)
public class RestDocumentationEndpoint extends AbstractEndpoint<Documentation> implements
		ApplicationContextAware {

	private RestDocumentationView restDocumentationView = new RestDocumentationView();

	public RestDocumentationEndpoint() {
		super("/rest-documentation");
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.restDocumentationView.setApplicationContext(context);
	}

	@Override
	public MediaType[] getProduces() {
		return new MediaType[] { MediaType.APPLICATION_JSON };
	}

	@Override
	public Documentation invoke() {
		return this.restDocumentationView.getSnapshot();
	}
}
