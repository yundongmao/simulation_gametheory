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
            double rowsum = 0;
            for(int k =0;k<N;k++){
                if(k==j){
                    tempList.add(0.0);
                }else{
                    tempList.add(random.nextDouble()<=p?1.0:0.0);
                    rowsum+= tempList.get(k);
                }
            }
            if(rowsum == 0){
                tempList.set(j,1.0);
                rowsum = 1.0;
            }
            for(int k =0;k<N;k++){
                tempList.set(k,tempList.get(k)/rowsum);
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
