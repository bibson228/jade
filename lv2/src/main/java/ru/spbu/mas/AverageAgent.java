package ru.spbu.mas;

import jade.core.AID;
import jade.core.Agent;
import ru.spbu.mas.exception.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AverageAgent extends Agent {
    private static final int AGENT_NUMBER = Application.getAgentNumber();

    @Override
    protected void setup() {
        Object[] arguments = getArguments();

        if (arguments == null || arguments.length != 1) {
            throw new InvalidArgumentException("Invalid parameters for agent setup");
        }

        List<AID> agents = new ArrayList<>();
        try {
            int agentId = Integer.parseInt(String.valueOf(getAID().getLocalName()));
            double agentValue = Double.parseDouble(String.valueOf(arguments[0]));

            StateHolder.getInstance().setAgentValue(agentId, agentValue);
            for (int i = 0; i < AGENT_NUMBER; i++) {
                if (i == agentId) {
                    continue;
                }

                AID agentAid = new AID(String.valueOf(i), AID.ISLOCALNAME);
                agents.add(agentAid);
            }

        }
        catch (Exception e)
        {
            throw new InvalidArgumentException("Invalid neighbours param");
        }

        addBehaviour(new FindAverageBehaviour(this, agents, TimeUnit.SECONDS.toMillis(1)));
    }
}