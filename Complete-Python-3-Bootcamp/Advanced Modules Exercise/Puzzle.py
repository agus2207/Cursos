import zipfile
import os
import re

def unzip_file():
    zip_object = zipfile.ZipFile('unzip_me_for_instructions.zip', 'r')
    zip_object.extractall()

def walk_files():
    pattern = r'\d{3}-\d{3}-\d{4}'
    for folder , sub_folders , files in os.walk("extracted_content"):
        print("Currently looking at folder: "+ folder)
        path = folder
        for f in files:
            read_file = open(folder+'/'+f, 'r')
            text = read_file.read()
            read_file.close()
            matches = re.findall(pattern, text)
            if len(matches) > 0:
                print("\t File: "+f)
                print(matches)

walk_files()