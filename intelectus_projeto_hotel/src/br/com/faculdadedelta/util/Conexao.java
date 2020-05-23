package br.com.faculdadedelta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

	public static Connection conectarNoBancoDeDados() throws ClassNotFoundException, SQLException {

		Connection conn = null;

		String DRIVER = "org.postgresql.Driver";
		String URL = "jdbc:postgresql://localhost/hotel";
		String usuario = "postgres";
		String senha = "v130783mi";

		Class.forName(DRIVER);

		conn = DriverManager.getConnection(URL, usuario, senha);

		return conn;
	}

	public static void fecharConexao(PreparedStatement ps, Connection conn, ResultSet rs) throws SQLException {

		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public static void main(String[] args) {

		try {
			Conexao.conectarNoBancoDeDados();
			System.out.println("Conexao efetuada com sucesso !");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}