#include "stdio.h"
#include "string.h"
void StrRev(char *str, int ci) {
 if (str[ci] == '\0') {
 return;
 } else {
 StrRev(str, ci + 1);
 }
 printf("%c", str[ci]);
}
char OutOneChar(char *str, int ci) {
 OutOneChar(str, ci + 1);
 if (str[ci] == '\0') {
 return '\0';
 } else {
 return str[ci + 1];
 }
}
int main(void) {
 char str[10] = "HElloWW";
 StrRev(str, 0);
}
