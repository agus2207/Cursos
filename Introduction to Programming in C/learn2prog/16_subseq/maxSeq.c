#include <stdio.h>
#include <stdlib.h>

size_t maxSeq(int * array, size_t n){
  size_t count = 1;
  size_t max_count = 1;
  if(n == 0 || n == 1){
    return n;
  }
  for(int i = 0; i < n-1; i++){
    if(array[i+1] > array[i]){
      count++;
      if(count >= max_count){
	max_count = count;
      }
    }
    else{
      count = 1;
    }
  }
  return max_count;
}
