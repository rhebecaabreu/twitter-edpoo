/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.facom.edpoo.tt_search_compress;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryStdOut;
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
    
    public Huffman(File file){
        this.file = file;
    }
    
    

    public  void compressor(BinaryIn in) throws IOException {
        String entrada = in.readString();
        char[] mensagem = entrada.toCharArray();
        NoTrie trie = criaTrie(mensagem);
        escreveTrie(trie);
        BinaryStdOut.write(mensagem.length);
        String[] st = new String[256];
        criaTabelaDeCodigos(st, trie, "");
        
        
        for (char c : mensagem) {
            escreveCodigo(st[c]);
        }

        
        BinaryStdOut.close();
    }

    private static void criaTabelaDeCodigos(String[] st, NoTrie no, String s) {
        if (!no.ehFolha()) {
            criaTabelaDeCodigos(st, no.esq, s + '0');
            criaTabelaDeCodigos(st, no.dir, s + '1');
        } else {
            st[no.simbolo] = s;
        }
    }
    

    private  void escreveCodigo(String code) throws IOException {
        File arquivo = this.file;
        FileWriter fw = new FileWriter(arquivo);
        
        for (int j = 0; j < code.length(); j++) {
            if (code.charAt(j) == '0') {
                BinaryStdOut.write(false);
            } else if (code.charAt(j) == '1') {
                BinaryStdOut.write(true);
            } else {
                throw new IllegalStateException("Illegal state");
            }
            fw.write(code.charAt(j)); 
        }
        fw.flush();
    }

    private static NoTrie criaTrie(char[] mensagem) {
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

    private static void escreveTrie(NoTrie n) {
        if (n.ehFolha()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(n.simbolo);
        } else {
            BinaryStdOut.write(false);
            escreveTrie(n.esq);
            escreveTrie(n.dir);
        }
    }

    public static void expansor(BinaryIn in) throws IOException {
        NoTrie trie = leTrie(in);
        int n = in.readInt();
        File arquivo = new File("expand.txt");
        FileWriter fw = new FileWriter(arquivo);
        for (int i = 0; i < n; ++i) {
            NoTrie no = trie;
            do {
                if (in.readBoolean()) {
                    no = no.dir;
                } else {
                    no = no.esq;
                }
            } while (!no.ehFolha());
            fw.write(no.simbolo);
            BinaryStdOut.write(no.simbolo);
        }
        fw.flush();
        BinaryStdOut.close();
    }
    
    

    private static NoTrie leTrie(BinaryIn in) {
        if (in.readBoolean()) {
            return new NoTrie(in.readChar());
        } else {
            return new NoTrie(leTrie(in), leTrie(in));
        }
    }
    
    
    
    
    

}
