package ru.spbu.mas;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;

public class Application {
    private static final int AGENT_NUMBER = 5;

    public static void main(String[] args) {
        Map<Integer, Double> parameters = new HashMap<>();

        for (int i = 0; i < AGENT_NUMBER; ++i) {
            double  randomDouble = ThreadLocalRandom.current().nextDouble(-10, 10);
            parameters.put(i, randomDouble);
            System.out.println(randomDouble);
        }

        OptionalDouble average = parameters.values().stream().mapToDouble(a -> a).average();
        System.out.println("Average:");
        System.out.println(average.getAsDouble());

        MainController controller = new MainController();
        controller.initAgents(parameters);
    }

    public static int getAgentNumber() {
        return AGENT_NUMBER;
    }
}
