package com.banqueMisr.champion.config;

import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.banqueMisr.champion.dto.ErrorResponseDto;
import com.banqueMisr.champion.exception.BusinessException;
import com.banqueMisr.champion.exception.DataNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	/**
	 * @param mnvex
	 * @return
	 */
	private String getErrorMessage(BindingResult bindingResult) {
		List<FieldError> errorFieldList = bindingResult.getFieldErrors();
		StringJoiner errorMsg = new StringJoiner(",");
		for (FieldError errorField : errorFieldList) {
			errorMsg.add(errorField.getDefaultMessage());
		}
		return errorMsg.toString();
	}

	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponseDto handleDataAccessException(DataAccessException daex, Locale locale) {
		ErrorResponseDto resp = new ErrorResponseDto("DataAccessException", daex.getLocalizedMessage());
		resp.setErrorDescription(daex.getMessage());
		return resp;

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException mnvex,
			Locale locale) {
		ErrorResponseDto resp = new ErrorResponseDto("INAVLID.INPUT.ERROR",
				getErrorMessage(mnvex.getBindingResult()));
		return resp;

	}
	/**
	 * 
	 * @param dnfex
	 * @return
	 */
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponseDto handleDataNotFoundException(DataNotFoundException dnfex, Locale locale) {
		ErrorResponseDto resp = new ErrorResponseDto(dnfex.getErrorCode(), dnfex.getMessage());
		resp.setErrorDescription(dnfex.getErrorDescription());
		return resp;
	}
	
	/**
	 * 
	 * @param dnfex
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ErrorResponseDto handleBusinessException(BusinessException dnfex, Locale locale) {
		ErrorResponseDto resp = new ErrorResponseDto(dnfex.getErrorCode(), dnfex.getMessage());
		resp.setErrorDescription(dnfex.getErrorDescription());
		return resp;
	}

}
