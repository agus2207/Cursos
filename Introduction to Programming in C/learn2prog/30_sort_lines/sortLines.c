#include <stdio.h>
#include <stdlib.h>
#include <string.h>


//This function is used to figure out the ordering
//of the strings in qsort.  You do not need
//to modify it.
int stringOrder(const void * vp1, const void * vp2) {
  const char * const * p1 = vp1;
  const char * const * p2 = vp2;
  return strcmp(*p1, *p2);
}
//This function will sort and print data (whose length is count).
void sortData(char ** data, size_t count) {
  qsort(data, count, sizeof(char *), stringOrder);
}

int main(int argc, char ** argv) {
  
  //WRITE YOUR CODE HERE!
  if(argc == 1){
    char ** lines = NULL;
    char * curr = NULL;
    size_t sz = 0;
    size_t row = 0;
    while(getline(&curr, &sz, stdin) >= 0){
      lines = realloc(lines, (row+1) * sizeof(*lines));
      lines[row] = curr;
      curr = NULL;
      row++;
    }
    free(curr);
    sortData(lines, row);
    for(size_t i = 0; i < row; i++){
      printf("%s", lines[i]);
      free(lines[i]);
    }
    free(lines);
  }
  if(argc > 1){
    for(size_t i = 0; i < argc - 1; i++){
      char ** lines = NULL;
      char * curr = NULL;
      size_t sz = 0;
      size_t row = 0;
      FILE * f = fopen(argv[i+1], "r");
      if(f == NULL){
	perror("Cannot open the file\n");
	return EXIT_FAILURE;
      }
      while(getline(&curr, &sz, f) >= 0){
	lines = realloc(lines, (row+1) * sizeof(*lines));
	lines[row] = curr;
	curr = NULL;
	row++;
      }
      free(curr);
      sortData(lines, row);
      for(size_t i = 0; i < row; i++){
	printf("%s", lines[i]);
	free(lines[i]);
      }
      free(lines);
      fclose(f);
    }
  }
  return EXIT_SUCCESS;
}
