"""
Uses Djikstra's Algorithm to find the shortest 
path from a given source node to all other nodes
"""

import graph
from Queue import PriorityQueue as pQueue
import sys

def main():
	test()

def Dijkstra(g, source):
	result = {}

	# pQueue filled with tuples path weight, destination node id
	source.d = 0
	q = [];
	q.append(source)

	for n in g.nodes:
		if n != source:
			n.d = sys.maxint
			q.append(n)


	while len(q) != 0:
		q.sort(key=lambda x: x.d)
		u = q.pop(0)
		result[u.id] = u.d;
		for n in u.adjList:
			newWeight = u.d + g.get_edge(u, n).weight
			if newWeight < n.d:
				result[n.id] = newWeight
				n.d = newWeight
				n.pred = u
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
         
	assert Dijkstra(g1, n1) == expected


if __name__ == "__main__":
	main()