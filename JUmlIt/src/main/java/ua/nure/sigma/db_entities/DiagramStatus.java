package ua.nure.sigma.db_entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "diagram_status")
public class DiagramStatus {
	private long statusId;
	private String status_type;

	public DiagramStatus() {
		status_type = null;
	}

	public DiagramStatus(DiagramStatus st_type) {
		status_type = st_type.getStatusType();
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "status_id")
	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long id) {
		statusId = id;
	}

	@Column(name = "status_type", length = 256)
	public String getStatusType() {
		return status_type;
	}

	public void setStatusType(String st) {
		status_type = st;
	}
}
