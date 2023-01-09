package github.zayn.others;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * @ClassName Test
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/5/25 下午2:49
 **/
public class Dfs {
    public static void main(String[] args) {
        Dfs dfs = new Dfs();
        List<DfsModel> dfsModelList = dfs.initDfsModels();
        List<DfsModel> collect = dfsModelList.stream().map(DfsModel::getDfsModels).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(collect);
    }

    private List<DfsModel> initDfsModels() {
        List<DfsModel> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DfsModel dfsModel = new DfsModel();
            dfsModel.setId(i);
            dfsModel.setDesc("第一层第" + i + "个");
            List<DfsModel> dfsModelList = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                DfsModel dfsModel2 = new DfsModel();
                dfsModel2.setId(k + 10);
                dfsModel2.setDesc("第2层第" + k + "个");
                List<DfsModel> dfsModel2List = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    DfsModel dfsModel3 = new DfsModel();
                    dfsModel3.setId(j + 100);
                    dfsModel3.setDesc("第3层第" + j + "个");
                    dfsModel2List.add(dfsModel3);
                }
                dfsModel2.setDfsModels(dfsModel2List);
                dfsModelList.add(dfsModel2);
            }
            dfsModel.setDfsModels(dfsModelList);
            result.add(dfsModel);
        }
        return result;
    }

    private void dfsAllModels(List<DfsModel> models, List<DfsModel> result) {
        for (DfsModel dfsModel : models) {
            if (dfsModel.getDfsModels() != null) {
                dfsAllModels(dfsModel.getDfsModels(), result);
            } else {
                DfsModel dfsModel1 = new DfsModel();
                dfsModel1.setId(dfsModel.getId());
                dfsModel1.setDesc(dfsModel.getDesc());
                dfsModel1.setDfsModels(dfsModel.getDfsModels());
                result.add(dfsModel1);
            }
        }
    }

    private List<DfsModel> dfsAllModels(List<DfsModel> models) {
        List<DfsModel> result = new ArrayList<>();
        for (DfsModel dfsModel : models) {
            if (dfsModel.getDfsModels() != null) {
                List<DfsModel> dfsModelList = dfsAllModels(dfsModel.getDfsModels());
                result.addAll(dfsModelList);
            } else {
                DfsModel dfsModel1 = new DfsModel();
                dfsModel1.setId(dfsModel.getId());
                dfsModel1.setDesc(dfsModel.getDesc());
                result.add(dfsModel1);
            }
        }
        return result;
    }

    private List<DfsModel> dfsConstructAllModels(List<DfsModel> models) {
        List<DfsModel> result = new ArrayList<>();
        for (DfsModel dfsModel : models) {
            List<DfsModel> dfsModelList = Lists.newArrayList();
            if (dfsModel.getDfsModels() != null) {
                dfsModelList = dfsConstructAllModels(dfsModel.getDfsModels());
                DfsModel dfsModel1 = new DfsModel();
                dfsModel1.setId(dfsModel.getId());
                dfsModel1.setDesc(dfsModel.getDesc());
                dfsModel1.setDfsModels(dfsModelList);
                result.add(dfsModel1);
            } else {
                DfsModel dfsModel1 = new DfsModel();
                dfsModel1.setId(dfsModel.getId());
                dfsModel1.setDesc(dfsModel.getDesc());
                dfsModel1.setDfsModels(dfsModelList);
                result.add(dfsModel1);
            }
        }
        return result;
    }


}

