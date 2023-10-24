package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class Login extends JFrame {

	// objetos JDBC
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	// objeto tela principal
	Principal principal = new Principal();

	/**
	 * 
	 */
	private static final long serialVersionUID = -3493449186404023102L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JLabel lblStatus;
	private JLabel lblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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

	public Login() {
		setTitle("Login");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/KURUMA.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();

				setarData();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 53, 46, 17);
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		txtLogin.setBounds(100, 50, 242, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 91, 46, 17);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		txtSenha.setBounds(100, 88, 242, 20);
		contentPane.add(txtSenha);

		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.setBackground(SystemColor.activeCaptionBorder);
		btnAcessar.setBorder(new LineBorder(new Color(128, 0, 0), 3));
		btnAcessar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnAcessar.setBounds(253, 132, 89, 23);
		contentPane.add(btnAcessar);
		getRootPane().setDefaultButton(btnAcessar);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 204, 434, 67);
		contentPane.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 11));
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(378, 1, 46, 56);
		panel.add(lblStatus);

		lblData = new JLabel("New label");
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblData.setBounds(10, 16, 358, 31);
		panel.add(lblData);
	}

	/**
	 * Método para autenticar um usuario
	 */
	private void logar() {
		String capturaSenha = new String(txtSenha.getPassword());

		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtSenha.requestFocus();
		} else {

			String read = "select * from usuarios where login=? and senha=md5(?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				rs = pst.executeQuery();
				if (rs.next()) {

					System.out.println(rs.getString(5));

					String perfil = rs.getString(5);
					if (perfil.equals("admin")) {

						principal.setVisible(true);
						principal.lblUsuario.setText(rs.getString(2));

						principal.btnUsuarios.setEnabled(true);
						principal.btnRelatorios.setEnabled(true);

						principal.panelRodape.setBackground(Color.RED);

						this.dispose();
					} else {

						principal.setVisible(true);
						principal.lblUsuario.setText(rs.getString(2));
						this.dispose();

					}

				} else {
					JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
				}
				con.close();
			} catch (Exception e) {
			}
		}
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
			System.out.println(e);
		}
	}

	private void setarData() {

		Date data = new Date();

		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

		lblData.setText(formatador.format(data));
	}
}
