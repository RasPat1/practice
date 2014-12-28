from math import factorial as fac
from random import randint as randint
import timeit
"""
determine the number of unique paths form point 0.0 to point n,m
when only allowed to move in teh direction of (a,b) -> (a+1,b) or 
(a,b) -> (a, b+1)
"""

def num_paths(n,m):
	"""
	This function counts the number of unique paths
	"""
	if (n <= 0 or m <= 0):
		return 1
	return num_paths(n-1,m) + num_paths(n, m-1)

def num_paths2(n,m):
	"""
	This functino calculates unique pathsusing a closed pformula
	O(1) mothafucka
	Method
	Each move moves one square
	To reach n,m on must move n+m times
	encode movement patter (path) as 0 for moving in m direction and
	1 for moving in the n direction. There is a bijection form counting
	number of paths and number of n+m bit integers wiht n 1's and m 0's
	(n+k-1)
	(  n  )
	number of ways to select n items with replication from a set of k items
	n = n
	k = m + 1
	(n+m)
	( n )

	"""

	return fac(n + m) / (fac(n) * fac(m))


def main():
	print num_paths(11,11)
	print num_paths2(11,11)
	# test_num_paths()
	# timer()

def test_num_paths():
	for i in range(10):
		r1, r2 = randint(0, 20), randint(0, 20)
		assert num_paths(r1, r2) == num_paths(r1, r2)

def timer():
	bounds = [1, 10]
	n1Times = []
	n2Times = []
	repeats = 100
	for i in bounds:
		n1Times.append(timeit.timeit("num_paths(i, i)", 
			setup="from __main__ import num_paths; i =" + str(i)))
		n2Times.append(timeit.timeit("num_paths2(i, i)", 
			setup="from __main__ import num_paths2; i =" + str(i)))

	print n1Times
	print n2Times
if __name__ == "__main__":
	main();