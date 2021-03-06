/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.facom.edpoo.tt_search_compress;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

import edu.princeton.cs.algs4.StdOut;
import java.io.ByteArrayInputStream;
import java.io.File;

import java.io.IOException;
import java.util.PriorityQueue;

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

    public void expansor(BinaryIn in, File file) {
        BinaryOut b1 = new BinaryOut(file.toString() + "Expandido.txt");
        NoTrie trie = leTrie(in);
        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            StdOut.println("CCC");
            NoTrie no = trie;
            while (!no.ehFolha()) {
                if (in.readBoolean()) {
                    StdOut.println("DDD");
                    no = no.dir;
                } else {
                    StdOut.println("EEE");
                    no = no.esq;
                }
            }
            StdOut.println("FFFF");
            b1.write(no.simbolo, 8);
        }
        b1.flush();
        b1.close();
    }

    private NoTrie leTrie(BinaryIn in) {
        if (in.readBoolean()) {
            StdOut.println("AAAA");
            return new NoTrie(in.readChar());
        } else {
            StdOut.println("BBB");
            return new NoTrie(leTrie(in), leTrie(in));
        }
    }

}
