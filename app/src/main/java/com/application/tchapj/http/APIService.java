package com.application.tchapj.http;


import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseModel;
import com.application.tchapj.bean.BindingPhoneBean;
import com.application.tchapj.bean.MemberInfo;
import com.application.tchapj.bean.PromotionPayResultBean;
import com.application.tchapj.bean.PromotionResultBean;
import com.application.tchapj.bean.SmsCodeBean;
import com.application.tchapj.consultation.bean.AddNewsCommentResultBean;
import com.application.tchapj.consultation.bean.CommentsResultBean;
import com.application.tchapj.consultation.bean.ConsultationNewsModel;
import com.application.tchapj.consultation.bean.ConsultationTopModel;
import com.application.tchapj.consultation.bean.InsertComments;
import com.application.tchapj.consultation.bean.IsAuthorModel;
import com.application.tchapj.consultation.bean.NewsAttentionResultBean;
import com.application.tchapj.consultation.bean.NewsCommentResultBean;
import com.application.tchapj.consultation.bean.ZanBean;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.NewPhoneLoginResult;
import com.application.tchapj.login.bean.SmsCodeResponse;
import com.application.tchapj.main.bean.FlashScreenBean;
import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.main.bean.HomeMediaList;
import com.application.tchapj.main.bean.HomePerson;
import com.application.tchapj.main.bean.HomeTopData;
import com.application.tchapj.main.bean.MemberInfoWhbyBean;
import com.application.tchapj.main.bean.MessagenotificationBean;
import com.application.tchapj.main.bean.MingPeopleListBean;
import com.application.tchapj.main.bean.NewsInfo;
import com.application.tchapj.main.bean.PersonMediaModel;
import com.application.tchapj.main.bean.PersonSelectModel;
import com.application.tchapj.main.bean.StartInitiationDataModel;
import com.application.tchapj.my.bean.AlipayPowerBean;
import com.application.tchapj.my.bean.AlipayPrivateKeyBean;
import com.application.tchapj.my.bean.AttentionListBean;
import com.application.tchapj.my.bean.DamuInfo;
import com.application.tchapj.my.bean.DarenDataBean;
import com.application.tchapj.my.bean.DarenDataOneBean;
import com.application.tchapj.my.bean.FansListBean;
import com.application.tchapj.my.bean.FrozenListBean;
import com.application.tchapj.my.bean.GuanggaoBean;
import com.application.tchapj.my.bean.MicroInfoBean;
import com.application.tchapj.my.bean.MicroTabBean;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.my.bean.MoneyInfoListBean;
import com.application.tchapj.my.bean.MoneyTransferBean;
import com.application.tchapj.my.bean.PostDamuInfo;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.my.bean.UpMyInfoBean;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.search.bean.SearchBean;
import com.application.tchapj.task.bean.FaTaskBean;
import com.application.tchapj.task.bean.FaTaskSuccessBean;
import com.application.tchapj.task.bean.FaTaskSuccessafterBean;
import com.application.tchapj.task.bean.MyTaskSquareModel;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.bean.TaskSquareModel;
import com.application.tchapj.video.bean.VideosModel;

import org.greenrobot.greendao.annotation.Id;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

// 接口类对象
public interface APIService {

    String APP_KEY = "002";
    String V = "1.0";
    String SIGN = "";
    String FORMAT = "JSON";
    String OS_TYPE = "1";


    // 首頁 消息
    @POST("app?method=pm.main.news.info")
    Observable<NewsInfo> getNewsInfoResult(@Query("memberId") String memberId
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 新首頁 消息
    @POST("app?method=pm.main.info")
    Observable<MessagenotificationBean> getMessagenotificationBeanResult(@Query("memberId") String memberId
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 首页Banner
    @POST("app?method=pm.main.top")
    Observable<HomeTopData> getHomeBannerResult(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 首页找名人
    @POST("app?method=pm.main.mingren")
    Observable<HomePerson> getHomePersonResult(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 首页找媒体
    @POST("app?method=pm.main.media")
    Observable<HomeMediaList> getHomeMediaListResult(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 首页找圈子
    @POST("app?method=pm.main.circles")
    Observable<HomeCircleModel> getHomeCircleModelResult(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 首页找圈子详情  圈子列表
    @POST("app?method=pm.media.ownmedia")
    Observable<HomeCircleInfoModel> getHomeCircleInfoModelResult(
            @Query("circleTypeId") String circleTypeId, @Query("dystate") String dystate
            , @Query("pyqstate") String pyqstate, @Query("wbstate") String wbstate
            , @Query("pageNum") String pageNum, @Query("pageSize") String pageSize
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 我的关注-名人列表
    @POST("app?method=pm.member.attentionbymr")
    Observable<BaseBean<AttentionListBean>> getMyAttentionCelebrityListModelResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("memberId") String memberId, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    // 我的关注-媒体列表
    @POST("app?method=pm.member.attentionbymt")
    Observable<BaseBean<AttentionListBean>> getMyAttentionMediaListModelResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("memberId") String memberId, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    // 我的关注-圈子列表
    @POST("app?method=pm.member.attentionbyqz")
    Observable<BaseBean<AttentionListBean>> getMyAttentionCircleListModelResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("memberId") String memberId, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    // 我的粉丝列表
    @POST("app?method=pm.member.fanslist")
    Observable<BaseBean<FansListBean>> getMyFansListModelResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("memberId") String memberId, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    /*// 全部名人分类
    @POST("app?method=allselect.mingrenResources")
    Observable<PersonMediaModel> getPersonMediaModelResult(@Query("type") String type
            , @Query("pageNum") String pageNum, @Query("pageSize") String pageSize
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);*/


    // 名人分类数据
    @FormUrlEncoded
    @POST("app?method=class.obtainResources")
    Observable<PersonMediaModel> getPersonMediaResult(@Query("cat_type") String cat_type
            , @Query("newsTypeId") String newsTypeId, @Field("serviceitem") String serviceitem
            , @Query("pageNum") String pageNum, @Query("pageSize") String pageSize
            , @Query("appKey") String appKey, @Query("v") String v
            , @Query("sign") String sign, @Query("format") String format);

    // 明星活跃周榜
    @POST("app?method=huoyue.mingrenList")
    Observable<MingPeopleListBean> getMingPeopleListResult(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 分类
    @POST("app?method=newstype.select")
    Observable<PersonSelectModel> getPersonSelectModelResult(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);


    // 分类 ConsultationTopModel
    @POST("app?method=newstype.select")
    Observable<ConsultationTopModel> getConsultationTopResult(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);


    @POST("app?method=pm.news.comments")
    Observable<BaseBean<NewsCommentResultBean>> getNewsCommentsRequest(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("pageNum") String pageNum
            , @Query("pageSize") String pageSize, @Query("userId") String userId, @Query("news_id") String news_id);

    //对评论进行评论
    @FormUrlEncoded
    @POST("app?method=pm.news.insertcommentreply")
    Observable<BaseBean<AddNewsCommentResultBean>> getAddNewsReplyCommentsRequest(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("userId") String userId, @Query("news_id") String news_id
            , @Field("content") String content, @Query("replyType") int replyType
            , @Query("commentId") String commentId, @Query("toMemberId") String toMemberId);

    //发表评论
    @FormUrlEncoded
    @POST("app?method=pm.news.insertcomments")
    Observable<BaseBean<AddNewsCommentResultBean>> getAddNewsCommentsRequest(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("userId") String userId, @Query("news_id") String news_id
            , @Field("content") String content);

    //关注、喜欢
    @POST("app?method=pm.media.attention")
    Observable<BaseBean<NewsAttentionResultBean>> getNewsAttentionRequest(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("memberId") String memberId, @Query("Id") String Id
            , @Query("isCancel") String isCancel);

    //回复评论列表
    @POST("app?method=pm.news.reply")
    Observable<BaseBean<CommentsResultBean>> getNewsCommentsReplyRequest(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("userId") String userId, @Query("news_id") String newsId
            , @Query("commentId") String commentId, @Query("pageNum") String pageNum
            , @Query("pageSize") String pageSize);


    // 视频
    @POST("app?method=pm.news.video")
    Observable<VideosModel> getVideosModelResult(@Query("pageNum") String pageNum
            , @Query("pageSize") String pageSize, @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 任务广场
    @POST("app?method=pm.task.list")
    Observable<TaskSquareModel> getTaskSquareModelResult(@Query("memberId") String memberId
            , @Query("pageNum") String pageNum, @Query("pageSize") String pageSize
            , @Query("appKey") String appKey, @Query("v") String v
            , @Query("sign") String sign, @Query("format") String format);


    // 我的任务分析列表
    @POST("app?method=pm.task.mytasklist")
    Observable<TaskSquareModel> getMyTaskListResult(@Query("memberId") String memberId,
                                                    @Query("Status") String status
            , @Query("pageNum") String pageNum, @Query("pageSize") String pageSize
            , @Query("appKey") String appKey, @Query("v") String v
            , @Query("sign") String sign, @Query("format") String format);


    // 弹幕
    @POST("app?method=dictionary.opinions.get")
    Observable<DamuInfo> getDamuInfoResult(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 发弹幕
    @FormUrlEncoded
    @POST("app?method=dictionary.opinions.new")
    Observable<PostDamuInfo> getPostDamuInfoResult(@Query("memberId") String memberId
            , @Field("opinions") String opinions, @Query("nickName") String nickName
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);


    // 新手机验证登录
    @FormUrlEncoded
    @POST("app?method=pm.login.newregister")
    Observable<NewPhoneLoginResult> getNewPhoneLoginResult(@Field("name") String username
            , @Query("code") String code, @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 第三方登录
    @FormUrlEncoded
    @POST("app?method=pm.login.thirdlandings")
    Observable<NewPhoneLoginResult> getThirdLoginResult(@Query("tid") String id
            , @Query("logintype") String logintype, @Field("name") String name
            , @Field("Sex") String sex, @Query("headimgurl") String headimgurl
            , @Query("appKey") String appKey, @Query("v") String v
            , @Query("sign") String sign, @Query("format") String format);

    // 七牛token
    @POST("app?method=pm.member.uploadtoken")
    Observable<QiniuBean> getQiniuBeanResult(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);


    // 得到登录数据
    @POST("app?method=pm.login.member")
    Observable<LoginResult> getLoginResult(@Query("name") String username
            , @Query("password") String password, @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign, @Query("format") String format);

    // 得到密码数据
    @POST("app?method=pm.login.updatepsw")
    Observable<LoginResult> getUpdateSwResult(@Query("name") String username
            , @Query("password") String password, @Query("code") String code
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 得到注册数据
    @POST("app?method=pm.login.register")
    Observable<LoginResult> getRegisterResult(@Query("name") String username
            , @Query("password") String password, @Query("code") String code
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 获取验证码
    @POST("app?method=pm.auth.code")
    Observable<BaseBean<SmsCodeBean>> getBindingSmsCodeResult(@Query("phonenumber") String phonenumber
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 三方登录绑定手机号
    @POST("app?method=pm.member.addphonenum")
    Observable<BaseBean<BindingPhoneBean>> onThirdLoginBindingResult(@Query("mobile") String mobile
            , @Query("memberId") String memberId
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);


    // 得到验证码数据
    @POST("app?method=pm.login.code")
    Observable<SmsCodeResponse> getSmsCodeResult(@Query("name") String name
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 小微号认证标签
    @POST("app?method=newstype.select")
    Observable<MicroTabBean> getMicroTabBeanResult(@Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 小微号认证  去掉身份证正反面照片字段：IDimgurl，IDbackimgurl
    @FormUrlEncoded
    @POST("app?method=pm.auth.add")
    Observable<MicroInfoBean> getMicroInfoBeanResult(

            @Query("Id") String Id
            , @Field("catType") String catType
            , @Query("resourcesTypeId") String resourcesTypeId
            , @Field("xwhName") String xwhName
            , @Query("headimageUrl") String headimageUrl
            , @Field("realName") String realName
            , @Field("IDnumber") String IDnumber
            , @Field("phoneNumber") String phoneNumber
            , @Query("code") String code
            , @Field("companyName") String companyName
            , @Field("conpanyCreditCode") String conpanyCreditCode
            , @Query("conpanyImgUrl") String conpanyImgUrl
            , @Query("cityId") String cityId
            , @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 小微号认证  去掉身份证正反面照片字段：IDimgurl，IDbackimgurl
    @FormUrlEncoded
    @POST("app?method=pm.auth.add")
    Observable<MicroInfoBean> getMicroInfoBeanResult(

            @Query("Id") String Id
            , @Field("catType") String catType
            , @Query("resourcesTypeId") String resourcesTypeId
            , @Field("xwhName") String xwhName
            , @Query("headimageUrl") String headimageUrl
            , @Field("realName") String realName
            , @Field("IDnumber") String IDnumber
            , @Field("phoneNumber") String phoneNumber
            , @Query("code") String code
            , @Field("companyName") String companyName
            , @Field("conpanyCreditCode") String conpanyCreditCode
            , @Query("conpanyImgUrl") String conpanyImgUrl
            , @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 搜索
    @FormUrlEncoded
    @POST("app?method=pm.main.searchbox")
    Observable<SearchBean> getSearchBeanResult(
            @Field("Name") String Name
            , @Query("appKey") String appKey
            , @Query("v") String v
            , @Query("sign") String sign
            , @Query("format") String format);

    // 搜索更多
    @FormUrlEncoded
    @POST("app?method=pm.main.searchbox")
    Observable<SearchBean> getSearchBeanResultmost(
            @Field("Name") String Name
            , @Field("id") String id
            , @Query("appKey") String appKey
            , @Query("v") String v
            , @Query("sign") String sign
            , @Query("format") String format);

    // 用户信息
    @POST("app?method=pm.member.get")
    Observable<UserModel> getUserModelResult(@Query("memberId") String memberId, @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign, @Query("format") String format);


    // 用户信息
    @POST("app?method=pm.member.get")
    Observable<MemberInfo> getMemberModelResult(@Query("memberId") String memberId, @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign, @Query("format") String format);


    // 用户信息
    @POST("app?method=pm.member.get")
    Observable<UserModel> getMemberModelResult(@QueryMap Map<String, String> map);

    // 支付宝私钥 参数
    @POST("app?method=alipay.app.privatekey")
    Observable<AlipayPrivateKeyBean> getAlipayPrivateKeyBeanResult(
            @Query("appKey") String appKey, @Query("v") String v
            , @Query("sign") String sign, @Query("format") String format);

    // 支付宝授权
    @POST("app?method=alipay.app.info.auth")
    Observable<AlipayPowerBean> getAlipayPowerBeanResult(
            @Query("memberId") String memberId, @Query("code") String authCode
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 达人资料完善
  /*  @FormUrlEncoded
    @POST("app?method=pm.receivetask.apply")
    Observable<DarenDataOneBean> getDarenDataOneBeanResult(

            @Query("memberId") String memberId
            , @Query("resourcesTypeId") String resourcesTypeId
            , @Field("realName") String realName
            , @Field("nickName") String nickName
            , @Field("sex") String sex
            , @Query("IDnumber") String IDnumber
            , @Query("phoneNumber") String phoneNumber
            , @Field("content") String content
            , @Query("headimageUrl") String headimageUrl
            , @Query("catType") String catType
            , @Query("price") String price
            , @Query("screenshotIngUrl") String screenshotIngUrl
            , @Query("appKey") String appKey
            , @Query("v") String v
            , @Query("sign") String sign
            , @Query("method") String method
            , @Query("format") String format
            , @Query("secret") String secret
            , @Query("type") String type);*/

    @FormUrlEncoded
    @POST("app?method=pm.receivetask.apply")
    Observable<DarenDataOneBean> getDarenDataOneBeanResult(

            @Query("appKey") String appKey
            , @Query("v") String v
            , @Query("format") String format
            , @Query("memberId") String memberId
            , @Field("nickName") String nickName
            , @Field("realName") String realName
            , @Field("sex") String sex
            , @Query("catType") String catType
            , @Query("resourcesTypeId") String resourcesTypeId
            , @Field("content") String content
            , @Query("screenshotIngUrl") String screenshotIngUrl
            , @Query("headimageUrl") String headimageUrl
            , @Query("inviteCode") String inviteCode
            , @Query("price") String price
            , @Query("cityId") String cityId
            , @Query("IDnumber") String IDnumber);


    // 达人资料完善
    @FormUrlEncoded
    @POST("app?method=pm.resources.apply")
    Observable<DarenDataBean> getDarenDataBeanResult(

            @Query("Id") String Id
            , @Query("MemberId") String MemberId
            , @Query("catType") String catType
            , @Field("fans") String fans
            , @Field("nickName") String nickName
            , @Field("price") String price
            , @Query("screenshotIngUrl") String screenshotIngUrl
            , @Field("province") String province
            , @Field("citys") String city
            , @Query("conpanyImgUrl") String conpanyImgUrl
            , @Field("Views") String Views
            , @Field("comments") String comments
            , @Field("likes") String likes

            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 认证资料
    @FormUrlEncoded
    @POST("app?method=pm.issuetask.apply")
    Observable<GuanggaoBean> getGuanggaoBeanResult(

            @Query("memberId") String Id
            , @Field("realName") String realName
            , @Field("nickName") String nickName
            , @Field("companyName") String companyName
            , @Query("conpanyCreditCode") String conpanyCreditCode
            , @Query("conpanyImgUrl") String conpanyImgUrl
            , @Query("phoneNumber") String phoneNumber
            , @Query("code") String code
            , @Field("industry") String industry

            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);


    /**
     * 发布任务
     *
     * @param Id
     * @param taskImageType
     * @param name
     * @param require
     * @param imgUrl
     * @param startTime
     * @param endTime
     * @param unitPrice
     * @param taskImgUrl
     * @param taskNum
     * @param taskGuidance
     * @param phonenumber
     * @param copywriting
     * @param Fans
     * @param ForwardUrl
     * @param appKey
     * @param v
     * @param sign
     * @param format
     * @param type
     * @param secret
     * @param method
     * @return
     */
    @FormUrlEncoded
    @POST("app?method=pm.task.release")
    Observable<FaTaskBean> getFaTaskBeanResult(

            @Query("memberId") String Id
            , @Query("taskType") String taskType
            , @Query("taskImageType") String taskImageType
            , @Field("name") String name
            , @Field("require") String require
            , @Query("imgUrl") String imgUrl
            , @Query("startTime") String startTime
            , @Query("endTime") String endTime
            , @Query("unitPrice") String unitPrice
            , @Query("taskImgUrl") String taskImgUrl
            , @Query("taskNum") String taskNum
            , @Field("taskGuidance") String taskGuidance
            , @Query("phonenumber") String phonenumber
            , @Field("copywriting") String copywriting
            , @Query("Fans") String Fans
            , @Field("ForwardUrl") String ForwardUrl

            , @Query("appKey") String appKey
            , @Query("v") String v
            , @Query("sign") String sign
            , @Query("format") String format
            , @Query("type") String type
            , @Query("secret") String secret
            , @Query("method") String method

    );

    // 我的发任务
    @POST("app?method=pm.task.ownfalist")
    Observable<MyTaskSquareModel> getFaTaskSquareinfoModelResult(
            @Query("memberId") String memberId
            , @Query("pageNum") String pageNum, @Query("pageSize") String pageSize
            , @Query("appKey") String appKey, @Query("v") String v
            , @Query("sign") String sign, @Query("format") String format);

    // 我的领任务
    @POST("app?method=pm.task.ownlist")
    Observable<MyTaskSquareModel> getTaskSquareinfoModelResult(
            @Query("memberId") String memberId
            , @Query("pageNum") String pageNum, @Query("pageSize") String pageSize
            , @Query("appKey") String appKey, @Query("v") String v
            , @Query("sign") String sign, @Query("format") String format);


    // 任务详情
    @FormUrlEncoded
    @POST("app?method=pm.task.detail")
    Observable<TaskSquareInfoModel> getTaskDetail(
            @Field("memberId") String memberId
            , @Field("id") String id
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);


    //递名片
    @POST("app?method=pm.task.order")
    Observable<BaseBean> getTaskdouyin(@Query("id") String id, @Query("memberId") String memberId, @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 领取任务
    @POST("app?method=pm.task.add")
    Observable<BaseBean> releaseTask(
            @Query("memberId") String memberId, @Query("id") String id
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);


    // 用户信息
    @POST("app?method=pm.task.doing")
    Observable<ResponseBody> submitTaskReview(@Query("memberId") String memberId, @Query("taskImgUrl") String taskImgUrl, @Query("id") String id, @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign, @Query("format") String format);

    // 任务提交审核资料
    @POST("app?method=pm.task.doing")
    Observable<BaseBean> submitAuditTask(@Query("memberId") String memberId, @Query("taskImgUrl") String taskImgUrl, @Query("id") String id, @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign, @Query("format") String format);

    // 预付订单成功
    @FormUrlEncoded
    @POST("app?method=alipay.app.pay")
    Observable<FaTaskSuccessBean> getFaTaskSuccessBeanResult(

            @Query("amount") String amount
            , @Field("method") String method
            , @Query("name") String name
            , @Query("appKey") String appKey,
            @Query("v") String v,
            @Query("sign") String sign
            , @Query("format") String format);

    // 任务付款成功
    @POST("app?method=pm.task.havepay")
    Observable<FaTaskSuccessafterBean> getFaTaskSuccessafterBeanResult(

            @Query("amount") String amount
            , @Query("id") String id
            , @Query("ordernumber") String ordernumber
            , @Query("memberId") String memberId

            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 获取钱包信息
    @POST("app?method=pm.own.wallet")
    Observable<MoneyInfoBean> getMoneyInfoBeanResult(

            @Query("memberId") String memberId
            , @Query("appKey") String appKey
            , @Query("v") String v
            , @Query("sign") String sign
            , @Query("format") String format);

    // 收益提现和退款--支付宝
    @POST("app?method=alipay.app.transfer")
    Observable<MoneyTransferBean> getMoneyTransferBeanResult(

            @Query("amount") String amount
            , @Query("id") String id
            , @Query("memberId") String memberId
            , @Query("appKey") String appKey
            , @Query("v") String v
            , @Query("sign") String sign
            , @Query("format") String format);


    //大额提现--人工转账
    @FormUrlEncoded
    @POST("app?method=promotion.app.transfer")
    Observable<BaseBean> getArtificialTransferResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("memberId") String memberId, @Query("amount") String amount, @Field("bankOutlets") String bankOutlets
            , @Field("name") String name, @Query("accountNumber") String accountNumber);

    // 我的任务列表--零钱模块
    @POST("app?method=pm.own.listlog")
    Observable<MoneyInfoListBean> getMoneyInfoListBeanResult(

            @Query("pageNum") String pageNum
            , @Query("pageSize") String pageSize
            , @Query("memberId") String memberId
            , @Query("classify") String classify

            , @Query("appKey") String appKey,
            @Query("v") String v,
            @Query("sign") String sign
            , @Query("format") String format);

    //  发评论接口
    @FormUrlEncoded
    @POST("app?method=pm.news.insertcomments")
    Observable<InsertComments> getInsertCommentsResult(

            @Field("content") String content
            , @Query("userId") String userId
            , @Query("news_id") String news_id

            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    //  点赞接口
    //isCancel  0：点赞  1：取消点赞
    @POST("app?method=pm.news.commentlikes")
    Observable<ZanBean> getZanBeanResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("userId") String memberId
            , @Query("id") String id
            , @Query("type") String type
            , @Query("isCancel") String isCancel);


    // 全部发现
    @POST("app?method=pm.news.all")
    Observable<ConsultationNewsModel> getConsultationNewsModelAllResult(@Query("pageNum") String pageNum
            , @Query("pageSize") String pageSize, @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("userId") String userId);

    // 其他发现
    @FormUrlEncoded
    @POST("app?method=pm.news.classify")
    Observable<ConsultationNewsModel> getConsultationNewsModelResult(@Field("newstype_id") String newstype_id
            , @Query("pageNum") String pageNum, @Query("pageSize") String pageSize
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("userId") String userId);

    //我的咨询列表
    @FormUrlEncoded
    @POST("app?method=pm.usernews.newsList")
    Observable<ConsultationNewsModel> getUserNewsListModelResult(@Field("userId") String userId,
                                                                 @Field("newModel") String newstype_id, @Field("pageNum") String pageNum, @Field("pageSize") String pageSize
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    //查询是否有资格开通 微呼百应号
    @POST("app?method=pm.user.isauthor")
    Observable<IsAuthorModel> getUserIsAuthorResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("format") String format,
            @Query("type") String type, @Query("memberId") String memberId);

    //开通微呼百应号
    @POST("app?method=pm.user.updateAuthor")
    Observable<BaseBean> updateUserIsAuthorResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("format") String format,
            @Query("type") String type, @Query("memberId") String memberId);

    // 修改个人信息
    @FormUrlEncoded
    @POST("app?method=pm.member.updateMember")
    Observable<UpMyInfoBean> getUpMyInfoBeanResult(

            @Query("memberId") String memberId

            , @Field("sex") String sex, @Query("headimgurl") String headimgurl
            , @Field("nickName") String nickName, @Field("province") String province
            , @Field("city") String city, @Query("birthday") String birthday

            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    // 修改微呼百应号信息
    @FormUrlEncoded
    @POST("app?method=pm.member.updatewhbyh")
    Observable<BaseBean> updateWhbyInfoResult(
            @Query("memberId") String memberId
            , @Query("headimgurl") String headimgurl
            , @Field("nickName") String nickName, @Field("province") String province
            , @Field("city") String city, @Query("mobile") String mobile, @Field("content") String content
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);


    //发布文章（图文、视频、转载）
    @FormUrlEncoded
    @POST("app?method=pm.news.addNews")
    Observable<BaseModel> getAddNewsResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("format") String format,
            @Query("UserId") String uerId, @Field("content") String content, @Field("ImgUrl") String imgurl, @Query("Type") String Type);

    // 删除文章
    @POST("app?method=pm.usernews.deletenews")
    Observable<BaseModel> getDeleteNewsResult(@Query("news_id") String news_id, @Query("appKey") String appKey
            , @Query("v") String v, @Query("sign") String sign, @Query("format") String format);


    //获取初始化数据 app重启时请求
    @POST("app?method=pm.start.initiation")
    Observable<BaseBean<StartInitiationDataModel>> getStartInitiation(
            @Query("appKey") String appKey, @Query("v") String v, @Query("format") String format, @Query("osType") String osType);

    //获取微呼百应号设置
    @POST("app?method=pm.member.getwhbyh")
    Observable<BaseBean<MemberInfoWhbyBean>> getMemberWhbyhResult(
            @Query("memberId") String memberId
            , @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);

    //获取启屏(闪屏)页数据
    @POST("app?method=pm.main.openpage")
    Observable<BaseBean<FlashScreenBean>> getFlashScreenResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format);


    // 我的钱包-冻结资金列表
    @POST("app?method=pm.task.frozenlist")
    Observable<BaseBean<FrozenListBean>> getMoneyFrozenListResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("memberId") String memberId, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    // 我的钱包-冻结资金列表-冻结资金转到余额
    @POST("app?method=pm.task.frozentobalance")
    Observable<BaseBean<FrozenListBean>> getFrozenToBalanceResult(
            @Query("appKey") String appKey, @Query("v") String v, @Query("sign") String sign
            , @Query("format") String format, @Query("memberId") String memberId, @Query("id") String id, @Query("amount") String amount);


    /**
     * 生成推广金预付订单
     *
     * @param appKey   app唯一标记
     * @param v        版本号
     * @param secret   密钥
     * @param format   格式
     * @param sign     令牌
     * @param type     客户端类型（"1":Android、 "2":IOS、 "3":WEB）
     * @param memberId 用户id
     * @param amount   充值金额
     * @param name     用户名
     * @return
     */
    @POST("app?method=promotion.app.recharge")
    Observable<BaseBean<PromotionResultBean>> promotionAdvanceOrder(
            @Query("appKey") String appKey
            , @Query("secret") String secret
            , @Query("method") String method
            , @Query("v") String v
            , @Query("format") String format
            , @Query("sign") String sign
            , @Query("memberId") String memberId
            , @Query("amount") String amount
            , @Query("name") String name
            , @Query("type") String type

    );

    @POST("app?method=promotion.app.alipay.success")
    Observable<BaseBean<PromotionResultBean>> promotionOrderSuccess(
            @Query("appKey") String appKey
            , @Query("secret") String secret
            , @Query("method") String method
            , @Query("v") String v
            , @Query("format") String format
            , @Query("sign") String sign
            , @Query("memberId") String memberId
            , @Query("amount") String amount
            , @Query("name") String name
            , @Query("type") String type
            , @Query("ordernumber") String ordernumber

    );

    /**
     * 推广金支付
     *
     * @param appKey
     * @param secret
     * @param method
     * @param v
     * @param format
     * @param sign
     * @param type
     * @param amount
     * @param Id
     * @param memberId
     * @return
     */
    @POST("app?method=promotion.app.pay")
    Observable<PromotionPayResultBean> promotionPayOrder(
            @Query("appKey") String appKey
            , @Query("secret") String secret
            , @Query("method") String method
            , @Query("v") String v
            , @Query("format") String format
            , @Query("sign") String sign
            , @Query("type") String type
            , @Query("amount") String amount
            , @Query("Id") String Id
            , @Query("memberId") String memberId
    );

    @FormUrlEncoded
    @POST("app?method=pm.member.updateIdentity")
    Observable<BaseBean> updateIndetity(
            @Query("appKey") String appKey
            , @Query("secret") String secret
            , @Query("method") String method
            , @Query("v") String v
            , @Query("format") String format
            , @Query("sign") String sign
            , @Query("type") String type
            , @Query("memberId") String memberId
            , @Query("identity") String identity
            , @Field("realname") String realname
    );

}
