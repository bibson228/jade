package ru.spbu.mas;

import jade.core.behaviours.TickerBehaviour;

public class AverageBehaviour extends TickerBehaviour
{
    private final AlgorithmAgent agent;

    AverageBehaviour(AlgorithmAgent agent, long period) {
        super(agent, period);
        this.setFixedPeriod(true);
        this.agent = agent;
    }

    @Override
    protected void onTick() {
        if (!agent.isCenter()) {
            agent.sendMessage();
        }

        if (agent.isTriggered()) {
            stop();
        }

        var msg = agent.blockingReceive();

        if (msg != null)
        {
            agent.proceedIncomingMessage(msg);

            if (agent.isCenter()) {
                System.out.println("Среднее арифметическое этих n чисел: " + agent.getValue());
                stop();
            }
        }
    }
}