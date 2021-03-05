#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include "cards.h"


void assert_card_valid(card_t c) {
  unsigned v = c.value;
  suit_t s = c.suit;
  assert(v>=2 && v<=14);
  assert(s >= 0 && s <= 3);
}

const char * ranking_to_string(hand_ranking_t r) {
  switch(r){
    case 0: return "STRAIGHT_FLUSH";break;
    case 1: return "FOUR_OF_A_KIND";break;
    case 2: return "FULL_HOUSE";break;
    case 3: return "FLUSH"; break;
    case 4: return "STRAIGHT"; break;
    case 5: return "THREE_OF_A_KIND"; break;
    case 6: return "TWO_PAIR"; break;
    case 7: return "PAIR"; break;
    case 8: return "NOTHING"; break;
    default: return "ERROR"; break;
  }
}

char value_letter(card_t c) {
  unsigned v = c.value;
  char r = 'x';
  if(v >= 2 && v <=9){
    r = '0'+v;
  }
  else{
    if(v == 10){
      r = '0';
    }
    else if(v == 11){
      r =  'J';
    }
    else if(v == 12){
      r = 'Q';
    }
    else if(v == 13){
      r = 'K';
    }
    else if(v == 14){
      r = 'A';
    }
  }
  return r;
}


char suit_letter(card_t c) {
  suit_t s = c.suit;
  char r = 'x';
  if(s == 0){
    r = 's';
  }
  else if(s == 1){
    r = 'h';
  }
  else if(s == 2){
    r = 'd';
  }
  else if(s == 3){
    r = 'c';
  }
  return r;
}

void print_card(card_t c) {
  char rv = value_letter(c);
  char rs = suit_letter(c);
  printf("%c%c",rv,rs);
  /*if(rs == 's'){
    switch(rv){
    case '0': printf("%c%c  (for 10 of spades)",rv,rs);break;
    case 'J': printf("%c%c  (for Jack  of spades)",rv,rs);break;
    case 'Q': printf("%c%c  (for Queen of spades)",rv,rs);break;
    case 'K': printf("%c%c  (for King of spades)",rv,rs);break;
    case 'A': printf("%c%c  (for Ace of spades)",rv,rs);break;
    default: printf("%c%c  (for %c of spades)",rv,rs,rv);break;
    }
  }
  else if(rs == 'h'){
    switch(rv){
    case '0': printf("%c%c  (for 10 of hearts)",rv,rs);break;
    case 'J': printf("%c%c  (for Jack  of hearts)",rv,rs);break;
    case 'Q': printf("%c%c  (for Queen of hearts)",rv,rs);break;
    case 'K': printf("%c%c  (for King of hearts)",rv,rs);break;
    case 'A': printf("%c%c  (for Ace of hearts)",rv,rs);break;
    default: printf("%c%c  (for %c of hearts)",rv,rs,rv);break;
    }
  }
  else if(rs == 'd'){
    switch(rv){
    case '0': printf("%c%c  (for 10 of diamonds)",rv,rs);break;
    case 'J': printf("%c%c  (for Jack  of diamonds)",rv,rs);break;
    case 'Q': printf("%c%c  (for Queen of diamonds)",rv,rs);break;
    case 'K': printf("%c%c  (for King of diamonds)",rv,rs);break;
    case 'A': printf("%c%c  (for Ace of diamonds)",rv,rs);break;
    default: printf("%c%c  (for %c of diamonds)",rv,rs,rv);break;
    }
  }
  else if(rs == 'c'){
    switch(rv){
    case '0': printf("%c%c  (for 10 of clubs)",rv,rs);break;
    case 'J': printf("%c%c  (for Jack  of clubs)",rv,rs);break;
    case 'Q': printf("%c%c  (for Queen of clubs)",rv,rs);break;
    case 'K': printf("%c%c  (for King of clubs)",rv,rs);break;
    case 'A': printf("%c%c  (for Ace of clubs)",rv,rs);break;
    default: printf("%c%c  (for %c of clubs)",rv,rs,rv);break;
    }
    }*/
}

card_t card_from_letters(char value_let, char suit_let) {
  card_t temp;
  assert((value_let>='2'&&value_let<='9')||value_let=='0'||value_let=='J'||value_let=='Q'||value_let=='K'||value_let=='A');
  assert(suit_let=='s'||suit_let=='h'||suit_let=='d'||suit_let=='c');
  if(value_let>='2'&&value_let<='9'){
    temp.value = value_let-'0';
  }
  else if(value_let == '0'){
    temp.value = 10;
  }
  else if(value_let == 'J'){
    temp.value = 11;
  }
  else if(value_let == 'Q'){
    temp.value = 12;
  }
  else if(value_let == 'K'){
    temp.value = 13;
  }
  else if(value_let == 'A'){
    temp.value = 14;
  }
  if(suit_let == 's'){
    temp.suit = 0;
  }
  else if(suit_let == 'h'){
    temp.suit = 1;
  }
  else if(suit_let == 'd'){
    temp.suit = 2;
  }
  else if(suit_let == 'c'){
    temp.suit = 3;
  }
  return temp;
}

card_t card_from_num(unsigned c) {
  card_t temp;
  temp.value = (c%13)+2;
  temp.suit = c/13;
  return temp;
}
