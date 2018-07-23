# Tyler-Atkinson-Rest-API
A REST API to access and run some of my previous Java projects, made with the Spring framework
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
  - GET {site}/salesman/genetic/{numberOfCities} = Returns the shortest route between all cities, which are generated randomly based on the provided number of cities, calculated with a genetic algorithm
  - GET {site}/salesman/annealing/{numberOfCities} = Returns the shortest route between all cities, which are generated randomly based on the provided number of cities, calculated with Simulated Annealing
<br/><br/>Each of the following take an array of cities as a parameter. The format is shown below in a JSON
<br/><i>(You should use more than 2 cities if you want it to do anything)</i>
```JSON
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

<b>Matrix Operations:</b> <i>source: </i>[Matrices](https://github.com/tha7556/Matrices "Matrices Source")
  - GET {site}/matrix/identity/{width} = Gets the identity Matrix with the given width
<br/><br/>Each of the following take an array of exactly 2 matrices of doubles. The format is shown below in a JSON
```JSON
[
   [
      [1,3,5],
      [2,3,9],
      [5,3,6]
   ],
   [
      [4,7,4],
      [1,2,2],
      [3,8,3]
   ]
]
```
  - POST {site}/matrix/add = Adds the 2 Matrices together and returns the result
  - POST {site}/matrix/subtract = Subtracts the second Matrix from the first Matrix and returns the result
  - POST {site}/matrix/multiply = Multiplies the 2 Matrices together using Matrix multiplication and returns the result
  - POST {site}/matrix/append = Appends the second Matrix on to the end of the first and returns the result
  <br/><br/>Each of the following take an array of arrays of doubles. The format is shown below in a JSON
```JSON
[
   [1,3,5],
   [2,3,9],
   [5,3,6]
]
```
  - POST {site}/matrix/multiplyScalar/{scalar} = Only takes 1 Matrix as a parameter, and multiplies it by the scalar
  - POST {site}/matrix/transpose = Gets the transpose of the provided Matrix
  - POST {site}/matrix/determinant = Gets the determinant of the provided Matrix
  - POST {site}/matrix/inverse = Gets the inverse of the provided Matrix
  

