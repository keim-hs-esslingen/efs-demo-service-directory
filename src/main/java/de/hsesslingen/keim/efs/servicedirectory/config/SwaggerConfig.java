/*
 * MIT License
 * 
 * Copyright (c) 2020 Hochschule Esslingen
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE. 
 */
package de.hsesslingen.keim.efs.servicedirectory.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class for Swagger Api documentation. Defines a Docket with api details, tags etc.
 * @author k.sivarasah
 * 18 Sep 2019
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String API_KEY_DESC = "Api key for authentication";
	public static final String API_KEY_HEADER_NAME = "x-api-key";
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("de.hsesslingen.keim.efs.servicedirectory.controller"))
				.paths(PathSelectors.any())
				.build()
				.tags(new Tag("Search Api", "Search for available services using filter criteria"), 
						new Tag("Service Api", "Service related API with CRUD functionalities"))
				.apiInfo(apiInfo())
				.globalOperationParameters(Arrays.asList(new ParameterBuilder()
						.name(API_KEY_HEADER_NAME)
						.description(API_KEY_DESC)
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.required(false).build()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Service Directory",
				"API description of Service Directory", "V0.1", null,
				new Contact("Hochschule Esslingen", "https://www.hs-esslingen.de", null), 
				null, null, Collections.emptyList());
	}

}