package kata.pokerhand.bean;

import kata.pokerhand.Card;
import kata.pokerhand.Hand;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Combination {

	HIGH_CARD("High card", (hand1, hand2) -> {
		List<Card> hand1Cards = hand1.getCards();
		List<Card> hand2Cards = hand2.getCards();

		int result = 0;
		for (int i = 0; i < hand1Cards.size(); i++) {
			result = hand1Cards.get(i).compareTo(hand2Cards.get(i));
			if (result != 0) {
				break;
			}
		}
		return result;
	}),

	PAIR("Pair", (hand1, hand2) -> {
		Figure hand1Pair = hand1.getPairsFiguresRankedFromHighest().get(0);
		Figure hand2Pair = hand1.getPairsFiguresRankedFromHighest().get(0);

		int result = hand1Pair.compareTo(hand2Pair);
		if (result == 0) {
			List<Card> hand1RemainingCards = hand1.getCards().stream()
					.filter(card -> !card.getFigure().equals(hand1Pair))
					.collect(Collectors.toList());

			List<Card> hand2RemainingCards = hand2.getCards().stream()
					.filter(card -> !card.getFigure().equals(hand2Pair))
					.collect(Collectors.toList());

			for (int i = 0; i < hand1RemainingCards.size(); i++) {
				result = hand1RemainingCards.get(i).compareTo(hand2RemainingCards.get(i));
				if (result != 0) {
					break;
				}
			}
		}
		return result;
	}),

	TWO_PAIRS("Two pairs", (hand1, hand2) -> {
		List<Figure> hand1Pairs = hand1.getPairsFiguresRankedFromHighest();
		List<Figure> hand2Pairs = hand2.getPairsFiguresRankedFromHighest();

		int result = 0;
		for (int i = 0; i < hand1Pairs.size(); i++) {
			result = hand1Pairs.get(i).compareTo(hand2Pairs.get(i));
			if (result != 0) {
				break;
			}
		}
		if (result == 0) {
			Card hand1RemainingCard = hand1.getCards().stream()
					.filter(card -> !hand1Pairs.contains(card.getFigure()))
					.findFirst().get();

			Card hand2RemainingCard = hand2.getCards().stream()
					.filter(card -> !hand2Pairs.contains(card.getFigure()))
					.findFirst().get();

			result = hand1RemainingCard.compareTo(hand2RemainingCard);
		}

		return result;
	}),

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

//	private static final Comparator<List<Card>> CARD_LIST_COMPARATOR = (list1, list2) -> {
//		int result = 0;
//		for (int i = 0; i < list1.size(); i++) {
//			result = list1.get(i).compareTo(list2.get(i));
//			if (result != 0) {
//				break;
//			}
//		}
//		return result;
//	};

}
