import random

class Node:
	"""
	This is a standard node like that used in a graph
	Each Node is uniquely numbered
	"""
	nodeCount = 0

	def __init__(self, key):
		self.key = key
		self.id = Node.nodeCount
		Node.nodeCount += 1
		self.adjList = []

	def delete(self):
		for n in self.adjList:
			e = Graph.get_edge(self, n)
			e.delete()

	def connect(self, node):
		self.adjList.append(node)

	def disconnect(self, node):
		if node in self.adjList:
			self.adjList.remove(node)

	def is_connected(self, node):
		return node in self.adjList

class Edge:
	"""
	For undirected weighted edges
	"""
	edgeCount = 0

	def __init__(self, n1, n2, weight):
		self.start = n1
		self.end = n2
		self.weight = weight
		n1.connect(n2)
		n2.connect(n1)
		self.id = Edge.edgeCount
		Edge.edgeCount += 1

	def delete(self):
		n1 = self.start
		n2 = self.end
		n1.disconnect(n2)
		n2.disconnect(n1)

class Graph:

	def __init__(self, nodes=[], edges=[]):
		self.nodes = nodes
		self.edges = edges

	def add_node(self, key):
		n = Node(key)
		self.nodes.append(n)
		return n

	def add_edge(self, n1, n2, weight):
		e = Edge(n1, n2, weight)
		self.edges.append(e)
		return e

	def del_node(self, n):
		if n in self.nodes:
			n.delete()
			self.nodes.remove(n)
		return n

	def del_edge(self, n1, n2):
		e = self.get_edge(n1, n2)
		if e in self.edges:
			e.delete(); 
			self.edges.remove(e)
		return e


	def add_rand_node(self):
		return self.add_node(random.randint(1, 100))
		
	def add_rand_edge(self):
		if (len(self.nodes) == 0):
			pass

		maxWeight = 10
		n1 = self.nodes[random.randint(0, len(self.nodes) - 1)]
		n2 = self.nodes[random.randint(0, len(self.nodes) - 1)]
		weight = random.randint(0, maxWeight)

		return self.add_edge(n1, n2, weight)

	def get_edge(self, n1, n2):
		for e in self.edges:
			if ((e.start == n1 and e.end == n2) or 
				(e.start == n2 and e.end == n1)):
				return e
		return null

	def rand_graph(self, nodeCount, edgeCount):
		for i in range(0, nodeCount):
			self.add_rand_node()

		for i in range(0, edgeCount):
			self.add_rand_edge()

		return self

	def print_graph(self):
		for n in sorted(self.nodes, key=lambda x: x.id):
			print '{0:2}: {1:2}'.format(n.id, n.key)

		for e in sorted(self.edges, key=lambda x: x.start):
			# space on one side but not the other because format puts
			# space in front of each interpolation after the first
			print '{0:2} <---{1:2} --->{2:2}'.format(e.start.id,e.weight, e.end.id)

def main():
	test();
	g1 = Graph().rand_graph(10,5)

def test():
	test_graph();
	test_node();
	test_edge();

def test_graph():
	nodeCount = 8
	edgeCount = 5
	g1 = Graph().rand_graph(nodeCount, edgeCount)
	
	g1.print_graph()

	assert len(g1.nodes) == nodeCount
	assert len(g1.edges) == edgeCount

	g1.add_rand_node();
	assert len(g1.nodes) == nodeCount + 1

	g1.add_edge(g1.nodes[0], g1.nodes[1], 2)
	assert len(g1.edges) == edgeCount + 1

	degree = 0;
	for n in g1.nodes:
		degree += len(n.adjList)

	assert degree % 2 == 0
	assert degree == len(g1.edges) * 2

	e = g1.edges[0]
	g1.del_edge(e.start, e.end)
	assert len(g1.edges) * 2 == degree - 2

	newDegree = 0;
	for n in g1.nodes:
		newDegree += len(n.adjList)

	assert degree == newDegree + 2

def test_edge():
	g1 = Graph();
	for i in range(0, 4):
		g1.add_rand_node()


	e1 = g1.add_edge(g1.nodes[0], g1.nodes[1], 1)
	e2 = g1.add_edge(g1.nodes[1], g1.nodes[2], 3)
	e3 = g1.add_edge(g1.nodes[2], g1.nodes[3], 5)
	assert e1.weight + e2.weight + e3.weight == 9 

def test_node():
	n1 = Node(10)
	n2 = Node(11)
	assert n1.key == 10
	assert n2.key == 11

	e1 = Edge(n1, n2, 1)
	assert n2.is_connected(n1) == True
	assert n1.is_connected(n2) == True


	n3 = Node(12);
	e2 = Edge(n1, n3, 1)
	assert n3.is_connected(n1) == True
	assert n2.is_connected(n1) == True
	assert n1.is_connected(n2) == True
	assert n1.is_connected(n3) == True

	assert n2.is_connected(n3) == False
	assert n3.is_connected(n2) == False

def test_adj_list():
	g1 = rand_graph()
	nList = g1.nodes[0].otherNodes

if __name__ == "__main__":
	main()