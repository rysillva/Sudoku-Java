import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberDocument extends PlainDocument {

    @Override
    public void insertString(int offset, String texto, AttributeSet atributo)
            throws BadLocationException {

        if (texto == null) {
            return;
        }

        // Permite apenas um caractere
        if (getLength() >= 1) {
            return;
        }

        // Aceita somente números de 1 a 9
        if (texto.matches("[1-9]")) {
            super.insertString(offset, texto, atributo);
        }

    }

}