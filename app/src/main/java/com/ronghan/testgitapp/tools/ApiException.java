package com.ronghan.testgitapp.tools;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/27 0027 15:52
 * 修改人：Michael
 * 修改时间：2016/4/27 0027 15:52
 * 修改备注：
 */
public class ApiException extends RuntimeException {

    public static final String UNKNOWN_ACCOUNT_ERROR = "UNKNOWN_ACCOUNT_ERROR";//未知用户
    public static final String DISABLED_ACCOUNT_ERROR = "DISABLED_ACCOUNT_ERROR";//用户未激活
    public static final String EXCESSIVE_ATTEMPTS_ERROR = "EXCESSIVE_ATTEMPTS_ERROR";//登录过于频繁
    public static final String INCORRECT_CREDENTIALS_ERROR = "INCORRECT_CREDENTIALS_ERROR";//用户名或密码错误
    public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";//未知错误
    public static final String CUSTOMER_EXIST_ERROR = "CUSTOMER_EXIST_ERROR";//用户名已存在
    public static final String VERIFICATION_CODE_INVALIDATION_ERROR = "VERIFICATION_CODE_INVALIDATION_ERROR";//验证码无效或错误
    public static final String REGISTER_FAILURE_ERROR = "REGISTER_FAILURE_ERROR";//注册失败
    public static final String EXTENSIONCODE_CUSTOMER_ERROR = "EXTENSIONCODE_CUSTOMER_ERROR";//该推广用户不存在
    public static final String CUSTOMER_NO_FOUND = "CUSTOMER_NO_FOUND";//没有找到用户信息
    public static final String ADD_BANK_MESSAGE_ERROR = "ADD_BANK_MESSAGE_ERROR";//添加银行信息失败
    public static final String NO_FOUNT_PAY_PASSWORD_ERROR = "NO_FOUNT_PAY_PASSWORD_ERROR";//没有设置支付码
    public static final String CHECK_PAY_PASSWORD_ERROR = "CHECK_PAY_PASSWORD_ERROR";//支付密码错误
    public static final String NO_FOUNT_OTHER_CUSTOMER_ERROR = "NO_FOUNT_OTHER_CUSTOMER_ERROR";//没有找到对方账户信息
    public static final String WALLET_BALANCE_NOT_ENOUGH = "WALLET_BALANCE_NOT_ENOUGH";//钱包余额不足
    public static final String TRANSFER_RECORD_ERROR = "TRANSFER_RECORD_ERROR";//转账失败
    public static final String REQUEST_FAILURE_ERROR = "REQUEST_FAILURE_ERROR";//远程请求异常
    public static final String PRODUCT_NO_FOUND_ERROR = "PRODUCT_NO_FOUND_ERROR";//没有找到相关产品信息
    public static final String CUSTOMER_NO_ACTIVATION_ERROR = "CUSTOMER_NO_ACTIVATION_ERROR";//：该推广用户未激活
    public static final String BANNER_NO_FOUND_ERROR = "BANNER_NO_FOUND_ERROR";//：banner没有找到


    public ApiException(String errmsg) {
        super(getApiExceptionMessage(errmsg));
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @param errmsg
     * @return
     */
    private static String getApiExceptionMessage(String errmsg) {
        String message;
        switch (errmsg) {
            case UNKNOWN_ACCOUNT_ERROR:
                message = "未知用户";
                break;
            case DISABLED_ACCOUNT_ERROR:
                message = "用户未激活";
                break;
            case EXCESSIVE_ATTEMPTS_ERROR:
                message = "登录过于频繁";
                break;
            case INCORRECT_CREDENTIALS_ERROR:
                message = "用户名或密码错误";
                break;
            case REQUEST_FAILURE_ERROR:
                message = "远程请求异常";
                break;
            case UNKNOWN_ERROR:
                message = "未知错误";
                break;
            case CUSTOMER_EXIST_ERROR:
                message = "用户名已存在";
                break;
            case VERIFICATION_CODE_INVALIDATION_ERROR:
                message = "验证码无效或错误";
                break;
            case REGISTER_FAILURE_ERROR:
                message = "注册失败";
                break;
            case EXTENSIONCODE_CUSTOMER_ERROR:
                message = "该推广用户不存在";
                break;
            case CUSTOMER_NO_FOUND:
                message = "没有找到用户信息";
                break;
            case ADD_BANK_MESSAGE_ERROR:
                message = "添加银行信息失败";
                break;
            case NO_FOUNT_PAY_PASSWORD_ERROR:
                message = "没有设置支付码";
                break;
            case CHECK_PAY_PASSWORD_ERROR:
                message = "支付密码错误";
                break;
            case NO_FOUNT_OTHER_CUSTOMER_ERROR:
                message = "没有找到对方账户信息";
                break;
            case WALLET_BALANCE_NOT_ENOUGH:
                message = "钱包余额不足";
                break;
            case TRANSFER_RECORD_ERROR:
                message = "转账失败";
                break;
            case PRODUCT_NO_FOUND_ERROR:
                message = "没有找到相关产品信息";
                break;
            case CUSTOMER_NO_ACTIVATION_ERROR:
                message = "该推广用户未激活";
                break;
            case BANNER_NO_FOUND_ERROR:
                message = "Banner没有找到";
                break;
            default:
//                message = "未知错误";
                message = "errmsg";

        }
        return message;
    }
}
