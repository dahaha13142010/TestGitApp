package com.ronghan.testgitapp.been;

import com.admin.control.quickRv.inter.RvQuickInterface;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/17 0017 9:33
 * 修改人：Michael
 * 修改时间：2016/5/17 0017 9:33
 * 修改备注：
 */
public class IcoEntities implements Serializable, RvQuickInterface {
    @Override
    public int getRvType() {
        return 0;
    }

    private List<IcoEntity> icoEntities;

    public List<IcoEntity> getIcoEntities() {
        return icoEntities;
    }

    public void setIcoEntities(List<IcoEntity> icoEntities) {
        this.icoEntities = icoEntities;
    }
}
