"""
Searches through graph using BFS
"""

import graph
from Queue import Queue

def main():
	g1 = graph.Graph().rand_graph(10,20)
	source = g1.nodes[0]
	BFS(g1, source)


def BFS(g, source):
	"""
	Do a BFS graph search on passed in Graph
	"""
	for n in g.nodes:
		n.predecessor = None
		n.distance = -1
		n.color = "white"
	
	source.distance = 0
	source.color = "gray"
	q = Queue()
	q.put(source)
	while not q.empty():
		newS = q.get()
		for adjNode in newS.adjList:
			if adjNode.color == "white":
				adjNode.color = "gray"
				adjNode.distance = newS.distance + 1
				adjNode.predecessor = newS
				q.put(adjNode)
		newS.color = "black"


	for n in g.nodes:
		print "id:{0} distance:{1}".format(n.id, n.distance)
		print printBFS(g, source, n)

def printBFS(g, source, vertex):
	"""
	print the BFS tree created by BFS
	"""
	if source == vertex:
		return source.id
	elif vertex.predecessor == None:
		return  "No path"
	else:
		return "{0}-->{1}".format(
			printBFS(g, source, vertex.predecessor), 
		 	vertex.id)




if __name__ == "__main__":
	main()