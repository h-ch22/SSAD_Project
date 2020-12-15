package kr.ac.jbnu.se.ssad_group6.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kr.ac.jbnu.se.ssad_group6.Item;
import kr.ac.jbnu.se.ssad_group6.R;

public class activity_userList extends Activity {
    private ListView mlistView;
    private ItemAdapter mAdapter;
    private List<Item> visited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


        visited = new ArrayList<>();

        Date currentTime = Calendar.getInstance().getTime();

        Item test = new Item("오늘과 오늘 사이", currentTime);
        Item test2 = new Item("공과대학 5호관", currentTime);
        visited.add(test);
        visited.add(test2);

        mlistView = (ListView) findViewById(R.id.todoListView);
        mAdapter = new ItemAdapter(visited);
        mlistView.setAdapter(mAdapter);

    }
}