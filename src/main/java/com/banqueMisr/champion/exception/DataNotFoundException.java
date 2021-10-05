package com.banqueMisr.champion.exception;

public class DataNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 153969113059457470L;
	private String errorCode;
	private String errorDescription;
	public DataNotFoundException() {
		super("DATA_N0T_FOUND");
		this.errorCode = "DATA_N0T_FOUND";
		this.errorDescription = "Requested data is not found";
	}

	
	public DataNotFoundException(String errorCode, String errorDescription) {
		super(errorDescription);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	/**
	 * @param message
	 */
	public DataNotFoundException(String message) {
		super(message);
		this.setErrorCode("404");
		this.setErrorDescription(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DataNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}
