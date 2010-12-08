package com.appspot.gaejf.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

public class ChannelCache {
	public static final Logger _log = Logger.getLogger(ChannelCache.class.getName());

	private static ChannelCache _instance;
	private static final String ACTIVE_USERS = "activeUsers";
	private Cache cache;

	private ChannelCache() {
		try {
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = cacheFactory.createCache(Collections.emptyMap());
		}
		catch (CacheException e) {
			//Log stuff
			_log.log(Level.WARNING, "Error in creating the Cache");
		}
	}

	public static synchronized ChannelCache getInstance() {
		if (_instance==null) {
			_instance = new ChannelCache();
		}
		return _instance;
	}

	@SuppressWarnings("unchecked")
	public void putInCache(String userKey) {
		Set <String> users = ((cache.containsKey(ACTIVE_USERS)) ? (HashSet<String>)cache.get(ACTIVE_USERS) :
			new HashSet<String> ());
		users.add(userKey);

		cache.put(ACTIVE_USERS,users);
	}
	
	@SuppressWarnings("unchecked")
	public Set<String> getActiveUsers() {
		if (cache.containsKey(ACTIVE_USERS)) {
			return (Set<String>)cache.get(ACTIVE_USERS);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void deleteFromCache(String userKey) {
		Set <String> users = ((cache.containsKey(ACTIVE_USERS)) ? (HashSet<String>)cache.get(ACTIVE_USERS) :
			new HashSet<String> ());
		int index = 0;

		Iterator <String> itr = users.iterator();
		boolean found = false;
		while(itr.hasNext()) {
			if (itr.next().equalsIgnoreCase(userKey)) {
				itr.remove();
				found = true;
			}
		}
		
		if (found) {
			users.remove(index);
			cache.put(ACTIVE_USERS,users);
		}
	}
}
