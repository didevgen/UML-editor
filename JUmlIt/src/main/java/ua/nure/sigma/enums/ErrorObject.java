package ua.nure.sigma.enums;

public class ErrorObject {
	
	private int errorCode;
	
	private String errorMessage; 
	
	public ErrorObject(String message) {
		this.errorMessage = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
