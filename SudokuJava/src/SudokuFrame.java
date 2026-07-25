
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class SudokuFrame extends JFrame {

    private SudokuCell[][] celulas = new SudokuCell[9][9];

private SudokuSolver solver;
private Sudoku sudoku;
private Sudoku solucao;
private SudokuGenerator gerador;
private JButton botaoNovo;
private JButton botaoVerificar;
private JButton botaoResolver;
private JButton botaoDica;

    public SudokuFrame() {

    gerador = new SudokuGenerator();
    solver = new SudokuSolver();

    configurarJanela();

    add(criarTitulo(), BorderLayout.NORTH);
    add(criarTabuleiro(), BorderLayout.CENTER);
    add(criarPainelBotoes(), BorderLayout.SOUTH);

    carregarSudoku(
    );

    setVisible(true);

}

    

    private void configurarJanela() {

        setTitle("Sudoku");

        setSize(720, 780);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

    }

    private JLabel criarTitulo() {

        JLabel titulo = new JLabel("SUDOKU");

        titulo.setHorizontalAlignment(JLabel.CENTER);

        titulo.setFont(new Font("Segoe UI", Font.BOLD, 34));

        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        return titulo;

    }

    private JPanel criarTabuleiro() {

        JPanel painel = new JPanel();

        painel.setLayout(new GridLayout(9, 9));

        painel.setBackground(new Color(220, 220, 220));

        for (int linha = 0; linha < 9; linha++) {

            for (int coluna = 0; coluna < 9; coluna++) {

                SudokuCell celula = new SudokuCell();

                int cima = (linha % 3 == 0) ? 3 : 1;
                int esquerda = (coluna % 3 == 0) ? 3 : 1;
                int baixo = (linha == 8) ? 3 : 1;
                int direita = (coluna == 8) ? 3 : 1;

                celula.setBorder(new MatteBorder(cima, esquerda, baixo, direita, Color.BLACK));

                celulas[linha][coluna] = celula;

                painel.add(celula);

            }

        }

        return painel;

    }

    
private JPanel criarPainelBotoes() {

    JPanel painel = new JPanel();

    botaoNovo = new JButton("Novo");
    botaoVerificar = new JButton("Verificar");
    botaoResolver = new JButton("Resolver");
    botaoDica = new JButton("Dica");

    painel.add(botaoNovo);
    painel.add(botaoVerificar);
    painel.add(botaoResolver);
    painel.add(botaoDica);

    botaoNovo.addActionListener(e -> carregarSudoku());
    botaoResolver.addActionListener(e -> resolverSudoku());
    botaoVerificar.addActionListener(e -> verificarSudoku());
    botaoDica.addActionListener(e -> darDica());

    return painel;

}
        

    

    private void carregarSudoku() {

    sudoku = gerador.gerarNovoSudoku();
    solucao = gerador.getSolucao();


    sudoku = gerador.gerarNovoSudoku();

    for (int linha = 0; linha < 9; linha++) {

        for (int coluna = 0; coluna < 9; coluna++) {

            int valor = sudoku.getValor(linha, coluna);

            SudokuCell celula = celulas[linha][coluna];

            if (valor == 0) {

                celula.setText("");

                celula.setEditable(true);

                celula.setBackground(Color.WHITE);

                celula.setForeground(Color.BLUE);

            } else {

                celula.setText(String.valueOf(valor));

                celula.setEditable(false);

                celula.setBackground(new Color(235, 235, 235));

                celula.setForeground(Color.BLACK);

            }

        }

    }

}

private void resolverSudoku() {

    atualizarSudokuDaTela();
    Sudoku copia = sudoku.copiar();

    if (solver.resolver(copia)) {

        sudoku = copia;

        atualizarTela(sudoku);

    }

}

private void atualizarTela(Sudoku sudokuResolvido) {

    for (int linha = 0; linha < 9; linha++) {

        for (int coluna = 0; coluna < 9; coluna++) {

            int valor = sudokuResolvido.getValor(linha, coluna);

            SudokuCell celula = celulas[linha][coluna];

            celula.setText(String.valueOf(valor));

            celula.setEditable(false);

            celula.setBackground(new Color(235, 235, 235));

            celula.setForeground(Color.BLACK);

        }

    }

}

private void verificarSudoku() {

    atualizarSudokuDaTela();

    boolean venceu = true;

    for (int linha = 0; linha < 9; linha++) {

        for (int coluna = 0; coluna < 9; coluna++) {

            SudokuCell celula = celulas[linha][coluna];

            // Ignora as células fixas
            if (!celula.isEditable()) {
                continue;
            }

            String texto = celula.getText();

            // Se estiver vazia, ainda não venceu
            if (texto.isEmpty()) {
                venceu = false;
                continue;
            }

            int valorDigitado = Integer.parseInt(texto);
            int valorCorreto = solucao.getValor(linha, coluna);

            if (valorDigitado == valorCorreto) {

                celula.setForeground(new Color(0, 150, 0));

            } else {

                celula.setForeground(Color.RED);
                venceu = false;

            }

        }

    }

    if (venceu) {

        JOptionPane.showMessageDialog(
                this,
                "🎉 Parabéns!\nVocê concluiu o Sudoku!",
                "Vitória",
                JOptionPane.INFORMATION_MESSAGE
        );

        carregarSudoku();

    }

}

private void darDica() {

    atualizarSudokuDaTela();
    Random random = new Random();

    int tentativas = 0;

    while (tentativas < 100) {

        int linha = random.nextInt(9);
        int coluna = random.nextInt(9);

        SudokuCell celula = celulas[linha][coluna];

        if (celula.isEditable() && sudoku.getValor(linha, coluna) == 0) {

            int valor = solucao.getValor(linha, coluna);

            celula.setText(String.valueOf(valor));

            celula.setEditable(false);

            celula.setBackground(new Color(220, 255, 220));

            celula.setForeground(new Color(0, 120, 0));

            sudoku.setValor(linha, coluna, valor);

            return;

        }

        tentativas++;

    }

    JOptionPane.showMessageDialog(
            this,
            "Não há mais dicas disponíveis.",
            "Sudoku",
            JOptionPane.INFORMATION_MESSAGE);

}

private void atualizarSudokuDaTela() {

    for (int linha = 0; linha < 9; linha++) {

        for (int coluna = 0; coluna < 9; coluna++) {

            SudokuCell celula = celulas[linha][coluna];

            String texto = celula.getText().trim();

            if (texto.isEmpty()) {

                sudoku.setValor(linha, coluna, 0);

            } else {

                sudoku.setValor(linha, coluna, Integer.parseInt(texto));

            }

        }

    }

}

private boolean podeInserirNumero(int linha, int coluna, int numero) {

    int valorAtual = sudoku.getValor(linha, coluna);

    sudoku.setValor(linha, coluna, 0);

    boolean permitido = solver.numeroValido(sudoku, linha, coluna, numero);

    sudoku.setValor(linha, coluna, valorAtual);

    return permitido;
}

}