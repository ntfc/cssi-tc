#nao tenho a certeza absoluta se sera assim
# (m1/n) = (m2/n) sse m1 = m2 (mod n)
#esta propriedade esta mal
def a(m, n):
  if m >= n:
    #return (mod(m, n), n)
    return
  else:
    return

def b(m, n):
  if m != 2:
    return
  else:
    if n.mod(8) == 1 or n.mod(8) == -1:
      return 1
    if n.mod(8) == 3 or n.mod(8) == -3:
      return -1
  # identity
  return

def c(m, n):
  f = list(factor(m))
  if len(f) == 2 and f[1][1] == 1:
    #tuple([(2,4)]*2)
    #((2, 4), (2, 4))
    if f[0][1] == 1:
      return ((f[0][0], n), (f[1][0], n))
    else:
      if f[0][0] == 2:
        #isto esta assim para formar o tuplo direito
        return tuple([(f[0][0], n)]*f[0][1])+tuple([(f[1][0],n)])
  else:
    if m % 2 == 0 and m != 2:
      return ((2, n),(m/2, n))
    if len(f) == 3 and f[0][1] == f[1][1] == f[2][1] == 1:
      return ((f[0][0]*f[1][0], n),(f[2][0],n))
    else:
      return 

def d(m, n):
  if mod(m, 4) == mod(n, 4) == 3:
    return (-n, m)
  else:
    return (n, m)

# TODO: tou senil, nao sei qual o sentido ou nao sentido disto
def tc_jacobi(m, n):
  while True:
    if not is_odd(n):
      print "n not odd"
      return
    if m >= n:
      (m, n) = (mod(m, n), n)
    if m == 2:
      if mod(n,8) in [1, mod(-1, 8)]:
        return 1
      if mod(n,8) in [3, mod(-3, 8)]:
        return -1


def jacobiAlg(m,n):
  final = []
  l = [(m, n)]
  i = 0
  while len(l) >= 1:
    if type(l[i]) is Integer:
      final.append(l.pop(i))
      if len(l) == 0:
        continue
      if i == len(l):
        i -=1
    print "ciclo: ", i, " de ", len(l)
    print l
    m = l[i][0]
    n = l[i][1]
    if not is_odd(n):
      print "n not odd"
      return
    #propriedade a
    t = a(m, n)
    if type(t) is tuple:
      l.pop(i)
      l.insert(i, t)
      i += 1
      if i >= len(l):
        i = 0
      print "prop a"
      continue
    #propriedade b
    t = b(m, n)
    if type(t) is Integer:
      l.pop(i)
      l.insert(i, t)
      i +=1
      if i >= len(l):
        i = 0
      print "prop b"
      continue
    #propriedade c
    t = c(m,n)
    if type(t) is tuple:
      l.pop(i)
      for j in range(len(t)):
        l.insert(i,t[j])
      i +=1
      if i >= len(l):
        i = 0
      print "prop c"
      continue
    #propriedade d
    if not is_odd(m):
      print "iteraccao que não fez nada"
      i +=1
      if i >= len(l):
        i = 0
      continue
    t = d(m,n)
    if type(t) is tuple:
      l.pop(i)
      l.insert(i,t)
      i += 1
      if i >= len(l):
        i = 0
      print "prop d"
      continue
    print "iteraccao que não fez nada"
    i +=1
    if i >= len(l):
      i = 0
  print final

#FDX PUTA DE LIXO
def tc_jacobi(m, n):
  result = 0
  if m == 0:
    if n == 1:
      result = 1
    else:
      result = 0
  elif m == 2:
    if n%8 == 7:
      result = 1
    elif n%8 == 5:
      result = -1
  elif m >= n:
    result = tc_jacobi(m%n, n)
  elif a % 2 == 0:
    result = tc_jacobi(2, n)*tc_jacobi(m/2, n)
  else:
    if m % 4 == 3 and n % 4 == 3:
      result = -tc_jacobi(n, m)
    else:
      result = tc_jacobi(n, m)
  print result
  return result


def jacobi1(a,n):
     if a == 0:
         return 0 
     if a == 1:
         return 1
     if a == 2:
         n8 = n%8
         if n8 == 3 or n8 == 5:
             return -1
         else:
             return 1
     if a%2 == 0:
         return jacobi1(2,n) * jacobi1(a//2,n)
     if a >= n:
         return jacobi1(a%n,n)
     if a%4 == 3 and n%4 == 3:
         return -jacobi1(n,a)
     else:
         return jacobi1(n,a)



#confirmar propriedades a e b
def jacobi(m,n):
  if not is_odd(n):
    print "n not odd"
    return
  if m == 0:
    return 0
  if m == 1:
    return 1
  #propriedade a)
  if m >= n:
    return jacobi(m%n, n)
  #propriedade b)
  if m == 2:
    nm8 = n%8
    if nm8 == 1 or nm8 == 7:
      return 1
    elif nm8 == 3 or nm8 == 5:
      return -1
  #propriedade c)
  f = list(factor(m))
  if len(f) == 2 and f[1][1] == 1 and f[0][0] == 2:
    return (jacobi(2, n)^3)*jacobi(f[0][1], n)
  if m%2 == 0:
    return jacobi(2,n)*jacobi(m//2,n)
  #propriedade d)
  if m%4 ==3 and n%4 == 3 and is_odd(m):
    return -jacobi(n,m)
  if is_odd(m):
    return jacobi(n,m)



def eulerPseudoPrime(b,n):
  j = jacobi(b, n) % n
  p = pow(b, (n-1)//2) % n
  if j == p and gcd(b,n) == 1: 
    print (b,n), n, "is an euler pseudoprime to base", b
    return true
  else:
    return false

def eulerBase(n):
  i = 0
  ct = 0
  while ct <= 5:
    if eulerPseudoPrime(i,n):
      ct += 1  
    i += 1
  return