#!/usr/bin/env python
# coding: utf-8

# In[23]:


import pandas as pd
import numpy as np
import sys

from keras.models import load_model

data = pd.read_csv("noWakeData.csv")
data.columns

#데이터 나누기. X= 입력, Y= 정답
dataset = data.values

x = dataset[:,0:11]
y = dataset[:,11]


# In[68]:


#입력으로 예측하는 함수. 수면의 질이 5인 시간만 반환한다. 
def test(one,two,three,four,five,six,seven,eight):
    
#     예측해야 하는 모든 값을 numpy array로 넣고 2차원 numpy array 만든다. 
    train_X5=np.empty((0,11),int)
    train_X4=np.empty((0,11),int)
    for sleepTime in range(200, 600):
        train_X5=np.append(train_X5, np.array([[sleepTime+one,sleepTime,(sleepTime/(sleepTime+one))*100,
                                                two,three,four,five,six,seven,eight,5]]),axis=0)
        train_X4=np.append(train_X4, np.array([[sleepTime+one,sleepTime,(sleepTime/(sleepTime+one))*100
                  ,two,three,four,five,six,seven,eight,4]]),axis=0)

#     print(train_X5)
    predictAll(train_X5, train_X4)


def predictAll(train_X5, train_X4):
    
#     넘겨 받은 2차원 numpy array를 predict로 예측한다. 
#     [[~,~,~,~,~,~], [~,~,~,~,~]..] 이런 형태로 나온다. 
    ans5=model.predict(train_X5)
    ans4=model.predict(train_X4)

#     2차원 배열 안의 각 1차원 배열들에 argmax를 한다. 
#     argmax하면 가장 높은 값을 가지는 index의 값이 나온다.
#     [3,3,3,3,5,5,5,4,4,4...] 이런 식으로 나온다. 
    ind5=np.argmax(ans5,axis=1)
    ind4=np.argmax(ans4,axis=1)
    
#     index의 값이 5인 것의 index를 얻어온다. 
#     [100, 101, 102, ...] 이런 식으로 나온다. 
    realAns5=np.where(ind5==5)
    realAns4=np.where(ind4==5)
    
#     실제 시간이 저장된 배열
    printAns5=realAns5[0]+200
    printAns4=realAns4[0]+200
    
#     print(printAns5)
#     print(printAns4)
    
#     시간 나오면 길이 짧은 것 시작, 끝 시간 반환
#     안 나오면 360, 360 반환
    if(len(printAns5)==0 and len(printAns4)==0):
        print(360,360)
    else:
        if(len(printAns5)>len(printAns4)):
            print(printAns4[0],printAns4[-1])
        else:
            print(printAns5[0],printAns5[-1])
    

#그냥 전체 함수
def start(argument):
    #인자 /로 나누는 것
    strList=argument.split('/')
    
    #인자를 숫자로 바꾸는 것
    argList=[]
    for arg in strList:
        argList.append(int(arg))
        
    test(argList[0],argList[1],argList[2],argList[3],argList[4],
                argList[5],argList[6],argList[7])

if __name__ == "__main__":
    model=load_model('SleepHelper1.h5')
#     string="100/60/60/30/0/0/0/0"
#     start(string)
    string=str(sys.argv[1])
    start(string)


# In[ ]:





# In[ ]:





# In[ ]:




