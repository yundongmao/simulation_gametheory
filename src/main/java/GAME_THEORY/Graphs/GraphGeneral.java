package GAME_THEORY.Graphs;

import GAME_THEORY.utils.RandomUtil;

import java.util.*;

public class GraphGeneral extends Graph {
    public GraphGeneral(int _initMutantNum, double _reward, int _size ,List<List<Double>> _adjaMatrix) {
        super(_initMutantNum, _reward, _size);
        mutantSet = new HashSet<Integer>();
        normalSet = new HashSet<Integer>();
        for(int j =0;j<N;j++){
            normalSet.add(j);
        }

//        adjaMatrix = new ArrayList<List<Double>>();
//        for(int j =0;j<N;j++){
//            adjaMatrix.add(new ArrayList<Double>());
//        }

        adjaMatrix = _adjaMatrix;

        while(mutantSet.size()<initMutantNum){
            int temp = random.nextInt(N);
            if(mutantSet.contains(temp)){
                continue;
            }else{
                mutantSet.add(temp);
                normalSet.remove(temp);
            }
        }
    }
    private Set<Integer> mutantSet;
    private Set<Integer> normalSet;


    private List<List<Double>> adjaMatrix;
    private Random random = new Random(System.currentTimeMillis());
    @Override
    public boolean update() {
        if(mutantSet.size() == N || mutantSet.size() == 0){
            return true;
        }
        //decide mutant or normal node to reproduce
        double temp = random.nextDouble();
        if (temp < i * r / (i * r + N - i)) {
            //decide which mutant to reproduce randomly
            int reproNode = (int)RandomUtil.RandomChooseFromSet(mutantSet);
            int deadNode = RandomUtil.randomNeighborFromList(reproNode,adjaMatrix.get(reproNode));
            if(!mutantSet.contains(deadNode)){
                mutantSet.add(deadNode);
                normalSet.remove(deadNode);
            }
        } else {
            int reproNode  = (int)RandomUtil.RandomChooseFromSet(normalSet);
            int deadNode = RandomUtil.randomNeighborFromList(reproNode,adjaMatrix.get(reproNode));
            if(!normalSet.contains(deadNode)){
                normalSet.add(deadNode);
                mutantSet.remove(deadNode);
            }
            //decide which normal node to reproduce
        }
        return false;
    }

    @Override
    public void reinit() {
        super.reinit();
    }

    @Override
    public boolean isSuccess() {
        return mutantSet.size()==N?true:false;
    }



    public List<List<Double>> getAdjaMatrix() {
        return adjaMatrix;
    }

    public void setAdjaMatrix(List<List<Double>> adjaMatrix) {
        this.adjaMatrix = adjaMatrix;
    }
}
