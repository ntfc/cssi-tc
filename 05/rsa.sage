#TODO: meter isto em classes
# create public key and private from public parameters
def createKeys(n, e):
  pq = factorN(n)
  if pq == 0:
    print "no factors found"
    return
  p = pq[0]
  q = pq[1]
  phi = (p-1)*(q-1)
  # inverse d
  d = Integer(e).inverse_mod(phi)
  # return (pk, sk)
  return ((n,e), (n,d))

# TODO: optimize
def factorN(n):
  for i in range(5, 500):
    for j in range(5, 500):
      if i*j == n:
        return (i,j)
  return -1

def enc(pk, m):
  return power_mod(m, pk[1], pk[0])

def dec(sk, c):
  return power_mod(c, sk[1], sk[0])

def decText(sk, text):
  return ''.join(map(lambda x : decodeTri(dec(sk, x)) , text))

# convert int to chr
def intToChr(i):
  return abc[i]

# convert chr to int
def chrToInt(c):
  return abc.index(c)

# TODO: optimize?
# decode trigram
def decodeTri(t):
  for i in xrange(0,26):
    for j in xrange(0,26):
      for k in xrange(0,26):
        if ((i*(26**2)) + (j*26) + k) == t:
          return str(intToChr(i) + intToChr(j) + intToChr(k))

# a, b and c must be char
def encodeTri(t):
  if type(t) != str:
    print "error str"
    return
  if len(t) != 3:
    print "len error"
    return
  l1 = chrToInt(t[0])
  l2 = chrToInt(t[1])
  l3 = chrToInt(t[2])
  return (l1 * 26**2) + (l2 * 26) + l3

abc = "abcdefghijklmnopqrstuvwxyz"

pk1 = (31313, 4913)
# lake wobegon is mostly poor sandy soil and every spring the earth heaves up a new crop of rocks piles of rocks ten feet high in the corners of fields picked by generations of us monuments to our industry our ancestors chose the place tired from their long journey sad for having left the mother land behind and this place reminded them of there so they settled here forgetting that they had left there because the land wasnt so good so the new life turned out to be a lot like the old except the winters are worsez
text1 = [6340, 8309, 14010, 8936, 27358, 25023, 16481, 25809, 23614, 7135, 24996, 30590, 27570, 26486, 30388, 9395, 27584, 14999, 4517, 12146, 29421, 26439, 1606, 17881, 25774, 7647, 23901, 7372, 25774, 18436, 12056, 13547, 7908, 8635, 2149, 1908, 22076, 7372, 8686, 1304, 4082, 11803, 5314, 107, 7359, 22470, 7372, 22827, 15698, 30317, 4685, 14696, 30388, 8671, 29956, 15705, 1417, 26905, 25809, 28347, 26277, 7897, 20240, 21519, 12437, 1108, 27106, 18743, 24144, 10685, 25234, 30155, 23005, 8267, 9917, 7994, 9694, 2149, 10042, 27705, 15930, 29748, 8635, 23645, 11738, 24591, 20240, 27212, 27486, 9741, 2149, 29329, 2149, 5501, 14015, 30155, 18154, 22319, 27705, 20321, 23254, 13624, 3249, 5443, 2149, 16975, 16087, 14600, 27705, 19386, 7325, 26277, 19554, 23614, 7553, 4734, 8091, 23973, 14015, 107, 3183, 17347, 25234, 4595, 21498, 6360, 19837, 8463, 6000, 31280, 29413, 2066, 369, 23204, 8425, 7792, 25973, 4477, 30989]

pk2 = (18923, 1261)
# i became involved in an argument about modern painting a subject upon which i am spectacularly ill informed however many of my friends can become heated and even violent on the subject and i enjoy their wrangles in a modest way i am an artist myself and i have some sympathy with the abstractionists although i have gone beyond them in my own approach to art i am a lumpist two or three decades ago it was quite fashionable to be a cubist and to draw every thing in cubes then there was a revolt by the vorticists who drew every thing in whirls we now have the abstractionists who paint every thing in a very abstracted manner but my own small works done on my telephone pad are composed of carefully shaded strangely shaped lumps with traces of cubism vorticism and abstractionism in them for those who possess the seeing eye as a lumpist i stand alone
text2 = [12423, 11524, 7243, 7459, 14303, 6127, 10964, 16399, 9792, 13629, 14407, 18817, 18830, 13556, 3159, 16647, 5300, 13951, 81, 8986, 8007, 13167, 10022, 17213, 2264, 961, 17459, 4101, 2999, 14569, 17183, 15827, 12693, 9553, 18194, 3830, 2664, 13998, 12501, 18873, 12161, 13071, 16900, 7233, 8270, 17086, 9792, 14266, 13236, 5300, 13951, 8850, 12129, 6091, 18110, 3332, 15061, 12347, 7817, 7946, 11675, 13924, 13892, 18031, 2620, 6276, 8500, 201, 8850, 11178, 16477, 10161, 3533, 13842, 7537, 12259, 18110, 44, 2364, 15570, 3460, 9886, 8687, 4481, 11231, 7547, 11383, 17910, 12867, 13203, 5102, 4742, 5053, 15407, 2976, 9330, 12192, 56, 2471, 15334, 841, 13995, 17592, 13297, 2430, 9741, 11675, 424, 6686, 738, 13874, 8168, 7913, 6246, 14301, 1144, 9056, 15967, 7328, 13203, 796, 195, 9872, 16979, 15404, 14130, 9105, 2001, 9792, 14251, 1498, 11296, 1105, 4502, 16979, 1105, 56, 4118, 11302, 5988, 3363, 15827, 6928, 4191, 4277, 10617, 874, 13211, 11821, 3090, 18110, 44, 2364, 15570, 3460, 9886, 9988, 3798, 1158, 9872, 16979, 15404, 6127, 9872, 3652, 14838, 7437, 2540, 1367, 2512, 14407, 5053, 1521, 297, 10935, 17137, 2186, 9433, 13293, 7555, 13618, 13000, 6490, 5310, 18676, 4782, 11374, 446, 4165, 11634, 3846, 14611, 2364, 6789, 11634, 4493, 4063, 4576, 17955, 7965, 11748, 14616, 11453, 17666, 925, 56, 4118, 18031, 9522, 14838, 7437, 3880, 11476, 8305, 5102, 2999, 18628, 14326, 9175, 9061, 650, 18110, 8720, 15404, 2951, 722, 15334, 841, 15610, 2443, 11056, 2186]
