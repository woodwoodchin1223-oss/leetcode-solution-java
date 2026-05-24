class Solution {
    String word;
    public boolean exist(char[][] board, String word) {
        this.word = word;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean tmp = backtrack(board, i, j, 0);
                if (tmp == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean backtrack(char[][] board, int x, int y, int index) {
        if (index == word.length()) {
            return true;
        }
        if (word.charAt(index) != board[x][y]) {
            return false;
        }
        if (index == word.length() - 1) return true;
        char tmp = board[x][y];
        board[x][y] = '*';
        int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
        };
        for (int i = 0; i < 4; i++) {
            int nx = x + directions[i][0];
            if (nx < 0 || nx >= board.length) continue;
            int ny = y + directions[i][1];
            if (ny < 0 || ny >= board[0].length) continue;

            if (backtrack(board, nx, ny, index + 1) == true) {
                return true;
            }
        }
        board[x][y] = tmp;
        return false;
    }
}
