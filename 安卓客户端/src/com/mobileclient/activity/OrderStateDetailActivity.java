package com.mobileclient.activity;

import java.util.Date;
import com.mobileclient.domain.OrderState;
import com.mobileclient.service.OrderStateService;
import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
public class OrderStateDetailActivity extends Activity {
	// �������ذ�ť
	private Button btnReturn;
	// ��������״̬id�ؼ�
	private TextView TV_orderStateId;
	// ��������״̬���ƿؼ�
	private TextView TV_orderStateName;
	/* Ҫ����Ķ���״̬��Ϣ */
	OrderState orderState = new OrderState(); 
	/* ����״̬����ҵ���߼��� */
	private OrderStateService orderStateService = new OrderStateService();
	private int orderStateId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ȥ��Activity�����״̬��
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.orderstate_detail);
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("�鿴����״̬����");
		ImageView back = (ImageView) this.findViewById(R.id.back_btn);
		back.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		// ͨ��findViewById����ʵ�������
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_orderStateId = (TextView) findViewById(R.id.TV_orderStateId);
		TV_orderStateName = (TextView) findViewById(R.id.TV_orderStateName);
		Bundle extras = this.getIntent().getExtras();
		orderStateId = extras.getInt("orderStateId");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				OrderStateDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* ��ʼ����ʾ������������ */
	private void initViewData() {
	    orderState = orderStateService.GetOrderState(orderStateId); 
		this.TV_orderStateId.setText(orderState.getOrderStateId() + "");
		this.TV_orderStateName.setText(orderState.getOrderStateName());
	} 
}
