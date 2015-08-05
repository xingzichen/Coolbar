package com.cmbchina.coolbar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.cmbchina.coolbar.R;
import com.cmbchina.coolbar.customviews.CartListView;
import com.cmbchina.coolbar.models.Commodity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liang on 7/2/15.
 */
public class CartFragment extends Fragment implements View.OnClickListener {

    private CartListAdapter mCartListAdapter;
    private CartListView mCartListView;

    private LayoutInflater mInflater;

    private ArrayList<Commodity> commodityArrayList;
    private ArrayList<Map<String,Object>> mArray;


    public CartFragment(){
        super();
    }

    @Override
    public void onClick(View view) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mInflater = inflater;

        mArray = new ArrayList<Map<String, Object>>();

        commodityArrayList = new ArrayList<Commodity>();

        View view = inflater.inflate(R.layout.main_right, null);
        mCartListView = (CartListView) view.findViewById(R.id.list_view_cart);


        mCartListAdapter = new CartListAdapter(getActivity(), mArray, R.layout.list_item_cart,
                new String[]{"pic","title"},
                new int[]{R.id.image_item, R.id.tv_name});

        mCartListView.setAdapter(mCartListAdapter);

//        initTestData();

        refreshListData();
        return view;
    }

    private void initTestData(){
        Map<String, Object> map = new HashMap<String, Object>();

        // Test data
        map.put("title", "这是啥");
        map.put("pic", R.drawable.mushroom);
        mArray.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "那是啥");
        map.put("pic", R.drawable.mushroom);
        mArray.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "还有啥");
        map.put("pic", R.drawable.mushroom);
        mArray.add(map);
    }

    private void refreshListData(){
        Map<String, Object> map;
        mArray.clear();

        for(Commodity cm:commodityArrayList){
            boolean isFindOut = false;

            for(Map<String,Object> tmp:mArray){
                if(((String)tmp.get("title")).equalsIgnoreCase(cm.getName())){
                    // find out the node
                    isFindOut = true;
                    int num = Integer.parseInt((String) tmp.get("quantity"));
                    tmp.put("quantity", ++num);
                }
            }

            if(false == isFindOut) {
                map = new HashMap<String, Object>();
                map.put("title", cm.getName());
                map.put("price", cm.getPrice());
                map.put("quantity", "1");
                map.put("pic", R.drawable.mushroom);
                mArray.add(map);
            }

        }
        mCartListAdapter.notifyDataSetChanged();
    }

    public void addCommoditytoCart(Commodity commodity){
        commodityArrayList.add(commodity);
        refreshListData();
    }

    public void subCommoditytoCart(Commodity commodity){
        commodityArrayList.remove(commodity);
        refreshListData();
    }

    private class CartListAdapter extends SimpleAdapter {

        public CartListAdapter(Context context, List<? extends Map<String, ?>> data,
                               int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View result = super.getView(position, convertView, parent);

            if(result == null){
                result = mInflater.inflate(R.layout.list_item_cart, null);
            }
            return result;
        }
    }

}
