package kata.pokerhand;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static kata.pokerhand.Deck.*;

public class HandTest {

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_if_hand_does_not_contains_five_cards() {
		new Hand();
	}

	@Test
	public void should_rank_hands_with_different_combinations_accordingly_to_combination_values() {
		// Given
		List<Hand> hands = getRankedHandList();
		Collections.shuffle(hands);

		// When
		Collections.sort(hands);

		// Then
		Assert.assertEquals(getRankedHandList(), hands);
	}

	private List<Hand> getRankedHandList() {
		return Lists.newArrayList(
				getHighCardHand(),
				getPairHand(),
				getTwoPairHand(),
				getThreeOfAKindHand(),
				getStraightHand(),
				getFlushHand(),
				getFullHouseHand(),
				getFourOfAKindHand(),
				getStraightFlushHand()
		);
	}

	@Test
	public void should_win_with_highest_different_card_between_two_high_card_hands() {
		Hand lowerHighCardHand = new Hand(EIGHT_OF_SPADE, TWO_OF_SPADE, QUEEN_OF_HEART, KING_OF_HEART, ACE_OF_SPADE);
		Assert.assertTrue(getHighCardHand().compareTo(lowerHighCardHand) > 0);
	}

	@Test
	public void should_win_with_highest_pair_between_two_one_pair_hands() {
		Hand lowerPairHand = new Hand(EIGHT_OF_SPADE, KING_OF_DIAMOND, QUEEN_OF_HEART, KING_OF_HEART, ACE_OF_SPADE);
		Assert.assertTrue(getPairHand().compareTo(lowerPairHand) > 0);
	}

	@Test
	public void should_win_with_highest_different_remaining_card_between_two_one_pair_hands_with_similar_pair() {
		Hand lowerPairHand = new Hand(TWO_OF_DIAMOND, ACE_OF_DIAMOND, QUEEN_OF_DIAMOND, KING_OF_CLUB, ACE_OF_HEART);
		Assert.assertTrue(getPairHand().compareTo(lowerPairHand) > 0);
	}

	@Test
	public void should_win_with_highest_different_pair_between_two_two_pairs_hands() {
		Hand lowerTwoPairsHand = new Hand(KING_OF_SPADE, QUEEN_OF_DIAMOND, QUEEN_OF_HEART, KING_OF_HEART, ACE_OF_SPADE);
		Assert.assertTrue(getTwoPairHand().compareTo(lowerTwoPairsHand) > 0);
	}

	@Test
	public void should_win_with_remaining_card_between_two_two_pairs_hands_with_similar_pairs() {
		Hand lowerTwoPairsHand = new Hand(KING_OF_SPADE, ACE_OF_CLUB, JACK_OF_SPADE, KING_OF_HEART, ACE_OF_SPADE);
		Assert.assertTrue(getTwoPairHand().compareTo(lowerTwoPairsHand) > 0);
	}

	@Test
	public void should_win_with_highest_three_of_a_kind_between_two_three_of_a_kind_hands() {
		Hand lowerThreeOfAKindHand = new Hand(KING_OF_SPADE, JACK_OF_SPADE, QUEEN_OF_HEART, JACK_OF_CLUB, JACK_OF_HEART);
		Assert.assertTrue(getThreeOfAKindHand().compareTo(lowerThreeOfAKindHand) > 0);
	}

	@Test
	public void should_win_with_highest_card_between_two_straight_hands() {
		Hand lowerThreeOfAKindHand = new Hand(KING_OF_CLUB, QUEEN_OF_HEART, JACK_OF_CLUB, TEN_OF_DIAMOND, NINE_OF_DIAMOND);
		Assert.assertTrue(getStraightHand().compareTo(lowerThreeOfAKindHand) > 0);
	}

	@Test
	public void should_win_with_highest_different_card_between_two_flush_hands() {
		Hand lowerFlushHand = new Hand(QUEEN_OF_SPADE, KING_OF_SPADE, TEN_OF_SPADE, NINE_OF_SPADE, EIGHT_OF_SPADE);
		Assert.assertTrue(getFlushHand().compareTo(lowerFlushHand) > 0);
	}

	@Test
	public void should_win_with_highest_three_of_a_kind_between_two_full_house_hands() {
		Hand lowerFullHouseHand = new Hand(KING_OF_SPADE, QUEEN_OF_SPADE, KING_OF_DIAMOND, QUEEN_OF_DIAMOND, QUEEN_OF_HEART);
		Assert.assertTrue(getFullHouseHand().compareTo(lowerFullHouseHand) > 0);
	}

	@Test
	public void should_win_with_highest_four_of_a_kind_between_two_four_of_a_kind_hands() {
		Hand lowerFourOfAKindHand = new Hand(KING_OF_SPADE, KING_OF_HEART, QUEEN_OF_HEART, KING_OF_DIAMOND, KING_OF_CLUB);
		Assert.assertTrue(getFourOfAKindHand().compareTo(lowerFourOfAKindHand) > 0);
	}

	private Hand getHighCardHand() {
		return new Hand(EIGHT_OF_SPADE, JACK_OF_SPADE, QUEEN_OF_HEART, KING_OF_HEART, ACE_OF_SPADE);
	}

	private Hand getPairHand() {
		return new Hand(EIGHT_OF_SPADE, ACE_OF_CLUB, QUEEN_OF_HEART, KING_OF_HEART, ACE_OF_SPADE);
	}

	private Hand getTwoPairHand() {
		return new Hand(KING_OF_SPADE, ACE_OF_CLUB, QUEEN_OF_HEART, KING_OF_HEART, ACE_OF_SPADE);
	}

	private Hand getThreeOfAKindHand() {
		return new Hand(KING_OF_SPADE, ACE_OF_CLUB, QUEEN_OF_HEART, ACE_OF_DIAMOND, ACE_OF_SPADE);
	}

	private Hand getStraightHand() {
		return new Hand(ACE_OF_DIAMOND, KING_OF_CLUB, QUEEN_OF_HEART, JACK_OF_CLUB, TEN_OF_DIAMOND);
	}

	private Hand getFlushHand() {
		return new Hand(ACE_OF_DIAMOND, KING_OF_DIAMOND, TEN_OF_DIAMOND, NINE_OF_DIAMOND, EIGHT_OF_DIAMOND);
	}

	private Hand getFullHouseHand() {
		return new Hand(KING_OF_SPADE, ACE_OF_CLUB, KING_OF_DIAMOND, ACE_OF_DIAMOND, ACE_OF_SPADE);
	}

	private Hand getFourOfAKindHand() {
		return new Hand(ACE_OF_HEART, ACE_OF_CLUB, QUEEN_OF_HEART, ACE_OF_DIAMOND, ACE_OF_SPADE);
	}

	private Hand getStraightFlushHand() {
		return new Hand(ACE_OF_DIAMOND, KING_OF_DIAMOND, QUEEN_OF_DIAMOND, JACK_OF_DIAMOND, TEN_OF_DIAMOND);
	}

}