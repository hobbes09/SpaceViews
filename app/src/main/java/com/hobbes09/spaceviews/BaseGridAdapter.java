package com.hobbes09.spaceviews;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.logging.Logger;

/**
 * Created by hobbes09 on 25/05/16.
 */
public abstract class BaseGridAdapter extends BaseAdapter {

    public abstract int getNumColumns();

    public abstract Context getContext();

    public abstract int getTotal();

    @Override
    public int getCount() {
        return (int)(Math.ceil((double)getTotal() / getNumColumns()));
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(getNumColumns() <= 0 || getContext() == null){
            return null;
        }

        LinearLayout linearLayout = new LinearLayout(getContext());
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(100f);

        float layoutWeight = 100f / getNumColumns();

        for(int index = 0; index < getNumColumns(); index++){
            if((getNumColumns() * position) + index < getTotal()){
                View child = getCellView((getNumColumns() * position) + index);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, layoutWeight);
                child.setLayoutParams(lp);

                linearLayout.addView(child);
            }
        }

        return linearLayout;
    }

    public abstract View getCellView(int cellPosition);
}
