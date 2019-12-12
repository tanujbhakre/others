import numpy as np

def feature_normalize(x):
    """
    Expects input to be pandas Data Frame
    """
    mean = x.mean(axis=0)
    # print(mean)
    standard_deviation = x.std(axis=0)
    x_norm = (x - mean) / standard_deviation
    # print('x', x)
    # print('mean', mean)
    # print('standard_deviation', standard_deviation)
    # print('x_norm', x_norm)
    
    return x_norm, mean, standard_deviation

def split(df, out_lable, train_frac=.6, test_frac=.2, drop_out_lable=True):
    df_x_train, df_x_test, df_x_cross_valid = np.split(df.sample(frac=1), [int(train_frac * len(df)), int((train_frac + test_frac) * len(df))])
    
    # Setting out data and converting them to Numpy array
    y_train = np.reshape(df_x_train[out_lable].as_matrix(), newshape=(-1, 1))
    y_test = np.reshape(df_x_test[out_lable].as_matrix(), newshape=(-1, 1))
    y_cross_valid = np.reshape(df_x_cross_valid[out_lable].as_matrix(), newshape=(-1, 1))
    
    if drop_out_lable :
        # dropping out label
        df_x_train.drop([out_lable], axis=1, inplace=True)
        df_x_test.drop([out_lable], axis=1, inplace=True)
        df_x_cross_valid.drop([out_lable], axis=1, inplace=True)
     
    # Converting data to Numpy array
    x_train = df_x_train.as_matrix()
    x_test = df_x_test.as_matrix()
    x_cross_valid = df_x_cross_valid.as_matrix()
    
    # print('x_train', x_train)
    
    return (x_train, y_train, x_test, y_test, x_cross_valid, y_cross_valid)
'''
X = np.array([[1, 2], [4, 5]])
feature_normalize(X)
'''
