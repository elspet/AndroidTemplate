## 模板项目说明（ @User : *Lisa* ）

### 零、前言


### 一、依赖库说明
###### 1.1、网络请求框架
+ `OkHttp3` 
+ `Retrofit`
+ `RxJava` \ `RxAndroid`
+ `Gson`

###### 1.2、图片加载工具
+ `Glide `
###### 1.3、数据库框架
+ `GreenDao`
###### 1.4、日志框架
+ `Logger`
###### 1.5、控件注入框架
+ `ButterKnife`
###### 1.6、权限请求框架
+ `EasyPermission`
###### 1.7、markdown展示库
+ `com.zzhoujay.markdown:markdown:1.0.5`   
 用来展示readme.md文件，嘿嘿

###### 1.8、调试工具
+ 'stetho'

###### 1.9、组件间通信工具
+ `EventBus`

###### 1.10、滑动退出页面
+ `bga-swipebacklayout`

#### 二、Android 资源文件命名
###### 1.1、注释规范
+ 以阿里编码规约工具为准

###### 1.2、基本命名方式
+ 包名：只用小写字母，每个节点只包含一个单词
+ 类名：

###### 1.3、layout布局文件命名方式
+ Activity： 以 `module_activity` 开头
+ Fragment： 以 `module_fragment` 开头
+ Dialog： 以 `module_dialog` 开头
+ include： 以 `module_include` 开头
+ ListView的item： 以 `module_list_item` 开头
+ RecyclerView的item： 以 `module_recycle_item` 开头
+ GridView的item： 以 `module_grid_item` 开头

###### 1.4、drawable资源名称
以小写单词+下划线的方式命名  
根据分辨率不同存放 在不同的 drawable 目录下，建议只使用一套,例如 drawable-xhdpi。
+ `模块名_业务功能描述_控件描述_控件状态限定词`

###### 1.5、anim资源名称
小写单词+下划线
+ `模块名_逻辑名称_[方向|序号]`

###### 1.6、color资源名称
以 `#AARRGGBB`格式，写入`module_colors.xml`文件
+ `模块名_逻辑名称_颜色`

如 ：
`<color name="module_btn_bg_color">#33b5e5e5</color>`

###### 1.7、dimen资源名称
小写单词+下划线，写入`module_dimens.xml`文件
+ `模块名_描述信息`

如：`<dimen name="module_horizontal_line_height">1dp</dimen>`

###### 1.8、style资源名称
小写单词+下划线，写入`module_style.xml`文件

+ `父 style 名称.当前 style 名称`
如：
```
<style name="ParentTheme.ThisActivityTheme">
 ...
</style>
```
###### 1.9、string资源名称
小写单词+下划线，写入`module_strings.xml`文件

+ `模块名_逻辑名称`

如：`moudule_login_tips , module_homepage_notice_desc`

###### 1.10、id资源名称
原则上以驼峰命名法命名  

**缩写：**
+ LinearLayout : lv
+ RelativeLayout : rl
+ ConstraintLayout : cl
+ ListView : lv
+ GridView : gv
+ ScrollView : sv
+ TextView : tv
+ Button : btn
+ ImageView : iv
+ CheckBox : cb
+ RedioButton : rb
+ EditText : et \ edt
+ ProgressBar : progress_bar
+ DatePicker : date_picker

###### 1.11、大分辨率图片统一放在xxhdpi文件夹










