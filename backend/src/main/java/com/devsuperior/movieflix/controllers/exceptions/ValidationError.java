package com.devsuperior.movieflix.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> fieldMessages = new ArrayList<>();
	
	public List<FieldMessage> getFieldMessages() {
		return fieldMessages;
	}
	
	public void addFiledMessage(String fieldName, String fieldMessage) {
		fieldMessages.add(new FieldMessage(fieldName, fieldMessage));
	}
}
