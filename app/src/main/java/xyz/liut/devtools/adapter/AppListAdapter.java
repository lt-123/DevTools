package xyz.liut.devtools.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.liut.devtools.R;

/**
 * app list
 * <p>
 * Created by liut on 17-8-20.
 */

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.Holder> {

    private static final String TAG = "AppListAdapter";

    private List<ApplicationInfo> applicationInfos;
    private List<PackageInfo> packageInfos;

    public AppListAdapter(Context context) {
        initAppData(context);
    }

    private void initAppData(Context context) {
        PackageManager pm = context.getPackageManager();
        applicationInfos = pm.getInstalledApplications(0);
        packageInfos = pm.getInstalledPackages(0);

        Log.d(TAG, "initAppData: >> " + applicationInfos);
        Log.d(TAG, "initAppData: >> " + packageInfos);


    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_main, parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        StringBuilder builder2 = new StringBuilder();

        try {
            Field[] fields = PackageInfo.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Log.d(TAG, "---2 >> " + field.getName() + ": " + field.get(packageInfos.get(position)));
                builder2.append(field.getName()).append(": ").append(field.get(packageInfos.get(position))).append("\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "----------------------------------------------------");

        StringBuilder builder = new StringBuilder();

        try {
            Field[] fields = ApplicationInfo.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
//                map.put(field.getName(), field.get(null).toString());

                Log.d(TAG, "--- >> " + field.getName() + ": " + field.get(packageInfos.get(position).applicationInfo));

                builder.append(field.getName()).append(": ").append(field.get(packageInfos.get(position).applicationInfo)).append("\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "----------------------------------------------------");

        holder.text.setText(builder2);
    }

    @Override
    public int getItemCount() {
        return packageInfos.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView text;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
