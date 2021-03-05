#The program will prompt for a URL, read the JSON data from that URL using urllib and 
#then parse and extract the comment counts from the JSON data, compute the sum 
#of the numbers in the file.

import json
import urllib.request, urllib.parse, urllib.error
import ssl

# Ignore SSL certificate errors
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE 

url = input('Enter - ')
html = urllib.request.urlopen(url, context=ctx)
data = html.read().decode()

js = json.loads(data)
#print(json.dumps(js,indent=4))
count = 0
for item in js["comments"]:
    #print(item["count"])
    count += int(item["count"])
print(count)
