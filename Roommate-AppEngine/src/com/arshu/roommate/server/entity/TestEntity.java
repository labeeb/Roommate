package com.arshu.roommate.server.entity;


import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Subclass;
import com.googlecode.objectify.ObjectifyService;


@Entity
public class TestEntity {
	  @Id Long id;
	    String name;

	   public static void test(){
		   ObjectifyService ofy = new ObjectifyService();
		  // ((Object) ofy).save().entity(annie).now();
	   }
}
