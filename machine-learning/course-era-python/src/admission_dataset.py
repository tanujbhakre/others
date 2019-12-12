import os

from numpy import genfromtxt

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd


def get_dataset():
    # Getting  path where file to be read is present
    file_path = os.path.join(os.path.dirname(__file__), os.pardir, 'resources/admission-data.csv')
    housing_data = genfromtxt(file_path, delimiter=',')
    return (housing_data[:, :2], housing_data[:, 2:])

def get_dataframe():
    file_path = os.path.join(os.path.dirname(__file__), os.pardir, 'resources/admission-data.csv')
    data = pd.read_csv(file_path, header=None, names=['Exam 1', 'Exam 2', 'Admitted'])
    positive = data[data['Admitted'].isin([1])]  
    negative = data[data['Admitted'].isin([0])]
    
#     fig, ax = plt.subplots(figsize=(12, 8))
#     ax.scatter(positive['Exam 1'], positive['Exam 2'], s=5, c='b', marker='o', label='Admitted')
#     ax.scatter(negative['Exam 1'], negative['Exam 2'], s=50, c='r', marker='x', label='Not Admitted')   
#     ax.legend()  
#     ax.set_xlabel('Exam 1 Score')  
#     ax.set_ylabel('Exam 2 Score')  
#     
#     x = np.linspace(data['Exam 1'].min(), data['Exam 1'].max(), 100) 
#     y = np.linspace(data['Exam 2'].min(), data['Exam 2'].max(), 100) 
#     f = 0.59999693 + (0.2873276 * x) + (0.25778387 * y)
#     ax.plot(x, f, 'r', label='Prediction') 
#     
#     plt.show()
#     
    # append a ones column to the front of the data set
    data.insert(0, 'Ones', 1)
    
    # set X (training data) and y (target variable)
    cols = data.shape[1]  
    X = data.iloc[:, 0:cols - 1]  
    y = data.iloc[:, cols - 1:cols]
    X = np.matrix(X.values)  
    y = np.matrix(y.values)    
    return X , y 

get_dataframe()
