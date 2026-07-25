public class Sudoku {

    private int[][] tabuleiro;

    // Construtor padrão
    public Sudoku() {
        tabuleiro = new int[9][9];
    }

    // Construtor que recebe uma matriz
    public Sudoku(int[][] matriz) {

        tabuleiro = new int[9][9];

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {
                tabuleiro[linha][coluna] = matriz[linha][coluna];
            }
        }
    }

    public int getValor(int linha, int coluna) {
        return tabuleiro[linha][coluna];
    }

    public void setValor(int linha, int coluna, int valor) {
        tabuleiro[linha][coluna] = valor;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void limpar() {

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {
                tabuleiro[linha][coluna] = 0;
            }
        }

    }

    public Sudoku copiar() {

        Sudoku copia = new Sudoku(tabuleiro);

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {
                copia.setValor(linha, coluna, this.getValor(linha, coluna));
            }
        }

        return copia;
    }

    public boolean estaCompleto() {

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {

                if (tabuleiro[linha][coluna] == 0) {
                    return false;
                }

            }
        }

        return true;
    }

    public int[][] copiarTabuleiro() {

        int[][] copia = new int[9][9];

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {
                copia[linha][coluna] = tabuleiro[linha][coluna];
            }
        }

        return copia;
    }

}