package org.hobsoft.emojiprimes;

public enum Operand implements Symbol
{
	ZERO("0️⃣", 0),
	ONE("1️⃣", 1),
	TWO("2️⃣", 2),
	THREE("3️⃣", 3),
	FOUR("4️⃣", 4),
	FIVE("5️⃣", 5),
	SIX("6️⃣", 6),
	SEVEN("7️⃣", 7),
	EIGHT("8️⃣", 8),
	NINE("9️⃣", 9),
	TEN("🔟", 10),
	HUNDRED("💯", 100);
	
	private final String emoji;
	
	private final int value;
	
	Operand(String emoji, int value)
	{
		this.emoji = emoji;
		this.value = value;
	}
	
	public int value()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return emoji;
	}
}
