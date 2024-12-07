package Model;

import java.awt.Color;
import javax.swing.*;

public class ValidacoesControleTV {
    
    public static boolean validarNomeProduto(String nome, JLabel lblNomeProduto) {
        if (nome.isEmpty() || nome.length() < 3) {
            lblNomeProduto.setForeground(new Color(204, 0, 0));
            return false;
        } else {
            lblNomeProduto.setForeground(new Color(0, 204, 0));
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