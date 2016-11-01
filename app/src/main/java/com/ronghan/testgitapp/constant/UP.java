package com.ronghan.testgitapp.constant;


import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.Serializable;

//import com.dahaha.android.xiaonanhai.been.AccountB;
//import com.dahaha.android.xiaonanhai.been.AccountSafeB;
//import com.dahaha.android.xiaonanhai.been.ExchangePaginationB;
//import com.dahaha.android.xiaonanhai.been.GetPassB;
//import com.dahaha.android.xiaonanhai.been.LoginB;
//import com.dahaha.android.xiaonanhai.been.PersonDataB;
//import com.dahaha.android.xiaonanhai.been.RegistB;
//import com.dahaha.android.xiaonanhai.been.SafeEmailB;
//import com.dahaha.android.xiaonanhai.been.SafeQuestionB;
//import com.dahaha.android.xiaonanhai.been.VerificationB;


public class UP {


    public String getBaseUrl() {
        return "http://192.168.199.137:7777/";
    }

    //json操作库
    private static Gson gson = null;

    public UP() {
    }

    private volatile static UP instance = null;

    /**
     * 获取UP实例
     *
     * @return
     */
    public static UP getInstance() {
        if (instance == null) {
            synchronized (UP.class) {
                if (instance == null) {
                    instance = new UP();
                }
                if (gson == null)
                    gson = new Gson();
            }
        }
        return instance;
    }


    /**
     * post
     *
     * @param sysId
     * @return params
     */
    public <T extends Serializable> RequestCall post(int sysId) {
        return OkHttpUtils.post().url(getBaseUrl()).addParams("Msgtype", "2").addParams("Sysid", ""+sysId).build();
    }
    /**
     * JKPayParams以json形式设置参数
     *
     * @param url
     * @param entity
     * @return params
     */
    public <T extends Serializable> RequestCall postTYF(String url, T entity) {

        if (null == gson)
            gson = new Gson();
        //TODO
        return OkHttpUtils.post().url(getBaseUrl()+url).addParams("json", null == entity ? "" : gson.toJson(entity)).build();
    }
    /**
     * 传空JKPayParams数据
     *
     * @param url
     * @return
     */
    public RequestCall getNull_P(String url) {
        return postTYF(url, null);
    }
///**
// * =====================================================================================================================================
// * =====================================================================================================================================
// * ============================================================Main=====================================================================
// * =====================================================================================================================================
// * =====================================================================================================================================
// */
//    /**
//     * 注册账号验证服务器验证请求
//     *
//     * @param phoneNum
//     * @return params
//     */
//    public RequestCall getRegistVerification_P(String phoneNum) {
//        String getVerificationUrl = getBaseUrl() + "/app/api/account/post/send/verification_code_message";
//        //设置参数
//        RegistB.getCodeB getCodeB = new RegistB.getCodeB();
//        if (!TextUtils.isEmpty(phoneNum))
//            getCodeB.username = phoneNum;
//        return postTYF(getVerificationUrl, getCodeB);
//    }
//
//    /**
//     * 注册账号验证服务器验证请求
//     *
//     * @param registPhoneNum
//     * @param verificationNum
//     * @return params
//     */
//    public RequestCall getCheckRegistVerification_P(String registPhoneNum, String verificationNum) {
//        String checkVerificationUrl = getBaseUrl() + "/app/api/account/check/verify_code";
//        //设置参数
//        RegistB.checkCodeB checkB = new RegistB.checkCodeB();
//        if (!TextUtils.isEmpty(registPhoneNum))
//            checkB.username = registPhoneNum;
//        if (!TextUtils.isEmpty(verificationNum))
//            checkB.verifyCode = verificationNum;
//        return postTYF(checkVerificationUrl, checkB);
//    }
//
//    /**
//     * 注册账号服务器验证请求
//     */
//    public RequestCall getRegist_P(String account, String passWord, String payPassWord) {
//        String registUrl = getBaseUrl() + "/app/api/account/post";
//        //设置参数
//        RegistB.registAccountB registB = new RegistB.registAccountB();
//        if (!TextUtils.isEmpty(account))
//            registB.username = account;
//        if (!TextUtils.isEmpty(passWord))
//            registB.password = passWord;
//        if (!TextUtils.isEmpty(payPassWord))
//            registB.payPassword = payPassWord;
//        return postTYF(registUrl, registB);
//    }
//
//    /**
//     * 登录服务器验证请求
//     */
//    public RequestCall getLogin_P(String username, String password) {
//        String loginUrl = getBaseUrl() + "/app/api/auth/post";
//        //设置参数
//        LoginB.RequstB requstB = new LoginB.RequstB();
//        if (!TextUtils.isEmpty(username))
//            requstB.username = username;
//        if (!TextUtils.isEmpty(password))
//            requstB.password = password;
//
//        return postTYF(loginUrl, requstB);
//    }

    /**
     * 登出服务器验证请求
     */
    public RequestCall getLogout_P() {
        String LogoutUrl = getBaseUrl() + "/app/api/auth/post";
        return postTYF(LogoutUrl, null);
    }

//    /**
//     * 找回密码请求
//     */
//    public RequestCall getGetPassInputNum_P(String accountName) {
//        String getAccountByAccountNum_Url = getBaseUrl() + "/app/api/account/search";
//        //设置参数
//        GetPassB.inputPhone_Rq getPassB = new GetPassB.inputPhone_Rq();
//        if (!TextUtils.isEmpty(accountName))
//            getPassB.accountName = accountName;
//        return postTYF(getAccountByAccountNum_Url, getPassB);
//    }
//
//    /**
//     * 找回密码---发送验证码--通过手机
//     *
//     * @param phoneNum
//     * @return params
//     */
//    public RequestCall getGetPassVerificationByPhone_P(String phoneNum) {
//        String getVerificationUrl = getBaseUrl() + "/app/api/account/found/password/send/verification_code_message";
//        //设置参数
//        VerificationB.getCodeB getCodeB = new VerificationB.getCodeB();
//        if (!TextUtils.isEmpty(phoneNum))
//            getCodeB.username = phoneNum;
//        return postTYF(getVerificationUrl, getCodeB);
//    }
//
//    /**
//     * 找回密码---发送验证码--通过邮箱
//     */
//    public RequestCall getGetPassVerificationByEmail_P(String email) {
//        String getPassUrl = getBaseUrl() + "/app/api/account/send_verification_code_message_to_email";
//        //设置参数
//        VerificationB.getVerifyCodeByEmailB getVerifyCodeByEmailB = new VerificationB.getVerifyCodeByEmailB();
//        if (!TextUtils.isEmpty(email))
//            getVerifyCodeByEmailB.email = email;
//        return postTYF(getPassUrl, getVerifyCodeByEmailB);
//    }
//
//    /**
//     * 找回密码---验证验证码--邮箱或手机
//     */
//    public RequestCall getGetPassAuthenticationCode_P(String username, String email, String verificationCode) {
//        String getPassUrl = getBaseUrl() + "/app/api/account/check/verification_code";
//        //设置参数
//        VerificationB.AuthenticationCode_Rq authenticationCode_rq = new VerificationB.AuthenticationCode_Rq();
//        if (!TextUtils.isEmpty(username))
//            authenticationCode_rq.username = username;
//        if (!TextUtils.isEmpty(email))
//            authenticationCode_rq.email = email;
//        if (!TextUtils.isEmpty(verificationCode))
//            authenticationCode_rq.verificationCode = verificationCode;
//        return postTYF(getPassUrl, authenticationCode_rq);
//    }
//
//    /**
//     * 找回密码--验证安全问题
//     */
//    public RequestCall getGetPassByQuestion_P(String accountId, ArrayList<String> questionList, ArrayList<String> answerList) {
//        String getPassUrl = getBaseUrl() + "/app/api/account/verify/safety_question";
//        //设置参数
//        VerificationB.SafetyProblemB safetyProblemB = new VerificationB.SafetyProblemB();
//        if (!TextUtils.isEmpty(accountId))
//            safetyProblemB.accountId = accountId;
//        if (questionList != null)
//            safetyProblemB.questionList = questionList;
//        if (answerList != null)
//            safetyProblemB.answerList = answerList;
//        return postTYF(getPassUrl, safetyProblemB);
//    }
//
//    /**
//     * 找回密码--设置新密码（登陆|支付）----取回密码的类型，true-登录密码、false-支付密码
//     */
//    public RequestCall getSettingNewPassWord_P(boolean type, String id, String oldPassword, String newPassword, String version) {
//        String getSettingNewPassWordUrl = getBaseUrl() + (type ? "/app/api/account/update/login_password" : "/app/api/account/update/pay_password");
//        //设置参数
//        if (type) {
//            GetPassB.setNewLoginPass_Rq setNewPass_rq = new GetPassB.setNewLoginPass_Rq();
//            if (!TextUtils.isEmpty(id))
//                setNewPass_rq.userId = id;
//            if (!TextUtils.isEmpty(oldPassword))
//                setNewPass_rq.oldLoginPassword = oldPassword;
//            if (!TextUtils.isEmpty(newPassword))
//                setNewPass_rq.newLoginPassword = newPassword;
//            if (!TextUtils.isEmpty(version))
//                setNewPass_rq.userVersion = version;
//            return postTYF(getSettingNewPassWordUrl, setNewPass_rq);
//        } else {
//            GetPassB.setNewPayPass_Rq setNewPayPass_rq = new GetPassB.setNewPayPass_Rq();
//            if (!TextUtils.isEmpty(id))
//                setNewPayPass_rq.id = id;
//            if (!TextUtils.isEmpty(oldPassword))
//                setNewPayPass_rq.oldPayPassword = oldPassword;
//            if (!TextUtils.isEmpty(newPassword))
//                setNewPayPass_rq.newPayPassword = newPassword;
//            if (!TextUtils.isEmpty(version))
//                setNewPayPass_rq.version = version;
//            return postTYF(getSettingNewPassWordUrl, setNewPayPass_rq);
//        }
//    }
    /**
     * =====================================================================================================================================
     * ============================================================兑换======================================================================
     * =====================================================================================================================================
     */
    /**
     * 主页数据获取
     */
    public RequestCall getProductListLoadling() {
        String payPasswordUrl = getBaseUrl() + "/app/api/product/home_page/info";
        return postTYF(payPasswordUrl, null);
    }

//    /**
//     * 产品类型分页查产品
//     */
//    public RequestCall getProductPaginationLoadling(int page) {
//        String productPaginationUrl = getBaseUrl() + "/app/api/product/pagination_by_type";
//        int pageSize = C.getInstance().defaultPageSize;
//        //设置参数
//        ExchangePaginationB.RqMoreProductTypeB exchangePaginationB = new ExchangePaginationB.RqMoreProductTypeB();
//        if (page > 0)
//            exchangePaginationB.setPage(page);
//        if (pageSize > 0)
//            exchangePaginationB.setPageSize(pageSize);
//        return postTYF(productPaginationUrl, exchangePaginationB);
//    }
//
//    /**
//     * 更多产品数据
//     */
//    public RequestCall getProductMoreDataLoadling(String productTypeId, int page) {
//        String ProductDataUrl = getBaseUrl() + "/app/api/product/pagination";
//        int pageSize = 0;
//        //设置参数
//        ExchangePaginationB.RqMoreProductDataB rqMoreProductDataB = new ExchangePaginationB.RqMoreProductDataB();
//        if (!TextUtils.isEmpty(productTypeId))
//            rqMoreProductDataB.setProductTypeId(productTypeId);
//        if (page > 0)
//            rqMoreProductDataB.setPage(page);
//        if (pageSize > 0)
//            rqMoreProductDataB.setPageSize(pageSize);
//        return postTYF(ProductDataUrl, rqMoreProductDataB);
//    }
    /**
     * =====================================================================================================================================
     * =====================================================================================================================================
     * ============================================================我的=====================================================================
     * =====================================================================================================================================
     * =====================================================================================================================================
     */
//    /**
//     * 获取个人资料信息
//     */
//    public RequestCall getGetPersonData_RC(String userId) {
//        String getPersonDataUrl = getBaseUrl() + "/app/api/account/search";
//        //设置参数
//        PersonDataB.PersonDataRq personDataRq = new PersonDataB.PersonDataRq();
//        if (!TextUtils.isEmpty(userId))
//            personDataRq.setUserId(userId);
//        return postTYF(getPersonDataUrl, personDataRq);
//    }
//
//    /**
//     * 获取个人钱包信息
//     */
//    public RequestCall getGetPersonWallet_RC(String userId) {
//        String getWalletUrl = getBaseUrl() + "/app/api/wallet/list";
//        //设置参数
//        AccountSafeB.WalletB walletB = new AccountSafeB.WalletB();
//        if (!TextUtils.isEmpty(userId))
//            walletB.setAccountId(userId);
//        return postTYF(getWalletUrl, walletB);
//    }

//    /**
//     * 编辑个人资料信息
//     */
//    public RequestCall getUpdatePersonData_RC(String userId, String version, String accountNickName, String trueName, String identityNumber) {
//        String personDataEditUrl = getBaseUrl() + "/app/api/account/edit";
//        //设置参数
//        PersonDataB.PersonDataEditRq personDataEditRq = new PersonDataB.PersonDataEditRq();
//        if (!TextUtils.isEmpty(userId))
//            personDataEditRq.setUserId(userId);
//        if (!TextUtils.isEmpty(version))
//            personDataEditRq.setVersion(version);
//        if (!TextUtils.isEmpty(accountNickName))
//            personDataEditRq.setAccountNickName(accountNickName);
//        if (!TextUtils.isEmpty(trueName))
//            personDataEditRq.setTrueName(trueName);
//        if (!TextUtils.isEmpty(identityNumber))
//            personDataEditRq.setIdentityNumber(identityNumber);
//        return postTYF(personDataEditUrl, personDataEditRq);
//    }
//
//    /**
//     * 更新安全邮箱
//     */
//    public RequestCall getUpdateSafeEmail_RC(String userId, String version, String email) {
//        String SafeEmailUrl = getBaseUrl() + "/app/api/account/update/email";
//        //设置参数
//        SafeEmailB.SafeEmailRq safeEmailRq = new SafeEmailB.SafeEmailRq();
//        if (!TextUtils.isEmpty(userId))
//            safeEmailRq.setUserId(userId);
//        if (!TextUtils.isEmpty(version))
//            safeEmailRq.setVersion(version);
//        if (!TextUtils.isEmpty(email))
//            safeEmailRq.setEmail(email);
//        return postTYF(SafeEmailUrl, safeEmailRq);
//    }
//
//    /**
//     * 更新安全邮箱
//     */
//    public RequestCall getSubmitSafeQuestion_RC(String accountId, ArrayList<String> questionList, ArrayList<String> answerList) {
//        String SafeEmailUrl = getBaseUrl() + "/app/api/account/update/safety_question";
//        //设置参数
//        SafeQuestionB.Submit_Rq safeQuestionB = new SafeQuestionB.Submit_Rq();
//        if (!TextUtils.isEmpty(accountId))
//            safeQuestionB.setAccountId(accountId);
//        if (questionList != null)
//            safeQuestionB.setQuestionList(questionList);
//        if (answerList != null)
//            safeQuestionB.setAnswerList(answerList);
//        return postTYF(SafeEmailUrl, safeQuestionB);
//    }

    /**
     * 上传文件
     */
    public String getUpdateFileURL() {
        return getBaseUrl() + "/app/api/upload/file";
    }

//    public RequestCall getUploadHeadIcon_RC(String id, String headPicUrl) {
//        String UploadHeadIconUrl = getBaseUrl() + "/app/api/account/update/head_pic";
//        //设置参数
//        AccountB.UploadHeadIconB uploadHeadIconB = new AccountB.UploadHeadIconB();
//        if (!TextUtils.isEmpty(id))
//            uploadHeadIconB.setId(id);
//        if (!TextUtils.isEmpty(headPicUrl))
//            uploadHeadIconB.setHeadPicUrl(headPicUrl);
//        return postTYF(UploadHeadIconUrl, uploadHeadIconB);
//    }
//
//    public RequestCall getwirelessPay_RC(String id, String version) {
//        String wireless_payUrl = getBaseUrl() + "/app/api/account/update/wireless_pay";
//        //设置参数
//        AccountSafeB.wirelessPayB wirelessPayB = new AccountSafeB.wirelessPayB();
//        if (!TextUtils.isEmpty(id))
//            wirelessPayB.setId(id);
//        if (!TextUtils.isEmpty(version))
//            wirelessPayB.setVersion(version);
//        return postTYF(wireless_payUrl, wirelessPayB);
//    }
}
