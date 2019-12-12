import gradient_descent
import matplotlib.pyplot as plt
import numpy as np
import scipy.optimize as opt  


def sigmoid(z) :
    return 1 / (1 + np.exp(-z))

def compute_cost(X, y, theta) :
    cost = 0
    data_size, feature_size = X.shape
    # estimated value
    h_of_X = sigmoid(X.dot(theta))
    first = (-y * np.log(h_of_X))
    second = (1 - y) * (np.log(1 - h_of_X))
    cost = np.sum(first - second) / data_size
    # print('cost', cost)
    return cost


def compute_gradient(X, y, theta) :
    data_size, feature_size = X.shape
    gradient = np.zeros(feature_size)
    # estimated value
    h_of_X = sigmoid(X.dot(theta))
    gradient = np.transpose(h_of_X - y).dot(X) / data_size
    
    return gradient

def learn(X, y, alpha, num_iters) :
     # Adding intercept term to X
    X = np.insert(X, 0, 1, axis=1)
    # Getting size of training data
    training_size, feature_size = X.shape
    # Initializing learning parameters
    theta = np.zeros((feature_size , 1))
    theta, J_history = gradient_descent.gradient_descent(X, y, theta, alpha, num_iters, compute_cost)
    # plotting the cost in each iteration
    # TODO Need to add label to x as 'Number of iterations' and y as 'Cost J'
    plt.plot(J_history)
    # plt.show()
    return theta

def predict_probablity(X, theta, mean , standard_deviation):
    input = np.reshape(X, newshape=(1, -1))
    # print('input', input)
    normalized_input = (input - mean) / standard_deviation;
    normalized_input = np.insert(normalized_input, 0, 1, axis=1)
    # print('normalized_input', normalized_input)
    # print('theta shape', theta.shape)
    # print('input shape', normalized_input.shape)
    return sigmoid(normalized_input.dot(theta))
