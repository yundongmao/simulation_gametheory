package GAME_THEORY;

import GAME_THEORY.Graphs.Graph;
import GAME_THEORY.Graphs.GraphGeneral;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphHandle {
    private static Random random = new Random(System.currentTimeMillis());
    public static void generateRamdomGraphs(){

    }

    /**
     *
     * @param p the probability of an edge exiting
     * @param N the number of the nodes
     * @return class of the graph
     */
    public static Graph generateErdoRandomGraph(double p, int N,int mutantnum,double reward){
        List<List<Double>> adjaMatrix = new ArrayList<>();
        for(int j =0;j<N;j++){
            List<Double> tempList = new ArrayList<>();
            for(int k =0;k<N;k++){
                tempList.add(random.nextDouble()<=p?1.0:0.0);
            }
            adjaMatrix.add(tempList);
        }
        Graph graph = new GraphGeneral(mutantnum,reward,N,adjaMatrix);
        return graph;
    }

    public static void generateGraphs(String settting_path){

    }

    public static void main(String[] args) {
        System.out.println(((GraphGeneral)generateErdoRandomGraph(0.5,10,1,2.0)).getAdjaMatrix());
    }
}
