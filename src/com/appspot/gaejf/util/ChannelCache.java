package com.appspot.gaejf.util;

import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

public class ChannelCache {
	public static final Logger _log = Logger.getLogger(ChannelCache.class.getName());

	private static ChannelCache _instance;
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

	public String findInCache(String userKey) {
		if (cache.containsKey(userKey)) {
			return (String)cache.get(userKey);
		}
		else {
			return null;
		}
	}

	public void putInCache(String userKey, String token) {
		cache.put(userKey,token);
	}
	
	public Collection getTokens () {
		return cache.values();
	}
}
