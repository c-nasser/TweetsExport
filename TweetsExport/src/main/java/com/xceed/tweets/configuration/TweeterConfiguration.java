package com.xceed.tweets.configuration;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import com.mongodb.MongoClient;

@org.springframework.context.annotation.Configuration
@ComponentScan({"com.xceed.tweets.controller", "com.xceed.tweets.service", "com.xceed.tweets.dao"} )
@PropertySource("classpath:tweeter.properties")
public class TweeterConfiguration {

	@Inject
	private Environment environment;

	@Bean
	public TwitterTemplate getTwitterTemplate() {
		
	    TwitterTemplate twitterTemplate = new TwitterTemplate(environment.getProperty("tweeter.consumerKey"),
	    													  environment.getProperty("tweeter.consumerSecret"),
	    													  environment.getProperty("tweeter.accessToken"),
	    													  environment.getProperty("tweeter.accessTokenSecret"));

	    return twitterTemplate;
	    
	}

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "tweeter");
	}
 
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
 
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
 
		return mongoTemplate;
 
	}

}
