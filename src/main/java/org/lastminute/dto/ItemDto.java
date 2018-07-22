package org.lastminute.dto;

import java.math.BigDecimal;

public class ItemDto
{
	private Integer quantity;
	private String name;
	private BigDecimal priceVatAdded;

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public BigDecimal getPriceVatAdded()
	{
		return priceVatAdded;
	}

	public void setPriceVatAdded(BigDecimal priceVatAdded)
	{
		this.priceVatAdded = priceVatAdded;
	}
}
