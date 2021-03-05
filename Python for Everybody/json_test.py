import json
import os

handle = open('C:\\Users\\agust\\Documents\\Cursos\\local_storage.json', 'r+')
#data = json.loads(handle)
count = 0
str_original = '{"ID":"bangkok","IsPermanentlyOwned":true,"TemporarilyOwned":null}'
str_temp = '{"ID":"test","IsPermanentlyOwned":true,"TemporarilyOwned":null}'
str_final = '{"ID":"bangkok","IsPermanentlyOwned":true,"TemporarilyOwned":null},'
for i in range(1,1000):
    temp = "test"+str(i)
    str_final += str_temp.replace("test", temp)+","
    #print(str_final)
str_final = str_final[:len(str_final)-1]
#print(str_final)
print("Longitud de cadena final:", len(str_final))
for line in handle:
    print(line)
    print("Longitud de inicial:", len(line))
    line = line.replace(str_original, str_final)
    print(line)
    print("Longitud final:", len(line))
    handle.write(line)
handle.close()
"""
print(len(str))
for line in handle:
    if str in line:
        print("Fine")
        x = line.index(str)
        print(x)
        tem = x + len(str)
        print(tem)
        print(line[:tem])
        print(line[tem+1:])
    count+=1
print(count)
"""