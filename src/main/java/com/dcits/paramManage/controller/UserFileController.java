package com.dcits.paramManage.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.service.UserFileService;

@Controller
@RequestMapping(value="/userFile")
public class UserFileController {
	
	@Autowired
	private UserFileService fileService;
	
	/**
	 * 上传文件
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/upload",produces = {"application/json;charset=UTF-8"})
	public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception{

		return fileService.insertOne(multipartFile);
	}
	
	/**
	 * 查询文件 列表
	 * @param limit
	 * @param page
	 * @param fileOriginalName
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/fileList",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public String fileList(int limit,int page,@RequestParam(required=false)String fileOriginalName) throws Exception{
		int offset=(page-1)*limit;//获取起始下标
		return fileService.fileList(limit, offset, fileOriginalName);
	}
	
	/**
	 * 删除文件
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("serial")
	@ResponseBody
	@RequestMapping(value="/delFile",method={RequestMethod.POST},produces = {"application/json;charset=UTF-8"})
	public String delFile(@RequestParam("fileId") String fileId) throws Exception{
		if(StringUtils.isBlank(fileId)){
			return new JSONObject(){
				{
					put("msg","未获取到渠道标识！");
				}
			}.toJSONString();
		}
		return fileService.deleteByFileId(fileId);
	}
}
