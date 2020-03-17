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

//在字典取出这个字符传的编码
int get_seq_code(Dictionary* dict, char* seq){


    for (int i = 0; i < dict->size; ++i) {
//        字符串比较，等于0表示有
        if(strcmp(dict->seq[i], seq) == 0){
            return dict->code[i];
        }
    }
    return NOT_EXIST;
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


void lzw_encode(char* text, Dictionary* dict){
//    这样定义数组表示数组内容可读可写，char*定义数组表示数组只读（因为只是定义了一个执行字符串数组的指针）效率高
    char current[1000];
    char next;
    int code;
//    探测的位数
    int i = 0;
//    从这个字符串0位置开始编码
    while(i < strlen(text)){
//        把第i个字母放入current数组中
        sprintf(current, "%c", text[i]);
        next = text[i+1];
//      检验编码是否以存在，如果存在就在current里放一位，在检查下一位
        while(i < strlen(text) && get_seq_code(dict, current) != NOT_EXIST){
//            放入current数组
            sprintf(current, "%s%c", current, next);
//            这位存在，所以探测下一位
            i++;
            next = text[i + 1];
        }
//        每次探测成功next都会+1，让next退回到探测失败的下表
        next = text[i];
//        最后一位是不存在的，所以去掉,为了拿到不算这个字母的前几个字母的已有编码

//        i的位置不是结束符的位置
        if (i != strlen(text)){
            current[strlen(current) - 1] = '\0';
        }

        code = get_seq_code(dict, current);

            //       在这里加上是因为要存算上最后一位的新编码
        sprintf(current, "%s%c", current, next);




        insert_seq(dict,current);

        printf("%d %s %c\n",code,current,next);


    }
}

int main(){
//    结构体本身就是一个指针
    Dictionary dict;

    init_dictionary(&dict, 1000);


//    printf("%d",get_seq_code(&dict,"B"));

    lzw_encode("TOBEORNOTTOBEORTOBEORNOT",&dict);
    print_dictionary(&dict);

//    检验最后是不是数组内存溢出，在shell会报错
//    printf("%d\n",strlen(dict.seq[42]));

//    两个字符串相等
    printf("带结束符和不带结束符字符串是否相等(0为相等)：%d\n",strcmp("xx","xx\0"));


  return 0;
}

