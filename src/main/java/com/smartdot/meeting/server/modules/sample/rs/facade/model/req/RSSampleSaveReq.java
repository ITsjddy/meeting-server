package com.smartdot.meeting.server.modules.sample.rs.facade.model.req;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Sample添加请求模型")
@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class RSSampleSaveReq {

	@ApiModelProperty(value="名称",required=true)
	@Description(value = "名称", target = DocTarget.REQUEST)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
