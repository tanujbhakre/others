import os
from numpy import genfromtxt

def get_dataset():
    # Getting  path where file to be read is present
    file_path = os.path.join(os.path.dirname(__file__), os.pardir, 'resources/housing-prices.csv')
    housing_data = genfromtxt(file_path, delimiter=',')
    return (housing_data[:, :2], housing_data[:, 2:])
