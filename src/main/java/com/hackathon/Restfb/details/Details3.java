package com.hackathon.Restfb.details;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "numbers")

public class Details3 implements Serializable {
	private static final long serialVersionUID = -3009157732242241606L;


	@Column(name="number")
	public
	String num;
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	
	public Details3() {}
	
	public Details3(String x) {
		this.num = x;
		
	}

}
