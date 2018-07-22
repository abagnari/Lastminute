package org.lastminute.entities;

public enum ItemType
{
	OTHER("OTHER"),
	BOOK("BOOK"),
	FOOD("FOOD"),
	MEDICAL("MEDICAL");

	private String identifier;

	ItemType(String identifier)
	{
		this.identifier = identifier;
	}

	public static ItemType fromString(String name) {
		for(ItemType itemType : values()) {
			if(itemType.identifier.equals(name)) {
				return itemType;
			}
		}

		throw new IllegalArgumentException(String.format("No enum defined with identifier %s", name));
	}
}
