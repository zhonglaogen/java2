#include<stdio.h>
#include<stdlib.h>

#define VERTICES 6

void initialise(int parent[], int rank[]){
	int i;
	for (i=0; i<VERTICES; i++) {
		parent[i] = -1;
		rank[i] = 0;
		
	}
}

int find_root(int x, int parent[]){
	int x_root = x;
	while(parent[x_root] != -1){
		x_root = parent[x_root];
	}
	return x_root;
}

/* 1 -union successfully, 0- failed 两个集合在同一个集合*/
int union_vertices(int x, int y, int parent[], int rank[]){
	int x_root = find_root(x,parent);
	int y_root = find_root(y,parent);
	if (x_root == y_root) {
		return 0;
	}else {
/* 这里需要压缩树的高度（logn），否则肯能会形成链表*/
/* 把高树和矮树合并，其中高树作为跟*/
/* 高低压相同，*/
		//parent[x_root] = y_root;
		if(rank[x_root] > rank[y_root]){
			parent[y_root] = x_root;
		}
		else if(rank[y_root] > rank[x_root]){
			parent[x_root] = y_root;
		}
		else {
			parent[x_root] = y_root;
			rank[y_root]++;
		}
		return 1;
	}
}

int main(){
	int parent[VERTICES] = {0};
	int rank[VERTICES] = {0};
	int edges[6][2] = {
		{0,1},{1,2},{1,3},
		{2,4},{3,4},{2,5}
	};
	initialise(parent, rank);
	int i;
	for (i=0; i<6; i++){
		int x =edges[i][0];
		int y =edges[i][1];
		if(union_vertices(x,y,parent,rank) == 0){
			printf("Cycle detected! \n");
			exit(0);			
		}
	}
	printf("No cycles founds\n");
	return 0;
}
