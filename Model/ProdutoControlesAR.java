package Model;

import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import DAO.ProdutoDAO;
import View.TelaPrincipal;

public class ProdutoControlesAR extends Produto{
	private String classeControleAR;
	private String principalControleAR;
	private String marcaExtra1ControleAR;
	private String marcaExtra2ControleAR;
	private String marcaExtra3ControleAR;
	private String marcaExtra4ControleAR;
	private String modeloControleAR;
	private Image fotoControleAR;
	
	public String getClasseControleAR() {
		return classeControleAR;
	}
	public void setClasseControleAR(String classeControleAR) {
		this.classeControleAR = classeControleAR;
	}
	public String getPrincipalControleAR() {
		return principalControleAR;
	}
	public void setPrincipalControleAR(String principalControleAR) {
		this.principalControleAR = principalControleAR;
	}
	public String getMarcaExtra1ControleAR() {
		return marcaExtra1ControleAR;
	}
	public void setMarcaExtra1ControleAR(String marcaExtra1ControleAR) {
		this.marcaExtra1ControleAR = marcaExtra1ControleAR;
	}
	public String getMarcaExtra2ControleAR() {
		return marcaExtra2ControleAR;
	}
	public void setMarcaExtra2ControleAR(String marcaExtra2ControleAR) {
		this.marcaExtra2ControleAR = marcaExtra2ControleAR;
	}
	public String getMarcaExtra3ControleAR() {
		return marcaExtra3ControleAR;
	}
	public void setMarcaExtra3ControleAR(String marcaExtra3ControleAR) {
		this.marcaExtra3ControleAR = marcaExtra3ControleAR;
	}
	public String getMarcaExtra4ControleAR() {
		return marcaExtra4ControleAR;
	}
	public void setMarcaExtra4ControleAR(String marcaExtra4ControleAR) {
		this.marcaExtra4ControleAR = marcaExtra4ControleAR;
	}
	public String getModeloControleAR() {
		return modeloControleAR;
	}
	public void setModeloControleAR(String modeloControleAR) {
		this.modeloControleAR = modeloControleAR;
	}
	public Image getFotoControleAR() {
		return fotoControleAR;
	}
	public void setFotoControleAR(Image fotoControleAR) {
		this.fotoControleAR = fotoControleAR;
	}
}