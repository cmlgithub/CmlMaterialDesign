package com.cml.cmlmaterialdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *  scroll|enterAlways|exitUntilCollapsed|enterAlwaysCollapsed|snap
 *
 *  scroll 上滑toolbar滚出屏幕 ,下滑scrollView到顶之后toolbar才出来
 *
 *  enterAlways|exitUntilCollapsed|enterAlwaysCollapsed|snap 都需要搭配scroll使用否则无效果
 *
 *  (以下效果均是搭配scroll之后)
 *
 *      enterAlways             toolbar 上滑出屏幕  下拉toolbar先进屏幕然后scrollView再滚动
 *
 *      exitUntilCollapsed      没效果
 *
 *      enterAlwaysCollapsed     toolbar 上滑出屏幕  下拉先滚动scrollView到头后再滚动toolbar进来屏幕
 *
 *      snap                    效果同 enterAlwaysCollapsed
 *
 *
 *
 */
public class AppBarLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout2);
    }
}
