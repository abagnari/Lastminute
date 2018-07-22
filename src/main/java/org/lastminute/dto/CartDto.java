package org.lastminute.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartDto
{
	private Integer id;
	private List<ItemDto> items = new ArrayList<>();
	private BigDecimal totalTaxes;
	private BigDecimal total;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public List<ItemDto> getItems()
	{
		return items;
	}

	public void setItems(List<ItemDto> items)
	{
		this.items = items;
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
