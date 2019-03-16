package ad.vishnu.com.ad_vishnu;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

public class App extends Application {

    private HttpProxyCacheServer proxy=null;

    public static HttpProxyCacheServer getProxy(Context context) {
        App app = (App) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        long a=3;
        long b=1024;
        a=a*b*b*b;
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(a)       // 1 Gb for cache
                .build();
    }
}