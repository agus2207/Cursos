#10.2 Write a program to read through the mbox-short.txt and figure out the distribution by hour of the day for each of the messages. 
#You can pull the hour out from the 'From ' line by finding the time and then splitting the string a second time using a colon.
#From stephen.marquard@uct.ac.za Sat Jan  5 09:14:16 2008
#Once you have accumulated the counts for each hour, print out the counts, sorted by hour as shown below.

name = input("Enter file:")
if len(name) < 1 : name = "mbox-short.txt"
handle = open(name)
hours = dict()
for line in handle:
    if line.startswith('From'):
        aux = line.split()
        if(len(aux) > 2):
            straux = aux[5].split(":")
            hora = straux[0]
            hours[hora] = hours.get(hora,0)+1
            
for k,v in sorted(hours.items()):
    print(k, v)