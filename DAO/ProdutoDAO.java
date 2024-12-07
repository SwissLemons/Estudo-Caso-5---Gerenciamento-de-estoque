package DAO;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Produto;
import Model.ProdutoControlesAR;
import Model.ProdutoControlesTV;
import Model.Utilidades;
import View.TelaPrincipal;

public class ProdutoDAO {
	public static boolean inserirControleTV(Produto produto, ProdutoControlesTV controlesTV) throws ClassNotFoundException, SQLException {
		ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();
		
		String sql = "insert into controlesTV (id, codigo, preco, quantidadeEstoque, classeControleTV, MarcaPrincipalControleTV, MarcaExtra1ControleTV, MarcaExtra2ControleTV, MarcaExtra3ControleTV, MarcaExtra4ControleTV, ModeloControleTV, fotoControleTV) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement comando = conn.prepareStatement(sql);
		comando.setInt(1, produto.getId());
		comando.setString(2, produto.getCodigo());
		comando.setDouble(3, produto.getPreco());
		comando.setInt(4, produto.getQuantidadeEstoque());
		comando.setString(5, controlesTV.getClasseControleTV());
		comando.setString(6, controlesTV.getPrincipalControleTV());
		comando.setString(7, controlesTV.getMarcaExtra1ControleTV());
		comando.setString(8, controlesTV.getMarcaExtra2ControleTV());
		comando.setString(9, controlesTV.getMarcaExtra3ControleTV());
		comando.setString(10, controlesTV.getMarcaExtra4ControleTV());
		comando.setString(11, controlesTV.getModeloControleTV());
		
		byte[] imageBytes = Utilidades.convertImageToBytes(controlesTV.getFotoControleTV());
        if (imageBytes != null) {
            Blob blob = conn.createBlob();
            blob.setBytes(1, imageBytes);
            comando.setBlob(12, blob);
        } else {
            comando.setNull(12, java.sql.Types.BLOB);
        }
        
        int rowsInserted = comando.executeUpdate();

        comando.close();
        conexao.desConectar();
        
        return rowsInserted > 0;
	}
	
	public static boolean inserirControleAR(Produto produto, ProdutoControlesAR controlesAR) throws ClassNotFoundException, SQLException {
		ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();
		
		String sql = "insert into controlesAR (id, codigo, preco, quantidadeEstoque, classeControleAR, MarcaPrincipalControleAR, MarcaExtra1ControleAR, MarcaExtra2ControleAR, MarcaExtra3ControleAR, MarcaExtra4ControleAR, ModeloControleAR, fotoControleAR) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement comando = conn.prepareStatement(sql);
		comando.setInt(1, produto.getId());
		comando.setString(2, produto.getCodigo());
		comando.setDouble(3, produto.getPreco());
		comando.setInt(4, produto.getQuantidadeEstoque());
		comando.setString(5, controlesAR.getClasseControleAR());
		comando.setString(6, controlesAR.getPrincipalControleAR());
		comando.setString(7, controlesAR.getMarcaExtra1ControleAR());
		comando.setString(8, controlesAR.getMarcaExtra2ControleAR());
		comando.setString(9, controlesAR.getMarcaExtra3ControleAR());
		comando.setString(10, controlesAR.getMarcaExtra4ControleAR());
		comando.setString(11, controlesAR.getModeloControleAR());
		
		byte[] imageBytes = Utilidades.convertImageToBytes(controlesAR.getFotoControleAR());
        if (imageBytes != null) {
            Blob blob = conn.createBlob();
            blob.setBytes(1, imageBytes);
            comando.setBlob(12, blob);
        } else {
            comando.setNull(12, java.sql.Types.BLOB);
        }
        
        int rowsInserted = comando.executeUpdate();

        comando.close();
        conexao.desConectar();
        
        return rowsInserted > 0;
	}
	
	public static List<String> buscarMarcasControlesTV() throws ClassNotFoundException, SQLException {
        ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();
        
        String sql = "SELECT marca FROM marcasControlesTV";
        PreparedStatement comando = conn.prepareStatement(sql);
        ResultSet resultado = comando.executeQuery();
        
        List<String> marcas = new ArrayList<>();
        while (resultado.next()) {
            marcas.add(resultado.getString("marca"));
        }
        
        resultado.close();
        comando.close();
        conexao.desConectar();
        
        return marcas;
    }
	
	public static List<String> buscarMarcasControlesAR() throws ClassNotFoundException, SQLException{
		ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();
        
        String sql = "SELECT marca FROM marcasControlesAR";
        PreparedStatement comando = conn.prepareStatement(sql);
        ResultSet resultado = comando.executeQuery();
        
        List<String> marcas = new ArrayList<>();
        while (resultado.next()) {
            marcas.add(resultado.getString("marca"));
        }
        
        resultado.close();
        comando.close();
        conexao.desConectar();
        
        return marcas;
	}
	
	public static ArrayList<ProdutoControlesTV> tabelaControlesTV() throws ClassNotFoundException, SQLException {
		ConexaoMysql conexao = new ConexaoMysql();
	    conexao.conectar();
	    Connection conn = conexao.getIdConexao();
	    
	    String sql = "SELECT * FROM controlesTV";
	    PreparedStatement comando = conn.prepareStatement(sql);
	    ResultSet resultado = comando.executeQuery();
	    
	    ArrayList<ProdutoControlesTV> produtos = new ArrayList<>();
	    while (resultado.next()) {
	        ProdutoControlesTV produto = new ProdutoControlesTV();
	        produto.setId(resultado.getInt(1));
	        produto.setCodigo(resultado.getString(2));
	        produto.setPreco(resultado.getDouble(3));
	        produto.setQuantidadeEstoque(resultado.getInt(4));
	        produto.setClasseControleTV(resultado.getString(5));
	        produto.setPrincipalControleTV(resultado.getString(6));
	        produto.setMarcaExtra1ControleTV(resultado.getString(7));
	        produto.setMarcaExtra2ControleTV(resultado.getString(8));
	        produto.setMarcaExtra3ControleTV(resultado.getString(9));
	        produto.setMarcaExtra4ControleTV(resultado.getString(10));
	        produto.setModeloControleTV(resultado.getString(11));
	        
	        //boolean isValid = resultado.next();
	        //if (isValid) {
	        //    byte[] fotoBytes = resultado.getBytes(12);
	        //    if (fotoBytes != null) {
	        //        Image foto = Toolkit.getDefaultToolkit().createImage(fotoBytes);
	        //        produto.setFotoControleTV(foto);
	        //    }
	        //}
	        produtos.add(produto);
	    }
	    resultado.close();
	    comando.close();
	    conexao.desConectar();
	    
	    return produtos;
	}
	
	public static ArrayList<ProdutoControlesAR> tabelaControlesAR() throws ClassNotFoundException, SQLException {
		ConexaoMysql conexao = new ConexaoMysql();
	    conexao.conectar();
	    Connection conn = conexao.getIdConexao();
	    
	    String sql = "SELECT * FROM controlesAR";
	    PreparedStatement comando = conn.prepareStatement(sql);
	    ResultSet resultado = comando.executeQuery();
	    
	    ArrayList<ProdutoControlesAR> produtos = new ArrayList<>();
	    while (resultado.next()) {
	        ProdutoControlesAR produto = new ProdutoControlesAR();
	        produto.setId(resultado.getInt(1));
	        produto.setCodigo(resultado.getString(2));
	        produto.setPreco(resultado.getDouble(3));
	        produto.setQuantidadeEstoque(resultado.getInt(4));
	        produto.setClasseControleAR(resultado.getString(5));
	        produto.setPrincipalControleAR(resultado.getString(6));
	        produto.setMarcaExtra1ControleAR(resultado.getString(7));
	        produto.setMarcaExtra2ControleAR(resultado.getString(8));
	        produto.setMarcaExtra3ControleAR(resultado.getString(9));
	        produto.setMarcaExtra4ControleAR(resultado.getString(10));
	        produto.setModeloControleAR(resultado.getString(11));
	        
	        //boolean isValid = resultado.next();
	        //if (isValid) {
	        //    byte[] fotoBytes = resultado.getBytes(12);
	        //    if (fotoBytes != null) {
	        //        Image foto = Toolkit.getDefaultToolkit().createImage(fotoBytes);
	        //        produto.setFotoControleTV(foto);
	        //    }
	        //}
	        produtos.add(produto);
	    }
	    resultado.close();
	    comando.close();
	    conexao.desConectar();
	    
	    return produtos;
	}
	
	public void excluirControleTV(Produto produto) throws SQLException {
		ConexaoMysql conexao = new ConexaoMysql();
	    //Connection con = conexao.getIdConexao();
	    
	    String sql = "DELETE FROM controlesTV WHERE codigo = ?";
	    ProdutoControlesTV controleTV = new ProdutoControlesTV();
		PreparedStatement comando = conexao.getIdConexao().prepareStatement(sql);
		comando.setString(1, controleTV.getCodigo());	
		comando.executeUpdate();

	    comando.close();
	    conexao.desConectar();
	}
	
	public void excluirControleAR(Produto produto) throws SQLException {
		ConexaoMysql conexao = new ConexaoMysql();
	    Connection con = conexao.getIdConexao();
	    
	    String sql = "DELETE FROM controlesAR WHERE codigo = ?";
	    ProdutoControlesAR controleAR = (ProdutoControlesAR) produto;
		PreparedStatement comando = con.prepareStatement(sql);
		comando.setString(1, controleAR.getCodigo());	
		comando.executeUpdate();
		
	    comando.close();
	    conexao.desConectar();
	}
}