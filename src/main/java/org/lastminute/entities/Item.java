package org.lastminute.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Item
{
	//private static final DecimalFormat priceFormat = new DecimalFormat("#0.00");
	private Integer id;
	private String name;
	private BigDecimal price;
	private BigDecimal priceVatAdded;
	private ItemType itemType;
	private boolean imported;

	public static Item newInstance(Integer id, String name, BigDecimal price, ItemType itemType, boolean imported) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(name);
		Objects.requireNonNull(price);
		Objects.requireNonNull(itemType);

		return new Item(id, name, price, itemType, imported);
	}

	private Item(Integer id, String name, BigDecimal price, ItemType itemType, boolean imported)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.itemType = itemType;
		this.imported = imported;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public String getName()
	{
		return name;
	}

	public ItemType getItemType()
	{
		return itemType;
	}

	public Integer getId()
	{
		return id;
	}

	public BigDecimal getPriceVatAdded()
	{
		return priceVatAdded;
	}

	public void setPriceVatAdded(BigDecimal priceVatAdded)
	{
		Objects.requireNonNull(priceVatAdded);

		this.priceVatAdded = priceVatAdded;
	}

	@Override
	public int hashCode()
	{
		return id;
	}

	public boolean isImported()
	{
		return imported;
	}
}
