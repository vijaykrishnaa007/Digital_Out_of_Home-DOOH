package ad.vishnu.com.ad_vishnu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;


public class SingleVideoActivity extends FragmentActivity implements videofr.finish
{
    int i=0;
    static ArrayList<String > arrayList;
    static ArrayList<String > arrayListtype;
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_single_video);
        String url="https://firebasestorage.googleapis.com/v0/b/adstartup.appspot.com/o/Pazhamudir%20Nilayam%20%26%20Supermarket%2C%20Coimbatore_Trim.mp4?alt=media&token=86780519-0880-40af-a91b-46dd7d655f38";
        arrayList=new ArrayList<>();
        arrayListtype=new ArrayList<>();
        arrayListtype.add("1");
        arrayList.add(url);
        FirebaseDatabase.getInstance().getReference().child("LINKS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> arrayList1=new ArrayList<>();
                ArrayList<String> arrayList2=new ArrayList<>();
                for(DataSnapshot child:dataSnapshot.getChildren())
                {
                    arrayList1.add(child.child("url").getValue().toString());
                    arrayList2.add(child.child("type").getValue().toString());
                }
                arrayList=arrayList1;
                arrayListtype=arrayList2;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final ImageView one=findViewById(R.id.one);
        SharedPreferences sharedPref = getSharedPreferences("secret",Context.MODE_PRIVATE);
        String qw=sharedPref.getString("key","sfg");
        FirebaseDatabase.getInstance().getReference().child("USERS").child(qw).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()==null)
                    return;
                Glide.with(SingleVideoActivity.this).load(dataSnapshot.getValue().toString()).into(one);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        PictureFragment pictureFragment=new PictureFragment();
        pictureFragment.url="https://firebasestorage.googleapis.com/v0/b/adstartup.appspot.com/o/mcdonals.png?alt=media&token=12c2d60e-eaf1-47d2-9395-d2a6bda1f41a";
        pictureFragment.a=this;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.two, pictureFragment)
                .commit();
    }

    @Override
    public void onfinish()
    {
        i++;
        i%=arrayList.size();
        if("1".equals(arrayListtype.get(i)))
        {
            videofr videofr = new videofr();
            videofr.a = this;
            videofr.url = arrayList.get(i);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.two, videofr)
                    .commit();
        }
        else
        {
            PictureFragment pictureFragment=new PictureFragment();
            pictureFragment.url=arrayList.get(i);
            pictureFragment.a=this;
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.two, pictureFragment)
                    .commit();
        }
    }
}
