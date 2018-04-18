/*
how to execute this code


1) go to that folder in which the program is stored using cd folder_name

2) open terminal ("ctrl+alt+t")

3) type the folloing commands to run this program
    "g++ quick.cpp -lpthread"
    and hit enter
    "./a.out"
    and hit enter

follow the instructions

i/p: demo.xml
<? xml version="1.0" ?>
<QuickSortInput>
<Numbers>
<Number>
50
</Number>
<Number>
86
</Number>
<Number>
12
</Number>
<Number>
117
</Number>
<Number>
69
</Number>
<Number>
3
</Number>
<Number>
36
</Number>
<Number>
20
</Number>
<Number>
105
</Number>
<Number>
81
</Number>
</Numbers>
</QuickSortInput>

*/

#include <iostream> 
#include <string> 
#include <fstream> // to take input file as we have taken .xml file as input
#include <stdlib.h> 
#include<sched.h>  // it contains the scheduling parameters required for implementation of each supported scheduling policy
#include<vector> // as we are using vector arrays in this code so we need this header file as well
#include<pthread.h> // for threading and making and joining thread we need this header file 
using namespace std; 

int n;  // to no of numbers stored in xml files
struct args 
{ 
    int *A; 
    int f,l; 
}; 

class Quick    // class for making a model(frame)
{ 
    public: 
        void Print(int A[],int n); 
        void swap(int &a,int &b); 
        int Pivote(int A[],int f,int l); 
}; 

void Quick::Print(int A[],int n) //printing the array 
{ 
    for(int i=0;i<n;i++) 
        cout<<A[i]<<endl; 
} 

void Quick::swap(int &a,int &b) //swap the elements 
{ 
    int temp=a; 
    a=b; 
    b=temp; 
} 

int Quick::Pivote(int A[],int f,int l) // to calculate pivote value 
{ 
    int p=f; 
    int pElement=A[f];    // first value is taken as pivote value
    for(int i=f+1;i<=l;i++) 
    { 
        if(A[i]<=pElement)      // elements smaller then pivote are placed first in the array
        { 
            p++; 
            swap(A[i],A[p]);  // here swapping is done for first half array 
        } 
    } 
    swap(A[p],A[f]); // here swapping is done for pivote value
    return p;          // return the pivote value
} 

void* QuickSort(void* Arg)  // main quick_sort logic
{ 
    Quick Q;    // object is created of class Quick 
    pthread_t id=pthread_self();   // The pthread_self() function returns the ID of the thread in which it is invoked, which have a data type as pthread_t.
    pthread_t thread[2];           // array of thread is created to store threads, which have a data type of phthread_t .
    args *Ar=(args*)Arg;           // structure args is call to set a structure of array.
    int piv; 
    if(Ar->f < Ar->l)              // if first element is less then last then 
    { 
        piv=Q.Pivote(Ar->A,Ar->f,Ar->l);   // pivote element is computed by Pivote function and stored in piv
        
        cout<<"thread: "<<id<<" core:"<<sched_getcpu()<<" pivote:"<<Ar->A[piv]<<endl; 
     
        args A1;   // 1st thread which have a structure of args
        A1.A=new int(n); 
        A1.A=Ar->A; 
        A1.f=Ar->f; 
        A1.l=piv-1; 
        args *X=&A1;  // creating a structure of args(like thread) for 1st thread 
        int success=pthread_create(&thread[0],NULL,&QuickSort,(void*)X); 
        /*
        int pthread_create(pthread_t *thread, const pthread_attr_t *attr, void *(*start)(void *), void *arg);
        
        thread - Is the location where the ID of the newly created thread should be stored, or NULL if the thread ID is not required.
        attr -Is the thread attribute object specifying the attributes for the thread that is being created. If attr is NULL, the thread is created with default attributes.
        start - Is the main function for the thread; the thread begins executing user code at this address.
        arg  - Is the argument passed to start.
        */
        
        args A2;    // 2nd thread which have a structure of args
        A2.A=new int(n); 
        A2.A=Ar->A; 
        A2.l=Ar->l; 
        A2.f=piv+1; 
        args *Y=&A2;  // creating a structure of args(like thread) for 2nd thread 
        int success1=pthread_create(&thread[1],NULL,&QuickSort,(void*)Y); 
        
        pthread_join(thread[0],NULL);  // The pthread_join() function shall suspend execution of the calling thread until the target thread terminates,
        pthread_join(thread[1],NULL);  // or in simple language to join this threads to the main process join is used.
    } 
} 

int main() 
{ 
    Quick Q; 
    int N=0,n=0; 
    vector <int> arr;   //  vector array is declared here
    string line; 
    ifstream in("demo.xml");  // to take input from the demo.xml file and store it in 'in'. it is "input file stream-ifstream"
    bool begin_tag = false; 
    bool begin_tag1 = false; 
    string tmp; // strip whitespaces from the beginning 
    
    while (getline(in,line))  //to store .xml file's data into an array
    { 
        tmp=""; 
        for (int i = 0; i < line.length(); i++) // for removing blank spaces from each line in .xml file 
        { 
            if (line[i] == ' ' && tmp.size() == 0) 
            { 
                    // do nothing
            } 
            else 
            { 
                tmp += line[i]; 
            } 
        } 
        
        if (tmp == "<Number>")   // matching with <Number> tag 
        { 
            begin_tag1 = true; 
            continue; 
        } 
        else if (tmp == "</Number>")    // matching with </Number> tag  
        { 
            begin_tag1 = false; 
        } 
        
        if (begin_tag1) 
        { 
            n++; 
            N=atoi(tmp.c_str()); // to take only interger from the file atoi() funvtion is used. and will scan the line till c_str() function means till this '\0' means "end of line"
            arr.push_back(N);  // add this Number taken from .xml file to vector array and push_back() Function to this because we have to add the new number at the end of the array.
            //cout<<n<<" "<<N<<endl; 
        } 
    } 
    
    int Arr[n]; 
    for ( int i = 0; i < arr.size(); i++) 
    { 
        //cout << arr[i] << "\n"; 
        Arr[i]=arr[i];      // assigning this array to new array Arr.
    } 
    cout<<"\nBefore Sorting: \n"; 
    Q.Print(Arr,n);  // printing unsorted array
    args a; 
    a.A=new int[n]; // setting array to the args structure 
    a.A=Arr; 
    a.f=0; 
    a.l=n-1; 
    QuickSort(&a); // performing Quicksort on array a
    cout<<endl; 
    cout<<"\nAfter Sorting: \n"; 
    Q.Print(Arr,n); // printing unsorted array
    return 0; 
} 

/*
output:

tony@tony:~/CL3_Working/A2_quick_sort/C++$ g++ quick.cpp -lpthread
tony@tony:~/CL3_Working/A2_quick_sort/C++$ ./a.out

Before Sorting: 
50
86
12
117
69
3
36
20
105
81
thread: 140381675185984 core:3 pivote:50
thread: 140381657839360 core:0 pivote:20
thread: 140381649446656 core:2 pivote:86
thread: 140381641053952 core:1 pivote:3
thread: 140381542938368 core:0 pivote:105
thread: 140381624268544 core:0 pivote:81


After Sorting: 
3
12
20
36
50
69
81
86
105
117
tony@tony:~/CL3_Working/A2_quick_sort/C++$ 

*/

/*
#include<iostream>

#include<omp.h>

using namespace std;

int k=0;

class sort

{

int a[20];

int n;

public:

void getdata();

void Quicksort();

void Quicksort(int low, int high);

int partition(int low, int high);

void putdata();

};

void sort::getdata()

{

cout<<"Enter the no. of elements in array\t";

cin>>n;

cout<<"Enter the elements of array:"<<endl;

for(int i=0;i<n;i++)

{

cin>>a[i];

}

}

void sort::Quicksort()

{

Quicksort(0,n-1);

}


void sort::Quicksort(int low, int high)

{
omp_set_num_threads(3);

if(low<high)
{

int partn;

partn=partition(low,high);


//cout<<"Thread Number: "<<k<<" pivot element selected : "<<a[partn]; 

cout<<"\n\t\tThread id : "<<omp_get_thread_num()<<" pivot element selected : "<<a[partn];;

#pragma omp parallel sections
{

#pragma omp section

{

//k=k+1;
cout<<"\n\t\tThread idL : "<<omp_get_thread_num();
Quicksort(low, partn-1);

}

#pragma omp section 

{

//k=k+1;
cout<<"\n\t\tThread idR : "<<omp_get_thread_num();
Quicksort(partn+1, high);

}

}//pragma_omp Parallel_end

}


}

int sort::partition(int low ,int high)

{

int pvt;

pvt=a[high];

int i;

i=low-1;

int j;

for(j=low;j<high;j++)

{

if(a[j]<=pvt)

{

int tem=0;

tem=a[j];
a[j]=a[i+1];

a[i+1]=tem;

i=i+1;

}

}

int te;

te=a[high];

a[high]=a[i+1];

a[i+1]=te;

return i+1;

}

void sort::putdata()

{

cout<<endl<<"\nThe Array is:"<<endl;

for(int i=0;i<n;i++)

cout<<" "<<a[i];

}

int main()

{

int n;

sort s1;

int ch;

do

{

s1.getdata();

s1.putdata();


cout<<"\nUsing Quick Sort";

double start = omp_get_wtime();

s1.Quicksort();

double end = omp_get_wtime();

cout<<"\nThe Sorted  ";

s1.putdata();

cout<<"\nExcecution time : "<<end - start<<" seconds ";
cout<<"Would you like to continue? (1/0 y/n)"<<endl;

cin>>ch;

}while(ch==1);

return 0;
}

// run : g++ -fopenmp cqs.cpp 
//./a.out

*/

