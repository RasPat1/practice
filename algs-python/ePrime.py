import math
from decimal import *

"""
Return the first 10 digit prime number
in the decimal expansion of e

"""

import math

def main():
	hardLim = 10 ** 5
	iteration = 2

	while (True):
		testNum = getDigits(math.e, iteration, 10)
		if (isPrime(testNum)):
			return testNum
		
		# break after a while cause we're scared
		if iteration > hardLim:
			break;
		iteration += 1

def getDigits(e, i, n):
	getcontext().prec = i + n
	stringNum = str(Decimal(1).exp())[i:i+n+1]
	return int(stringNum)
	
def isPrime(n):
	lim = int(math.sqrt(n))
	for i in range(2, lim + 1):
		if n % i == 0:
			return False

def testGetDigits():
	assert getDigits(math.e, 1, 10) == 71828198284
	assert getDigits(math.e, 4, 10) == 28198284590
	assert getDigits(math.e, 14, 10) == 45090795598

def testIsPrime():
	for i in range(2, 100):
		print i, isPrime(i)
	return True

def testSet():
	testIsPrime()
	testGetDigits()

if __name__ == "__main__":
	main()
	# testSet()
	