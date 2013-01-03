package com.fangwei.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.fangwei.domain.Person;
import com.fangwei.otherdb.DBOpenHelper;
import com.fangwei.service.OtherPersonService;


public class OtherPersonServiceTest extends AndroidTestCase {
	private static final String TAG = "otherPersonServiceTes";

	public void testDBCreate() throws Exception {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this.getContext());
//		dbOpenHelper.getReadableDatabase();
		dbOpenHelper.getWritableDatabase();
		//<包>/databases/itcast.db
		
	}
	
	public void testSave() throws Exception {
		OtherPersonService service = new OtherPersonService(getContext());
		Person person = new Person("9999", "186010233434", 200);
		service.save(person);
		//在文件夹下看 .db用SQLlite软件 打开 看 是否插入
	}
	

	/**
	 * 给测试分页提供数据
	 * @throws Exception
	 */
	public void testSave01() throws Exception {
		OtherPersonService service  = new OtherPersonService(getContext());
		for(int i =1 ; i < 20 ; i++){
			Person person = new Person(1,"fangwei","15827241251");
			service.save(person);
			//在文件夹下看 .db用SQLlite软件 打开 看 是否插入
		}
	
	}
	
	/**
	 * 获取记录
	 * @param id
	 * @throws Exception
	 */
	public void testfind() throws Exception {
		OtherPersonService service  = new OtherPersonService(getContext());
		Person person = service.find(1);
		//重写 person的tostring方法
		Log.i(TAG,person.toString());
		
	}
	
	/**
	 * 更新记录
	 * @throws Exception
	 */
	public void testUpdate() throws Exception {
		OtherPersonService service = new OtherPersonService(getContext());
		Person person = service.find(1);
		person.setName("fangwei110");
		service.update(person);
		//测试方式 先执行该方法  ----再次 执行 上面的testfind（）方法
	}
	
	/**
	 * 
	 */
	public void testCount()  {
		OtherPersonService service = new OtherPersonService(getContext());
		Log.i(TAG, service.getCount()+" ");
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public  void testScrollData() throws Exception {
		OtherPersonService service = new OtherPersonService(getContext());
		//测试的时候  改变前面的 参数 8 16 是代表第几条 8 -- 9 开始  16 --17 开始
		List<Person> persons = service.getScrollData(0, 8);
		for(Person person : persons)
			Log.i(TAG, person.toString());
	}
	
	/**
	 * 
	 */
	public void testdelete() {
		OtherPersonService service = new OtherPersonService(getContext());
		service.delete(19);
		//测试方法 :执行该方法 然后 执行 测试总数  是否是19条
	}
}
