package se.helpers;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    private static final int MIN_CUSTOMER_YEARS = 18;
    private static final int MAX_CUSTOMER_YEARS = 90;
    private static final int ZERO_YEARS = 0;
    static LocalDate today = LocalDate.now();

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static LocalDate getRandomBirthdayMoreThen18() {
        LocalDate earliestDate = today.minusYears(MIN_CUSTOMER_YEARS);
        LocalDate startDate = today.minusYears(MAX_CUSTOMER_YEARS);
        LocalDate randomDate = generateRandomDate(startDate, earliestDate);
        return validateBirthday(randomDate);
    }

    public static LocalDate getRandomBirthdayLessThen18() {
        LocalDate earliestDate = today.minusYears(ZERO_YEARS);
        LocalDate startDate = today.minusYears(MIN_CUSTOMER_YEARS);
        LocalDate randomDate = generateRandomDate(startDate, earliestDate);
        return validateBirthday(randomDate);
    }

    private static LocalDate validateBirthday(LocalDate randomDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        randomDate.format(formatter);
        return randomDate;
    }

    public static LocalDate generateRandomDate(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public String getRandomString(List<String> list) {
        return list.get(getRandomNumber(0, list.size()));
    }

    public static String getRandomMonogram(Faker faker, List<String> excludeOptions) {
        String randomMonogram = "";
        int countOfMonogramLetters = new RandomGenerator().getRandomNumber(1, 4);
        if (countOfMonogramLetters == 1)
            return faker.letterify("?").toUpperCase();
        else if (countOfMonogramLetters == 2) {
            do {
                randomMonogram = faker.letterify("??").toUpperCase();
            } while (excludeOptions.contains(randomMonogram));
            return randomMonogram;
        } else if (countOfMonogramLetters == 3) {
            return faker.letterify("???").toUpperCase();
        }
        return randomMonogram;
    }
}
