package xyz.liut.devtools.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import butterknife.BindView;
import xyz.liut.devtools.R;
import xyz.liut.devtools.base.ToolBarFragment;
import xyz.liut.devtools.service.TopActivityAService;
import xyz.liut.devtools.utils.Utils;

/**
 * LittleToolsFragment
 * <p>
 * Created by liut2 on 2017/8/10.
 */

public class LittleToolsFragment extends ToolBarFragment implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.sw_top_act)
    SwitchCompat swTopAct;

    @Override
    protected int getToolBarTitle() {
        return R.string.little_tools_fragment_title;
    }

    @Override
    protected int getContent() {
        return R.layout.fragment_little_tools;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swTopAct.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (Utils.isAccessibilitySettingsOn(getContext().getApplicationContext(), TopActivityAService.class)) {
            Intent intent = new Intent(getContext(), TopActivityAService.class);
            if (isChecked)
                intent.putExtra(TopActivityAService.IS_OPEN, true);
            else
                intent.putExtra(TopActivityAService.IS_OPEN, false);
            getActivity().startService(intent);
            Toast.makeText(getContext(), getString(R.string.open_acc_service_ok), Toast.LENGTH_SHORT).show();
        } else { // 打开系统无障碍设置界面
            Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(accessibleIntent);
            Toast.makeText(getContext(), getString(R.string.open_acc_service), Toast.LENGTH_SHORT).show();
            buttonView.setChecked(false);
        }
    }
}
