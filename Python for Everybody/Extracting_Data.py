#n this assignment you will write a Python program somewhat similar to https://py4e.com/code3/geoxml.py. 
#The program will prompt for a URL, read the XML data from that URL using urllib and then parse and 
#extract the comment counts from the XML data, compute the sum of the numbers in the file and enter the sum.

import urllib.request, urllib.parse, urllib.error
import xml.etree.ElementTree as ET
import ssl

# Ignore SSL certificate errors
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE 

url = input('Enter - ')
html = urllib.request.urlopen(url, context=ctx)
data = html.read()
#print('Retrieved', len(data), 'characters')
#print(data.decode())
tree = ET.fromstring(data)
lst = tree.findall('comments/comment')
count = 0
for item in lst:
    count += int(item.find('count').text)
    #print("Count:",item.find('count').text)
print(count)