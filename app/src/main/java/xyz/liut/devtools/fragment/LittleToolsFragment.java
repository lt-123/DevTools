package xyz.liut.devtools.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.liut.devtools.R;
import xyz.liut.devtools.base.ToolBarFragment;

/**
 * LittleToolsFragment
 * <p>
 * Created by liut2 on 2017/8/10.
 */

public class LittleToolsFragment extends ToolBarFragment {

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
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
