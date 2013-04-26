# returns a tuple (g,X,Y) such that: g = X*a + Y*b = gcd(a,b)
def extended_gcd(a, b):
  x = 0
  y = 1
  lastx = 1
  lasty = 0
  a1 = a
  b1 = b
  while b != 0:
    (quot, bm) = divmod(int(a), int(b))
    (a, b) = (b, bm)
    (x, lastx) = (lastx - quot*x, x)
    (y, lasty) = (lasty - quot*y, y)
  g = (lastx*a1) + (lasty*b1)
  return (g, lastx, lasty)

# a and n must be lists of the same length
def solve_congruences(a, n):
  if type(a) != list or type(n) != list:
    print "list error"
    return
  if len(a) != len(n):
    print "len error"
    return
  # TODO: test that all n_i are pairwise coprime
  N = 1
  for ni in n:
    N *= ni

  x = 0
  for i in xrange(0, len(n)):
    d = N/n[i]
    (g, ri, si) = extended_gcd(n[i], d)
    #print xgcd(n[i], d) == extended_gcd(n[i], d)
    # TODO: verify that g is always 1
    ei = si * d
    #TODO: validate that ri * n[i] + ei is always 1
    x += a[i] * ei
  print Mod(x, N) == CRT_list(a, n)
  return Mod(x, N)

# exercicio 1
a1 = [12, 9, 23]
n1 = [25, 26, 27]

# para resolver o exercicio 2:
# tem-se 13x = 4 <=> x = 4/13 (mod 99), com mod(4/13, 99) = 46
# tem-se 15x = 56 <=> x = 56/15 (mod 101), com mod(56/15, 101) = 98
# Com isto, basta resolver o sistema:
#  x \equiv 46 (mod 99)
#  x \equiv 98 (mod 101)
a2 = [int(mod(4/13, 99)), int(mod(56/15, 101))]
n2 = [99, 101]

# TODO: procurar teoria disto
