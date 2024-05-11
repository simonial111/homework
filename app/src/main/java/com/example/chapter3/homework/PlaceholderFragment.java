package com.example.chapter3.homework;


import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    private LottieAnimationView animationView;

    private ListView listView;

    private static final String EXTRA_EXIT_ANIM = "extra_exit_anim";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        animationView = view.findViewById(R.id.animation_view);
        listView = view.findViewById(R.id.list_view);

        String[] data={"","芒果","石榴","葡萄"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
//                Intent intent = new Intent(getActivity(), getActivity().getClass());
//                intent.putExtra(EXTRA_EXIT_ANIM, R.anim.fade_out);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in, 0);
//                animationView.setVisibility(View.INVISIBLE);
//                listView.setVisibility(View.VISIBLE);
//                getFragmentManager().beginTransaction()
//                        .setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
//                        .replace(R.id.fragment_container, new HelloFragment())
//                        .addToBackStack(null).commit();
//                animationView.setVisibility(View.INVISIBLE);

                System.out.println("postDelayed1111");
                Intent intent = new Intent(getActivity(), getActivity().getClass());
                intent.putExtra(EXTRA_EXIT_ANIM, R.anim.fade_out);
                boolean is_transitioned = getActivity().getIntent().getBooleanExtra("transition_data",false);
                System.out.println("bool:"+is_transitioned);
                if (!is_transitioned){
                    intent.putExtra("transition_data", true);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fade_in, 0);
                }
            }
        }, 3000);

        boolean is_transitioned = getActivity().getIntent().getBooleanExtra("transition_data",false);
        if (is_transitioned){
            listView.setVisibility(View.VISIBLE);
            animationView.setVisibility(View.INVISIBLE);
        }

    }
}

