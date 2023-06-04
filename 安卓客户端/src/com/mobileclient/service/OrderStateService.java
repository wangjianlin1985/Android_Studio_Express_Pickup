package com.mobileclient.service;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mobileclient.domain.OrderState;
import com.mobileclient.util.HttpUtil;

/*����״̬����ҵ���߼���*/
public class OrderStateService {
	/* ��Ӷ���״̬ */
	public String AddOrderState(OrderState orderState) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderStateId", orderState.getOrderStateId() + "");
		params.put("orderStateName", orderState.getOrderStateName());
		params.put("action", "add");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "OrderStateServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* ��ѯ����״̬ */
	public List<OrderState> QueryOrderState(OrderState queryConditionOrderState) throws Exception {
		String urlString = HttpUtil.BASE_URL + "OrderStateServlet?action=query";
		if(queryConditionOrderState != null) {
		}

		/* 2�����ݽ�����������һ������SAXParser����xml�ļ���ʽ
		URL url = new URL(urlString);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();

		OrderStateListHandler orderStateListHander = new OrderStateListHandler();
		xr.setContentHandler(orderStateListHander);
		InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
		InputSource is = new InputSource(isr);
		xr.parse(is);
		List<OrderState> orderStateList = orderStateListHander.getOrderStateList();
		return orderStateList;*/
		//��2���ǻ���json���ݸ�ʽ���������ǲ��õ��ǵ�2��
		List<OrderState> orderStateList = new ArrayList<OrderState>();
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(urlString, null, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				OrderState orderState = new OrderState();
				orderState.setOrderStateId(object.getInt("orderStateId"));
				orderState.setOrderStateName(object.getString("orderStateName"));
				orderStateList.add(orderState);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderStateList;
	}

	/* ���¶���״̬ */
	public String UpdateOrderState(OrderState orderState) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderStateId", orderState.getOrderStateId() + "");
		params.put("orderStateName", orderState.getOrderStateName());
		params.put("action", "update");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "OrderStateServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* ɾ������״̬ */
	public String DeleteOrderState(int orderStateId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderStateId", orderStateId + "");
		params.put("action", "delete");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "OrderStateServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "����״̬��Ϣɾ��ʧ��!";
		}
	}

	/* ���ݶ���״̬id��ȡ����״̬���� */
	public OrderState GetOrderState(int orderStateId)  {
		List<OrderState> orderStateList = new ArrayList<OrderState>();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderStateId", orderStateId + "");
		params.put("action", "updateQuery");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "OrderStateServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				OrderState orderState = new OrderState();
				orderState.setOrderStateId(object.getInt("orderStateId"));
				orderState.setOrderStateName(object.getString("orderStateName"));
				orderStateList.add(orderState);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int size = orderStateList.size();
		if(size>0) return orderStateList.get(0); 
		else return null; 
	}
}
