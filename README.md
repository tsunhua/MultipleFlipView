# MultipleFlipView

效果
--------

![](https://github.com/LinLshare/MultipleFlipView/blob/master/img/demo.gif?raw=true)

配置
--------

```
//todo
```

使用
--------

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
 
License
--------

    Copyright 2016 Lshare

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
