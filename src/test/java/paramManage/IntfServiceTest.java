package paramManage;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.entity.IntfService;
import com.dcits.paramManage.mapper.IntfServiceMapper;
import com.dcits.paramManage.util.PropertiesUtil;

public class IntfServiceTest implements Cloneable{

	@Autowired
	private  IntfServiceMapper mapper;
	
	/**
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {

//		String a="[{system_cnname=互金存款, service_desc=2额, service_id=1231, service_cnname=萨达, service_updatetime=2018-11-14 21:00:05, service_status=0, service_no=, service_system=HJTP, service_version=2 , service_enname=wqeq}, {system_cnname=互金存款, service_desc=三峡回调接口, service_id=1f7f4a9b-52c9-4757-a339-6778660911b3, service_cnname=联合贷款结果推送, service_updatetime=2018-11-14 20:54:49, service_status=0, service_no=, service_system=WD, service_version=null, service_enname=UnLdRstPsh}, {system_cnname=直销银行, service_desc=京东企业众邦宝开户, service_id=0fee1fc3-f6f8-40ae-abe1-adc593f0c3fa, service_cnname=企业开户接口, service_updatetime=2018-11-14 21:31:35, service_status=1, service_no=, service_system=ZX, service_version=null, service_enname=EntprsMsgSend}]";
//		List<HashMap<String, Object>> list=a;
		
//		Map<String, Object> map=new HashMap<String,Object>();
//		map.put("system_cnname", "互金存款");
//		map.put("service_desc", "三峡");
//		map.put("service_id", "1f7f4a9b-52c9-4757-a339-6778660911b3");
//		map.put("service_updatetime", "2018-11-14 20:54:49");
		
//		JSONObject jsonObject=new JSONObject();
//		jsonObject.put("system_cnname", "互金存款");
//		jsonObject.put("service_desc", "三峡");
//		jsonObject.put("service_id", "1f7f4a9b-52c9-4757-a339-6778660911b3");
//		jsonObject.put("service_updatetime", "2018-11-14 20:54:49");
//		System.out.println(map);
//		
//		JSONArray array=new JSONArray();
//		array.add(map);
//		System.out.println(array);
//		String aaa={"system_cnname":"互金存款","service_desc":"三峡回调接口","service_id":"1f7f4a9b-52c9-4757-a339-6778660911b3","service_cnname":"联合贷款结果推送","service_updatetime":"2018-11-14 20:54:49","service_status":"0","service_no":"","service_system":"WD","service_version":null,"service_enname":"UnLdRstPsh"}"
		
		
//		IntfService intf=(IntfService) Class.forName("com.dcits.paramManage.entity.IntfService").newInstance();
//		Constructor<IntfService> constructor=IntfService.class.getConstructor();
//		IntfService service=constructor.newInstance();
		
//		String a="1ffb72757091457d869485817ee680d4";
//		String dir=PropertiesUtil.getProperty("upload.file.dir");
//		System.out.println(a.length());
		
		String filename="服务治理_众邦银行开放平台_字段映射_原道企业众邦宝_V1.0.3.xlsx";
		String describe=filename.substring(filename.indexOf("字段映射")+5,filename.lastIndexOf("_"));
		System.out.println(describe);
		
	}

}
