/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author 201619060655
 */
public class Huffman {

    File file;

    public void compressor(BinaryIn in, File file) throws IOException {
        String entrada = in.readString();
        char[] mensagem = entrada.toCharArray();
        NoTrie trie = criaTrie(mensagem);
        BinaryOut b = new BinaryOut(file.toString() + ".bin");

        String[] st = new String[1000];
        criaTabelaDeCodigos(st, trie, "");

        escreveTrie(trie, b);

        b.write(mensagem.length);

        // use Huffman code to encode input
        for (int i = 0; i < mensagem.length; i++) {
            String code = st[mensagem[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    b.write(false);
                } else if (code.charAt(j) == '1') {
                    b.write(true);
                } else {
                    throw new IllegalStateException("Illegal state");
                }
            }
        }

        // close output stream
        b.close();
    }

    private void criaTabelaDeCodigos(String[] st, NoTrie no, String s) {
        if (!no.ehFolha()) {
            criaTabelaDeCodigos(st, no.esq, s + '0');
            criaTabelaDeCodigos(st, no.dir, s + '1');
        } else {
            st[no.simbolo] = s;
        }
    }

    private void escreveCodigo(String code, BinaryOut b) throws IOException {

        for (int j = 0; j < code.length(); j++) {
            if (code.charAt(j) == '0') {
                b.write(false);
            } else if (code.charAt(j) == '1') {
                b.write(true);
            } else {
                throw new IllegalStateException("Illegal state");
            }

        }

    }

    private NoTrie criaTrie(char[] mensagem) {
        int[] freq = new int[256];
        for (char c : mensagem) {
            ++freq[c];
        }
        PriorityQueue<NoTrie> tries = new PriorityQueue<>();
        for (char c = 0; c < 256; ++c) {
            if (freq[c] > 0) {
                tries.add(new NoTrie(c, freq[c]));
            }
        }
        while (tries.size() > 1) {
            NoTrie t1 = tries.remove();
            NoTrie t2 = tries.remove();
            tries.add(new NoTrie(t1.frequencia + t2.frequencia, t1, t2));
        }
        return tries.remove();
    }

    private void escreveTrie(NoTrie n, BinaryOut b) {
        if (n.ehFolha()) {
            b.write(true);
            b.write(n.simbolo);
        } else {
            b.write(false);
            escreveTrie(n.esq, b);
            escreveTrie(n.dir, b);
        }
    }

    public static void expansor() throws IOException {
        NoTrie trie = leTrie();
        int n = BinaryStdIn.readInt();	

        for (int i = 0; i < n; ++i) {
            NoTrie no = trie;

            while (!no.ehFolha()) {
                boolean bit = BinaryStdIn.readBoolean();
                if (bit) {
                    no = no.dir;
                } else {
                    no = no.esq;
                }
            }
            if(no.simbolo==('>')){
                BinaryStdOut.write(no.simbolo, 8);
                BinaryStdOut.write('\n');

            } else {
                BinaryStdOut.write(no.simbolo, 8);

            }
        }

        BinaryStdOut.close();
    }

    private static NoTrie leTrie() {
        boolean isLeaf = BinaryStdIn.readBoolean();

        if (isLeaf) {
            return new NoTrie(BinaryStdIn.readChar());
        } else {
            return new NoTrie(leTrie(), leTrie());
        }
    }

}
