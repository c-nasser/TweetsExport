package com.xceed.tweets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import com.xceed.tweets.dao.TweetDao;
import com.xceed.tweets.domain.impl.TweetImpl;
import com.xceed.tweets.domain.impl.UserImpl;
import com.xceed.tweets.service.TweetService;

/**
 * Tweets service class
 * @author Nasser Chaabia
 */
@Service
public class TweetServiceImpl implements TweetService {

	@Resource
	private TweetDao tweeterDao;

	/**{@inheritDoc}*/
	public void insertTweets(List<Tweet> tweets) {
		
		getTweeterDao().deleteTweets();
		getTweeterDao().insertTweets(tweets);
		
	}

	/**{@inheritDoc}*/
	public List<TweetImpl> FindMostRetweetedTweetsWithMedia() {
		return getTweeterDao().FindMostRetweetedTweetsWithMedia();
	}

	/**{@inheritDoc}*/
	public TweetImpl findMostPopularRetweet() {
		return getTweeterDao().findMostPopularRetweet();
	}

	/**{@inheritDoc}*/
	public List<UserImpl> FindMostFollowedUsers() {
		return getTweeterDao().FindMostFollowedUsers();
	}
	
	// Getter and setter
	public TweetDao getTweeterDao() {
		return tweeterDao;
	}

	public void setTweeterDao(TweetDao tweeterDao) {
		this.tweeterDao = tweeterDao;
	}



}
