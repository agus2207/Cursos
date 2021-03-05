#In this assignment you will write a Python program that expands on https://www.py4e.com/code3/urllinks.py. 
#The program will use urllib to read the HTML from the data files below, 
#extract the href= values from the anchor tags, scan for a tag that is in a particular 
#position from the top and follow that link, repeat the process a number of times, and 
#report the last name you find.

import urllib.request, urllib.parse, urllib.error
from bs4 import BeautifulSoup
import ssl

def conect(name,position):
    # Ignore SSL certificate errors
    ctx = ssl.create_default_context()
    ctx.check_hostname = False
    ctx.verify_mode = ssl.CERT_NONE 

    html = urllib.request.urlopen(name, context=ctx).read()
    soup = BeautifulSoup(html, 'html.parser')

    # Retrieve all of the anchor tags
    tags = soup('a')
    count = 1;
    for tag in tags:
        #print(tag.get('href', None))
        if(count == position):
            return tag.get('href', None)
        else:
            count += 1

inicio = input("Enter URL: ")
position = input("Enter position: ")
count = input("Enter count: ")
for i in range (0,int(count)):
    inicio = conect(inicio,int(position))
    print(inicio) 
