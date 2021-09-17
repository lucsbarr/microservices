package br.com.telefonicatreinamento.productms.config.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	private String message = "";
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public FormErrorDto handle(MethodArgumentNotValidException exception) {
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			message = message.concat("[" + messageSource.getMessage(e, LocaleContextHolder.getLocale()) + "]");
		});
		int code = HttpStatus.BAD_REQUEST.value();
		FormErrorDto dto = new FormErrorDto(code,message);
		
		return dto;
	}

}
