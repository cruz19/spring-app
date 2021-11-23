package cundi.edu.co.demo.exception;

import java.time.LocalTime;

import io.swagger.annotations.ApiModelProperty;

public class ExceptionWrapper<T> {
	@ApiModelProperty(value = "Timestamp", name= "timestamp", dataType = "localTime", example = "19:47:23.1082616")
	private LocalTime timestamp;
	
	@ApiModelProperty(value = "Status", name= "status", dataType = "int", example = "400")
	private int status;
	
	@ApiModelProperty(value = "Error", name= "error", dataType = "string", example = "400 BAD_REQUEST")
	private String error;
	
	@ApiModelProperty(value = "Message", name= "message", dataType = "string", example = "Mensaje de error")
	private T message;
	
	@ApiModelProperty(value = "Path", name= "path", dataType = "string", example = "uri=/{controller}/{method}")
	private String path;

	public ExceptionWrapper(int status, String error, T message, String path) {
		this.timestamp = LocalTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public LocalTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
