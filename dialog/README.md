# Dialog 项目使用说明文档
### 使用步骤

#### 1. 引入第三方库

```groovy
 implementation 'com.liujc.util:jcdialog:1.0.1'
```



#### 2. 初始化 DialogUtils

```java
DialogUtils.init(mContext);
```

### 使用 PopupWindow

```java
private void initPopupWindow(View view){
        // 初始化 PopuWindowView
        final PopuWindowView popuWindowView = new PopuWindowView(this,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 绑定数据和点击事件
        popuWindowView.initPupoData(new TdataListener() {
            @Override
            public void initPupoData(List<PopuBean> lists) {
                for (int i = 0; i < 5; i++) {
                    PopuBean popu = new PopuBean();
                    popu.setTitle("item"+i);
                    popu.setId(i);
                    lists.add(popu);
                }
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position) {
                showToast(popuWindowView.getTitle(position));
                popuWindowView.dismiss();
            }
        });
        // 展示 PopuWindowView
        popuWindowView.showing(view);
    }
```

### 使用 LoadingDialog

#### 1. 横向 

```java
DialogUtils.showLoadingHorizontal(LoadingDialog.this, "加载中...").show();
```

#### 2. 竖向 

```java
DialogUtils.showLoadingVertical(LoadingDialog.this, "加载中...").show();
```

#### 3. 横向灰色

```java
DialogUtils.showLoadingHorizontal(LoadingDialog.this,"加载中...", false).show();
```

#### 4. 竖向灰色

```java
DialogUtils.showLoadingVertical(LoadingDialog.this, "加载中...", false).show();
```

#### 5. MD 风格的横向

```java
DialogUtils.showMdLoadingHorizontal(LoadingDialog.this,"加载中...").show();
```

#### 6. MD 风格的竖向

```java
DialogUtils.showMdLoadingVertical(LoadingDialog.this,"加载中...").show();
```

