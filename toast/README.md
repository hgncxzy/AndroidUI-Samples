## [XToast](https://github.com/hgncxzy/XToast)

自己写的 Toast。

Android Toast 的各种实现，包括默认、自定义位置与背景色、带图片、自定义纯文本、动画等样式的封装。

## [smart-show](https://github.com/the-pig-of-jungle/smart-show)

Toast & Snackbar & TopBar & Dialog

## [AnyLayer](https://github.com/goweii/AnyLayer)

Android稳定高效的浮层创建管理框架。

- 链式调用
- 三大效果
  - Dialog 效果（Dialog/Popup/BottomSheet 等效果）
    - 占用区域不会超过当前Activity避免导航栏遮挡
    - 支持自由控制浮层大小和显示位置
    - 支持自定义数据绑定
    - 支持自定义进出场动画
    - 支持自定义背景颜色/图片/高斯模糊
    - 支持Popup效果时跟随目标View移动
    - 支持在Activity的onCreate生命周期弹出
  - Toast效果（依附Activity非全局）
    - 支持自定义图标和文字
    - 支持自定义显示时长
    - 待完善
  - Guide效果（引导层）

## [ToastUtils](https://github.com/getActivity/ToastUtils)

#### 框架亮点

- 无需权限：不管有没有授予通知栏权限都不影响吐司的弹出

- 功能强大：不分主次线程都可以弹出Toast，自动区分资源id和int类型

- 使用简单：只需传入文本，会自动根据文本长度决定吐司显示的时长

- 性能最佳：单例吐司，整个Toast只有一个TextView，并且通过代码创建

- 体验最优：限制Toast短时间内弹出的次数，避免频繁弹出造成不良的用户体验

- 支持多种样式：默认为黑色样式，夜间模式可使用白色样式，还有仿QQ吐司样式

- 支持自定义样式：吐司（背景、圆角、重心、偏移），文字（大小、颜色、边距）

- 支持自定义扩展：支持获取ToastUtils中的Toast对象，支持重新自定义Toast布局

- 支持全局配置样式：可以在Application中初始化Toast样式，达到一劳永逸的效果

- 框架兼容性良好：本框架不依赖任何第三方库，支持Eclipse和Studio的集成使用

  



