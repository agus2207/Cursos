#include "input.h"
#include <string.h>

int checkEntry(char v, char s, char r){
  int val = 0, su = 0;
  for(int i = 2; i <= 9; i++){
    if(v == '0'+i){
      val++;
    }
  }
  if(v == '0'){
    val++;
  }
  if(v == 'J'){
    val++;
  }
  if(v == 'Q'){
    val++;
  }
  if(v == 'K'){
    val++;
  }
  if(v == 'A'){
    val++;
  }
  if(v == '?'){
    val = 5;
  }
  if(s == 's'){
    su++;
  }
  if(s == 'h'){
    su++;
  }
  if(s == 'd'){
    su++;
  }
  if(s == 'c'){
    su++;
  }
  if((val == 1) && (su == 1)){
    return 100;
  }
  else if((val == 5) && (su == 0)){
    for(int i = 0; i <= 9; i++){
      if(s == '0'+i){
	for(int j = 0; j <= 9; j++){
	  if(r == '0'+j){
	    return 10*i + j;
	  }
	}
	return i;
      }
    }
  }
  return -1;
}

deck_t * hand_from_string(const char * str, future_cards_t * fc){
  if((str == NULL) || (strlen(str) == 0)){
    perror("There is no characters in the line\n");
    exit(EXIT_FAILURE);
  }
  deck_t * deck = calloc(1, sizeof(*deck));
  deck -> n_cards = 0;
  deck -> cards = NULL;
  int i = 0, count = 0;
  while((str[i] != '\n') && (str[i] != '\0')){
    while(str[i] == ' '){
      i++;
    }
    if(i+1 >= strlen(str)){
      break;
    }
    if(i+2 < strlen(str)){
      if(checkEntry(str[i], str[i+1], '+') == 100){
	card_t c = card_from_letters(str[i], str[i+1]);
	add_card_to(deck, c);
	count++;
	i+=2;
      }
      else if((checkEntry(str[i], str[i+1], str[i+2]) >= 0) && (checkEntry(str[i], str[i+1], str[i+2]) <= 9)){
	card_t * card = add_empty_card(deck);
	add_future_card(fc, checkEntry(str[i], str[i+1], '+'), card);
	i+=2;
	count++;
      }
      else if(checkEntry(str[i], str[i+1], str[i+2]) >= 0){
	card_t * card = add_empty_card(deck);
	add_future_card(fc, checkEntry(str[i], str[i+1], str[i+2]), card);
	i+=3;
	count++;
      }
      else{
	perror("Wrong cards\n");
	exit(EXIT_FAILURE);
      }
    }
    else{
      if(checkEntry(str[i], str[i+1], '+') == 100){
	card_t c = card_from_letters(str[i], str[i+1]);
	add_card_to(deck, c);
	count++;
      }
      else if(checkEntry(str[i], str[i+1], '+') >= 0){
	card_t * card = add_empty_card(deck);
	add_future_card(fc, checkEntry(str[i], str[i+1], '+'), card);
	count++;
      }
      else{
	perror("Wrong cards\n");
	exit(EXIT_FAILURE);
      }
      i+=2;
    }
    if(i >= strlen(str)){
      break;
    }
  }
  if(count < 5){
    perror("This line has less than 5 cards\n");
    exit(EXIT_FAILURE);
  }
  return deck;
}

deck_t ** read_input(FILE * f, size_t * n_hands, future_cards_t *fc){
  if(f == NULL){
    perror("The file is not open\n");
    exit(EXIT_FAILURE);
  }
  deck_t ** deckArray = NULL;
  deckArray = calloc(1, sizeof(*deckArray));
  char * line = NULL;
  size_t x = 0;
  *(n_hands) = 0;
  while(getline(&line, &x, f) >= 0){
    deckArray = realloc(deckArray, (*n_hands + 1)*sizeof(*deckArray));
    deckArray[*n_hands] = hand_from_string(line, fc);
    *n_hands+=1;
  }
  free(line);
  return deckArray;
}
