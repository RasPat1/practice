"""
Find key in list using binary
"""

import random

def bin_search(list, key):
	"""
	Standard binary search
	"""
	l = sorted(list)
	low = 0
	high = len(l) - 1
	while low <= high:
		mid = (low + high) / 2
		if l[mid] == key:
			return True
		elif l[mid] > key:
			high = mid - 1
		elif l[mid] < key:
			low = mid + 1
	return False

def main():
	test()

def test():
	rand_num = 15
	l = [random.randint(1,100) for x in range(20)]
	if l[0] > 50:
		l.append(rand_num)
	random.shuffle(l)
	
	result = bin_search(l, rand_num)
	test_val = rand_num in l
	print l, result, test_val
	assert result == test_val

if __name__ == "__main__":
	main()