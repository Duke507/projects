import pandas as pd
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import joblib

music_data = pd.read_csv('music.csv')

x = music_data.drop(columns=['genre'])
y = music_data['genre']
print('will predict genre based on age and gender')
model = DecisionTreeClassifier()
model.fit(x,y)
pred = model.predict([ [21, 1],[22,0]])
print('for 21 male and for 22 female..')
print(pred)

x_train,x_test,y_train,y_test=train_test_split(x,y, test_size=0.2)
model = DecisionTreeClassifier()
model.fit(x,y)
print('now the dataset for testing is:')
print(x_test)
print('these are the correct answers:')
print(y_test)
pred = model.predict(x_test)
print('predictions were:')
print(pred)

print('accuracy is:')
print(accuracy_score(y_test,pred))

joblib.dump(model, 'music.joblib')
