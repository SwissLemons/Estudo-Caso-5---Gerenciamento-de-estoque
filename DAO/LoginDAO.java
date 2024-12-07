package DAO;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Usuario;

public class LoginDAO {
    public static boolean validarLogin(Usuario usuario) throws ClassNotFoundException, SQLException {
        ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();

        String sql = "SELECT * FROM pessoal WHERE nomePessoa = ? AND senhaPessoa = ?";
        PreparedStatement comando = conn.prepareStatement(sql);
        comando.setString(1, usuario.getNomeUsuario());
        comando.setString(2, usuario.getSenhaUsuario());
        
        ResultSet rs = comando.executeQuery();
        
        boolean isValid = rs.next();
        if (isValid) {
            byte[] fotoBytes = rs.getBytes("fotoPessoa");
            if (fotoBytes != null) {
                Image foto = Toolkit.getDefaultToolkit().createImage(fotoBytes);
                usuario.setFotoUsuario(foto);
            }
            int nivelAcesso = rs.getInt("permissaoPessoa");
            usuario.setNivelAcesso(nivelAcesso);
        } else {
            usuario.setNivelAcesso(1);
        }
        
        rs.close();
        comando.close();
        conexao.desConectar();
        
        return isValid;
    }
}