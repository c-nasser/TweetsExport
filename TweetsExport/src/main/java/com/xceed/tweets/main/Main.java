package com.xceed.tweets.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xceed.tweets.configuration.TweeterConfiguration;
import com.xceed.tweets.controller.RequestBeanTweeterInteract;
import com.xceed.tweets.domain.impl.TweetImpl;
import com.xceed.tweets.domain.impl.UserImpl;
import com.xceed.tweets.service.TweetService;

public class Main {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(TweeterConfiguration.class);
		
		RequestBeanTweeterInteract tweeterRequest = ctx.getBean(RequestBeanTweeterInteract.class);
		TweetService tweeterService = ctx.getBean(TweetService.class);
		
		
		// Request and Insert of 100 tweets on the tweeter database
		tweeterService.insertTweets(tweeterRequest.getTweets());
		
		// Statistics
		//Top 3 most popular tweet with medias
		List<TweetImpl> tweets = tweeterService.FindMostRetweetedTweetsWithMedia();
		System.out.println(">> Top 3 most popular tweets with media");
		
		for(TweetImpl tweet : tweets) {
			System.out.println(tweet);
		}		
	
		System.out.println(">> Most popular retweet");
		
		//Most popular retweet
		TweetImpl mostPopularTweet = tweeterService.findMostPopularRetweet();
		System.out.println(mostPopularTweet);
		

		
		//Top 3 most users followed
		System.out.println(">> Top 3 most users followed");
		List<UserImpl> users = tweeterService.FindMostFollowedUsers();

		for(UserImpl user : users) {
			System.out.println(user);
		}
		
		
	}

}
