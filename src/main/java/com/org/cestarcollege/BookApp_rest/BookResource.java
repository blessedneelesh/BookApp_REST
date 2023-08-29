package com.org.cestarcollege.BookApp_rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cestar.model.Book;
import com.cestar.dao.*;

@Path("book")
public class BookResource {

	BookDao obj=new BookDao();
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> showAllEmps(){
		List<Book> emps=new ArrayList<>();
		
		emps=obj.SelectAllBook();
		return emps;
	}
	
	@Path("insert")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public int addEmps(Book book_from_client) {
		return obj.insertRec(book_from_client);
		// call display rec after inserting and return latest displayrecs()
	}
	
	@Path("book/{eid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Book getEmpById(@PathParam("eid") String id) {
		Book emp= obj.getBookById(id);
		return emp;
	}
	
	@Path("delete/{eid}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	
	public int deleteEmp(@PathParam("eid") String id) {
		return obj.delete(id);
	}
	
	@Path("update/{e_id}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public int updateEmp(@PathParam("e_id") String oid, Book book_from_client) {
		return obj.updateBook(oid, book_from_client);
	}
	
}
