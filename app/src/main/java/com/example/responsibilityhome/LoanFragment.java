package com.example.responsibilityhome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.HashMap;

public class LoanFragment extends Fragment {

    private static final String TAG = "대출";

    private PageViewModel pageViewModel;
    private ArrayList<HashMap<String, String>> stringList;
    ListView list;


    /**
     * @return A new instance of fragment SpeedDialFragment.
     */
    public static LoanFragment newInstance() {
        return new LoanFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        pageViewModel.setIndex(TAG);

        stringList = new ArrayList<HashMap<String, String>>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_loan, container, false);
        list = (ListView) root.findViewById(R.id.list);
        int minusSum=0;

        for(int index=1;index<=10;index++) {
            String content = "Item"+String.valueOf(index);
            String date = "2019/10/"+String.valueOf(index);
            String score= "-" + index;

            HashMap<String, String> posts2 = new HashMap<String, String>();

            posts2.put("content", content);
            posts2.put("date", date);
            posts2.put("score", score);
            stringList.add(posts2);
            minusSum+=index;
        }

        String content = "감점점수 합";
        String date = "2019/10/11";
        String score= "-" + String.valueOf(minusSum);

        HashMap<String, String> posts2 = new HashMap<String, String>();

        posts2.put("content", content);
        posts2.put("date", date);
        posts2.put("score", score);
        stringList.add(posts2);

        ListAdapter adapter = new SimpleAdapter(
                getActivity(), stringList, R.layout.item_detail_credit_check,
                new String[]{"content", "date", "score"},
                new int[]{R.id.content, R.id.date, R.id.score}
        );


        // 6. ListView 객체에 adapter 객체를 연결합니다.
        list.setAdapter(adapter);
        return root;
    }
}