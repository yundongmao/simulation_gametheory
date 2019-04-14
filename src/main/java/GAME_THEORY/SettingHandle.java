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
//        for(int pi=1;pi<11;pi++){
//            double p = pi/10.0;
//            for(int r=20;r<51;r++){
//                for(int N = 10;N<40;N++){
//                    System.out.println(N+" size graph");
//                    int totalsuccess = 0;
//                    for(int i=0;i<100;i++){
//                        graph = GraphHandle.generateErdoRandomGraphUndirect(p, N, 1, r/10.0);
//                        if(!graph.isConnected()){
//                            i--;
//                            continue;
//                        }
//                        Setting setting = new Setting(graph, 100, ProcessType.BD);
//                        setting.runTest();
//                        totalsuccess+=setting.getSuccessTimes();
//                    }
//                    jsonObject.put("fixation_prob",totalsuccess/(100*100.0));
//                    jsonObject.put("type","ErdoRandomGraph");
//                    jsonObject.put("p",p);
//                    jsonObject.put("init_mutant_num",1);
//                    jsonObject.put("reward",r/10.0);
//                    jsonObject.put("size",N);
//                    jsonObject.put("total_test",100*100);
//                    jsonObject.put("total_success_times",totalsuccess);
//                    FileUtil.writeStringToFile("simulation_0002", true,jsonObject.toJSONString()+"\n");
//                }
//            }
//
//        }


//        graph = GraphHandle.generateErdoRandomGraphUndirect(0.5, 10, 1, 2.0);

//        JSONObject jsonObject = new JSONObject();
//        for (int N = 10; N < 20; N++) {
//            System.out.println(N + " size graph");
//            for (int r = 20; r < 21; r++) {
//                for (int K = 4; K < N/2; K+=2) {
//                    for(int Bi=1;Bi<11;Bi++) {
//                        double B = Bi/10.0;
//                        int totalsuccess = 0;
//                        for (int i = 0; i < 100; i++) {
//                            graph = GraphHandle.generateWattsStrogatzGraphUndirect(N, K, B, 1, r/10.0);
//                            if (!graph.isConnected()) {
//                                i--;
//                                continue;
//                            }
//                            Setting setting = new Setting(graph, 100, ProcessType.BD);
//                            setting.runTest();
//                            totalsuccess += setting.getSuccessTimes();
//                        }
//                        jsonObject.put("fixation_prob", totalsuccess / (100 * 100.0));
//                        jsonObject.put("type", "WattsStrogatz");
//                        jsonObject.put("B", B);
//                        jsonObject.put("K", K);
//                        jsonObject.put("init_mutant_num", 1);
//                        jsonObject.put("reward", r / 10.0);
//                        jsonObject.put("size", N);
//                        jsonObject.put("total_test", 100 * 100);
//                        jsonObject.put("total_success_times", totalsuccess);
//                        FileUtil.writeStringToFile("simulation_WattsStrogatz", true, jsonObject.toJSONString() + "\n");
//                    }
//
//                }
//            }
//        }

//
        JSONObject jsonObject = new JSONObject();
        for (int initNode = 2; initNode < 3; initNode++) {
            for (int N = 10; N < 20; N++) {
                System.out.println(N + " size graph");
                for (int r = 20; r < 21; r++) {
                    for (int outD = 3; outD < N; outD++) {
                        int totalsuccess = 0;
                        for (int i = 0; i < 100; i++) {
                            graph = GraphHandle.generateBaraAlbertGraph(outD, outD, N, 1, r/10.0);
                            if (!graph.isConnected()) {
                                i--;
                                continue;
                            }
                            Setting setting = new Setting(graph, 100, ProcessType.BD);
                            setting.runTest();
                            totalsuccess += setting.getSuccessTimes();
                        }
                        jsonObject.put("fixation_prob", totalsuccess / (100 * 100.0));
                        jsonObject.put("type", "WattsStrogatz");
                        jsonObject.put("outDegree", outD);
                        jsonObject.put("initNode", outD);
                        jsonObject.put("init_mutant_num", 1);
                        jsonObject.put("reward", r / 10.0);
                        jsonObject.put("size", N);
                        jsonObject.put("total_test", 100 * 100);
                        jsonObject.put("total_success_times", totalsuccess);
                        FileUtil.writeStringToFile("simulation_BaraAlbert", true, jsonObject.toJSONString() + "\n");

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
