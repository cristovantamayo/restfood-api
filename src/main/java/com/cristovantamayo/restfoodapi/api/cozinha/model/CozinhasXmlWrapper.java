package com.cristovantamayo.restfoodapi.api.cozinha.model;

import java.util.List;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "cozinhas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CozinhasXmlWrapper {
	
	@JsonProperty("cozinha")
	@JacksonXmlElementWrapper(useWrapping = false)
	@NonNull
	private List<Cozinha> cozinhas;
}
