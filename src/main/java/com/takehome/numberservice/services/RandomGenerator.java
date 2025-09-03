package com.takehome.numberservice.services;

import com.takehome.numberservice.model.IntegerRange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The RandomGenerator class is responsible for generating random sets of integers
 * within a specified range.
 * <p>
 * This class implements the RandomGeneratorI interface and provides functionality to:
 * <ul>
 *   <li>Generate a specified number of sets</li>
 *   <li>Ensure each set contains a specified number of unique integers</li>
 *   <li>Generate integers within a given range</li>
 * </ul>
 * <p>
 */
public class RandomGenerator implements RandomGeneratorI {

    private static final Logger LOGGER = LogManager.getLogger();

    private final IntegerRange integerRange;
    private final int size;
    private final int numberOfSets;
    private final java.util.random.RandomGenerator jvmGenerator;

    /**
     * Constructor for the RandomGenerator class.
     * <p>
     * This constructor initializes the RandomGenerator object with the specified parameters.
     * It generates random integer values within the given range, array size, and number of sets.
     *
     * @param integerRange   the range of numbers to generate. The minimum value is inclusive, and the maximum value is exclusive.
     * @param integersPerSet the size of each array to be generated.
     * @param numberOfSets   the number of arrays to be generated.
     */
    public RandomGenerator(final IntegerRange integerRange, final int integersPerSet, final int numberOfSets) {
        this.integerRange = integerRange;
        this.size = integersPerSet;
        this.numberOfSets = numberOfSets;
        this.jvmGenerator = java.util.random.RandomGenerator.getDefault();
    }


    /**
     * Generates a list of unique integer sets based on the provided parameters.
     *
     * @return a list of unique integer sets. Each set contains random integers within the specified range and size.
     *
     * @throws IllegalArgumentException if the number of sets or array size is less than or equal to zero.
     * @throws IllegalArgumentException if the minimum value in the range is greater than or equal to the maximum value.
     */
    @Override
    public List<Set<Integer>> asSet() {

        final List<Set<Integer>> sets = new ArrayList<>();
        while (sets.size() < numberOfSets) {
            sets.add(singleSet());
        }

        return sets;
    }

    /**
     * Generates a single set of unique random integers within the specified range.
     * <p>
     * This method creates a set of unique integers, where the size of the set is determined
     * by the 'size' field of the class. Each integer in the set is randomly generated
     * within the range specified by the 'integerRange' field.
     *
     * @return A Set of Integer objects containing unique random integers.
     */
    private Set<Integer> singleSet() {

        final Set<Integer> integers = new HashSet<>();
        while (integers.size() < size) {
            integers.add(jvmGenerator.nextInt(integerRange.min(), integerRange.max()));
        }
        LOGGER.info("Generated set: {}", integers);
        return integers;
    }


}
