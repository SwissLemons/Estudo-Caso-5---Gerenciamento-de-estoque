package Model;

import javax.swing.*;
import java.awt.*;

public class ValidacoesCadastroCliente {
    public static boolean validarNomeUsuario(String nome, JLabel lblNomeCadastro) {
        if (nome.isEmpty() || nome.length() < 3) {
            lblNomeCadastro.setForeground(new Color(204, 0, 0));
            return false;
        } else {
            lblNomeCadastro.setForeground(new Color(0, 204, 0));
            return true;
        }
    }

    public static boolean validarSenhaUsuario(String senha, JLabel lblSenhaCadastro) {
        if (senha.isEmpty() || senha.length() < 6) {
            lblSenhaCadastro.setForeground(new Color(204, 0, 0));
            return false;
        } else {
            lblSenhaCadastro.setForeground(new Color(0, 204, 0));
            return true;
        }
    }

    public static boolean validarFotoUsuario(JFileChooser fileChooser, JLabel lblFotoCadastro) {
        if (fileChooser.getSelectedFile() != null) {
            lblFotoCadastro.setForeground(new Color(0, 204, 0));
            return true;
        } else {
            lblFotoCadastro.setForeground(new Color(184, 184, 0));
            return false;
        }
    }

    public static boolean validarNivelAcesso(int nivelAcesso, JComboBox<String> comboBox) {
        if (nivelAcesso > 0) {
            comboBox.setForeground(new Color(0, 204, 0));
            return true;
        } else {
            comboBox.setForeground(new Color(204, 0, 0));
            return false;
        }
    }
}