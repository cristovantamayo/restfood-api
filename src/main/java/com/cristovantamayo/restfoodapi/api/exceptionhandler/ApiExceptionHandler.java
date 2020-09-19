package com.cristovantamayo.restfoodapi.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cristovantamayo.restfoodapi.exception.EntidadeEmUsoException;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;
import com.cristovantamayo.restfoodapi.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof InvalidFormatException)
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		
		if(rootCause instanceof IgnoredPropertyException)
			return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, headers, status, request);
		
		if(rootCause instanceof UnrecognizedPropertyException)
			return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request);
		
		
		ProblemType problemType = ProblemType.REQUISICAO_INCOMPREENSSIVEL;
		String detail = "O corpo da requisição contém erro(s) de sintaxe.";
		
		Problem problem = 
				createProblemBuilder(status, problemType, detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.REQUISICAO_INCOMPREENSSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija com dados do tipo '%s' e tente novamente.",
				path,
				ex.getValue(),
				ex.getTargetType().getSimpleName());
		
		Problem problem =  
				createProblemBuilder(status, problemType, detail)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.REQUISICAO_INCOMPREENSSIVEL;
		String detail = String.format("Não é permitido atribuir a propriedade '%s' através deste recurso. Tente com as propriedades '%s'.",
				path,
				ex.getKnownPropertyIds());
		
		Problem problem =  
				createProblemBuilder(status, problemType, detail)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.REQUISICAO_INCOMPREENSSIVEL;
		String detail = String.format("A propriedade '%s' não é válida para este recurso. Tente as propriedades '%s'.",
				path,
				ex.getKnownPropertyIds());
		
		Problem problem =  
				createProblemBuilder(status, problemType, detail)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	private String joinPath(List<Reference> references) {
	    return references.stream()
	        .map(ref -> ref.getFieldName())
	        .collect(Collectors.joining("."));
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		
		Problem problem = 
				createProblemBuilder(status, problemType, detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){

		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
		Problem problem = 
				createProblemBuilder(status, problemType, detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request){

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.SOLICITACAO_IMPROPRIA;
		String detail = ex.getMessage();
		
		Problem problem = 
				createProblemBuilder(status, problemType, detail)
				.build();
		
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if(body == null) {
			body = Problem.builder()
					.title(status.getReasonPhrase())
					.status(status.value())
					.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.title((String) body)
					.status(status.value())
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail){
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
	}

}
