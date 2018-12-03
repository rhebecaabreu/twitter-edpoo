# Aplicação de Busca e Compressão de Tweets

Trabalho da disciplina de Estrutura de Dados e Programação Orientada a Objetos da FACOM - UFMS. 

Este trabalho contém dois programas

 - *tt_search_compress*, que foi compilado e executado na IDE Netbeans
 - *tt_search_descompress*, este se encontra dentro da pasta src e deve ser compilado via linha de comando no terminal, seguindo os seguintes passos:
	 - Abrir a pasta *tt_search_descompress* no terminal e dentro dela executar para compilar arquivos do programa: 
	 ` javac -classpath ../algs4.jar NoTrie.java Huffman.java ExpandTweets.java KMP.java`
	- Para executar o programa principal utilizar o comando abaixo, que pegará um arquivo.bin e fará com que ele volte para arquivo.txt, vale ressaltar que o arquivo.bin deve estar na mema pasta do ExpandTweets.java : 
	`java -cp ../algs4.jar:. ExpandTweets + nome_arquivo_onde_ira_buscar_a_palavra.txt palavra_buscada < nome_do_arquivo.bin > nome_do_novo_arquivo_descomprimido.txt`
              
		OBS:. A saída do ExpandTweets vai para um arquivo chamado expand.txt

O programa utiliza base feita pelo professor da disciplina Eraldo Luís Rezende Fernandes e a biblioteca algs4.jar do livro Algorithms 4th Edition por Robert Sedgewick. 

Alunos: Guilherme Campos, Rhebeca Abreu e Tiago Fugishima



