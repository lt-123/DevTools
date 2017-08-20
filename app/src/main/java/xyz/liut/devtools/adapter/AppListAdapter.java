package xyz.liut.devtools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.liut.devtools.R;

/**
 * app list
 * <p>
 * Created by liut on 17-8-20.
 */

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.Holder> {

    public AppListAdapter(Context context) {
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_main, parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
//        holder.text.setText("======" + position);
    }

    @Override
    public int getItemCount() {
        return 130;
    }

    static class Holder extends RecyclerView.ViewHolder {

//        @BindView(R.id.tv_test)
//        TextView text;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
