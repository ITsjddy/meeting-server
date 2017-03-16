#set ($domain = $!domainName.substring(0,1).toLowerCase()+$!domainName.substring(1))package $!{packageName}.modules.$!{floder}.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.$!{floder}.dao.I$!{domainName}Dao;
import com.smartdot.meeting.server.modules.$!{floder}.service.I$!{domainName}Service;
import com.smartdot.meeting.server.common.entity.$!{domainName};
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = I$!{domainName}Service.SERVICE_NAME)
public class $!{domainName}ServiceImpl implements I$!{domainName}Service {
	
	@Resource(name = I$!{domainName}Dao.DAO_NAME)
	private I$!{domainName}Dao $!{domain}Dao;

	@Override
	public List<$!{domainName}> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass($!{domainName}.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return $!{domain}Dao.findByDetachedCriteria(dc);
	}

	@Override
	public $!{domainName} get$!{domainName}ById(String id) {
		
		return $!{domain}Dao.findById(id);
	}

	@Audit(module= "$!{domainName}管理",action= "$!{domainName}信息添加",description= "$!{domainName}信息添加说明")
	@Override
	public boolean save($!{domainName} $!{domain}) {
		boolean flag = false;
		if ($!{domain} != null) {
			$!{domain}Dao.save($!{domain});
			flag = true;
		}
		return flag;
	}

	@Audit(module= "$!{domainName}管理",action= "$!{domainName}多条信息添加",description= "$!{domainName}多条信息添加说明")
	@Override
	public boolean saveAll(List<$!{domainName}> $!{domain}List) {
		boolean flag = false;
		if ($!{domain}List != null) {
			$!{domain}Dao.saveAll($!{domain}List);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "$!{domainName}管理",action= "$!{domainName}信息修改",description= "$!{domainName}信息修改说明")
	@Override
	public boolean update$!{domainName}($!{domainName} $!{domain}) {
		boolean flag = false;
		if ($!{domain} != null) {
			$!{domain}Dao.update($!{domain});
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove($!{domainName} $!{domain}) {
		boolean flag = false;
		if ($!{domain} != null) {
			$!{domainName} instance = this.get$!{domainName}ById($!{domain}.getId());
			$!{domain}Dao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "$!{domainName}管理",action= "$!{domainName}多选信息删除",description= "$!{domainName}多选信息删除说明")
	@Override
	public boolean deleteAll(String[] ids) {
		boolean flag = false;
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				this.deleteById(id);
			}
			flag = true;
		}
		return flag;
	}
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<$!{domainName}> pageQuery) {
		
		return $!{domain}Dao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "$!{domainName}管理",action= "$!{domainName}信息删除",description= "$!{domainName}信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				$!{domainName} $!{domain} = $!{domain}Dao.get(id);
				$!{domain}.setEnable(false);
				$!{domain}Dao.update($!{domain});
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<$!{domainName}> findByHQLAndParams($!{domainName} $!{domain}) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<$!{domainName}> sList = $!{domain}Dao
				.findByHQLAndParams("from $!{domainName} where enable = true","");
		return sList;
	}
	
}
