package com.sop.ShoppingCenter.response;

import java.util.Date;

public class ResponseMessage {
	private Date timestamp;
	private int status;
	private Object payload;

	public ResponseMessage(Date timestamp, int status, Object payload) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.payload = payload;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
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
