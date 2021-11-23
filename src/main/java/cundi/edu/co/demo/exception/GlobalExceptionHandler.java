package cundi.edu.co.demo.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
	    ex.getGlobalErrors().forEach(err -> errors.put(err.getObjectName(), err.getDefaultMessage()));

		ExceptionWrapper<?> ew = new ExceptionWrapper<Map<String, String>>(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.toString(),
				errors,
				request.getDescription(false)
			);

	    return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> myHandleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.toString(),
				ex.getMessage(),
				request.getDescription(false)
			);

	    return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<Object> myHandleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.toString(),
				ex.getMessage(),
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(ArithmeticException.class)
	public final ResponseEntity<Object> myHandleArithmeticException(ArithmeticException ex, WebRequest request) {
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				"Ha ocurrido un error",
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> myHandleException(Exception ex, WebRequest request) {
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				"Ha ocurrido un error",
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(),
				ex.getMessage(),
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.METHOD_NOT_ALLOWED.value(),
				HttpStatus.METHOD_NOT_ALLOWED.toString(),
				ex.getMessage(),
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.toString(),
				"JSON Mal formado",
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.toString(),
				ex.getMessage(),
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConflictException.class)
	public final ResponseEntity<Object> myHandleConflictException(ConflictException ex,
			WebRequest request){
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.toString(),
				ex.getMessage(),
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.CONFLICT);
	}		
	
	@ExceptionHandler(ArgumentRequiredException.class)
	public final ResponseEntity<Object> myHandleArgumentRequiredException(ArgumentRequiredException ex,
			WebRequest request){
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.toString(),
				ex.getMessage(),
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<Object> myHandleAccessDeniedException(AccessDeniedException ex,
			WebRequest request){
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.toString(),
				ex.getMessage(),
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(InsufficientAuthenticationException.class)
	public final ResponseEntity<Object> myHandleInsufficientAuthenticationException(InsufficientAuthenticationException ex,
			WebRequest request){
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.toString(),
				ex.getMessage(),
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public final ResponseEntity<Object> myHandleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex,
			WebRequest request){
		ExceptionWrapper<?> ew = new ExceptionWrapper<String>(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.toString(),
				ex.getMessage(),
				request.getDescription(false)
			);
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}
	
}
