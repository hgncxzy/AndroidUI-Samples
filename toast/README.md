## [使用库1](https://github.com/liujinchao/DialogUi)

#### 库名

com.liujc.util:jcdialog:1.0.1

#### 使用方式

1. 添加依赖

   ```groovy
   implementation 'com.liujc.util:jcdialog:1.0.1'
   ```

2. 实例化 DialogUtils

   ```java
   DialogUtils.init(this);
   ```


####  APIs

可以显示顶部、底部、中部、带图片、自定义位置和布局的 Toast。

```java
public static void showToast(CharSequence msg) {
        showToastLong(msg.toString());
    }
    /**
     * 短时间中下位置显示。线程安全，可以在非UI线程调用。
     */
    public static void showToast(final int resId) {
        showToast(ToolUtils.getString(DialogUtils.appContext, resId));
    }

    /**
     * 短时间中下位置显示。
     */
    public static void showToast(final String str) {
        showToast(str, Toast.LENGTH_SHORT, Gravity.BOTTOM);
    }

    /**
     * 长时间中下位置显示。
     */
    public static void showToastLong(final int resId) {
        showToastLong(ToolUtils.getString(DialogUtils.appContext, resId));
    }

    /**
     * 长时间中下位置显示。
     */
    public static void showToastLong(final String str) {
        showToast(str, Toast.LENGTH_LONG, Gravity.BOTTOM);
    }


    /**
     * 短时间居中位置显示。
     */
    public static void showToastCenter(final int resId) {
        showToastCenter(ToolUtils.getString(DialogUtils.appContext, resId));
    }

    /**
     * 短时间居中位置显示。
     */
    public static void showToastCenter(final String str) {
        showToast(str, Toast.LENGTH_SHORT, Gravity.CENTER);
    }

    /**
     * 长时间居中位置显示。
     */
    public static void showToastCenterLong(final int resId) {
        showToastCenterLong(ToolUtils.getString(DialogUtils.appContext, resId));
    }

    /**
     * 长时间居中位置显示。
     */
    public static void showToastCenterLong(final String str) {
        showToast(str, Toast.LENGTH_LONG, Gravity.CENTER);
    }

    /**
     * 短时间居中位置显示。
     */
    public static void showToastTop(final int resId) {
        showToastTop(ToolUtils.getString(DialogUtils.appContext, resId));
    }

    /**
     * 短时间居中位置显示。
     */
    public static void showToastTop(final String str) {
        showToast(str, Toast.LENGTH_SHORT, Gravity.TOP);
    }

    /**
     * 长时间居中位置显示。
     */
    public static void showToastTopLong(final int resId) {
        showToastTopLong(ToolUtils.getString(DialogUtils.appContext, resId));
    }

    /**
     * 长时间居中位置显示。
     */
    public static void showToastTopLong(final String str) {
        showToast(str, Toast.LENGTH_LONG, Gravity.TOP);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(String message, int duration) {
        showToast(message, duration,Gravity.BOTTOM);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param strResId
     * @param duration
     */
    public static void show(int strResId, int duration) {
        showToast(ToolUtils.getString(DialogUtils.appContext, strResId), duration,Gravity.BOTTOM);
    }

    /**
     * 显示有image的toast
     *
     * @param tvStr
     * @param imageResource
     */
    public static void showToastWithImg(final String tvStr, final int imageResource) {
        if (toast2 == null) {
            toast2 = new Toast(DialogUtils.appContext);
        }
        View view = LayoutInflater.from(DialogUtils.appContext).inflate(R.layout.toast_custom, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        ImageView iv = (ImageView) view.findViewById(R.id.toast_custom_iv);
        if (imageResource > 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(imageResource);
        } else {
            iv.setVisibility(View.GONE);
        }
        toast2.setView(view);
        toast2.setGravity(Gravity.CENTER, 0, 0);
        mToast = toast2;
        mToast.show();
    }

    public static void showToastLayout(final int toastLayout){
        showToastLayout(toastLayout,Gravity.CENTER);
    }
    /**
     * @param toastLayout  自定义布局
     * @param gravity   显示位置
     */
    public static void showToastLayout(final int toastLayout, int gravity) {
        if (toast2 == null) {
            toast2 = new Toast(DialogUtils.appContext);
        }
        View view = LayoutInflater.from(DialogUtils.appContext).inflate(toastLayout, null);
        toast2.setView(view);
        toast2.setGravity(gravity, 0, 0);
        mToast = toast2;
        mToast.show();
    }

    /**
     * 对toast的简易封装。线程不安全，不可以在非UI线程调用。
     */
    private static void showToast(String str, int showTime, int gravity) {
        if (DialogUtils.appContext == null) {
            throw new RuntimeException("DialogUIUtils not initialized!");
        }
        int layoutId = R.layout.dialogui_toast;
        if (gravity == Gravity.TOP) {
            if (mToastTop == null) {
                mToastTop = Toast.makeText(DialogUtils.appContext, str, showTime);
                LayoutInflater inflate = (LayoutInflater)
                        DialogUtils.appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflate.inflate(layoutId, null);
                mToastTop.setView(view);
                mToastTop.setGravity(gravity, 0, DialogUtils.appContext.getResources().getDimensionPixelSize(R.dimen.dialogui_toast_margin));
            }
            mToast = mToastTop;
            mToast.setText(str);
            mToast.show();
        } else if (gravity == Gravity.CENTER) {
            if (mToastCenter == null) {
                mToastCenter = Toast.makeText(DialogUtils.appContext, str, showTime);
                LayoutInflater inflate = (LayoutInflater)
                        DialogUtils.appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflate.inflate(layoutId, null);
                mToastCenter.setView(view);
                mToastCenter.setGravity(gravity, 0, 0);
            }
            mToast = mToastCenter;
            mToast.setText(str);
            mToast.show();
        } else if (gravity == Gravity.BOTTOM) {
            if (mToastBottom == null) {
                mToastBottom = Toast.makeText(DialogUtils.appContext, str, showTime);
                LayoutInflater inflate = (LayoutInflater)
                        DialogUtils.appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflate.inflate(layoutId, null);
                mToastBottom.setView(view);
                mToastBottom.setGravity(gravity, 0, DialogUtils.appContext.getResources().getDimensionPixelSize(R.dimen.dialogui_toast_margin));
            }
            mToast = mToastBottom;
            mToast.setText(str);
            mToast.show();
        }

    }
```

#### 调用方式

```java
ToastUitl.showXXX
```

## [使用库2](https://github.com/the-pig-of-jungle/smart-show)

## [使用库3](https://github.com/goweii/AnyLayer)

