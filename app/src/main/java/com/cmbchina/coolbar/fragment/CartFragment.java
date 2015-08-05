package com.cmbchina.coolbar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    private static final String TAG = "CartFragment";

    private CartListAdapter mCartListAdapter;
    private CartListView mCartListView;

    private LayoutInflater mInflater;

    private Map<String, Map<String, Object>> commodityMap;
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

//        commodityArrayList = new ArrayList<Commodity>();

        commodityMap = new HashMap<>();

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

    private void refreshListData(){
        mArray.clear();

        for(Map<String, Object> map : commodityMap.values()){
            mArray.add(map);
            Log.d(TAG, "quantity : " + map.get("quantity"));
        }

        mCartListAdapter.notifyDataSetChanged();
    }

    public void addCommoditytoCart(Commodity commodity){
        if (commodityMap.containsKey(commodity.getName())){

            Map<String, Object> tmp = commodityMap.get(commodity.getName());
            int num = Integer.parseInt((String) tmp.get("quantity"));
            tmp.put("quantity", Integer.toString(++num));

        }else {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", commodity.getName());
            map.put("price", commodity.getPrice());
            map.put("quantity", "1");
            map.put("pic", R.drawable.mushroom);
            commodityMap.put(commodity.getName(), map);
        }
        refreshListData();
    }

    public void subCommoditytoCart(Commodity commodity){
        if (commodityMap.containsKey(commodity.getName())){

            Map<String, Object> tmp = commodityMap.get(commodity.getName());
            int num = Integer.parseInt((String) tmp.get("quantity"));
            tmp.put("quantity", Integer.toString(--num));
            if(0 == num){
                commodityMap.remove(commodity.getName());
            }

        }
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
