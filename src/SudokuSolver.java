public class SudokuSolver {

    public boolean resolver(Sudoku sudoku) {

        for (int linha = 0; linha < 9; linha++) {

            for (int coluna = 0; coluna < 9; coluna++) {

                if (sudoku.getValor(linha, coluna) == 0) {

                    for (int numero = 1; numero <= 9; numero++) {

                        if (numeroValido(sudoku, linha, coluna, numero)) {

                            sudoku.setValor(linha, coluna, numero);

                            if (resolver(sudoku)) {
                                return true;
                            }

                            sudoku.setValor(linha, coluna, 0);

                        }

                    }

                    return false;

                }

            }

        }

        return true;

    }

    public boolean numeroValido(Sudoku sudoku, int linha, int coluna, int numero) {

    // Remove temporariamente o valor da posição
    int valorAtual = sudoku.getValor(linha, coluna);
    sudoku.setValor(linha, coluna, 0);

    boolean valido = !existeNaLinha(sudoku, linha, numero)
            && !existeNaColuna(sudoku, coluna, numero)
            && !existeNoBloco(sudoku, linha, coluna, numero);

    // Restaura o valor original
    sudoku.setValor(linha, coluna, valorAtual);

    return valido;
}

    private boolean existeNaLinha(Sudoku sudoku, int linha, int numero) {

        for (int coluna = 0; coluna < 9; coluna++) {

            if (sudoku.getValor(linha, coluna) == numero) {
                return true;
            }

        }

        return false;

    }

    private boolean existeNaColuna(Sudoku sudoku, int coluna, int numero) {

        for (int linha = 0; linha < 9; linha++) {

            if (sudoku.getValor(linha, coluna) == numero) {
                return true;
            }

        }

        return false;

    }

    private boolean existeNoBloco(Sudoku sudoku, int linha, int coluna, int numero) {

        int inicioLinha = linha - (linha % 3);
        int inicioColuna = coluna - (coluna % 3);

        for (int i = inicioLinha; i < inicioLinha + 3; i++) {

            for (int j = inicioColuna; j < inicioColuna + 3; j++) {

                if (sudoku.getValor(i, j) == numero) {
                    return true;
                }

            }

        }

        return false;

    }

    
}