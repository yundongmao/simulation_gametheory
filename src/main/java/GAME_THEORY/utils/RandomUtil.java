package GAME_THEORY.utils;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomUtil {
    private static Random random = new Random(System.currentTimeMillis());
    public static int randomNeighborFromList(int index, List<Double> list){
        double temp = random.nextDouble();
        double total = 0;
        while(true){
            for(int i=0;i<list.size();i++){
                total+=list.get(i);
                if(temp<=total){
                    if(i!=index){
                        return i;
                    }
                    break;
                }
            }
        }

    }

    public static Object randomChooseFromSet(Set set){
        int item = random.nextInt(set.size());
        int i = 0;
        for(Object obj : set)
        {
            if (i == item)
                return obj;
            i++;
        }
        //won't come here
        return null;
    }

    public static int randomChooseIndexDepValue(double[] list,int index){
        double temp = random.nextDouble();
        double sum = 0.0;
        for(double d : list){
            sum+=d;
        }
        sum-=list[index];
        double tempsum = 0;
        for(int i=0;i<list.length;i++){
            tempsum+=list[i];
            if(temp<=tempsum/sum){
                return i;
            }
        }
        return 0;
    }

    public static void normaliseList(double[] list){
        double sum = 0;
        for(double d:list){
            sum+=d;
        }
        for(int i =0;i<list.length;i++){
            list[i] = list[i]/sum;
        }
    }
}
