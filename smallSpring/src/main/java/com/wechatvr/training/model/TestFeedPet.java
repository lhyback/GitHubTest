package com.wechatvr.training.model;

import org.junit.Test;

import com.wechatvr.training.mySpring.ClassPathXmlApplicationContext;

public class TestFeedPet {
	
	 //@Test
	 public void testFeedPetJava() {
    	    People people = new People();
    	    Animal pet = new Dog();
    	    people.setPet(pet);
    	    people.feedPet();
    }
    
	 @Test
	 public void testFeedPetXml() throws Exception {
		 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
		 People people = (People) ctx.getBean("people");
		 //更好的效果是要让xml中的startup_method起作用，自动调用feedPet方法
		 //people.feedPet();
		 
	 }
	 
	 
}
