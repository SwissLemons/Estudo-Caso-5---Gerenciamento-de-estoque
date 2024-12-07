package Model;

import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import DAO.ProdutoDAO;
import View.TelaPrincipal;

public class ProdutoControlesTV extends Produto{
	private String classeControleTV;
	private String principalControleTV;
	private String marcaExtra1ControleTV;
	private String marcaExtra2ControleTV;
	private String marcaExtra3ControleTV;
	private String marcaExtra4ControleTV;
	private String modeloControleTV;
	private Image fotoControleTV;
	
	public String getClasseControleTV() {
		return classeControleTV;
	}
	public void setClasseControleTV(String classeControleTV) {
		this.classeControleTV = classeControleTV;
	}
	public String getPrincipalControleTV() {
		return principalControleTV;
	}
	public void setPrincipalControleTV(String principalControleTV) {
		this.principalControleTV = principalControleTV;
	}
	public String getMarcaExtra1ControleTV() {
		return marcaExtra1ControleTV;
	}
	public void setMarcaExtra1ControleTV(String marcaExtra1ControleTV) {
		this.marcaExtra1ControleTV = marcaExtra1ControleTV;
	}
	public String getMarcaExtra2ControleTV() {
		return marcaExtra2ControleTV;
	}
	public void setMarcaExtra2ControleTV(String marcaExtra2ControleTV) {
		this.marcaExtra2ControleTV = marcaExtra2ControleTV;
	}
	public String getMarcaExtra3ControleTV() {
		return marcaExtra3ControleTV;
	}
	public void setMarcaExtra3ControleTV(String marcaExtra3ControleTV) {
		this.marcaExtra3ControleTV = marcaExtra3ControleTV;
	}
	public String getMarcaExtra4ControleTV() {
		return marcaExtra4ControleTV;
	}
	public void setMarcaExtra4ControleTV(String marcaExtra4ControleTV) {
		this.marcaExtra4ControleTV = marcaExtra4ControleTV;
	}
	public String getModeloControleTV() {
		return modeloControleTV;
	}
	public void setModeloControleTV(String modeloControleTV) {
		this.modeloControleTV = modeloControleTV;
	}
	public Image getFotoControleTV() {
		return fotoControleTV;
	}
	public void setFotoControleTV(Image fotoControleTV) {
		this.fotoControleTV = fotoControleTV;
	}
}