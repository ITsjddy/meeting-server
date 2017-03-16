#set ($domain = $!domainName.substring(0,1).toLowerCase()+$!domainName.substring(1))package $!{packageName}.modules.$!{floder}.service;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.$!{domainName};

public interface I$!{domainName}Service {
	public final static String SERVICE_NAME = "$!{domain}Service";

	/**
	 * 全部查看$!{domainName}
	 * 
	 * @return List
	 */
	public List<$!{domainName}> findAll();

	/**
	 * 根据ID查找$!{domainName}
	 * 
	 * @param id
	 *            可以根据ID查看$!{domainName}中的字段
	 * @return $!{domainName}
	 */
	public $!{domainName} get$!{domainName}ById(String id);

	/**
	 * 添加$!{domainName}
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save($!{domainName} instance);
	
	/**
	 * 添加多个$!{domainName}对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<$!{domainName}> instanceList);

	/**
	 * 修改$!{domainName}
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean update$!{domainName}($!{domainName} instance);

	/**
	 * 删除$!{domainName}
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove($!{domainName} instance);
	
	/**
	 * 删除$!{domainName}
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);

	/**
	 * $!{domainName}分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<$!{domainName}> pageQuery);

	/**
	 * $!{domainName}根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * $!{domainName}根据条件查询列表;
	 * 
	 * @param $!{domain}
	 * @return
	 */
	public List<$!{domainName}> findByHQLAndParams($!{domainName} $!{domain});
}
