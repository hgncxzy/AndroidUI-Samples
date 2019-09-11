SpannableString其实和String一样，都是一种字符串类型，SpannableString可以直接作为TextView的显示文本，不同的是SpannableString可以通过使用其方法setSpan方法实现字符串各种形式风格的显示,重要的是可以指定设置的区间，也就是为字符串指定下标区间内的子字符串设置格式。

setSpan(Object what, int start, int end, int  flags)方法需要用户输入四个参数，`what`表示设置的格式是什么，可以是前景色、背景色也可以是可点击的文本等等，`start`表示需要设置格式的子字符串的起始下标，同理`end`表示终了下标，`flags`属性就有意思了，共有四种属性：

`Spanned.SPAN_INCLUSIVE_EXCLUSIVE`  从起始下标到终了下标，包括起始下标
`Spanned.SPAN_INCLUSIVE_INCLUSIVE`  从起始下标到终了下标，同时包括起始下标和终了下标
`Spanned.SPAN_EXCLUSIVE_EXCLUSIVE`  从起始下标到终了下标，但都不包括起始下标和终了下标
`Spanned.SPAN_EXCLUSIVE_INCLUSIVE`  从起始下标到终了下标，包括终了下标

SpannableString的setSpan()方法可以同时使用多个，实现多种效果叠加。

常见的效果有：

`ForegroundColorSpan`，为文本设置前景色，效果和 TextView 的 setTextColor() 类似；

`BackgroundColorSpan`，为文本设置背景色，效果和 TextView 的 setBackground() 类似；

`RelativeSizeSpan`，设置文字相对大小，在 TextView 原有的文字大小的基础上，相对设置文字大小；

`StrikethroughSpan`，为文本设置中划线，也就是常说的删除线；

`UnderlineSpan`，为文本设置下划线；

`SuperscriptSpan`，设置上标；

`SubscriptSpan`，设置下标，功能与设置上标类似；

`StyleSpan`，为文字设置风格（粗体、斜体），和 TextView 属性 textStyle 类似；

`ImageSpan`，设置文本图片；

`ClickableSpan`，设置可点击的文本，设置这个属性的文本可以相应用户点击事件，至于点击事件用户可以自定义，就像效果图显示一样，用户可以实现点击跳转页面的效果；

`URLSpan`，设置超链接文本，其实聪明的小伙帮在讲到`ClickableSpan`的时候就能实现超链接文本的效果了，重写 onClick 点击事件就行，也确实看了`URLSpan`的源码，`URLSpan`就是继承自`ClickableSpan`，也和想象中一样，就是重写了父类的onClick事件，用系统自带浏览器打开链接；

`MaskFilterSpan`可以实现模糊和浮雕效果；

`RasterizerSpan`可以实现光栅效果；

##### SpannableStringBuilder

应该有不少开发的小伙伴知道 StringBuilder，可以使用 append() 方法实现字符串拼接，非常方便。同样，SpannableString 中也有 SpannableStringBuilder，顾名思义，就是实现对，SpannableString 的一个拼接效果，同样是append() 方法，可以实现各种风格效果的 SpannableString 拼接，非常实用。

参考 http://www.jianshu.com/p/84067ad289d2

