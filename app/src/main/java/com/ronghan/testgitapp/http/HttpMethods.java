package com.ronghan.testgitapp.http;

import com.admin.utils.ABLogUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.been.HttpResult;
import com.ronghan.testgitapp.tools.ApiException;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/27 0027 15:14
 * 修改人：Michael
 * 修改时间：2016/4/27 0027 15:14
 * 修改备注：
 */
public class HttpMethods<T> {
    //    public static final String BASE_URL = "http://139.224.16.80:28080/";
    public static final String BASE_URL = "http://192.168.1.180:7777/";

    private static final int DEFAULT_TIMEOUT = 50;

    private Retrofit retrofit;

    //构造方法私有

    public HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    /*private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCode() != 10000) {
                throw new ApiException(httpResult.getErrmsg());
            }
//            ABLogUtil.i(httpResult.getData().toString());
            return httpResult.getData();
        }
    }*/

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc implements Func1<HttpResult, String> {
        @Override
        public String call(HttpResult httpResult) {
            ABLogUtil.i("httpResult.getCode() == 10000:" + (httpResult.getCode() == 10000));
            if (httpResult.getCode() != 10000) {
                throw new ApiException(httpResult.getErrmsg());
            }
            if (null == httpResult.getData()) {
                httpResult.setData("");
            }
            ABLogUtil.i(httpResult.getData().toString());
            return httpResult.getData();
        }

    }

    /***********************************************************************/
    /******************************用户操作(user)****************************/
    /***********************************************************************/
    /**
     * 1.登录
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject 用户名：username
     *                   密码：password
     */
    public void login(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.LoginService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 2.登出
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void logout(Subscriber<String> subscriber) {
        Observable observable = retrofit.create(HttpService.LogoutService.class).post()
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************账户操作(app_user)************************/
    /***********************************************************************/
    /**
     * 1.注册发送短信
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userName ： 账号（手机号码）
     */
    public void sendVerificationCode(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.SendVerificationCodeService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 2.核对注册短信码
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userName ： 账号（手机号码）
     *                   verificationCode ： 验证码
     */
    public void checkVerificationCode(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.CheckVerificationCodeService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 3.注册
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userName : 账号（手机号码）
     *                   password : 密码
     *                   extensionCode : 邀请码
     */
    public void register(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.RegisterService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 4.appUser查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 登录成功后的（id）
     */
    public void appUserShow(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.AppUserShowService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 5.添加银行信息
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject customerId : appUser编号
     *                   bankAccount : 银行账号
     *                   accountName :持卡人名称
     *                   bankName : 银行名称
     *                   mobilePhone : 预留电话
     *                   idCard : 身份证
     */
    public void createBank(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.CreateBankService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 6.查询上级用户
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject id : appUser（id）
     */
    public void parentService(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.ParentService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 7.账户激活
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject customerId : appUser编号
     *                   isActivation : 是否激活[YES(激活),NO[不激活]]
     */
    public void editActivation(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.EditActivationService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 8.发送短信
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userName ： 账号（手机号码）
     */
    public void sendMessage(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.SendMessageService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 9.重置密码
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userName ： 账号（手机号码）
     *                   password ： 密码
     */
    public void resetPassword(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.ResetPasswordService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 10.添加或修改支付密码
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 当前登录用户ID
     *                   payPassword : 支付密码
     */
    public void editPayPassword(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.EditPayPasswordService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 11.核对支付密码
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 当前登录用户ID
     *                   payPassword : 支付密码
     */
    public void payPassword(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.PayPasswordService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************推广数据**********************************/
    /***********************************************************************/
    /**
     * 1.推广数据查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 登录者账号id
     */
    public void promotionSearch(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.PromotionSearchService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 2.推广统计
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 登录者账号id
     */
    public void promotionStatistic(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.PromotionStatisticService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 3.推广详情
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 登录者账号ID
     *                   nowRole : 查询角色[CLERK：店员，SHOPOWNER：店长，BOSS：老板]
     */
    public void appUserPromotion(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.AppUserPromotionService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************收款分润**********************************/
    /***********************************************************************/
    /**
     * 1. 收款分润信息(通道)
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId ： 当前登录者（id）
     */
    public void collectionProfitSearch(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.CollectionProfitSearchService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************产品*************************************/
    /***********************************************************************/
    /**
     * 1.产品分页
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject page : 页数
     *                   pageSize : 每页数量
     */
    public void productPagination(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.ProductPaginationService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 2.产品单个查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject id : 产品ID
     */
    public void productSearch(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.ProductSearchService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 3.产品类型
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void productTypeList(Subscriber<String> subscriber) {
        Observable observable = retrofit.create(HttpService.ProductTypeListService.class).post()
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 4.产品分润详情
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void productRatioList(Subscriber<String> subscriber) {
        Observable observable = retrofit.create(HttpService.ProductRatioListService.class).post()
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************钱包*************************************/
    /***********************************************************************/
    /**
     * 1.钱包查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 当前登录者（id）
     */
    public void walletSearch(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.WalletSearchService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************转账*************************************/
    /***********************************************************************/
    /**
     * 1.转账查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 当前登录者（id）
     *                   【transferRecordId : 转账编号】(用户筛选出那一条)
     *                   page : 页数
     *                   pageSize : 每页数量
     */
    public void transferRecordPagination(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.TransferRecordPaginationService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 2.转账
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 转账者
     *                   userName : 转账账户
     *                   amount : 转账金额
     */
    public void transferRecordCreate(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.TransferRecordCreateService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************提现*************************************/
    /***********************************************************************/
    /**
     * 1. 提现查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject page : 页数
     *                   pageSize : 每页数量
     *                   userId : 当前登录者（ID）
     */
    public void withdrawPagination(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.WithdrawPaginationService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 2.提现
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 当前登录者（ID）
     *                   amount : 提现金额
     */
    public void withdrawCreate(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.WithdrawCreateService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************分润查询**********************************/
    /***********************************************************************/
    /**
     * 1.分润查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 当前登录用户ID
     *                   status : 类型[ALL:全部，COLLECTION:收款分润，PRODUCT:产品分润]
     */
    public void billingPagination(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.BillingPaginationService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************申请升级为代理商***************************/
    /***********************************************************************/
    /**
     * 1.请求升级代理商
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 申请者
     *                   phone : 联系电话
     *                   bankAccount : 银行卡号
     *                   accountName : 持卡人
     *                   bankName : 银行名称
     *                   mobilePhone : 预留电话
     */
    public void notificationAgentCreate(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.NotificationAgentCreateService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************app功能配置信息访问************************/
    /***********************************************************************/
    /**
     * 1. banner信息查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject bannerType : MALL或者TITLE    //MALL为请求商城banner，TITLE为请求首页banner
     */
    public void bannerFind(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.BannerFindService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 2.ico信息查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void icoFindAll(Subscriber<String> subscriber) {
        Observable observable = retrofit.create(HttpService.IcoFindAllService.class).post()
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 3.parameter推广参数信息
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void parameterFindAll(Subscriber<String> subscriber) {
        Observable observable = retrofit.create(HttpService.ParameterFindAllService.class).post()
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 4.urlConfig配置参数信息
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void urlConfigFindAll(Subscriber<String> subscriber) {
        Observable observable = retrofit.create(HttpService.UrlConfigFindAllService.class).post()
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /********************parameter推广参数(新增查询费率/金额接口)***************/
    /***********************************************************************/
    /**
     * 1.推广参数费率/金额查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void parameterFindMoneyOrRatio(Subscriber<String> subscriber) {
        Observable observable = retrofit.create(HttpService.ParameterFindMoneyOrRatioService.class).post()
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /********************推送消息********************************************/
    /***********************************************************************/
    /**
     * 1.查询所有推送消息
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void notificationFindAll(Subscriber<String> subscriber) {
        Observable observable = retrofit.create(HttpService.NotificationFindAllService.class).post()
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************购物车************************/
    /***********************************************************************/
    /**
     * 1.购物车查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject page : 当前页数
     *                   pageSize : 没有显示数量
     */
    public void shopCarPagination(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.ShopCarPaginationService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 2.添加购物车
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject productId : 产品id
     *                   userId : 用户当前登录id
     */
    public void shopCarCreate(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.ShopCarCreateService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 3.购物车修改
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject id : 购物车ID
     *                   count : 购买数量
     */
    public void shopCarEdit(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.ShopCarEditService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 4.删除购物车
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject ids : 购物车ID(可以是多个)
     */
    public void shopCarDelete(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.ShopCarDeleteService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************订单************************/
    /***********************************************************************/
    /**
     * 1.订单查询
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId : 当前登录用户id
     *                   status : 订单类型[ALL:全部,WAIT_PAYMENT:待付款,PROCESSING:交易处理中,WAIT_DELIVERY:正在出库,ISCANCEL:已取消,DELIVERY:已出库,SUCCESS:已完成,WAIT_PICKUP:退货,WAIT_REFUND:待退款,COMPLETE:已退货退款,CANCEL:已废交易]
     *                   page : 当前页数
     *                   pageSize : 每页数量
     */
    public void orderPagination(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.OrderPaginationService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 2.添加订单
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject customerId : 用户customerId
     *                   productId : 产品id
     *                   count : 购买数量
     *                   paymentType : 支付类型[WALLET:钱包支付,WALLET_OTHER:钱包和在线支付,OTHER:在线支付]
     *                   walletAmount : 钱包支付金额(当paymentType为（WALLET,WALLET_OTHER）有值)
     *                   deliveryAddressId : 收货地址Id
     */
    public void orderCreate(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.OrderCreateService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 3.确认收货
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject orderId : 订单编号
     */
    public void orderConfirmReceive(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.OrderConfirmReceiveService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 4.修改订单状态
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject orderId : 订单编号
     *                   status : 订单状态
     *                   (注: 1.WAIT_PAYMENT时可以ISCANCEL；2.WAIT_DELIVERY时可以ISCANCEL；3.DELIVERY时可以SUCCESS[确认收货])
     *                   [ALL:全部,WAIT_PAYMENT:待付款,PROCESSING:交易处理中,WAIT_DELIVERY:正在出库,ISCANCEL:已取消,DELIVERY:已出库,SUCCESS:已完成,WAIT_PICKUP:退货,WAIT_REFUND:待退款,COMPLETE:已退货退款,CANCEL:已废交易]
     */
    public void orderEditStatus(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.OrderEditStatusService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    /***********************************************************************/
    /******************************收货地址信息*******************************/
    /***********************************************************************/
    /**
     * 1.创建收货地址信息
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject userId:         用户账号
     *                   name:           收货人姓名
     *                   address:        收货地址
     *                   telephone:      电话号码
     *                   status:         是否为默认收货地址(YES :是, NO :否)
     */
    public void deliveryCreate(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.DeliveryCreateService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 2.删除收货地址信息
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param s id : 收货地址信息的id,传参方式见 请求地址
     */
    public void deliveryDelete(Subscriber<String> subscriber, String s) {
        Observable observable = retrofit.create(HttpService.DeliveryDeleteService.class).get(s)
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 3.更新收货地址信息
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param jsonObject id:             唯一标识
     *                   name:           收货人姓名
     *                   address:        收货地址
     *                   telephone:      电话号码
     *                   status:         是否为默认收货地址的状态(YES:是, NO:否)
     */
    public void deliveryUpdate(Subscriber<String> subscriber, JSONObject jsonObject) {
        Observable observable = retrofit.create(HttpService.DeliveryUpdateService.class).post(JSON.toJSONString(jsonObject))
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    /**
     * 4.查询所有收货地址信息
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param s userId:         账户信息ID
     */
    public void deliveryFind(Subscriber<String> subscriber, String s) {
        Observable observable = retrofit.create(HttpService.DeliveryFindService.class).get(s)
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }
}
