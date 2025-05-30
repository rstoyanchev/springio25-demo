/*
 * Copyright 2002-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;


@ImportHttpServices(group = "github", basePackages = "demo.github")
@ImportHttpServices(group = "stackoverflow", basePackages = "demo.stackoverflow")
@Configuration
public class DemoConfig {

	@Bean
	RestClientHttpServiceGroupConfigurer groupConfigurer() {
		return groups -> {

			groups.filterByName("github")
					.forEachClient((group, builder) -> builder
							.baseUrl("https://api.github.com")
							.defaultHeader("Accept", "application/vnd.github.v3+json"));

			groups.filterByName("stackoverflow")
					.forEachClient((group, builder) -> builder
							.baseUrl("https://api.stackexchange.com?site=stackoverflow"));
		};
	}

}
