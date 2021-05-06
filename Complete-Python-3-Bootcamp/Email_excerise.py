import smtplib
from email.mime.text import MIMEText
import getpass # For hidden passwords
import requests
import bs4

#Function to get all the external links of a page
def get_urls():
    urls = '' 
    url = input("Type a url to scrap: ") #Ask for a page to scrap
    try:
        res = requests.get(url) #Try to make the conection
    except:
        print("Unable to scrap") #If the conection fail
        return 0
    soup = bs4.BeautifulSoup(res.text,"lxml") #Convert the request in a lxml format
    tags = soup.select('a') #Select all the a tags
    for tag in tags:
        try:
            if "http" in tag.get("href"): #Find all the href that start with http
                urls += tag.get("href") + '\n' #Make a string with the url found
        except:
            continue #In case we found a NoneType object
    return urls #Return all the urls found in a string format


smtp_object = smtplib.SMTP('smtp.gmail.com',587) #Establish conection with gmail
smtp_object.ehlo() 
smtp_object.starttls()

email = input("Enter your email: ") #Get the user's email
password = getpass.getpass("Enter your password: ") #Get the password
smtp_object.login(email,password) #Login into the account

msg = MIMEText(get_urls(), 'plain', 'utf-8') #message to send
msg['Subject'] = 'Urls find in the page' #subject of the message
smtp_object.sendmail(email,email,msg.as_string()) #send the message

smtp_object.quit() #quit the current sesion
