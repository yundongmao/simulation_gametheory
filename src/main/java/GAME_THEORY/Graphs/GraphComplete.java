package GAME_THEORY.Graphs;

import java.util.*;

public class GraphComplete extends Graph {
    public GraphComplete(int _initMutantNum, double _reward, int _size) {
        super(_initMutantNum, _reward, _size);
        mutantSet = new HashSet<Integer>();
        mutantList = new ArrayList<Integer>();

        for(int j=0;mutantSet.size()<initMutantNum; j++){
            int temp = random.nextInt(N);
            if(mutantSet.contains(temp)){
                continue;
            }else{
                mutantSet.add(temp);
                mutantList.add(temp);
            }

        }
    }
    private Set mutantSet;
    private List mutantList;
    private Random random = new Random(System.currentTimeMillis());
    @Override
    public boolean update() {
        //decide mutant or normal node to reproduce
        double temp = random.nextDouble();
        if (temp < i * r / (i * r + N - i)) {
            //decide which mutant to reproduce randomly
            int index = random.nextInt(mutantList.size());
//            mutantList[index]


        } else {
            //decide which normal node to reproduce
            int temp2 = random.nextInt(N - 1) + 1;
            if (temp2 <= i) {
                i--;
            }
        }
        return false;
    }

    @Override
    public void reinit() {
        super.reinit();
    }

    @Override
    public boolean isSuccess() {
        return false;
    }
}
