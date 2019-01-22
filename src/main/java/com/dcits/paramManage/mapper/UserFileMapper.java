package com.dcits.paramManage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dcits.paramManage.entity.UserFile;

public interface UserFileMapper {

	UserFile selectByPrimaryKey(String fileId);

	/**
	 * 删除单个文件
	 * 
	 * @param fileId
	 * @return
	 */
	int deleteByFileId(String fileId);

	/**
	 * 上传单个文件
	 * 
	 * @param record
	 * @return
	 */
	int insertOne(UserFile userfile);

	/**
	 * 修改文件名称
	 * 
	 * @param record
	 * @return
	 */
	int updateByFileId(UserFile userfile);

	/**
	 * 查询文件列表
	 * @param limit
	 * @param offset 
	 * @param fileOriginalName  文件原始名称
	 * @return
	 */
	List<UserFile> selectFileList(@Param("limit") int limit, @Param("offset") int offset,
			@Param("fileOriginalName") String fileOriginalName);
	
	/**
	 * 查询文件数量
	 * @param limit
	 * @param offset
	 * @param fileOriginalName  文件原始名称
	 * @return
	 */
	int fileCount(@Param("limit") int limit, @Param("offset") int offset,
			@Param("fileOriginalName") String fileOriginalName);
	
	/**
	 * 查询文件信息
	 * @param fileId
	 * @return
	 */
	UserFile selectOne(String fileId);
}