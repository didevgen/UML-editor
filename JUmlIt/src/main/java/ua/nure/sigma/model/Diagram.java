package ua.nure.sigma.model;

import org.joda.time.DateTime;

import ua.nure.sigma.enums.Status;

public class Diagram {
	
	private long diagramId;
	
	private String jsonData;
	
	private DateTime creationDate;
	
	private DateTime lastUpdated;
	
	private User owner;
	
	private Status status;
	
	public Diagram() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (diagramId ^ (diagramId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diagram other = (Diagram) obj;
		if (diagramId != other.diagramId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Diagram [diagramId=" + diagramId + ", jsonData=" + jsonData + ", creationDate=" + creationDate
				+ ", lastUpdated=" + lastUpdated + ", owner=" + owner + "]";
	}

	public long getDiagramId() {
		return diagramId;
	}

	public void setDiagramId(long diagramId) {
		this.diagramId = diagramId;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public DateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(DateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
