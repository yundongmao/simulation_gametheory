package GAME_THEORY;

import GAME_THEORY.Graphs.Graph;
import GAME_THEORY.enums.Type;

public class Setting {
//    public Type type = Type.COMPLETE;

    private Graph graph;
    private int testTimes;

    public int getSuccessTimes() {
        return successTimes;
    }

    public void setSuccessTimes(int successTimes) {
        this.successTimes = successTimes;
    }

    private int successTimes;


    public boolean update(){
        return graph.update();
    }

    public boolean isSuccess(){
        return graph.isSuccess();
    }
    public void runTest(){
        for(int i=0;i<testTimes;i++){
            System.out.println(""+i+"th test");
            runOneTest();
        }
    }

    public void runOneTest(){
        graph.reinit();
        while(true){
            if(graph.update()){
                successTimes = graph.isSuccess()?successTimes+1:successTimes;
                break;
            }
        }
    }

//  abort this construct.
//    public Setting(Type _type){
//        graph = null;
//    }

    public Setting(Graph _graph,int _testTimes){
        graph = _graph;
        testTimes = _testTimes;
        successTimes = 0;
    }

//following are all setters and getters
    public int getTestTimes() {
        return testTimes;
    }

    public void setTestTimes(int testTimes) {
        this.testTimes = testTimes;
    }


//    graph setter and getter is likely useless
//    public Graph getGraph() {
//        return graph;
//    }
//
//    public void setGraph(Graph graph) {
//        this.graph = graph;
//    }


}
