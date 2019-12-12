import numpy as np

def score_classification(X, y, theta):
    # accuracy 
    # Adding intercept term to X
    X = np.insert(X, 0, 1, axis=1)
    estimate = X.dot(theta)
    estimate[estimate >= .5] = 1
    estimate[estimate < .5] = 0
    estimate = estimate.astype(int)
    result_vector = estimate == y
    total_correct = np.sum(result_vector)
    # print('total_correct', total_correct)
    # print('total predicted', result_vector.size)
    return (total_correct) / result_vector.size

def score_regression(X, y, theta):
    # accuracy 
    # Adding intercept term to X
    X = np.insert(X, 0, 1, axis=1)
    estimate = X.dot(theta)
    difference = (y - estimate)
    # print(difference)
    error = (np.sum(np.sqrt(difference ** 2))) / y.shape[0]
    return error
