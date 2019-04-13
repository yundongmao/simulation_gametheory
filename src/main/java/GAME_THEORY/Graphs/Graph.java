package GAME_THEORY.Graphs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph{

    public Graph(int _initMutantNum,double _reward,int _size){
        initMutantNum = _initMutantNum;
        i = initMutantNum;
        r =  _reward;
        N = _size;
    }

    public int getInitMutantNum() {
        return initMutantNum;
    }

    public void setInitMutantNum(int initMutantNum) {
        this.initMutantNum = initMutantNum;
    }

    protected int initMutantNum = 1;

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    //default we suppose the mutant number is only one
    //i is the mutant number
    protected int i = 1;

    //the mutant reward and we suppose the normal node has the reward 1.0;
    //we just casually choose the value 2.0 for the mutant reward
    //r is the reward of the mutant
    protected double r = 2.0;
    //graph size
    protected int N = 0;


    //if the process is end return true
    public abstract boolean updateBD();
    public abstract boolean updateDB();

    public void reinit(){
        i = initMutantNum;
    }

    //when the graph become stable, absorb state;
    //if the mutant occupy the whole graph then return true
    //if not return false;
    public abstract boolean isSuccess();

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    public double getReward() {
        return r;
    }

    public void setReward(double reward) {
        this.r = reward;
    }

    public abstract String toJSONString();

    public abstract boolean isConnected();

}
