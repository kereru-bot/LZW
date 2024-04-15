class TrieNode {
    private char suffix;
    private TrieNode[] children;
    private int phraseNum;
    //private int phraseNum;
    //must have multiple children
    //can use a hash table for 0-F hexadecimal good idea
    public TrieNode(char suffix, int phraseNum) {
        this.suffix = suffix;
        this.phraseNum = phraseNum;
        //can store all hex possibilities 
        children = new TrieNode[17];
    }

    public char getSuffix() {
        return this.suffix;
    }

    //returns phrase number of parent node
    //return -1 if inserted phrase is already part of trie
    public int insert(String prefix, char nextSuffix, int phraseNum) {
        int index = 0;

        //at the destination 
        if(prefix == null) {
            if(nextSuffix > 57) {
                //assume it's A-F
                index = nextSuffix - 55;
            } else {
                //assume it's 0-9
                index = nextSuffix - 48;
            }
            
            //this means that the input phrase was already part of the trie
            if(children[index] != null) {
                return -1;
            }

            children[index] = new TrieNode(nextSuffix, phraseNum);
            
            return this.phraseNum;
        }
        
        //traverse to the next point in the trie
        char indexChoice = prefix.charAt(0);
        //String indexChoice = prefix.substring(0,1);
        index = (int)indexChoice;
        
        if(index > 57) {
            //assume it's A-F
            index = index - 55;
        } else {
            //assume it's 0-9
            index = index - 48;
        }

        if(prefix.length() == 1) {
            prefix = null;
        } else {
            prefix = prefix.substring(1,prefix.length());
        }


        return children[index].insert(prefix,nextSuffix,phraseNum);
    }
}