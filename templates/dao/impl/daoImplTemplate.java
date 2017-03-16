#set ($domain = $!domainName.substring(0,1).toLowerCase()+$!domainName.substring(1))package $!{packageName}.modules.$!{floder}.dao.impl;

import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.$!{floder}.dao.I$!{domainName}Dao;
import com.smartdot.meeting.server.common.entity.$!{domainName};
@Repository(value = I$!{domainName}Dao.DAO_NAME)
public class $!{domainName}DaoImpl extends GenericDaoHibernateSupport<$!{domainName}> implements I$!{domainName}Dao {

}
