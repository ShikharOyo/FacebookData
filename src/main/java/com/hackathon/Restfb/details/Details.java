package com.hackathon.Restfb.details;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Details implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3009157732242241606L;


	@Column(name="postid")
	public
	String postid;
	
	@Column(name="commentid")
	public
	String commentid;
	
	@Column(name="message")
	public
	String message;
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	
	public Details() {}
	
	public Details(String x, String y, String z) {
		this.postid = x;
		this.commentid = y;
		this.message=z;
	}
}

