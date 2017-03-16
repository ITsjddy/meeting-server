package com.smartdot.meeting.server.modules.sample.rs.facade;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.rs.facade.CommonFacade;
import com.smartdot.meeting.server.modules.sample.rs.facade.model.req.RSSampleSaveReq;

import io.swagger.annotations.ApiOperation;

@Path(value = "/sample")
public interface ISampleFacade extends CommonFacade {

	public static final String FACADE_NAME = "sampleFacade";

	@POST
	@Path(value = "/save")
	@Descriptions({ 
		   @Description(value = "Adds a SampleEntity for test", target = DocTarget.METHOD),
		   @Description(value = "返回结果集", target = DocTarget.RETURN),
		   @Description(value = "请求sample模型", target = DocTarget.REQUEST),
		   @Description(value = "返回是否成功", target = DocTarget.RESPONSE),
		   @Description(value = "Resource", target = DocTarget.RESOURCE)
		})
	@ApiOperation(value="保存")
	public ReturnValue save(RSSampleSaveReq req) throws Exception;
	
}
