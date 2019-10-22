package com.sop.ShoppingCenter.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ResponseMessage {
	private String timestamp;
	private int status;
	private Object payload;

	public ResponseMessage(int status, Object payload) {
		super();
		Date timestamp = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		this.timestamp = formatter.format(timestamp);
		this.status = status;
		this.payload = payload;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

}
