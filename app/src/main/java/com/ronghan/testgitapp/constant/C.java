package com.ronghan.testgitapp.constant;

import android.content.Context;
import android.text.TextUtils;

import com.admin.mine.MApplication;
import com.admin.utils.ABLogUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.squareup.okhttp.OkHttpClient;

import butterknife.ButterKnife;


/**
 * User: Meekly-hj
 * Date: 2015-06-19
 * Time: 10:31
 * 自定义 Application
 */
@SuppressWarnings("ALL")
public class C extends MApplication {
    //需要使用的类对象
    /**
     * Application对象
     */
    public static C application;

    public static C getInstance() {
        return application;
    }

    public void onCreate() {
        this.application = this;
        setIsGetLog(false); //是否抓取错误日志（默认抓取）
        ABLogUtil.setVerbose(true, true, true, true); //是否显示相应日志
        ButterKnife.setDebug(true);
        initImageLoader(getApplicationContext());
        super.onCreate();
    }

    private OkHttpClient okHttpClient;

    public OkHttpClient getHttpClient() {
        if (okHttpClient == null)
            okHttpClient = new OkHttpClient();
        return okHttpClient;
    }

//    /**
//     * 初始化OkHttpUtils
//     */
//    private void initOkHttp() {
//        //设置证书
//        OkHttpUtils.getInstance().setCertificates(new InputStream[]{new Buffer().writeUtf8(H.CER_12306).inputStream()});
//        //设置请求超时事件
//        OkHttpUtils.getInstance().getOkHttpClient().setConnectTimeout(60000, TimeUnit.MILLISECONDS);
//    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

//    /**
//     * ImageLoader 图片设置
//     *
//     * @return options
//     */
//    public DisplayImageOptions getOptions() {
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.ic_stub)
//                .showImageForEmptyUri(R.mipmap.ic_empty)
//                .showImageOnFail(R.mipmap.jd)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .considerExifParams(true)
//                .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
//                .build();
//        return options;
//    }
//
//    //需要使用的类的实例
//    /*登录返回数据*/
//    public LoginB.ReceiveB loginB;
//    /*个人资料*/
//    public PersonDataB.PersonDataRS personDataRS = null;
//    public AccountB.WalletRSB walletRSB = null;
//    public MainActivity mainActivity = null;
//    public int defaultPageSize = 2;

    public String getResultMsg(String resultMsg) {
        if (TextUtils.isEmpty(resultMsg))
            return "";
        switch (resultMsg) {
            //登陆
            case "UNKNOWN_ACCOUNT_ERROR":
                return "未知用户";
            case "DISABLED_ACCOUNT_ERROR":
                return "用户未激活";
            case "EXCESSIVE_ATTEMPTS_ERROR":
                return "登录过于频繁";
            case "INCORRECT_CREDENTIALS_ERROR":
                return "用户名或密码错误";
            case "REQUEST_FAILURE_ERROR":
                return "远程请求异常";
            case "UNKNOWN_ERROR":
                return "未知错误";
            //
            case "ACCOUNT_EXIST_ERROR":
                return "账户名(手机号码)已存在";
            case "VERIFICATION_CODE_NOT_EXIST_ERROR":
                return "验证码为空，请输入验证码";
            case "VERIFICATION_CODE_INVALIDATION_ERROR":
                return "验证码无效或错误";
            case "UNKNOWN_VERIFY_ACCOUNT_ERROR":
                return "未知验证账户，请输入验证账户";
            case "MAIL_SEND_FAILURE_ERROR":
                return "邮件发送失败";
            case "UPLOAD_FILE_ERROR":
                return "上传文件失败";
            default:
                return "其他未知错误";
        }
    }
}
