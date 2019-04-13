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
//        for(int i=0;;i++){
        JSONObject jsonObject = new JSONObject();
        for(double p=0.1;p<=1.0;p+=0.1){
            for(int r=10;r<50;r++){
                for(int N = 10;N<12;N++){
                    int totalsuccess = 0;
                    for(int i=0;i<100;i++){
                        graph = GraphHandle.generateErdoRandomGraphUndirect(p, N, 1, r/10.0);
                        if(!graph.isConnected()){
                            i--;
                            continue;
                        }
                        Setting setting = new Setting(graph, 100, ProcessType.BD);
                        setting.runTest();
                        totalsuccess+=setting.getSuccessTimes();
                        FileUtil.writeStringToFile("simulation_0001", true,jsonObject.toJSONString());
                    }
                    jsonObject.put("fixation_prob",totalsuccess/100*100.0);
                    jsonObject.put("type","ErdoRandomGraph");
                    jsonObject.put("p",p);
                    jsonObject.put("init_mutant_num",1);
                    jsonObject.put("reward",r/10.0);
                    jsonObject.put("size",N);
                    jsonObject.put("test_times",100);
                    break;
                }
            }

        }


//        graph = GraphHandle.generateErdoRandomGraphUndirect(0.5, 10, 1, 2.0);




//        graph= GraphHandle.generateWattsStrogatzGraph(10,4,0.5,1,2.0);
//        graph= GraphHandle.generateBaraAlbertGraph(2,3,10,1,2.0);
//        System.out.println(((GraphGeneral)graph).getAdjaMatrix());





//        System.out.println(((GraphGeneral)graph).getAdjaMatrix());
//        System.out.println(graph.toJSONString());
//        System.out.println("asdfasdf");
    }

}
