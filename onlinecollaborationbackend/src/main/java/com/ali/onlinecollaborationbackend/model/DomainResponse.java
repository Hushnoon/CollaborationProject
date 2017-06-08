package com.ali.onlinecollaborationbackend.model;

public class DomainResponse {

	
	private int responseCode;
	private String responseMessage;
	public DomainResponse(String responseMessage,int responseCode) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
