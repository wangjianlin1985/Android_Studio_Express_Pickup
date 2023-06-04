package com.mobileclient.activity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.mobileclient.domain.UserInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.ImageView;
import android.widget.TextView;
public class UserInfoQueryActivity extends Activity {
	// ������ѯ��ť
	private Button btnQuery;
	// �����û��������
	private EditText ET_user_name;
	// �����û����������
	private EditText ET_userType;
	// �������������
	private EditText ET_name;
	// �������ڿؼ�
	private DatePicker dp_birthDate;
	private CheckBox cb_birthDate;
	// ������ϵ�绰�����
	private EditText ET_telephone;
	// �������״̬�����
	private EditText ET_shenHeState;
	/*��ѯ�����������浽���������*/
	private UserInfo queryConditionUserInfo = new UserInfo();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ȥ��Activity�����״̬��
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.userinfo_query);
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("�����û���ѯ����");
		ImageView back_btn = (ImageView) this.findViewById(R.id.back_btn);
		back_btn.setOnClickListener(new android.view.View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btnQuery = (Button) findViewById(R.id.btnQuery);
		ET_user_name = (EditText) findViewById(R.id.ET_user_name);
		ET_userType = (EditText) findViewById(R.id.ET_userType);
		ET_name = (EditText) findViewById(R.id.ET_name);
		dp_birthDate = (DatePicker) findViewById(R.id.dp_birthDate);
		cb_birthDate = (CheckBox) findViewById(R.id.cb_birthDate);
		ET_telephone = (EditText) findViewById(R.id.ET_telephone);
		ET_shenHeState = (EditText) findViewById(R.id.ET_shenHeState);
		/*������ѯ��ť*/
		btnQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��ȡ��ѯ����*/
					queryConditionUserInfo.setUser_name(ET_user_name.getText().toString());
					queryConditionUserInfo.setUserType(ET_userType.getText().toString());
					queryConditionUserInfo.setName(ET_name.getText().toString());
					if(cb_birthDate.isChecked()) {
						/*��ȡ��������*/
						Date birthDate = new Date(dp_birthDate.getYear()-1900,dp_birthDate.getMonth(),dp_birthDate.getDayOfMonth());
						queryConditionUserInfo.setBirthDate(new Timestamp(birthDate.getTime()));
					} else {
						queryConditionUserInfo.setBirthDate(null);
					} 
					queryConditionUserInfo.setTelephone(ET_telephone.getText().toString());
					queryConditionUserInfo.setShenHeState(ET_shenHeState.getText().toString());
					Intent intent = getIntent();
					//����ʹ��bundle��������������
					Bundle bundle =new Bundle();
					//�����������Ȼ�Ǽ�ֵ�Ե���ʽ
					bundle.putSerializable("queryConditionUserInfo", queryConditionUserInfo);
					intent.putExtras(bundle);
					setResult(RESULT_OK,intent);
					finish();
				} catch (Exception e) {}
			}
			});
	}
}
