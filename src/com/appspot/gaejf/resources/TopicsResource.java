package com.appspot.gaejf.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/topics")
public class TopicsResource {

	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getTopics() {
		return Response.ok("{\"hello\":\"world\"}").build();
	}
}
