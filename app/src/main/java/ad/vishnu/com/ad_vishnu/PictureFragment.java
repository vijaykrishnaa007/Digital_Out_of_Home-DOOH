package ad.vishnu.com.ad_vishnu;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PictureFragment extends Fragment
{
    String url;
    videofr.finish a;
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        ImageView imageView=view.findViewById(R.id.image);
        Glide.with(container.getContext()).load(url).into(imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                a.onfinish();
            }
        },5000);
        return view;
    }
}
