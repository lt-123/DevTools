package xyz.liut.devtools.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    public static final int APP_USER = 0;
    public static final int APP_SYSTEM = 1;

    private final Context context;
    private List<PackageInfo> packageInfos;

    /**
     * @param context
     * @param packageInfos
     * @param type         {@link #APP_USER} or {@link #APP_SYSTEM}
     */
    public AppListAdapter(Context context, List<PackageInfo> packageInfos, int type) {
        this.context = context;
        this.packageInfos = packageInfos;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_main, parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        PackageInfo info = packageInfos.get(position);
        holder.imgIcon.setImageDrawable(info.applicationInfo.loadIcon(context.getPackageManager()));
        String title = info.applicationInfo.loadLabel(context.getPackageManager())
                + " [" + info.packageName + "]";
        holder.tvAppTitle.setText(title);
        holder.tvVersion.setText(info.versionName + " [" + info.versionCode + "]");
    }

    @Override
    public int getItemCount() {
        return packageInfos.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.tv_app_title)
        TextView tvAppTitle;
        @BindView(R.id.tv_version)
        TextView tvVersion;
        @BindView(R.id.text)
        TextView text;
        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
