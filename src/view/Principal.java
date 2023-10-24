package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.DAO;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class Principal extends JFrame {

	DAO dao = new DAO();
	private Connection con;
	@SuppressWarnings("unused")
	private PreparedStatement pst;
	@SuppressWarnings("unused")
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;
	private JLabel lblData;
	public JLabel lblUsuario;
	public JPanel panelRodape;
	public JButton btnRelatorios;
	public JButton btnUsuarios;
	private JButton btnProdutos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Kuruma Shine");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/KURUMA.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				
				setarData ();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 792, 600);
		contentPane = new JPanel();
		contentPane.setLocation(-306, -41);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// abrir tela de usuarios
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		btnUsuarios.setBorder(null);
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setContentAreaFilled(false);
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/users.png")));
		btnUsuarios.setToolTipText("Usuários");
		btnUsuarios.setBounds(63, 83, 128, 128);
		contentPane.add(btnUsuarios);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/img/info.png")));
		btnNewButton.setToolTipText("Sobre");
		btnNewButton.setBounds(726, 11, 47, 46);
		contentPane.add(btnNewButton);
		
		panelRodape = new JPanel();
		panelRodape.setBackground(new Color(132, 0, 0));
		panelRodape.setBounds(0, 481, 784, 80);
		contentPane.add(panelRodape);
		panelRodape.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Usuario:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(435, 33, 61, 14);
		panelRodape.add(lblNewLabel_3);
		
		lblData = new JLabel("New label");
		lblData.setBounds(10, 29, 488, 22);
		panelRodape.add(lblData);
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		
		lblUsuario = new JLabel("");
		lblUsuario.setBounds(507, 34, 175, 14);
		panelRodape.add(lblUsuario);
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		
				lblStatus = new JLabel("");
				lblStatus.setBounds(718, 11, 56, 58);
				panelRodape.add(lblStatus);
				lblStatus.setToolTipText("Status off");
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 32, 32), 3, true));
		panel_1.setBounds(53, 78, 148, 131);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("CLIENTE");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(79, 247, 99, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("USUÁRIO");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel.setBounds(70, 50, 114, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnUsuarios_1 = new JButton("");
		btnUsuarios_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			}
		});
		btnUsuarios_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios_1.setIcon(new ImageIcon(Principal.class.getResource("/img/cliente.png")));
		btnUsuarios_1.setToolTipText("Usuários");
		btnUsuarios_1.setContentAreaFilled(false);
		btnUsuarios_1.setBorder(null);
		btnUsuarios_1.setBounds(64, 281, 128, 128);
		contentPane.add(btnUsuarios_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(255, 32, 32), 3, true));
		panel_1_1.setBounds(54, 278, 148, 131);
		contentPane.add(panel_1_1);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new LineBorder(new Color(255, 32, 32), 3, true));
		panel_1_1_1.setBounds(315, 80, 148, 131);
		contentPane.add(panel_1_1_1);
		panel_1_1_1.setLayout(null);
		
		JButton btnOrdem = new JButton("");
		btnOrdem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servicos servicos = new Servicos();
				servicos.setVisible(true);
			}
		});
		btnOrdem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOrdem.setBounds(9, 4, 129, 129);
		panel_1_1_1.add(btnOrdem);
		btnOrdem.setIcon(new ImageIcon(Principal.class.getResource("/img/ordem.png")));
		btnOrdem.setToolTipText("Usuários");
		btnOrdem.setContentAreaFilled(false);
		btnOrdem.setBorder(null);
		
		JLabel lblOs = new JLabel("ORDEM DE SERVIÇO");
		lblOs.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblOs.setBounds(267, 51, 244, 29);
		contentPane.add(lblOs);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setBorder(new LineBorder(new Color(255, 32, 32), 3, true));
		panel_1_1_1_1.setBounds(315, 277, 148, 131);
		contentPane.add(panel_1_1_1_1);
		panel_1_1_1_1.setLayout(null);
		
		btnRelatorios = new JButton("");
		btnRelatorios.setEnabled(false);
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/img/relatorios.png")));
		btnRelatorios.setToolTipText("Usuários");
		btnRelatorios.setContentAreaFilled(false);
		btnRelatorios.setBorder(null);
		btnRelatorios.setBounds(10, 3, 129, 129);
		panel_1_1_1_1.add(btnRelatorios);
		
		JLabel lblNewLabel_2 = new JLabel("RELATÓRIOS");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(315, 244, 148, 34);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
		panel_2.setBounds(248, 81, 3, 331);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
		panel_3.setBounds(17, 228, 737, 3);
		contentPane.add(panel_3);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
		panel_2_1.setBounds(508, 80, 3, 331);
		contentPane.add(panel_2_1);
		
		JPanel panel_1_1_1_2 = new JPanel();
		panel_1_1_1_2.setLayout(null);
		panel_1_1_1_2.setBorder(new LineBorder(new Color(255, 32, 32), 3, true));
		panel_1_1_1_2.setBounds(558, 78, 148, 131);
		contentPane.add(panel_1_1_1_2);
		
		btnProdutos = new JButton("");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produtos produtos = new Produtos();
				produtos.setVisible(true);
			}
		});
		btnProdutos.setContentAreaFilled(false);
		btnProdutos.setBorderPainted(false);
		btnProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProdutos.setIcon(new ImageIcon(Principal.class.getResource("/img/produtos.png")));
		btnProdutos.setBounds(10, 6, 128, 120);
		panel_1_1_1_2.add(btnProdutos);
		
		JLabel lblProdutos = new JLabel("PRODUTOS");
		lblProdutos.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblProdutos.setBounds(566, 50, 164, 29);
		contentPane.add(lblProdutos);
		
		JPanel panel_1_1_1_2_1 = new JPanel();
		panel_1_1_1_2_1.setLayout(null);
		panel_1_1_1_2_1.setBorder(new LineBorder(new Color(255, 32, 32), 3, true));
		panel_1_1_1_2_1.setBounds(558, 275, 148, 131);
		contentPane.add(panel_1_1_1_2_1);
		
		JButton btnFornecedor = new JButton("");
		btnFornecedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedor fornecedor =new Fornecedor();
				fornecedor.setVisible(true);
			}
		});
		btnFornecedor.setIcon(new ImageIcon(Principal.class.getResource("/img/fornecedor.png")));
		btnFornecedor.setContentAreaFilled(false);
		btnFornecedor.setBorderPainted(false);
		btnFornecedor.setBounds(10, 6, 128, 120);
		panel_1_1_1_2_1.add(btnFornecedor);
		
		JLabel lblFornecedores = new JLabel("FORNECEDORES");
		lblFornecedores.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblFornecedores.setBounds(534, 247, 220, 29);
		contentPane.add(lblFornecedores);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Principal.class.getResource("/img/KURUMA.png")));
		lblNewLabel_4.setBounds(687, 403, 86, 80);
		contentPane.add(lblNewLabel_4);
	}

	private void status() {
		try {
			con = dao.conectar();
			if (con == null) {
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
			} else {
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/dbon.png")));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e); }

			
		} 
		private void setarData() {
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			lblData.setText(formatador.format(data));
		}
	}



/** 
 * *Método responsavel por setar a data no rodapé
 */

