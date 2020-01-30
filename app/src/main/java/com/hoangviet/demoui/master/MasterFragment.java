package com.hoangviet.demoui.master;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoangviet.demoui.R;

import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment implements LifecycleOwner, MasterAdapter.OnClickItemListener {
    private MasterViewModel mViewModel;
    private MasterAdapter mAdapter;


    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(MasterViewModel.class);
        mViewModel.getData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                mAdapter.addData(strings);
            }
        });
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMaster);
        mAdapter = new MasterAdapter();
        mAdapter.setOnClickItemListener(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), VERTICAL);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onClickItem(String itemName) {
        mViewModel.setSelectedItem(itemName);
    }
}
