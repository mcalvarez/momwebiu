package org.chinpon.mom.web;

import org.chinpon.mom.rest.MOMGameResource;
import org.chinpon.mom.rest.PlayerResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

public class MOMApplication extends ResourceConfig {
	public MOMApplication() {
		// register application resources
		//register(PeopleResource.class);
		register(PlayerResource.class);
		register(MOMGameResource.class);
		
		// register filters
		// which is a Spring filter that provides a bridge between JAX-RS and Spring request attributes
		register(RequestContextFilter.class);
		
		// register features
		// which is a feature that registers Jackson JSON providers � you need it for the application to understand JSON data
		register(JacksonFeature.class);
		
		//register(CORSResponseFilter.class);
	}
}