package org.quarkusTest.controller;


import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.quarkusTest.model.Task;


@Path("/task")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> listAll() {
        return Task.listAll();
    }

    @POST
    @Path("post/add/task")
    @Transactional
    public Response addTask(@RequestBody Task task) {
        task.persist();
        return Response.ok(task).status(Response.Status.CREATED).build();
    }  
}
