import string

alphabet_lower = string.ascii_lowercase #The alphabet in lowercase
alphabet_upper = string.ascii_uppercase #The alpabeth in uppercase

#Funtion to cipher a text
def caesar_cipher(text, key):
    encrypt_msg = '' 
    #We analize every characte in the text
    for character in text: 
        #If the character is in the alphabet
        if character in alphabet_lower or character in alphabet_upper:
            #If the character is lower
            if character in alphabet_lower:
                #We calculate a new character with base in its position
                new_char = ((alphabet_lower.index(character))+key)%26
                #We add the new character to the encrypted message
                encrypt_msg += alphabet_lower[new_char]
            #In case the character is upper
            elif character in alphabet_upper:
                new_char = ((alphabet_upper.index(character))+key)%26
                encrypt_msg += alphabet_upper[new_char]
        #In case the caracter is not in the alphabet
        else:
            encrypt_msg += character
    return encrypt_msg #Return the encrypted message

#Funtion to descipher a message
def caesar_descipher(text, key):
    decrypt_msg = ''
    #We analize every character in the text
    for character in text:
        #If the character is in the alphabet
        if character in alphabet_lower or character in alphabet_upper:
            #If the character is lower
            if character in alphabet_lower:
                #We calculate a new character with base in its position
                new_char = ((alphabet_lower.index(character))-key)%26
                #Add the new character to the decripted message
                decrypt_msg += alphabet_lower[new_char] 
            #In case the character is upper
            elif character in alphabet_upper:
                new_char = ((alphabet_upper.index(character))-key)%26
                decrypt_msg += alphabet_upper[new_char]
        #In case the character is not in the alphabet
        else:
            decrypt_msg += character
    return decrypt_msg #Return the decripted message

#Funtion to ask for the key to cipher/Descipher
def input_key():
    key = ''
    #Ask for an input while the key is not a digit or the key is out the range 1-26
    while not key.isdigit() or int(key) not in range(1,26):
        key = input("Please enter a number between 1 and 25: ")
        #Inform the user his key is invalid
        if not key.isdigit() or int(key) not in range(1,26):
            print("Invalid input")
    return int(key) #Return the key

#Function to ask for the text to cipher
def input_text():
    text = input("Type some text to cipher: ")
    return text

key = input_key()
text = input_text()
encrypt_msg = caesar_cipher(text, key)
print(encrypt_msg)
decrypt_msg = caesar_descipher(encrypt_msg, key)
print(decrypt_msg)