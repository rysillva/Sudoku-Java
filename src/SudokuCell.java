import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;

public class SudokuCell extends JTextField {
    public SudokuCell() {
        configurarCelula();
    }

    private void configurarCelula() {

        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Segoe UI", Font.BOLD, 28));
        setPreferredSize(new Dimension(60, 60));
        setBackground(Color.WHITE);
        setSelectionColor(new Color(120, 170, 255));
        setCaretColor(Color.BLUE);
        setDocument(new NumberDocument());
    }
}
