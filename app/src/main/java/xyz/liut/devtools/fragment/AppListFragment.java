package xyz.liut.devtools.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import xyz.liut.devtools.R;
import xyz.liut.devtools.adapter.AppListAdapter;
import xyz.liut.devtools.base.BaseFragment;

/**
 * app List
 * Created by liut on 17-8-20.
 */

public class AppListFragment extends BaseFragment {

    @BindView(R.id.rv_main)
    RecyclerView recyclerView;

    @Override
    protected int getContent() {
        return R.layout.fragment_app_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AppListAdapter(getContext()));
    }

}
