package com.xceed.tweets.domain.impl;

import java.util.Map;

import com.mongodb.BasicDBObject;


/**
 * A user
 * @author Nasser Chaabia
 */
public class UserImpl extends BasicDBObject {

	private static final long serialVersionUID = 8993301946035741328L;

	public UserImpl() {
		super();
	}
	
	public UserImpl(Map<String, ?> base) {
		super();
		this.putAll(base);
	}

}
