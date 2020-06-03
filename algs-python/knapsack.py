"""
Given a list of items with speicified weights and
values and a knapsack which can only hold a fixed weight.
Select some subset of items that fit in the knapsack with
the largest possible sum of values.
"""

import random

class Item:
	weightMax = 20
	valueMax = 20

	def __init__(self, weight, value):
		self.weight = weight
		self.value = value

	@staticmethod
	def rand_item():
		return Item(random.randint(0, Item.weightMax), 
			random.randint(0, Item.valueMax))

class Knapsack:
	def __init__(self, maxWeight):
		self.maxWeight = maxWeight
		self.weight = 0
		self.value = 0
		self.count = 0
		self.itemList = []

	def add_item(self, item):
		if (item.weight + self.weight < self.maxWeight):
			self.value += item.value
			self.weight += item.weight
			self.count += 1
			self.itemList.append(item)
			return True
		else:
			return False

def main():
	testSack();

def solveKnap(kSack, items):
	



def testSack():
	k = Knapsack(40)
	items = []
	for i in range(10):
		items.append(Item.rand_item());

	for i in items:
		print i.weight, i.value





if __name__ == "__main__":
	main();

