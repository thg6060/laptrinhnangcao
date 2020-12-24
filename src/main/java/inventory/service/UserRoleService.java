package inventory.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import inventory.dao.UserRoleDAO;
import inventory.model.UserRole;


@Service
public class UserRoleService {
//	final static Logger log = Logger.getLogger(UserService.class);
	@Autowired
	private UserRoleDAO<UserRole> userRoleDAO;
	
	public void saveUserRole(UserRole ur) {
		ur.setActiveFlag(1);
		ur.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		ur.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		ur.setCreateDate(ur.getCreateDate());
		ur.setUpdateDate(ur.getUpdateDate());
		userRoleDAO.save(ur);
	}
	public void updateUserRole(UserRole ur) {
		ur.setUpdateDate(ur.getUpdateDate());
		userRoleDAO.update(ur);
	}
	public void deleteUserRole(UserRole ur) {
		userRoleDAO.delete(ur);
	}
	
	public List<UserRole> findByProperty(String property , Object value) {
//		log.info("Find user by property start ");
		return userRoleDAO.findByProperty(property, value);

	}
	
	public UserRole findByIdUserRole(int id) {
		return userRoleDAO.findById(UserRole.class, id);
	}
	public List<UserRole> getAllUserRole(UserRole ur){
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(ur!=null) {
			if(ur.getId()!=0) {
				queryStr.append(" and model.id=:id");
				mapParams.put("id", ur.getId());
			}
		}
		return userRoleDAO.findAll(queryStr.toString(), mapParams);
	}

//	public List<Users> findAll()
//	{
//		return userDAO.findAll(null, null);
//	}
}