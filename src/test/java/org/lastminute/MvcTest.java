package org.lastminute;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lastminute.entities.Cart;
import org.lastminute.entities.Item;
import org.lastminute.entities.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CartApplication.class)
@WebAppConfiguration
public class MvcTest {
	@Autowired
	private WebApplicationContext webContext;

	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webContext)
				.build();
	}

	@Test
	public void restControllerTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/cart/1"))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":1,\"items\":[{\"quantity\":1,\"name\":\"imported box of chocolates\",\"priceVatAdded\":10.50},{\"quantity\":1,\"name\":\"imported bottle of perfume\",\"priceVatAdded\":54.65}],\"totalTaxes\":7.65,\"total\":65.15}", true));
	}

	@Test
	public void htmlModelTest() throws Exception
	{
		Cart testCart = Cart.newInstance(1);

		testCart.setTotal(new BigDecimal("65.15"));
		testCart.setTotalTaxes(new BigDecimal("7.65"));

		Item firstItem = Item.newInstance(3, "imported box of chocolates", new BigDecimal("10.00"), ItemType.FOOD, true);
		firstItem.setPriceVatAdded(new BigDecimal("10.50"));

		Item secondItem = Item.newInstance(4, "imported bottle of perfume", new BigDecimal("47.50"), ItemType.OTHER, true);
		secondItem.setPriceVatAdded(new BigDecimal("54.65"));

		testCart.addItem(firstItem);
		testCart.addItem(secondItem);

		mockMvc.perform(MockMvcRequestBuilders.get("/1"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("cart", testCart));
	}
}