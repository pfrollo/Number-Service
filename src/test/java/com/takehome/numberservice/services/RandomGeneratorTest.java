package com.takehome.numberservice.services;

import com.takehome.numberservice.model.IntegerRange;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RandomGeneratorTest {

    /*
      The tests use the following naming convention:
        given_<test conditions or setup>_when_<the unit of the code being tested>_then_<expected result>

      At first, there appears to be a benefit in parametrizing these tests. For example, we would test with the following min values: 10, 100, 1000
      I used TDD, the test failed at first, then I implemented the code, then the test passed.
      There is a school of thought that feeding different values have no benefits in these cases, hence these tests where not parametrized.
      However, parametrized tests are beneficial when you want to test different scenarios or edge cases.

     */

    @Test
    void given_minNumber_when_RandomGenerator_asSet_then_valuesAreGreaterThenOrEqualThanMinNumber() {
        // Given
        final IntegerRange integerRange = new IntegerRange(100, 1000);
        final int size = 1;
        final int numberOfSets = 1;
        RandomGeneratorI randomGeneratorI = new RandomGenerator(integerRange, size, numberOfSets);

        // When
        List<Set<Integer>> integerSets = randomGeneratorI.asSet();

        // Then
        for (Integer integer : integerSets.stream().findFirst().orElseThrow()) {
            assertTrue(integer >= integerRange.min(), "Value should be greater or equal to " + integerRange.min());
        }
    }

    @Test
    void given_maxNumber_when_RandomGenerator_asSet_then_valuesAreLessThenOrEqualThanMaxNumber() {
        // Given
        final IntegerRange integerRange = new IntegerRange(0, 1);
        final int size = 1;
        final int numberOfSets = 1;
        RandomGeneratorI randomGeneratorI = new RandomGenerator(integerRange, size, numberOfSets);

        // When
        List<Set<Integer>> integerSets = randomGeneratorI.asSet();

        // Then
        for (Integer integer : integerSets.stream().findFirst().orElseThrow()) {
            assertTrue(integer < integerRange.max(), "Value should be less to " + integerRange.max());
        }
    }

    @Test
    void given_size_when_RandomGenerator_asSet_then_numberOfValuesMatchesInput() {
        // Given
        final IntegerRange integerRange = new IntegerRange(0, 10);
        final int size = 5;
        final int numberOfSets = 1;
        RandomGeneratorI randomGeneratorI = new RandomGenerator(integerRange, size, numberOfSets);

        // When
        List<Set<Integer>> integerSets = randomGeneratorI.asSet();

        // Then
        assertEquals(size, integerSets.stream().findFirst().orElseThrow().size(), "Number of values should be " + size);
    }

    @Test
    void given_numberOfSets_when_RandomGenerator_asSet_then_numberOfSetsMatchesInput() {
        // Given
        final IntegerRange integerRange = new IntegerRange(0, 10);
        final int size = 1;
        final int numberOfSets = 5;
        RandomGeneratorI randomGeneratorI = new RandomGenerator(integerRange, size, numberOfSets);

        // When
        List<Set<Integer>> integerSets = randomGeneratorI.asSet();

        // Then
        assertEquals(numberOfSets, integerSets.size(), "Number of sets should be " + numberOfSets);
    }

}
