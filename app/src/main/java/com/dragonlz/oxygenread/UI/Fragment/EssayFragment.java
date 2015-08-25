package com.dragonlz.oxygenread.UI.Fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.dragonlz.oxygenread.R;
import com.dragonlz.oxygenread.UI.Adapter.EssayAdapter;
import com.dragonlz.oxygenread.UI.Model.Essay;
import com.dragonlz.oxygenread.UI.Utils.HttoCallbackListener;
import com.dragonlz.oxygenread.UI.Utils.HttpUtil;
import com.dragonlz.oxygenread.UI.Utils.LogUtils;
import com.dragonlz.oxygenread.UI.confilg.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class EssayFragment extends Fragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener, View.OnClickListener {

    private List<Essay> essaysLists = new ArrayList<Essay>();
    private boolean scrollFlag = false;
    private int lastVisibleItemPosition;
    private ImageButton refresh;
    private ImageView shadow;
    private ListView mListView;
    private EssayAdapter adapter;

    private Object messageHttp;
    private int numbersInOnePage = 0;
    private int page = 1;

    public static final int SHOW_RESPONSE = 0;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    response(msg.obj, 0);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.essay_list, container,false);
        httpRequset(page);
        init(rootView);
        return rootView;
    }

    private void init(View v) {
        mListView = (ListView) v.findViewById(R.id.lv_stories);
        adapter = new EssayAdapter(getActivity(), R.layout.essay_list_item, essaysLists);
        mListView.setAdapter(adapter);
        mListView.setOnScrollListener(this);
        mListView.setOnItemClickListener(this);
        refresh = (ImageButton) v.findViewById(R.id.btn_float);
        refresh.setOnClickListener(this);
        shadow = (ImageView) v.findViewById(R.id.btn_float_shadow);
    }

    private void response(Object msg, int startPosition) {
        try {
            JSONObject jsonObject = new JSONObject((String) msg);
            JSONArray comments = jsonObject.getJSONArray("comments");
            numbersInOnePage = comments.length();
            for (int i = startPosition; i < comments.length() && i < startPosition + 10; i++) {
                JSONObject json = (JSONObject) comments.opt(i);
                String title = json.getString("comment_author");
                String date = json.getString("comment_date");
                String content = json.getString("text_content");
                String email = json.getString("comment_author_email");
                int oo = json.getInt("vote_positive");
                int xx = json.getInt("vote_negative");
                int reply = json.getInt("comment_reply_ID");
                int id = json.getInt("comment_ID");
                essaysLists.add(new Essay(title, date, content, oo, xx, reply, id, email));
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void httpRequset(int page) {
        HttpUtil.sendHttpRequest(API.storiesAPI + page, new HttoCallbackListener() {
            @Override
            public void onFinish(String response) {
                LogUtils.d("TAG", response);
                Message message = new Message();
                message.what = SHOW_RESPONSE;
                message.obj = response;
                messageHttp = response;
                handler.sendMessage(message);
            }


            @Override
            public void onError(Exception e) {
            }
        });
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            scrollFlag = true;
        } else {
            scrollFlag = false;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            if (messageHttp != null) {
                if (totalItemCount != (page - 1) * numbersInOnePage) {
                    if (totalItemCount < numbersInOnePage * page) {
                        response(messageHttp, totalItemCount - (page - 1) * numbersInOnePage);
                    } else {
                        httpRequset(++page);
                    }
                }
            }
        }
        if (scrollFlag) {
            if (firstVisibleItem > lastVisibleItemPosition) {
                //上滑
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) refresh.getLayoutParams();
                int fabBottomMargin = lp.bottomMargin;
                refresh.animate().translationY(refresh.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
                shadow.animate().translationY(refresh.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
            }
            if (firstVisibleItem < lastVisibleItemPosition) {
                //下滑
                refresh.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                shadow.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }
            if (firstVisibleItem == lastVisibleItemPosition) {
                return;
            }
            lastVisibleItemPosition = firstVisibleItem;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_float:
                RotateAnimation animation = new RotateAnimation(0f, 1800f, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(1000);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                refresh.startAnimation(animation);

                essaysLists.clear();
                numbersInOnePage = 0;
                page = 1;
                httpRequset(page);
                mListView.setSelection(0);
                break;
        }
    }
    }






