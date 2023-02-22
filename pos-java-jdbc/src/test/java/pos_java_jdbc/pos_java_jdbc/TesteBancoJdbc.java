package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDao;
import model.Telefone;
import model.UserPosJava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		UserPosDao userPosDao = new UserPosDao();

		UserPosJava userPosJava = new UserPosJava();

		userPosJava.setNome("Paulo");
		userPosJava.setEmail("Paulo.cab@gmail.com");

		userPosDao.salvar(userPosJava);
	}

	@Test
	public void initListar() {
		UserPosDao dao = new UserPosDao();
		try {
			List<UserPosJava> list = dao.listar();

			for (UserPosJava userPosJava : list) {
				System.out.println(userPosJava.toString());
				System.out.println("--------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initBuscar() {
		UserPosDao dao = new UserPosDao();
		try {
			UserPosJava userPosJava = dao.buscar(4L);
			System.out.println(userPosJava);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initAtualizar() {
		try {
			UserPosDao dao = new UserPosDao();

			UserPosJava objetoBanco = dao.buscar(5L);
			objetoBanco.setNome("ATT: Clifford");
			dao.atualizar(objetoBanco);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initDeletar() {
		try {
			UserPosDao dao = new UserPosDao();
			dao.deletar(0L);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeInsertTelefone() {
		Telefone telefone = new Telefone();
		telefone.setNumero("83 9 8632-5573");
		telefone.setTipo("Pessoal");
		telefone.setUsuario(15L);
		
		UserPosDao dao = new UserPosDao();
		dao.salvarTelefone(telefone);
		
		
		
	}

}
