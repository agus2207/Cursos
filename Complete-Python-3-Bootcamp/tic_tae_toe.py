'''
Tic Tac Toe game
'''

import random

#Function to choose marker for the first player
def player_input():
    
    marker = ''
    while marker not in ['X', 'O']: #Choose between X and O other option will be invalid
        marker = input("Please pick a marker 'X' or 'O': ").upper()
        if marker not in ['X', 'O']:
            print("Invalid input")
    return marker

#Function to display the tic tac toe board
def display_board(board):

    print(board[1:4])
    print(board[4:7])
    print(board[7:10])
    print('\n\n')

#Function to place the marker in the desired position
def place_marker(board, marker, position):

    board[position] = marker
    return board

#Function to check if someone win the game
def win_check(board, mark):

    #Check Rows
    if board[1] == mark and board[2] == mark and board[3] == mark:
        return True
    elif board[4] == mark and board[5] == mark and board[6] == mark:
        return True
    elif board[7] == mark and board[8] == mark and board[9] == mark:
        return True
    #Check Columns
    elif board[1] == mark and board[5] == mark and board[9] == mark:
        return True
    elif board[3] == mark and board[5] == mark and board[7] == mark:
        return True
    elif board[1] == mark and board[4] == mark and board[7] == mark:
        return True
    #Check Diagonals
    elif board[2] == mark and board[5] == mark and board[8] == mark:
        return True
    elif board[3] == mark and board[6] == mark and board[9] == mark:
        return True
    else:
        return False

#Function to select the first turn
def choose_first():

    return random.randint(1, 2)

#Function to check if there desired position is available
def space_check(board, position):

    if board[position] == 'X' or board[position] == 'O':
        return False
    else:
        return True

#Function to check if the board is full
def full_board_check(board):

    if ' ' in board[1:10]:
        return False
    else:
        return True

#Function to define the position the player want to play
def player_choice(board):

    choice = ''
    #Input check
    #Only want digits between 1 and 9 and we want to see if there some space available
    while not choice.isdigit() or int(choice) not in range(1,10) or not space_check(board, int(choice)):
        choice = input('Please enter a number between 1-9: ') 
        #Out of range or a invalid input
        if not choice.isdigit() or int(choice) not in range(1,10):
            print("Invalid input")
        #If the space desired is alredy used
        elif not space_check(board, int(choice)):
            print("Space not available")
    return int(choice)

#Function to check if the players want to play again
def replay():

    answer = ''
    #Check valid inputs
    while answer not in ['Y', 'N']:
        answer = input("Wanna play again [Y, N]: ").upper()
        if answer not in ['Y', 'N']:
            print("Invalid input")
    if answer == 'Y':
        return True
    else:
        return False


print("Welcome to Tic Tac Toe game")
while True:
    board = [' ']*10 #Define the initial board
    display_board(board)
    marker = player_input() #The marker selection for the first player
    if marker == 'X': #In case player 1 choose X
        players = {'1':'X', '2':'O'}
    else: #Case player 1 choose O
        players = {'1':'O', '2':'X'}
    next_player = choose_first() #Select randomly who goes first
    print(f"Next player is {next_player} with mark {players[str(next_player)]}")
    while True:
        shot = player_choice(board) #Choose a place to make the shot
        #Place the player's market in the desired position on the board
        board = place_marker(board, players[str(next_player)], shot) 
        display_board(board)
        #Check if the player already won
        if win_check(board, players[str(next_player)]):
            print("Congratulation! You have won the game!")
            break
        #Check if the board is full 
        elif full_board_check(board):
            print("Tie")
            break
        #Change the turn to the next player
        f = lambda next_player: 2 if next_player == 1 else 1  
        next_player = f(next_player)
    if not replay(): #Check if they want to play again 
        break
     