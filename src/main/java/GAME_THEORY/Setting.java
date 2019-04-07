package GAME_THEORY;

import GAME_THEORY.Graphs.Graph;
import GAME_THEORY.enums.ProcessType;

public class Setting {
//    public Type type = Type.COMPLETE;

    private Graph graph;
    private int testTimes;
    private ProcessType processType;

    public int getSuccessTimes() {
        return successTimes;
    }

    public void setSuccessTimes(int successTimes) {
        this.successTimes = successTimes;
    }

    private int successTimes;


    public boolean update() {
        return graph.updateBD();
    }

    public boolean isSuccess() {
        return graph.isSuccess();
    }

    public void runTest() {
        if (processType.equals(ProcessType.BD)) {
            for (int i = 0; i < testTimes; i++) {
                System.out.println("BD " + i + "th test");
                runOneTestBD();
            }
        }else if(processType.equals(ProcessType.DB)){
            for (int i = 0; i < testTimes; i++) {
                System.out.println("DB " + i + "th test");
                runOneTestDB();
            }
        }

    }

    public void runOneTestBD() {
        graph.reinit();
        while (true) {
            if (graph.updateBD()) {
                successTimes = graph.isSuccess() ? successTimes + 1 : successTimes;
                break;
            }
        }
    }

    public void runOneTestDB() {
        graph.reinit();
        while (true) {
            if (graph.updateDB()) {
                successTimes = graph.isSuccess() ? successTimes + 1 : successTimes;
                break;
            }
        }
    }


//  abort this construct.
//    public Setting(Type _type){
//        graph = null;
//    }

    public Setting(Graph _graph, int _testTimes, ProcessType _processType) {
        graph = _graph;
        testTimes = _testTimes;
        successTimes = 0;
        processType = _processType;

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
