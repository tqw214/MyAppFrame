1、屏幕分辨率适配方法
原理：通过分辨率按比例缩放
步骤:设置设计图的尺寸数据，然后运行com.viger.screenadapter.plan_one包下的 GenerateValueFiles类中的main方法，
将会在src/main/res/目录下生成各种分辨率（可配置分辨率）的values文件夹
使用：直接按照设计图中的px在xml中引用dimen数值即可。

2、屏幕最小dp宽度适配方法
原理：通过dp按比例缩放
步骤：安装module根目录下的插件ScreenMatch.jar插件，然后在任意目录点击右键-ScreenMatch，选中当前module，即可在
src/main/res/目录下生成一系列类似values-sw600dp的文件夹。
使用：在xml中按照dp来设置，按照ui设计图dp标志或者自己计算，计算方式：
例如设计如720px，按照360dp设计，如果一个按钮长200px,则对应的dp=720px/360dp=系数2(1dp=2px)
所有200px的按钮dp=200/2=100dp

3、今日头条适配方式
原理：通过修改density系数，达到按比例放大px=dp*density
步骤:引入开源库：implementation 'me.jessyan:autosize:1.2.0'
https://github.com/JessYanCoding/AndroidAutoSize/blob/master/README-zh.md
在AndroidManifest.xml中添加：单位dp
<!--720-1280-->
        <meta-data
            android:name="design_width_in_dp"
            android:value="320"/>
        <meta-data
            android:name="design_height_in_dp"
            android:value="640"/>


Android 屏幕适配 使用蓝湖解决屏幕适配问题
1.蓝湖官网：
https://lanhuapp.com/
ui图片长度采用dp标注

一种非常好用的Android屏幕适配 - 最小屏幕宽度适配
https://www.jianshu.com/p/1302ad5a4b04

大多数 UI 设计师提供设计图有如下几种方式：
上传到蓝湖：显示多少 dp 就写多少 dp。
psd 源文件：用像素大厨查看，显示多少 dp 就写多少 dp（注意像素大厨需要选择与设计图对应的dpi 进行显示）
dp 单位的设计图：标注多少 dp 就写多少 dp。
px 单位的设计图：叫 UI 设计师标注为 dp 单位或跟她要 psd 源文件，如果都不行，那自己算吧！

 使用步骤总结
1、以设计图最小宽度（单位为 dp）作为基准值，利用插件生成所有设备对应的 dimens.xml 文件
2、根据设计图标注，标注多少 dp，布局中就写多少dp，格式为@dimen/dp_XX。

怎么适配其他 module?
问题：在项目的其他 module 中怎么实现适配？难道也要多套 dimens 文件？
解决：并不需要多套 dimens 文件，只需要在 values 文件夹下有一套与 app module 一样的 dimens 文件即可达到适配。因为经过编译，
所有 module 中的 dimen 数据都会统一归类到主 module（即 app module）中的 values/dimens.xml 文件中了，
然后系统又会根据你设置的值去找对应 values-swxxxdp 文件夹下的dimens.xml 文件中的值。

如何同时适配横竖屏？
方案一：（不推荐）
计算出设备宽度和高度的dp值，然后生成对应的宽高 dimens.xml 文件。然后去掉所有 values-swXXXdp 目录上的s，即改为 values-wXXXdp。
这样设备不管横竖屏都能找到对应的 values-wXXXdp 目录下的 dimens.xml 文件了。 虽然也能达到一定程度的适配，
但是这样会增加很多 dimens.xml 文件，而且使用竖屏的设计图显示出来的效果也不够好。
方案二：（推荐）
因为横屏时宽高变化太大，想要横屏时也能完全适配，那就只能让设计师出一套横屏的设计图，然后单独写一套横屏的布局文件。

GitHub:https://github.com/wildma/ScreenAdaptation

