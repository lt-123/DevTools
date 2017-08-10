package xyz.liut.devtools.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import xyz.liut.devtools.R;
import xyz.liut.devtools.base.ToolBarFragment;

/**
 * AppInfo
 *
 * Created by liut2 on 2017/8/6.
 */
public class DevInfoFragment extends ToolBarFragment {

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
        devInfo.setText(Build.BRAND + " " +
                Build.MODEL);
    }
}
