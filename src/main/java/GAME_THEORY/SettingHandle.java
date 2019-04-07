package GAME_THEORY;

import GAME_THEORY.Graphs.Graph;
import GAME_THEORY.Graphs.GraphGeneral;
import GAME_THEORY.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        return new Setting(null, 100);
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
//        graph= GraphHandle.generateErdoRandomGraph(0.9,10,1,2.0);
//        graph= GraphHandle.generateWattsStrogatzGraph(10,4,0.5,1,2.0);
        graph= GraphHandle.generateBaraAlbertGraph(2,3,10,1,2.0);
        System.out.println(((GraphGeneral)graph).getAdjaMatrix());
        Setting setting = new Setting(graph,10);
        System.out.println("simulation start");
        setting.runTest();
        System.out.println("simulation end");
        System.out.println(setting.getSuccessTimes());




//        System.out.println(((GraphGeneral)graph).getAdjaMatrix());
//        System.out.println(graph.toJSONString());
//        System.out.println("asdfasdf");
    }

}
