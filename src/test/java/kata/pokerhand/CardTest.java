package kata.pokerhand;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static kata.pokerhand.Deck.*;

public class CardTest {

	@Test
	public void should_have_same_value_for_two_card_with_same_figure() {
		Assert.assertNotEquals(ACE_OF_SPADE, ACE_OF_HEART);
		Assert.assertEquals(0, ACE_OF_SPADE.compareTo(ACE_OF_HEART));
	}

	@Test
	public void should_rank_cards_accordingly_to_card_values() {
		// Given
		List<Card> cards = getOrderedCardList();
		Collections.shuffle(cards);

		// When
		Collections.sort(cards);

		// Then
		Assert.assertEquals(getOrderedCardList(), cards);
	}

	private List<Card> getOrderedCardList() {
		return Lists.newArrayList(
				TWO_OF_DIAMOND,
				THREE_OF_CLUB,
				FOUR_OF_HEART,
				FIVE_OF_CLUB,
				SIX_OF_SPADE,
				SEVEN_OF_DIAMOND,
				EIGHT_OF_CLUB,
				NINE_OF_HEART,
				TEN_OF_DIAMOND,
				JACK_OF_SPADE,
				QUEEN_OF_HEART,
				KING_OF_CLUB,
				ACE_OF_SPADE
		);
	}

}