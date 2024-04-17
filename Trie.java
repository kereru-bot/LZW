class Trie {
    private int nextPhraseNum;
    private TrieNode[] children;
    private int maxPhrases;

    public Trie(int maxPhrases) {
        //for every hex character
        nextPhraseNum = 0;
        children = new TrieNode[17];
        this.maxPhrases = maxPhrases;
        for(int i = 0; i < children.length; i++) {
            if(i < 10) {
                //0-9
                children[i] = new TrieNode((char)(i + 48), nextPhraseNum, null);
            } else {
                //A-G
                children[i] = new TrieNode((char)(i + 55), nextPhraseNum, null);
                //G IS TO MARK END OF FILE
            }
            nextPhraseNum++;
        }
    }

    //maybe make it return node instead of phrase number to make parent searching easier
    public int insert(String prefix, String suffix) {
        if(prefix == "") {
            //System.out.println("here");
            return -1;
        }

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

        int phrase;
        //remove the first char of the prefix and continue down the trie
        if(getNextPhraseNumber() == getMaxPhrases()) {
            //trie is full
            //dont insert
            //return matching phrase
            phrase = children[index].insert(prefix, suffix.charAt(0), -1);
        } else {
            phrase = children[index].insert(prefix, suffix.charAt(0), nextPhraseNum);
            if(phrase != -1) {
                //only increment if given phrase is new phrase in the trie
                nextPhraseNum++;
            }
        }
        
        //stop incrementing phrase number like this, check if pattern is in trie or not
        return phrase;
    }

    //returns the phrase for the given phrase number
    public TrieNode findPhrase(int phraseNum) {
        if(phraseNum < 16) {
            return children[phraseNum];
        }

        TrieNode node = null;
        for(int i = 0; i < 16; i++) {
            //call find on every child
            //each child will also call find
            node = children[i].findPhrase(phraseNum);
            //is the correct node found
            if(node != null) {
                return node;
            }
        }
        return node;
    }
  

    public TrieNode[] getChildren() {
        return children;
    }

    public int getNextPhraseNumber() {
        return nextPhraseNum;
    }

    public int getMaxPhrases() {
        return maxPhrases;
    }
}