package org.lastminute.converter;

import org.lastminute.dto.CartDto;
import org.lastminute.entities.Cart;

public interface CartConverter
{
	CartDto convert(Cart cart);
}
