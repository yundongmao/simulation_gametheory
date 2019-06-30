package GAME_THEORY;

import GAME_THEORY.Graphs.Graph;
import GAME_THEORY.Graphs.GraphGeneral;
import GAME_THEORY.enums.ProcessType;
import GAME_THEORY.utils.FileUtil;
import GAME_THEORY.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TransferQueue;

public class SettingHandle {
    private static List settings = null;
    private static int index = 0;
    private static int i = 0;

    public static void initAllSetting(String setting_path) {
        if (StringUtil.isNullorEmpty(setting_path)) {
            return;
        }
        settings = new ArrayList<Setting>();
    }

    public static void generateDefaultSetting() {
        settings = new ArrayList<Setting>();
    }

    public synchronized static Setting readSetting() {
        return new Setting(null, 100, ProcessType.BD);
    }

    public synchronized static void test(String name) {
        System.out.println(name + " " + i++);
    }

    public static Graph generateFromJson(String jsonstr) {
        JSONObject jsonObject = JSONObject.parseObject(jsonstr);
        String type = jsonObject.getString("type");
        if ("general".equals(type)) {
            GraphGeneral.generateFromJson(jsonstr);
        }
        return null;
    }

//    public static void main(String[] args) {
//        Graph graph = null;
//        JSONObject jsonObject = new JSONObject();
//        for(int pi=1;pi<11;pi++){
//            double p = pi/10.0;
//            for(int r=50;r<51;r++){
//                for(int N = 6;N<51;N+=1){
//                    System.out.println(N+" size graph");
//                    int totalsuccess = 0;
//                    int testTimes = 1000;
//                    int numberOfGraphs = 1000;
//                    for(int i=0;i<numberOfGraphs;i++){
//                        graph = GraphHandle.generateErdoRandomGraphUndirect(p, N, 1, r/10.0);
//                        if(!graph.isConnected()){
//                            i--;
//                            continue;
//                        }
////                        Setting setting = new Setting(graph, testTimes, ProcessType.DB);
////                        setting.runTest();
////                        totalsuccess+=setting.getSuccessTimes();
//                        FileUtil.writeStringToFile("simulation_Erdo_graph_000", true,graph.toJSONString()+"\n");
//
//                    }
////                    jsonObject.put("fixation_prob",totalsuccess/(testTimes*numberOfGraphs*1.0));
////                    jsonObject.put("type","ErdoRandomGraph");
////                    jsonObject.put("p",p);
////                    jsonObject.put("init_mutant_num",1);
////                    jsonObject.put("reward",r/10.0);
////                    jsonObject.put("size",N);
////                    jsonObject.put("total_test",testTimes*numberOfGraphs*1.0);
////                    jsonObject.put("total_success_times",totalsuccess);
////                    FileUtil.writeStringToFile("simulation_week8_Erdo_DB_001_p0.5", true,jsonObject.toJSONString()+"\n");
////                    System.out.println("N: "+N+", fixation prob: "+totalsuccess/(testTimes*numberOfGraphs*1.0));
//                }
//            }
//        }
//
////        JSONObject jsonObject = new JSONObject();
////        for(int pi=5;pi<6;pi++){
////            double p = pi/10.0;
////            for(int r=50;r<51;r++){
////                for(int N = 30;N<31;N+=1){
////                    System.out.println(N+" size graph");
////                    int totalsuccess = 0;
////                    int testTimes = 10;
////                    int numberOfGraphs = 10000;
////                    for(int i=0;i<numberOfGraphs;i++){
////                        graph = GraphHandle.generateErdoRandomGraphUndirect(p, N, 1, r/10.0);
////                        if(!graph.isConnected()){
////                            i--;
////                            continue;
////                        }
////                        Setting setting = new Setting(graph, testTimes, ProcessType.DB);
////                        setting.runTest();
////                        totalsuccess+=setting.getSuccessTimes();
////                        if(i%10 == 9){
////                            jsonObject.put("fixation_prob",totalsuccess/((i+1)*testTimes*1.0));
////                            jsonObject.put("type","ErdoRandomGraph");
////                            jsonObject.put("p",p);
////                            jsonObject.put("init_mutant_num",1);
////                            jsonObject.put("reward",r/10.0);
////                            jsonObject.put("size",N);
////                            jsonObject.put("total_test",testTimes*numberOfGraphs*1.0);
////                            jsonObject.put("total_success_times",totalsuccess);
////                            jsonObject.put("times",i*testTimes);
////                            FileUtil.writeStringToFile("simulation_week10_Erdo_DB_error_001", true,jsonObject.toJSONString()+"\n");
////                            System.out.println("N: "+N+", fixation prob: "+totalsuccess/((i+1)*testTimes*1.0));
////                        }
////
////                    }
////
////                }
////            }
////        }
//
//
//
////        graph = GraphHandle.generateErdoRandomGraphUndirect(0.5, 10, 1, 2.0);
////
////        JSONObject jsonObject = new JSONObject();
////        for (int N = 10; N < 51; N+=1) {
////            System.out.println(N + " size graph");
////            int testTimes = 1000;
////            int numberOfGraphs = 1000;
////            for (int r = 50; r < 51; r++) {
////                for (int K = 4; K < N/2; K+=2) {
////                    System.out.println("K: "+K);
////                    for(int Bi=8;Bi<9;Bi++) {
////                        double B = Bi/10.0;
////                        int totalsuccess = 0;
////                        for (int i = 0; i < numberOfGraphs; i++) {
////                            graph = GraphHandle.generateWattsStrogatzGraphUndirect(N, K, B, 1, r/10.0);
////                            if (!graph.isConnected()) {
////                                i--;
////                                continue;
////                            }
//////                            Setting setting = new Setting(graph, testTimes, ProcessType.DB);
//////                            setting.runTest();
//////                            totalsuccess += setting.getSuccessTimes();
////                            FileUtil.writeStringToFile("simulation_WattsStrogatz_graph_000", true, graph.toJSONString() + "\n");
////                        }
//////                        jsonObject.put("fixation_prob", totalsuccess / (testTimes*numberOfGraphs*1.0));
//////                        jsonObject.put("type", "WattsStrogatz");
//////                        jsonObject.put("B", B);
//////                        jsonObject.put("K", K);
//////                        jsonObject.put("init_mutant_num", 1);
//////                        jsonObject.put("reward", r / 10.0);
//////                        jsonObject.put("size", N);
//////                        jsonObject.put("total_test", testTimes*numberOfGraphs*1.0);
//////                        jsonObject.put("total_success_times", totalsuccess);
//////                        FileUtil.writeStringToFile("simulation_WattsStrogatz_week10_DB_000", true, jsonObject.toJSONString() + "\n");
//////                        System.out.println("N: "+N+", fixation prob: "+totalsuccess/(testTimes*numberOfGraphs*1.0));
////                    }
////                }
////            }
////        }
//
////
////        JSONObject jsonObject = new JSONObject();
////        for (int initNode = 2; initNode < 3; initNode++) {
////            for (int N = 110; N < 201; N+=20) {
////                int testTimes = 1000;
////                int numberOfGraphs = 1000;
////                System.out.println(N + " size graph");
////                for (int r = 50; r < 51; r++) {
////                    for (int outD = 4; outD < 50; outD++) {
////                        int totalsuccess = 0;
////                        for (int i = 0; i < numberOfGraphs; i++) {
////                            graph = GraphHandle.generateBaraAlbertGraph(outD, outD, N, 1, r/10.0);
////                            if (!graph.isConnected()) {
////                                i--;
////                                continue;
////                            }
////                            Setting setting = new Setting(graph, testTimes, ProcessType.BD);
////                            setting.runTest();
////                            totalsuccess += setting.getSuccessTimes();
////                        }
////                        jsonObject.put("fixation_prob", totalsuccess / (testTimes*numberOfGraphs*1.0));
////                        jsonObject.put("type", "WattsStrogatz");
////                        jsonObject.put("outDegree", outD);
////                        jsonObject.put("initNode", outD);
////                        jsonObject.put("init_mutant_num", 1);
////                        jsonObject.put("reward", r / 10.0);
////                        jsonObject.put("size", N);
////                        jsonObject.put("total_test", testTimes*numberOfGraphs*1.0);
////                        jsonObject.put("total_success_times", totalsuccess);
////                        FileUtil.writeStringToFile("simulation_BaraAlbert_week10_BD_004", true, jsonObject.toJSONString() + "\n");
////                        System.out.println("N: "+N+", fixation prob: "+totalsuccess/(testTimes*numberOfGraphs*1.0));
////                    }
////                }
////            }
////        }
//
//
////        JSONObject jsonObject = new JSONObject();
////        for (int initNode = 2; initNode < 3; initNode++) {
////            for (int N = 10; N < 50; N+=1) {
////                int testTimes = 1000;
////                int numberOfGraphs = 1000;
////                System.out.println(N + " size graph");
////                for (int r = 50; r < 51; r++) {
////                    for (int outD = 4; outD < N; outD++) {
////                        int totalsuccess = 0;
////                        for (int i = 0; i < numberOfGraphs; i++) {
////                            graph = GraphHandle.generateBaraAlbertGraph(outD, outD, N, 1, r/10.0);
////
////                            if (!graph.isConnected()) {
////                                i--;
////                                continue;
////                            }
////                            FileUtil.writeStringToFile("simulation_BaraAlbert_week10_graph_000", true, graph.toJSONString() + "\n");
//////                            Setting setting = new Setting(graph, testTimes, ProcessType.BD);
//////                            setting.runTest();
//////                            totalsuccess += setting.getSuccessTimes();
////                        }
//////                        jsonObject.put("fixation_prob", totalsuccess / (testTimes*numberOfGraphs*1.0));
//////                        jsonObject.put("type", "WattsStrogatz");
//////                        jsonObject.put("outDegree", outD);
//////                        jsonObject.put("initNode", outD);
//////                        jsonObject.put("init_mutant_num", 1);
//////                        jsonObject.put("reward", r / 10.0);
//////                        jsonObject.put("size", N);
//////                        jsonObject.put("total_test", testTimes*numberOfGraphs*1.0);
//////                        jsonObject.put("total_success_times", totalsuccess);
//////                        FileUtil.writeStringToFile("simulation_BaraAlbert_week10_BD_graph_004", true, jsonObject.toJSONString() + "\n");
////
//////                        System.out.println("N: "+N+", fixation prob: "+totalsuccess/(testTimes*numberOfGraphs*1.0));
////                    }
////                }
////            }
////        }
//
//
//
////        graph = GraphHandle.generateBaraAlbertGraph(4, 4, 10, 1, 2);
////
////        System.out.println(((GraphGeneral) graph).getAdjaMatrix());
////        graph= GraphHandle.generateBaraAlbertGraph(2,3,10,1,2.0);
////                    System.out.println(((GraphGeneral) graph).getAdjaMatrix());
//
////        System.out.println(((GraphGeneral)graph).getAdjaMatrix());
////        System.out.println(graph.toJSONString());
////        System.out.println("asdfasdf");
//    }


    public static void main(String[] args) {

        int testTimes = 1000;
        try {
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream("../../project/data/simulation_WattsStrogatz_graph_000"));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            int count = 0;
            while (line != null) {
                count++;

                line = br.readLine();
                JSONObject jsonObject = new JSONObject();
                GraphGeneral graph = GraphGeneral.generateFromJson(line);
                int N = graph.getN();
                int totalsuccess = 0;


                Setting setting = new Setting(graph, testTimes, ProcessType.DB);
                setting.runTest();
                totalsuccess += setting.getSuccessTimes();
//                System.out.println(calculateAveClusteringCoefficient(graph));
                jsonObject.put("average_clustering_coefficient", calculateAveClusteringCoefficient(graph));
                jsonObject.put("fixation_prob", totalsuccess / (testTimes * 1.0));
                jsonObject.put("type", "WattsGraph");
                jsonObject.put("init_mutant_num", 1);
                jsonObject.put("size", N);
                jsonObject.put("total_test", testTimes * 1.0);
                jsonObject.put("total_success_times", totalsuccess);
                jsonObject.put("ave_degree", calculateAveDegree(graph));

                FileUtil.writeStringToFile("simulation_ave_clustering_coefficient_Watts_DB_001", true, jsonObject.toJSONString() + "\n");
                if (count % 100 == 0) {
                    System.out.println("N: " + N + ", fixation prob: " + totalsuccess / (testTimes * 1.0));
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

//    public static void main(String[] args) {
//
//        int testTimes = 1000;
//        try {
//            InputStreamReader reader = new InputStreamReader(
//                    new FileInputStream("../../project/data/simulation_Erdo_graph_000"));
//            BufferedReader br = new BufferedReader(reader);
//            String line = br.readLine();
//            int count = 0;
//            while (line != null) {
//                count++;
//
//                line = br.readLine();
//                JSONObject jsonObject = new JSONObject();
//                GraphGeneral graph = GraphGeneral.generateFromJson(line);
//
//
//                FileUtil.writeStringToFile("simulation_ave_degree_Erdo_DB_000", true, ""+calculateAveDegree(graph) + "\n");
//                if (count % 1000 == 0) {
//                    System.out.println(count);
//                }
//            }
//            br.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//
//    }

    public static double calculateAveDegree(GraphGeneral graphGeneral){
        List<List<Double>> matrix = graphGeneral.getAdjaMatrix();
        int result =0;
        for (int i=0;i<matrix.size();i++){
            for(int j=i+1;j<matrix.size();j++){
                if(matrix.get(i).get(j)>0){
                    result+=1;
                }
            }
        }
        return result/(matrix.size()*1.0);
    }


    public static double calculateAveClusteringCoefficient(GraphGeneral graphGeneral) {
        double totalScore = 0;
        for (int i = 0; i < graphGeneral.getAdjaMatrix().size(); i++) {
            totalScore += calculateLocalClusteringCoefficient(graphGeneral, i);
        }
        return totalScore / (graphGeneral.getAdjaMatrix().size() * 1.0);
    }

    public static double calculateLocalClusteringCoefficient(GraphGeneral graph, int node) {
        List<List<Double>> matrix = graph.getAdjaMatrix();
        int neighbours = 0;
        List<Integer> nodeList = new ArrayList<>();
        for (int j = 0; j < matrix.size(); j++) {
            if (matrix.get(node).get(j) > 0) {
                neighbours++;
                nodeList.add(j);
            }
        }
        int totalEdges = 0;
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = i + 1; j < nodeList.size(); j++) {
                if (matrix.get(i).get(j) > 0) {
                    totalEdges++;
                }
            }
        }
//        System.out.println(neighbours);
        if (neighbours == 1) {
            return 0;
        }
        return totalEdges / (neighbours * (neighbours - 1) / 2.0 * 1.0);

    }


}
