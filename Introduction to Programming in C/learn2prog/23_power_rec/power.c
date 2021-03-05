#include <stdio.h>
#include <stdlib.h>

unsigned power(unsigned x, unsigned y){
  if(x < 0 || y < 0){
    printf("Entry must be greater than 0");
    exit(EXIT_FAILURE);
  }
  else if(y == 0){
    return 1;
  }
  else if(y == 1){
    return x;
  }
  else{
    return power(x, y-1)*x;
  }
}
