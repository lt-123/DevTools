package xyz.liut.devtools.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import xyz.liut.devtools.R;
import xyz.liut.devtools.adapter.AppListAdapter;
import xyz.liut.devtools.base.BaseFragment;

/**
 * app List
 * Created by liut on 17-8-20.
 */

public class AppListFragment extends BaseFragment implements Runnable {

    private static final String TAG = "AppListFragment";

    @BindView(R.id.rv_main)
    RecyclerView recyclerView;

    private List<PackageInfo> packageInfos;

    @Override
    protected int getContent() {
        return R.layout.fragment_app_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new AppListAdapter(getContext()));
        new Thread(new AppListFragment()).start();
    }

    @Override
    public void run() {
//        packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0
        PackageManager pm = getContext().getPackageManager();
        packageInfos = pm.getInstalledPackages(0);
        recyclerView.setAdapter(new AppListAdapter(getContext(), packageInfos, AppListAdapter.APP_USER));

    }
}
