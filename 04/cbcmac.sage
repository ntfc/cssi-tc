from binascii import hexlify

class Key:
  def __init__(self, length=128):
    self.n = int(length)
    self.k = intToBin(getrandbits(self.n), self.n)

class CBCMAC:
  def __init__(self, l, k=0):
    if type(k) == type(Key):
      self.k = k
    else:
      self.k = Key() # default is 128 bits
    self.n = self.k.n
    self.l = l

  # m must be in bit list
  def Mac(self, m, iv=0):
    if type(m) != str or len(m) != (self.l * self.n):
      print "len(msg) != l(n)*n. couldnt not mac"
      return False
    # partition m
    m_ = self.part(m)
    if iv == 0:
      iv = intToBin(0, self.n)
    t = [iv]

    for i in xrange(1, self.l + 1):
      gi = bitwiseXor(t[i-1], m_[i-1])
      # TODO: ver funcao pseudo aleatoria.....
      # TODO: usar AES/DES??? https://gist.github.com/HarryR/5017218
      t.append(self.F(gi))

    return (t[self.l])
    #for i in range(1, self.l()+1):
      #g = bitwiseXor(t[i-1], m_[i-1])
      # TODO: usar aqui um funcao pseudo aleatoria
      #t[i] = bitwiseXor(keyBin, g)
    #return t[self.l()]

  # m must be a bit list
  def part(self, m):
    if len(m) != (self.l * self.n):
      print "len(msg) != l(n)*n. could not partition"
      return False
    ml = []
    for i in range(0, self.l):
      blockStart = i * self.n
      blockEnd = blockStart + self.n
      ml.append(m[blockStart : blockEnd])
    return ml

  # m and t must be in bit list
  def Ver(self, m, t, iv=0):
    if type(m) != str or len(m) != (self.l * self.n):
      print "len(msg) != l(n)*n"
      return False
    t_2 = self.Mac(m, iv)
    return t == t_2

  # pseudo random function. try to use a block cipher here
  def F(self, m):
    return bitwiseXor(self.k.k, m)

# convert an int to a binary
# returns a binary string with len = length
def intToBin(i, length):
  # convert int to binary: 10101
  intBin = bin(int(i))[2:]
  if len(intBin) > length:
    print "Error: i.nbits() > length"
    return
  # add the necessary 0's so that len(result) = length
  return intBin.zfill(length)

# converts a binary to int
def binToInt(i):
  out = 0
  for bit in i:
    out = (out << 1) | int(bit)
  return out

# perform bitwise xor on two binary strings
# string can have different length
def bitwiseXor(a, b):
  lenA = len(a)
  lenB = len(b)
  if len(a) >= len(b):
    diff = lenA - lenB
    c = a[:diff]
    c += ''.join(str(e) for e in map(lambda x,y : int(x).__xor__(int(y)), a[diff:], b))
  else:
    diff = lenB - lenA
    c = b[:diff]
    c += ''.join(str(e) for e in map(lambda x,y : int(x).__xor__(int(y)), a, b[diff:]))
  return c
