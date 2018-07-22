package org.lastminute.converter;

import org.lastminute.dto.ItemDto;
import org.lastminute.entities.Item;

import java.util.Map;

public interface ItemConverter
{
	ItemDto convert(Map.Entry<Item, Integer> entry);
}
