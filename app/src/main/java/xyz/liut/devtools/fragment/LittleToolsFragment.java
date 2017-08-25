package xyz.liut.devtools.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import xyz.liut.devtools.R;
import xyz.liut.devtools.base.ToolBarFragment;
import xyz.liut.devtools.service.TopActivityAService;
import xyz.liut.devtools.utils.Utils;

/**
 * LittleToolsFragment
 * <p>
 * Created by liut2 on 2017/8/10.
 */

public class LittleToolsFragment extends ToolBarFragment {

    @BindView(R.id.ll_top_activity)
    LinearLayout llTopActivity;
    Unbinder unbinder;

    @Override
    protected int getToolBarTitle() {
        return R.string.little_tools_fragment_title;
    }

    @Override
    protected int getContent() {
        return R.layout.fragment_little_tools;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ll_top_activity)
    public void onViewClicked() {
        if (Utils.isAccessibilitySettingsOn(getContext().getApplicationContext(), TopActivityAService.class)) {
            getActivity().startService(new Intent(getContext(), TopActivityAService.class));
            Toast.makeText(getContext(), getString(R.string.open_acc_service_ok), Toast.LENGTH_SHORT).show();
        } else {
            //打开系统无障碍设置界面
            Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(accessibleIntent);
            Toast.makeText(getContext(), getString(R.string.open_acc_service), Toast.LENGTH_SHORT).show();
        }
    }


}
