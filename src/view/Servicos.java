package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;

public class Servicos extends JDialog {

	/**
	 * 
	 */
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
	private JTextField txtOS;
	private JTextField txtData;
	private JTextField txtVeiculo;
	private JTextField txtServico;
	private JTextField txtValor;
	private JTextField txtID;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnExcluir;
	private JTextField txtCliente;
	@SuppressWarnings("rawtypes")
	private JList listaClientes;
	private JScrollPane scrollPane;
	private JButton btnOS;
	private JLabel label;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servicos dialog = new Servicos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public Servicos() {
		setTitle("Serviços");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Servicos.class.getResource("/img/KURUMA.png")));
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
			}
		});
		setModal(true);
		setBounds(410, 180, 650, 428);
		getContentPane().setLayout(null);
		
		lblNewLabel_8 = new JLabel("Serviços");
		lblNewLabel_8.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		lblNewLabel_8.setBounds(31, 220, 66, 20);
		getContentPane().add(lblNewLabel_8);
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(Servicos.class.getResource("/img/prancheta3.png")));
		lblNewLabel_7.setBounds(329, 144, 180, 137);
		getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel = new JLabel("OS");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		lblNewLabel.setBounds(31, 45, 46, 14);
		getContentPane().add(lblNewLabel);

		txtOS = new JTextField();
		txtOS.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtOS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtOS.setEditable(false);
		txtOS.setBounds(135, 42, 103, 20);
		getContentPane().add(txtOS);
		txtOS.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		lblNewLabel_1.setBounds(31, 97, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtData = new JTextField();
		txtData.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtData.setEditable(false);
		txtData.setBounds(135, 94, 204, 20);
		getContentPane().add(txtData);
		txtData.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Veiculo");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		lblNewLabel_2.setBounds(31, 152, 78, 14);
		getContentPane().add(lblNewLabel_2);

		txtVeiculo = new JTextField();
		txtVeiculo.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtVeiculo.setBounds(135, 149, 204, 20);
		getContentPane().add(txtVeiculo);
		txtVeiculo.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tipo de ");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		lblNewLabel_3.setBounds(31, 208, 89, 14);
		getContentPane().add(lblNewLabel_3);

		txtServico = new JTextField();
		txtServico.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtServico.setBounds(135, 205, 204, 20);
		getContentPane().add(txtServico);
		txtServico.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Valor");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		lblNewLabel_4.setBounds(31, 262, 32, 14);
		getContentPane().add(lblNewLabel_4);

		txtValor = new JTextField();
		txtValor.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtValor.setBounds(135, 260, 103, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setIcon(new ImageIcon(Servicos.class.getResource("/img/add.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBounds(51, 302, 46, 57);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(new ImageIcon(Servicos.class.getResource("/img/editar.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(173, 302, 53, 57);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(Servicos.class.getResource("/img/deletar.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirServico();
			}
		});
		btnExcluir.setBounds(304, 302, 53, 57);
		getContentPane().add(btnExcluir);

		btnBuscar = new JButton("");
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBorder(null);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setIcon(new ImageIcon(Servicos.class.getResource("/img/pesquisar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(248, 29, 46, 41);
		getContentPane().add(btnBuscar);

		JButton btnNewButton = new JButton("");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setToolTipText("Limpar Campos");
		btnNewButton.setIcon(new ImageIcon(Servicos.class.getResource("/img/Borracha.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}

		});
		btnNewButton.setBounds(433, 302, 67, 63);
		getContentPane().add(btnNewButton);
		getRootPane().setDefaultButton(btnBuscar);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cliente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(373, 21, 159, 73);
		getContentPane().add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(9, 33, 140, 37);
		panel.add(scrollPane);

		listaClientes = new JList();
		listaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarClienteLista();
				listaClientes.setEnabled(true);
			}
		});
		scrollPane.setViewportView(listaClientes);

		txtCliente = new JTextField();
		txtCliente.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarClientes();
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		txtCliente.setColumns(10);
		txtCliente.setBounds(10, 15, 139, 20);
		panel.add(txtCliente);

		JLabel lblNewLabel_5 = new JLabel("ID do Cliente");
		lblNewLabel_5.setBounds(12, 43, 74, 14);
		panel.add(lblNewLabel_5);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(85, 40, 63, 20);
		panel.add(txtID);
		txtID.setColumns(10);

		btnOS = new JButton("Imprimir OS");
		btnOS.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnOS.setToolTipText("Imprimir OS");
		btnOS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOS.setContentAreaFilled(false);
		btnOS.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		btnOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirOS();
			}
		});
		btnOS.setBounds(389, 105, 126, 23);
		getContentPane().add(btnOS);

		label = new JLabel("");
		label.setBorder(new LineBorder(Color.WHITE, 280));
		label.setForeground(Color.WHITE);
		label.setBackground(Color.WHITE);
		label.setBounds(0, 0, 553, 389);
		getContentPane().add(label);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Servicos.class.getResource("/img/ferrari.jpg")));
		lblNewLabel_6.setBounds(551, 0, 83, 389);
		getContentPane().add(lblNewLabel_6);

	}

	private void limparCampos() {
		txtCliente.setText(null);
		txtID.setText(null);
		txtVeiculo.setText(null);
		txtServico.setText(null);
		txtValor.setText(null);
		txtData.setText(null);
		txtOS.setText(null);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnBuscar.setEnabled(true);

	}

	private void adicionar() {

		if (txtID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o ID do cliente");
			txtID.requestFocus();
		} else if (txtVeiculo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o veiculo do cliente");
			txtVeiculo.requestFocus();
		} else if (txtServico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o serviço desejado");
			txtServico.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o valor total do serviço");
			txtValor.requestFocus();

		} else {
			String create = "insert into servicos(veiculo, servico, valor, idcli) values (?,?,?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtVeiculo.getText());
				pst.setString(2, txtServico.getText());
				pst.setString(3, txtValor.getText());
				pst.setString(4, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Serviço Cadastrado");
				limparCampos();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void editar() {
		if (txtVeiculo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o modelo do veiculo");
			txtVeiculo.requestFocus();

		} else if (txtServico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o serviço desejado");
			txtServico.requestFocus();

		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite valor do serviço");
			txtValor.requestFocus();

		} else {
			String update = "update servicos set os=?, dataOS=?, veiculo=?, servico=?, valor=? where idcli=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtOS.getText());
				pst.setString(2, txtData.getText());
				pst.setString(3, txtVeiculo.getText());
				pst.setString(4, txtServico.getText());
				pst.setString(5, txtValor.getText());
				pst.setString(6, txtID.getText());

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do cliente editados com sucesso.");
				limparCampos();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void buscar() {
		String numOS = JOptionPane.showInputDialog("Número da OS");
		String read = "select * from servicos where os = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, numOS);
			rs = pst.executeQuery();
			if (rs.next()) {
				txtOS.setText(rs.getString(1));
				txtData.setText(rs.getString(2));
				txtVeiculo.setText(rs.getString(3));
				txtServico.setText(rs.getString(4));
				txtValor.setText(rs.getString(5));
				txtID.setText(rs.getString(6));
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
				btnBuscar.setEnabled(false);

			} else {
				JOptionPane.showMessageDialog(null, "OS inexistente");
				btnAdicionar.setEnabled(true);
				btnBuscar.setEnabled(false);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void excluirServico() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste serviço?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from servicos where idcli=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Serviço excluído");
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * Método usado para listar o nome dos usuários na lista
	 */
	@SuppressWarnings("unchecked")
	private void listarClientes() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaClientes.setModel(modelo);
		String readLista = "select * from clientes where nome like '" + txtCliente.getText() + "%'" + "order by nome";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			rs = pst.executeQuery();
			while (rs.next()) {
				scrollPane.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtCliente.getText().isEmpty()) {
					scrollPane.setVisible(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método que busca o usuário selecionado na lista
	 */
	private void buscarClienteLista() {
		int linha = listaClientes.getSelectedIndex();
		if (linha >= 0) {
			String readBuscaLista = "select * from clientes where nome like '" + txtCliente.getText() + "%'"
					+ "order by nome limit " + (linha) + ", 1";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readBuscaLista);
				rs = pst.executeQuery();
				if (rs.next()) {
					scrollPane.setVisible(false);
					txtID.setText(rs.getString(1));
					txtCliente.setText(rs.getString(2));
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					listaClientes.setEnabled(false);
					btnAdicionar.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Cliente Inexistente");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			scrollPane.setVisible(false);

		}
	}

	/**
	 * Impressão da OS
	 */
	private void imprimirOS() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("os.pdf"));
			document.open();
			String readOS = "select * from servicos where os = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readOS);
				pst.setString(1, txtOS.getText());
				rs = pst.executeQuery();
				if (rs.next()) {
					Paragraph os = new Paragraph("OS: " + rs.getString(1));
					os.setAlignment(Element.ALIGN_RIGHT);
					document.add(os);

					Paragraph usuario = new Paragraph("Veículo: " + rs.getString(3));
					usuario.setAlignment(Element.ALIGN_LEFT);
					document.add(usuario);

					Paragraph usuario2 = new Paragraph("Serviço: " + rs.getString(4));
					usuario.setAlignment(Element.ALIGN_LEFT);
					document.add(usuario2);

					Paragraph usuario3 = new Paragraph("Valor: " + rs.getString(5));
					usuario.setAlignment(Element.ALIGN_LEFT);
					document.add(usuario3);

					Paragraph usuario4 = new Paragraph("Data: " + rs.getString(2));
					usuario.setAlignment(Element.ALIGN_LEFT);
					document.add(usuario4);

					Image imagem = Image.getInstance(Servicos.class.getResource("/img/blueprint.jpg"));
					imagem.scaleToFit(500, 500);
					imagem.setAbsolutePosition(0, 400);
					document.add(imagem);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("os.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
