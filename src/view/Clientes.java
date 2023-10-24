package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;

public class Clientes extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtID;

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtCEP;
	private JTextField txtCidade;
	private JLabel lblNewLabel_2_1_3;
	private JTextField txtBairro;
	private JLabel lblNewLabel_2_1_4;
	private JTextField txtComplemento;
	private JLabel lblNewLabel_2_1_2;
	private JLabel lblNewLabel_2_1_5;
	private JTextField txtReferencia;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUF;
	private JButton btnBuscarCEP;
	private JFormattedTextField txtContato;
	private JLabel lblNewLabel_3;
	private JList listaClientes;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Clientes() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);

			}
		});
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setForeground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/KURUMA.png")));
		setTitle("Cadastro de Cliente");
		setBounds(430, 180, 547, 590);
		getContentPane().setLayout(null);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				listarClientes();
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(128, 90, 273, 54);
		getContentPane().add(scrollPane);
		
		listaClientes = new JList();
		listaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarClienteLista();
				listaClientes.setEnabled(true);
				
			}
		});
		scrollPane.setViewportView(listaClientes);
		txtNome.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtNome.setBounds(128, 72, 274, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(50));

		JLabel lblidCliente = new JLabel("ID");
		lblidCliente.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblidCliente.setBounds(36, 35, 23, 14);
		getContentPane().add(lblidCliente);

		txtCpf = new JTextField();
		txtCpf.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtCpf.setBounds(128, 114, 274, 20);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);
		txtCpf.setDocument(new Validador(11));

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel.setBounds(36, 75, 36, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(36, 117, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Telefone");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(36, 164, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtID.setBounds(128, 32, 46, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/add.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setBounds(50, 441, 63, 41);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/img/editar.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorder(null);
		btnEditar.setBounds(169, 436, 57, 47);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/deletar.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBorder(null);
		btnExcluir.setBounds(291, 429, 55, 58);
		getContentPane().add(btnExcluir);

		JButton btnLimparCampos = new JButton("");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});

		btnLimparCampos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimparCampos.setIcon(new ImageIcon(Clientes.class.getResource("/img/Borracha.png")));
		btnLimparCampos.setToolTipText("Limpar campos");
		btnLimparCampos.setContentAreaFilled(false);
		btnLimparCampos.setBorder(null);
		btnLimparCampos.setBounds(409, 429, 65, 58);
		getContentPane().add(btnLimparCampos);

		JLabel lblNewLabel_2_1 = new JLabel("Endereço");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(36, 251, 68, 14);
		getContentPane().add(lblNewLabel_2_1);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtEndereco.setBounds(128, 249, 274, 20);
		getContentPane().add(txtEndereco);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtNumero.setBounds(445, 249, 46, 20);
		getContentPane().add(txtNumero);

		JLabel lblNewLabel_2_1_1 = new JLabel("N°");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_2_1_1.setBounds(412, 251, 23, 14);
		getContentPane().add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_2 = new JLabel("CEP");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_2_2.setBounds(36, 207, 46, 14);
		getContentPane().add(lblNewLabel_2_2);

		txtCEP = new JTextField();
		txtCEP.setColumns(10);
		txtCEP.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtCEP.setBounds(128, 205, 139, 20);
		getContentPane().add(txtCEP);
		txtCEP.setColumns(10);
		txtCEP.setDocument(new Validador(8));

		btnBuscarCEP = new JButton("Buscar CEP");
		btnBuscarCEP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnBuscarCEP.setBounds(287, 204, 115, 23);
		getContentPane().add(btnBuscarCEP);

		JLabel lblNewLabel_2_1_2_1 = new JLabel("Cidade");
		lblNewLabel_2_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_2_1_2_1.setBounds(36, 294, 83, 14);
		getContentPane().add(lblNewLabel_2_1_2_1);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtCidade.setBounds(128, 291, 188, 20);
		getContentPane().add(txtCidade);

		lblNewLabel_2_1_3 = new JLabel("Bairro");
		lblNewLabel_2_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_2_1_3.setBounds(36, 334, 92, 14);
		getContentPane().add(lblNewLabel_2_1_3);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtBairro.setBounds(128, 332, 139, 20);
		getContentPane().add(txtBairro);

		lblNewLabel_2_1_4 = new JLabel("Complemento");
		lblNewLabel_2_1_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_2_1_4.setBounds(277, 335, 92, 14);
		getContentPane().add(lblNewLabel_2_1_4);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtComplemento.setBounds(366, 332, 139, 20);
		getContentPane().add(txtComplemento);

		lblNewLabel_2_1_2 = new JLabel("UF");
		lblNewLabel_2_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_2_1_2.setBounds(328, 295, 23, 14);
		getContentPane().add(lblNewLabel_2_1_2);

		lblNewLabel_2_1_5 = new JLabel("Referência");
		lblNewLabel_2_1_5.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_2_1_5.setBounds(36, 383, 92, 14);
		getContentPane().add(lblNewLabel_2_1_5);

		txtReferencia = new JTextField();
		txtReferencia.setColumns(10);
		txtReferencia.setBorder(new LineBorder(new Color(128, 0, 0), 2, true));
		txtReferencia.setBounds(128, 380, 274, 20);
		getContentPane().add(txtReferencia);

		cboUF = new JComboBox();
		cboUF.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUF.setBounds(356, 291, 46, 22);
		getContentPane().add(cboUF);

		// metodo para marcar o formato do texto
		MaskFormatter msf = null;
		try {
			msf = new MaskFormatter("(##)#####-####");
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtContato = new JFormattedTextField(msf);
		txtContato.setBorder(new LineBorder(new Color(128, 0, 0), 2));

		txtContato.setBounds(128, 162, 274, 20);
		getContentPane().add(txtContato);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Clientes.class.getResource("/img/redbackground.jpg")));
		lblNewLabel_3.setBounds(0, 498, 531, 53);
		getContentPane().add(lblNewLabel_3);

	}

	/**
	 * buscarCep
	 */
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCEP.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUF.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {

					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtCpf.setText(null);
		txtContato.setText(null);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		txtCEP.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtCidade.setText(null);
		txtBairro.setText(null);
		txtComplemento.setText(null);
		txtReferencia.setText(null);
		listaClientes.setEnabled(true);

	}

	private void adicionar() {

		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
			txtNome.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CPF do cliente");
			txtCpf.requestFocus();
		} else if (txtContato.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do cliente");
			txtContato.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o endereço do cliente");
			txtContato.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o numero do endereço do cliente");
			txtContato.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do cliente");
			txtContato.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do cliente");
			txtContato.requestFocus();

		} else {

			String create = "insert into clientes(nome,cpf,contato,cep,endereco,numero,complemento,bairro,cidade,uf,referencia) values (?,?,?,?,?,?,?,?,?,?,?)";

			try {

				con = dao.conectar();

				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtContato.getText());
				pst.setString(4, txtCEP.getText());
				pst.setString(5, txtEndereco.getText());
				pst.setString(6, txtNumero.getText());
				pst.setString(7, txtComplemento.getText());
				pst.setString(8, txtBairro.getText());
				pst.setString(9, txtCidade.getText());
				pst.setString(10, cboUF.getSelectedItem().toString());
				pst.setString(11, txtReferencia.getText());

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Cliente Cadastrado");

				limparCampos();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Cliente não adicionado.\nEste CPF já está sendo utilizado.");
				txtCpf.setText(null);
				txtCpf.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void editar() {

		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do cliente");
			txtNome.requestFocus();

		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CPF do cliente");
			txtCpf.requestFocus();

		} else if (txtContato.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número de contato do cliente");
			txtContato.requestFocus();

		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número de telefone do cliente");
			txtEndereco.requestFocus();

		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número da casa do cliente");
			txtNumero.requestFocus();

		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o bairro do cliente");
			txtBairro.requestFocus();

		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a cidade do cliente");
			txtCidade.requestFocus();

		} else {

			String update = "update clientes set nome=?,cpf=?, contato=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, uf=?, referencia=? where idcli=?";
			try {

				con = dao.conectar();

				pst = con.prepareStatement(update);

				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtContato.getText());
				pst.setString(4, txtCEP.getText());
				pst.setString(5, txtEndereco.getText());
				pst.setString(6, txtNumero.getText());
				pst.setString(7, txtComplemento.getText());
				pst.setString(8, txtBairro.getText());
				pst.setString(9, txtCidade.getText());
				pst.setString(10, cboUF.getSelectedItem().toString());
				pst.setString(11, txtReferencia.getText());
				pst.setString(12, txtID.getText());

				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Dados do cliente editados com sucesso.");

				limparCampos();

				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Cliente não adicionado.\nEste CPF já está sendo utilizado.");
				txtCpf.setText(null);
				txtCpf.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void excluirCliente() {

		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {

			String delete = "delete from clientes where idcli=?";

			try {

				con = dao.conectar();

				pst = con.prepareStatement(delete);

				pst.setString(1, txtID.getText());

				pst.executeUpdate();

				limparCampos();

				JOptionPane.showMessageDialog(null, "Cliente excluído");

				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null,
						"Cliente não excluído.\nEste cliente ainda tem serviço(s) pendente(s).");
			} catch (Exception e2) {
				System.out.println(e2);
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

		String readLista = "select * from clientes where nome like '" + txtNome.getText() + "%'" + "order by nome";
		try {

			con = dao.conectar();

			pst = con.prepareStatement(readLista);

			rs = pst.executeQuery();

			while (rs.next()) {

				scrollPane.setVisible(true);

				modelo.addElement(rs.getString(2));

				if (txtNome.getText().isEmpty()) {
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

			String readBuscaLista = "select * from clientes where nome like '" + txtNome.getText() + "%'"
					+ "order by nome limit " + (linha) + ", 1";
			try {

				con = dao.conectar();

				pst = con.prepareStatement(readBuscaLista);

				rs = pst.executeQuery();
				if (rs.next()) {

					scrollPane.setVisible(false);
					txtID.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtCpf.setText(rs.getString(3));
					txtContato.setText(rs.getString(4));
					txtCEP.setText(rs.getString(5));
					txtEndereco.setText(rs.getString(6));
					txtNumero.setText(rs.getString(7));
					txtComplemento.setText(rs.getString(8));
					txtBairro.setText(rs.getString(9));
					txtCidade.setText(rs.getString(10));
					cboUF.setSelectedItem(rs.getString(11));
					txtReferencia.setText(rs.getString(12));
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
}
