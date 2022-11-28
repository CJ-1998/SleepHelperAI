#!/usr/bin/env python
# coding: utf-8

# In[2]:


import pandas as pd
import numpy as np
import sys

from keras.models import load_model

data = pd.read_csv("noWakeData.csv")
data.columns

dataset = data.values
x = dataset[:,0:11]
y = dataset[:,11]


# In[11]:


def test(sleepTime,quality,one,two,three,four,five,six,seven,eight):
  train_x=[]
  train_x.append([sleepTime+one,sleepTime,(sleepTime/(sleepTime+one))*100
                  ,two,three,four,five,six,seven,eight,quality])
  train_x=np.array(train_x)
  ans=model.predict(train_x)
  if(count(ans)==1):
    return sleepTime

def count(ans):
  cnt=0
  cnt=ans.argmax()
  # print(cnt)
  if(cnt==5):
    return 1
  else:
    return 0

def start(argument):
    strList=argument.split('/')
    #인자는 자기전 누워있는 시간, 침대in-취침, 침대out-기상, 눈감고 잠드는 시간,
    #수면제 여부, 커피, 알콜, 낮잠
    #총 8개 순서대로 one, two, three, four, five, six, seven, eight
    argList=[]
    for arg in strList:
        argList.append(int(arg))
    
    Arr=[]
    for quality in range(4,6):
      arr=[]
      for sleepTime in range(200, 800):  
          t=test(sleepTime,quality,argList[0],argList[1],argList[2],argList[3],argList[4],
                argList[5],argList[6],argList[7])
          if(t!=None):
            arr.append(t)
      Arr.append(arr)

    Ans=[]
    indexArr=[]
    for ans in Arr:
      if(len(ans)==0):
        indexArr.append(1000)
      else:
        indexArr.append(len(ans))

    if(indexArr[0]==1000 and indexArr[1]==1000):
        print(360,360)
        
    else:
        Ans=Arr[indexArr.index(min(indexArr))]
        print(Ans[0],Ans[-1])
    
if __name__ == "__main__":
    model=load_model('SleepHelper1.h5')
    string=str(sys.argv[1])
    start(string)


# In[ ]:





# In[ ]:




