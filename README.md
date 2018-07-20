# Tyler-Atkinson-Rest-API
A REST API to access and run some of my previous Java projects
<br/><i>(Not yet on a server)</i>

Calls available so far:

<b>Markov Chains:</b> <i>source: </i>[Markov Chains](https://github.com/tha7556/Ai-Markov-Chains "Markov Chain Source")
  - GET {site}/markov/{user} = Gets a tweet generated based off of the user
  - GET {site}/markov/{user}/{quantity} = Gets {quantity} tweets generated based off of the user

<b>Backpropagation:</b> <i>source: </i>[Backpropagation](https://github.com/tha7556/Ai-Backpropagation "Backpropagation Source")
  - POST {site}/backpropagation/ = Gets a neural network trained from backpropagation.
<br/>It should pass a JSON object in this form:
<br/><i>ex) A XOR network</i>
```JSON
{
   "input": [
       [0.1,0.1],
       [0.1,0.9],
       [0.9,0.1],
       [0.9,0.9]
   ],
   "expectedOutput": [
       [0.1],
       [0.9],
       [0.9],
       [0.1]
   ],
   "numHidden": 3,
   "targetError": 0.000000001
}
```
<br/>input: A 2D array of doubles containing the training set inputs
<br/>expectedOutput: A 2D array of doubles containing the expected output corresponding to each input
<br/>numHidden: The number of hidden nodes to have
<br/>targetError: The target error to train to

  - POST {site}/backpropagation/run = runs the trained neural network and returns the result
<br>It should pass the created network in the JSON that is returned from POST {site}/backpropagation, modifying only the data inside of the input.

<b>Traveling Salesman Problem:</b> <i>source: </i>[Traveling Salesman](https://github.com/tha7556/Traveling-Salesman-Problem "Traveling Salesman Source")
<br/>Each of these take an array of cities as a parameter. The format is shown below in a JSON
<br/><i>(You should use more than 2 cities if you want it to do anything)</i>
```
[
    {
      "x": 10,
      "y": 11,
      "name": "A"
    },
    {
      "x": 15,
      "y": 12,
      "name": "B"
   }
]
```
   - POST {site}/salesman/genetic/ = Returns the shortest route between all cities, calculated with a genetic algorithm
   - POST {site}/salesman/annealing/ = Returns the shortest route between all cities, calculated with Simulated Annealing
