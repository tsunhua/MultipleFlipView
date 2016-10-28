# MultipleFlipView

### 效果

![](https://github.com/LinLshare/MultipleFlipView/blob/master/img/demo.gif?raw=true)

### 配置

```
//todo
```

### 使用

1. 在布局文件中引入RandomFlipLayout

 ```
<io.github.linlshare.MultipleFlipViewLib.RandomFlipLayout
   android:id="@+id/rflayout"
   android:layout_width="match_parent"
   android:layout_height="match_parent"/>
 ```

2. 配置RandomFlipLayout

 ```
   randomFlipLayout.setupWithImage(
          R.drawable.android_logo,
          R.drawable.login_cell_1, R.drawable.login_cell_1,
          R.drawable.login_cell_3, R.drawable.login_cell_4,
          R.drawable.login_cell_5, R.drawable.login_cell_6,
          R.drawable.login_cell_7, R.drawable.login_cell_8,
          R.drawable.login_cell_9, R.drawable.login_cell_10,
          R.drawable.login_cell_11, R.drawable.login_cell_12,
          R.drawable.login_cell_13, R.drawable.login_cell_14,
          R.drawable.login_cell_15, R.drawable.login_cell_16,
          R.drawable.login_cell_17, R.drawable.login_cell_18
  );
 ```

3. 开始RandomFlip

 ```
  randomFlipLayout.startRandomFlip();
 ```
