package xyz.liut.devtools.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.liut.devtools.R;
import xyz.liut.devtools.ToolBarFragment;

/**
 * AppInfo
 *
 * Created by liut2 on 2017/8/6.
 */
public class DevInfoFragment extends ToolBarFragment {

    @BindView(R.id.dev_info)
    TextView devInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dev_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        devInfo.setText(Build.BRAND + " " +
                Build.MODEL);
    }

    @Override
    protected String getToolBarTitle() {
        return "DeviceInfo";
    }
}
