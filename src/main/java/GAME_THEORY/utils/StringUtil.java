package GAME_THEORY.utils;

public class StringUtil {
    public static boolean isNullorEmpty(String s){
        if(s== null){
            return true;
        }
        if("".equals(s)){
            return true;
        }
        return false;
    }
}
