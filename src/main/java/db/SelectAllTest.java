package db;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import db.ApplicationConfig;
import db.RoleDao;
import db.Role;

public class SelectAllTest {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		RoleDao roleDao = ac.getBean(RoleDao.class);
		
		List<Role> list = roleDao.selectAll();
		for (Role role:list) {
			System.out.print(role);
		}
	}
}
