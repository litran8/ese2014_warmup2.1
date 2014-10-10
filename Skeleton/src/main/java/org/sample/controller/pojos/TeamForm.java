package org.sample.controller.pojos;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Connected with jsp
 * 
 * @author Lina Tran
 */

public class TeamForm {

	private Long id;
	
	@NotNull
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+", 
    message = "Please enter a Team-Name before clicking \"Sign Up\"")
	private String teamName;
	
	private Date creationDate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName.trim();
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
		
}
