#In this assignment you will read through and parse a file with text and numbers. 
#You will extract all the numbers in the file and compute the sum of the numbers.
#regex_sum_890137.txt: http://py4e-data.dr-chuck.net/regex_sum_890137.txt

import re

handle = open("regex_sum_890137.txt")
lst = list()
for line in handle:
    #print(line)
    aux = re.findall('[0-9]+',line)
    if(len(aux)>0):
        lst = lst + aux
for i in range(0, len(lst)):
    lst[i] = int(lst[i])
print(sum(lst))