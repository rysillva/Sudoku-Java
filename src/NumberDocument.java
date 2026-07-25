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
        if (getLength() >= 1) {
            return;
        }
        if (texto.matches("[1-9]")) {
            super.insertString(offset, texto, atributo);
   }
 }
}
