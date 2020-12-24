
package inventory.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.RoleDAO;
import inventory.dao.UserDAO;
import inventory.dao.UserRoleDAO;
import inventory.model.Role;


@Service
public class RoleService {
//	final static Logger log = Logger.getLogger(UserService.class);
	@Autowired
	private RoleDAO<Role> roleDAO;
	
	public void saveRole(Role role) {
		role.setActiveFlag(1);
		role.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		role.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		role.setCreateDate(role.getCreateDate());
		role.setUpdateDate(role.getUpdateDate());
		roleDAO.save(role);
	}
	public void updateRole(Role role) {
		role.setUpdateDate(role.getUpdateDate());
		roleDAO.update(role);
	}
	public void deleteRole(Role role) {
		role.setActiveFlag(0);
		role.setUpdateDate(role.getUpdateDate());
		roleDAO.update(role);
	}
	
	public List<Role> findByProperty(String property , Object value) {
//		log.info("Find user by property start ");
		return roleDAO.findByProperty(property, value);

	}
	
	public Role findByIdURole(int id) {
		return roleDAO.findById(Role.class, id);
	}
	public List<Role> getAllRole(Role role){
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(role!=null) {
			if(role.getId()!=0) {
				queryStr.append(" and model.id=:id");
				mapParams.put("id", role.getId());
			}
		}
		return roleDAO.findAll(queryStr.toString(), mapParams);
	}

//	public List<Users> findAll()
//	{
//		return userDAO.findAll(null, null);
//	}
}