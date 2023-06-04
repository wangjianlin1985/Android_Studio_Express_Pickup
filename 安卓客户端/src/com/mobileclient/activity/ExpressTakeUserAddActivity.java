package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.mobileclient.util.HttpUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.ExpressTake;
import com.mobileclient.service.ExpressTakeService;
import com.mobileclient.domain.Company;
import com.mobileclient.service.CompanyService;
import com.mobileclient.domain.UserInfo;
import com.mobileclient.service.UserInfoService;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class ExpressTakeUserAddActivity extends Activity {
	// ����ȷ����Ӱ�ť
	private Button btnAdd;
	// �����������������
	private EditText ET_taskTitle;
	// ����������˾������
	private Spinner spinner_companyObj;
	private ArrayAdapter<String> companyObj_adapter;
	private static  String[] companyObj_ShowText  = null;
	private List<Company> companyList = null;
	/*������˾����ҵ���߼���*/
	private CompanyService companyService = new CompanyService();
	// �����˵����������
	private EditText ET_waybill;
	// �����ջ��������
	private EditText ET_receiverName;
	// �����ջ��绰�����
	private EditText ET_telephone;
	// �����ջ���ע�����
	private EditText ET_receiveMemo;
	// �����ʹ��ַ�����
	private EditText ET_takePlace;
	// �������ñ��������
	private EditText ET_giveMoney;
	 
	protected String carmera_path;
	/*Ҫ����Ŀ�ݴ�����Ϣ*/
	ExpressTake expressTake = new ExpressTake();
	/*��ݴ��ù���ҵ���߼���*/
	private ExpressTakeService expressTakeService = new ExpressTakeService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ȥ��Activity�����״̬��
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.expresstake_user_add); 
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("��ӿ�ݴ���");
		ImageView back = (ImageView) this.findViewById(R.id.back_btn);
		back.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		ET_taskTitle = (EditText) findViewById(R.id.ET_taskTitle);
		spinner_companyObj = (Spinner) findViewById(R.id.Spinner_companyObj);
		// ��ȡ���е�������˾
		try {
			companyList = companyService.QueryCompany(null);
		} catch (Exception e1) { 
			e1.printStackTrace(); 
		}
		int companyCount = companyList.size();
		companyObj_ShowText = new String[companyCount];
		for(int i=0;i<companyCount;i++) { 
			companyObj_ShowText[i] = companyList.get(i).getCompanyName();
		}
		// ����ѡ������ArrayAdapter��������
		companyObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, companyObj_ShowText);
		// ���������б�ķ��
		companyObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ��adapter ��ӵ�spinner��
		spinner_companyObj.setAdapter(companyObj_adapter);
		// ����¼�Spinner�¼�����
		spinner_companyObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				expressTake.setCompanyObj(companyList.get(arg2).getCompanyId()); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// ����Ĭ��ֵ
		spinner_companyObj.setVisibility(View.VISIBLE);
		ET_waybill = (EditText) findViewById(R.id.ET_waybill);
		ET_receiverName = (EditText) findViewById(R.id.ET_receiverName);
		ET_telephone = (EditText) findViewById(R.id.ET_telephone);
		ET_receiveMemo = (EditText) findViewById(R.id.ET_receiveMemo);
		ET_takePlace = (EditText) findViewById(R.id.ET_takePlace);
		ET_giveMoney = (EditText) findViewById(R.id.ET_giveMoney);
		 
		Declare declare = (Declare) ExpressTakeUserAddActivity.this.getApplication();
		expressTake.setUserObj(declare.getUserName());
		
		 
		btnAdd = (Button) findViewById(R.id.BtnAdd);
		/*������ӿ�ݴ��ð�ť*/
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��֤��ȡ��������*/ 
					if(ET_taskTitle.getText().toString().equals("")) {
						Toast.makeText(ExpressTakeUserAddActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_taskTitle.setFocusable(true);
						ET_taskTitle.requestFocus();
						return;	
					}
					expressTake.setTaskTitle(ET_taskTitle.getText().toString());
					/*��֤��ȡ�˵�����*/ 
					if(ET_waybill.getText().toString().equals("")) {
						Toast.makeText(ExpressTakeUserAddActivity.this, "�˵��������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_waybill.setFocusable(true);
						ET_waybill.requestFocus();
						return;	
					}
					expressTake.setWaybill(ET_waybill.getText().toString());
					/*��֤��ȡ�ջ���*/ 
					if(ET_receiverName.getText().toString().equals("")) {
						Toast.makeText(ExpressTakeUserAddActivity.this, "�ջ������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_receiverName.setFocusable(true);
						ET_receiverName.requestFocus();
						return;	
					}
					expressTake.setReceiverName(ET_receiverName.getText().toString());
					/*��֤��ȡ�ջ��绰*/ 
					if(ET_telephone.getText().toString().equals("")) {
						Toast.makeText(ExpressTakeUserAddActivity.this, "�ջ��绰���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_telephone.setFocusable(true);
						ET_telephone.requestFocus();
						return;	
					}
					expressTake.setTelephone(ET_telephone.getText().toString());
					/*��֤��ȡ�ջ���ע*/ 
					if(ET_receiveMemo.getText().toString().equals("")) {
						Toast.makeText(ExpressTakeUserAddActivity.this, "�ջ���ע���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_receiveMemo.setFocusable(true);
						ET_receiveMemo.requestFocus();
						return;	
					}
					expressTake.setReceiveMemo(ET_receiveMemo.getText().toString());
					/*��֤��ȡ�ʹ��ַ*/ 
					if(ET_takePlace.getText().toString().equals("")) {
						Toast.makeText(ExpressTakeUserAddActivity.this, "�ʹ��ַ���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_takePlace.setFocusable(true);
						ET_takePlace.requestFocus();
						return;	
					}
					expressTake.setTakePlace(ET_takePlace.getText().toString());
					/*��֤��ȡ���ñ���*/ 
					if(ET_giveMoney.getText().toString().equals("")) {
						Toast.makeText(ExpressTakeUserAddActivity.this, "���ñ������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_giveMoney.setFocusable(true);
						ET_giveMoney.requestFocus();
						return;	
					}
					expressTake.setGiveMoney(Float.parseFloat(ET_giveMoney.getText().toString()));
					 
					expressTake.setTakeStateObj("���ӵ�");
					 
					expressTake.setAddTime("--");
					/*����ҵ���߼����ϴ���ݴ�����Ϣ*/
					ExpressTakeUserAddActivity.this.setTitle("�����ϴ���ݴ�����Ϣ���Ե�...");
					String result = expressTakeService.AddExpressTake(expressTake);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					Intent intent = getIntent();
					setResult(RESULT_OK,intent);
					finish();
				} catch (Exception e) {}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
