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

  def Mac(self, key, msg, iv=0, secure=True):
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
      print "INFO: using iv different than 0"
    F = AES.new(binToHex(key.k), AES.MODE_ECB)
    t = [iv]
    mi = self.partition(binMsg)
    for i in xrange(1, self.l + 1): # == [1 .. self.l]
      xori = bitwiseXor(t[i-1], mi[i-1])
      t.append(encryptBinToBin(F, xori, self.n))
    if secure == True:
      return (iv, t[self.l])
    else:
      return (iv, t[1:])
  
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
  def Vrfy(self, key, msg, tag, iv=0, secure=True):
    if secure == True:
      msgBin = strToBin(msg)
      # when secure=True, message must be in string format and tag is the last one
      if len(msgBin) != (self.l * self.n):
        print "message length must be {0}".format((self.l*self.n)//8)
        return False
    else:
      # when secure=False, message and tag must be an array of length l(n)
      if len(tag) != self.l:
        print "Wrong len for tag or message."
        return False
      
    (iv_2, t_2) = self.Mac(key, msg, iv, secure)
    return tag == t_2
      
  """ m is the message sent and t is a list with all the tags """
  def Forge(self, m, t):
    if len(t) != 2:
      print "WARNING: this doesnt work for len(tag) > 2 yet!"
    # convert message to binary string
    binMsg = strToBin(m)
    # since self.l is public, we are going to partition the message
    m_part = self.partition(binMsg)
    # build new forged message and tag
    newM = []
    newT = []
    for i in xrange(0, len(t)):
      newM.append(bitwiseXor(t[i], m_part[len(t)- i - 1]))
      newT.append(t[len(t) - i - 1])
    return (newM, newT)

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

def randomIV(nbits=128):
  return bin(Integer(getrandbits(nbits)))[2:].zfill(nbits)

def encryptBinToBin(F, m, n):
  return hexToBin(hexlify(F.encrypt(unhexlify(binToHex(m))))).zfill(n)
