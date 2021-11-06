package com.onethreefourtwo.gamegagner;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.w3c.dom.Text;

import java.util.List;

public class ViewPagerAdapter_Intro extends PagerAdapter {

    Context mContext;
    List<ScreenItem_Intro> nListScreen;

    public ViewPagerAdapter_Intro(List<ScreenItem_Intro> nListScreen, Context mContext) {
        this.nListScreen = nListScreen;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = layoutInflater.inflate(R.layout.layout_screen_one_intro,null);

        ImageView Image1_Intro = (ImageView)layoutScreen.findViewById(R.id.intro_image);
        TextView Title_Intro = (TextView)layoutScreen.findViewById(R.id.intro_title);
        TextView Description_Intro = (TextView)layoutScreen.findViewById(R.id.intro_description);

        Image1_Intro.setImageResource(nListScreen.get(position).getScreenImg());
        Title_Intro.setText(nListScreen.get(position).getTitle());
        Description_Intro.setText(nListScreen.get(position).getDescription());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return nListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
