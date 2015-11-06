package ua.nure.sigma.db_entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "collaborator")
public class Collaborator {
	private long userId;
	private long diagramId;

	public Collaborator() {

	}
	@Column(name = "user_id")
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


	@Column(name = "diagram_id")
	public long getDiagramId() {
		return diagramId;
	}

	public void setDiagramId(long id) {
		diagramId = id;
	}

}
