package com.admin.control.quickRv.helper;

import com.admin.control.quickRv.model.RvQuickModel;

import java.util.ArrayList;
import java.util.List;

/**
 * QuickAdapter     com.march.adapterlibs.helper
 * Created by 陈栋 on 16/4/8.
 * 功能:
 */
public class RvConvertor {

    public static <T> List<RvQuickModel> convert(List<T> list){
        List<RvQuickModel> quicks = new ArrayList<>();
        for (T t  : list) {
            quicks.add(new RvQuickModel(t));
        }
        return quicks;
    }


    public static <T> List<RvQuickModel> convert(T[] list){
        List<RvQuickModel> quicks = new ArrayList<>();
        for (T t  : list) {
            quicks.add(new RvQuickModel(t));
        }
        return quicks;
    }

}
