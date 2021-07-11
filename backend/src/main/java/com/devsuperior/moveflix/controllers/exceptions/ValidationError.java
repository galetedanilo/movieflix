package com.devsuperior.moveflix.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errorList = new ArrayList<>();
	
	public List<FieldMessage> getErrorList() {
		return errorList;
	}
	
	public void addErrorInList(String fieldName, String message) {
		errorList.add(new FieldMessage(fieldName, message));
	}

}
