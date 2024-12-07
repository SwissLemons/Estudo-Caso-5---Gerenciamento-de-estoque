package DAO;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Usuario;
import Model.Utilidades;

public class CadastroDAO {
	public static boolean conectarCadastro(Usuario usuario) throws ClassNotFoundException, SQLException {
		ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();
        
        String sql = "INSERT INTO pessoal (nomePessoa, senhaPessoa, fotoPessoa, permissaoPessoa) VALUES (?, ?, ?, ?)";
        PreparedStatement comando = conn.prepareStatement(sql);
        comando.setString(1, usuario.getNomeUsuario());
        comando.setString(2, usuario.getSenhaUsuario());
        
        byte[] imageBytes = Utilidades.convertImageToBytes(usuario.getFotoUsuario());
        if (imageBytes != null) {
            Blob blob = conn.createBlob();
            blob.setBytes(1, imageBytes);
            comando.setBlob(3, blob);
        } else {
            comando.setNull(3, java.sql.Types.BLOB);
        }
        comando.setInt(4, usuario.getNivelAcesso());
        
        int rowsInserted = comando.executeUpdate();

        comando.close();
        conexao.desConectar();
        
        return rowsInserted > 0;
	}
}