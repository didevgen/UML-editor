package ua.nure.sigma.model;

import java.util.ArrayList;
import java.util.List;

import ua.nure.sigma.db_entities.Diagram;

public class UserDetails {
	
	private List<Diagram> ownDiagrams = new ArrayList<Diagram>();
	
	private List<Diagram> collabDiagrams = new ArrayList<Diagram>();

	public List<Diagram> getOwnDiagrams() {
		return ownDiagrams;
	}

	public void setOwnDiagrams(List<Diagram> ownDiagrams) {
		this.ownDiagrams = ownDiagrams;
	}

	public List<Diagram> getCollabDiagrams() {
		return collabDiagrams;
	}

	public void setCollabDiagrams(List<Diagram> collabDiagrams) {
		this.collabDiagrams = collabDiagrams;
	}
	
	
}
