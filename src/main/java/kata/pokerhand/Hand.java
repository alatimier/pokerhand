package kata.pokerhand;

import com.google.common.collect.Lists;
import kata.pokerhand.bean.Combination;
import kata.pokerhand.bean.Figure;
import kata.pokerhand.bean.Suit;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand implements Comparable<Hand> {

	private List<Card> cards;

	public Hand(Card... cards) {
		if (cards.length != 5) {
			throw new IllegalArgumentException("Hand must contains 5 cards");
		}
		this.cards = Lists.newArrayList(cards);
		this.cards.sort(Comparator.reverseOrder());
	}

	private Combination getCombination() {
		boolean isSequence = isSequence();
		boolean isFlush = getFlushSuit().isPresent();
		boolean hasThreeOfAKind = getThreeOfAKindFigure().isPresent();
		int nbPairs = getPairsFiguresRankedFromHighest().size();

		if (isSequence && isFlush) {
			return Combination.STRAIGHT_FLUSH;
		}
		if (getFourOfAKindFigure().isPresent()) {
			return Combination.FOUR_OF_A_KIND;
		}
		if (hasThreeOfAKind && nbPairs == 1) {
			return Combination.FULL_HOUSE;
		}
		if (isFlush) {
			return Combination.FLUSH;
		}
		if (isSequence) {
			return Combination.STRAIGHT;
		}
		if (hasThreeOfAKind) {
			return Combination.THREE_OF_A_KIND;
		}
		if (nbPairs == 2) {
			return Combination.TWO_PAIRS;
		}
		if (nbPairs == 1) {
			return Combination.PAIR;
		}
		return Combination.HIGH_CARD;
	}

	private Optional<Suit> getFlushSuit() {
		List<Suit> distinctSuits = cards.stream().map(Card::getSuit).distinct().collect(Collectors.toList());
		if (distinctSuits.size() == 1) {
			return Optional.of(distinctSuits.get(0));
		}
		return Optional.empty();
	}

	private boolean isSequence() {
		boolean isSequence = true;
		for (int i = 1; i < cards.size(); i++) {
			isSequence = isSequence && cards.get(i - 1).getFigure().ordinal() - cards.get(i).getFigure().ordinal() == 1;
		}
		return isSequence;
	}

	public List<Figure> getPairsFiguresRankedFromHighest() {
		return getFiguresByNumberOfOccurrence(2).collect(Collectors.toList());
	}

	public Optional<Figure> getThreeOfAKindFigure() {
		return getFiguresByNumberOfOccurrence(3).findFirst();
	}

	public Optional<Figure> getFourOfAKindFigure() {
		return getFiguresByNumberOfOccurrence(4).findFirst();
	}

	private Stream<Figure> getFiguresByNumberOfOccurrence(int occurrences) {
		return cards.stream()
				.map(Card::getFigure)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.filter(figureEntry -> figureEntry.getValue() == occurrences)
				.map(Map.Entry::getKey)
				.sorted(Comparator.reverseOrder());
	}

	public List<Card> getCards() {
		return Lists.newArrayList(cards);
	}

	@Override
	public int compareTo(Hand otherHand) {
		Combination combination = getCombination();
		int result = combination.compareTo(otherHand.getCombination());
		if (result == 0) {
			result = combination.getComparator().compare(this, otherHand);
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hand hand = (Hand) o;
		return Objects.equals(cards, hand.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}

}
