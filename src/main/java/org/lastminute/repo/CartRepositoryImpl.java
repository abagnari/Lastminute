package org.lastminute.repo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.lastminute.entities.Cart;
import org.lastminute.entities.Item;
import org.lastminute.entities.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Repository
public class CartRepositoryImpl implements CartRepository
{
	private final Logger log = LogManager.getLogManager().getLogger(CartRepositoryImpl.class.getName());
	private final Map<Integer, Item> mockItemsTable = new HashMap<>();
	private final Map<Integer, Cart> mockCartsTable = new HashMap<>();

	@Autowired
	public CartRepositoryImpl()
	{
	}

	@PostConstruct
	private void init() {
		loadItems();
		loadCarts();
	}

	private void loadItems()
	{
		URL csvItemsDataPath = this.getClass().getClassLoader().getResource("items.csv");

		if(csvItemsDataPath != null) {
			try (Reader in = new FileReader(csvItemsDataPath.getFile())) {
				Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
				for (CSVRecord record : records) {
					Integer id = Integer.valueOf(record.get("id"));
					String name = record.get("name");
					BigDecimal price = new BigDecimal(record.get("price"));
					ItemType itemType = ItemType.fromString(record.get("itemType"));
					boolean imported = Boolean.parseBoolean(record.get("imported"));

					mockItemsTable.put(id, Item.newInstance(id, name, price, itemType, imported));
				}
			}
			catch (IOException e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		else {
			log.log(Level.SEVERE, "No items csv data file found.");
		}
	}

	private void loadCarts()
	{
		URL csvCartsDataPath = this.getClass().getClassLoader().getResource("carts.csv");

		if(csvCartsDataPath != null) {
			try (Reader in = new FileReader(csvCartsDataPath.getFile())) {
				Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
				for (CSVRecord record : records) {
					Integer id = Integer.valueOf(record.get("id"));
					String items = record.get("items");

					Cart cart = Cart.newInstance(id);

					for(String itemId : items.split(",")) {
						cart.addItem(
								Optional.ofNullable(mockItemsTable.get(Integer.valueOf(itemId))).orElseThrow(() ->
									new IllegalArgumentException(String.format("No Item exists having id %s", itemId))));
					}

					mockCartsTable.put(id, cart);
				}
			}
			catch (IOException e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		else {
			log.log(Level.SEVERE, "No carts csv data file found.");
		}
	}

	@Override
	public Cart getCart(Integer id)	{
		return mockCartsTable.get(id);
	}
}
