package org.lastminute.controller;

import org.lastminute.dto.CartDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

public interface RestCartController
{
	@GetMapping(path = "/{id:[\\d]+}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<CartDto> getCart(@PathVariable(name = "id") Integer id);

	@RequestMapping(path = "/error")
	String getError(HttpServletRequest request);

	String getErrorPath();
}
