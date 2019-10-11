package com.example.responsibilityhome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class TemporaryFragment extends Fragment {

    private static final String TAG = "임의";

    private PageViewModel pageViewModel;
    private ArrayList<HashMap<String, String>> stringList;
    ListView list;

    /**
     * @return A new instance of fragment SpeedDialFragment.
     */
    public static TemporaryFragment newInstance() {
        return new TemporaryFragment();
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
        View root = inflater.inflate(R.layout.fragment_temporary, container, false);
        list = (ListView) root.findViewById(R.id.list);

        for(int index=1;index<=10;index++) {
            String number = "Item"+String.valueOf(index);
            String title = String.valueOf(index);
            String score;
            if(index%2!=0)
                score = "-" + index;
            else
                score = ""+index;


            HashMap<String, String> posts2 = new HashMap<String, String>();

            posts2.put("a", number);
            posts2.put("b", title);
            posts2.put("c", score);
            stringList.add(posts2);
        }

        ListAdapter adapter = new SimpleAdapter(
                getActivity(), stringList, R.layout.item_detail_credit_check,
                new String[]{"a", "b", "c"},
                new int[]{R.id.content, R.id.date, R.id.score}
        );


        // 6. ListView 객체에 adapter 객체를 연결합니다.
        list.setAdapter(adapter);
        return root;
    }
}