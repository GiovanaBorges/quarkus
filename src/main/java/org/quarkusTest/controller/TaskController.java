package org.quarkusTest.controller;


import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import org.quarkusTest.model.Task;
import org.quarkusTest.repository.TaskResource;


@Path("/pessoa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {

    @Inject
    private TaskResource repository;
    
    @GET
    @Path("/peidinho")
    public String hello() {
        return "caguei na roupa";
    }  

    @GET
    public List<Task> listAll() {
        return Task.listAll();
    }

    @POST
    @Transactional
    public Response addTask(Task task) {
        Task taskEntity = repository.save(task);
        return Response.ok(task).status(Response.Status.CREATED).build();
    }  
}
