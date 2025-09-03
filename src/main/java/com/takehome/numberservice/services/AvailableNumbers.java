package com.takehome.numberservice.services;

import com.takehome.numberservice.model.IntegerRange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The AvailableNumbers class is responsible for calculating the set of available numbers
 * within a specified integer range, given a list of sets containing numbers that are already taken.
 */
public class AvailableNumbers {

    private static final Logger LOGGER = LogManager.getLogger();

    private final List<Set<Integer>> sets;
    private final IntegerRange integerRange;

    /**
     * Constructs an AvailableNumbers object with a list of integer sets and a defined integer range.
     *
     * @param sets A list of sets, where each set contains integers. These sets are used to determine the numbers that are already "taken".
     * @param integerRange The range of integers to check for availability.
     */
    public AvailableNumbers(final List<Set<Integer>> sets, final IntegerRange integerRange) {
        this.sets = sets;
        this.integerRange = integerRange;
    }


    /**
     * Calculates the set of integers that are within the defined integer range but are not present in the provided sets.
     * These are considered the "available" or "missing" numbers. The range is inclusive of the minimum value and
     * exclusive of the maximum value.
     *
     * @return A {@code Set<Integer>} containing all the numbers that are in the {@code integerRange} but not in any of the {@code sets}.
     */
    public Set<Integer> asSet() {

        final Set<Integer> uniqueNumbers = uniqueNumbers(sets);

        final Set<Integer> availableNumbers = IntStream
                .range(integerRange.min(), integerRange.max())
                .parallel()
                .filter(i -> !uniqueNumbers.contains(i))
                .boxed()
                .collect(Collectors.toSet());

        LOGGER.info("Available or missing numbers: {}", availableNumbers);

        return availableNumbers;
    }

    /**
     * Combines a list of sets of integers into a single set of unique integers.
     *
     * @param sets A list of sets, with each set containing integers.
     * @return A single set containing all unique integers from the input sets.
     */
    private Set<Integer> uniqueNumbers(final List<Set<Integer>> sets) {

        // Using parallelStream to improve performance for large sets of numbers
        final Set<Integer> uniqueNumbers = sets
                .parallelStream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        LOGGER.info("All unique numbers in the given sets: {}", uniqueNumbers);

        return uniqueNumbers;
    }


}
