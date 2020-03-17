//
// Created by zhonglianxi on 20-3-17.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//声明字符指针并赋值字符串：char *s1 = "abc"其实实际物理存储模式和字符数组是一样的，一个地址存一个字符，但是有两个和字符数组完全不同的地方。
//1、赋值后不可修改数据
//2、移动指针输出字符时，是从当前指针位置开始输出，到‘\0’停止

/**
 *
 * 以一维数组为例

int arr[10];

int arr[] = {1,2,4};

int arr[10] = {1,2};

int *arr  = new int[10];

前面三种常常都可以用到，但使用第四种时需要注意。在.c文件里使用这种形式编译会不能通过，　但.cpp文件里是可以的，可以推断出这是C++对C的扩展。


 //    char seq[2] = "A";
//    结果是1
    printf("%d\n",strlen(seq));
//    sizeof 是2 所以每个字符串末尾都有一个结束符
//    printf("%d", sizeof(seq));
 */

/**
 *
char *p = "hello world";

char s[] = "hello world";
p[i]赋值导致程序崩溃；

原因：

p指向的为"hello world"的首地址，“hello world”是只读数据区的常量，类似于用const修饰的常量，p[i]是对只读数据区的写，因此导致程序崩溃；
 */


#define NOT_EXIST -1


typedef struct {
//    字典的字母,二维数组
    char** seq;
//    字典的编码，一维数组
    int*   code;
//    字典的大小
    int size;
    int max_size;

} Dictionary;


//插入,引用传递，*表示把那个结构体的地址传过来复制给这个参数，在这里实际操作结构体都是操作结构体指针,就相当于操作结构体本身
//这里相当于直接通过结构体的地址操作机构提

void insert_seq(Dictionary* dict, char* seq){

    int i = dict->size;
//    !!!!!求字符串长度，不包括结束符号\0  sizeof求对象大小也可以求数组长度
    dict->seq[i] = malloc(sizeof(char) * strlen(seq) + 1);
    dict->code[i] = i;
    dict->size++;
//    !!!!!字符串拷贝
    strcpy(dict->seq[i],seq);
}


void init_dictionary(Dictionary* dict, int max_size){

    dict->max_size = max_size;
    dict->size = 0;
    dict->seq = malloc(sizeof(char*) * max_size);
    dict->code = malloc(sizeof(int) * max_size);

//    防止0号位置是A
    insert_seq(dict,"#");
    char seq[2] = "A";
    for(int i = 0; i<26; i++){
        insert_seq(dict,seq);
        seq[0]++;
    }
}




//这个是通过编码找字符
char* get_code_seq(Dictionary* dict, int code){

    if(code < 0 || code > dict->size){
        return NULL;
    } else {
        int i = code;
        return dict->seq[i];
    }
}

//打印
void print_dictionary(Dictionary* dict){
    printf("===================\n");
    printf(" Code       Sequence\n");

    printf("===================\n");

    for (int i = 0; i < dict->size; ++i) {
        printf("%5d%7c",dict->code[i], ' ');
        printf("%s\n",dict->seq[i]);

    }
    printf("===================\n");

}


void lzw_decode(int codes[], int n, Dictionary* dict){
    int code;
//    pre只能是保存out的值，间接获得，不能直接拿到
    char pre[1000];
//    output可以直接拿，可读就行
    char* output;

//    第一个元素不需要放入字典直接拿
    code = codes[0];
    output = get_code_seq(dict, code);

    printf("%s", output);

    int i;
    for (int i = 1; i < n; ++i) {
//        ！！！！！！！！！！复制数组
        strcpy(pre,output);
        output = get_code_seq(dict,codes[i]);
        sprintf(pre, "%s%c", pre, output[0]);
        insert_seq(dict,pre);

        printf("%s",output);
    }


}



int main(){
//    结构体本身就是一个指针
    Dictionary dict;

    init_dictionary(&dict, 1000);

//    print_dictionary(&dict);

    int arr[16] = {20,15,2,5,15,18,14,15,20,27,29,31,36,30,32,34};

    lzw_decode(arr,16,&dict);




    return 0;
}



