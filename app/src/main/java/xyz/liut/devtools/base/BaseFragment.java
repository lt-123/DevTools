package xyz.liut.devtools.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * baseFrament
 * <p>
 * Created by liut2 on 2017/8/10.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContent(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    /**
     * @return layout
     */
    protected abstract int getContent();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        this.context = null;
    }

}
