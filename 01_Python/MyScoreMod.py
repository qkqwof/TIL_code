
# MyScore 변수... 기능...
def getSum(data):
    total = 0
    
    for i in data:
        total += i
    return total

def getMean(data):
    return getSum(data)/len(data)

def getMax(data):
    maxV = data[0]
    for i in data[1:]:
        if i > maxV:
            maxV = i
    return maxV

def getMin(data):
    minV = data[0]
    for i in data[1:]:
        if i<minV:
            minV = i
    return minV

# num1에 더 큰 값이 들어왔을 떄를 고려해서 작성...
def getTwoSum(num2, num1=1): # 두 수 사이의 합을 구하는 함수
    total = 0
    if num1 > num2:
        num1, num2 = num2, num1 # 두 수를 스왑시킴
    for i in range(num1, num2+1):
        total += i
    return total
