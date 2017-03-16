#set ($domain = $!domainName.substring(0,1).toLowerCase()+$!domainName.substring(1))
package $!{packageName}.modules.$!{floder}.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.$!{floder}.model.$!{domainName}Form;
import com.smartdot.meeting.server.modules.$!{floder}.model.$!{domainName}PageForm;
import com.smartdot.meeting.server.modules.$!{floder}.model.$!{domainName}VO;
import com.smartdot.meeting.server.modules.$!{floder}.service.I$!{domainName}Service;
import com.smartdot.meeting.server.common.entity.$!{domainName};


@Controller
@RequestMapping(value = "/$!{domain}")
public class $!{domainName}Controller {

	private static final Logger _LOG = Logger.getLogger($!{domainName}Controller.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = I$!{domainName}Service.SERVICE_NAME)
	private I$!{domainName}Service $!{domain}Service;

	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "$!{domain}/list";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "$!{domain}/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "$!{domain}/edit";
	}

	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "$!{domain}/details";
	}
	
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<$!{domainName}VO> queryAll() throws Exception {
		List<$!{domainName}> $!{domain}s = $!{domain}Service.findAll();
		List<$!{domainName}VO> vo = new ArrayList<$!{domainName}VO>();
		$!{domainName}VO $!{domain}VO = null;
		for ($!{domainName} $!{domain} : $!{domain}s) {
			$!{domain}VO  = new $!{domainName}VO();
			BeanUtils.copyProperties($!{domain}, $!{domain}VO);
			vo.add($!{domain}VO);
		}
		return vo;
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save($!{domainName}Form form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		$!{domainName} $!{domain}= new $!{domainName}();
		BeanUtils.copyProperties(form, $!{domain});
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = $!{domain}Service.save($!{domain});
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage($!{domainName}Controller.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<$!{domainName}Form> form) throws Exception {
	List<$!{domainName}> $!{domain}s=new ArrayList<$!{domainName}>();
		ReturnValue returnValue =new ReturnValue();
		$!{domainName} entity$!{domainName} = new $!{domainName}();
		for ($!{domainName}Form $!{domain} : form) {
			entity$!{domainName}=new $!{domainName}();
			BeanUtils.copyProperties($!{domain}, entity$!{domainName});
			$!{domain}s.add(entity$!{domainName});
		}
		boolean result = $!{domain}Service.saveAll($!{domain}s);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage($!{domainName}Controller.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = $!{domain}Service.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage($!{domainName}Controller.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = true;
		if(null != ids && ids.length > 0){
			result = $!{domain}Service.deleteAll(ids);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage($!{domainName}Controller.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update($!{domainName}Form form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		$!{domainName} $!{domain} = new $!{domainName}();
		BeanUtils.copyProperties(form, $!{domain});
		if (StringUtils.isNotEmpty(form.getId())) {
			result = $!{domain}Service.update$!{domainName}($!{domain});
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage($!{domainName}Controller.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	$!{domainName}VO queryById(String id) throws Exception {
		$!{domainName}VO vo = new $!{domainName}VO();
		$!{domainName} $!{domain} = $!{domain}Service.get$!{domainName}ById(id);
		if ($!{domain} != null) {
			BeanUtils.copyProperties($!{domain}, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, $!{domainName}PageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass($!{domainName}.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.desc("updateTimes"));
		
		return $!{domain}Service.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
}
