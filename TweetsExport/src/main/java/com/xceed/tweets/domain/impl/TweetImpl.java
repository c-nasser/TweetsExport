package com.xceed.tweets.domain.impl;

import java.util.Map;

import com.mongodb.BasicDBObject;

/**
 * A tweet
 * @author Nasser Chaabia
 */
public class TweetImpl extends BasicDBObject {

	private static final long serialVersionUID = 8400162636851417204L;

	public TweetImpl() {
		super();
	}
	
	public TweetImpl(Map<String, String> base) {
		super();
		this.putAll(base);
	}

	public String toString() {
		return "id : " + get("_id") + ", text : " + get("text") + ", fromUser : " +get("fromUser") + ", retweetCount : " + get("retweetCount"); 
	}

}
