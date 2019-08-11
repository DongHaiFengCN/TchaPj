package com.application.tchapj.utils2.poptabview.loader;


import com.application.tchapj.utils2.poptabview.PopsTabUtils;
import com.application.tchapj.utils2.poptabview.base.BaseFilterTabBean;
import com.application.tchapj.utils2.poptabview.bean.FilterTabBean;

import java.util.List;

/**
 * 如果 遇到复杂的 业务需求, 只需要在这里,对筛选结果进行构建即可.
 * Created by chenchangjun on 17/7/25.
 */

public class ResultLoaderImp implements ResultLoader<String> {


    @Override
    public String getResultParamsIds(List<BaseFilterTabBean> selectedList, int filterType) {
        StringBuilder stringValues = new StringBuilder();
        for (int i = 0; i < selectedList.size(); i++) {
            FilterTabBean filterTabBean= (FilterTabBean) selectedList.get(i);
            stringValues.append( filterTabBean.getTab_id()+ ",");
        }
        return PopsTabUtils.builderToString(stringValues);
    }

    @Override
    public String getSecondResultParamsIds(List<BaseFilterTabBean> selectedList, int filterType) {
        StringBuilder stringValues = new StringBuilder();
        for (int i = 0; i < selectedList.size(); i++) {
            FilterTabBean.ChildTabBean filterTabBean= (FilterTabBean.ChildTabBean) selectedList.get(i);
            stringValues.append( filterTabBean.getTab_id()+ ",");
        }
        return PopsTabUtils.builderToString(stringValues);

    }


    @Override
    public String getResultShowValues(List<BaseFilterTabBean> selectedList, int filterType) {

        StringBuilder stringValues = new StringBuilder();
        for (int i = 0; i < selectedList.size(); i++) {
            stringValues.append(selectedList.get(i).getTab_name() + ",");
        }
        return PopsTabUtils.builderToString(stringValues);
    }

    @Override
    public String getSecondResultShowValues(List<BaseFilterTabBean> selectedList, int filterType) {
        StringBuilder stringValues = new StringBuilder();
        for (int i = 0; i < selectedList.size(); i++) {
            stringValues.append(selectedList.get(i).getTab_name() + ",");
        }
        return PopsTabUtils.builderToString(stringValues);
    }


    @Override
    public String getResultShowIds(List<BaseFilterTabBean> selectedList, int filterType) {
        StringBuilder stringValues = new StringBuilder();
        for (int i = 0; i < selectedList.size(); i++) {
            stringValues.append(selectedList.get(i).getTab_id() + ",");
        }
        return PopsTabUtils.builderToString(stringValues);
    }

    @Override
    public String getSecondResultShowIds(List<BaseFilterTabBean> selectedList, int filterType) {
        StringBuilder stringValues = new StringBuilder();
        for (int i = 0; i < selectedList.size(); i++) {
            stringValues.append(selectedList.get(i).getTab_id() + ",");
        }
        return PopsTabUtils.builderToString(stringValues);
    }


}
