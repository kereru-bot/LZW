class Trie {
    private int nextPhraseNum;
    private TrieNode[] children;

    public Trie() {
        //for every hex character
        nextPhraseNum = 0;
        children = new TrieNode[17];
        for(int i = 0; i < children.length; i++) {
            if(i < 10) {
                //0-9
                children[i] = new TrieNode((char)(i + 48), nextPhraseNum);
            } else {
                //A-G
                children[i] = new TrieNode((char)(i + 55), nextPhraseNum);
                //G IS TO MARK END OF FILE
            }
            nextPhraseNum++;
        }
    }

    public int insert(String prefix, String suffix) {
        //there will always be at least one prefix
        char indexChoice = prefix.charAt(0);
        int index = (int)indexChoice;
        //System.out.println(index);
        if(index > 57) {
            index -= 55;
        } else {
            index -= 48;
        }

        if(prefix.length() == 1) {
            prefix = null;
        } else {
            prefix = prefix.substring(1, prefix.length());
        }
        //remove the first char of the prefix and continue down the trie
        nextPhraseNum++;
        return children[index].insert(prefix, suffix.charAt(0), nextPhraseNum - 1);
    }


    public TrieNode[] getChildren() {
        return children;
    }

    public int getNextPhraseNumber() {
        return nextPhraseNum;
    }
}