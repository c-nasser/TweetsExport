package com.xceed.tweets.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Repository;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.xceed.tweets.dao.TweetDao;
import com.xceed.tweets.domain.impl.TweetImpl;
import com.xceed.tweets.domain.impl.UserImpl;

/**
 * Tweets Dao class
 * @author Nasser Chaabia
 */
@Repository
public class TweetDaoImpl implements TweetDao {

	@Resource
	MongoOperations mongoOperation;

	/**{@inheritDoc}*/
	public void insertTweets(final List<Tweet> tweets) {
		mongoOperation.insert(tweets, Tweet.class);
	}

	/**{@inheritDoc}*/
	public void deleteTweets() {
		mongoOperation.dropCollection(Tweet.class);
	}

	/**{@inheritDoc}*/
	public List<TweetImpl> FindMostRetweetedTweetsWithMedia() {
		
		BasicDBObject retweetsQuery = new BasicDBObject();
		retweetsQuery.put("entities.media", new BasicDBObject("$exists", true));
		
		mongoOperation.getCollection("tweet").setObjectClass(TweetImpl.class);
		DBCursor cursorMostPopularRetweet = mongoOperation.getCollection("tweet").find(retweetsQuery)
																				 .sort(new BasicDBObject("retweetCount", -1))
																				 .limit(3);
		
		List<TweetImpl> tweets = new ArrayList<TweetImpl>();
		
		while (cursorMostPopularRetweet.hasNext()) {
			tweets.add((TweetImpl)cursorMostPopularRetweet.next());
		} 
		
		return tweets;
		
	}

	/**{@inheritDoc}*/
	public TweetImpl findMostPopularRetweet() {
		
		BasicDBObject retweetsQuery = new BasicDBObject();
		retweetsQuery.put("retweetedStatus", new BasicDBObject("$exists", true));
		
		mongoOperation.getCollection("tweet").setObjectClass(TweetImpl.class);
		DBCursor cursorMostPopularRetweet = mongoOperation.getCollection("tweet").find(retweetsQuery)
																				 .sort(new BasicDBObject("retweetCount", -1))
																				 .limit(1);
		
		TweetImpl tweet;
		
		if (cursorMostPopularRetweet.hasNext()) {
			tweet = (TweetImpl)cursorMostPopularRetweet.next();
		} else {
			tweet = null;
		}
		
		return tweet;
		
	}

	/**{@inheritDoc}*/
	@SuppressWarnings("unchecked")
	public List<UserImpl> FindMostFollowedUsers() {
		
		BasicDBObject fields = new BasicDBObject("user._id", 1);
		fields.put("user.name", 1);
		fields.put("user.followersCount", 1);
		
		DBObject project = new BasicDBObject("$project", fields);
		
		DBObject groupFields = new BasicDBObject( "_id", "$user._id");
		groupFields.put("followersCount", new BasicDBObject( "$first", "$user.followersCount"));
		groupFields.put("name", new BasicDBObject( "$first", "$user.name"));
		
		DBObject group = new BasicDBObject("$group", groupFields);
		DBObject sort = new BasicDBObject("$sort", new BasicDBObject("followersCount", -1));
		DBObject limit = new BasicDBObject("$limit", 3);
		
		AggregationOutput mostFollowedUsers = mongoOperation.getCollection("tweet").aggregate(project, group, sort, limit);
		
		List<UserImpl> users = new ArrayList<UserImpl>();
		for (Iterator<DBObject> it = mostFollowedUsers.results().iterator(); it.hasNext();) { 
			users.add(new UserImpl(it.next().toMap()));
	    }
		
		return users;
		
	}

	// Getter and setter
	public MongoOperations getMongoOperation() {
		return mongoOperation;
	}

	public void setMongoOperation(final MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}


}
