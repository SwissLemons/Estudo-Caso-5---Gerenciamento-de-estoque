package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysql {
	//usuario senha e url para conexao
		private String usuario = "root";
		private String senha = "root";
		private String url = "jdbc:mysql://localhost:3306/Caso5";
		private Connection idConexao;
		
		public Connection getIdConexao() {
			return idConexao;
		}
		public void setIdConexao(Connection idConexao) {
			this.idConexao = idConexao;
		}
		//conecta ao mysql
		public void conectar() throws ClassNotFoundException, SQLException {
			//carregar driver(biblioteca)
			Class.forName("com.mysql.jdbc.Driver");
			//fazer a conecção:
			this.idConexao = DriverManager.getConnection(url, usuario, senha);
			//testar coneção:
			if(this.idConexao != null) {
				System.out.println("Conectado.");
			}
		}
		//desconecta para evitar uso exessivo de recursos
		public void desConectar() throws SQLException {
			if(this.idConexao != null) {
				System.out.println("Desconectado.");
				this.idConexao.close();
			}
		}
}
