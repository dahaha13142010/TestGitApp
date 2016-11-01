package com.ronghan.testgitapp.tools;

import android.content.Context;

import com.admin.utils.ABPrefsUtil;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.been.LoginEntity;
import com.ronghan.testgitapp.been.MyWalletEntity;
import com.ronghan.testgitapp.constant.H;

/**
 * 项目名称：dyb-app-android
 * 类描述：项目相关--自定义SharedPreferences功能类
 * 创建人：Michael
 * 创建时间：2015/11/24 13:46
 * 修改人：Michael
 * 修改时间：2015/11/24 13:46
 * 修改备注：
 */
public class MSPUtils {
    public static Context context;
    public static MSPUtils application;


    public static MSPUtils getInstance(Context c) {
        if (application == null)
            application = new MSPUtils();
        context = c;
        return application;
    }

    private ABPrefsUtil prefsUtil;

    /**
     * 初始化ABPrefsUtil
     */
    private void initsp(String spAboveName) {
        //初始化数据
        if (prefsUtil == null)
            prefsUtil = ABPrefsUtil.getInstance(context);
        if (prefsUtil.prefs == null || prefsUtil.editor == null)
            prefsUtil.init(spAboveName, Context.MODE_PRIVATE);
    }

    /**
     * ============================================================================================================================
     * =============================================================登录相关========================================================
     * ============================================================================================================================
     */

    /**
     * 保存登录信息
     **/
    public void setLoginSp(LoginEntity loginEntity) {
        initsp(H.LOGIN_TITLE);
        prefsUtil.putString(H.LOGIN_USERNAME, loginEntity.username).commit();
        prefsUtil.putString(H.LOGIN_ID, loginEntity.id).commit();
        prefsUtil.finish();
    }

    /**
     * 获取登陆信息
     **/
    public LoginEntity getLoginSp() {
        LoginEntity viewHoler = new LoginEntity();
        initsp(H.LOGIN_TITLE);
        viewHoler.username = prefsUtil.getString(H.LOGIN_USERNAME, "");
        viewHoler.id = prefsUtil.getString(H.LOGIN_ID, "");
        return viewHoler;
    }

    /**
     * 是否登录状态
     **/
    public boolean isLogin() {
        LoginEntity loginEntity = getLoginSp();
        if ("" == loginEntity.username) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 注销登录
     **/
    public void setLogout() {
        LoginEntity loginEntity = new LoginEntity("", "");
        setLoginSp(loginEntity);
    }


    /**
     * ============================================================================================================================
     * =============================================================用户相关========================================================
     * ============================================================================================================================
     */
    /**
     * 保存用户信息
     **/
    public void setAppUserSp(AppUserEntity appUserEntity) {
        initsp(H.APP_USER_TITLE);
        prefsUtil.putString(H.APP_USER_ID, appUserEntity.getId()).commit();
        prefsUtil.putString(H.APP_USER_CUSTOMER_ID, appUserEntity.getCustomerId()).commit();
        prefsUtil.putString(H.APP_USER_USER_ID, appUserEntity.getUserId()).commit();
        prefsUtil.putString(H.APP_USER_USERNAME, appUserEntity.getUserName()).commit();
        prefsUtil.putString(H.APP_USER_BANKACCOUNT, appUserEntity.getBankAccount()).commit();
        prefsUtil.putString(H.APP_USER_ACCOUNTNAME, appUserEntity.getAccountName()).commit();
        prefsUtil.putString(H.APP_USER_BANKNAME, appUserEntity.getBankName()).commit();
        prefsUtil.putString(H.APP_USER_MOBILE_PHONE, appUserEntity.getMobilePhone()).commit();
        prefsUtil.putString(H.APP_USER_ISVALIDATE, appUserEntity.getIsValidate()).commit();
        prefsUtil.putString(H.APP_USER_EXTENSION_CODE, appUserEntity.getExtensionCode()).commit();
        prefsUtil.putString(H.APP_USER_NOW_ROLE, appUserEntity.getNowRole()).commit();
        prefsUtil.putLong(H.APP_USER_CREATED_DATE, appUserEntity.getCreatedDate()).commit();
        prefsUtil.putLong(H.APP_USER_UPDATED_DATE, appUserEntity.getUpdatedDate()).commit();
        prefsUtil.putString(H.APP_USER_STATUS, appUserEntity.getStatus()).commit();
        prefsUtil.putString(H.APP_USER_IS_ACTIVATION, appUserEntity.getIsActivation()).commit();
        prefsUtil.putString(H.APP_USER_ID_CARD, appUserEntity.getIdCard()).commit();

        prefsUtil.finish();
    }

    /**
     * 获取用户信息
     **/
    public AppUserEntity getAppUserSp() {
        AppUserEntity viewHoler = new AppUserEntity();
//        AppUserEntity.AppUserEntity parentIdBean = new AppUserEntity.ParentIdBean();
        initsp(H.LOGIN_TITLE);
        viewHoler.setCreatedDate(prefsUtil.getLong(H.APP_USER_CREATED_DATE));
        viewHoler.setCustomerId(prefsUtil.getString(H.APP_USER_CUSTOMER_ID));
        viewHoler.setExtensionCode(prefsUtil.getString(H.APP_USER_EXTENSION_CODE));
        viewHoler.setId(prefsUtil.getString(H.APP_USER_ID));
        viewHoler.setIsActivation(prefsUtil.getString(H.APP_USER_IS_ACTIVATION));
        viewHoler.setIsValidate(prefsUtil.getString(H.APP_USER_ISVALIDATE));
        viewHoler.setNowRole(prefsUtil.getString(H.APP_USER_NOW_ROLE));
        viewHoler.setStatus(prefsUtil.getString(H.APP_USER_STATUS));
        viewHoler.setUpdatedDate(prefsUtil.getLong(H.APP_USER_UPDATED_DATE));
        viewHoler.setUserId(prefsUtil.getString(H.APP_USER_USER_ID));
        viewHoler.setUserName(prefsUtil.getString(H.APP_USER_USERNAME));
        viewHoler.setAccountName(prefsUtil.getString(H.APP_USER_ACCOUNTNAME));
        viewHoler.setBankAccount(prefsUtil.getString(H.APP_USER_BANKACCOUNT));
        viewHoler.setBankName(prefsUtil.getString(H.APP_USER_BANKNAME));
        viewHoler.setIdCard(prefsUtil.getString(H.APP_USER_ID_CARD));
        viewHoler.setMobilePhone(prefsUtil.getString(H.APP_USER_MOBILE_PHONE));
        return viewHoler;
    }

    /**
     * ============================================================================================================================
     * =============================================================钱包相关========================================================
     * ============================================================================================================================
     */
    public void setMyWalletSp(MyWalletEntity myWalletEntity) {
        initsp(H.MY_WALLET_TITLE);
//        Gson gson = new Gson();
//        String myWalletStr = gson.toJson(myWalletEntity);
//        prefsUtil.putString(H.MY_WALLET_TITLE, myWalletStr).commit();
        prefsUtil.putDouble(H.MY_WALLET_BALANCE, myWalletEntity.getBalance()).commit();
        prefsUtil.putDouble(H.MY_WALLET_CHANGED_IN_BALANCE, myWalletEntity.getChangedInBalance()).commit();
        prefsUtil.putDouble(H.MY_WALLET_CHANGED_OUT_BALANCE, myWalletEntity.getChangedOutBalance()).commit();
        prefsUtil.putDouble(H.MY_WALLET_COLLECTION_BALANCE, myWalletEntity.getCollectionBalance()).commit();
        prefsUtil.putDouble(H.MY_WALLET_CONSUMED_BALANCE, myWalletEntity.getConsumedBalance()).commit();
        prefsUtil.putString(H.MY_WALLET_CUSTOMER_ID, myWalletEntity.getCustomerId()).commit();
        prefsUtil.putString(H.MY_WALLET_CUSTOMER_NAME, myWalletEntity.getCustomerName()).commit();
        prefsUtil.putDouble(H.MY_WALLET_FROZE_BALANCE, myWalletEntity.getFrozeBalance()).commit();
        prefsUtil.putString(H.MY_WALLET_ID, myWalletEntity.getId()).commit();
        prefsUtil.putDouble(H.MY_WALLET_PRODUCT_BALANCE, myWalletEntity.getProductBalance()).commit();
        prefsUtil.putString(H.MY_WALLET_STATUS, myWalletEntity.getStatus()).commit();
        prefsUtil.putDouble(H.MY_WALLET_WITHDRAW_BALANCE, myWalletEntity.getWithdrawBalance()).commit();
        prefsUtil.finish();
    }

    public MyWalletEntity getMyWalletSp() {
//        if (isLogin()){
//            initsp(H.MY_WALLET_TITLE);
//            Gson gson = new Gson();
//            MyWalletEntity myWalletEntity = gson.fromJson(H.MY_WALLET_TITLE, MyWalletEntity.class);
//            return myWalletEntity;
//        }else {
//            return null;
//        }

        MyWalletEntity viewHoler = new MyWalletEntity();
        initsp(H.MY_WALLET_TITLE);
        viewHoler.setBalance(prefsUtil.getDouble(H.MY_WALLET_BALANCE));
        viewHoler.setChangedInBalance(prefsUtil.getDouble(H.MY_WALLET_CHANGED_IN_BALANCE));
        viewHoler.setChangedOutBalance(prefsUtil.getDouble(H.MY_WALLET_CHANGED_OUT_BALANCE));
        viewHoler.setCollectionBalance(prefsUtil.getDouble(H.MY_WALLET_COLLECTION_BALANCE));
        viewHoler.setConsumedBalance(prefsUtil.getDouble(H.MY_WALLET_CONSUMED_BALANCE));
        viewHoler.setCustomerId(prefsUtil.getString(H.MY_WALLET_CUSTOMER_ID));
        viewHoler.setCustomerName(prefsUtil.getString(H.MY_WALLET_CUSTOMER_NAME));
        viewHoler.setFrozeBalance(prefsUtil.getDouble(H.MY_WALLET_FROZE_BALANCE));
        viewHoler.setId(prefsUtil.getString(H.MY_WALLET_ID));
        viewHoler.setProductBalance(prefsUtil.getDouble(H.MY_WALLET_PRODUCT_BALANCE));
        viewHoler.setStatus(prefsUtil.getString(H.MY_WALLET_STATUS));
        viewHoler.setWithdrawBalance(prefsUtil.getDouble(H.MY_WALLET_WITHDRAW_BALANCE));
        return viewHoler;
    }
}
