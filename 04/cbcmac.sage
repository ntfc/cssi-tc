from Crypto.Cipher import AES

class MyKey:
  def __init__(self, length=128):
    # n is in bits!
    self.n = int(length)
    self.k = os.urandom(self.n // 8).encode('hex')

class CBCMAC:
  def __init__(self, n=128, l=5):
    if n < 128:
      print "INIT: Length ERROR"
    # n is in bits
    self.n = n # block length
    self.l = l # number of blocks
    self.block_len = self.n // 8 # iv length
    self.str_len = (self.n * self.l) // 8 # message length

  # msg: message must be in str
  # iv: must be in str format
  def Mac(self, key, msg, iv=0, secure=True):
    if key.n != self.n:
      print "MAC: Wrong len for key (should be {0} bits)".format(self.n)
      return False
    if type(msg) != str or len(msg) != self.str_len:
      print "MAC: Message must be a str with {0} characters".format(self.str_len)
      return False
    if iv == 0:
      iv = '\x00' * self.block_len
    else:
      if type(iv) != str or len(iv) != self.block_len:
        print "MAC: IV must be a str with {0} characters".format(self.block_len)
        return False
    F = AES.new(key.k, AES.MODE_ECB)
    
    t = [iv]
    mi = self.partition(msg)
    
    if mi == False:
      print "MAC: An error ocorred while partitioning"
      return False
    for i in xrange(1, self.l + 1): # i in [1 .. self.l]
      xor_i = strXor(t[i - 1], mi[i - 1])
      t.append(F.encrypt(xor_i))
    
    if secure == True:
      return t[self.l]
    else:
      # returns all tags joined. Use partition to un-join
      return (iv, ''.join(t[1:]))
    
  def partition(self, msg):
    if type(msg) != str or len(msg) != self.str_len:
      print "PARTITION: Message must be a str with {0} characters".format(self.str_len)
      return False

    ml = []
    for i in xrange(0, self.l):
      start = i * self.block_len
      end = start + self.block_len
      ml.append(msg[start : end])
    return ml

  # tag must be in binary string
  def Vrfy(self, key, msg, tag, iv=0, secure=True):
    if key.n != self.n:
      print "VRFY: Wrong len for key (should be {0} bits)".format(self.n)
      return False
    if type(msg) != str or len(msg) != self.str_len:
      print "VRFY: Message must be a str with {0} characters".format(self.str_len)
      return False
    if iv == 0:
      iv = '\x00' * self.block_len
    else:
      # validate IV
      if type(iv) != str or len(iv) != self.block_len:
        print "VRFY: IV must be a str with {0} characters".format(self.block_len)
        return False

    if secure == True:
      # validate tag
      if type(tag) != str and len(tag) != self.block_len:
        print "VRFY: Tag must be a str with {0} characters".format(self.block_len)
        return False
    else:
      # when secure=False, tag must be an array with self.l blocks
      if type(tag) != list and len(tag) != self.l and len(''.join(tag)) != (self.str_len):
        print "VRFY: Tag must be a list with {0} blocks, each with {1} characters".format(self.l, self.str_len)
        return False
      

    tag2 = self.Mac(key, msg, iv, secure)
    if secure == True:
      return tag == tag2
    else:
      return tag == tag2[1]

  # creates the new fake message and fake tag, when all tags are returned
  # m: original message (in str form)
  # t: str of all tags
  def ForgeMac(self, m, t):
    if len(m) != len(t):
      print "FORGE: Length of message must be the same as the length of all tags"
      return
    if len(m) != self.str_len:
      print "FORGE: Length of message and tag must be of {0} bits ({1} chars)".format(self.n * self.l, (self.n * self.l) / 8)
      return
    mi = self.partition(m)
    ti = self.partition(t)
    newM = ''
    newT = ''
    
    newM += strXor(ti[0], mi[1])
    newT += ti[1]
    for i in xrange(1, self.l):
      if i != (self.l - 1):
        newM += mi[i + 1]
        newT += ti[i + 1]
      else:
        newM += strXor(ti[i], mi[0])
        newT += ti[0]
    return (newM, newT)

# perform bitwise xor on two binary strings
# strings must have different length
def bitwiseXor(a, b):
  lenA = len(a)
  lenB = len(b)
  if len(a) != len(b):
    print "XOR: Cant xor |a| = {0} with |b| = {1}".format(len(a), len(b))
    return
  c = ''.join(str(e) for e in map(lambda x,y : int(x).__xor__(int(y)), a, b))
  return c

# n: block length
def strXor(a, b):
  return ''.join(chr(ord(a).__xor__(ord(b))) for a,b in zip(a, b))

def randomIV(nbits=128):
  iv = hex(Integer(getrandbits(nbits)))
  if is_odd(len(iv)):
    iv = iv.zfill(len(iv) + 1)
  return iv.decode('hex')
