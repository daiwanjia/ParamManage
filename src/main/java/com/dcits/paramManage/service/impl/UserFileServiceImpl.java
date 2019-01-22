package com.dcits.paramManage.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.common.JsonData;
import com.dcits.paramManage.entity.UserFile;
import com.dcits.paramManage.mapper.UserFileMapper;
import com.dcits.paramManage.service.UserFileService;
import com.dcits.paramManage.util.PropertiesUtil;
import com.dcits.paramManage.util.TimeUtil;
import com.dcits.paramManage.util.UUIDUtil;

@Service
@Transactional
public class UserFileServiceImpl implements UserFileService {

	private static final Log log = LogFactory.getLog(UserFileServiceImpl.class);

	@Autowired
	private UserFileMapper fileMapper;

	// 删除单个文件
	@Override
	public String deleteByFileId(String fileId) throws Exception{
		//根据id查询此文件路径
		UserFile userFile=fileMapper.selectOne(fileId);
		String filePath=userFile.getFilePath();
		File file=new File(filePath);
		if(file.exists()){
			file.delete();
			if(log.isInfoEnabled()){
				log.info(userFile.getFileOriginalName()+"文件删除成功！");
			}
		}
		//即使无此文件也应删除库中的记录
		JSONObject jsonObject = new JSONObject();
		int flag=fileMapper.deleteByFileId(fileId);
		if (flag == 1) {
			jsonObject.put("msg", "文件删除成功！");
			jsonObject.put("code", 0);
		} else {
			jsonObject.put("msg", "文件删除失败！");
		}
		return jsonObject.toJSONString();
	}

	// 新增单个文件
	@Override
	public String insertOne(MultipartFile multipartFile) throws Exception{
		JSONObject jsonObject = new JSONObject();
		// 文件根路径
		String dir = PropertiesUtil.getProperty("upload.file.dir");
		// 根据时间创建目录
		File filedir = new File(dir + File.separator + TimeUtil.getData());
		if (!filedir.exists()) {
			filedir.mkdirs();
		}
		// 获取原始名称
		String originalFilename = multipartFile.getOriginalFilename();

		// 文件后缀
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

		// 文件名称
		String filename = UUIDUtil.getUUID() + suffix;
		File file = new File(filedir.getPath().replace("\\", "/") + File.separator + filename);
		try {
			multipartFile.transferTo(file);// 文件写入磁盘
		} catch (Exception e) {
			log.error(originalFilename + "文件写入磁盘失败！", e);
			jsonObject.put("msg", "文件写入磁盘失败！"+e);
			return jsonObject.toJSONString();
		}

		UserFile userfile = new UserFile();
		userfile.setFileId(UUIDUtil.getUUID());//id
		userfile.setFileName(filename);//新名称
		userfile.setFilePath(file.getPath().replace("\\", "/"));//路径
		userfile.setFileOriginalName(originalFilename);//原始名称
		userfile.setUpdateTime(TimeUtil.getSystemTime());//修改日期
		int flag = fileMapper.insertOne(userfile);
		if (flag == 1) {
			jsonObject.put("msg", "文件上传成功！");
			jsonObject.put("code", 0);
		} else {
			jsonObject.put("msg", "文件上传失败！");
		}
		return jsonObject.toJSONString();

	}

	// 修改文件名称
	@Override
	public String updateByFileId(MultipartFile multipartFile) throws Exception{

		return null;
	}
	//查询文件列表
	@Override
	public String fileList(int limit, int offset, String fileOriginalName) throws Exception{
		
		List<UserFile> fileList=fileMapper.selectFileList(limit, offset, fileOriginalName);
		JSONArray array=JSONArray.parseArray(fileList.toString());
		int count=fileMapper.fileCount(limit, offset, fileOriginalName);
		return JsonData.toTableData(count, array);
	}

}
