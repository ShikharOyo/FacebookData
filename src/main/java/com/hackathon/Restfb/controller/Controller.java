package com.hackathon.Restfb.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hackathon.Restfb.details.Details;
import com.hackathon.Restfb.details.Details2;
import com.hackathon.Restfb.details.Details3;
import com.hackathon.Restfb.repository.FbRepo;
import com.hackathon.Restfb.repository.FbRepo2;
import com.hackathon.Restfb.repository.FbRepo3;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.json.JsonArray;
import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.restfb.types.User;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RestController
public class Controller {
	
	@Autowired
	private FbRepo repo;
	
	@Autowired
	private FbRepo2 repo2;
	
	@Autowired
	private FbRepo3 repo3;
	
	List<String> places = new ArrayList<>();
	
	public void createdata()
	{
		places.add("hsr");
		places.add("whiteField");
		places.add("kormangala");
		places.add("indiranagar");
	}
	
	@RequestMapping("/display")
	public void display() {
	FacebookClient facebookClient = new DefaultFacebookClient("EAAdxDjhAIPQBAG388fFBdozFBhcQbMkgysGbeEepNqH8uJ6jUMyftggdZAiptqtqokKH9KZAufJsc6ZCZAZCsUgKqZBoZAgvNKMyZA4F1z7kTRPGhFvNhMaZBOuZCYbyIAy7r1NLezIZBqXcFF9Y2I91Lt3UHbrmMkdMlVRwhlZCS0ZAmYYYH2ZByLxB1JfHDPDglPmHBnnf80SZCNDPAZDZD", Version.VERSION_2_10);
	
	User user = facebookClient.fetchObject("2147708062011647", User.class);
	
	

	System.out.println("User name: " + user.getName());
	System.out.println("User Id:"+ user.getId());
	System.out.println();
	System.out.println();
	System.out.println();
	
	Connection<Post> myFeed = facebookClient.fetchConnection("2147708062011647/feed", Post.class);
	
	
	
	for (List<Post> myFeedPage : myFeed) {
	  for (Post post : myFeedPage) {
	    
	    String x=post.getId().toString();
	    String x1=post.getMessage().toString();
	    
	    repo2.save(new Details2(x,x1));
	    
	    System.out.println("New post saved in datbase");
	    
	    
	    String postId = post.getId().toString();
			  Connection<Comment> commentConnection 
			     = facebookClient.fetchConnection(postId + "/comments", 
			           Comment.class);

			  int personalLimit = 50;

			  for (List<Comment> commentPage : commentConnection) {
			    for (Comment comment : commentPage) {
			      
			      personalLimit--;
			      
			      String y=comment.getId().toString();
			      String z=comment.getMessage().toString();
			      
			      repo.save(new Details(x,y,z));
			      
			      System.out.println("New comment saved in database with respective post id");
			      
			      // break both loops
			      if (personalLimit == 1) {
			         return;
			      }
			    }
			  }
			  			  
			  System.out.println();	  
	}
	}
	}
	
	@RequestMapping("/findallcomments")
	public List<String> findAll1()
	{	
		
		List <String> messageList = new ArrayList<>();
		List <String> postidList = new ArrayList<>();
		for(Details det : repo.findAll())
		{
			messageList.add(det.message.toString());
			postidList.add(det.postid.toString());
			
		}
		return messageList;
		
	}
	
	@RequestMapping("/findallposts")
	public List<String> findAll2()
	{	
		List <String> messageList = new ArrayList<>();
		List <String> postidList = new ArrayList<>();
		for(Details2 det : repo2.findAll())
		{
			messageList.add(det.message.toString());
			postidList.add(det.postid.toString());
			
		}
		
		return messageList;
		
	}
	
	@RequestMapping("/analysisofposts")
	public List<String> analysisofposts()
	{
		createdata();
		
		List <String> postidList = new ArrayList<>();
		for(Details2 det : repo2.findAll())
		{
			String z=det.message.toString();
			z.trim();
			int c=0;
			StringTokenizer st=new StringTokenizer(z);
			
			for(int i=0; st.hasMoreTokens();i++)
			{
				String x=st.nextToken().toLowerCase();
				if(x.compareTo("looking")==0)
				{
					System.out.println("looking found in post id" + det.postid.toString());
					c++;
				}
				
				else if(x.compareTo("flat")==0 || x.compareTo("pg")==0)
				{
					System.out.println("flat/pg found in post id" + det.postid.toString());
					c++;
				}
				
				else if(places.contains(x))
				{
					System.out.println("place found in post id" + det.postid.toString());
					c++;
				}
			}
			if(c>=3)
				postidList.add(det.postid.toString());
			
		}
		return postidList;
	}
	
	@RequestMapping("/analysisofcomments")
	public List<String> analysisofcomments()
	{
		createdata();
		
		List <String> postidList = new ArrayList<>();
		for(Details det : repo.findAll())
		{
			String z=det.message.toString();
			z.trim();
			int c=0;
			StringTokenizer st=new StringTokenizer(z);
			
			for(int i=0; st.hasMoreTokens();i++)
			{
				String x=st.nextToken().toLowerCase();
				if(x.compareTo("looking")==0)
				{
					System.out.println("looking found in post id" + det.postid.toString());
					c++;
				}
				
				else if(x.compareTo("flat")==0 || x.compareTo("pg")==0)
				{
					System.out.println("flat/pg found in post id" + det.postid.toString());
					c++;
				}
				
				else if(places.contains(x))
				{
					System.out.println("place found in post id" + det.postid.toString());
					c++;
				}
			}
			if(c>=3)
				postidList.add(det.postid.toString());
			
		}
		return postidList;
	}
	
	@RequestMapping("/getnumbers")
	public List<String> getnumbers()
	{
		List<String> numbers = new ArrayList<>();
		
		
		//numbers from posts
		for(Details2 det : repo2.findAll()) 
		{
			String z=det.message.toString();
			z.trim();
			StringTokenizer st=new StringTokenizer(z);
			
			for(int i=0; st.hasMoreTokens();i++)
			{
				String x=st.nextToken().toLowerCase();
				
				if(x.matches("\\d{10}"))
					repo3.save(new Details3(x));
				
				else if(x.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
					repo3.save(new Details3(x));
				
				else if(x.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
					repo3.save(new Details3(x));
				
				else if(x.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
					repo3.save(new Details3(x));
			}
		}
		
		//numbers from comments
		for(Details det : repo.findAll()) 
		{
			String z=det.message.toString();
			z.trim();
			StringTokenizer st=new StringTokenizer(z);
			
			for(int i=0; st.hasMoreTokens();i++)
			{
				String x=st.nextToken().toLowerCase();
				
				if(x.matches("\\d{10}"))
					repo3.save(new Details3(x));
				
				else if(x.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
					repo3.save(new Details3(x));
				
				else if(x.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
					repo3.save(new Details3(x));
				
				else if(x.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
					repo3.save(new Details3(x));
			}
		}
		return numbers;
	}

}
