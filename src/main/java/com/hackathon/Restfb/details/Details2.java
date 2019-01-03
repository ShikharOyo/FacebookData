package com.hackathon.Restfb.details;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Details2 implements Serializable {
	
	private static final long serialVersionUID = -3009157732242241606L;


	@Column(name="postid")
	public
	String postid;
	
	
	@Column(name="message")
	public
	String message;
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	
	public Details2() {}
	
	public Details2(String x, String z) {
		this.postid = x;
		this.message=z;
	}
	

}
