# Tyler-Atkinson-Rest-API
A REST API to access and run some of my previous Java projects

Calls available so far:

Markov Chains:
<br/>GET {site}/markov/{user} = Gets a tweet generated based off of the user
<br/>GET {site}/markov/{user}/{quantity} = Gets {quantity} tweets generated based off of the user

Backpropagation:
<br/>POST {site}/backpropagation/ = Gets a neural network trained from backpropagation.
<br/>It should pass a JSON object in this form:
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

<br/>POST {site}/backpropagation/run = runs the trained neural network and returns the result
<br>It should pass the created network in the JSON that is returned from POST {site}/backpropagation, modifying only the data inside of the input.

Working on implementing calls for various methods of solving Travelling Salesman as well as others
