#include <stdio.h>
#include <stdlib.h>

size_t maxSeq(int * array, size_t n);

int main(void){
  int test1[] = {1,2,1,3,5,7,2,4,6,9};
  int test2[] = {-5,-4,-3,-2,-1,0,1};
  int test3[] = {1,0};
  int test4[] = {10};
  int test5[] = {};
  int test6[] = {0,0,0,0,0};
  int test7[] = {0,-1,-2,-3,-4,-5,-6,-7,-8,-9};

  if(maxSeq(test1, 10) != 4){
    exit(EXIT_FAILURE);
  }
  else if(maxSeq(test2, 7) != 7){
    exit(EXIT_FAILURE);
  }
  else if(maxSeq(test3, 2) != 1){
    exit(EXIT_FAILURE);
  }
  else if(maxSeq(test4, 1) != 1){
    exit(EXIT_FAILURE);
  }
  else if(maxSeq(test5, 0) != 0){
    exit(EXIT_FAILURE);
  }
  else if(maxSeq(test6, 5) != 1){
    exit(EXIT_FAILURE);
  }
  else if(maxSeq(test7, 10) != 1){
    exit(EXIT_FAILURE);
  }
  else{
    printf("All tests passed\n");
    exit(EXIT_SUCCESS);
  }
}

