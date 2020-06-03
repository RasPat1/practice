/**
 * Classic Knapsack
*/


class Dog{  
  action

  train(){
    action.call()
  }
}

sit = { println "Sit, Sit! Sit! Good dog"}
down = { println "Down! DOWN!" }


myDog = new Dog(action:sit)
myDog.train()  // prints Sit, Sit! Sit! Good dog

mollie = new Dog(action:down)
mollie.train() // prints Down! DOWN!

/*
class Sack {
		// maxWeight
	weight
	items
	value

	addItem(item) {
		if (weight + item.weight < maxWeight) {
			weight += item.weight
			items.append(item)
			value += item.value
			true
		}
		false
	}

}

class Item {
	weight
	value

}

println "ha"