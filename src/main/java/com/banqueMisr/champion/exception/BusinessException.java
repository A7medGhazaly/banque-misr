package com.banqueMisr.champion.exception;

public class BusinessException  extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5415071350932792647L;
	/**
	 * 
	 */
	private String errorCode;
	private String errorDescription;
	public BusinessException() {
		super("DATA_N0T_FOUND");
		this.errorCode = "DATA_N0T_FOUND";
		this.errorDescription = "Requested data is not found";
	}

	
	public BusinessException(String errorCode, String errorDescription) {
		super(errorDescription);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	/**
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
		this.setErrorCode("404");
		this.setErrorDescription(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BusinessException(String message, Throwable cause, boolean enableSuppression,
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

