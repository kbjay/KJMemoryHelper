##### 为啥要做这个工具
开发过程中自动检测内存可优化项
1. 每次进版内存不达标耗费研发测试人力
2. 完成优化之后随着版本迭代不断出现新的内存问题

##### 目前已经实现的功能
1. 自动检测ImageView图片尺寸 【done】
2. 自动检测view的bg图片尺寸【done】
3. 自动检测ImageView图片rgb（低端机）【done】
4. 自动检测sp数据 【done】
5. 自动检测rv释放 【done】
6. 自动检测glide缓存（低端机）【done】
7. 自动检测数据结构使用 【todo】
8. 自动检测glide是否复写了onlowMemory以及trimMemory【todo】
9. 自动检测线程（阈值）【todo】


##### 使用方式
1. 导包
   buildscript {
   repositories {
   maven { url 'https://www.jitpack.io' }
   }
   }
   debugImplementation 'com.github.kbjay:KJMemoryHelper:v1.0.0'
2. 使用  
   app.onCreate()方法中调用
   Helper.Companion.getInstance()
   .addMonitor(new SPPutMonitor())
   .addMonitor(new SPGetMonitor())
   .addMonitor(new RVMonitor())
   .addMonitor(new ImageMonitor(PhoneLevel.High))
   .addMonitor(new GlideMonitor(PhoneLevel.High))
   .addMonitor(new ViewBgMonitor())
   .init(this);

##### 实现


##### 效果展示
