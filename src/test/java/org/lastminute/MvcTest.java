package org.lastminute;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"items\":[{\"quantity\":1,\"name\":\"imported box of chocolates\",\"priceVatAdded\":10.50},{\"quantity\":1,\"name\":\"imported bottle of perfume\",\"priceVatAdded\":54.65}],\"totalTaxes\":7.65,\"total\":65.15}", true));
	}

	@Test
	public void htmlPageTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attribute("cart", null));
	}
}