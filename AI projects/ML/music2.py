import pandas as pd
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import joblib

music_data = pd.read_csv('music.csv')

print('will predict genre based on age and gender')

#load the saved model
model = joblib.load('music.joblib')

pred = model.predict([ [21, 1],[22,0]])
print('for 21 male and for 22 female..')
print(pred)


joblib.dump(model, 'music.joblib')
