package com.ronghan.testgitapp.http;

import com.ronghan.testgitapp.been.HttpResult;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/9 0009 16:15
 * 修改人：Michael
 * 修改时间：2016/5/9 0009 16:15
 * 修改备注：
 */
public class HttpService {


    /***************************************************************************/
    /*********************************用户操作(user)*****************************/
    /***************************************************************************/
    /**
     * 1.登录
     */
    public interface LoginService {
        @POST("app/api/auth/login")
        Observable<HttpResult> post(@Query("json") String strJson);
    }

    /**
     * 2.登出
     */
    public interface LogoutService {
        @GET("app/api/auth/logout")
        Observable<HttpResult> post();
    }


    /***************************************************************************/
    /*********************************账户操作(app_user)*************************/
    /***************************************************************************/
    /**
     * 1.注册发送短信
     */
    public interface SendVerificationCodeService {

        @POST("app/api/app_user/send/verification_code")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 2.核对注册发送短信
     */
    public interface CheckVerificationCodeService {
        @POST("app/api/app_user/check/verification_code")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 3.注册
     */
    public interface RegisterService {
        @POST("app/api/app_user/register")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 4.AppUser查询
     */
    public interface AppUserShowService {
        @POST("/app/api/app_user/show")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 5.添加银行信息
     */
    public interface CreateBankService {
        @POST("/app/api/app_user/create_bank")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 6.查询上级用户
     */
    public interface ParentService {
        @POST("/app/api/app_user/parent")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 7.设置账户激活
     */
    public interface EditActivationService {
        @POST("/app/api/app_user/edit_activation")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 8.发送短信
     */
    public interface SendMessageService {
        @POST("/app/api/app_user/send/message")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 9.重置密码
     */
    public interface ResetPasswordService {
        @POST("/app/api/app_user/reset_password")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 10.添加或修改支付密码
     */
    public interface EditPayPasswordService {
        @POST("/app/api/app_user/edit/pay_password")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 11.核对支付密码
     */
    public interface PayPasswordService {
        @POST("/app/api/app_user/pay_password")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /*********************************推广数据***********************************/
    /***************************************************************************/
    /**
     * 查询推广
     */
    public interface QueryPromotionService {
        @POST("/app/api/app_user/promotion")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 1.推广数据查询
     */
    public interface PromotionSearchService {
        @POST("/app/api/promotion/search")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 2.推广统计
     */
    public interface PromotionStatisticService {
        @POST("/app/api/promotion/statistic")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 3.推广详情
     */
    public interface AppUserPromotionService {
        @POST("/app/api/app_user/promotion")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /*********************************收款分润***********************************/
    /***************************************************************************/
    /**
     * 1.收款分润信息(通道)
     */
    public interface CollectionProfitSearchService {
        @POST("/app/api/collection_profit/search")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /*********************************产品**************************************/
    /***************************************************************************/
    /**
     * 1.产品分页
     */
    public interface ProductPaginationService {
        @POST("/app/api/product/pagination")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 2.产品单个查询
     */
    public interface ProductSearchService {
        @POST("/app/api/product/search")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 3.产品类型
     */
    public interface ProductTypeListService {
        @POST("/app/api/product_type/list")
        Observable<HttpResult> post();
    }

    /**
     * 4.产品分润详情
     */
    public interface ProductRatioListService {
        @POST("/app/api/profit_ratio/list/list")
        Observable<HttpResult> post();
    }


    /***************************************************************************/
    /*********************************钱包**************************************/
    /***************************************************************************/
    /**
     * 1.钱包查询
     */
    public interface WalletSearchService {
        @POST("/app/api/wallet/search")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /*********************************转账**************************************/
    /***************************************************************************/
    /**
     * 1.转账查询
     */
    public interface TransferRecordPaginationService {
        @POST("/app/api/transfer_record/pagination")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 2.转账
     */
    public interface TransferRecordCreateService {
        @POST("/app/api/transfer_record/create")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /*********************************提现**************************************/
    /***************************************************************************/
    /**
     * 1.提现查询
     */
    public interface WithdrawPaginationService {
        @POST("/app/api/withdraw/pagination")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 2.提现
     */
    public interface WithdrawCreateService {
        @POST("/app/api/withdraw/pagination")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /**********************************分润查询**********************************/
    /***************************************************************************/
    /**
     * 1.分润查询
     */
    public interface BillingPaginationService {
        @POST("/app/api/billing/pagination")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /**********************************申请升级为代理商***************************/
    /***************************************************************************/
    /**
     * 1.请求升级代理商
     */
    public interface NotificationAgentCreateService {
        @POST("/app/api/notification_agent/create")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /*********************************app功能配置信息访问*************************/
    /***************************************************************************/
    /**
     * 1.banner信息查询
     */
    public interface BannerFindService {
        @POST("/app/api/banner/find")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /*********************************收款分润***********************************/
    /***************************************************************************/
    /**
     * 2.ico信息查询
     */
    public interface IcoFindAllService {
        @POST("/app/api/ico/find_all")
        Observable<HttpResult> post();
    }

    /**
     * 3.parameter推广参数信息
     */
    public interface ParameterFindAllService {
        @POST("/app/api/parameter/find_all")
        Observable<HttpResult> post();
    }

    /**
     * 4.urlConfig配置参数信息
     */
    public interface UrlConfigFindAllService {
        @POST("/app/api/url_config/find_all")
        Observable<HttpResult> post();
    }


    /***************************************************************************/
    /********************parameter推广参数(新增查询费率/金额接口)*******************/
    /***************************************************************************/
    /**
     * 1.推广参数费率/金额查询
     */
    public interface ParameterFindMoneyOrRatioService {
        @POST("/app/api/parameter/find_money_or_ratio")
        Observable<HttpResult> post();
    }


    /***************************************************************************/
    /********************推送消息************************************************/
    /***************************************************************************/
    /**
     * 1.查询所有推送消息
     */
    public interface NotificationFindAllService {
        @POST("/app/api/notification/find_all")
        Observable<HttpResult> post();
    }


    /***************************************************************************/
    /********************购物车     ************************************************/
    /***************************************************************************/
    /**
     * 1.购物车查询
     */
    public interface ShopCarPaginationService {
        @POST("/app/api/shop_car/pagination")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 2.添加购物车
     */
    public interface ShopCarCreateService {
        @POST("/app/api/shop_car/create")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 3.购物车修改
     */
    public interface ShopCarEditService {
        @POST("/app/api/shop_car/edit")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 4.删除购物车
     */
    public interface ShopCarDeleteService {
        @POST("/app/api/shop_car/delete")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /********************订单************************************************/
    /***************************************************************************/
    /**
     * 1.订单查询
     */
    public interface OrderPaginationService {
        @POST("/app/api/order/pagination")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 2.添加订单
     */
    public interface OrderCreateService {
        @POST("/app/api/order/create")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 3.确认收货
     */
    public interface OrderConfirmReceiveService {
        @POST("/app/api/order/confirm_receive")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 4.修改订单状态
     */
    public interface OrderEditStatusService {
        @POST("/app/api/order/edit_status")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }


    /***************************************************************************/
    /********************收货地址信息*********************************************/
    /***************************************************************************/
    /**
     * 1.创建收货地址信息
     */
    public interface DeliveryCreateService {
        @POST("/api/delivery/create")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 2.删除收货地址信息
     */
    public interface DeliveryDeleteService {
        @GET("/api/delivery/delete/{id}")
        Observable<HttpResult> get(@Path("id") String jsonStr);
    }

    /**
     * 3.更新收货地址信息
     */
    public interface DeliveryUpdateService {
        @POST("/api/delivery/update")
        Observable<HttpResult> post(@Query("json") String jsonStr);
    }

    /**
     * 4.查询所有收货地址信息
     */
    public interface DeliveryFindService {
        @GET("/api/delivery/find/{para}")
        Observable<HttpResult> get(@Path("para") String jsonStr );
    }

}
