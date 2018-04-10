package kata.pokerhand.bean;

import kata.pokerhand.Hand;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Combination {

	HIGH_CARD("High card", (hand1, hand2) -> compareLists(hand1.getCards(), hand2.getCards())),

	PAIR("Pair", (hand1, hand2) -> {
		List<Figure> hand1Pairs = hand1.getPairsFiguresRankedFromHighest();
		List<Figure> hand2Pairs = hand2.getPairsFiguresRankedFromHighest();

		// Compare pairs first
		int result = compareLists(hand1Pairs, hand2Pairs);

		// If pairs have same values, compare remaining cards
		if (result == 0) {
			result = compareLists(
					hand1.getCards().stream().filter(card -> !hand1Pairs.contains(card.getFigure())).collect(Collectors.toList()),
					hand2.getCards().stream().filter(card -> !hand2Pairs.contains(card.getFigure())).collect(Collectors.toList())
			);
		}

		return result;
	}),

	TWO_PAIRS("Two pairs", PAIR.comparator),

	THREE_OF_A_KIND("Three of a kind", Comparator.comparing(hand -> hand.getThreeOfAKindFigure().get())),

	STRAIGHT("Straight", Comparator.comparing(hand -> hand.getCards().get(0))),

	FLUSH("Flush", HIGH_CARD.comparator),

	FULL_HOUSE("Full house", THREE_OF_A_KIND.comparator),

	FOUR_OF_A_KIND("Four of a kind", Comparator.comparing(hand -> hand.getFourOfAKindFigure().get())),

	STRAIGHT_FLUSH("Straight flush", STRAIGHT.comparator);

	private String label;
	private Comparator<Hand> comparator;

	Combination(String label, Comparator<Hand> comparator) {
		this.label = label;
		this.comparator = comparator;
	}

	public Comparator<Hand> getComparator() {
		return comparator;
	}

	@Override
	public String toString() {
		return label;
	}

	/**
	 * Compare two lists of comparable objects sorted from the highest value to to lowest.
	 * Each object from the first list is compared to the object with the same position in the 2nd.
	 * The highest list is the one containing the first highest object.
	 */
	private static <T> int compareLists(List<? extends Comparable<T>> list1, List<T> list2) {
		int result = 0;
		for (int i = 0; i < list1.size(); i++) {
			result = list1.get(i).compareTo(list2.get(i));
			if (result != 0) {
				break;
			}
		}
		return result;
	}

}
