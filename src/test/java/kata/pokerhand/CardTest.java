package kata.pokerhand;

import kata.pokerhand.enumeration.Figure;
import kata.pokerhand.enumeration.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardTest {

	@Test
	public void two_cards_with_same_figure_but_different_suit_should_have_same_value() {
		// Given
		Card asOfSpade = new Card(Figure.AS, Suit.SPADE);
		Card asOfHeart = new Card(Figure.AS, Suit.HEART);

		// When
		int result = asOfSpade.compareTo(asOfHeart);

		// Then
		Assert.assertEquals(0, result);
	}

	@Test
	public void a_list_of_cards_should_be_sortable_in_ascending_order() {
		// Given
		List<Card> cards = getOrderedCardList();
		Collections.shuffle(cards);

		// When
		Collections.sort(cards);

		// Then
		Assert.assertEquals(getOrderedCardList(), cards);
	}

	private List<Card> getOrderedCardList() {
		List<Card> cards = new LinkedList<>();
		cards.add(new Card(Figure.TWO, Suit.DIAMOND));
		cards.add(new Card(Figure.THREE, Suit.SPADE));
		cards.add(new Card(Figure.FOUR, Suit.DIAMOND));
		cards.add(new Card(Figure.FIVE, Suit.DIAMOND));
		cards.add(new Card(Figure.SIX, Suit.CLUB));
		cards.add(new Card(Figure.SEVEN, Suit.DIAMOND));
		cards.add(new Card(Figure.EIGHT, Suit.DIAMOND));
		cards.add(new Card(Figure.NINE, Suit.CLUB));
		cards.add(new Card(Figure.TEN, Suit.DIAMOND));
		cards.add(new Card(Figure.JACK, Suit.SPADE));
		cards.add(new Card(Figure.QUEEN, Suit.CLUB));
		cards.add(new Card(Figure.KING, Suit.DIAMOND));
		cards.add(new Card(Figure.AS, Suit.HEART));
		return cards;
	}

}