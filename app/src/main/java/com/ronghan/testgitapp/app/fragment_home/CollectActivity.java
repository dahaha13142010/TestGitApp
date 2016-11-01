package com.ronghan.testgitapp.app.fragment_home;

import android.view.View;
import android.widget.TextView;

import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseActivity;
import com.ronghan.testgitapp.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/26 0026 16:34
 * 修改人：Michael
 * 修改时间：2016/4/26 0026 16:34
 * 修改备注：
 */
public class CollectActivity extends BaseActivity {
    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.tv_rmb)
    TextView tvRmb;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_collect;
    }

    @OnClick({R.id.iv_number_7, R.id.iv_number_8, R.id.iv_number_9, R.id.iv_number_delete, R.id.iv_number_4, R.id.iv_number_5, R.id.iv_number_6, R.id.iv_number_1, R.id.iv_number_2, R.id.iv_number_3, R.id.iv_number_0, R.id.iv_number_point, R.id.iv_number_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_number_7:
                break;
            case R.id.iv_number_8:
                break;
            case R.id.iv_number_9:
                break;
            case R.id.iv_number_delete:
                break;
            case R.id.iv_number_4:
                break;
            case R.id.iv_number_5:
                break;
            case R.id.iv_number_6:
                break;
            case R.id.iv_number_1:
                break;
            case R.id.iv_number_2:
                break;
            case R.id.iv_number_3:
                break;
            case R.id.iv_number_0:
                break;
            case R.id.iv_number_point:
                break;
            case R.id.iv_number_confirm:
                break;
        }
    }
}
