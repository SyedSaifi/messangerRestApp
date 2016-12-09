package org.example.jersey.messangerRestApp.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6599182443819529093L;
	
	public DataNotFoundException(String str){
		super(str);
	}

}
