
package com.takehome.numberservice.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

/**
 * The LargestPrimeNumber class is responsible for finding the largest prime number
 * from a given set of integers.
 */
public class LargestPrimeNumber {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Set<Integer> numbers;

    public LargestPrimeNumber(final Set<Integer> numbers) {
        this.numbers = numbers;
    }


    /**
     * Finds the largest prime number from the set of numbers.
     *
     * @return an {@link Optional} containing the largest prime number, or an empty {@link Optional} if no prime numbers are found.
     */
    public Optional<Integer> asInteger() {

        final Optional<Integer> max = numbers
                .parallelStream()
                .filter(this::isPrime)
                .max(Comparator.naturalOrder());

        LOGGER.info("Largest prime number: {}", max.isPresent() ? max.get(): "No primes found");

        return max;
    }

    /**
     * Checks if a given number is a prime number.
     * A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself.
     *
     * @param number The number to check.
     * @return {@code true} if the number is prime, {@code false} otherwise.
     */
    private boolean isPrime(int number) {

        if (number <= 1) {
            return false;
        }

        /*
        The use of i * i in the loop condition i * i <= number is an optimization technique for checking primality.
        1. Efficiency: By using i * i <= number instead of i <= number, we significantly reduce the number
        of iterations needed to check if a number is prime.
        2. Mathematical reasoning: If a number n is not prime, it must have at least one divisor d where d <= √n.
        This is because if d > √n, then n/d < √n, and n/d would be another divisor of n that is less than or equal to √n.
        3. Avoiding floating-point operations: Using i * i <= number allows us to check up to the square root
        of the number without actually calculating the square root, which would involve floating-point arithmetic and be less efficient.
         */
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }


}
