package org.lastminute.entities;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cart
{
	private Integer id;
	private Map<Item, Integer> items = new HashMap<>();
	private BigDecimal totalTaxes;
	private BigDecimal total;

	public static Cart newInstance(Integer id) {
		Objects.requireNonNull(id);

		return new Cart(id);
	}

	private Cart(Integer id)
	{
		this.id = id;
	}

	public Map<Item, Integer> getItems()
	{
		return Collections.unmodifiableMap(items);
	}

	public void addItem(Item item)
	{
		Objects.requireNonNull(item);

		this.items.merge(item, 1, (old, given) -> old+1);
	}

	public Integer getId()
	{
		return id;
	}

	public BigDecimal getTotalTaxes()
	{
		return totalTaxes;
	}

	public void setTotalTaxes(BigDecimal totalTaxes)
	{
		this.totalTaxes = totalTaxes;
	}

	public BigDecimal getTotal()
	{
		return total;
	}

	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}
}
