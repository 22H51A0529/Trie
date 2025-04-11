import java.util.*;

public class Class {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Trie root = new Trie();
        int t = sc.nextInt();
        sc.nextLine();

        while (t-- != 0) {
            int op = sc.nextInt();

            if (op == 1) {
                String s = sc.next();
                insert(root, s);
            } 
            else if (op == 2) {
                String s = sc.next();
                if (doesExists(root, s)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            } 
            else if (op == 3) {
                String s = sc.next();
                List<String> li = new ArrayList<>();
                getAllWordsWithPrefix(root, li, s);
                if (li.isEmpty()) {
                    System.out.println("No words found with prefix " + s);
                } else {
                    System.out.println("The words with prefix " + s + " are:");
                    for (int j = 0; j < li.size(); j++) {
                        System.out.println(li.get(j));
                    }
                }
            } 
            else if (op == 4) {
                List<String> li = new ArrayList<>();
                String te = "";
                help(root, li, te);
                if (li.isEmpty()) {
                    System.out.println("No words in the trie.");
                } else {
                    System.out.println("The words are:");
                    for (int j = 0; j < li.size(); j++) {
                        System.out.println(li.get(j));
                    }
                }
            } 
            else if (op == 5) {
                int x = sc.nextInt();
                t=t+x;
            }
        }
    }

    static void insert(Trie root, String s) {
        Trie te = root;
        for (char ci : s.toCharArray()) {
            int ind = ci - 'a';
            if (te.ch[ind] == null) {
                te.ch[ind] = new Trie();
            }
            te = te.ch[ind];
            te.wc++;
        }
        te.ended = true;
    }

    static void help(Trie root, List<String> l, String te) {
        if (root.ended) {
            l.add(te);
        }
        for (int i = 0; i < 26; i++) {
            if (root.ch[i] != null) {
                char ch = (char) (i + 'a');
                help(root.ch[i], l, te + ch);
            }
        }
    }

    static boolean doesExists(Trie root, String s) {
        Trie te = root;
        for (char ci : s.toCharArray()) {
            int ind = ci - 'a';
            if (te.ch[ind] == null)
                return false;
            te = te.ch[ind];
        }
        return te.ended;
    }

    static void getAllWordsWithPrefix(Trie root, List<String> li, String ps) {
        Trie te = root;
        for (char ci : ps.toCharArray()) {
            int ind = ci - 'a';
            if (te.ch[ind] == null)
                return;
            te = te.ch[ind];
        }
        help(te, li, ps);
    }

    
}

class Trie {
    Trie ch[];
    boolean ended;
    int wc;

    Trie() {
        ch = new Trie[26];
        ended = false;
        wc = 0;
    }
}
