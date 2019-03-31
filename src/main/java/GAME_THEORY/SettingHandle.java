package GAME_THEORY;

import GAME_THEORY.enums.Type;
import GAME_THEORY.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SettingHandle {
    private static List settings=null;
    private static int index = 0;
    private static int i = 0;

    public static void initAllSetting(String setting_path){
        if(StringUtil.isNullorEmpty(setting_path)){
            return;
        }
        settings = new ArrayList<Setting>();
    }

    public static void generateDefaultSetting(){
        settings = new ArrayList<Setting>();
    }

    public synchronized static Setting readSetting(){
        return new Setting(null,100);
    }

    public synchronized static void test(String name){
        System.out.println(name +" "+i++);
    }

}
