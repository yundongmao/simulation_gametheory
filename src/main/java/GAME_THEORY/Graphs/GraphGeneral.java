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

    public void init(){
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
    public boolean update() {
        if (mutantSet.size() == N || mutantSet.size() == 0) {
            return true;
        }
        //decide mutant or normal node to reproduce
        double temp = random.nextDouble();
        if (temp < i * r / (i * r + N - i)) {
//            System.out.println("choose mutant to reproduce");
            //decide which mutant to reproduce randomly
            int reproNode = (int) RandomUtil.RandomChooseFromSet(mutantSet);
            int deadNode = RandomUtil.randomNeighborFromList(reproNode, adjaMatrix.get(reproNode));
            if (!mutantSet.contains(deadNode)) {
                System.out.println("i++");
                i++;
                mutantSet.add(deadNode);
                normalSet.remove(deadNode);
            }
        } else {
//            System.out.println("choose normal to reproduce");
            int reproNode = (int) RandomUtil.RandomChooseFromSet(normalSet);
            int deadNode = RandomUtil.randomNeighborFromList(reproNode, adjaMatrix.get(reproNode));
            if (!normalSet.contains(deadNode)) {
                System.out.println("i--");
                i--;
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
        jsonObject.put("initMutantNum",initMutantNum);
        jsonObject.put("reward",r);
        jsonObject.put("size",N);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < adjaMatrix.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for(Double d: adjaMatrix.get(i)){
                sb.append(d+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            String rowStr = sb.toString();
            jsonArray.add(rowStr);
        }
        jsonObject.put("matrix",jsonArray);
        return jsonObject.toJSONString();
    }


}
