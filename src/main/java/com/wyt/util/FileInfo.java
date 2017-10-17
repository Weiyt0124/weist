package com.wyt.util;

import java.util.Date;

/**
 * 文件信息对象
 * @author hc.L
 */
public class FileInfo {

	/**
	 * id(作为实际文件名称，唯一性)
	 */
	private String id;
	/**
	 * 文件保存路径
	 */
	private String depositUrl;

	/**
	 * 文件名（显示名称，不包含文件格式）
	 */
	private String fileName;
	/**
	 * 文件大小
	 */
	private Long size;
	/**
	 * 文件详情
	 */
	private String detail;
	/**
	 * 上传时间
	 */
	private Date upDate;
	/**
	 * 下载次数
	 */
	private Integer downNum;
	/**
	 * 标签
	 */
	private String tag;
	
	/**
	 * 文件格式
	 */
	private String fileFormat;
	
	
	
	
	
	public FileInfo() {
		super();
	}


	public FileInfo(String id, String depositUrl, String fileName, String fileFormat,
                    Long size, String detail, String tag) {
		super();
		this.id=id;
		this.depositUrl = depositUrl;
		this.fileName = fileName;
		this.fileFormat=fileFormat;
		this.size = size;
		this.detail = detail;
		this.upDate =new Date();
		this.tag = tag;
	}
	
	
	public String getDepositUrl() {
		return depositUrl;
	}
	public void setDepositUrl(String depositUrl) {
		this.depositUrl = depositUrl;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	public Integer getDownNum() {
		return downNum;
	}
	public void setDownNum(Integer downNum) {
		this.downNum = downNum;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}


	public String getFileFormat() {
		return fileFormat;
	}


	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	
	
	
}
