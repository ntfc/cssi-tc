def jacobi(m,n):
  if not is_odd(n):
    print "n not odd"
    return
  if m == 0:
  #if mod(m,n) == 0:
    return 0
  if m == 1:
    return 1
  #propriedade a)
  if m>=n:
    return jacobi(m%n, n)
  #propriedade b)
  if m == 2:
    # n = -+ 1 (mod 8) <=> [1,7]
    # n = -+ 3 (mod 8) <=> [3,5]
    if mod(n,8) in [1,7]:
      return 1
    elif mod(n,8) in [3,5]:
      return -1
  #propriedade c)
  f = list(factor(m))
  if len(f) == 2:
    if f[1][1] == 1 and f[0][0] == 2:
      k = f[0][1]
      t = f[1][0]
      return (jacobi(2, n)**k * jacobi(t, n))
    #else: isto e preciso?
    #  return jacobi(f[0][0], n) * jacobi(t, n)
  if m%2 == 0:
    return jacobi(2,n)*jacobi(m//2,n)
  #propriedade d)
  if is_odd(m): # and is_odd(n):
    if mod(m,4) == mod(n,4) == 3:
      return -jacobi(n,m)
    else:
      return jacobi(n,m)
  #if m%4 == 3 and n%4 == 3 and is_odd(m):
  #  return -jacobi(n,m)
  #if is_odd(m):
  #  return jacobi(n,m)

def eulerPseudoPrime(b,n):
  j = jacobi(b, n) % n
  p = pow(b, (n-1)//2) % n
  if j == p and gcd(b,n) == 1: 
    print (b,n), n, "is an euler pseudoprime to base", b
    return true
  else:
    return false

def eulerBase(n):
  i = 2
  ct = 0
  while ct <= 5:
    if eulerPseudoPrime(i,n):
      ct += 1  
    i += 1
  return

jacobi1 = [610, 987]
jacobi2 = [20694, 1987]
jacobi3 = [1234567, 11111111]
