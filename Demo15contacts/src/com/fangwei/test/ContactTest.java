package com.fangwei.test;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

public class ContactTest extends AndroidTestCase{
	
	//
	private static final String TAG = "ContactsTest";
	
	//��ȡ���е���ϵ��
	public void testContacts() throws Exception {
		//ͨѶ¼ �����ṩ�ߵ�Ψһ�ı�ʶ
		Uri uri = Uri.parse("content://com.android.contacts/contacts");
		ContentResolver resolver = getContext().getContentResolver();
		Cursor cursor = resolver.query(uri, new String[]{"_id"}, null, null, null);
		//
			
		while(cursor.moveToNext()){
			int contactid = cursor.getInt(0);
			StringBuilder sb = new StringBuilder("contactid=");
			sb.append(contactid);
			uri = Uri.parse("content://com.android.contacts/contacts/"+contactid+"/data");
			Cursor datacursor = resolver.query(uri, new String[]{"mimetype","data1","data2"}, null, null, null);
			//
			while(datacursor.moveToNext()){
				String data = datacursor.getString(datacursor.getColumnIndex("data1"));
				String type = datacursor.getString(datacursor.getColumnIndex("mimetype"));
				if("vnd.android.cursor.item/name".equals(type)){//����
					sb.append(",name="+data);
				}
				else if("vnd.android.cursor.item/email_v2".equals(type)){//email
					sb.append(",email="+data);
				}else if("vnd.android.cursor.item/phone_v2".equals(type)){//phone
					sb.append(",phone="+data);
				}
				
			}

			//��ӡlog��־
			//��ӡ֮ǰҪ�Ըõ�Ӧ�ø����ȡͨѶ¼��Ϣ��Ȩ��
			Log.i(TAG, sb.toString());
		}
		
	}
	
	
	
	
	//���ݺ����ȡ��ϵ�˵�����
	public void testContactNameByNumer() throws Exception{
		String number = "15827241255";
		Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/"+ number); 
		ContentResolver resolver = getContext().getContentResolver();
		Cursor cursor = resolver.query(uri, new String[]{"display_name"}, null, null, null);
		if(cursor.moveToFirst()){
			String name = cursor.getString(0);
			Log.i(TAG, name);
		}
		cursor.close();
	}
	
	
	//������ϵ��
		public void testAddContact() throws Exception{
			Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
			ContentResolver resolver = getContext().getContentResolver();
			ContentValues values = new ContentValues();
			long contactid = ContentUris.parseId(resolver.insert(uri, values));
			//��������
			uri = Uri.parse("content://com.android.contacts/data");
			values.put("raw_contact_id", contactid);
			values.put("mimetype", "vnd.android.cursor.item/name");
			values.put("data2", "����01");
			resolver.insert(uri, values);
			//���ӵ绰
			values.clear();
			values.put("raw_contact_id", contactid);
			values.put("mimetype", "vnd.android.cursor.item/phone_v2");
			values.put("data2", "2");
			values.put("data1", "15827241256");
			resolver.insert(uri, values);
			
			//����Email
			values.clear();
			values.put("raw_contact_id", contactid);
			values.put("mimetype", "vnd.android.cursor.item/email_v2");
			values.put("data2", "2");
			values.put("data1", "89667566@qq.com");
			resolver.insert(uri, values);
		}
	
	
		//��ͬһ�������������ϵ�˸������ݵ�����
		public void testAddContact2() throws Exception{
			Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
			ContentResolver resolver = getContext().getContentResolver();
			ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
			ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
				.withValue("account_name", null)
				.build();
			operations.add(op1);
			
			uri = Uri.parse("content://com.android.contacts/data");
			ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/name")
				.withValue("data2", "����02")
				.build();
			operations.add(op2);
			
			ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/phone_v2")
				.withValue("data1", "15827241257")
				.withValue("data2", "2")
				.build();
			operations.add(op3);
			
			ContentProviderOperation op4 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/email_v2")
				.withValue("data1", "694614205@qq.com")
				.withValue("data2", "2")
				.build();
			operations.add(op4);
			
			resolver.applyBatch("com.android.contacts", operations);
		}
	
	
	
	
}