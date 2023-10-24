package view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//PlainDocument -> recursos para formatação
public class Validador extends PlainDocument {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//variável que irá armazenar o npumero máximo de caracteres permitidos
	private int limite;
	//construtor personalizado -> será usado pelas caixas de texto JTextField
	public Validador(int limite) {
		super();
		this.limite = limite;
	}
	//método interno para validar o limite de caracteres
	//BadLocaion - tratamento de exceções
	public void insertString(int ofs, String str, AttributeSet a) throws BadLocationException {
		//se o limite não for ultrapassado permitir a digitação
		if ((getLength() + str.length()) <= limite) {
			super.insertString(ofs, str, a);
			
		}
		
	}
	

}
