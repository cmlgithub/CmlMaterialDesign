package com.cml.cmlmaterialdesign;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaletteActivity2 extends AppCompatActivity {

    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private int[] items = {R.mipmap.beauty,R.mipmap.flower,R.mipmap.scenery};
    private int[] colors = new int[3];
    private TextView mToolbarCenterText;
    private LinearLayout mRootLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette2);
        initDatas();
    }
    public void initDatas() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(1);
        mRootLL = (LinearLayout) findViewById(R.id.rootLL);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarCenterText = (TextView) findViewById(R.id.toolbarCenterText);
        mViewPager.setAdapter(new PicShowAdapter());
        mToolbarCenterText.setText(1+"/"+items.length);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mToolbarCenterText.setText((position+1)+"/"+items.length);
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    window.setStatusBarColor(colors[position]);
                    window.setNavigationBarColor(colors[position]);
                }
                mRootLL.setBackgroundColor(colors[position]);
                mToolbar.setBackgroundColor(colors[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    class PicShowAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(PaletteActivity2.this);
            imageView.setImageResource(items[position]);
            getBitmapColor(imageView,position);
            container.addView(imageView);
            return imageView;
        }

        private void getBitmapColor(ImageView imageView, final int position) {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            Palette.Builder builder = Palette.from(bitmap);
            builder.generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    Palette.Swatch vibrant = palette.getVibrantSwatch();
                    colors[position] = vibrant.getRgb();
                }
            });
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /** * 对颜色进行加深处理 * * @return */
    private int deepenColor(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
