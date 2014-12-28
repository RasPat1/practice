import graph
import BFS



def main():
	g1 = graph.Graph().rand_graph(1, 12)
	source = g1.nodes[0]
	DFS(g1, source)
	# test_DFS()

def DFS(g, source):
	for n in g.nodes:
		n.color = "white"
		n.pred = None
	time = 0;
	for n in g.nodes:
		# print n.color
		if n.color == "white":
			time = DFS_visit(n, time)
	print_DFS(g)

def DFS_visit(source, time):
	time += 1
	source.startTime = time
	source.color = "gray"
	for n in source.adjList:
		if n.color == "white":
			n.pred = source
			time = DFS_visit(n, time)
	source.color = "black"
	time += 1
	source.endTime = time;
	return time


def print_DFS(g):
	for n in g.nodes:
		predString = "{0}".format(n.id)
		while n.pred != None:
			n = n.pred
			predString = "{0}-->".format(n.id) + predString
		print predString


def test_DFS():
	g1 = graph.Graph().rand_graph(10,15)
	BFS.BFS(g1, g1.nodes[0])
	distances = {}
	for n in g1.nodes:
		distances[n.id] = n.distance
		n.distance = -1

	DFS(g1, g1.nodes[0])
	print distances
	for n in g1.nodes:
		assert distances[n.id] == n.distance

if __name__ == "__main__":
	main()