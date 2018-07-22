package org.lastminute.controller;

import org.lastminute.converter.CartConverter;
import org.lastminute.dto.CartDto;
import org.lastminute.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(path="/cart")
@RestController
public class RestCartControllerImpl implements ErrorController, RestCartController
{
	private final CartConverter cartConverter;
	private final CartServiceImpl cartService;

	@Autowired
	public RestCartControllerImpl(CartConverter cartConverter, CartServiceImpl cartService)
	{
		this.cartConverter = cartConverter;
		this.cartService = cartService;
	}

	@Override
	@GetMapping(path = "/{id:[\\d]+}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CartDto> getCart(@PathVariable(name = "id") Integer id) {

		return new ResponseEntity<>(cartConverter.convert(cartService.getCart(id)), HttpStatus.OK);
	}

	@Override
	@RequestMapping(path = "/error")
	public String getError(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

		return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div><div><b>Sorry</b></div></body></html>",
							 statusCode);
	}

	@Override
	public String getErrorPath() {
		return "/cart/error";
	}
}
