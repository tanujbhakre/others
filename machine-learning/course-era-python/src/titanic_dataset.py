import os
import numpy as np
import pandas as pd
import pre_processing

def get_dataframe():
    # Getting  path where file to be read is present
    file_path = os.path.join(os.path.dirname(__file__), os.pardir, 'resources/titanic-train.csv')
    # print(file_path)
    
    # Loading file into Data Frame
    df_titanic_raw = pd.read_csv(file_path)
    # print(df_titanic_raw.head(1))
    
    # Setting passengerId as index
    df_titanic_raw.set_index('PassengerId', inplace=True)
    
    # Making values Numeric
    # Converting sex into numeric field
    is_female_function = lambda x : 1 if x == 'female' else 0
    df_titanic_raw['Is_Female'] = df_titanic_raw['Sex'].apply(is_female_function)
    
    # Converting embarked into fields
    embarked_from_S_function = lambda x : 1 if x == 'S' else 0
    embarked_from_C_function = lambda x : 1 if x == 'C' else 0
    embarked_from_Q_function = lambda x : 1 if x == 'Q' else 0
    
    df_titanic_raw['Embarked_From_Queenstown'] = df_titanic_raw['Embarked'].apply(embarked_from_Q_function)
    df_titanic_raw['Embarked_From_Cherbourg'] = df_titanic_raw['Embarked'].apply(embarked_from_C_function)
    df_titanic_raw['Embarked_From_Southampton'] = df_titanic_raw['Embarked'].apply(embarked_from_S_function)
    
    # Removing unnecessary columns
    df_titanic = df_titanic_raw.drop(['Ticket', 'Cabin', 'Name', 'Sex', 'Embarked'], axis=1)
    # print(df_titanic.head(4))
    
    # Dropping rows which have invalid values
    df_titanic.dropna(inplace=True)
    
    out_lable = 'Survived'
    
    # Getting the result
    df_titanic_result = df_titanic[out_lable]
    
    # print(df_titanic_result)
    df_titanic.rename(columns={'Pclass':'Passanger_Class', 'SibSp':'Sibling_Spouse', 'Parch':'Par_Children'}, inplace=True) 
    # print(df_titanic.head(1))
    # Feature scaling
    df_titanic_norm, mean, standard_deviation = pre_processing.feature_normalize(df_titanic)
    # print(mean)
    # print(standard_deviation)
    # print(df_titanic.head(1))
    # print(df_titanic_norm.head(1))
    # Overriding feature normalization for out
    df_titanic_norm[out_lable] = df_titanic_result
    return (df_titanic_norm, out_lable)

'''
# Code for processing the titanic data set
print('Running linear regression on Titanic Dataset')
df_data, out_lable = titanic_dataset.get_dataframe()

# Splitting data into different sizes
X_train, y_train, X_test, y_test, X_cross_valid, y_cross_valid = pre_processing.split(df_data, out_lable)

# Starting machine learning
theta = linear_regression.learn(X_train, y_train)
features = df_data.columns.tolist()
features.remove(out_lable);
features.insert(0, 'Intercept')
print(features)
print(theta.flatten().tolist())
result = learning_result.score_classification(X_test, y_test, theta)
print('accuracy=', result)


'''