import numpy as np


def gradient_descent(X, y, theta, alpha, num_iterations, cost_function):
    J_history = np.zeros(num_iterations)
    data_size, feature_size = X.shape
    for iteration in range (num_iterations):
        error = X.dot(theta) - y  # result is m X 1
        delta = np.transpose(X).dot(error) / data_size  # result is n X 1 
        theta = theta - (alpha * delta)
        # computing cost
        cost = cost_function(X, y, theta)
        np.put(J_history, iteration, cost)
    return (theta , J_history)
