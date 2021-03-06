-dontshrink
-dontoptimize
-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

-keep public class * extends android.app.Activity      # 保持哪些类不被混淆
-keep public class * extends android.app.Application   # 保持哪些类不被混淆
-keep public class * extends android.app.Service       # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆   
    public void *(android.view.View);
}

#-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}

-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆  
    public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep public class * implements java.io.Serializable {*;}
-dontwarn cn..**
-dontwarn com.baidu.**
-dontwarn com.google.gson.**
-dontwarn android.support.**
-dontwarn org.apache.http.**
-dontwarn com.squareup.picasso.**
-dontwarn android.net.http.**

-keep class com.google.gson.** {*;}
-keep class org.apache.http.** {*;}
-keep class com.squareup.picasso.** {*;}
-keep class android.net.http.** {*;}
-keep class com.time.memory.wxapi.** { *; }
-keep class com.time.memory.MainApplication{ *; }
-keep class com.time.memory.entity.** { *; }
-keep public class * extends android.app.Application
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
-keep class sun.misc.Unsafe.**{*;}
-keep class com.google.protobuf.**{*;}
-keep class org.codehaus.mojo.animal_sniffer.**{*;}
-keep class butterknife.** { *; }
-keep class com.time.memory.model.AliUploadController{ *; }
#-keep class com.time.memory.util.**{ *; }

#-keep class com.zbar.lib.** { *; }
-keep class cn.jpush.** { *; }
-keep class com.time.memory.mt.vo.** { *; }
-keep class com.time.memory.mt.common.constant.message.** { *; }
-keep class com.time.memory.mt.nio.message.response.** { *; }
-keep class com.time.memory.mt.nio.message.response.** { *; }
-keep class com.time.memory.mt.nio.proto.** { *; }
-keep class com.time.memory.mt.** { *; }


#-dontwarn com.zbar.lib.**
-dontwarn org.codehaus.mojo.animal_sniffer.**
-dontwarn sun.misc.Unsafe.**
-dontwarn com.google.protobuf.**
-dontwarn butterknife.internal.**

-keep class java.nio.file.**{*;}
-dontwarn java.nio.file.**

-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * { @butterknife.* <fields>;}
-keepclasseswithmembernames class * { @butterknife.* <methods>;}
-dontwarn org.apache.http.**

#定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

# 搜索
-keep  class com.amap.api.services.**{*;}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

#-keep public class [com.time.memory].R$*{
#public static final int *;
#}


-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**
-keep public class com.umeng.socialize.* {*;}

-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements   com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep class com.tencent.mm.sdk.** {
 *;
}

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep public class com.umeng.com.umeng.soexample.R$*{
public static final int *;
}
-keep public class com.linkedin.android.mobilesdk.R$*{
public static final int *;
    }
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}

-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}

-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}
-keepnames class * implements android.os.Parcelable {
public static final ** CREATOR;
}

-keep class com.linkedin.** { *; }
-keepattributes Signature


# webview + js
-keepattributes *JavascriptInterface*
# keep 使用 webview 的类
-keepclassmembers class  com.veidy.activity.WebViewActivity {
   public *;
}
# keep 使用 webview 的类的所有的内部类
-keepclassmembers  class  com.veidy.activity.WebViewActivity$*{
    *;
}

-keepattributes *Annotation*
-keepattributes *JavascriptInterface*


#greenDao混淆
# # -------------------------------------------
# #  ######## greenDao混淆  ##########
# # -------------------------------------------
-keep class de.greenrobot.dao.** {*;}
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static Java.lang.String TABLENAME;
}
-keep class **$Properties

#volley混淆
# # -------------------------------------------
# #  ############### volley混淆  ###############
# # -------------------------------------------
-keep class com.time.memory.core.volley.** {*;}
-keep class com.time.memory.core.volley.toolbox.** {*;}
-keep class com.time.memory.core.volley.Response$* { *; }
-keep class com.time.memory.core.volley.Request$* { *; }
-keep class com.time.memory.core.volley.RequestQueue$* { *; }
-keep class com.time.memory.core.volley.toolbox.HurlStack$* { *; }
-keep class com.time.memory.core.volley.toolbox.ImageLoader$* { *; }

### greenDAO 3
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

# If you do not use SQLCipher:
#-dontwarn org.greenrobot.greendao.database.**
# If you do not use RxJava:
-dontwarn rx.**

### Alibaba_Oss
-keep class com.alibaba.sdk.android.**{ *; }
-keep class com.alibaba.sdk.android.oss.**{ *; }

-keep class com.squareup.okhttp3.**{ *;}
-keep class okhttp3.**{ *;}
-keep class okio.**{*;}

-keep class com.time.memory.core.im.**{*;}
-keep class org.apache.**{*;}
-keep class org.apache.commons.codec.binary.**{*;}
-keep class com.android.internal.http.multipart.**{*;}

-keep class javax.crypto.**{*;}

-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn com.squareup.okhttp3.**
-dontwarn com.time.memory.core.im.**
-dontwarn org.apache.commons.codec.binary.**
-dontwarn com.android.internal.http.multipart.**
-dontwarn javax.crypto.**

