package ua.nure.sigma.model;

import java.util.Date;

public class HistoryModel {
	private String userName = "";
	private String diagramName = "";
	private Date timeStart;
	private Date timeFinish;
	
	public HistoryModel() {
	}
	public HistoryModel(String userName, String diagramName, Date timeStart, Date timeFinish) {
		this.userName = userName;
		this.diagramName = diagramName;
		this.timeStart = timeStart;
		this.timeFinish = timeFinish;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDiagramName() {
		return diagramName;
	}
	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}
	public Date getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}
	public Date getTimeFinish() {
		return timeFinish;
	}
	public void setTimeFinish(Date timeFinish) {
		this.timeFinish = timeFinish;
	}
	
}
