package ru.spbu.mas;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class StateHolder {
    // Number of agents
    private static final int AGENT_NUMBER = Application.getAgentNumber();

    private double alpha = 0.1;

    // Beta from local voting protocol formula
    private double beta = 1;

    // Ticks counter
    private int counter = 0;

    // Maximum number of ticks
    private static final int MAX_COUNTER = 30;

    // Flags to check if agent has sent message on current tick
    private List<Boolean> sent = new ArrayList<>(Collections.nCopies(AGENT_NUMBER, false));

    // Flags to check if agent has been updated on current tick
    private List<Boolean> updatedAgentsList = new ArrayList<>(Collections.nCopies(AGENT_NUMBER, false));
    // Current values in agents
    private List<Double> agentValues = new ArrayList<>(Collections.nCopies(AGENT_NUMBER, 0.0));
    // Intermediate results of 'u'-s for agents
    private List<Double> uList = new ArrayList<>(Collections.nCopies(AGENT_NUMBER, 0.0));

    private static StateHolder self = new StateHolder();

    private boolean isResultCounted = false;

    public static StateHolder getInstance() { return self; }

    public double getAlpha() {
        return alpha;
    }

    public double getBeta() {
        return beta;
    }
    // increments the counter
    public void incrementCounter() {
        counter++;
    }

    // if the counter has reached its maximum value maxCounter of ticks
    public boolean counterFinished() {
        return counter >= MAX_COUNTER;
    }

    // checks if the agent has sent value
    public boolean checkSentAgent(int id) {
        return sent.get(id);
    }
    //
    public boolean isUpdatedByAid(int aid) {
        return updatedAgentsList.get(aid);
    }

    public void setUpdatedByAid(int aid) {
        updatedAgentsList.set(aid, true);
    }

    public boolean isAllUpdated() {
        return !updatedAgentsList.contains(false);
    }

    public void resetUpdatedList() {
        updatedAgentsList = new ArrayList<>(Collections.nCopies(AGENT_NUMBER, false));
    }

    /** A method that checks if all agents sent their values.*/
    public boolean checkAllSentAgents() {
        return !sent.contains(false);
    }

    // sets the sent agent
    public void setSentAgent(int id) {
        sent.set(id, true);
    }

    // gets the agent value
    public double getAgentValue(int id) {
        return agentValues.get(id);
    }

    // sets the agent value
    public void setAgentValue(int id, double val) {
        agentValues.set(id, val);
    }

    // A method that gets the 'u' value
    public double getUValue(int id) {
        return uList.get(id);
    }

    // A method that sets the 'u' value
    public void setUValue(int id, double val) {
        uList.set(id, val);
    }

    // A method that resets the sent agents list
    public void resetSentList() {
        sent = new ArrayList<>(Collections.nCopies(AGENT_NUMBER, false));
    }

    // A method that resets the 'u' list
    public void resetUS() {
        uList = new ArrayList<>(Collections.nCopies(AGENT_NUMBER, 0.0));
    }
}
