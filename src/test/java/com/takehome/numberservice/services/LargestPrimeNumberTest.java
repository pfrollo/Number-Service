package com.takehome.numberservice.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LargestPrimeNumberTest {

    @ParameterizedTest
    @MethodSource("testInAndOutValues")
    void given_setsOfPrimeNumbers_when_LargestPrimeNumber_asInteger_thenLargestPrimeNumber( final Set<Integer> numbers, final Optional<Integer> expectedLargestPrime) {

        // Given
        LargestPrimeNumber largestPrimeNumber = new LargestPrimeNumber(numbers);

        // When
        Optional<Integer> largestPrime = largestPrimeNumber.asInteger();

        // Then
        assertEquals(expectedLargestPrime, largestPrime,
                "The largest prime number should be" +
                (expectedLargestPrime.isPresent() ? expectedLargestPrime.get() : "not be present") +
                " but was " + largestPrime.orElse(null));

    }

    private static Stream<Arguments> testInAndOutValues() {
        return Stream.of(
                // not a prime number
                Arguments.of(Set.of(1), Optional.empty()),
                Arguments.of(Set.of(), Optional.empty()),
                // single prime number
                Arguments.of(Set.of(5), Optional.of(5)),
                // smallest prime number
                Arguments.of(Set.of(2), Optional.of(2)),
                // no prime number
                Arguments.of(Set.of(1, 4, 6, 8, 9), Optional.empty()),
                // multiple prime numbers with a large non-prime number
                Arguments.of(Set.of(2, 3, 5, 7, 11, 25), Optional.of(11))
        );
    }
}
