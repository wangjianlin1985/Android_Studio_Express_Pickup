package com.mobileclient.util;

import java.util.List;  
import java.util.Map;

import com.mobileclient.service.ExpressTakeService;
import com.mobileclient.service.UserInfoService;
import com.mobileclient.service.OrderStateService;
import com.mobileclient.activity.R;
import com.mobileclient.imgCache.ImageLoadListener;
import com.mobileclient.imgCache.ListViewOnScrollListener;
import com.mobileclient.imgCache.SyncImageLoader;
import android.content.Context;
import android.view.LayoutInflater; 
import android.view.View;
import android.view.ViewGroup;  
import android.widget.ImageView; 
import android.widget.ListView;
import android.widget.SimpleAdapter; 
import android.widget.TextView; 

public class TakeOrderSimpleAdapter extends SimpleAdapter { 
	/*��Ҫ�󶨵Ŀؼ���Դid*/
    private int[] mTo;
    /*map���Ϲؼ�������*/
    private String[] mFrom;
/*��Ҫ�󶨵�����*/
    private List<? extends Map<String, ?>> mData; 

    private LayoutInflater mInflater;
    Context context = null;

    private ListView mListView;
    //ͼƬ�첽���������,���ڴ滺����ļ�����
    private SyncImageLoader syncImageLoader;

    public TakeOrderSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,ListView listView) { 
        super(context, data, resource, from, to); 
        mTo = to; 
        mFrom = from; 
        mData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context= context;
        mListView = listView; 
        syncImageLoader = SyncImageLoader.getInstance();
        ListViewOnScrollListener onScrollListener = new ListViewOnScrollListener(syncImageLoader,listView,getCount());
        mListView.setOnScrollListener(onScrollListener);
    } 

  public View getView(int position, View convertView, ViewGroup parent) { 
	  ViewHolder holder = null;
	  ///*��һ��װ�����viewʱ=null,���½�һ������inflate��Ⱦһ��view*/
	  if (convertView == null) convertView = mInflater.inflate(R.layout.takeorder_list_item, null);
	  convertView.setTag("listViewTAG" + position);
	  holder = new ViewHolder(); 
	  /*�󶨸�view�����ؼ�*/
	  holder.tv_expressTakeObj = (TextView)convertView.findViewById(R.id.tv_expressTakeObj);
	  holder.tv_userObj = (TextView)convertView.findViewById(R.id.tv_userObj);
	  holder.tv_userObj.setVisibility(View.GONE);
	  holder.tv_takeTime = (TextView)convertView.findViewById(R.id.tv_takeTime);
	  holder.tv_orderStateObj = (TextView)convertView.findViewById(R.id.tv_orderStateObj);
	  holder.tv_ssdt = (TextView)convertView.findViewById(R.id.tv_ssdt);
	  holder.tv_evaluate = (TextView)convertView.findViewById(R.id.tv_evaluate);
	  /*���ø����ؼ���չʾ����*/
	  holder.tv_expressTakeObj.setText("���õĿ�ݣ�" + (new ExpressTakeService()).GetExpressTake(Integer.parseInt(mData.get(position).get("expressTakeObj").toString())).getTaskTitle());
	  holder.tv_userObj.setText("�������ˣ�" + (new UserInfoService()).GetUserInfo(mData.get(position).get("userObj").toString()).getName());
	  holder.tv_takeTime.setText("�ӵ�ʱ�䣺" + mData.get(position).get("takeTime").toString());
	  holder.tv_orderStateObj.setText("����״̬��" + (new OrderStateService()).GetOrderState(Integer.parseInt(mData.get(position).get("orderStateObj").toString())).getOrderStateName());
	  holder.tv_ssdt.setText("ʵʱ��̬��" + mData.get(position).get("ssdt").toString());
	  holder.tv_evaluate.setText("�û����ۣ�" + mData.get(position).get("evaluate").toString());
	  /*�����޸ĺõ�view*/
	  return convertView; 
    } 

    static class ViewHolder{ 
    	TextView tv_expressTakeObj;
    	TextView tv_userObj;
    	TextView tv_takeTime;
    	TextView tv_orderStateObj;
    	TextView tv_ssdt;
    	TextView tv_evaluate;
    }
} 
