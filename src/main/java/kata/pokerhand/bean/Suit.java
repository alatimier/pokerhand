package kata.pokerhand.bean;

public enum Suit {

	DIAMOND("Diamond"),
	CLUB("Club"),
	SPADE("Spade"),
	HEART("Heart");

	private String label;

	Suit(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
}
