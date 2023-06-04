package com.mobileclient.activity;

import java.util.Date;
import com.mobileclient.domain.Company;
import com.mobileclient.service.CompanyService;
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
public class CompanyDetailActivity extends Activity {
	// �������ذ�ť
	private Button btnReturn;
	// ������˾id�ؼ�
	private TextView TV_companyId;
	// ������˾���ƿؼ�
	private TextView TV_companyName;
	/* Ҫ�����������˾��Ϣ */
	Company company = new Company(); 
	/* ������˾����ҵ���߼��� */
	private CompanyService companyService = new CompanyService();
	private int companyId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ȥ��Activity�����״̬��
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.company_detail);
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("�鿴������˾����");
		ImageView back = (ImageView) this.findViewById(R.id.back_btn);
		back.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		// ͨ��findViewById����ʵ�������
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_companyId = (TextView) findViewById(R.id.TV_companyId);
		TV_companyName = (TextView) findViewById(R.id.TV_companyName);
		Bundle extras = this.getIntent().getExtras();
		companyId = extras.getInt("companyId");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				CompanyDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* ��ʼ����ʾ������������ */
	private void initViewData() {
	    company = companyService.GetCompany(companyId); 
		this.TV_companyId.setText(company.getCompanyId() + "");
		this.TV_companyName.setText(company.getCompanyName());
	} 
}
