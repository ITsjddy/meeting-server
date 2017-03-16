package com.smartdot.meeting.server.modules.importtemplate.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.importtemplate.dao.IImportTemplateDao;
import com.smartdot.meeting.server.modules.importtemplate.service.IImportTemplateService;
import com.smartdot.meeting.server.common.entity.ImportTemplate;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IImportTemplateService.SERVICE_NAME)
public class ImportTemplateServiceImpl implements IImportTemplateService {
	
	@Resource(name = IImportTemplateDao.DAO_NAME)
	private IImportTemplateDao importTemplateDao;

	@Override
	public List<ImportTemplate> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(ImportTemplate.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return importTemplateDao.findByDetachedCriteria(dc);
	}

	@Override
	public ImportTemplate getImportTemplateById(String id) {
		
		return importTemplateDao.findById(id);
	}

	@Audit(module= "ImportTemplate管理",action= "ImportTemplate信息添加",description= "ImportTemplate信息添加说明")
	@Override
	public boolean save(ImportTemplate importTemplate) {
		boolean flag = false;
		if (importTemplate != null) {
			importTemplateDao.save(importTemplate);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "ImportTemplate管理",action= "ImportTemplate多条信息添加",description= "ImportTemplate多条信息添加说明")
	@Override
	public boolean saveAll(List<ImportTemplate> importTemplateList) {
		boolean flag = false;
		if (importTemplateList != null) {
			importTemplateDao.saveAll(importTemplateList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "ImportTemplate管理",action= "ImportTemplate信息修改",description= "ImportTemplate信息修改说明")
	@Override
	public boolean updateImportTemplate(ImportTemplate importTemplate) {
		boolean flag = false;
		if (importTemplate != null) {
			importTemplateDao.update(importTemplate);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(ImportTemplate importTemplate) {
		boolean flag = false;
		if (importTemplate != null) {
			ImportTemplate instance = this.getImportTemplateById(importTemplate.getId());
			importTemplateDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				this.deleteById(id);
			}
			flag = true;
		}
		return flag;
	}
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<ImportTemplate> pageQuery) {
		
		return importTemplateDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "ImportTemplate管理",action= "ImportTemplate信息删除",description= "ImportTemplate信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				ImportTemplate importTemplate = importTemplateDao.get(id);
				importTemplate.setEnable(false);
				importTemplateDao.update(importTemplate);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<ImportTemplate> findByHQLAndParams(ImportTemplate importTemplate) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<ImportTemplate> sList = importTemplateDao
				.findByHQLAndParams("from ImportTemplate where enable = true","");
		return sList;
	}
	
}
