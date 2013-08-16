package com.xceed.tweets.dao;

import java.util.List;

import org.springframework.social.twitter.api.Tweet;

import com.xceed.tweets.domain.impl.TweetImpl;
import com.xceed.tweets.domain.impl.UserImpl;

/**
 * Tweets Dao class
 * @author Nasser Chaabia
 */
public interface TweetDao {

	/**
	 * Insert a list of tweets in a collection
	 * @param tweets : list of tweets
	 */
	public void insertTweets(List<Tweet> tweets);

	/**
	 * Delete all tweets
	 */
	public void deleteTweets();

	/**
	 * @return Top 3 most retweeted with media
	 */
	public List<TweetImpl> FindMostRetweetedTweetsWithMedia();

	/**
	 * @return Find the retweet with most count of retweets
	 */
	public TweetImpl findMostPopularRetweet();


	/**
	 * @return Top 3 users most followed
	 */
	public List<UserImpl> FindMostFollowedUsers();
	
}
