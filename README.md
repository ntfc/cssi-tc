## TP01
If the Index of Coincidence is around 0.06 we conclude the cipher is probably a substitution cipher
 -> usar isto para fazer um cracker mais inteligente

* 4 textos, 1º, 2º e 4º em inglês, 3º em francês
* 2º e 4º são Vigenere
* 1º e 3º serão monoalfabetico ou affine

http://cs.colgate.edu/~chris/FSemWeb/hw/lab1.html

if IC(criptograma) ~ IC(lingua) ==> monoalphabetic

IC(texto1)=0.0623468137254902 (ingles = 0.0667)
IC(texto2)=0.040871838349583155 (ingles) (vigenere)
IC(texto3)=0.07993642003794288 (frances=0.0746) (affine)
IC(text4)=0.04138199429213872 (ingles) (vigenere)

chaves:
 - texto1:
 - texto2: crypto
 - texto3: 19, 4
 - texto4: theory

Chi-Squared usado no affine: http://practicalcryptography.com/cryptanalysis/text-characterisation/chi-squared-statistic/ <br>
Tentar usado o chi-squared no viginere. Pelo menos assim ja da para dar um nome ao metodo usado para crackar :) <br>
Mais metodos que se podem usar aqui: http://practicalcryptography.com/cryptanalysis/text-characterisation/ <br>

BIB: http://www.amazon.com/Elementary-Cryptanalysis-Mathematical-Association-Textbooks/dp/0883856220

## TP02
20 criptogramas, 19 chaves
|k| = |m| = |c| = 290

## TP03
TODO: nao cifrar o ficheiro todo, cifrar apenas a matriz!

1. Abrir imagem em modo hexadecimal
2. Ver em que posicao comeca o bitmap. Isto serve para determinar o tamanho do header
 * esta na posicao 10 (0xa) e tem 4 bytes
3. Usar openssl para cifrar uma imagem em formato bmp:
 * openssl enc [cifra com ecb] -in file.bmp -out file-out.bmp
4. Usar dd para copiar o header do ficheiro original para o file-out.bmp
 * dd if=file.bmp of=file-out.bmp bs=1 count=[header_size] conv=notrunc

Meter no relatorio que depende das imagens. Uma foto normal, por exemplo, nao da la mt bem <br>
BIB: https://en.wikipedia.org/wiki/BMP_file_format#DIB_header_.28bitmap_information_header.29, man pages

##TP04

##TP05
- `extended_gcd(a, b)` == `xgcd(a, b)` do sage
- `solve_congruences(a, n)` == `CRT_list(a, n)` do sage

###exercicio 1
Basta fazer `solve_congruences([12,9,23],[25,26,27])`<br>
BIB: um livro qualquer de cripto com o algoritmo do xgcd, e outro com o do CRT

###exercicio 2
Tem-se `13x = 4 <=> x = (4/13) mod(99)`, com `mod(4/13, 99) = 46`<br>
Faz-se o mesmo para a equacao `15x = 56 (mod 101)`<br>
No final, chamar `solve_congruences([int(mod(4/13, 99)), int(mod(56/15, 101))], [99,101])`<br>

###exercicio 3
Como os dois n sao pequenos, e facil factorizar por brute force. Dessa forma, obtem-se a chave privada com o metodo `createKeys(n, e)` que retorna ambas as chaves<br>
Para decifrar um texto, fazer `decText(sk, text)`

###exercicio 4
As propriedades a e b n�o tenho a certeza absoluta sobre elas. Usar o jacobi(m,n).

###exercicio 5
Encontra-se no jacobi.sage. Chamar eulerBase(n).
b = 93, n = 837
b = 850, n = 851
b = 204, n = 1189
H� mais mas estes s�o os primeiros a ser encontrados.