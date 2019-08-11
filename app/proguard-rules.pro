#-flattenpackagehierarchy com.stone.myapplication.interfaces # 将包里的类混淆成n个再重新打包到一个个的package中
#-repackageclasses com.stone.myapplication.interfaces #将包里的类混淆成n个再重新打包到一个统一的package中  会覆盖flattenpackagehierarchy选项
#-forceprocessing #强制处理，即使混淆后的输出文件已经是最新
#-overloadaggressively #过度加载，多个属性和方法使用相同的名字，只是参数和返回类型不同 可能各种异常

# 混淆时可能被移除下面这些东西，如果想保留，需要用该选项。对于一般注解处理如上
# 可以保持的参数有：Exceptions, Signature, Deprecated, SourceFile, SourceDir, LineNumberTable,
#LocalVariableTable, LocalVariableTypeTable, Synthetic,
#EnclosingMethod, RuntimeVisibleAnnotations, RuntimeInvisibleAnnotations, RuntimeVisibleParameterAnnotations,
#RuntimeInvisibleParameterAnnotations, and AnnotationDefault.

 #-keep public class * extends java.lang.annotation.Annotation  #保持任意 继承了注解的 class的声明(package和名不变，所有使用到该class的地方都不变)

 #-keep ,allowoptimization,allowshrinking public class com.stone.myapplication.Alph {
 #,allowoptimization,allowshrinking 受全局 -dontshrink影响 无效
 #不使用allowobfuscation时，类名相关不混淆，成员未定义keep，混淆了
 #使用allowobfuscation时，类名也混淆了
 #}

 #缺省情况下，proguard 会混淆所有代码，但是下面几种情况是不能改变java 元素的名称，否则就会这样就会导致程序出错。
 #一， 我们用到反射的地方。
 #二， 我们代码依赖于系统的接口，比如被系统代码调用的回调方法，这种情况最复杂。
 #三， 是我们的java 元素名称是在配置文件中配置好的。
 #常见的不能混淆的androidCode
 #Android 程序 ，下面这样代码混淆的时候要注意保留。
 #Android系统组件，系统组件有固定的方法被系统调用。
 #被Android Resource 文件引用到的。名字已经固定，也不能混淆，比如自定义的View 。
 #Android Parcelable ，需要使用android 序列化的。
 #其他Anroid 官方建议 不混淆的，如
 #android.app.backup.BackupAgentHelper
 #android.preference.Preference
 #com.android.vending.licensing.ILicensingService
 #Java序列化方法，系统序列化需要固定的方法。
 #枚举 ，系统需要处理枚举的固定方法。
 #本地方法，不能修改本地方法名
 #annotations 注释
 #数据库驱动
 #有些resource 文件
 #用到反射的地方
 #所以使用proguard时，我们需要有个配置文件告诉proguard 那些java 元素是不能混淆的。
 #-optimizationpasses 105

 #-keepclassmembers class * extends android.app.Activity {
 #   public void *(android.view.View);
 #}

 #-keepclassmembers class **.R$* {
 #    public static <fields>;
 #}

#测试 反编译  是否  混淆
-optimizationpasses 5
-dontusemixedcaseclassnames   #不使用大小写混合类名
-mergeinterfacesaggressively  #合并接口
#-dontshrink #反射调用的eventbus的方法 不瘦身 不移除
#-dontoptimize #不优化
#-dontobfuscate #不混淆
-printseeds ** #打印-keep处理的类和类成员列表
-dontskipnonpubliclibraryclasses #不跳过(即混淆)类库中的非public classes
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-allowaccessmodification
-printmapping map.txt
-dontskipnonpubliclibraryclassmembers
-ignorewarnings
-keepattributes LocalVariableTable
-keepparameternames
-useuniqueclassmembernames #类和类成员都使用不一样的名字

-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
-keep public class * extends android.os.IInterface

-dontwarn tv.danmaku.**
-keep class tv.danmaku.**{*;}

-keep class com.application.tchapj.*.bean.**{*; }

-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}
-keep class m.framework.**{*;}
-dontwarn cn.sharesdk.**
-dontwarn com.sina.**
-dontwarn com.mob.**
-dontwarn **.R$*

-dontwarn android.net.**
-keep class android.net.SSLCertificateSocketFactory{*;}

-keep public class * extends java.lang.Throwable {*;}
-keep public class * extends java.lang.Exception {*;}
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-keep class com.alipay.sdk.app.H5PayCallback {
    <fields>;
    <methods>;
}
-keep public class com.google.vending.licensing.ILicensingService
-keep class com.alipay.android.phone.mrpc.core.** { *; }
-keep class com.alipay.apmobilesecuritysdk.** { *; }
-keep class com.alipay.mobile.framework.service.annotation.** { *; }
-keep class com.alipay.mobilesecuritysdk.face.** { *; }
-keep class com.alipay.tscenter.biz.rpc.** { *; }
-keep class org.json.alipay.** { *; }
-keep class com.alipay.tscenter.** { *; }
-keep class com.ta.utdid2.** { *;}
-keep class com.ut.device.** { *;}

-keepattributes *Annotation*
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes SourceFile,LineNumberTable

-keep ,allowoptimization,allowshrinking,allowobfuscation public interface *  #dontobfuscate时 该keep 无效

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepnames class * implements java.io.Serializable

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclassmembers !final class *  implements java.io.Serializable { #保持非final 的实现了序列化接口的类成员
    public private protected static volatile transient <fields>;   # 属性
    public private  <methods>; #public 和private 方法
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-keep class **.R$* { *; }
-dontwarn android.support.**
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

-dontwarn com.android.support.**
-keep class com.android.support.** { *; }

-dontwarn de.greenrobot.event.**
-keep class de.greenrobot.event.** { *; }

-keepclassmembers class ** {
    public void onEvent*(**);
    void onEvent*(**);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-dontwarn com.hannesdorfmann.**
-keep class com.hannesdorfmann.**{*;}

-dontwarn com.squareup.**
-keep class com.squareup.**{*;}

-dontwarn io.reactivex.**
-keep class io.reactivex.**{*;}

-dontwarn com.jude.**
-keep class com.jude.**{*;}

-dontwarn com.jakewharton.**
-keep class com.jakewharton.**{*;}

-dontwarn jp.wasabeef.**
-keep class jp.wasabeef.**{*;}

-dontwarn com.google.**
-keep class com.google.**{*;}

-dontwarn de.hdodenhof.**
-keep class de.hdodenhof.**{*;}

-dontwarn org.greenrobot.**
-keep class org.greenrobot.**{*;}

-dontwarn com.bigkoo.**
-keep class com.bigkoo.**{*;}

-dontwarn com.qiniu.**
-keep class com.qiniu.**{*;}

-dontwarn com.king.base.**
-keep class com.king.base.**{*;}

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#,allowobfuscation  #启用混淆  本例表示 只启用瘦身和优化
# returntype methodname(argumenttype,...)
-keep ,allowoptimization,allowshrinking,allowobfuscation public class com.stone.myapplication.*Activity {
    protected <methods>;#keep activity时，继承的方法自动受保护了
#    public void onEvent*(android.app.Activity);
#    public void onEvent*(android.app.Activity, %);
#    public % *();
#    private int ll;
    public *;
#    public void onEventMainThread(android.app.Activity,...);
#    public void onEventAsync(android.app.Activity,...);
#    public void onEventBackgroundThread(android.app.Activity,...);
}

# 上面保护，下面启用 启用无效； 上面启用，下面保护，保护有效。 重在保护
-keep public class com.stone.myapplication.*Activity {
     public void onEvent*(android.app.Activity);
#      *;
 }

 -assumenosideeffects public class com.stone.myapplication.Alph {
     public String testassumenosideeffects();
 }

 -keepnames public class com.stone.myapplication.Alph {

 }

 -keep  @com.stone.myapplication.annotation.Say public class * { #保持使用了@Say的任意class 声明   User声明
     public <methods>; #保持所有public 方法
     private <fields>; #操持所有private 属性
 }

 -keepclasseswithmembers @com.stone.myapplication.annotation.Say public class * { #保持使用了@Say的任意class 声明   User声明
 #    public <methods>; #保持所有public 方法
 #    private <fields>; #操持所有private 属性
     private int ll;
 }

 -keep @com.stone.myapplication.annotation.Say  @ class * #保持使用了@Say的注解@Ha 的声明









