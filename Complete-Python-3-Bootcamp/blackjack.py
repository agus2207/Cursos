import random

suits = ('Hearts', 'Diamonds', 'Spades', 'Clubs')
ranks = ('Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine', 'Ten', 'Jack', 'Queen', 'King', 'Ace')
values = {'Two':2, 'Three':3, 'Four':4, 'Five':5, 'Six':6, 'Seven':7, 'Eight':8, 'Nine':9, 'Ten':10, 'Jack':10,
         'Queen':10, 'King':10, 'Ace':11}

playing = True

#class for card object
class Card:
    
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        self.value = values[rank]
    
    def __str__(self):
        return f"{self.rank} of {self.suit}"

#class for a deck object
class Deck:
    
    def __init__(self):
        self.deck = []  # start with an empty list
        for suit in suits:
            for rank in ranks:
                new_card = Card(suit, rank)
                self.deck.append(new_card)
    
    def __str__(self):
        content = ''
        for item in self.deck:
            content += item.__str__()+'\n'    
        return content

    #Function to randomly suffle the deck
    def shuffle(self): 
        random.shuffle(self.deck)

    #Function to take the last card of the deck   
    def deal(self):
        return self.deck.pop()

#Class for a Hand object
class Hand:
    def __init__(self):
        self.cards = []  # start with an empty list as we did in the Deck class
        self.value = 0   # start with zero value
        self.aces = 0    # add an attribute to keep track of aces
    
    #Funtion to add a card to the hand
    def add_card(self,card):
        self.cards.append(card)
        if card.rank == 'Ace': #In case the card is an ace we keeo the track
            self.aces += 1
        self.value += card.value #add the card value to the total value of the hand
    
    #Function to adjust the ace value from 11 to 1
    def adjust_for_ace(self):
        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1

#Class for a chip object
class Chips:
    def __init__(self, total):
        self.total = total  # This can be set to a default value or supplied by a user input
        self.bet = 0

    #Funtion for winning a bet   
    def win_bet(self):
        self.total += self.bet
    
    #Function for losing a bet
    def lose_bet(self):
        self.total -= self.bet

#Funtion to take a bet of the player
def take_bet(chips):
    #Check until the bet is a valid input
    while True:
        try:
            current_bet = int(input("Please make a bet: "))
            #Check is the player has enougth chips to cover the bet
            if current_bet > chips.total:
                print("You can cover the bet")
            else:
                print("Bet done!")
                chips.bet = current_bet
                break
        except:
            print("Invalid bet")

#Funtion in case the player want to take another card
def hit(deck,hand):
    card = deck.deal() #Take out a card of the deck
    hand.add_card(card) #Add the card to the player hand
    hand.adjust_for_ace() #Check if we can change the value of the aces

#Function to ask the player if he want to hit or stand
def hit_or_stand(deck,hand):
    global playing  # to control an upcoming while loop
    #Check if the input is valid
    while True: 
        shot = input("Would you like to Hit or Stand? Enter 'h' or 's': ").lower()
        if shot == 'h': #If the player want to hit
            hit(deck, hand)
        elif shot == 's': #Case if the player want to stand
            print("Player stands. Dealer's turn.")
            playing = False
        else:
            print('Invalid input')
            continue
        break

#Show the player hand and hide the first card of the dealer
def show_some(player,dealer):
    print("Dealer Hand")
    #Print all cards except the first of the dealer
    for card in dealer.cards[1:]: 
        print(card, card.value)
    print("Player Hand")
    #Print all the cards of the player
    for card in player.cards:
        print(card, card.value)
    
#Funtion to show all the cards of the player and dealer
def show_all(player,dealer):
    print("Dealer Hand")
    for card in dealer.cards:
        print(card, card.value)
    print("Player Hand")
    for card in player.cards:
        print(card, card.value)

#Function in case the player is busts
def player_busts(chips):
    print("Player bust")
    chips.lose_bet() #Player lose the bet and credits will be discounted

#Function in case the player wins
def player_wins(chips):
    print("Player Wins")
    chips.win_bet() #Player win the bet and credits will be added

#Function in case the dealer is busts
def dealer_busts(chips):
    print("Dealer bust")
    chips.win_bet() #Player win the bet and credits will be added

#Function in case the dealer wins    
def dealer_wins(chips):
    print("Dealer wins")
    chips.lose_bet() #Player lose the bet and credits will be discounted

#Function in case the player and dealer tie    
def push():
    print("It's a tie")

#Funtion to insert the first chips to the player's account
def set_chips():
    #Check if the input is valid
    while True:
        try:
            credit = int(input("Please enter your number of credits: "))
        except:
            print("Invalid input")
        else:
            print("Credits accepted")
            break
    return credit

#Ask the player if he want to play again
def play_again():
    #Wait until a valid input
    while True:
        answer = input("Wanna play again [Y or N]: ").upper()
        if answer not in ['Y', 'N']:
            print("Invalid input. Try again")
        else:
            return answer

# Print an opening statement
print("Welcome to BlackJack game")
credits = set_chips()
# Set up the Player's chips
current_chips = Chips(credits)

while True:
    # Create & shuffle the deck, deal two cards to each player
    deck = Deck()
    deck.shuffle()
    # Prompt the Player for their bet
    take_bet(current_chips)
    #Create the hand for the player and dealer
    player_hand = Hand()
    dealer_hand = Hand()
    for i in range(2): #Give two cards to each player
        player_hand.add_card(deck.deal())
        dealer_hand.add_card(deck.deal())
    # Show cards (but keep one dealer card hidden)
    show_some(player_hand, dealer_hand)
    while playing:  # recall this variable from our hit_or_stand function
        # Prompt for Player to Hit or Stand
        hit_or_stand(deck, player_hand)
        # Show cards (but keep one dealer card hidden)
        show_some(player_hand, dealer_hand)
        # If player's hand exceeds 21, run player_busts() and break out of loop
        if player_hand.value > 21:
            print("Hand value:", player_hand.value)
            player_busts(current_chips)
            break
    # If Player hasn't busted, play Dealer's hand until Dealer reaches 17
    if player_hand.value <= 21:
        while dealer_hand.value <= 17:
            hit(deck, dealer_hand)
        # Show all cards
        show_all(player_hand, dealer_hand)
        print("Hand player value:", player_hand.value)
        print("Hand dealer value:", dealer_hand.value)
        # Run different winning scenarios
        if dealer_hand.value > 21: #Dealer busts
            dealer_busts(current_chips)
        elif player_hand.value > dealer_hand.value: #Player has a greater hand than the dealer
            player_wins(current_chips)
        elif dealer_hand.value > player_hand.value: #Dealer has a greater hand than the player
            dealer_wins(current_chips)
        elif dealer_hand.value == player_hand.value: #A tie
            push()
    # Inform Player of their chips total 
    print("You actual chips are:", current_chips.total)
    # Ask to play again
    answer = play_again()
    if answer == 'N':
        break
    elif current_chips.total == 0: #If the player has no more chips to bet
        print("Not chips available")
        break
    else:
        playing = True
        continue
