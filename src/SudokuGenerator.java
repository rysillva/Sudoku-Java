import java.util.ArrayList;
import java.util.Collections;

public class SudokuGenerator {
    private Sudoku sudoku;
    private Sudoku solucao;
    private SudokuSolver solver;

    public SudokuGenerator() {
        solver = new SudokuSolver();
    }

    public Sudoku gerarNovoSudoku() {
        sudoku = new Sudoku();
        gerar(0, 0);
        solucao = sudoku.copiar();
        removerNumeros(40);
        return sudoku;
    }

    public Sudoku getSolucao() {
        return solucao;
    }

    private boolean gerar(int linha, int coluna) {
        if (linha == 9) {
            return true;
        }

        int proximaLinha = linha;
        int proximaColuna = coluna + 1;

        if (proximaColuna == 9) {
            proximaColuna = 0;
            proximaLinha++;
        }
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numeros.add(i);
        }
        Collections.shuffle(numeros);
        for (int numero : numeros) {
            if (solver.numeroValido(sudoku, linha, coluna, numero)) {
                sudoku.setValor(linha, coluna, numero);
                if (gerar(proximaLinha, proximaColuna)) {
                    return true;
                }
                sudoku.setValor(linha, coluna, 0);
       }
}
 return false;
    }
    private void removerNumeros(int quantidade) {
        while (quantidade > 0) {
            int linha = (int) (Math.random() * 9);
            int coluna = (int) (Math.random() * 9);

            if (sudoku.getValor(linha, coluna) != 0) {
                sudoku.setValor(linha, coluna, 0);
                quantidade--;

            }
        }
    }
}
