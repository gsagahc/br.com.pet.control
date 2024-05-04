package br.com.pet.control.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	 private Date timestamp;
	 private String message;
	 private String details;

	public ExceptionResponse(Date timestamp, String message, String details) {
	this.timestamp = timestamp;
	this.message = message;
	this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public String getmessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
}