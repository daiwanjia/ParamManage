package paramManage;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dcits.paramManage.service.IntfServiceService;

public class ReadExcel {
	
	@Autowired
	private IntfServiceService service;
	
	@Test
	public void readExcel() throws Exception {
		
//		String filepath=ReadExcel.class.getClassLoader().getResource("服务治理_众邦银行开放平台_字段映射_互金系统_存款类_V1.3.1.xlsx").getPath();
//		File file=new File(filepath);
//		String systemId="4";
//		String result=service.batchimportIntf(file, systemId);
//		System.out.println(result);
	}
}
