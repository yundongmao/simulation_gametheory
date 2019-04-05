package GAME_THEORY;

import java.util.List;
import java.util.Random;

public class Game {
    public static void main(String[] args) {
//        SettingHandle.initAllSetting("setting.txt");
//        System.out.println("hello");
//        GameWorker gameWorker1 = new GameWorker("work_1");
//        gameWorker1.start();
//        GameWorker gameWorker2 = new GameWorker("work_2");
//        gameWorker2.start();

        sampleGameTest();
    }

    public static void sampleGameTest() {
        int i = 1;//mutant number
        double r = 2.0;//reward
        int N = 10;//total number of the node
        int testTimes = 100;
        int success = 0;
        int fail = 0;
        Random random = new Random(System.currentTimeMillis());
        for (int k = 0; k < testTimes; k++) {
            i = 1;
            for (int j = 0;; j++) {
                if(i == N){
                    success++;
                    break;
                }
                if (i == 0){
                    fail ++;
                    break;
                }

                double temp = random.nextDouble();
//                System.out.println(temp);
                if(temp < i*r/(i*r+N-i)){
                    int temp2 = random.nextInt(N-1)+1;
                    if(temp2 <= N -i){
                        i++;
                    }
                }else{
                    int temp2 = random.nextInt(N-1)+1;
                    if(temp2 <= i){
                        i--;
                    }
                }
            }
        }
        System.out.println(success);
        System.out.println(fail);

    }


}
