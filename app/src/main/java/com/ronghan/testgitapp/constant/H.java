package com.ronghan.testgitapp.constant;


import java.io.Serializable;

/**
 * 项目名称：dyb-app-android
 * 类描述：
 * 创建人：Michael
 * 创建时间：2015/11/13 13:36
 * 修改人：Michael
 * 修改时间：2015/11/13 13:36
 * 修改备注：序列化（防止被混淆）
 */
public class H implements Serializable {
    /**
     * ============================================================================================================================
     * ========================================================intentTAG======================================================
     * ============================================================================================================================
     */
    public static final String TAG_INTENT = "skip";
    public static final String TAG_INTENT_BOOLEAN = "boolean";
    public static final String TAG_INTENT_PHONE = "phone";
    public static final String TAG_INTENT_REGISTER = "register";
    public static final String TAG_INTENT_LOGIN_PWD = "loginPwd";
    public static final String TAG_INTENT_PAY_PWD = "payPwd";

    public static final String TAG_INTENT_SERIALIZABLE = "TAG_INTENT_SERIALIZABLE";
    public static final boolean TAG_INTENT_IS_CREATE = false;
    /**
     * ============================================================================================================================
     * ========================================================日志抓取工具相关======================================================
     * ============================================================================================================================
     */
    public static final String CRASHHANDLER_SP_ABOVE_NAME = "save";
    public static final String CRASHHANDLER_SP_ABOVE_KEY_ISCRASH = "save";
    public static final String CRASHHANDLER_SP_ABOVE_KEY_LOGFILEPATHANDNAME = "save";

    /**
     * ============================================================================================================================
     * =============================================================登录相关========================================================
     * ============================================================================================================================
     */
    public static final String LOGIN_TITLE = "LoginSP";
    public static final String LOGIN_CUSTOMER_ID = "login_CustomerId";
    public static final String LOGIN_USERNAME = "login_username";
    public static final String LOGIN_ID = "login_id";

    /**
     * ============================================================================================================================
     * =============================================================用户相关========================================================
     * ============================================================================================================================
     */
    public static final String APP_USER_TITLE = "AppUserSP";
    public static final String APP_USER_CUSTOMER_ID = "app_user_CustomerId";
    public static final String APP_USER_CREATED_DATE = "app_user_CreatedDate";
    public static final String APP_USER_EXTENSION_CODE = "app_user_extensionCode";
    public static final String APP_USER_ID = "app_user_id";
    public static final String APP_USER_IS_ACTIVATION = "app_user_isActivation";
    public static final String APP_USER_ISVALIDATE = "app_user_isValidate";
    public static final String APP_USER_NOW_ROLE = "app_user_nowRole";
    public static final String APP_USER_STATUS = "app_user_status";
    public static final String APP_USER_UPDATED_DATE = "app_user_updatedDate";
    public static final String APP_USER_USER_ID = "app_user_userId";
    public static final String APP_USER_USERNAME = "app_user_userName";
    public static final String APP_USER_ACCOUNTNAME = "app_user_AccountName";
    public static final String APP_USER_BANKACCOUNT = "app_user_BankAccount";
    public static final String APP_USER_BANKNAME = "app_user_BankName";
    public static final String APP_USER_ID_CARD = "app_user_IdCard";
    public static final String APP_USER_MOBILE_PHONE = "app_user_MobilePhone";

    /**
     * ============================================================================================================================
     * =============================================================钱包相关========================================================
     * ============================================================================================================================
     */
    public static final String MY_WALLET_TITLE = "MyWalletSp";
    public static final String MY_WALLET_BALANCE = "my_wallet_balance";
    public static final String MY_WALLET_CHANGED_IN_BALANCE = "my_wallet_changedInBalance";
    public static final String MY_WALLET_CHANGED_OUT_BALANCE = "my_wallet_changedOutBalance";
    public static final String MY_WALLET_COLLECTION_BALANCE = "my_wallet_collectionBalance";
    public static final String MY_WALLET_CONSUMED_BALANCE = "my_wallet_consumedBalance";
    public static final String MY_WALLET_CUSTOMER_ID = "my_wallet_customerId";
    public static final String MY_WALLET_CUSTOMER_NAME = "my_wallet_customerName";
    public static final String MY_WALLET_FROZE_BALANCE = "my_wallet_frozeBalance";
    public static final String MY_WALLET_ID = "my_wallet_id";
    public static final String MY_WALLET_PRODUCT_BALANCE = "my_wallet_productBalance";
    public static final String MY_WALLET_STATUS = "my_wallet_status";
    public static final String MY_WALLET_WITHDRAW_BALANCE = "my_wallet_withdrawBalance";
    /**
     * ============================================================================================================================
     * =============================================================常量============================================================
     * ============================================================================================================================
     */
    public static final int PAGE_SIZE = 1000;
    public static final int ADDRESS_ACTIVITY = 10;
    public static final int INTENT_SUCCESS = 0;
    public static final int INTENT_FAIL = -1;
    /**
     * NOT_VERIFIED：未验证
     * VERIFICATION_IN:验证中
     * VERIFIED:验证成功
     * VALIDATION_FAILURE:验证失败
     */
    public static final String VERIFICATION_IN = "VERIFICATION_IN";
    public static final String VERIFIED = "VERIFIED";
    public static final String NOT_VERIFIED = "NOT_VERIFIED";
    public static final String VALIDATION_FAILURE = "VALIDATION_FAILURE";

    /**
     * 当前角色[
     * CLERK:店员
     * SHOPOWNER:店长
     * BOSS:老板
     * AGENT:代理商
     * PLATFORM:平台
     * ]
     */
    public static final String CLERK = "CLERK";
    public static final String SHOPOWNER = "SHOPOWNER";
    public static final String BOSS = "BOSS";
    public static final String AGENT = "AGENT";
    public static final String PLATFORM = "PLATFORM";

    /**
     * bannerType : MALL或者TITLE
     * MALL为请求商城banner，TITLE为请求首页banner
     */
    public static final String TITLE = "TITLE";
    public static final String MALL = "MALL";
}
