package GAME_THEORY.Graphs;

public abstract class Graph{


    //default we suppose the mutant number is only one
    protected int mutantNum= 1;
    //the mutant reward and we suppose the normal node has the reward 1.0;
    //we just casually choose the value 2.0 for the mutant reward
    protected double reward = 2.0;


    //if the process is end return true
    public abstract boolean update();

    public abstract void reinit();

    //when the graph become stable, absorb state;
    //if the mutant occupy the whole graph then return true
    //if not return false;
    public abstract boolean isSuccess();



    public int getMutantNum() {
        return mutantNum;
    }

    public void setMutantNum(int mutantNum) {
        this.mutantNum = mutantNum;
    }
    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }
}
