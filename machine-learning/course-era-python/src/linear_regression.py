import gradient_descent
import matplotlib.pyplot as plt
import numpy as np


def compute_cost(X, y, theta):
    data_size, feature_size = X.shape
    prediction = X.dot(theta)
    # print(prediction - y)
    return np.sum((prediction - y) ** 2) / (2 * data_size)

def learn(X, y, alpha=0.1, num_iters=100):
    '''    
    alpha - learning rate
    num_iters - number of iteration of gradient descent
    '''
    # Adding intercept term to X
    X = np.insert(X, 0, 1, axis=1)
    # Getting size of training data
    training_size, feature_size = X.shape
    # Initializing learning parameters
    theta = np.zeros((feature_size , 1))
    theta, J_history = gradient_descent.gradient_descent(X, y, theta, alpha, num_iters, compute_cost)
    # plotting the cost in each iteration
    # TODO Need to add label to x as 'Number of iterations' and y as 'Cost J'
    # plt.plot(J_history)
    # plt.show()
    return theta
    
