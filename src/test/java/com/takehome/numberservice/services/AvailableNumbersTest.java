package com.takehome.numberservice.services;

import com.takehome.numberservice.model.IntegerRange;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.Set;

class AvailableNumbersTest {

    @Test
    void given_evenNumberGenerator_when_AvailableNumbers_asSet() {

        // Given
        final IntegerRange integerRange = new IntegerRange(0, 10);
        final List<Set<Integer>> sets = new EvenNumbersGenerator().asSet();

        // When
        Set<Integer> set = new AvailableNumbers(sets, integerRange).asSet();

        // Then
        assertEquals(Set.of(0, 1, 3, 5, 7, 9), set, "Set should be {0, 1, 3, 5, 7, 9} but was " + set);
    }


    /**
     * A test implementation of {@link RandomGeneratorI} that generates a predictable
     * list of sets containing only even numbers. This is used to create a stable
     * testing environment.
     */
    static class EvenNumbersGenerator implements RandomGeneratorI{

        /**
         * Generates a fixed list of sets containing even numbers.
         * This implementation is used for testing purposes to provide a predictable
         * set of numbers.
         *
         * @return A list containing two sets of even integers.
         */
        @Override
        public List<Set<Integer>> asSet() {

            // Even numbers, odds numbers are missing
            Set<Integer> set1 = Set.of(2, 4);
            Set<Integer> set2 = Set.of(6, 8);

            return List.of(set1, set2);
        }
    }
}
