package com.mobileclient.activity;

import java.net.URLEncoder;
import java.util.Date;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.ExpressTake;
import com.mobileclient.domain.TakeOrder;
import com.mobileclient.service.ExpressTakeService;
import com.mobileclient.service.TakeOrderService;
import com.mobileclient.domain.Company;
import com.mobileclient.service.CompanyService;
import com.mobileclient.domain.UserInfo;
import com.mobileclient.service.UserInfoService;
import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import android.app.Activity;
import android.content.Intent;
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
public class ExpressTakeDetailActivity extends Activity {
	// �������ذ�ť
	private Button btnReturn;
	// ��������id�ؼ�
	private TextView TV_orderId;
	// ������������ؼ�
	private TextView TV_taskTitle;
	// ����������˾�ؼ�
	private TextView TV_companyObj;
	// �����˵�����ؼ�
	private TextView TV_waybill;
	// �����ջ��˿ؼ�
	private TextView TV_receiverName;
	// �����ջ��绰�ؼ�
	private TextView TV_telephone;
	// �����ջ���ע�ؼ�
	private TextView TV_receiveMemo;
	// �����ʹ��ַ�ؼ�
	private TextView TV_takePlace;
	// �������ñ���ؼ�
	private TextView TV_giveMoney;
	// ��������״̬�ؼ�
	private TextView TV_takeStateObj;
	// �������񷢲��˿ؼ�
	private TextView TV_userObj;
	// ��������ʱ��ؼ�
	private TextView TV_addTime;
	/* Ҫ����Ŀ�ݴ�����Ϣ */
	ExpressTake expressTake = new ExpressTake(); 
	/* ��ݴ��ù���ҵ���߼��� */
	private ExpressTakeService expressTakeService = new ExpressTakeService();
	private CompanyService companyService = new CompanyService();
	private UserInfoService userInfoService = new UserInfoService(); 
	private TakeOrderService takeOrderService = new TakeOrderService();
	
	private int orderId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ȥ��Activity�����״̬��
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.expresstake_detail);
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("�鿴��ݴ�������");
		ImageView back = (ImageView) this.findViewById(R.id.back_btn);
		back.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		// ͨ��findViewById����ʵ�������
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_orderId = (TextView) findViewById(R.id.TV_orderId);
		TV_taskTitle = (TextView) findViewById(R.id.TV_taskTitle);
		TV_companyObj = (TextView) findViewById(R.id.TV_companyObj);
		TV_waybill = (TextView) findViewById(R.id.TV_waybill);
		TV_receiverName = (TextView) findViewById(R.id.TV_receiverName);
		TV_telephone = (TextView) findViewById(R.id.TV_telephone);
		TV_receiveMemo = (TextView) findViewById(R.id.TV_receiveMemo);
		TV_takePlace = (TextView) findViewById(R.id.TV_takePlace);
		TV_giveMoney = (TextView) findViewById(R.id.TV_giveMoney);
		TV_takeStateObj = (TextView) findViewById(R.id.TV_takeStateObj);
		TV_userObj = (TextView) findViewById(R.id.TV_userObj);
		TV_addTime = (TextView) findViewById(R.id.TV_addTime);
		Bundle extras = this.getIntent().getExtras();
		orderId = extras.getInt("orderId");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ExpressTakeDetailActivity.this.finish();
			}
		}); 
		
		initViewData();
		
		Declare declare = (Declare) this.getApplication();
		final String userName = declare.getUserName();
		
		Button btnGetOrder = (Button) findViewById(R.id.btnGetOrder);
		btnGetOrder.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TakeOrder takeOrder = new TakeOrder();
				takeOrder.setEvaluate("--");
				takeOrder.setExpressTakeObj(expressTake.getOrderId());
				takeOrder.setOrderStateObj(1);
				takeOrder.setSsdt("--");
				takeOrder.setTakeTime("--");
				takeOrder.setUserObj(userName);
				
				String result = takeOrderService.AddTakeOrder(takeOrder);
				Toast.makeText(getApplicationContext(), result, 1).show(); 
				Intent intent = getIntent();
				setResult(RESULT_OK,intent);
				finish();
			}
		}); 
		
		UserInfo userInfo = userInfoService.GetUserInfo(userName);
		if(userInfo.getUserType().equals("���Ա") && expressTake.getTakeStateObj().equals("���ӵ�")) {
			btnGetOrder.setVisibility(View.VISIBLE);
		}
		
		Button btnViewOrder = (Button) findViewById(R.id.btnViewOrder);
		btnViewOrder.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				try {
					int orderId = expressTake.getOrderId();
					TakeOrder queryConditionTakeOrder = new TakeOrder();
					queryConditionTakeOrder.setExpressTakeObj(orderId);
					queryConditionTakeOrder.setUserObj("");
					queryConditionTakeOrder.setTakeTime("");
					queryConditionTakeOrder.setOrderStateObj(0); 
					TakeOrder takeOrder= takeOrderService.QueryTakeOrder(queryConditionTakeOrder).get(0);
 
	            	Intent intent = new Intent();
	            	intent.setClass(ExpressTakeDetailActivity.this, TakeOrderDetailActivity.class);
	            	Bundle bundle = new Bundle();
	            	bundle.putInt("orderId", takeOrder.getOrderId());
	            	intent.putExtras(bundle);
	            	startActivity(intent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}); 
		
		
		if(expressTake.getTakeStateObj().equals("�ѽӵ�") && expressTake.getUserObj().equals(userName) ) {
			btnViewOrder.setVisibility(View.VISIBLE);
		}
		
		
	}
	/* ��ʼ����ʾ������������ */
	private void initViewData() {
	    expressTake = expressTakeService.GetExpressTake(orderId); 
		this.TV_orderId.setText(expressTake.getOrderId() + "");
		this.TV_taskTitle.setText(expressTake.getTaskTitle());
		Company companyObj = companyService.GetCompany(expressTake.getCompanyObj());
		this.TV_companyObj.setText(companyObj.getCompanyName());
		this.TV_waybill.setText(expressTake.getWaybill());
		this.TV_receiverName.setText(expressTake.getReceiverName());
		this.TV_telephone.setText(expressTake.getTelephone());
		this.TV_receiveMemo.setText(expressTake.getReceiveMemo());
		this.TV_takePlace.setText(expressTake.getTakePlace());
		this.TV_giveMoney.setText(expressTake.getGiveMoney() + "");
		this.TV_takeStateObj.setText(expressTake.getTakeStateObj());
		UserInfo userObj = userInfoService.GetUserInfo(expressTake.getUserObj());
		this.TV_userObj.setText(userObj.getName());
		this.TV_addTime.setText(expressTake.getAddTime());
	} 
}
