package GAME_THEORY.Graphs;

import GAME_THEORY.utils.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class GraphGeneral extends Graph {
    public GraphGeneral(int _initMutantNum, double _reward, int _size, List<List<Double>> _adjaMatrix) {
        super(_initMutantNum, _reward, _size);
        adjaMatrix = _adjaMatrix;
        init();
    }

    public void init() {
        mutantSet = new HashSet<Integer>();
        normalSet = new HashSet<Integer>();
        for (int j = 0; j < N; j++) {
            normalSet.add(j);
        }

        while (mutantSet.size() < initMutantNum) {
            int temp = random.nextInt(N);
            if (mutantSet.contains(temp)) {
                continue;
            } else {
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
    public boolean updateBD() {
        if (mutantSet.size() == N || mutantSet.size() == 0) {
            return true;
        }
        //decide mutant or normal node to reproduce
        double temp = random.nextDouble();
        if (temp < i * r / (i * r + N - i)) {
//            System.out.println("choose mutant to reproduce");
            //decide which mutant to reproduce randomly
            int reproNode = (int) RandomUtil.randomChooseFromSet(mutantSet);
            int deadNode = RandomUtil.randomNeighborFromList(reproNode, adjaMatrix.get(reproNode));
            if (!mutantSet.contains(deadNode)) {
//                System.out.println("i++");
                i++;
                mutantSet.add(deadNode);
                normalSet.remove(deadNode);
            }
        } else {
//            System.out.println("choose normal to reproduce");
            int reproNode = (int) RandomUtil.randomChooseFromSet(normalSet);
            int deadNode = RandomUtil.randomNeighborFromList(reproNode, adjaMatrix.get(reproNode));
            if (!normalSet.contains(deadNode)) {
//                System.out.println("i--");
                i--;
                normalSet.add(deadNode);
                mutantSet.remove(deadNode);
            }
            //decide which normal node to reproduce
        }
        return false;
    }

    @Override
    public boolean updateDB() {
        if (mutantSet.size() == N || mutantSet.size() == 0) {
            return true;
        }

        //decide which mutant to die randomly
        int deadNode = random.nextInt(N);
        List<Double> tempList = new ArrayList<>();
        List<Double> deadNeighbourList = adjaMatrix.get(deadNode);
        for (int j = 0; j < deadNeighbourList.size(); j++) {
            double temp2 = deadNeighbourList.get(j);
            if (mutantSet.contains(j)) {
                tempList.add(temp2 * r);
            } else {
                tempList.add(temp2);
            }
        }
        List<Double> newList = newNormaliseList(tempList);
        int reproNode = RandomUtil.randomNeighborFromList(deadNode, newList);
        if (mutantSet.contains(reproNode)) {
            if(!mutantSet.contains(deadNode)) {
                i++;
                mutantSet.add(deadNode);
                normalSet.remove(deadNode);
            }
        }else{
            if(mutantSet.contains(deadNode)){
                i--;
                mutantSet.remove(deadNode);
                normalSet.add(deadNode);
            }
        }
        return false;
    }

    @Override
    public void reinit() {
        super.reinit();
        init();
    }

    @Override
    public boolean isSuccess() {
        return mutantSet.size() == N ? true : false;
    }


    public List<List<Double>> getAdjaMatrix() {
        return adjaMatrix;
    }

    public void setAdjaMatrix(List<List<Double>> adjaMatrix) {
        this.adjaMatrix = adjaMatrix;
    }

    public static GraphGeneral generateFromJson(String jsonstr) {
        JSONObject jsonObject = JSONObject.parseObject(jsonstr);

        int initMutantNum = jsonObject.getInteger("initMutantNum");
        int reward = jsonObject.getInteger("reward");
        int size = jsonObject.getInteger("size");
        JSONArray matrix = jsonObject.getJSONArray("matrix");
        List<List<Double>> adjaMatrix = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            List<Double> tempRowList = new ArrayList<>();
            String tempRowStr = matrix.getString(i);
            for (String str : tempRowStr.split(" ")) {
                tempRowList.add(Double.valueOf(str));
            }
            adjaMatrix.add(tempRowList);
        }
        return new GraphGeneral(initMutantNum, reward, size, adjaMatrix);
    }

    @Override
    public String toJSONString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("initMutantNum", initMutantNum);
        jsonObject.put("reward", r);
        jsonObject.put("size", N);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < adjaMatrix.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (Double d : adjaMatrix.get(i)) {
                sb.append(d + " ");
            }
            sb.deleteCharAt(sb.length() - 1);
            String rowStr = sb.toString();
            jsonArray.add(rowStr);
        }
        jsonObject.put("matrix", jsonArray);
        return jsonObject.toJSONString();
    }

    @Override
    public boolean isConnected() {
        Set<Integer> current = new HashSet();
        Set<Integer> prev = new HashSet();
        current.add(0);
        prev.add(0);
        while(prev.size()>0){
            Set<Integer> temp = new HashSet();
            for(int node:prev){
                for(int j=0;j<N;j++){
                    if(adjaMatrix.get(node).get(j)>0 && !current.contains(j)){
                        current.add(j);
                        temp.add(j);
                    }
                }
            }
            prev = temp;
        }
        if(current.size() < N){
            return false;
        }

        current = new HashSet();
        prev = new HashSet();
        current.add(0);
        prev.add(0);
        while(prev.size()>0){
            Set<Integer> temp = new HashSet();
            for(int node:prev){
                for(int j=0;j<N;j++){
                    if(adjaMatrix.get(j).get(node)>0 && !current.contains(j)){
                        current.add(j);
                        temp.add(j);
                    }
                }
            }
            prev = temp;
        }
        if(current.size() < N){
            return false;
        }
        return true;
    }

    public static void normaliseRow(List<List<Double>> matrix) {
        //todo lacking sum == 0 handling
        for (int j = 0; j < matrix.size(); j++) {
            double sum = 0;
            for (int k = 0; k < matrix.get(0).size(); k++) {
                sum += matrix.get(j).get(k);
            }
            for (int k = 0; k < matrix.get(0).size(); k++) {
                matrix.get(j).set(k, matrix.get(j).get(k) / sum);
            }
        }
    }

    public static List<Double> newNormaliseList(List<Double> list) {
        double sum = 0;
        List<Double> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        for (int i = 0; i < list.size(); i++) {
            resultList.add(list.get(i) / sum);
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<Double> ll = new ArrayList<>();
        ll.add(1.8);
        ll.add(1.8);
        ll.add(1.8);
        ll.add(1.8);
        ll.add(1.8);
        ll.add(1.8);
        List<Double> result = newNormaliseList(ll);
        System.out.println(result);
    }


}
