import graph
import sys

def main():
	# BF()
	test()
	# pass

def BF(g, source):
	for node in g.nodes:
		node.d = sys.maxint
		node.pred = None
	source.d = 0

	for i in range(len(g.nodes) - 1):
		for edge in g.edges:
			n1 = edge.start
			n2 = edge.end
			if n1.d + edge.weight < n2.d:
				n2.d = n1.d + edge.weight
				n2.pred = n1
			if n2.d + edge.weight < n1.d:
				n1.d = n2.d + edge.weight
				n1.pred = n2

	for edge in g.edges:
		n1 = edge.start
		n2 = edge.end
		if (n1.d > n2.d + edge.weight) or (n2.d > n1.d + edge.weight):
			return False

	result = {i.id: i.d for i in g.nodes}
	return result

def test():
	g1 = graph.Graph()
	n1 = g1.add_rand_node()
	n2 = g1.add_rand_node()
	n3 = g1.add_rand_node()
	n4 = g1.add_rand_node()
	n5 = g1.add_rand_node()

	g1.add_edge(n1, n2, 1)
	g1.add_edge(n2, n3, 4)
	g1.add_edge(n1, n3, 2)
	g1.add_edge(n3, n4, 3)
	g1.add_edge(n2, n4, 3)
	g1.add_edge(n1, n5, 6)
	g1.add_edge(n4, n5, 1)
	g1.add_edge(n2, n5, 1)

	expected = {i.id: j for i,j in zip([n1,n2,n3,n4,n5], [0,1,2,3,2])}
	assert BF(g1, n1) == expected

def test_negative_cycles():
	g1 = graph.Graph()
	n1 = g1.add_rand_node()
	n2 = g1.add_rand_node()
	n3 = g1.add_rand_node()
	n4 = g1.add_rand_node()
	n5 = g1.add_rand_node()

	g1.add_edge(n1, n2, 1)
	g1.add_edge(n2, n3, -4)
	g1.add_edge(n3, n1, 2)
	g1.add_edge(n2, n4, 3)
	g1.add_edge(n1, n5, 4)

	assert BF(g1, n1) == False
if __name__ == "__main__":
	main()