package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoJdbc.SingleConnection;
import model.Telefone;
import model.UserPosJava;

public class UserPosDao {

	private Connection connection;

	public UserPosDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserPosJava userPosJava) {
		try {
			String sql = "insert into userposjava (nome, email) values (?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userPosJava.getNome());
			insert.setString(2, userPosJava.getEmail());
			insert.execute();
			connection.commit(); // Salva no banco de dados - SQL

		} catch (Exception e) {
			try {
				connection.rollback(); // Reverte operação
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void salvarTelefone(Telefone telefone) {
		
		try {
			
			String sql = "INSERT INTO public.telefoneuser(numero, tipo, usuariopessoa) VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public List<UserPosJava> listar() throws Exception {
		List<UserPosJava> list = new ArrayList<UserPosJava>();

		String sql = "select * from userposjava";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			UserPosJava userPosJava = new UserPosJava();
			userPosJava.setId(resultado.getLong("id"));
			userPosJava.setNome(resultado.getString("nome"));
			userPosJava.setEmail(resultado.getString("email"));
			list.add(userPosJava);

		}

		return list;
	}

	public UserPosJava buscar(Long id) throws Exception {
		UserPosJava retorno = new UserPosJava();

		String sql = "select * from userposjava where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
		}

		return retorno;
	}

	public void atualizar(UserPosJava userPosJava) {
		try {
			String sql = "update userposjava set nome = ? where id = " + userPosJava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userPosJava.getNome());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void deletar(Long id) {
	try {
		
		String sql = "delete from userposjava where id = " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.execute();
		
		connection.commit();
	} catch (Exception e) {
		try {
			connection.rollback();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		e.printStackTrace();
	}	
	}
	
	
}
