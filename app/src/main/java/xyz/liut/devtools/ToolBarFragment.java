package xyz.liut.devtools;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 带有ToolBar
 * Created by liut on 17-8-6.
 */

public class ToolBarFragment extends Fragment {

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        MainActivity activity = (MainActivity) getActivity();
        activity.setToolBar(toolbar);

    }
}
