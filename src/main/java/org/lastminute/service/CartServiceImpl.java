package org.lastminute.service;

import org.lastminute.entities.Cart;
import org.lastminute.entities.Item;
import org.lastminute.entities.ItemType;
import org.lastminute.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CartServiceImpl implements CartService
{
	private static final int BASE_TAX = 10;
	private static final int IMPORTED_TAX = 5;
	private static final BigDecimal HUNDRED = new BigDecimal("100");
	private static final BigDecimal FIVE_CENT_INCREMENT = new BigDecimal("0.05");

	private final CartRepository cartRepository;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository)
	{
		this.cartRepository = cartRepository;
	}

	@Override
	public Cart getCart(Integer id)
	{
		Cart cart = cartRepository.getCart(id);
		BigDecimal totalTax = BigDecimal.ZERO;
		BigDecimal totalPrice = BigDecimal.ZERO;

		for(Item item : cart.getItems().keySet()) {
			if(item.isImported() || item.getItemType().equals(ItemType.OTHER)) {
				BigDecimal tax = getTaxFromItem(item);

				totalTax = totalTax.add(tax);

				item.setPriceVatAdded(addTaxToPrice(item.getPrice(), tax));
			}
			else {
				item.setPriceVatAdded(item.getPrice());
			}

			totalPrice = totalPrice.add(item.getPriceVatAdded());
		}

		cart.setTotal(totalPrice);
		cart.setTotalTaxes(totalTax);

		return cart;
	}

	private BigDecimal getTaxFromItem(Item item) {
		BigDecimal tax = new BigDecimal(String.valueOf(
				(item.isImported() ? IMPORTED_TAX : 0) +
				(item.getItemType().equals(ItemType.OTHER) ? BASE_TAX : 0)))
				.divide(HUNDRED).setScale(4, RoundingMode.UP);

		return round(tax.multiply(item.getPrice()), FIVE_CENT_INCREMENT, RoundingMode.UP);
	}

	private BigDecimal addTaxToPrice(BigDecimal price, BigDecimal tax) {
		return price.add(tax);
	}

	private static BigDecimal round(BigDecimal value, BigDecimal increment,
									RoundingMode roundingMode) {
		if (increment.signum() == 0) {
			return value;
		} else {
			BigDecimal divided = value.divide(increment, 0, roundingMode);
			return divided.multiply(increment);
		}
	}
}
