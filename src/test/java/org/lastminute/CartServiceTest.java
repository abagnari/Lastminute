package org.lastminute;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lastminute.entities.Cart;
import org.lastminute.entities.Item;
import org.lastminute.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CartApplication.class)
@WebAppConfiguration
public class CartServiceTest
{
	@Autowired
	private CartService cartService;

	@Test
	public void firstCartTest() {
		Cart cart = cartService.getCart(0);

		Assert.assertEquals(cart.getTotal(), new BigDecimal("29.83"));
		Assert.assertEquals(cart.getTotalTaxes(), new BigDecimal("1.50"));

		for(Item i : cart.getItems().keySet()) {
			switch(i.getName()) {
				case "book":
					Assert.assertEquals(i.getPriceVatAdded(), new BigDecimal("12.49"));
					break;
				case "music CD":
					Assert.assertEquals(i.getPriceVatAdded(), new BigDecimal("16.49"));
					break;
				case "chocolate bar":
					Assert.assertEquals(i.getPriceVatAdded(), new BigDecimal("0.85"));
					break;
			}
		}
	}

	@Test
	public void secondCartTest() {
		Cart cart = cartService.getCart(1);

		Assert.assertEquals(cart.getTotal(), new BigDecimal("65.15"));
		Assert.assertEquals(cart.getTotalTaxes(), new BigDecimal("7.65"));

		for(Item i : cart.getItems().keySet()) {
			switch(i.getName()) {
				case "imported box of chocolates":
					Assert.assertEquals(i.getPriceVatAdded(), new BigDecimal("10.50"));
					break;
				case "imported bottle of perfume":
					Assert.assertEquals(i.getPriceVatAdded(), new BigDecimal("54.65"));
					break;
			}
		}
	}

	@Test
	public void thirdCartTest() {
		Cart cart = cartService.getCart(2);

		Assert.assertEquals(cart.getTotal(), new BigDecimal("74.68"));
		Assert.assertEquals(cart.getTotalTaxes(), new BigDecimal("6.70"));

		for(Item i : cart.getItems().keySet()) {
			switch(i.getName()) {
				case "imported bottle of perfume":
					Assert.assertEquals(i.getPriceVatAdded(), new BigDecimal("32.19"));
					break;
				case "bottle of perfume":
					Assert.assertEquals(i.getPriceVatAdded(), new BigDecimal("20.89"));
					break;
				case "packet of headache pills":
					Assert.assertEquals(i.getPriceVatAdded(), new BigDecimal("9.75"));
					break;
				case "imported box of chocolates":
					Assert.assertEquals(i.getPriceVatAdded(), new BigDecimal("11.85"));
					break;
			}
		}
	}
}
