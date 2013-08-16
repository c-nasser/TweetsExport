package com.xceed.tweets.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;

/**
 * Allow interacting with Twitter
 * @author Nasser Chaabia
 */
@Controller
public class RequestBeanTweeterInteract {

	@Resource
	private TwitterTemplate twitterTemplate;

	/**
	 * @return Get 100 tweets
	 */
	public List<Tweet> getTweets() {

		List<Tweet> tweets = new ArrayList<>();

		tweets.addAll(getTwitterTemplate().timelineOperations().getUserTimeline("@pcinpact", 10));
		tweets.addAll(getTwitterTemplate().timelineOperations().getUserTimeline("@Emirates247", 20));
		tweets.addAll(getTwitterTemplate().timelineOperations().getUserTimeline("@firefox", 20));
		tweets.addAll(getTwitterTemplate().timelineOperations().getUserTimeline("@JournalDuGeek", 20));
		tweets.addAll(getTwitterTemplate().timelineOperations().getUserTimeline("@twandroid", 10));
		tweets.addAll(getTwitterTemplate().timelineOperations().getUserTimeline("@googlemaps", 10));
		tweets.addAll(getTwitterTemplate().timelineOperations().getUserTimeline("@gmail", 10));

		return tweets;
	}

	// Getter and setter
	public TwitterTemplate getTwitterTemplate() {
		return twitterTemplate;
	}

	public void setTwitterTemplate(TwitterTemplate twitterTemplate) {
		this.twitterTemplate = twitterTemplate;
	}

	

}
