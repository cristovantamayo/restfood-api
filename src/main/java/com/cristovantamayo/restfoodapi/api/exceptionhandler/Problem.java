package com.cristovantamayo.restfoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problem {
	private final LocalDateTime dateTime;
	private final String message;
}
