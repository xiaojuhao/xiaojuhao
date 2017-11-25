package com.xjh.dao.dataobject;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="wms_upload_files")
public class WmsUploadFilesDO extends PageDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
	Long id;
	String busiNo;
	String contentType;
	String fileLocation;
	String filePath;
	String fileName;
	String fileOriName;
	String remark;
	String creator;
	Date gmtCreated;

}
