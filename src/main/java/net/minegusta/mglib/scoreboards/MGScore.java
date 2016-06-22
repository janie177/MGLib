package net.minegusta.mglib.scoreboards;

public class MGScore {

	private String name;
	private int amount;

	public MGScore(String name, int amount)
	{
		this.amount = amount;
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public String getName() {
		return name;
	}
}
