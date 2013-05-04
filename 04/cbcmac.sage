from binascii import hexlify, unhexlify
from Crypto.Cipher import AES

class MyKey:
  def __init__(self, length=128):
    self.n = int(length)
    #self.k = intToBin(getrandbits(self.n), self.n)
    self.k = bin(int(os.urandom(self.n // 8).encode('hex'), 16))[2:].zfill(self.n)

class CBCMAC:
  def __init__(self, n=128, l=5):
    self.n = n
    self.l = l

  """def Mac(self, m, iv=0):
    if type(m) != str or len(m) != (self.l * self.n):
      print "len(msg) != l(n)*n or type(msg) != str. couldnt not mac"
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
  """
  def Mac(self, key, msg, iv=0):
    if key.n != self.n:
      print "wrogn len for key"
      return False
    # convert message to binary string
    binMsg = strToBin(msg)
    if len(binMsg) != (self.l * self.n):
      print "message length must be {0}".format((self.l*self.n)//8)
      return False
    # init 0 iv
    if iv == 0:
      iv = '0' * self.n
    else:
      print "using iv different than 0"
    F = AES.new(binToHex(key.k), AES.MODE_ECB)
    t = [iv]
    mi = self.partition(binMsg)
    print "l = {0}, len(mi) = {1}".format(self.l, len(mi))
    for i in xrange(1, self.l + 1): # == [1 .. self.l]
      #print len(t[i-1])
      xori = bitwiseXor(t[i-1], mi[i-1])
      # to encrypt, string must be unhexlified
      ti = hexlify(F.encrypt(unhexlify(binToHex(xori))))
      t.append(hexToBin(ti).zfill(self.n))
      #print "t[{0}] = t[{1}] xor mi[{1}]".format(i, i-1)
    return t[self.l]
  
  # m must be a bit list
  def partition(self, m):
    if len(m) != (self.l * self.n):
      print "len(msg) != l(n)*n. could not partition"
      return False
    ml = []
    for i in range(0, self.l):
      blockStart = i * self.n
      blockEnd = blockStart + self.n
      ml.append(m[blockStart : blockEnd])
    return ml

  # tag must be in binary string
  def Vrfy(self, key, msg, tag, iv=0):
    msgBin = strToBin(msg)
    if len(msgBin) != (self.l * self.n):
      print "message length must be {0}".format((self.l*self.n)//8)
      return False
    t_2 = self.Mac(key, msg, iv)
    return tag == t_2

def strToBin(s):
  # call zfill in the end to get 8 * len(s) length
  return bin(Integer(hexlify(s), 16))[2:].zfill(8 * len(s))

def binToStr(b):
  return unhexlify(hex(Integer(b, 2)))

# convert an int to a binary
# returns a binary string with len = length
def intToBin(i, length=128):
  return bin(Integer(i))[2:].zfill(length)

# converts a binary to int
def binToInt(i):
  out = 0
  for bit in i:
    out = (out << 1) | int(bit)
  return out

def binToHex(b):
  h = hex(Integer(b, 2))
  if is_odd(len(h)):
    h = h.zfill(len(h)+1)
  return h

# str must already be hexlified
def hexToBin(h):
  return bin(int(h,16))[2:]
  
# perform bitwise xor on two binary strings
# strings must have different length
def bitwiseXor(a, b):
  lenA = len(a)
  lenB = len(b)
  if len(a) != len(b):
    print "XOR error"
    return
  c = ''.join(str(e) for e in map(lambda x,y : int(x).__xor__(int(y)), a, b))
  return c
