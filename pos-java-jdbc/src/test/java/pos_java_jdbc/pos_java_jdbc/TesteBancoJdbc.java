package pos_java_jdbc.pos_java_jdbc;

import org.junit.Test;

import dao.UserPosDao;
import model.UserPosJava;

public class TesteBancoJdbc {

	@Test
	public void initBanco(){
		UserPosDao userPosDao = new UserPosDao();
		UserPosJava userPosJava = new UserPosJava();
		
		userPosJava.setId(6L);
		userPosJava.setNome("Paulo");
		userPosJava.setEmail("Paulo.cab@gmail.com");
		
		userPosDao.salvar(userPosJava);
	}
	
}
