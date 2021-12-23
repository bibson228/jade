package ru.spbu.mas;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FindAverageBehaviour extends TickerBehaviour {

    private final AverageAgent agent;
    private final List<AID> neighbours;
    private final int aid;
    private static final int AGENT_NUMBER = Application.getAgentNumber();

    public FindAverageBehaviour(AverageAgent agent, List<AID> agentAidList, long period) {
        super(agent, period);
        this.agent = agent;
        this.neighbours = agentAidList;
        this.aid = Integer.parseInt(agent.getAID().getLocalName());
        this.setFixedPeriod(true);
    }

    @Override
    protected void onTick() {
        if (!StateHolder.getInstance().checkSentAgent(aid)) {
            // Sending messages to neighbours
            for (AID nAID : neighbours) {
                // Check if connection fails
                if (Math.random() < 0.8) {
                    agent.doWait(100);
                }
                ACLMessage msg;
                msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(nAID);
                // Add interference
                double valueWithInterference;
                valueWithInterference = StateHolder.getInstance().getAgentValue(aid) + ThreadLocalRandom.current().nextDouble(-1, 1);
                msg.setContent(Double.toString(valueWithInterference));
                agent.send(msg);
            }

            StateHolder.getInstance().setSentAgent(aid);
        }

        ACLMessage msg;
        msg = agent.receive();

        if (msg != null) {
            AID a = msg.getSender();
            double current = StateHolder.getInstance().getAgentValue(aid);
            double uDelta = StateHolder.getInstance().getBeta() * (Double.parseDouble(msg.getContent()) - current);
            double uCurrent = StateHolder.getInstance().getUValue(aid);
            StateHolder.getInstance().setUValue(aid, uCurrent + uDelta);
        }

        if (StateHolder.getInstance().checkAllSentAgents()) {
            StateHolder.getInstance().resetSentList();
            for (int id = 0; id < AGENT_NUMBER; ++id) {
                double u = StateHolder.getInstance().getUValue(aid);
                double current = StateHolder.getInstance().getAgentValue(aid);
                StateHolder.getInstance().setAgentValue(aid, current + u * StateHolder.getInstance().getAlpha());
            }
            StateHolder.getInstance().resetUS();
            StateHolder.getInstance().incrementCounter();

            double res = StateHolder.getInstance().getAgentValue(0);

            if (StateHolder.getInstance().counterFinished()) {
                System.out.printf("Результат %f\n", res);
                stop();
            }
        }

        agent.doWait(10);
    }
}