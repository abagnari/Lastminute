package org.lastminute.controller;

import org.lastminute.entities.Cart;
import org.lastminute.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//@RequestMapping(path="/")
@Controller
public class CartControllerImpl implements ErrorController, CartController
{
	private final CartServiceImpl cartService;

	@Autowired
	public CartControllerImpl(CartServiceImpl cartService)
	{
		this.cartService = cartService;
	}

	/*@GetMapping(path = "/")
	public ResponseEntity<String> getGreeting()
	{
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}*/

	@Override
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@Override
	@GetMapping(path = "/{id:[\\d]+}")
	public String getCartPage(@PathVariable(name = "id") Integer id, Model model) {

		Cart cart = cartService.getCart(id);

		model.addAttribute("cart", cart);

		return "index";
	}

	@Override
	@RequestMapping(path = "/error")
	@ResponseBody
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
