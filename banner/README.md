### [YCBanner](https://github.com/yangchong211/YCBanner)

轮播图，支持多种自定义属性，可以设置轮播红点或者轮播数字，支持设置引导页。

可以根据不同使用场景，可以选择无限循环，静态管理或者动态管理 adapter，还可以设置暂停和开始轮播。

后期添加了 RecyclerView 轮播图，同时自定义多种类型 SnapHelper，卡片滑动流畅，目前已经用于多个正式项目中。

- 1.1 自定义轮播图，可以设置轮播红点或者轮播数字，多种指示器，并且灵活设置位置
- 1.2 支持多种轮播图适配器，无限轮播adapter，静态管理adapter，和动态管理adapter。支持多种场合使用。
- 1.3 支持自定义 hintView，十分灵活，拓展性强
- 1.4 无限循环自动轮播、手指按下暂停轮播、抬起手指开始轮播
- 1.5 优化：在页面onPause中调用停止轮播，在页面onResume中调用开始轮播
- 1.6 支持监听item点击事件，支持轮播图中ViewPager的滑动监听事件
- 1.7 不仅支持轮播图，还支持引导页面，十分方便
- 1.8 后期增加使用recyclerView设置轮播图。已经封装到GalleryRecyclerView中，链式调用十分方便
- 1.9 GalleryRecyclerView轮播图支持设置轮播间隔时间，设置滑动速度，设置缩放比例因子
- 如何引用：直接在项目build文件中添加库即可：compile 'cn.yc:YCBannerLib:1.3.9'

### [MagicViewPager](https://github.com/hongyangAndroid/MagicViewPager)

单页显示3个Item的ViewPager炫酷切换效果，适用于Banner等。