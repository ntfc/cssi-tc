## TP01
If the Index of Coincidence is around 0.06 we conclude the cipher is probably a substitution cipher
 -> usar isto para fazer um cracker mais inteligente

4 textos, 1º, 2º e 4º em inglês, 3º em francês
2º e 4º são Vigenere
1º e 3º serão monoalfabetico ou affine

http://cs.colgate.edu/~chris/FSemWeb/hw/lab1.html

IC(criptograma) ~ IC(lingua) ==> monoalphabetic

IC(texto1)=0.0623468137254902 (ingles = 0.0667)
IC(texto2)=0.040871838349583155 (ingles) (vigenere)
IC(texto3)=0.07993642003794288 (frances=0.0746) (affine)
IC(text4)=0.04138199429213872 (ingles) (vigenere)

chaves:
 - texto1:
 - texto2: crypto
 - texto3: 19, 4
 - texto4: theory

Chi-Squared usado no affine: http://practicalcryptography.com/cryptanalysis/text-characterisation/chi-squared-statistic/
Tentar usado o chi-squared no viginere. Pelo menos assim ja da para dar um nome ao metodo usado para crackar :)
Mais metodos que se podem usar aqui: http://practicalcryptography.com/cryptanalysis/text-characterisation/

BIB: http://www.amazon.com/Elementary-Cryptanalysis-Mathematical-Association-Textbooks/dp/0883856220
## TP02
20 criptogramas, 19 chaves
|k| = |m| = |c| = 290

## TP03
TODO: nao cifrar o ficheiro todo, cifrar apenas a matriz!
1. Abrir imagem em modo hexadecimal
2. Ver em que posicao comeca o bitmap. Isto serve para determinar o tamanho do header
 - esta na posicao 10 (0xa) e tem 4 bytes
3. Usar openssl para cifrar uma imagem em formato bmp:
 - openssl enc [cifra com ecb] -in file.bmp -out file-out.bmp
4. Usar dd para copiar o header do ficheiro original para o file-out.bmp
 - dd if=file.bmp of=file-out.bmp bs=1 count=[header_size] conv=notrunc

Meter no relatorio que depende das imagens. Uma foto normal, por exemplo, nao da la mt bem
BIB: https://en.wikipedia.org/wiki/BMP_file_format#DIB_header_.28bitmap_information_header.29, man's

## TP04

##TP05

