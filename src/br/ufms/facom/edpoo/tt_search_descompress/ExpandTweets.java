/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author rhebeca
 */
/* 
        INSTRUÇÕES PARA COMPILAR ESTE ARQUIVO 
            ABRIR A PASTA ONDE ELE ESTA SITUADO NO TERMINAL E EXECUTAR O SEGUINTE COMANDO: 
            javac -classpath ../algs4.jar NoTrie.java Huffman.java ExpandTweets.java
            
            EM SEGUIDA 
            java -cp ../algs4.jar:. ExpandTweets + nome_arquivo_original.txt palavra_buscada < nome_do_arquivo.bin > nome_do_novo_arquivo.txt

          OBS:. o arquivo.bin deve estar na mesma pasta do ExpandTweets 

 */
public class ExpandTweets {

    public static void main(String[] args) throws IOException {
        String args1 = args[1];
        String args2 = args[2];

        Huffman.expansor();

        BufferedReader br = new BufferedReader(new FileReader(args1));
        StringBuilder sb1 = new StringBuilder();

        String[] linha = new String[1000000];
        int i = 0;
        while (br.ready()) {
            linha[i] = br.readLine();

            i++;

        }

        for (int j = 0; linha[j] != null; j++) {
            sb1.append(linha[j]);
        }

        String pat = args2;

        String txt1 = sb1.toString();

        String[] b = txt1.split("<>");
        File arquivo = new File("expand.txt");
        FileWriter fw = new FileWriter(arquivo);
        int k = 0;
        for (String txt : b) {
            if (k == 0 || k <= b.length - 1) {
                char[] pattern = pat.toCharArray();
                char[] text = txt.toCharArray();

                KMP kmp1 = new KMP(pat);
                int offset1 = kmp1.search(txt);

                KMP kmp2 = new KMP(pattern, 256);
                int offset2 = kmp2.search(text);

                // print results
                fw.write("Text: ");
                fw.write(txt + "\n");

            }
            k++;
            System.out.println(k);
        }
        
        fw.close();

    }
}
