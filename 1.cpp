#include <stdio.h> 
void move(int *x, int n, int m) 
{ 
int i,j,t; 
for(i=0;i<m;i++){
t=*x; 
for(j=0;j<n-1;j++) {
	*(x+j)=*(x+j+1);}
*(x+j)=t; 
} 
}
int main() 
{ 
int m,n,i,a[10]; 
printf("Enter n and m£º"); 
scanf("%d%d",&n,&m); 
printf("Enter %d integers£º",n); 
for(i=0;i<n;i++)
scanf("%d",&a[i]); 
move(a,n,m);
for(i=0;i<n;i++) 
printf("%-6d",a[i]); 
printf("\n"); 
return 0; 
}