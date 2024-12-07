package Model;

import java.awt.Image;

public class Usuario {

	private String nomeUsuario;
	private String senhaUsuario;
	private Image fotoUsuario;
	private int nivelAcesso;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	public Image getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(Image image) {
        this.fotoUsuario = image;
    }
    
    public int getNivelAcesso() {
		return nivelAcesso;
	}
	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
}