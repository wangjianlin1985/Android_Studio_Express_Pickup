package com.mobileclient.util;

import java.util.List;  
import java.util.Map;

import com.mobileclient.service.CompanyService;
import com.mobileclient.service.UserInfoService;
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

public class ExpressTakeSimpleAdapter extends SimpleAdapter { 
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

    public ExpressTakeSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,ListView listView) { 
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
	  if (convertView == null) convertView = mInflater.inflate(R.layout.expresstake_list_item, null);
	  convertView.setTag("listViewTAG" + position);
	  holder = new ViewHolder(); 
	  /*�󶨸�view�����ؼ�*/
	  holder.tv_taskTitle = (TextView)convertView.findViewById(R.id.tv_taskTitle);
	  holder.tv_companyObj = (TextView)convertView.findViewById(R.id.tv_companyObj);
	  holder.tv_waybill = (TextView)convertView.findViewById(R.id.tv_waybill);
	  holder.tv_receiverName = (TextView)convertView.findViewById(R.id.tv_receiverName);
	  holder.tv_telephone = (TextView)convertView.findViewById(R.id.tv_telephone);
	  holder.tv_receiveMemo = (TextView)convertView.findViewById(R.id.tv_receiveMemo);
	  holder.tv_takePlace = (TextView)convertView.findViewById(R.id.tv_takePlace);
	  holder.tv_giveMoney = (TextView)convertView.findViewById(R.id.tv_giveMoney);
	  holder.tv_takeStateObj = (TextView)convertView.findViewById(R.id.tv_takeStateObj);
	  holder.tv_userObj = (TextView)convertView.findViewById(R.id.tv_userObj);
	  holder.tv_addTime = (TextView)convertView.findViewById(R.id.tv_addTime);
	  /*���ø����ؼ���չʾ����*/
	  holder.tv_taskTitle.setText("��������" + mData.get(position).get("taskTitle").toString());
	  holder.tv_companyObj.setText("������˾��" + (new CompanyService()).GetCompany(Integer.parseInt(mData.get(position).get("companyObj").toString())).getCompanyName());
	  holder.tv_waybill.setText("�˵����룺" + mData.get(position).get("waybill").toString());
	  holder.tv_receiverName.setText("�ջ��ˣ�" + mData.get(position).get("receiverName").toString());
	  holder.tv_telephone.setText("�ջ��绰��" + mData.get(position).get("telephone").toString());
	  holder.tv_receiveMemo.setText("�ջ���ע��" + mData.get(position).get("receiveMemo").toString());
	  holder.tv_takePlace.setText("�ʹ��ַ��" + mData.get(position).get("takePlace").toString());
	  holder.tv_giveMoney.setText("���ñ��꣺" + mData.get(position).get("giveMoney").toString());
	  holder.tv_takeStateObj.setText("����״̬��" + mData.get(position).get("takeStateObj").toString());
	  holder.tv_userObj.setText("���񷢲��ˣ�" + (new UserInfoService()).GetUserInfo(mData.get(position).get("userObj").toString()).getName());
	  holder.tv_addTime.setText("����ʱ�䣺" + mData.get(position).get("addTime").toString());
	  /*�����޸ĺõ�view*/
	  return convertView; 
    } 

    static class ViewHolder{ 
    	TextView tv_taskTitle;
    	TextView tv_companyObj;
    	TextView tv_waybill;
    	TextView tv_receiverName;
    	TextView tv_telephone;
    	TextView tv_receiveMemo;
    	TextView tv_takePlace;
    	TextView tv_giveMoney;
    	TextView tv_takeStateObj;
    	TextView tv_userObj;
    	TextView tv_addTime;
    }
} 
