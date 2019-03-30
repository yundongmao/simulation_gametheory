package GAME_THEORY;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SettingHandle {
    private static List settings=null;
    private static int index = 0;
    private static int i = 0;

    public static void initAllSetting(String setting_path){
        if()
        settings = new ArrayList<Setting>();
    }

    public static void generateDefaultSetting(){

    }

    public synchronized static Setting readSetting(){

        return new Setting();
    }

    public synchronized static void test(String name){
        System.out.println(name +" "+i++);
    }

}
