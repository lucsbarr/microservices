package br.com.telefonicatreinamento.productms.config.validacao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormErrorDto {

	@JsonProperty("status_code")
	private int statusCode;
	@JsonProperty("message")
	private String message;

	public FormErrorDto(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}

}
