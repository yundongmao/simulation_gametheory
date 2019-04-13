package GAME_THEORY;

import GAME_THEORY.Graphs.Graph;
import GAME_THEORY.Graphs.GraphGeneral;
import GAME_THEORY.utils.RandomUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GraphHandle {
    private static Random random = new Random(System.currentTimeMillis());

    public static void generateRamdomGraphs() {

    }

    /**
     * @param p the probability of an edge exiting
     * @param N the number of the nodes
     * @return class of the graph
     */
    public static Graph generateErdoRandomGraph(double p, int N, int mutantnum, double reward) {
        List<List<Double>> adjaMatrix = new ArrayList<>();
        for (int j = 0; j < N; j++) {
            List<Double> tempList = new ArrayList<>();
            double rowsum = 0;
            for (int k = 0; k < N; k++) {
                if (k == j) {
                    tempList.add(0.0);
                } else {
                    tempList.add(random.nextDouble() <= p ? 1.0 : 0.0);
                    rowsum += tempList.get(k);
                }
            }
            if (rowsum == 0) {
                tempList.set(j, 1.0);
                rowsum = 1.0;
            }
            for (int k = 0; k < N; k++) {
                tempList.set(k, tempList.get(k) / rowsum);
            }

            adjaMatrix.add(tempList);
        }
        Graph graph = new GraphGeneral(mutantnum, reward, N, adjaMatrix);
        return graph;
    }

    public static Graph generateErdoRandomGraphUndirect(double p, int N, int mutantnum, double reward) {
        List<List<Double>> adjaMatrix = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<Double> tempList = new ArrayList<>(Collections.nCopies(N, 0.0));
            adjaMatrix.add(tempList);
        }


        for (int j = 0; j < N; j++) {
            for (int k = j + 1; k < N; k++) {
                double temp = random.nextDouble() <= p ? 1.0 : 0.0;
                adjaMatrix.get(j).set(k,temp);
                adjaMatrix.get(k).set(j,temp);
            }
        }
        GraphGeneral.normaliseRow(adjaMatrix);
        Graph graph = new GraphGeneral(mutantnum, reward, N, adjaMatrix);
        return graph;
    }


    /**
     * @param N         node number
     * @param K         should be even
     * @param B         probability rewrite the right most link
     * @param mutantnum init mutant number
     * @param reward    mutant reward
     * @return
     */
    public static Graph generateWattsStrogatzGraph(int N, int K, double B, int mutantnum, double reward) {
        if (K % 2 == 1) {
            System.out.println("K should be even not " + K);
            return null;
        }
        List<List<Double>> adjaMatrix = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<Double> tempList = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (j == i) {
                    tempList.add(0.0);
                } else if (j > i) {
                    if (j - i <= K / 2 || i + N - j <= K / 2) {
                        tempList.add(1.0);
                    } else {
                        tempList.add(0.0);
                    }
                } else {
                    if (i - j <= K / 2 || j + N - i <= K / 2) {
                        tempList.add(1.0);
                    } else {
                        tempList.add(0.0);
                    }
                }
            }
            adjaMatrix.add(tempList);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j > i) {
                    if (j - i <= K / 2) {
                        if (random.nextDouble() <= B) {
                            adjaMatrix.get(i).set(j, 0.0);
                            while (true) {
                                int temp = random.nextInt(N);
                                if (temp == i) {
                                    continue;
                                }
                                if (adjaMatrix.get(i).get(temp) == 1.0) {
                                    continue;
                                }
                                adjaMatrix.get(i).set(temp, 1.0);
                                break;
                            }
                        }
                    }
                } else if (j < i) {
                    if (j + N - i <= K / 2) {
                        if (random.nextDouble() <= B) {
                            adjaMatrix.get(i).set(j, 0.0);
                            while (true) {
                                int temp = random.nextInt(N);
                                if (temp == i) {
                                    continue;
                                }
                                if (adjaMatrix.get(i).get(temp) == 1.0) {
                                    continue;
                                }
                                adjaMatrix.get(i).set(temp, 1.0);
                                break;
                            }
                        }
                    }
                }
            }
        }
        GraphGeneral.normaliseRow(adjaMatrix);
        Graph graph = new GraphGeneral(mutantnum, reward, N, adjaMatrix);
        return graph;
    }

    /**
     * @param initNodes
     * @param outDegrees
     * @param N
     * @param mutantnum
     * @param reward
     * @return
     */
    public static Graph generateBaraAlbertGraph(int initNodes, int outDegrees, int N, int mutantnum, double reward) {
        List<List<Double>> adjaMatrix = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<Double> tempList = new ArrayList<>(Collections.nCopies(N, 0.0));
            adjaMatrix.add(tempList);
        }
        double[] sumList = new double[N];
        for (int i = 0; i < initNodes - 1; i++) {
            adjaMatrix.get(i).set(i + 1, 1.0);
            adjaMatrix.get(i + 1).set(i, 1.0);
            sumList[i] += 1;
            sumList[i + 1] += 1;
        }


        for (int i = initNodes; i < N; i++) {
            for (int j = 0; j < outDegrees; j++) {
                int index = RandomUtil.randomChooseIndexDepValue(sumList);
                if (adjaMatrix.get(i).get(index) != 0) {
                    j -= 1;
                    continue;
                }
                adjaMatrix.get(i).set(index, 1.0);
                adjaMatrix.get(index).set(i, 1.0);
                sumList[i] += 1;
                sumList[index] += 1;
            }
        }

        GraphGeneral.normaliseRow(adjaMatrix);
        Graph graph = new GraphGeneral(mutantnum, reward, N, adjaMatrix);

        return graph;
    }

    public static void generateGraphs(String settting_path) {

    }


    public static void main(String[] args) {
//        System.out.println(((GraphGeneral)generateErdoRandomGraph(0.5,10,1,2.0)).getAdjaMatrix());
//        System.out.println(((GraphGeneral)generateWattsStrogatzGraph(10,4,0.5,1,2.0)).getAdjaMatrix());
        System.out.println(((GraphGeneral) generateBaraAlbertGraph(2, 2, 10, 1, 2.0)).getAdjaMatrix());
    }
}
