package kata.pokerhand;

import kata.pokerhand.enumeration.Figure;
import kata.pokerhand.enumeration.Suit;

import java.util.Objects;

public class Card implements Comparable<Card> {

	private Figure figure;
	private Suit suit;

	public Card(Figure figure, Suit suit) {
		this.figure = figure;
		this.suit = suit;
	}

	public Figure getFigure() {
		return figure;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public int compareTo(Card other) {
		return this.figure.compareTo(other.figure);
	}

	@Override
	public String toString() {
		return figure + " of " + suit;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return figure == card.figure && suit == card.suit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(figure, suit);
	}

}
