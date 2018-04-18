#include"iostream"
#include"stdlib.h"
using namespace std;
class BinarySearch
{
public:
 int search(int *arr,  int upper,int lower, const int s)
 {
  upper = upper - 1;
  int middle = (lower + upper) / 2;
  while (lower <= upper)
  {
   if (arr[middle] == s)
    return middle;
   else if (arr[middle] > s)
    upper = middle - 1;      // change upper position
   else
    lower = middle + 1;   // change lower position

   middle = (lower + upper) / 2;  //new middle element's position
  // search(arr, upper, lower, s);
  }
  return -1;
 }
 void bubblesort(int *arr, const int size)
 {
  for (int i = 0; i < size; i++)
  {
   for (int j = 0; j < size-1; j++)
   {
    if (arr[j]>arr[j + 1])
    {
     int temp;
     temp = arr[j];
     arr[j] = arr[j + 1];
     arr[j + 1] = temp;

    }
   }
  }
 }
}b;
int main()
{
 cout << "\n\n_ _ _ _ _ _ _ _ _ _ _ Binary Search _ _ _ _ _ _ _ _ _ _ _";
 int len, s, a = 1;
 cout << "\n\nEnter size of an array: ";
 cin >> len;
 int *arr=new int[len];
 cout << "\nEnter elements: ";
 for (int i = 0; i < len; i++)
  cin >> arr[i];
 b.bubblesort(arr, len);
 cout << "\nSorted array: [ ";
 for (int i = 0; i < len; i++)
  cout << arr[i] << " ";
 cout << "]";
 while (a != 0) {
  cout << "\n\nEnter element to be searched: ";
  cin >> s;
  int result = b.search(arr, len,0, s);
  if (result != -1)
   cout << "\n\n" << s << " successfully found in the array at index " << result << ".\n\n";
  else
   cout << "\n\n Element " << s << " is not found in the given array.\n\n";

  cout << "\n Do you want to continue(1/0)?";
  cin >> a;
 }
 return 0;
}
