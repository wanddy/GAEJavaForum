package com.appspot.gaejf.resources;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.appspot.gaejf.util.ChannelCache;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

@Path ("/channel")
public class ChannelResource {
	private static final Logger log = Logger.getLogger(ChannelResource.class.getName());
	private static final ChannelService channelService;
	private static final ChannelCache cache;
	
	static {
		channelService = ChannelServiceFactory.getChannelService();
		cache = ChannelCache.getInstance();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChannel(){
		String userKey = "fakemeoutkey";
		String token = channelService.createChannel(userKey);
		log.severe("STORING TOKEN: " + token);
		//cache.putInCache(userKey);
		log.severe("STORED TOKEN: " + token);
		
		return Response.ok("{\"token\":\"" + token + "\"}").build();
	}
}
