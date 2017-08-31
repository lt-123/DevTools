package xyz.liut.devtools.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;

import butterknife.BindView;
import xyz.liut.devtools.R;
import xyz.liut.devtools.base.ToolBarFragment;

/**
 * AppInfo
 *
 * Created by liut2 on 2017/8/6.
 */
public class DevInfoFragment extends ToolBarFragment {

    private static final String TAG = "DevInfoFragment";

    @BindView(R.id.dev_info)
    TextView devInfo;

    @Override
    protected int getToolBarTitle() {
        return R.string.device_info_fragment_title;
    }

    @Override
    protected int getContent() {
        return R.layout.fragment_dev_info;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Map<String, String> map = new HashMap<>();

        StringBuilder builder = new StringBuilder();

        try {
            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
//                map.put(field.getName(), field.get(null).toString());

                builder.append(field.getName()).append(": ").append(field.get(null).toString()).append("\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        devInfo.setText("\n");
        devInfo.append(builder);
    }
}
