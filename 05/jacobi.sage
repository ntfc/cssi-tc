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
    return (jacobi(2, n)^f[0][1])*jacobi(f[0][1], n)
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