package com.application.tchapj.main.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.main.bean.PersonMediaModel;
import com.application.tchapj.main.bean.PersonSelectModel;

/**
 * Created by Sun on 2018/6/24.
 */

public interface IPersonView extends BaseMvpView {

    /*// 得到全部名人分类
    void onGetPersonMediaModelResult(PersonMediaModel personMediaModel);*/

    // 得到名人分类数据
    void onGetPersonMediaResult(PersonMediaModel personMediaModel);


    /*// 得到全部名人分类 最多
    void onGetPersonMediaModelResultMost(PersonMediaModel personMediaModel);*/


    // 得到分类
    void onGetPersonSelectModelResult(PersonSelectModel personSelectModel);

}
