package com.appspot.gaejf.resources;

import java.net.URI;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

@Path("/topics")
public class TopicsResource {
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	private static final Logger log = Logger.getLogger(TopicsResource.class.getName());
	private static final ChannelService channelService;
	
	static {
		channelService = ChannelServiceFactory.getChannelService();
		channelService.createChannel("topics");
	}

	@Path("{topicId}")
	public TopicResource topicResource(@PathParam("{topicId}") String topicId) {
		return new TopicResource();
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getTopics() {
		log.severe("GET WAS CALLED AND LOGGED!");
		return Response.ok("{\"hello\":\"world\"}").build();
	}
	
	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	public Response createTopic() {
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI uri = ub.build("");

		log.severe("POST WAS CALLED AND LOGGED!");
		channelService.sendMessage(new ChannelMessage("topics", "MESSAGE BODYL LIKE"));
		
		return Response.created(uri).build();
	}
}
