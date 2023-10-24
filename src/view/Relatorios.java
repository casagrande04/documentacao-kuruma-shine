package view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Font;

public class Relatorios extends JDialog {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnEstoque;
	private JButton btnValidade;
	private JButton btnServicos_1;
	private JButton btnPatrimonio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorios dialog = new Relatorios();
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
	public Relatorios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/img/serviços.png")));
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Relatorios");
		setResizable(false);
		setModal(true);
		setBounds(350, 150, 800, 600);
		getContentPane().setLayout(null);

		JButton btnClientes = new JButton("");
		btnClientes.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setContentAreaFilled(false);
		btnClientes.setToolTipText("Clientes");
		btnClientes.setIcon(new ImageIcon(Relatorios.class.getResource("/img/cliente.png")));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnClientes.setBounds(71, 60, 150, 135);
		getContentPane().add(btnClientes);

		JButton btnServicos = new JButton("");
		btnServicos.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.setContentAreaFilled(false);
		btnServicos.setIcon(new ImageIcon(Relatorios.class.getResource("/img/serviços.png")));
		btnServicos.setToolTipText("Serviços");
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioServicos();
			}
		});
		btnServicos.setBounds(302, 60, 151, 135);
		getContentPane().add(btnServicos);

		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(107, 198, 79, 52);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Serviços");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(338, 198, 79, 52);
		getContentPane().add(lblNewLabel_1);

		btnEstoque = new JButton("");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioEstoque();
			}
		});
		btnEstoque.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstoque.setIcon(new ImageIcon(Relatorios.class.getResource("/img/reposicao.png")));
		btnEstoque.setToolTipText("Clientes");
		btnEstoque.setContentAreaFilled(false);
		btnEstoque.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		btnEstoque.setBounds(71, 306, 150, 135);
		getContentPane().add(btnEstoque);

		JLabel lblReposio = new JLabel("Estoque");
		lblReposio.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblReposio.setBounds(117, 444, 79, 52);
		getContentPane().add(lblReposio);

		btnValidade = new JButton("");
		btnValidade.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnValidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioValidade();
			}
		});
		btnValidade.setIcon(new ImageIcon(Relatorios.class.getResource("/img/validade.png")));
		btnValidade.setToolTipText("Clientes");
		btnValidade.setContentAreaFilled(false);
		btnValidade.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		btnValidade.setBounds(302, 306, 150, 135);
		getContentPane().add(btnValidade);

		JLabel lblNewLabel_3 = new JLabel("Validade");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(346, 444, 79, 52);
		getContentPane().add(lblNewLabel_3);

		btnPatrimonio = new JButton("");
		btnPatrimonio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioPatrimonio();
			}
		});
		btnPatrimonio.setIcon(new ImageIcon(Relatorios.class.getResource("/img/patrimonio.png")));
		btnPatrimonio.setToolTipText("Clientes");
		btnPatrimonio.setContentAreaFilled(false);
		btnPatrimonio.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		btnPatrimonio.setBounds(523, 306, 150, 135);
		getContentPane().add(btnPatrimonio);

		JLabel lblNewLabel_4 = new JLabel("Patrimônio");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(557, 444, 103, 52);
		getContentPane().add(lblNewLabel_4);

		btnServicos_1 = new JButton("");
		btnServicos_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioFornecedores();
			}
		});
		btnServicos_1.setIcon(new ImageIcon(Relatorios.class.getResource("/img/fornecedor.png")));
		btnServicos_1.setToolTipText("Serviços");
		btnServicos_1.setContentAreaFilled(false);
		btnServicos_1.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		btnServicos_1.setBounds(523, 60, 151, 135);
		getContentPane().add(btnServicos_1);

		JLabel lblNewLabel_1_1 = new JLabel("Fornecedores");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(548, 198, 101, 52);
		getContentPane().add(lblNewLabel_1_1);

	}

	private void relatorioClientes() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Clientes:"));
			document.add(new Paragraph(" "));
			String readClientes = "select nome,contato,cpf from clientes order by nome";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readClientes);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(3);
				PdfPCell col1 = new PdfPCell(new Paragraph("Cliente"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
				PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));

				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void relatorioServicos() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("servicos.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Clientes:"));
			document.add(new Paragraph(" "));
			String readClientes = "select\r\n"
					+ "servicos.os,servicos.dataOS,servicos.veiculo,servicos.servico,servicos.valor,\r\n"
					+ "clientes.nome, clientes.contato\r\n" + "from servicos\r\n" + "inner join clientes\r\n"
					+ "on servicos.idcli = clientes.idcli;";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readClientes);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(6);
				PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Data"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Veiculo"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Tipo de Serviço"));
				PdfPCell col5 = new PdfPCell(new Paragraph("Valor"));
				PdfPCell col6 = new PdfPCell(new Paragraph("Nome"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				tabela.addCell(col6);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(6));
				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("servicos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void relatorioEstoque() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("estoque.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Estoque:"));
			document.add(new Paragraph(" "));
			String readEstoque = "select codigo,produto,date_format(dataval, '%d/%m%Y') as validade, estoque, estoquemin as estoque_mínimo from produtos where estoque < estoquemin";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readEstoque);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(5);
				PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Validade"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Estoque"));
				PdfPCell col5 = new PdfPCell(new Paragraph("Estoque mínimo"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));

				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("estoque.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void relatorioValidade() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("estoque.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Estoque:"));
			document.add(new Paragraph(" "));
			String readEstoque = "select codigo,produto,date_format(dataval, '%d/%m%Y') as validade,estoque, estoquemin as estoque_mínimo from produtos where estoque < estoquemin;";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readEstoque);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(4);
				PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Validade"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Entrada"));

				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);

				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));

				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("estoque.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void relatorioFornecedores() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("fornecedores.pdf"));
			document.open();
			Date dataFornecedores = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataFornecedores)));
			document.add(new Paragraph("Fornecedores:"));
			document.add(new Paragraph(" "));
			String readFornecedores = "select codefornecedor, razaosocial as Nome from fornecedores";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readFornecedores);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(2);
				PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Fornecedores"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("fornecedores.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void relatorioPatrimonio() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("patrimonio.pdf"));
			document.open();
			Date dataPatrimonio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataPatrimonio)));
			document.add(new Paragraph("Patrimônio:"));
			document.add(new Paragraph(" "));
			String readPatrimonio = "select sum(custo * estoque) as Total from produtos";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readPatrimonio);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(1);
				PdfPCell col1 = new PdfPCell(new Paragraph("Total"));
				tabela.addCell(col1);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("patrimonio.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
