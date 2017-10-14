package xyz.liut.devtools;

import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;

/**
 * DevTools
 * </p>
 * Created by liut on 2017/10/14.
 */
public class MyWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.colorPrimary)
                .page(new TitlePage(R.mipmap.ic_launcher,
                        "Title")
                )
                .page(new BasicPage(R.mipmap.ic_launcher,
                        "操",
                        "我特么需要权限！！！")
                        .background(R.color.colorAccent)
                )
//                .page(new ParallaxPage(R.layout.fragment_dev_info,
//                        "操",
//                        "我特么需要权限！！！")
//                )
//                .page(new FragmentWelcomePage() {
//                    @Override
//                    protected Fragment fragment() {
//                        return new DevInfoFragment();
//                    }
//                })
                .swipeToDismiss(true)
                .canSkip(true)
                .build();
    }
}
