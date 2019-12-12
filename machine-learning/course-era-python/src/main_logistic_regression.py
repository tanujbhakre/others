from numpy import shape 
import admission_dataset
import learning_result
import logistic_regression
import numpy as np
import pre_processing
import titanic_dataset


print('Welcome to linear regression')

print('Linear regression for housing prices')
X, y = admission_dataset.get_dataset()


X_norm, mean, standard_deviation = pre_processing.feature_normalize(X)
# print(X_norm, mean, standard_deviation)
theta = logistic_regression.learn(X_norm, y, alpha=0.03, num_iters=400)
print('theta', theta)

input = np.array([100, 100]);

probability = logistic_regression.predict_probablity(input, theta, mean , standard_deviation)
print('Prediction for ', input, probability)
