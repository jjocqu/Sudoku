package Logic;

import java.util.Random;

/**
 * Created by jens on 7/1/2016.
 * Singleton class that generates random integers
 */
public class RandomNumberGenerator {

    private static RandomNumberGenerator generator = new RandomNumberGenerator();

    private Random random;

    private RandomNumberGenerator() {
        random = new Random();
    }

    public static RandomNumberGenerator getGenerator() {
        return generator;
    }

    public int generateInteger(int bound) {
        return random.nextInt(bound);
    }
}
