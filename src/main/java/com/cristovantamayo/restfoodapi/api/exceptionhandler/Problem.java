package com.cristovantamayo.restfoodapi.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	private final Integer status;
	private final LocalDateTime timestamp;
	private final String type;
	private final String title;
	private final String detail;
	private final String userMessage;
	private final List<Field> fields;
	
	@Builder
	@Getter
	public static class Field {
		private String nome;
		private String userMessage;
	}

}
