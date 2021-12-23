package ru.spbu.mas;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MainController {
    private HashMap<Integer, String> parameters;
    private static final String HOST = "localhost";
    private static final String PORT = "10098";
    private static final String GUI = "true";
    private static final String AGENT_PACKAGE_NAME = "ru.spbu.mas.AlgorithmAgent";
    private static final String CENTRAL_NICKNAME = "Central";

    public MainController(HashMap<Integer, String> parameters)
    {
        this.parameters = parameters;
    }


    public void initAgents() {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.MAIN_PORT, "10098");
        p.setParameter(Profile.GUI, "true");
        ContainerController cc = rt.createMainContainer(p);

        try {
            var receivers = getReceivers(parameters);
            System.out.println( receivers );
            var requiredSendersCounts = getRequiredSendersCounts(receivers);
            System.out.println( requiredSendersCounts );
            for(int i = 0; i < parameters.size(); i++)
            {
                AgentController agent = cc.createNewAgent(Integer.toString(i), "ru.spbu.mas.AlgorithmAgent",
                                  new Object[] {parameters.get(i), parameters.size(),
                                  receivers.get(i), requiredSendersCounts.get(i)});
                System.out.println("Агент - " + parameters.get(i));
                agent.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ContainerController initMainContainerController() {
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();

        profile.setParameter(Profile.MAIN_HOST, HOST);
        profile.setParameter(Profile.MAIN_PORT, PORT);
        profile.setParameter(Profile.GUI, GUI);

        return runtime.createMainContainer(profile);
    }

    private HashMap<Integer, Integer> getReceivers(HashMap<Integer, String> parameters)
    {
        var receivers = new HashMap<Integer, Integer>();

        for (var id : parameters.keySet())
        {
            receivers.put(id, findReceiverById(id, parameters.size()));
        }

        return  receivers;
    }

    private HashMap<Integer, Integer> getRequiredSendersCounts(HashMap<Integer, Integer> receivers) {
        var sendersCounts = new  HashMap<Integer, Integer>();

        for (var id : receivers.keySet()) {
            sendersCounts.put(id, receivers.values().stream().filter(item -> item == id).collect(Collectors.toList()).size());
        }

        return  sendersCounts;
    }

    private int findReceiverById(int id, int totalAgents) {
        if (id == 0)
        {
            return -1;
        }

        var buffer = new ArrayList<Integer>();

        for (var i = 0; i < totalAgents; ++i)
        {
            buffer.add(i);
        }

        var numbers = buffer.size()/2;
        int num = 0;

        var elementIndex = buffer.indexOf(id);

        if (elementIndex % 2 == 0)
        {
            if ((buffer.size() - 2) == elementIndex)
            {
                return buffer.get(elementIndex+1);
            }
            if ((elementIndex != buffer.size() - 1))
            {
                return buffer.get(elementIndex+2);
            }
            return 0;

        }
        else
        {
            if((elementIndex == buffer.size() - 1))
            {
                return 0;
            }
            return buffer.get(elementIndex+1);
        }
    }
}
