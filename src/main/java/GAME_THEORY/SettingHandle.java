package GAME_THEORY;

import GAME_THEORY.Graphs.Graph;
import GAME_THEORY.Graphs.GraphGeneral;
import GAME_THEORY.enums.ProcessType;
import GAME_THEORY.utils.FileUtil;
import GAME_THEORY.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
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

    public static void main(String[] args) {
        Graph graph = null;
//        JSONObject jsonObject = new JSONObject();
//        for(int pi=5;pi<6;pi++){
//            double p = pi/10.0;
//            for(int r=50;r<51;r++){
//                for(int N = 51;N<101;N+=1){
//                    System.out.println(N+" size graph");
//                    int totalsuccess = 0;
//                    int testTimes = 100;
//                    int numberOfGraphs = 100;
//                    for(int i=0;i<numberOfGraphs;i++){
//                        graph = GraphHandle.generateErdoRandomGraphUndirect(p, N, 1, r/10.0);
//                        if(!graph.isConnected()){
//                            i--;
//                            continue;
//                        }
//                        Setting setting = new Setting(graph, testTimes, ProcessType.DB);
//                        setting.runTest();
//                        totalsuccess+=setting.getSuccessTimes();
//                    }
//                    jsonObject.put("fixation_prob",totalsuccess/(testTimes*numberOfGraphs*1.0));
//                    jsonObject.put("type","ErdoRandomGraph");
//                    jsonObject.put("p",p);
//                    jsonObject.put("init_mutant_num",1);
//                    jsonObject.put("reward",r/10.0);
//                    jsonObject.put("size",N);
//                    jsonObject.put("total_test",testTimes*100);
//                    jsonObject.put("total_success_times",totalsuccess);
//                    FileUtil.writeStringToFile("simulation_week7_Erdo_DB_008_p0.5", true,jsonObject.toJSONString()+"\n");
//                    System.out.println("N: "+N+", fixation prob: "+totalsuccess/(testTimes*numberOfGraphs*1.0));
//                }
//            }
//
//        }


//        graph = GraphHandle.generateErdoRandomGraphUndirect(0.5, 10, 1, 2.0);
//
//        JSONObject jsonObject = new JSONObject();
//        for (int N = 100; N < 101; N+=1) {
//            System.out.println(N + " size graph");
//            int testTimes = 100;
//            int numberOfGraphs = 100;
//            for (int r = 50; r < 51; r++) {
//                for (int K = 10; K < N/2; K+=2) {
//                    System.out.println("K: "+K);
//                    for(int Bi=5;Bi<6;Bi++) {
//                        double B = Bi/10.0;
//                        int totalsuccess = 0;
//                        for (int i = 0; i < numberOfGraphs; i++) {
//                            graph = GraphHandle.generateWattsStrogatzGraphUndirect(N, K, B, 1, r/10.0);
//                            if (!graph.isConnected()) {
//                                i--;
//                                continue;
//                            }
//                            Setting setting = new Setting(graph, testTimes, ProcessType.DB);
//                            setting.runTest();
//                            totalsuccess += setting.getSuccessTimes();
//                        }
//                        jsonObject.put("fixation_prob", totalsuccess / (testTimes*numberOfGraphs*1.0));
//                        jsonObject.put("type", "WattsStrogatz");
//                        jsonObject.put("B", B);
//                        jsonObject.put("K", K);
//                        jsonObject.put("init_mutant_num", 1);
//                        jsonObject.put("reward", r / 10.0);
//                        jsonObject.put("size", N);
//                        jsonObject.put("total_test", testTimes*numberOfGraphs*1.0);
//                        jsonObject.put("total_success_times", totalsuccess);
//                        FileUtil.writeStringToFile("simulation_WattsStrogatz_DB_005", true, jsonObject.toJSONString() + "\n");
//                        System.out.println("N: "+N+", fixation prob: "+totalsuccess/(testTimes*numberOfGraphs*1.0));
//                    }
//                }
//            }
//        }

//
        JSONObject jsonObject = new JSONObject();
        for (int initNode = 2; initNode < 3; initNode++) {
            for (int N = 4; N < 101; N+=1) {
                int testTimes = 100;
                int numberOfGraphs = 100;
                System.out.println(N + " size graph");
                for (int r = 50; r < 51; r++) {
                    for (int outD = 4; outD < N; outD++) {
                        int totalsuccess = 0;
                        for (int i = 0; i < numberOfGraphs; i++) {
                            graph = GraphHandle.generateBaraAlbertGraph(outD, outD, N, 1, r/10.0);
                            if (!graph.isConnected()) {
                                i--;
                                continue;
                            }
                            Setting setting = new Setting(graph, testTimes, ProcessType.BD);
                            setting.runTest();
                            totalsuccess += setting.getSuccessTimes();
                        }
                        jsonObject.put("fixation_prob", totalsuccess / (testTimes*numberOfGraphs*1.0));
                        jsonObject.put("type", "WattsStrogatz");
                        jsonObject.put("outDegree", outD);
                        jsonObject.put("initNode", outD);
                        jsonObject.put("init_mutant_num", 1);
                        jsonObject.put("reward", r / 10.0);
                        jsonObject.put("size", N);
                        jsonObject.put("total_test", testTimes*numberOfGraphs*1.0);
                        jsonObject.put("total_success_times", totalsuccess);
                        FileUtil.writeStringToFile("simulation_BaraAlbert_BD_002", true, jsonObject.toJSONString() + "\n");
                        System.out.println("N: "+N+", fixation prob: "+totalsuccess/(testTimes*numberOfGraphs*1.0));
                        break;
                    }
                }
            }
        }

//        graph = GraphHandle.generateBaraAlbertGraph(4, 4, 10, 1, 2);
//
//        System.out.println(((GraphGeneral) graph).getAdjaMatrix());
//        graph= GraphHandle.generateBaraAlbertGraph(2,3,10,1,2.0);
//                    System.out.println(((GraphGeneral) graph).getAdjaMatrix());

//        System.out.println(((GraphGeneral)graph).getAdjaMatrix());
//        System.out.println(graph.toJSONString());
//        System.out.println("asdfasdf");
    }

}
