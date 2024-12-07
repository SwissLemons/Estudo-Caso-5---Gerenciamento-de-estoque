package Model;

import java.awt.Color;
import javax.swing.*;

public class ValidacoesControles {
    
    public static boolean validarCodigoProduto(String codigo, JLabel lblCodigoProduto) {
        if (codigo.isEmpty() || codigo.length() < 4) {
            lblCodigoProduto.setForeground(new Color(204, 0, 0));
            return false;
        } else {
        	lblCodigoProduto.setForeground(new Color(0, 204, 0));
            return true;
        }
    }

    public static boolean validarPrecoProduto(String precoStr, JLabel lblPrecoProduto) {
        try {
            double preco = Double.parseDouble(precoStr);
            if (preco <= 0.00) {
                lblPrecoProduto.setForeground(new Color(204, 0, 0));
                return false;
            } else {
                lblPrecoProduto.setForeground(new Color(0, 204, 0));
                return true;
            }
        } catch (NumberFormatException e) {
            lblPrecoProduto.setForeground(new Color(204, 0, 0));
            return false;
        }
    }

    public static boolean validarQuantidadeProduto(String quantidadeStr, JLabel lblQuantidadeEmEstoque) {
        try {
            int quantidade = Integer.parseInt(quantidadeStr);
            if (quantidade < 0) {
                lblQuantidadeEmEstoque.setForeground(new Color(204, 0, 0));
                return false;
            } else {
                lblQuantidadeEmEstoque.setForeground(new Color(0, 204, 0));
                return true;
            }
        } catch (NumberFormatException e) {
            lblQuantidadeEmEstoque.setForeground(new Color(204, 0, 0));
            return false;
        }
    }

    public static boolean validarClasseProduto(String classe, JLabel lblClasseProduto) {
        if (classe == null || classe.isEmpty()) {
            lblClasseProduto.setForeground(new Color(204, 0, 0));
            return false;
        } else {
            lblClasseProduto.setForeground(new Color(0, 204, 0));
            return true;
        }
    }

    public static boolean validarMarcaPrincipalProduto(String marca, JLabel lblMarcaPrincipal) {
        if (marca == null || marca.isEmpty()) {
            lblMarcaPrincipal.setForeground(new Color(204, 0, 0));
            return false;
        } else {
            lblMarcaPrincipal.setForeground(new Color(0, 204, 0));
            return true;
        }
    }

    public static boolean validarMarcaExtra1Produto(String marcaExtra1, JLabel lblMarcaExtra1){
		if (marcaExtra1 == "------" || marcaExtra1.isEmpty()) {
			lblMarcaExtra1.setForeground(new Color(185, 185, 0));
            return true;
		} else {
			lblMarcaExtra1.setForeground(new Color(0, 204, 0));
            return true;
		}
	}
    
    public static boolean validarMarcaExtra2Produto(String marcaExtra2, JLabel lblMarcaExtra2){
		if (marcaExtra2 == "------" || marcaExtra2.isEmpty()) {
			lblMarcaExtra2.setForeground(new Color(185, 185, 0));
            return true;
		} else {
			lblMarcaExtra2.setForeground(new Color(0, 204, 0));
            return true;
		}
	}
    
    public static boolean validarMarcaExtra3Produto(String marcaExtra3, JLabel lblMarcaExtra3){
		if (marcaExtra3 == "------" || marcaExtra3.isEmpty()) {
			lblMarcaExtra3.setForeground(new Color(185, 185, 0));
            return true;
		} else {
			lblMarcaExtra3.setForeground(new Color(0, 204, 0));
            return true;
		}
	}
    
    public static boolean validarMarcaExtra4Produto(String marcaExtra4, JLabel lblMarcaExtra4){
		if (marcaExtra4 == "------" || marcaExtra4.isEmpty()) {
			lblMarcaExtra4.setForeground(new Color(185, 185, 0));
            return true;
		} else {
			lblMarcaExtra4.setForeground(new Color(0, 204, 0));
            return true;
		}
	}

    public static boolean validarModeloProduto(String modelo, JLabel lblModeloProduto) {
        if (modelo.isEmpty() || modelo.length() < 3) {
            lblModeloProduto.setForeground(new Color(204, 0, 0));
            return false;
        } else {
            lblModeloProduto.setForeground(new Color(0, 204, 0));
            return true;
        }
    }

    public static boolean validarFotoProduto(JFileChooser fileChooser, JLabel lblEscolhaFotoProduto) {
        if (fileChooser.getSelectedFile() != null) {
            lblEscolhaFotoProduto.setForeground(new Color(0, 204, 0));
            return true;
        } else {
            lblEscolhaFotoProduto.setForeground(new Color(204, 0, 0));
            return false;
        }
    }
}