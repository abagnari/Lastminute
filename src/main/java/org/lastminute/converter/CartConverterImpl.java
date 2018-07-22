package org.lastminute.converter;

import org.lastminute.dto.CartDto;
import org.lastminute.dto.ItemDto;
import org.lastminute.entities.Cart;
import org.lastminute.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.stream.Collectors.toList;

@Component
public class CartConverterImpl implements Converter<Cart, CartDto>, CartConverter
{
	private final Converter<Map.Entry<Item, Integer>, ItemDto> itemConverter;

	@Autowired
	public CartConverterImpl(Converter<Map.Entry<Item, Integer>, ItemDto> itemConverter)
	{
		this.itemConverter = itemConverter;
	}

	@Override
	public CartDto convert(Cart cart)
	{
		CartDto cartDto = new CartDto();

		cartDto.setId(cart.getId());
		cartDto.setTotal(cart.getTotal());
		cartDto.setTotalTaxes(cart.getTotalTaxes());
		cartDto.setItems(cart.getItems().entrySet().stream().map(itemConverter::convert).collect(toList()));

		return cartDto;
	}
}
