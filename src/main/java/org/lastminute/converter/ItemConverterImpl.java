package org.lastminute.converter;

import org.lastminute.dto.ItemDto;
import org.lastminute.entities.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class ItemConverterImpl implements Converter<Map.Entry<Item, Integer>, ItemDto>, ItemConverter
{
	@Override
	public ItemDto convert(Map.Entry<Item, Integer> entry)
	{
		ItemDto itemDto = new ItemDto();

		itemDto.setName(entry.getKey().getName());

		if(entry.getKey().getPriceVatAdded() != null) {
			itemDto.setPriceVatAdded(entry.getKey().getPriceVatAdded().multiply(BigDecimal.valueOf(entry.getValue())));
		}

		itemDto.setQuantity(entry.getValue());

		return itemDto;
	}
}
