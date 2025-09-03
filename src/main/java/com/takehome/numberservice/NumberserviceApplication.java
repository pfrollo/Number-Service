
package com.takehome.numberservice;

import com.takehome.numberservice.model.IntegerRange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import  com.takehome.numberservice.services.*;

import java.util.List;
import java.util.Set;


@SpringBootApplication
public class NumberserviceApplication implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${MIN_RANGE:0}")
    private int minRange;

    @Value("${MAX_RANGE:100}")
    private int maxRange;

    @Value("${NUM_SETS:5}")
    private int numSets;

    @Value("${SET_SIZE:5}")
    private int setSize;

    public static void main(String[] args) {
        SpringApplication.run(NumberserviceApplication.class, args);
    }

    @Override
    public void run(String... args) {

        LOGGER.info("MIN_RANGE: {}", minRange);
        LOGGER.info("MAX_RANGE: {}", maxRange);
        LOGGER.info("NUM_SETS: {}", numSets);
        LOGGER.info("SET_SIZE: {}", setSize);

        final IntegerRange integerRange = new IntegerRange(minRange, maxRange);
        final List<Set<Integer>> randomSets = new RandomGenerator(integerRange, setSize, numSets).asSet();
        final Set<Integer> availableNumbers = new AvailableNumbers(randomSets, integerRange).asSet();
        new LargestPrimeNumber(availableNumbers).asInteger();

        System.exit(0);
    }
}
