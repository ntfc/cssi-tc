## TP01
If the Index of Coincidence is around 0.06 we conclude the cipher is probably a substitution cipher
 -> usar isto para fazer um cracker mais inteligente

* 4 textos, 1o, 2o e 4o em ingles, 3o em frances
* 2o e 4o sao Vigenere
* 1o e 3o serao monoalfabetico ou affine

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
20 criptogramas, 19 chaves, 2 textos limpos<br>
|k| = |m| = |c| = 290<br>
Fazer XOR de dois criptogramas, e detectar se a distribuição é uniforme

## TP03
1. Abrir imagem em modo hexadecimal, com o `od -t x -An "$IFILE"`
2. Ver em que posicao comeca o bitmap. Isto serve para determinar o tamanho do header
 * esta na posicao 10 (0xa) e tem 4 bytes
 * `comando 1. | head -n1 | cut -d' ' -f4,5 | tr -d ' ' | tail -c15 | cut -c1-8`
3. Depois de fazer o `reverse` do resultado de `2.`, converter para decimal com o `bc`
 * `echo "obase=10; ibase=16; $HEADERSIZE" | bc`
4. Copiar o header da imagem original para o ficheiro de output
 * `dd if="$IFILE" of="$OFILE" bs=16 count="$HEADERSIZE" conv=notrunc`
3. Usar openssl para cifrar apenas os restantes bytes da imagem
 * `dd if="$IFILE" bs=16 skip="$HEADERSIZE" conv=notrunc | openssl enc [cifra com ecb] > "$OFILE"`

Executar script com: `./script infile.bmp outfile.bmp -aes-128-ecb`<br>
Meter no relatorio que depende das imagens. Uma foto normal, por exemplo, nao da la mt bem <br>
BIB: https://en.wikipedia.org/wiki/BMP_file_format#DIB_header_.28bitmap_information_header.29, man pages

##TP04
- Cria-se uma chave de `n` bits com: `K = MyKey(n)`
 * para aceder aos `n` bits, fazer `K.k`
- Cria-se um CBC-MAC com `cbcmac = CBCMAC(n, l)`
- Para criar tags, existem 2 versões:
 * Segura: `cbcmac.Mac(K, m, secure=True)`
 * Insegura: `cbcmac.Mac(K, m, iv, secure=False)`
- Para verificar, existem tambem 2 versões:
 * Segura: `cbcmac.Vrfy(K, m, tag, secure=True)`
 * Insegura: `cbcmac.Vrfy(K, m, tag, iv, secure=False)`
- Para falsificar MACs (quando se retornam todas as tags) fazer o seguinte (usando a versao insegura):
 * `m = m_1,..,m_l` e `t = t_0,..,t_l` sao interceptados
 * atacante sabe que `t_1 = E_k(m_1), .., t_l = E_k(m_l)`
 * atacante constroi `m' = (t_1 XOR m_2) || (t2_2XOR m_1)` e `t' = t_2 || t_1` <b>TODO</b>: este e o caso para `l = 2`
 * Vrfy aceita m' e respectiva tag t'
 * m' != m, excepto se m_2 = m_1 XOR E_k(m_1)
- Para falsificar MACs quando se usa um IV aleatório fazer:
 * Usar método `Mac` com um IV aleatório
 * Fazer XOR do primeiro bloco da mensagem com o IV aleatório, e reconstruir nova mensagem
 * Usar método `Vrfy` com a mensagem falsificado e com IV=0
- <b>TODO:</b> criar metodo para falsificar MAC<br>

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
Para decifrar um texto, fazer `decText(sk, text)`<br>
<b>TODO:</b> arranjar uma forma mais inteligente de factorizar `n`

###exercicio 4
As propriedades a e b nao tenho a certeza absoluta sobre elas. Usar o jacobi(m,n).

###exercicio 5
Encontra-se no jacobi.sage. Chamar eulerBase(n).<br>
b = 93, n = 837<br>
b = 850, n = 851<br>
b = 204, n = 1189<br>
Ha mais mas estes sao os primeiros a ser encontrados.
