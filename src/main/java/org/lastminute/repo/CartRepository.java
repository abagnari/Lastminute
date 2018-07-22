package org.lastminute.repo;

import org.lastminute.entities.Cart;

public interface CartRepository
{
	Cart getCart(Integer id);
}
