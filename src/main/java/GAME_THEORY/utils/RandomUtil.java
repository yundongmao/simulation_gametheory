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

    public static Object RandomChooseFromSet(Set set){
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
}
