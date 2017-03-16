package com.smartdot.meeting.server.common.util;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;

public class GBKOrder extends Order {
	/**
	 * <p>
	 * <pre>
	 * <b>属性描述：</b>
	 * 	汉字升序排列
	 * </pre>
	 * </p>
	 */
	private static final long serialVersionUID = 2911918143422454789L;
	private String propertyName;

	protected GBKOrder(String propertyName) {
		super(propertyName, false);
		this.propertyName = propertyName;
	}

	/**
	 * 只考虑按一个字段排序的情况
	 */
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery)
			throws HibernateException {
		String[] columns = criteriaQuery.getColumnsUsingProjection(criteria,
				propertyName);
		return " CONVERT( " + columns[0] + " USING GBK) ";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	按照汉字排序（ASC）
	 * <b>作者：</b>
	 *	孙俊东
	 * <b>创建时间：</b> 
	 * 	2016年9月22日 上午10:37:56
	 * </pre>
	 * </p>
	 */
	public static GBKOrder getOrder(String propertyName) {
		return new GBKOrder(propertyName);
	}
}
