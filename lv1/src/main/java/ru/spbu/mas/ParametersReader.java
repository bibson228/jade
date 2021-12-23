package ru.spbu.mas;

import java.util.HashMap;
import java.util.Scanner;

public class ParametersReader {
    public static HashMap<Integer, String> getTopologyParameters(String fileName) {
        var result = new HashMap<Integer, String>();

        try {
            var stream = ParametersReader.class.getClassLoader().getResourceAsStream(fileName);

            var scanner = new Scanner(stream);
            var agentNumber = 0;

            scanner.nextLine();

            while (scanner.hasNextLine()) {

                result.put(agentNumber, scanner.nextLine());
                agentNumber++;
            }

            scanner.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            return  result;
        }
    }
}
