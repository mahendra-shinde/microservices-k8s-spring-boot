package com.mahendra.membersapi.models;

import java.io.Serializable;

import javax.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Table(name="members")
@Schema(description="Members Model object", name="Member")
public class Member implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="Member ID", name="id", type="Integer", example="101")
	@Column(name="member_id",scale = 5, precision = 0)
	private Integer id;

	@Column(name="first_name",length = 30)
	@Schema(description="First Name of member", name="firstName", type="String", example="Mahendra")
	private String firstName;

	@Column(name="last_name",length = 30)
	@Schema(description="Last Name of member", name="lastName", type="String", example="Shinde")
	private String lastName;

	@Column(name="member_status",length = 1)
	@Schema(description="Status of member ( 'D' => Defaulter, 'R' => Regular)", name="firstName", type="String", example="R")
	private char status;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Member( String firstName, String lastName, char status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
	}
	public Member() {
		super();
	}
	
}
