/**
 * Dao 包含DAO层操作数据库的基本方法.
 */
package com.smartdot.meeting.server.common.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;

/**
 * Dao 包含DAO层操作数据库的基本方法.
 * 
 * @author gm
 * 
 */
public interface GenericDao<T> {
	/**
	 * 删除一个Bean对象.
	 * 
	 * @param o 实体对象
	 */
	void delete(T o);

	/**
	 * 删除一个集合内的所有对象
	 * 
	 * @param list 对象集合
	 */
	void deleteAll(Collection<T> list);

	/**
	 * 根据对象id删除对象.
	 * 
	 * @param id 实体对象id
	 */
	void deleteById(Serializable id);

	/**
	 * 执行SQL语句获得Set结果,此SQL语句非HQL语句.
	 * 
	 * @param sql 基本sql语句
	 * 
	 * @return Set集合
	 */
	ResultSet executeQuery(final String sql) throws Exception;

	/**
	 * 查询所有信息.
	 * 
	 * @return 实体Bean List
	 */
	List<T> findAll();

	/**
	 * 根据某些属性条件查询.
	 * 
	 * @param map 存放属性name-value 相对应的条件集.
	 * @return 根据条件获得的所有数据集
	 */
	List<T> findAllByProperties(Map<String, Object> map);
	
	List<T> findAllByProperties(Map<String, Object> map,LinkedHashMap<String, String> orderby);

	/**
	 * 根据属性名值进行查询.
	 * 
	 * @param propertyName 属性名字：如id，username。。。
	 * @param propertyValue 属性值： 如11，'admin'。。。
	 * @return 根据条件获得的所有数据集
	 */
	List<T> findAllByProperty(String propertyName, Object propertyValue);

	/**
	 * 依靠当前实体的id查找该实体.
	 * 
	 * @param idValue 主键值
	 * @return 该id实体对象
	 */
	T findById(Serializable idValue);

	/**
	 * 新增一条信息.
	 * 
	 */
	Serializable save(T value);

	/**
	 * 保存一组对象集合.
	 * 
	 * @param coll 对象集合
	 * 
	 */
	void saveAll(Collection<T> coll);

	/**
	 * 修改一条信息.
	 */
	void update(T value);

	/**
	 * 新增或修改一条信息.
	 */
	void saveOrUpdate(T o);
	
	List<T> findByHQL(String hql);
	
	List<T> findByHQLAndParams(String hql,Object... params);
	
	/**
	 * 从缓存中根据ID查询对象，如缓存中不存在则返回该代理对象
	 * 
	 * @param idValue
	 * 
	 * @return
	 */
	public T loadById(Serializable idValue);
	
	
	public Session getSession();

	
	public int executeHQL(String hql, Object... params);
	
	public List<T> findByHQLWithFilterAndParams(String hql, String filter, String fpName, Object fpVal, Object... params);
	
	/**
	 * Criteria 单表映射查询
	 * 
	 * @param detachedCriteria
	 * 
	 * @return List<对象>
	 */
	public List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria);
	
	public T get(Serializable id);

	/**
	 * 根据属性名值进行查询.
	 * 
	 * @param propertyName 属性名字：如id，username。。。
	 * @param propertyValue 属性值： 如11，'admin'。。。
	 * @return 获得符合条件的数据的条数
	 */
	int findColumnCount(String propertyName, Object propertyValue);

	/**
	 * 根据条件查询某个Bean.
	 * 
	 * @param strings 可以传个数为n的任意数量参数。名值之间用英文的::=分开 例selectBSql("id::=11","name::='myName'");
	 * @return 返回find(from 表 where id=11 and name='myName')的结果
	 */
	List<T> selectBySqlCondition(String... strings);

	/**
	 * 根据条件查询某个Bean.
	 * 
	 * @param strings 可以传个数为n的任意数量参数。名值之间用英文的::=分开 例selectBSql("id::=11","name::='myName'");
	 * @return 返回find(from 表 where id=11 or name='myName')的结果
	 */
	List<T> selectBySqlConditionOr(String... strings);

	
	/**--------------------------------------- 分页查询 -----------------------------------*/
	
	/**
	 * 分页查询.
	 * 
	 * @param page page对象
	 * @param countHql 查询总条数
	 * @param resultHql 查询结果集
	 * @param params 总条数参数列表
	 * @param params 结果集参数列表
	 * @return Map集合
	 */
	public Map<String, Object> pageQueryByHql(Page<T> page,final String countHql,final String resultHql,Object[] countParams,Object[] resultParams);
	
	public Map<String, Object> pageQueryByHqlWithFilter(final String filter, final String fpName, Object fpVal, Page<T> page,final String countHql,final String resultHql,Object[] countParams,Object[] resultParams);
	
	/**
	 * 分页查询.
	 * 
	 * @param map 条件map
	 * @param page page对象
	 * @param orderby 排序
	 * @return Map集合
	 */
	public Map<String, Object> pageQuery(Map<String, Object> map, Page<T> page, LinkedHashMap<String, String> orderby);

	/**
	 * 分页查询.
	 * 
	 * @param map 条件map
	 * @param page page对象
	 * @param orderby 排序
	 * @param like 是否模糊搜索
	 * @return Map集合
	 */
	public Map<String, Object> pageQuery(Map<String, Object> map, Page<T> page, LinkedHashMap<String, String> orderby, boolean like);

	/**
	 * 分页查询.
	 * 
	 * @param map 条件map
	 * @param page page对象
	 * @param like 是否模糊搜索
	 * @return Map集合
	 */
	public Map<String, Object> pageQuery(Map<String, Object> map, Page<T> page, boolean like);

	/**
	 * 分页查询.
	 * 
	 * @param map 条件map
	 * @param page page对象
	 * @return Map集合
	 */
	public Map<String, Object> pageQuery(Map<String, Object> map, Page<T> page);

	/**
	 * 分页查询.
	 * 
	 * @param page page对象
	 * @param orderby 排序
	 * @return Map集合
	 */
	public Map<String, Object> pageQuery(Page<T> page, LinkedHashMap<String, String> orderby);

	/**
	 * 分页查询.
	 * 
	 * @param page page对象
	 * @return Map集合
	 */
	public Map<String, Object> pageQuery(Page<T> page);
	
	/**
	 * 分页查询.
	 * 
	 * @param detachedCriteria DetachedCriteria集合
	 * @param page page对象
	 * @return Map集合
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<T> page);
	
	
}
