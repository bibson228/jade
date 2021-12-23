package ru.spbu.mas;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.util.Map;

public class MainController {
    public void initAgents(Map<Integer, Double> agentValues)
    {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.MAIN_PORT, "10098");
        p.setParameter(Profile.GUI, "true");
        ContainerController cc = rt.createMainContainer(p);

        try {
            AgentController agentController;
            Object[] agentArguments;
            int agentsNumber = agentValues.size();
            for (int i = 0; i < agentsNumber; i++) {
                agentArguments = new Object[] {agentValues.get(i)};
                agentController = cc.createNewAgent(String.valueOf(i), "ru.spbu.mas.AverageAgent", agentArguments);
                agentController.start();
            }
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

}
