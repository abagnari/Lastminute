package org.lastminute.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public interface CartController
{
	@RequestMapping("/")
	String index();

	@GetMapping(path = "/{id:[\\d]+}")
	String getCartPage(@PathVariable(name = "id") Integer id, Model model);

	@RequestMapping(path = "/error")
	@ResponseBody
	String getError(HttpServletRequest request);

	String getErrorPath();
}
