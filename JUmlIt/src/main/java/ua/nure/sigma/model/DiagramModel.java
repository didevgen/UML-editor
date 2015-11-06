package ua.nure.sigma.model;

import java.util.ArrayList;
import java.util.List;

import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.User;

public class DiagramModel {

	private Diagram diagram;
	
	private User owner;
	
	private List<User> collaborators = new ArrayList<User>();
	
	public DiagramModel() {
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<User> collaborators) {
		this.collaborators = collaborators;
	}

}
