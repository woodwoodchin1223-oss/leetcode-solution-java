class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    String word = null;
    public TrieNode() {}
}

class Solution {
    ArrayList<String> result;
    char[][] board;
    public List<String> findWords(char[][] board, String[] words) {
        result = new ArrayList<String>();
        this.board = board;
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            String current = words[i];
            TrieNode currentNode = root;
            for (Character c : current.toCharArray()) {
                if (currentNode.children.get(c) == null) {
                    currentNode.children.put(c, new TrieNode());
                }
                currentNode = currentNode.children.get(c);
            }
            currentNode.word = current;
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.children.containsKey(board[i][j]) == true) {
                    backtrack(i, j, root);
                }
            }
        }
        return this.result;
    }

    public void backtrack(int x, int y, TrieNode node) {
        Character c = this.board[x][y];
        TrieNode currentNode = node.children.get(c);
        if (currentNode.word != null) {
            this.result.add(currentNode.word);
            currentNode.word = null;
        }
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        board[x][y] = '#';
        for (int i = 0; i < 4; i++) {
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];
            if (nx < 0 || nx >= board.length) continue;
            if (ny < 0 || ny >= board[0].length) continue;
            if (currentNode.children.containsKey(board[nx][ny]) == true) {
                backtrack(nx, ny, currentNode);
            }
        }
        board[x][y] = c;
        return;
    }
}
