/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.facom.edpoo.tt_search_compress;

/**
 *
 * @author rhebeca
 */
public class NoTrie implements Comparable<NoTrie> {

    char simbolo;
    int frequencia;
    NoTrie esq;
    NoTrie dir;

    NoTrie(char simbolo) {
        this.simbolo = simbolo;
    }

    NoTrie(char simbolo, int frequencia) {
        this.simbolo = simbolo;
        this.frequencia = frequencia;
    }

    NoTrie(int frequencia, NoTrie esq, NoTrie dir) {
        this.frequencia = frequencia;
        this.esq = esq;
        this.dir = dir;
    }
    
    NoTrie(char simbolo, int frequencia, NoTrie esq, NoTrie dir){
        this.simbolo = simbolo;
        this.frequencia = frequencia;
        this.esq = esq;
        this.dir = dir;
    }

    NoTrie(NoTrie esq, NoTrie dir) {
        this.esq = esq;
        this.dir = dir;
    }

    boolean ehFolha() {
        return esq == null && dir == null;
    }

    @Override
    public int compareTo(NoTrie o) {
        return this.frequencia - o.frequencia;
    }
}
