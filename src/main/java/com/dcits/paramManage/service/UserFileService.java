package com.dcits.paramManage.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserFileService {
	
    /**
     * 删除单个文件
     * @param fileId
     * @return
     */
    String deleteByFileId(String fileId) throws Exception;
    
    /**
     * 上传单个文件
     * @param record
     * @return
     */
    String insertOne(MultipartFile multipartFile) throws Exception;
    
    /**
     * 修改文件名称
     * @param record
     * @return
     */
    String updateByFileId(MultipartFile multipartFile) throws Exception;
    
    /**
	 * 查询文件列表
	 * @param limit
	 * @param offset 
	 * @param fileOriginalName  文件原始名称
	 * @return
	 */
	String fileList(int limit, int offset,String fileOriginalName) throws Exception;
	
}
