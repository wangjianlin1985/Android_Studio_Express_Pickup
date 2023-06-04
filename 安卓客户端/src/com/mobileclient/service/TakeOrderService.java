package com.mobileclient.service;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mobileclient.domain.TakeOrder;
import com.mobileclient.util.HttpUtil;

/*���ö�������ҵ���߼���*/
public class TakeOrderService {
	/* ��Ӵ��ö��� */
	public String AddTakeOrder(TakeOrder takeOrder) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderId", takeOrder.getOrderId() + "");
		params.put("expressTakeObj", takeOrder.getExpressTakeObj() + "");
		params.put("userObj", takeOrder.getUserObj());
		params.put("takeTime", takeOrder.getTakeTime());
		params.put("orderStateObj", takeOrder.getOrderStateObj() + "");
		params.put("ssdt", takeOrder.getSsdt());
		params.put("evaluate", takeOrder.getEvaluate());
		params.put("action", "add");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "TakeOrderServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* ��ѯ���ö��� */
	public List<TakeOrder> QueryTakeOrder(TakeOrder queryConditionTakeOrder) throws Exception {
		String urlString = HttpUtil.BASE_URL + "TakeOrderServlet?action=query";
		if(queryConditionTakeOrder != null) {
			urlString += "&expressTakeObj=" + queryConditionTakeOrder.getExpressTakeObj();
			urlString += "&userObj=" + URLEncoder.encode(queryConditionTakeOrder.getUserObj(), "UTF-8") + "";
			urlString += "&takeTime=" + URLEncoder.encode(queryConditionTakeOrder.getTakeTime(), "UTF-8") + "";
			urlString += "&orderStateObj=" + queryConditionTakeOrder.getOrderStateObj();
		}

		/* 2�����ݽ�����������һ������SAXParser����xml�ļ���ʽ
		URL url = new URL(urlString);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();

		TakeOrderListHandler takeOrderListHander = new TakeOrderListHandler();
		xr.setContentHandler(takeOrderListHander);
		InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
		InputSource is = new InputSource(isr);
		xr.parse(is);
		List<TakeOrder> takeOrderList = takeOrderListHander.getTakeOrderList();
		return takeOrderList;*/
		//��2���ǻ���json���ݸ�ʽ���������ǲ��õ��ǵ�2��
		List<TakeOrder> takeOrderList = new ArrayList<TakeOrder>();
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(urlString, null, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				TakeOrder takeOrder = new TakeOrder();
				takeOrder.setOrderId(object.getInt("orderId"));
				takeOrder.setExpressTakeObj(object.getInt("expressTakeObj"));
				takeOrder.setUserObj(object.getString("userObj"));
				takeOrder.setTakeTime(object.getString("takeTime"));
				takeOrder.setOrderStateObj(object.getInt("orderStateObj"));
				takeOrder.setSsdt(object.getString("ssdt"));
				takeOrder.setEvaluate(object.getString("evaluate"));
				takeOrderList.add(takeOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return takeOrderList;
	}

	/* ���´��ö��� */
	public String UpdateTakeOrder(TakeOrder takeOrder) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderId", takeOrder.getOrderId() + "");
		params.put("expressTakeObj", takeOrder.getExpressTakeObj() + "");
		params.put("userObj", takeOrder.getUserObj());
		params.put("takeTime", takeOrder.getTakeTime());
		params.put("orderStateObj", takeOrder.getOrderStateObj() + "");
		params.put("ssdt", takeOrder.getSsdt());
		params.put("evaluate", takeOrder.getEvaluate());
		params.put("action", "update");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "TakeOrderServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* ɾ�����ö��� */
	public String DeleteTakeOrder(int orderId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderId", orderId + "");
		params.put("action", "delete");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "TakeOrderServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "���ö�����Ϣɾ��ʧ��!";
		}
	}

	/* ���ݶ���id��ȡ���ö������� */
	public TakeOrder GetTakeOrder(int orderId)  {
		List<TakeOrder> takeOrderList = new ArrayList<TakeOrder>();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderId", orderId + "");
		params.put("action", "updateQuery");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "TakeOrderServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				TakeOrder takeOrder = new TakeOrder();
				takeOrder.setOrderId(object.getInt("orderId"));
				takeOrder.setExpressTakeObj(object.getInt("expressTakeObj"));
				takeOrder.setUserObj(object.getString("userObj"));
				takeOrder.setTakeTime(object.getString("takeTime"));
				takeOrder.setOrderStateObj(object.getInt("orderStateObj"));
				takeOrder.setSsdt(object.getString("ssdt"));
				takeOrder.setEvaluate(object.getString("evaluate"));
				takeOrderList.add(takeOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int size = takeOrderList.size();
		if(size>0) return takeOrderList.get(0); 
		else return null; 
	}
}
