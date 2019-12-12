from numpy import shape

import housing_dataset
import learning_result
import linear_regression
import numpy as np
import pre_processing
import titanic_dataset


print('Welcome to linear regression')

print('Linear regression for housing prices')
X, y = housing_dataset.get_dataset()

X_norm, mean, standard_deviation = pre_processing.feature_normalize(X)
# print(X_norm, mean, standard_deviation)
theta = linear_regression.learn(X_norm, y, alpha=0.01, num_iters=400)
print('theta', theta)

input = np.array([1650, 3]);
input = np.reshape(input, newshape=(1, -1))
# print('input', input)
normalized_input = (input - mean) / standard_deviation
normalized_input = np.insert(normalized_input, 0, 1, axis=1)
# print('normalized_input', normalized_input)
# print('theta shape', theta.shape)
# print('input shape', normalized_input.shape)

price = np.sum(normalized_input.dot(theta))
print('Price of 3 BHK 1650 sft house is', price)
