# SleepHelperAI
SleepHelper AI를 위한 repository

## 서버 폴더
1. ANNPredict.py, SleepHelper1.h5: AI 모델과 예측 프로그램. ServerTest.java에서 사용됨. 

   (ANNPredict.py가 성능 향상된 버전이 ANNPredictTestSpeedup.py)
2. ANNPredictTest.ipynb: local에서 AI 잘 돌아가는지 테스트하기 위한 프로그램.
3. MainActivity.kt: 안드로이드에서 서버 통신 잘 되는지 확인하기 위한 코틀린 클래스.
4. ClientTest: 위의 안드로이드 프로그램 전체. 혹시 몰라 그냥 올렸습니다. 
5. ServerTest.java: 서버 프로그램
6. ClientTest.java: 서버 프로그램 잘 돌아가는지 테스트하기 위한 client 프로그램.
7. 서버설명.docx: 필요한 설명 적은 문서

## SleepHelper 폴더
1. ANNpredict는 98% 정확도 ANN 모델로 예측하는 파일
2. ANNpredict1은 70% 정확도 ANN 모델로 예측하는 파일
3. ANNtest~ANNtest9는 여러 ANN 모델 생성 테스트 파일
4. dataTest는 데이터 확인 파일
5. DTtest는 decision tree 모델 적용 파일
6. otherModelTest는 다른 여러 모델 적용 파일
7. SVMtest는 Support vector machine 모델 적용 파일
8. test는 처음 각 모델들 사용 예제 파일
