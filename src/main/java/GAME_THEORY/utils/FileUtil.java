package GAME_THEORY.utils;

import java.io.*;

public class FileUtil {
    public static void writeStringToFile(String fileName,boolean isAdd,String str){
        File file = new File(fileName);

        try  {
            if(!file.exists()){
                file.createNewFile();
            }
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file,isAdd));
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(str);
            bw.flush();
            bw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        try  {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("test.txt",true));
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write("I'm handsome\n");
            bw.flush();
            bw.close();


        }catch (Exception e){
            System.out.println(e);
        }


        try{
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream("test.txt"));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while(line !=null){
                System.out.println(line);
                line = br.readLine();
            }
            br.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
