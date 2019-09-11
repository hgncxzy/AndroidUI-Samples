package com.example.spannablestring

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spanned
import android.graphics.Color.parseColor
import android.text.style.ForegroundColorSpan
import android.text.SpannableString
import kotlinx.android.synthetic.main.activity_main.*
import android.text.style.BackgroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import android.text.style.SuperscriptSpan
import android.text.style.SubscriptSpan
import android.graphics.Typeface
import android.text.style.StyleSpan
import android.text.style.ImageSpan
import android.content.Intent
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.method.LinkMovementMethod
import android.view.View
import android.text.style.URLSpan


/**
 * 富文本的用法。
 * 参考 https://www.jianshu.com/p/84067ad289d2
 * @author xzy
 */
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handle()
    }

    /**
     *
     * 参考 https://www.jianshu.com/p/84067ad289d2
     * */
    private fun handle() {
        // 设置文字的前景色为淡蓝色
        ForegroundColorSpan.setOnClickListener {
            val spannableString = SpannableString("设置文字的前景色为淡蓝色")
            val colorSpan = ForegroundColorSpan(parseColor("#0099EE"))
            spannableString.setSpan(colorSpan, 9, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.text = spannableString
        }

        // 设置文字的背景色为淡绿色
        BackgroundColorSpan.setOnClickListener {
            val spannableString = SpannableString("设置文字的背景色为淡绿色")
            val colorSpan = BackgroundColorSpan(parseColor("#AC00FF30"))
            spannableString.setSpan(colorSpan, 9, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.text = spannableString
        }

        // 设置文字相对大小，在TextView原有的文字大小的基础上，相对设置文字大小
        RelativeSizeSpan.setOnClickListener {
            val spannableString = SpannableString("万丈高楼平地起")

            val sizeSpan01 = RelativeSizeSpan(1.2f)
            val sizeSpan02 = RelativeSizeSpan(1.4f)
            val sizeSpan03 = RelativeSizeSpan(1.6f)
            val sizeSpan04 = RelativeSizeSpan(1.8f)
            val sizeSpan05 = RelativeSizeSpan(1.6f)
            val sizeSpan06 = RelativeSizeSpan(1.4f)
            val sizeSpan07 = RelativeSizeSpan(1.2f)

            spannableString.setSpan(sizeSpan01, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(sizeSpan02, 1, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(sizeSpan03, 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(sizeSpan04, 3, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(sizeSpan05, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(sizeSpan06, 5, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(sizeSpan07, 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.text = spannableString
        }

        // 为文本设置中划线，也就是常说的删除线
        StrikethroughSpan.setOnClickListener {
            val spannableString = SpannableString("为文字设置删除线")
            val strikethroughSpan = StrikethroughSpan()
            spannableString.setSpan(strikethroughSpan, 5, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.text = spannableString
        }

        // 为文本设置下划线
        UnderlineSpan.setOnClickListener {
            val spannableString = SpannableString("为文字设置下划线")
            val underlineSpan = UnderlineSpan()
            spannableString.setSpan(underlineSpan, 5, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.text = spannableString
        }

        // 为文字设置上标
        SuperscriptSpan.setOnClickListener {
            val spannableString = SpannableString("为文字设置上标")
            val superscriptSpan = SuperscriptSpan()
            spannableString.setSpan(superscriptSpan, 5, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.text = spannableString
        }

        // 为文字设置下标
        SubscriptSpan.setOnClickListener {
            val spannableString = SpannableString("为文字设置下标")
            val subscriptSpan = SubscriptSpan()
            spannableString.setSpan(subscriptSpan, 5, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.text = spannableString
        }

        // 为文字设置粗体、斜体风格
        StyleSpan.setOnClickListener {
            val spannableString = SpannableString("为文字设置粗体、斜体风格")
            val styleSpanB = StyleSpan(Typeface.BOLD)
            val styleSpanI = StyleSpan(Typeface.ITALIC)
            spannableString.setSpan(styleSpanB, 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(styleSpanI, 8, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.highlightColor = parseColor("#36969696")
            show.text = spannableString
        }

        // 在文本中添加表情
        ImageSpan.setOnClickListener {
            val spannableString = SpannableString("在文本中添加表情（表情）")
            val drawable = resources.getDrawable(R.mipmap.test)
            drawable.setBounds(0, 0, 42, 42)
            val imageSpan = ImageSpan(drawable)
            spannableString.setSpan(imageSpan, 6, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.text = spannableString
        }

        // 设置可点击的文本
        ClickableSpan.setOnClickListener {
            val spannableString = SpannableString("为文字设置点击事件")
            val clickableSpan = MyClickableSpan(this@MainActivity, "http://www.jianshu.com/users/dbae9ac95c78")
            spannableString.setSpan(clickableSpan, 5, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.movementMethod = LinkMovementMethod.getInstance()
            show.highlightColor = parseColor("#36969696")
            show.text = spannableString

        }

        // 为文字设置超链接
        URLSpan.setOnClickListener {
            val spannableString = SpannableString("为文字设置超链接")
            val urlSpan = URLSpan("http://www.jianshu.com/users/dbae9ac95c78")
            spannableString.setSpan(urlSpan, 5, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            show.movementMethod = LinkMovementMethod.getInstance()
            show.highlightColor = parseColor("#36969696")
            show.text = spannableString
        }


    }
}

/***************************************************************/

class MyClickableSpan(context: Context, private val content: String) : ClickableSpan() {
    val ctx: Context = context
    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = false
    }

    override fun onClick(widget: View) {
        val intent = Intent(ctx, OtherActivity::class.java)
        val bundle = Bundle()
        bundle.putString("content", content)
        intent.putExtra("bundle", bundle)
        ctx.startActivity(intent)
    }
}

