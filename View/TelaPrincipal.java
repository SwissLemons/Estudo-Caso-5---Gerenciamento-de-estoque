package View;

import DAO.CadastroDAO;
import DAO.LoginDAO;
import DAO.ProdutoDAO;
import Model.Produto;
import Model.ProdutoControlesAR;
import Model.ProdutoControlesTV;
import Model.Usuario;
import Model.ValidacoesCadastroCliente;
import Model.ValidacoesControles;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private static JTable tableProdutos;
	private JTextField tfNomeUsuarioLogin;
	private JPasswordField tfSenhaUsuarioLogin;
	private JTextField tfNomeUsuarioCadastro;
	private JTextField tfSenhaUsuarioCadastro;
	private JTable tablePermissoes;
	private Image imageCadastro;
	private Image imageLogin;
	private Image imageControle;
	private JEditorPane dtrpnAviso;
	private JTextField tfCodigoProduto;
	private JTextField tfPrecoProduto;
	private JTextField tfQuantidadeEmEstoque;
	private JPanel acessoPanel;
	private JPanel produtosPanel;
	private JPanel pedidoPanel;
	private JPanel permisaoPanel;
	private JPanel cadastroPanel;
	private JTextField tfModelo;
	private JTextField textField;
	private JLabel lblData = new JLabel("00/00/0000");
	private JLabel lblHorario = new JLabel("00:00");
	public JTextField tfTipoCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public TelaPrincipal() throws ClassNotFoundException, SQLException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\resources\\Focus_BRA_air_force.png"));
		setTitle("Gerenciador de Estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 714, 731);
		contentPane.add(tabbedPane);
		
		tabbedPane.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	if (tabbedPane.getSelectedIndex() == 0){
	        		setSize(730, 310); // original
	        		tabbedPane.setSize(714,271);
	        		setarData();
	        		setarHorario();
	        	}else if (tabbedPane.getSelectedIndex() == 1) {
	                setSize(730, 770); // adjusted
	                tabbedPane.setSize(714, 731);
	                try {
	        	    	carregarControlesTV();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("nao carregou os produtos");
					}
	            } else {
	                setSize(730, 310); // original
	                tabbedPane.setSize(714,271);
	            }
	        }
	    });
		///////////////////////////////////////////////////////////////////////// acessoPanel
		acessoPanel = new JPanel();
		tabbedPane.addTab("Acesso", null, acessoPanel, null);
		tabbedPane.setEnabledAt(0, true);
		acessoPanel.setLayout(null);
		
		JLabel lblNomeUsuario = new JLabel("Nome do usuario:");
		lblNomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeUsuario.setBounds(25, 47, 659, 14);
		acessoPanel.add(lblNomeUsuario);
		
		tfNomeUsuarioLogin = new JTextField();
		tfNomeUsuarioLogin.setBounds(228, 72, 250, 20);
		acessoPanel.add(tfNomeUsuarioLogin);
		tfNomeUsuarioLogin.setColumns(10);
		
		JLabel lblSenhaUsuario = new JLabel("Senha do usuario:");
		lblSenhaUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenhaUsuario.setBounds(25, 103, 659, 14);
		acessoPanel.add(lblSenhaUsuario);
		
		tfSenhaUsuarioLogin = new JPasswordField();
		tfSenhaUsuarioLogin.setBounds(228, 128, 250, 20);
		acessoPanel.add(tfSenhaUsuarioLogin);
		
		JLabel lblBemVindo = new JLabel("Bem vindo! Faça login no sistema para continuar.");
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindo.setBounds(25, 11, 659, 14);
		acessoPanel.add(lblBemVindo);
		
		JLabel lblFotoLogin = new JLabel("");
		lblFotoLogin.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFotoLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotoLogin.setBounds(530, 50, 115, 115);
		acessoPanel.add(lblFotoLogin);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuarioNome = tfNomeUsuarioLogin.getText();
                String usuarioSenha = new String(tfSenhaUsuarioLogin.getPassword());
                
                Usuario usuarioLogin = new Usuario();
                usuarioLogin.setNomeUsuario(usuarioNome);
                usuarioLogin.setSenhaUsuario(usuarioSenha);
		        try {
		            if (LoginDAO.validarLogin(usuarioLogin)) {
		            	lblBemVindo.setText("Login bem-sucedido! Acesso liberado.");
		            	lblBemVindo.setForeground(new Color(0, 204, 0));
		            	lblNomeUsuario.setForeground(new Color(0, 204, 0));
		            	lblSenhaUsuario.setForeground(new Color(0, 204, 0));

		                imageLogin = usuarioLogin.getFotoUsuario();
		                if (imageLogin != null) {
		                    ImageIcon imageIcon = new ImageIcon(imageLogin.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		                    lblFotoLogin.setIcon(imageIcon);
		                }
		            	if (usuarioLogin.getNivelAcesso() > 1) {
		            		//complete access
		            		tabbedPane.setEnabledAt(1, true);
			                tabbedPane.setEnabledAt(2, true);
			                tabbedPane.setEnabledAt(3, true);
			                tabbedPane.setEnabledAt(4, true);
			                btnLogin.setEnabled(false);
						} else {
							//partial access
							tabbedPane.setEnabledAt(1, true);
			                tabbedPane.setEnabledAt(2, true);
			                btnLogin.setEnabled(false);
						}
		            } else {
		            	lblBemVindo.setText("Login fracaçado! Acesso negado.");
		            	lblBemVindo.setForeground(new Color(204, 0, 0));
		            	lblNomeUsuario.setForeground(new Color(204 ,0 , 0));
		            	lblSenhaUsuario.setForeground(new Color(204, 0, 0));
		            }
		        } catch (ClassNotFoundException | SQLException e1) {
		            e1.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnLogin.setBounds(310, 163, 89, 23);
		acessoPanel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(0, 199, 709, 44);
		acessoPanel.add(panel_1);
		panel_1.setLayout(null);
		
		//lblData = new JLabel("00/00/0000");
		lblData.setBounds(10, 16, 71, 14);
		panel_1.add(lblData);
		setarData();
		
		JLabel lblNewLabel_1 = new JLabel("DBCon");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(633, 8, 66, 31);
		panel_1.add(lblNewLabel_1);
		
		//lblHorario = new JLabel("00:00");
		lblHorario.setBounds(91, 16, 46, 14);
		panel_1.add(lblHorario);
		setarHorario();
		///////////////////////////////////////////////////////////////////////// produtosPanel
		produtosPanel = new JPanel();
		tabbedPane.addTab("Cadastro e gerenciamento de novos produtos", null, produtosPanel, null);
		tabbedPane.setEnabledAt(1, false);
		produtosPanel.setLayout(null);

		JScrollPane scrollPaneProdutos = new JScrollPane();
		scrollPaneProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPaneProdutos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneProdutos.setBounds(10, 414, 689, 248);
		produtosPanel.add(scrollPaneProdutos);
		scrollPaneProdutos.setViewportView(tableProdutos);                                   
		
		tableProdutos = new JTable(new DefaultTableModel(new Object[]{"id","Código", "Preço", "Qtd", "Classe", "Marca", "Marca Ex", "Marca Ex", "Marca Ex", "Marca Ex", "Modelo"}, 0));
		scrollPaneProdutos.setViewportView(tableProdutos);
		DefaultTableModel modeloEstoque = (DefaultTableModel) tableProdutos.getModel();		
		
		JLabel lblCodigoProduto = new JLabel("Codigo do produto:");
		lblCodigoProduto.setBounds(10, 86, 119, 14);
		produtosPanel.add(lblCodigoProduto);
		
		tfCodigoProduto = new JTextField();
		tfCodigoProduto.setText("0000");
		tfCodigoProduto.setBounds(172, 82, 134, 22);
		produtosPanel.add(tfCodigoProduto);
		tfCodigoProduto.setColumns(10);
		
		JLabel lblPrecoProduto = new JLabel("Preço do produto:");
		lblPrecoProduto.setBounds(10, 119, 119, 14);
		produtosPanel.add(lblPrecoProduto);
		
		tfPrecoProduto = new JTextField();
		tfPrecoProduto.setText("0.00");
		tfPrecoProduto.setColumns(10);
		tfPrecoProduto.setBounds(129, 115, 177, 22);
		produtosPanel.add(tfPrecoProduto);
		
		JLabel lblQuantidadeEmEstoque = new JLabel("Quantidade:");
		lblQuantidadeEmEstoque.setBounds(10, 152, 119, 14);
		produtosPanel.add(lblQuantidadeEmEstoque);
		
		tfQuantidadeEmEstoque = new JTextField();
		tfQuantidadeEmEstoque.setText("0");
		tfQuantidadeEmEstoque.setColumns(10);
		tfQuantidadeEmEstoque.setBounds(129, 148, 177, 22);
		produtosPanel.add(tfQuantidadeEmEstoque);		
		
		tfTipoCodigo = new JTextField();
		tfTipoCodigo.setEditable(false);
		tfTipoCodigo.setColumns(10);
		tfTipoCodigo.setBounds(129, 83, 33, 22);
		produtosPanel.add(tfTipoCodigo);
		
		JLabel lblMarcaExtra1 = new JLabel("Marca extra 1:");
		lblMarcaExtra1.setBounds(10, 251, 119, 14);
		produtosPanel.add(lblMarcaExtra1);
		
		JFileChooser fileChooserCadastroProdutos = new JFileChooser();
		fileChooserCadastroProdutos.setFileSelectionMode(2);
		fileChooserCadastroProdutos.setControlButtonsAreShown(false);
		fileChooserCadastroProdutos.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens(*.PNG,*.JPG,*.JPEG)", "PNG", "JPG","JPEG"));
		fileChooserCadastroProdutos.setBackground(new Color(0, 51, 153));
		fileChooserCadastroProdutos.setBounds(316, 0, 393, 248);
		produtosPanel.add(fileChooserCadastroProdutos);
		
		JLabel lblTipoProduto = new JLabel("Tipo do produto:");
		lblTipoProduto.setBounds(10, 52, 119, 14);
		produtosPanel.add(lblTipoProduto);
		
		JLabel lblTituloProduto = new JLabel("Cadastrar um novo produto no estoque:");
		lblTituloProduto.setBounds(47, 11, 259, 27);
		produtosPanel.add(lblTituloProduto);
		
		JLabel lblIconeEstoque = new JLabel("");
		lblIconeEstoque.setIcon(new ImageIcon(".\\resources\\International_trade_icon.png"));
		lblIconeEstoque.setBounds(10, 11, 27, 27);
		produtosPanel.add(lblIconeEstoque);
		
		JLabel lblFotoControle = new JLabel("");
		lblFotoControle.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotoControle.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFotoControle.setIcon(new ImageIcon(".\\resources\\Focus_generic_industry_2.png"));
		lblFotoControle.setBounds(577, 247, 122, 122);
		produtosPanel.add(lblFotoControle);
		
		JComboBox<String> comboBoxMarcaExtra1 = new JComboBox<String>();
		comboBoxMarcaExtra1.setBounds(130, 247, 176, 22);
		produtosPanel.add(comboBoxMarcaExtra1);
		
		JLabel lblMarcaExtra2 = new JLabel("Marca extra 2:");
		lblMarcaExtra2.setBounds(10, 285, 119, 14);
		produtosPanel.add(lblMarcaExtra2);
		
		JComboBox<String> comboBoxMarcaExtra2 = new JComboBox<String>();
		comboBoxMarcaExtra2.setBounds(129, 281, 177, 22);
		produtosPanel.add(comboBoxMarcaExtra2);
		
		JLabel lblMarcaExtra3 = new JLabel("Marca extra 3:");
		lblMarcaExtra3.setBounds(10, 318, 119, 14);
		produtosPanel.add(lblMarcaExtra3);
		
		JComboBox<String> comboBoxMarcaExtra3 = new JComboBox<String>();
		comboBoxMarcaExtra3.setBounds(129, 314, 177, 22);
		produtosPanel.add(comboBoxMarcaExtra3);
		
		JLabel lblMarcaExtra4 = new JLabel("Marca extra 4:");
		lblMarcaExtra4.setBounds(10, 351, 119, 14);
		produtosPanel.add(lblMarcaExtra4);
		
		JComboBox<String> comboBoxMarcaExtra4 = new JComboBox<String>();
		comboBoxMarcaExtra4.setBounds(129, 347, 177, 22);
		produtosPanel.add(comboBoxMarcaExtra4);
		
		JLabel lblClasseProduto = new JLabel("Classe:");
		lblClasseProduto.setBounds(10, 185, 119, 14);
		produtosPanel.add(lblClasseProduto);
		
		JComboBox<String> comboBoxClasseProduto = new JComboBox<String>();
		comboBoxClasseProduto.setBounds(130, 181, 176, 22);
		produtosPanel.add(comboBoxClasseProduto);
		
		JLabel lblMarcaPrincipal = new JLabel("Marca principal:");
		lblMarcaPrincipal.setBounds(10, 218, 119, 14);
		produtosPanel.add(lblMarcaPrincipal);
		
		JComboBox<String> comboBoxMarcaPrincipal = new JComboBox<String>();
		comboBoxMarcaPrincipal.setBounds(130, 214, 176, 22);
		produtosPanel.add(comboBoxMarcaPrincipal);
		
		JLabel lblModeloProduto = new JLabel("Modelo :");
		lblModeloProduto.setBounds(316, 285, 81, 14);
		produtosPanel.add(lblModeloProduto);
		
		tfModelo = new JTextField();
		tfModelo.setColumns(10);
		tfModelo.setBounds(390, 281, 177, 22);
		produtosPanel.add(tfModelo);
		
		JEditorPane dtrpnAvisoProduto = new JEditorPane();
		dtrpnAvisoProduto.setText("É recomendado escolher marcas extra compativeis com este controle expecifico para melhor guardar as especificações de cada controle");
		dtrpnAvisoProduto.setFont(new Font("Tahoma", Font.ITALIC, 11));
		dtrpnAvisoProduto.setEditable(false);
		dtrpnAvisoProduto.setBounds(316, 314, 251, 55);
		produtosPanel.add(dtrpnAvisoProduto);
		
		JLabel lblEscolhaFotoProduto = new JLabel("Foto:");
		lblEscolhaFotoProduto.setBounds(316, 251, 81, 14);
		produtosPanel.add(lblEscolhaFotoProduto);
		
		JButton btnEscolherFotoControle = new JButton("Escolher foto");
		btnEscolherFotoControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooserCadastroProdutos != null) {
					ImageIcon imageIcon = new ImageIcon(fileChooserCadastroProdutos.getSelectedFile().getAbsolutePath());
					imageControle = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	                lblFotoControle.setIcon(new ImageIcon(imageControle));
				}
			}
		});
		btnEscolherFotoControle.setBounds(390, 247, 177, 23);
		produtosPanel.add(btnEscolherFotoControle);
        comboBoxClasseProduto.addItem("Todos os controles para tv");
        comboBoxClasseProduto.addItem("Todos os controles para ar");
		
		JComboBox<String> comboBoxTipoProduto = new JComboBox<String>();
		comboBoxTipoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxTipoProduto.getSelectedIndex() == 0) {
			    	try {
						carregarControlesTV();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tfTipoCodigo.setText("1-"); //tv
	                comboBoxClasseProduto.removeAllItems();
	                comboBoxClasseProduto.addItem("Smart");
	                comboBoxClasseProduto.addItem("Não Smart");
	                comboBoxClasseProduto.addItem("Tubo");
	                try {
	                    List<String> marcas = ProdutoDAO.buscarMarcasControlesTV();
	                    comboBoxMarcaPrincipal.removeAllItems();
	                    comboBoxMarcaExtra1.removeAllItems();
	            		comboBoxMarcaExtra1.addItem("------");
	                    comboBoxMarcaExtra2.removeAllItems();
	            		comboBoxMarcaExtra2.addItem("------");
	                    comboBoxMarcaExtra3.removeAllItems();
	            		comboBoxMarcaExtra3.addItem("------");
	                    comboBoxMarcaExtra4.removeAllItems();
	            		comboBoxMarcaExtra4.addItem("------");
	                    
	                    for (String marca : marcas) {
	                        comboBoxMarcaPrincipal.addItem(marca);
	                        comboBoxMarcaExtra1.addItem(marca);
	                        comboBoxMarcaExtra2.addItem(marca);
	                        comboBoxMarcaExtra3.addItem(marca);
	                        comboBoxMarcaExtra4.addItem(marca);
	                    }
	                } catch (ClassNotFoundException | SQLException e1) {
	                    e1.printStackTrace();
	                }
				}else if(comboBoxTipoProduto.getSelectedIndex() == 1) {
					try {
						carregarControlesAR();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tfTipoCodigo.setText("2-"); //ar
	                comboBoxClasseProduto.removeAllItems();
	                comboBoxClasseProduto.addItem("Convencional");
	                comboBoxClasseProduto.addItem("Spliter");
	                comboBoxClasseProduto.addItem("Janela");
	                
	                List<String> marcas;
					try {
						marcas = ProdutoDAO.buscarMarcasControlesAR();
	                    comboBoxMarcaPrincipal.removeAllItems();
	                    comboBoxMarcaExtra1.removeAllItems();
	            		comboBoxMarcaExtra1.addItem("------");
	                    comboBoxMarcaExtra2.removeAllItems();
	            		comboBoxMarcaExtra2.addItem("------");
	                    comboBoxMarcaExtra3.removeAllItems();
	            		comboBoxMarcaExtra3.addItem("------");
	                    comboBoxMarcaExtra4.removeAllItems();
	            		comboBoxMarcaExtra4.addItem("------");
		                
	            		for (String marca : marcas) {
	                        comboBoxMarcaPrincipal.addItem(marca);
	                        comboBoxMarcaExtra1.addItem(marca);
	                        comboBoxMarcaExtra2.addItem(marca);
	                        comboBoxMarcaExtra3.addItem(marca);
	                        comboBoxMarcaExtra4.addItem(marca);
	                    }
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		comboBoxTipoProduto.setBounds(129, 49, 177, 22);
		comboBoxTipoProduto.addItem("1 - Controles para TV");
		comboBoxTipoProduto.addItem("2 - Controles para AR");
		produtosPanel.add(comboBoxTipoProduto);
		
		JButton btnSalvarProduto = new JButton("Salvar produto");
		btnSalvarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean showNomeProduto = ValidacoesControles.validarCodigoProduto(tfCodigoProduto.getText(), lblCodigoProduto);
		        boolean showPrecoProduto = ValidacoesControles.validarPrecoProduto(tfPrecoProduto.getText(), lblPrecoProduto);
		        boolean showQuantidadeProduto = ValidacoesControles.validarQuantidadeProduto(tfQuantidadeEmEstoque.getText(), lblQuantidadeEmEstoque);
		        boolean showClasseProduto = ValidacoesControles.validarClasseProduto(comboBoxClasseProduto.getSelectedItem().toString(), lblClasseProduto);
		        boolean showMarcaPrincipal = ValidacoesControles.validarMarcaPrincipalProduto(comboBoxMarcaPrincipal.getSelectedItem().toString(), lblMarcaPrincipal);
		        boolean showMarcaExtra1 = ValidacoesControles.validarMarcaExtra1Produto(comboBoxMarcaExtra1.getSelectedItem().toString(), lblMarcaExtra1);
        		boolean showMarcaExtra2 = ValidacoesControles.validarMarcaExtra2Produto(comboBoxMarcaExtra2.getSelectedItem().toString(), lblMarcaExtra2);
				boolean showMarcaExtra3 = ValidacoesControles.validarMarcaExtra3Produto(comboBoxMarcaExtra3.getSelectedItem().toString(), lblMarcaExtra3);
				boolean showMarcaExtra4 = ValidacoesControles.validarMarcaExtra4Produto(comboBoxMarcaExtra4.getSelectedItem().toString(), lblMarcaExtra4);
		        boolean showModeloProduto = ValidacoesControles.validarModeloProduto(tfModelo.getText(), lblModeloProduto);
		        boolean showFotoProduto = ValidacoesControles.validarFotoProduto(fileChooserCadastroProdutos, lblEscolhaFotoProduto);
		        
		        if (showNomeProduto && showPrecoProduto && showQuantidadeProduto && showClasseProduto && showMarcaPrincipal && showModeloProduto && showFotoProduto && showMarcaExtra1 && showMarcaExtra2 && showMarcaExtra3 && showMarcaExtra4 ) {
		            if (comboBoxTipoProduto.getSelectedIndex() == 0) {
		            	ProdutoControlesTV produtoCadastroTV = new ProdutoControlesTV();
		            	produtoCadastroTV.setCodigo(tfTipoCodigo.getText() + tfCodigoProduto.getText());
		            	produtoCadastroTV.setPreco(Double.parseDouble(tfPrecoProduto.getText()));
		            	produtoCadastroTV.setQuantidadeEstoque(Integer.parseInt(tfQuantidadeEmEstoque.getText()));
		            	produtoCadastroTV.setClasseControleTV(comboBoxClasseProduto.getSelectedItem().toString());
		            	produtoCadastroTV.setPrincipalControleTV(comboBoxMarcaPrincipal.getSelectedItem().toString());
		            	produtoCadastroTV.setMarcaExtra1ControleTV(comboBoxMarcaExtra1.getSelectedItem().toString());
		            	produtoCadastroTV.setMarcaExtra2ControleTV(comboBoxMarcaExtra2.getSelectedItem().toString());
		            	produtoCadastroTV.setMarcaExtra3ControleTV(comboBoxMarcaExtra3.getSelectedItem().toString());
		            	produtoCadastroTV.setMarcaExtra4ControleTV(comboBoxMarcaExtra4.getSelectedItem().toString());
		            	produtoCadastroTV.setModeloControleTV(tfModelo.getText());
		            	produtoCadastroTV.setFotoControleTV(imageControle);
			            try {
			                if (ProdutoDAO.inserirControleTV(produtoCadastroTV, produtoCadastroTV)) {
			        	    	carregarControlesTV();
			                    btnSalvarProduto.setForeground(new Color(0, 204, 0));
			                } else {
			                    btnSalvarProduto.setForeground(new Color(204, 0, 0));
			                    dtrpnAvisoProduto.setText("Falha ao salvar um novo controle para tv no sistema.");
			                }
			            } catch (ClassNotFoundException | SQLException e1) {
			                e1.printStackTrace();
			            }
					} else {
						ProdutoControlesAR produtoCadastroAR = new ProdutoControlesAR();
						produtoCadastroAR.setCodigo(tfTipoCodigo.getText() + tfCodigoProduto.getText());
						produtoCadastroAR.setPreco(Double.parseDouble(tfPrecoProduto.getText()));
						produtoCadastroAR.setQuantidadeEstoque(Integer.parseInt(tfQuantidadeEmEstoque.getText()));
						produtoCadastroAR.setClasseControleAR(comboBoxClasseProduto.getSelectedItem().toString());
						produtoCadastroAR.setPrincipalControleAR(comboBoxMarcaPrincipal.getSelectedItem().toString());
						produtoCadastroAR.setMarcaExtra1ControleAR(comboBoxMarcaExtra1.getSelectedItem().toString());
						produtoCadastroAR.setMarcaExtra2ControleAR(comboBoxMarcaExtra2.getSelectedItem().toString());
						produtoCadastroAR.setMarcaExtra3ControleAR(comboBoxMarcaExtra3.getSelectedItem().toString());
						produtoCadastroAR.setMarcaExtra4ControleAR(comboBoxMarcaExtra4.getSelectedItem().toString());
			            produtoCadastroAR.setModeloControleAR(tfModelo.getText());
			            produtoCadastroAR.setFotoControleAR(imageControle);
			            try {
			                if (ProdutoDAO.inserirControleAR(produtoCadastroAR, produtoCadastroAR)) {
			        	    	carregarControlesAR();
			                    btnSalvarProduto.setForeground(new Color(0, 204, 0));
			                } else {
			                    btnSalvarProduto.setForeground(new Color(204, 0, 0));
			                    dtrpnAvisoProduto.setText("Falha ao salvar um novo controle para ar no sistema.");
			                }
			            } catch (ClassNotFoundException | SQLException e1) {
			                e1.printStackTrace();
			            }
					}
		        }
			}
		});
		btnSalvarProduto.setBounds(10, 380, 296, 23);
		produtosPanel.add(btnSalvarProduto);
		
		JButton btnExcluirProduto = new JButton("Excluir produto");
		btnExcluirProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//excluir uma linha da tabela:				
				DefaultTableModel modelo = (DefaultTableModel) tableProdutos.getModel();
		        if (tableProdutos.getSelectedRow() != -1) {
	                Produto produto = new Produto();
	                ProdutoDAO produtoDAO = new ProdutoDAO();
	                
	                String meubin = modelo.getValueAt(tableProdutos.getSelectedRow(),1).toString();
	                produto.setCodigo(meubin);
	                try {
		                produtoDAO.excluirControleTV(produto);
		                btnExcluirProduto.setForeground(new Color(0, 204, 0));
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                dtrpnAvisoProduto.setText("Erro ao excluir o produto: ");
		                btnExcluirProduto.setForeground(new Color(204, 0, 0));
		            }
		        } else {
		        	dtrpnAvisoProduto.setText("Selecione um produto para excluir.");
		        	btnExcluirProduto.setForeground(new Color(204, 0, 0));
		        }
			}
		});
		btnExcluirProduto.setBounds(577, 380, 122, 23);
		produtosPanel.add(btnExcluirProduto);
		
		JButton btnAlterarProduto = new JButton("Alterar produto");
		btnAlterarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAlterarProduto.setBounds(316, 380, 251, 23);
		produtosPanel.add(btnAlterarProduto);
		
		JLabel lblPesquisa = new JLabel("Pesquisar Codigo:");
		lblPesquisa.setBounds(10, 673, 119, 14);
		produtosPanel.add(lblPesquisa);
		
		textField = new JTextField();
		textField.setBounds(129, 670, 177, 20);
		produtosPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(316, 669, 119, 23);
		produtosPanel.add(btnPesquisar);
		
		JComboBox comboBoxPesquisas = new JComboBox();
		comboBoxPesquisas.setBounds(445, 669, 254, 22);
		comboBoxPesquisas.addItem("Pesquisar por código");
		comboBoxPesquisas.addItem("Pesquisar por modelo");
		produtosPanel.add(comboBoxPesquisas);
		
		///////////////////////////////////////////////////////////////////////// pedidoPanel
		pedidoPanel = new JPanel();
		tabbedPane.addTab("Efetuar pedido", null, pedidoPanel, null);
		tabbedPane.setEnabledAt(2, false);
		pedidoPanel.setLayout(null);
		
		//////////////////////////////////////////////////////////////////////// permissaoPanel
		permisaoPanel = new JPanel();
		tabbedPane.addTab("p", null, permisaoPanel, null);
		tabbedPane.setEnabledAt(3, false);
		permisaoPanel.setLayout(null);
		
		JButton btnSalvarMudanças = new JButton("Salvar Mudanças");
		btnSalvarMudanças.setBounds(10, 209, 188, 23);
		permisaoPanel.add(btnSalvarMudanças);
		
		JScrollPane scrollPanePermissoes = new JScrollPane();
		scrollPanePermissoes.setBounds(10, 11, 699, 187);
		permisaoPanel.add(scrollPanePermissoes);
		
		tablePermissoes = new JTable();
		scrollPanePermissoes.setViewportView(tablePermissoes);
		
		JButton btnNewButton_2_1 = new JButton("Salvar Mudanças");
		btnNewButton_2_1.setBounds(511, 209, 188, 23);
		permisaoPanel.add(btnNewButton_2_1);
		
		///////////////////////////////////////////////////////////////////////// cadastroPanel		
		cadastroPanel = new JPanel();
		tabbedPane.addTab("Gerenciar cadastros", null, cadastroPanel, null);
		tabbedPane.setEnabledAt(4, false);
		cadastroPanel.setLayout(null);
		
		JLabel lblNomeCadastro = new JLabel("Nome do usuario:");
		lblNomeCadastro.setBounds(10, 35, 115, 14);
		cadastroPanel.add(lblNomeCadastro);
		
		tfNomeUsuarioCadastro = new JTextField();
		tfNomeUsuarioCadastro.setBounds(135, 32, 173, 20);
		cadastroPanel.add(tfNomeUsuarioCadastro);
		tfNomeUsuarioCadastro.setColumns(10);
		
		JLabel lblSenhaCadastro = new JLabel("Senha do usuario:");
		lblSenhaCadastro.setBounds(10, 63, 115, 14);
		cadastroPanel.add(lblSenhaCadastro);
		
		tfSenhaUsuarioCadastro = new JTextField();
		tfSenhaUsuarioCadastro.setColumns(10);
		tfSenhaUsuarioCadastro.setBounds(135, 60, 173, 20);
		cadastroPanel.add(tfSenhaUsuarioCadastro);
		
		JLabel lblFotoUsuarioEscolhida = new JLabel("");
		lblFotoUsuarioEscolhida.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotoUsuarioEscolhida.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFotoUsuarioEscolhida.setIcon(new ImageIcon(".\\resources\\Focus_ARG_women_in_industry.png"));
		lblFotoUsuarioEscolhida.setBounds(10, 117, 115, 115);
		cadastroPanel.add(lblFotoUsuarioEscolhida);
		
		JLabel lblTituloCadastro = new JLabel("Cadastrar um novo usuario no sistema:");
		lblTituloCadastro.setBounds(10, 11, 298, 14);
		cadastroPanel.add(lblTituloCadastro);
		
		JFileChooser fileChooserFotoUsuario = new JFileChooser();
		fileChooserFotoUsuario.setFileSelectionMode(2);
		fileChooserFotoUsuario.setControlButtonsAreShown(false);
		fileChooserFotoUsuario.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens(*.PNG,*.JPG,*.JPEG)", "PNG", "JPG","JPEG"));
		fileChooserFotoUsuario.setBackground(new Color(0, 51, 153));
		fileChooserFotoUsuario.setBounds(316, 0, 393, 243);
		cadastroPanel.add(fileChooserFotoUsuario);
		
		JLabel lblFotoCadastro = new JLabel("Foto do usuario:");
		lblFotoCadastro.setBounds(10, 92, 115, 14);
		cadastroPanel.add(lblFotoCadastro);
		
		dtrpnAviso = new JEditorPane();
		dtrpnAviso.setFont(new Font("Tahoma", Font.ITALIC, 11));
		dtrpnAviso.setEditable(false);
		dtrpnAviso.setText("É recomendado escolher uma foto para diferenciação e identificação dos usuarios no sistema.");
		dtrpnAviso.setBounds(135, 150, 173, 48);
		cadastroPanel.add(dtrpnAviso);
		
		JButton btnEscolherFotoUsuarioCadastro = new JButton("Escolher foto");
		btnEscolherFotoUsuarioCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (fileChooserFotoUsuario != null) {
                    ImageIcon imageIcon = new ImageIcon(fileChooserFotoUsuario.getSelectedFile().getAbsolutePath());
                    imageCadastro = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                    lblFotoUsuarioEscolhida.setIcon(new ImageIcon(imageCadastro));
                }
            }
		});
		btnEscolherFotoUsuarioCadastro.setBounds(135, 88, 173, 23);
		cadastroPanel.add(btnEscolherFotoUsuarioCadastro);
		
		JComboBox<String> comboBoxNivelAcesso = new JComboBox<String>();
		comboBoxNivelAcesso.setBounds(135, 117, 173, 22);
		comboBoxNivelAcesso.addItem("Nivel de acesso:");
		comboBoxNivelAcesso.addItem("1 - Acesso parcial");
		comboBoxNivelAcesso.addItem("2 - Acesso total");
		cadastroPanel.add(comboBoxNivelAcesso);
		
		JButton btnCadastrarUsuario = new JButton("Cadastrar novo usuario");
		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String usuarioNomeCadastro = tfNomeUsuarioCadastro.getText();
                String usuarioSenhaCadastro = tfSenhaUsuarioCadastro.getText();
                int nivelAcessoCadastro = comboBoxNivelAcesso.getSelectedIndex();

                boolean nomeValido = ValidacoesCadastroCliente.validarNomeUsuario(usuarioNomeCadastro, lblNomeCadastro);
                boolean senhaValida = ValidacoesCadastroCliente.validarSenhaUsuario(usuarioSenhaCadastro, lblSenhaCadastro);
                boolean fotoValida = ValidacoesCadastroCliente.validarFotoUsuario(fileChooserFotoUsuario, lblFotoCadastro);
                boolean nivelAcessoValido = ValidacoesCadastroCliente.validarNivelAcesso(nivelAcessoCadastro, comboBoxNivelAcesso);

                if (nomeValido && senhaValida && nivelAcessoValido) {
                    Usuario usuarioCadastro = new Usuario();
                    usuarioCadastro.setNomeUsuario(usuarioNomeCadastro);
                    usuarioCadastro.setSenhaUsuario(usuarioSenhaCadastro);
                    usuarioCadastro.setFotoUsuario(imageCadastro);
                    usuarioCadastro.setNivelAcesso(nivelAcessoCadastro);

                    try {
                        if (CadastroDAO.conectarCadastro(usuarioCadastro)) {
                            btnCadastrarUsuario.setForeground(new Color(0, 204, 0));
                        } else {
                            btnCadastrarUsuario.setForeground(new Color(204, 0, 0));
                            dtrpnAviso.setText("Falha ao cadastrar um novo usuario no sistema.");
                        }
                    } catch (ClassNotFoundException | SQLException e1) {
                        e1.printStackTrace();
                    }
                }
			}
		});
		btnCadastrarUsuario.setBounds(135, 209, 173, 23);
		cadastroPanel.add(btnCadastrarUsuario);
	}

	public void setarData() {
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.DATE_FIELD);
		lblData.setText(formatador.format(data));
	}
	
	public void setarHorario() {
	    Calendar data = Calendar.getInstance();
	    SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
	    lblHorario.setText(formatador.format(data.getTime()));
	}
	
	public static void carregarControlesTV() throws ClassNotFoundException, SQLException {	        
		ArrayList<ProdutoControlesTV> produtos = ProdutoDAO.tabelaControlesTV();
	    DefaultTableModel modelo = (DefaultTableModel) tableProdutos.getModel();
	    modelo.setRowCount(0);//exclui todas as linhas da tabekla
	
	    for (ProdutoControlesTV produto : produtos) {
	        Object[] row = {
	        	produto.getId(),
	            produto.getCodigo(),
	            produto.getPreco(),
	            produto.getQuantidadeEstoque(),
	            produto.getClasseControleTV(),
	            produto.getPrincipalControleTV(),
	            produto.getMarcaExtra1ControleTV(),
	            produto.getMarcaExtra2ControleTV(),
	            produto.getMarcaExtra3ControleTV(),
	            produto.getMarcaExtra4ControleTV(),
	            produto.getModeloControleTV()
	        };
	        modelo.addRow(row);
	    }
	}
	
	public static void carregarControlesAR() throws ClassNotFoundException, SQLException {	        
		ArrayList<ProdutoControlesAR> produtos = ProdutoDAO.tabelaControlesAR();
	    DefaultTableModel modelo = (DefaultTableModel) tableProdutos.getModel();
	    modelo.setRowCount(0);//exclui todas as linhas da tabekla
	
	    for (ProdutoControlesAR produto : produtos) {
	        Object[] row = {
	        	produto.getId(),
	            produto.getCodigo(),
	            produto.getPreco(),
	            produto.getQuantidadeEstoque(),
	            produto.getClasseControleAR(),
	            produto.getPrincipalControleAR(),
	            produto.getMarcaExtra1ControleAR(),
	            produto.getMarcaExtra2ControleAR(),
	            produto.getMarcaExtra3ControleAR(),
	            produto.getMarcaExtra4ControleAR(),
	            produto.getModeloControleAR()
	        };
	        modelo.addRow(row);
	    }
	}
}