package ua.nure.sigma.enums;

public class Status {
	
	private long statusId;
	
	private String description;

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public String getStatusType() {
		return description;
	}

	public void setStatusType(String statusType) {
		this.description = statusType;
	}

	@Override
	public String toString() {
		return "DiagramStatus [statusId=" + statusId + ", statusType=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (statusId ^ (statusId >>> 32));
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
		Status other = (Status) obj;
		if (statusId != other.statusId)
			return false;
		return true;
	}
	
	
}
