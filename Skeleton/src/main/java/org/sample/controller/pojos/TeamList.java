package org.sample.controller.pojos;

import java.util.List;

import org.sample.model.Team;

public class TeamList {

	private List<Team> teamList;
	
	public TeamList() {
		
	}
	
	public List<Team> getTeamList() {
		return teamList;
	}
	
	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}
	
}
