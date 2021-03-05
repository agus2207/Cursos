#include <stdio.h>
#include <stdlib.h>

struct _retire_info{
  int months;
  double contribution;
  double rate_of_return;
};
typedef struct _retire_info retire_info;

int age(int months){
  int res = months/12;
  return res;
}

int month(int months){
  int res = months-(12*age(months));
  return res;
}

void retirement(int startAge, double initial, retire_info working, retire_info retired){
  for(int i = startAge+1; i < startAge+1+working.months; i++){
    initial = initial+(initial*(working.rate_of_return/100.0)/12.0)+working.contribution;
    printf("Age %3d month %2d you have $%.2lf\n", age(i), month(i),initial);
  }
  int cont = startAge+1+working.months;
  for(int i = cont; i < cont+retired.months-1; i++){
    initial = initial+(initial*(retired.rate_of_return/100.0)/12.0)+retired.contribution;
    printf("Age %3d month %2d you have $%.2lf\n", age(i), month(i),initial);
  }
}

int main(void){
  retire_info working;
  retire_info retired;
  working.months = 489;
  working.contribution = 1000.0;
  working.rate_of_return = 4.5;
  retired.months = 384;
  retired.contribution = -4000.0;
  retired.rate_of_return = 1.0;
  int startAge = 327;
  double initial = 21345.0;
  printf("Age %3d month %2d you have $%.2lf\n", age(startAge), month(startAge), initial);
  retirement(startAge,initial,working,retired);
  return 0;
}
