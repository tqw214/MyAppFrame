## 打包命令：gradlew assembleRelease
------------------------------------------------------
## android{…} 配置项（AppExtension类）

1、AppExtension的属性 使用方式：buildTypes {}

aaptOptions：aapt是一个可以将资源文件编译成二进制文件的工具。aaptOptions表示aapt工具设置的可选项参数。
adbExecutable：adb从编译sdk时执行
adbOptions：adb的可选项参数
applicationVariants：应用变体列表
==buildToolsVersion==：构建工具版本(必要的)
buildTypes：构建类型(一般是release和debug，还可以自定义)
compileOptions：编译可选项参数
==compileSdkVersion==：编译sdk版本(必要的)
dataBinding：Data Binding可选项参数(关于DataBinding的使用)
defualtConfig：默认配置，对于所有的打包项目
defualtPublishConfig：默认是release。使用参考
dexOptions：Dex可选项参数。
externalNativeBuild：native编译支持。参考
flavorDimensionList：
generatePureSplits：是否拆成多个APK
jacoco：JaCoCo可选项参数
lintOptions：Lint工具可选项参数
ndkDirectory：ndk目录(一般在local.properties中)
packagingOptions：packaging的可选参数
productFlavors：项目所有flavor
publishNonDefualt：不仅仅使用默认的publish artifacts。可参考defualtPublishConfig。
resourcePrefix：创建新资源时使用的前缀。
sdkDirectory：sdk目录(一般在local.properties中)
signingConfigs：签名文件的可选项参数
sourceSets：资源文件目录指定(Android中有自己的AndroidSourceSets，这个一般用于assets，jin等目录)
splits：splits类型。
testBuildType：测试构建类型
testOptions：测试可选项参数
testVariants：测试变体
unitTestVariants：单元测试变体
variantFilter：变体过滤器
------------------------------------------------------
2、AppExtension的方法
flavorDimensions(dimension):指定flavor名称
useLibraray(name)：请求使用一个lib库
useLibrary(name,required)：与上面解释一样。
------------------------------------------------------
3、AppExtension的配置闭包(Configration blocks)
与app中build.gradle中android{}一样，代码中由AppExtension类表示。其他的配置闭包也一样。
aaptOptions{}
aaptOptions{}代码由AaptOptions类表示。

AaptOptions的属性：
1.additionalParameters:额外参数，List类型。
2.cruncherEnabled：如果PNG图片是否可以大量快速的处理，boolean类型。 true表示可以。
3.cruncherProcesses:快速处理，可能需要更多的内存和CPU。int类型。默认0，值越大处理越快，需要的内存和CPU也越大。
4.failOnMissingConfigEntry:如果没有找到一个配置，就返回一个错误。Boolean值，默认false。
5.ignoreAssetsPattern：忽略Assets模块。
6.moCompress：拓展文件不会打包进apk中。

aaptOptions{
    cruncherEnabled true//快速处理PNG图片
}
------------------------------------------------------
4、buildTypes{}对应的是BuildType类
##buildTypes的属性：
1.applicationIdSuffix：应用id后缀(给Applica)
2.consumerProguardFiles：混淆文件包含在arr包中。
3.debuggable：是否生成一个debug的apk
4.embedMicroApp：可穿戴设备app是否可以使用这个编译类型
5.javaCompileOption：Java编译配置参数
6.jniDebuggable：这个编译类型的配置是否可以与debuggable的native代码生成一个apk
7.manifestPlaceholders：清单占位符
8.minifyEnabled：是否缩小
9.multiDexEnabled：是否拆成多个Dex
10.multiDexKeepFile：指定文本文件编译进主Dex文件中
11.multiDexKeepProguard：指定混淆文件编译进主Dex文件中
12.name：build type的名字
13.proguardFiles：混淆文件
14.pseudoLocalesEnabled：是否生成伪现场apk(如果没有提供混淆规则文件，则设置默认的混淆规则文件（SDK/tools/proguard/proguard-android.txt）)
15.renderscriptDebuggable：使用RenderScript编译器的优化级别。
16.shrinkResources：是否去除未利用的资源，默认false，表示不去除。
17.signingConfig：签名配置
18.testCoverageEnabled：测试覆盖率是否被激活。
19.useJack：过时
20.versionNameSuffix：版本名称后缀
21.zipAlignEnable：是否使用zipalign工具压缩。
------------------------------------------------------
##buildType的方法：
1.buildConfigField(type,name,value)：添加一个变量生成BuildConfig类。
2.consumeProguardFile(proguardFile)：添加一个混淆文件进arr包。
3.consumeProguardFile(proguardFiles)：添加混淆文件进arr包。
4.externalNativeBuild(action)：配置本地的build选项。
5.initWith：复制这个build类型的所有属性。
6.proguardFile(proguardFile)：添加一个新的混淆配置文件。
7.proguradFiles(files):添加新的混淆文件
8.resValue(type,name,value)：添加一个新的生成资源
9.setProguardFiles(proguardFileIterable):设置一个混淆配置文件。
------------------------------------------------------
5、compileOptions{}
compileOptions{}对应的是CompileOptions
CompileOptions的属性：
1.encoding：Java源文件的编码格式
2.incremental：是否应该使用Java编写的Gradle新的增量模型
3.sourceCompatibility：指定编译编译.java文件的jdk版本
4.targetCompatibility：确保class文件与targetCompatibility指定版本，或者更新的java虚拟机兼容
------------------------------------------------------
6、dataBinding{}
dataBinding{}对应的是DataBindingOptions
DataBindingOptions的属性：
1.addDefualtAdapters：是否添加一个默认的data binding适配器。默认true。
2.enabled：是否使用data binding
3.version：data binding使用版本
dataBinding的使用：
dataBinding{
    enabled true
}
------------------------------------------------------
dexOptions{}对应的是DexOptions

DexOptions属性:

1.additionalParameters：给dx添加一系列附加的参数
2.javaMaxHeapSize：当调用dx时指定-Xmx值。
3.jumboMode：使用jumbo(庞大的)模式
4.keepRuntimeAnnotatedClasses：保持所有类中的运行时的注解在主Dex中。
5.maxProcessCount：可以使用Dex的最大并发进程数。默认为4。
6.optimize：运行在dx编译器是否有optimize标记。
7.preDexLibraries：是否预先dex库，它可以改善增量的生成，但是在clear build可能会变慢
8.threadCount：当dx运行时使用的线程的数量。默认4个。

dexOptions{}的用法：

dexOptions {
    preDexLibraries  false
    javaMaxHeapSize "4g"
}
------------------------------------------------------
externalNativeBuild{}对应的是ExternalNativeBuild
ExternalNativeBuild的属性：
1.cmake：CMake工具编译选项。
2.ndkBuild：ndk-build选项。
在externalNativeBuild{}中有2个模块，cmake{}和ndkBuild{}模块
cmake{}对应的是CmakeOptions
CmakeOption的属性：
1.path：你的CmakeLists.txt编译脚本的相对路径。
ndkBuild{}对应的是NdkBuildOptions
NdkBuildOptions的属性：
1.path:你的Android.mk文件的相对路径。
externalNativeBuild{}的用法：
externalNativeBuild{
    ndkBuild{
        path file("src\\main\\jni\\Android.mk")
    }
    cmake {
        path "src/main/cpp/CMakeLists.txt"
    }
}
------------------------------------------------------
lintOptions{}对应于LintOptions

LintOptions的属性：
1.abortOnError：如果发现错误，lint工具是否应该退出这个程序。true表示退出。
2.absolutePaths：是否在输出错误的时候，lint应该展示出全路径。默认是相对路径，也就是默认false。
3.check：精确的检查(搜集)问题的集合，默认情况下，任何问题都可以通过LintOptions.getEnable()启用，没有问题可以通过LintOptions.getDisable()使之无效。
4.checkAllWarnings：是否检查所有警告，包括那些默认关闭。
5.disable：通过id's来压制这个问题，允许修改
6.enable：通过id's来处理这个问题，循序修改，他会将添加id，并返回一个集合。
7.explainIssues：返回lint是否包含错误问题的解释(注意：HTML和XML报告会无条件的去做，忽略这个设置)。
8.htmlOutput：html输出方式。
9.htmlReport：我们应该是否写一个HTML报告，默认true， 这个使用场景由LintOptions.getHtmlOutput()控制。
10.ignoreWarings：lint仅仅检查错误，忽略警告。
11.lintConfig：默认配置文件作为备份。
12.noLines：lint在输出错误日志的时候，是否包含行数。默认true。
13.quiet：lint是否应该quiet(安静)。如：报告文件写入路径，不写消息。
14.severityOverrides：An optional map of severity overrides. The map maps from issue id's to the corresponding severity to use, which must be "fatal", "error", "warning", or "ignore".
15.showAll：lint是否包含所有的输出。
16.textOutput：文本输出方式。
17.textReport：是否是文本报告写入，默认false。
18.warningAsErrors：lint是否把警告当做错误来处理。
19.xmlOutput：XML输出方式。
20.xmlReport：XML格式写入报告，默认true。
------------------------------------------------------
LintOptions的方法：
1.check(id)：检查这个id的问题的集合
2.check(ids)：
3.disable(id)：将id添加到不用启动的问题集
4.disable(ids)：
5.enable(id)：将id添加到启动的问题集
6.enable(ids)
7.error(id)：将id添加到错误的问题集
8.error(ids)
9.fatal(id)：将id添加到fatal级别的问题集
10.fatal(ids)
11.ignore(id)：将id添加到ignore级别的问题集
12.ignore(ids)
13.waring(id)：将id添加到waring级别的问题集
14.waring(ids)
lintOptions{}的一般用法：
lintOptions {
    abortOnError false
}
------------------------------------------------------
###productFlavors{}对应的是ProductFlavors
ProductFlavors的属性：
1.applicationId：应用程序ID。
2.applicationIdSuffix：应用程序ID后缀。
3.consumerProguardFiles：混淆规则文件被包含在aar包中。
4.dimension：flavor名称的尺寸。
5.externalNativeBuild：详情见externalNativeBuild{}
6.flavorDeminsion：过时
7.generatedDensities：过时
8.jackOption：jack配置可选项。
9.javaCompileOptions：Java编译配置参数
10.manifestPlaceholders：manifest占位符
11.multiDexEnabled：是否进行dex拆分
12.multiDexKeepFile：文本文件编译进主dex文件中。
13.multiDexKeepProgroud：文本文件作为混淆规则编译进主dex文件中
14.ndk：ndk配置
15.proguardFiles:混淆文件
16.signingConfig：这个flavor的签名配置信息
17.testApplicationId：测试应用ID
18.testFunctionalTest：
19.testHandleProfiling：
20.testInstrumentationRunner：
21.testInstrumentionRunnerArguments：
22.useJack：过时
23.verctorDrawables：生成矢量图支持
24.versionCode：版本号
25.versionName:版本名
26.versionNameSuffix：版本名后缀
27.wearAppUnbundled：是否对嵌入式穿戴app进行拆分模式。如果true，那么这个app将在应用市场被分发为穿戴设备的app。
productFlavor{}的用法：
productFlavors {
        googlePlay {
        }

        xiaomi {
        }
}
//所有打包配置（批量处理打包渠道--> manifestPlaceholders：设置打包渠道）
productFlavors.all {
    //平台id
    flavor -> flavor.manifestPlaceholders = [UMENG_CHANNEL_VALUE: name]
}
------------------------------------------------------
### signingConfig{}
signingConfig{}对应的是SigningConfig
SigningConfig的属性：
1.keyAlias：签名使用key的别名
2.KeyPassword：签名使用的key的密码
3.storeFile：store签名文件
4.storePassword：store签名密码
5.storeType：store签名类型
6.v1SigningEnabled：是否使用jar签名(又名v1签名)。
7.v2SigningEnabled：是否使用apk签名(又名v2签名)。
signingConfig{}的用法：
signingConfigs {
    config {
        keyAlias '...'
        keyPassword '...'
        storeFile file('C:/../Key.jks')
        storePassword '...'
    }
}
------------------------------------------------------
### sourceSets{}
sourceSets{}对应的AndroidSourceSet
AndroidSourceSet的属性：
1.aidl：aidl目录
2.assets：assets目录
3.compileConfiguraName：编译配置资源目录。
4.java：java代码目录（需要编译成.class文件）
5.jni：jni资源目录
6.jniLibs：jni库目录
7.manifest：AndroidManifest.xml资源文件
8.name：source set名称。
9.packageConfigurationName：运行时配置的资源集。
10.providedConfigurationName：仅仅编译时配置的资源集。
11.renderscript：RenderScript脚本资源目录
12.res：Android资源目录
13.resource：java资源被复制到输出到javaresource目录
AndroidSourceSet的方法：
1.setRoot(path)：资源集的根目录，所有的资源都在这个跟目录下。
sourceSets{}的使用：
sourceSets {
    //在main目录中
    main {
        //assets目录设置
        assets.srcDirs = ['assets']
        //jni目录设置
        jni.srcDirs 'src/main/jni'
        //jni库设置
        jniLibs.srcDir 'src/main/jniLibs'
    }
}
------------------------------------------------------
## splits{}
splits{}对应的是Splites
Splits的属性：
1.abi：ABI设置
2.abiFilters：用于多个apk的ABI筛选列表
3.density：密度设置
4.densityFilters：用于多个apk的密度筛选列表
5.language：语言设置。
6.languageFilters：用于多个apk的语言筛选列表
Spiltes对应有三个模块，abi{},density{},language{}
abi{}对应的是AbiSplitOptions
AbiSplitsOptions的属性：
1.applicableFilters：返回此范围的所有适用筛选器的列表。
2.enable：是否在这个范围分裂
3.universalApk：是否创建所有可用的ABIs一个APK。
AbiSplitesOptions的方法：
1.exclude(excludes)：排除一些值。
2.include(include)：包含一些值。
3.reset()：重新设置split配置。
density{}对应的是DensitySplitOptions
DensitySplitOptions的属性：
1.applicableFilters：返回此范围的所有适用筛选器的列表。
2.auto：编译系统是否确定分割“language-*”文件夹中的资源。
3.compatibleScreen：兼容屏幕列表
4.enable：是否拆分
DensitySplitOptions的方法：
1.exclude(exclude):排除一些值
2.include(include):包含一些值
3.reset()：重新设置split配置。
language{}对应的是LanguageSplitOptions
LanguageSplitOptions的属性：
1.enable：如果true，就是拆分language
LanguageSplitOptions的方法：
1.include(include)：包含一个模型。
splits{}的用法：
splits {
    density {
        enable true
        exclude 'ldpi', 'mdpi'
        compatibleScreens 'normal', 'large', 'xlarge'
    }
}
生成结果：
app-hdpi-release.apk
app-universal-release.apk
app-xhdpi-release.apk
app-xxhdpi-release.apk
app-xxxhdpi-release.apk
splits {
    abi {
      enable true
      reset()
      include 'x86', 'armeabi-v7a', 'mips'
      universalApk true
    }
}
这个就是生成不同手机架构的app

------------------------------------------------
testOptions{}
testOptions{}对应的是TestOptions
TestOptions的属性：
1.reportDir：报告目录
2.resultDir：结果目录
3.unitTests：单元测试配置参数
TestOptions包含unitTests{}
unitTests{}对应的是UnitTestOptions
UnitTestOptions的属性：
1.returnDefaultValues：无论unmocked方法从android.jar中抛出异常或是默认值（0或null）。
UnitTestOtions的方法：
all(configClosure)：配置所有单元测试任务。
testOptions{}的使用：
testOptions {
    resultsDir = "$project.buildDir/foo/results"
}