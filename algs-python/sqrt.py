def absVal(n):
	if n < 0:
		return -1 * n
	else:
		return n

def sqrt(n, est):
	"""
	runs newton's method to get successive 
	approximations of a sqrt value 
	"""
	tolerance = 0.000001
	if absVal(est * est - n) <= tolerance:
		return est

	newEst = (est ** 2 + n) / 2.
	newEst = newEst / est

	return sqrt(n, newEst)

def main():
	test()

def test():
	assert round(sqrt(2, 1.5), 3) == 1.414
	assert round(sqrt(10, 3), 3) == 3.162
	
if __name__ == "__main__":
	main()