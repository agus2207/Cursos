import csv
'''
Autor: Agustin Galindo Reyes
Fecha: 03 de marzo del 2021
Descripcion: Script que cambia los delimitadores de un archivo por pipes (|)
Input: Archivo csv
Output: Archivo separado por pipes
'''
#array para almacenar lo delimitadores mas comunes que se puede encontrar en un archivo csv
delimitadores_comunes = [',','\t'] 
mydata = [] #arreglo para guardar los datos a almacenar
header = [] #arreglo con los datos del header del documento

with open('starts_data.csv') as f: #se abre el archivo dañado
    Lines = f.readlines() #Leemos el archivo linea por linea
    for Line in Lines: #Recorremos cada linea del archivo leido
        if "INFECTADO" in Line or "Delete" in Line: #Ignoramos las lineas que no nos sirven
            continue
        if "~" in Line: #Buscamos la linea con la informacion de los header
            Line = Line.replace("\n","") #Quitamos el salto de linea
            header = Line.split("~") #Alamacenamos los titulos de cada columna
            continue
        #Buscamos delimitadores en el archivo en caso de que cuente con mas de uno    
        for delimitador in delimitadores_comunes: 
            Line = Line.replace(delimitador, "|") #Los remplazamos por comas
            Line = Line.replace("\n","") #Eliminamos los salto de linea

        #Separmos los datos por columna y los almacenamos en el arreglo a escribir en el nuevo archivo
        mydata.append(Line.split("|")) 

#Vamos a crear el archivo de salida con una codificacion utf-8, el delimitador | y con dobles comillas en
#todas las columnas del archivo
writer = csv.writer(open("respuesta.psv", 'w', encoding="utf-8"), delimiter='|', quoting=csv.QUOTE_ALL)
writer.writerow(header) #Escribimos el Header del nuevo archivo
writer.writerows(mydata) #Escribimos los datos en el nuevo 

