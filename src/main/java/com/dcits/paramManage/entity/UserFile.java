package com.dcits.paramManage.entity;

public class UserFile {
    private String fileId;

    private String fileName;

    private String filePath;
    
    private String fileOriginalName;
    
    private String updateTime;
    
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

	public String getFileOriginalName() {
		return fileOriginalName;
	}

	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "{\"fileId\":\"" + fileId + "\",\"fileName\":\"" + fileName + "\",\"filePath\":\"" + filePath
				+ "\",\"fileOriginalName\":\"" + fileOriginalName + "\",\"updateTime\":\"" + updateTime + "\"}";
	}

	
}