package net.minegusta.mglib.scoreboards;

public class MGScore {

	private String name;
	private int amount;
	private String key;

	public MGScore(String key, String name, int amount)
	{
		this.amount = amount;
		this.key = key;
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
