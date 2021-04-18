from decimal import *
import math

#Funtion to ask for a the number of decimals the user want
def input_decimal():
    decimals = ''
    #Ask for an input while the input is not a digit or is less than 0
    while not decimals.isdigit() or int(decimals) < 0:
        decimals = input("Please enter the total number of decimals you want: ")
        #In case the input is not a digit
        if not decimals.isdigit():
            print("Invalid input")
        #In case the input is less than 0
        elif int(decimals) < 0:
            print("Please enter a positive number")
    return int(decimals) #Return the number of decimals

#Function to find the factorial of a number
def factorial(num):
    res = 1
    #The factorial of 0 is 1
    if num == 0:
        return 1
    #The factorial of a number is (n)*(n-1)*(n-2)*...*(1)
    else:
        for i in range(1, num+1):
            res = res*i
        return res

#Funtion tha calculate decimals with the chudnovsky formula
def chudnovsky(n):
    res = 0
    for k in range(0,n+1):
	    first = factorial(6*k)*(13591409+545140134*k)
	    down = factorial(3*k)*(factorial(k))**3*(640320**(3*k))
	    res += first/down 
    return Decimal(res)

#Calculate pi with the chudnovsky formula output
def calculate_pi(chudnovsky):
    up = 426880*math.sqrt(10005)
    pi = Decimal(up)/chudnovsky 
    return pi

decimals = input_decimal()
getcontext().prec = decimals+1 #The number of decimals show with base on the user input
res = chudnovsky(decimals)
pi = calculate_pi(res)
print(pi)