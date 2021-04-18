import tkinter as tk
import tkinter.filedialog as fd
from PIL import Image
import threading

#Function to insert the watermark in the image
def insert_watermark(file_path, mark_file):
    #Make the name for the image with the watermark
    new_file_path = file_path[:-4]+"_watermark"+file_path[-4:]
    image = Image.open(file_path) #Open the image
    mark = Image.open(mark_file) #Open the watermark
    width, height = image.size #Width and height of the image
    mark = mark.resize((width, height)) #Resize the watermark to the size of the image
    mark.putalpha(150) #Make the watermark more transparent
    image.paste(im=mark, box=(0,0), mask=mark) #Paste the watermark in the image
    image.save(new_file_path, format=None) #Save the image in the path we mmake
    image.show() #Show the image to the user
    
threads = []
#Make a window to the user
root = tk.Tk()
#Ask for the images to modify
images = fd.askopenfilenames(parent=root, title='Choose your images', filetypes = (("JPG Files","*.jpg"),
("PNG Files","*.png"),))
#Ask for the watermark
watermark = fd.askopenfilename(parent=root, title='Choose a watermark', filetypes = (("JPG Files","*.jpg"),
("PNG Files","*.png"),))
for f in images:
    #Make de threading call the function
    t = threading.Thread(target=insert_watermark, args=(f,watermark,))
    threads.append(t)
    #Start the threading
    t.start()
