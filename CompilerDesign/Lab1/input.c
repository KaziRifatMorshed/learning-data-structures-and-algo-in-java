#include "stdio.h"
#include "string.h"

void StrRev(char *str, int ci) {
  if (str[ci] == '\0')             {
    return;
  } else { // Call first, print later
        StrRev(str, ci + 1);
        /*
        yess
        * no
        ***** very WELL
        */
  }
  printf("%c", str[ci]);
}

char          OutOneChar(char *str, int ci) {
  OutOneChar(str, ci + 1);
  if (str[ci] == '\0') {
    return '\0';
  } else {
    return str[ci + 1];
  }
} // will it work ???I

int main(void) {
  char str[10] = "HElloWW";
  // int len = strlen(str); // দরকার নাই
  StrRev(str, 0);
} // WORKING