"""
Largest possible subsequence
Taken a list of integers select the largest
subsequence in which all digits are strictly increasing
"""

import random

def main():
	ssSize = random.randint(1, 20);
	ss = makeSS(ssSize);
	print ss;
	print getSS(ss)

def makeSS(size):
	return random.sample(range(1,101), size)

def getSS(ss):
	ssLen = [1 for x in range(len(ss))]
	for i in reversed(range(len(ss))):
		for j in range(i, len(ss)):
			if (ss[j] > ss[i]):
				ssLen[i] = max(ssLen[i], ssLen[j] + 1)
	print ssLen
	return max(ssLen)

if __name__ == "__main__":
	main();