package com.cmbchina.coolbar.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;

import com.cmbchina.coolbar.R;
import com.cmbchina.coolbar.customviews.ShoppingListView;
import com.cmbchina.coolbar.fragment.CartFragment;
import com.cmbchina.coolbar.fragment.UserInfoFragment;
import com.cmbchina.coolbar.models.Commodity;
import com.cmbchina.coolbar.slidingmenu.BaseSlidingFragmentActivity;
import com.cmbchina.coolbar.slidingmenu.SlidingMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liang on 7/1/15.
 */
public class MainActivity extends BaseSlidingFragmentActivity implements View.OnClickListener{

    private SlidingMenu mSlidingMenu;

    ArrayList<Map<String,Object>> mArray;
    ShoppingListAdapter mListAdapter;
    ShoppingListView mShoppingListView;

    private CartFragment mCartFrag = new CartFragment();

    private ArrayList<Commodity> commodityArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initSlidingMenu();
        setContentView(R.layout.main_center);
        initViews();
    }

    private void initSlidingMenu() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        setBehindContentView(R.layout.main_left);// 设置左菜单
        FragmentTransaction mFragementTransaction = getSupportFragmentManager()
                .beginTransaction();
        Fragment mFrag = new UserInfoFragment();
        mFragementTransaction.replace(R.id.main_left_fragment, mFrag);
        mFragementTransaction.commit();
        // customize the SlidingMenu
        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置是左滑还是右滑，还是左右都可以滑，我这里左右都可以滑
        mSlidingMenu.setShadowWidth(mScreenWidth / 40);// 设置阴影宽度
        mSlidingMenu.setBehindOffset(mScreenWidth / 8);// 设置菜单宽度
        mSlidingMenu.setFadeDegree(0.35f);// 设置淡入淡出的比例
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mSlidingMenu.setShadowDrawable(R.drawable.shadow_left);// 设置左菜单阴影图片
        mSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadow_right);// 设置右菜单阴影图片
        mSlidingMenu.setFadeEnabled(true);// 设置滑动时菜单的是否淡入淡出
        mSlidingMenu.setBehindScrollScale(0.333f);// 设置滑动时拖拽效果
    }

    private void initViews() {
//        mNetErrorView = findViewById(R.id.net_status_bar_top);
        mSlidingMenu.setSecondaryMenu(R.layout.main_right);
        FragmentTransaction mFragementTransaction = getSupportFragmentManager()
                .beginTransaction();

//        mFrag.setContext(this);
        mFragementTransaction.replace(R.id.main_right_fragment, mCartFrag);
        mFragementTransaction.commit();

        Button btnLeft  = (Button)findViewById(R.id.btn_main_left);
        Button btnRight = (Button)findViewById(R.id.btn_main_right);

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);

        mShoppingListView = (ShoppingListView) findViewById(R.id.list_view_shop);

        mArray = new ArrayList<Map<String, Object>>();

        mListAdapter = new ShoppingListAdapter(this, mArray, R.layout.list_item_shopping,
                new String[]{"pic","title"},
                new int[]{R.id.image_item, R.id.tv_name});

        mShoppingListView.setAdapter(mListAdapter);

        initTestData();
    }

    private void initTestData(){
        // Test data
        commodityArrayList = new ArrayList<Commodity>();
        Commodity c = new Commodity();
        c.setName("这是啥");
        c.setImageResource(R.drawable.mushroom);
        commodityArrayList.add(c);

        c = new Commodity();
        c.setName("那是啥");
        c.setImageResource(R.drawable.mushroom);
        commodityArrayList.add(c);

        c = new Commodity();
        c.setName("还有啥");
        c.setImageResource(R.drawable.mushroom);
        commodityArrayList.add(c);

        for(Commodity cmt:commodityArrayList){
            setListItem(cmt);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_main_left:
                mSlidingMenu.showMenu(true);
                break;
            case R.id.btn_main_right:
                mSlidingMenu.showSecondaryMenu(true);
                break;
            default:
        }
    }

    private class ShoppingListAdapter extends SimpleAdapter {

        private LayoutInflater layout;

        public ShoppingListAdapter(Context context, List<? extends Map<String, ?>> data,
                                   int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View result = super.getView(position, convertView, parent);

            layout = getLayoutInflater();
            if(result == null){
                result = layout.inflate(R.layout.list_item_shopping, null);
            }
            Button sub = (Button)result.findViewById(R.id.btn_shop_sub);
            Button add = (Button)result.findViewById(R.id.btn_shop_add);
            final EditText quantity = (EditText)result.findViewById(R.id.edit_shop_quantity);
            quantity.setText("0");
            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = quantity.getText().toString();
                    int num = Integer.parseInt(str);
                    if (num > 0) {
                        num--;
                        Commodity cm = new Commodity();
                        mCartFrag.subCommoditytoCart(cm);
                    }
                    quantity.setText(Integer.toString(num));
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = quantity.getText().toString();
                    int num = Integer.parseInt(str);
                    num++;
                    quantity.setText(Integer.toString(num));
                    Commodity cm = new Commodity();
                    mCartFrag.addCommoditytoCart(cm);
                }
            });
            return result;
        }
    }

    private void setListItem(Commodity commodity){
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("title", commodity.getName());
        map.put("pic", commodity.getImageResource());
        mArray.add(map);
    }

}
