package com.examp.travel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Class {@code Object} is .
 *
 * @author MSI
 * @version 1.0
 */
public class C {

    public static void main(String[] args) {
        BigDecimal test = new BigDecimal("1.12345E10");
        System.out.println("普通1： " + test);

        String num = test.toPlainString();
        System.out.println("科学： " + num);

        BigDecimal test2 = new BigDecimal(num);
        System.out.println("普通2： " + test2.stripTrailingZeros());




//        DecimalFormat df=(DecimalFormat) NumberFormat.getInstance();
//        df.setMaximumFractionDigits(2);
//        System.out.println(df.format(12.3456789));
    }

//    public static void main(String[] args) {
//        float s = 0.0f;
//        float a = 2,b = 1,t = 2;
//        for (int i = 1; i <= 20; i++) {
//            s += a/b;
//            t = a+b;System.out.printf("t=" + t);
//            b = a;System.out.printf("b=" + b);
//            a = t;System.out.printf("a=" + a);
//
//            System.out.println("s=" + s);
//        }
//        System.out.println("s = " + s);
//    }

    //            s += t/a;
//            t = a + b;
//            System.out.printf("t=%d\t",t);
//            b = a;
//            System.out.printf("b=%d\t",b);
//            a = t;
//            System.out.printf("a=%d\t",a);
//            System.out.printf("s=%.2f",s);






//    int main()
//    {
//        char stemp[20],name[5][20];
//        int i, j,p;
//        printf("input five names:\n");
//        for(i=0;i<5;i++)
//        {
//            gets(name[i]);	//接收键盘输入的名字，回车键结束
//        }
//        printf("\n");
//        for(i=0;i<5;i++)  //循环五次，第一次找最小的，第二次找第二小的，以此类推排序
//        {
//            p=i;
//            strcpy(stemp,name[i]);	//把name[i]的值复制给stemp
//            for(j=i+1;j<5;j++)
//            {
//                if(strcmp(name[j],stemp)<0)  //比较name[j]和stemp，如果前者排序靠前就返回负数
//                {p=j;strcpy(strmp,name[j]);  } //记录排序靠前的元素下标，复制给strmp
//            }
//            if(p!=i)
//            {
//                strcpy(stemp,name[i]);	//这里及以下两行是交换位置
//                strcpy(name[i],name[p]);
//                strcpy(name[p],stemp);
//            }
//            puts(name[i]);
//        }
//        printf("\n");
//        return 0;
//    }
//
//
//    /**
//     * c语言二维数组行列转换
//     *
//     * 用函数void fun(int *a, int *b, int NX, int NY)；
//     * NX，NY 是行列，列行。NX*NY 等于元素总个数。
//     */
//    void fun(int *a, int *b, int NX, int NY){
//        int i;
//        for (i=0;i<NX*NY;i++) b[i]=a[i];
//    }
//    int main()
//    {
//        int a[2][3]={{1,2,3},{4,5,6}};
//        int b[3][2];
//        int i,j;
//        fun(&a[0][0], &b[0][0], 2, 3);
//        for (j=0;j<3;j++)
//        {
//            for (i=0;i<2;i++)printf("%d ",b[j][i]);
//            printf("\n");
//        }
//        return 0;
//    }
//
//
//    #include<stdio.h>
//
//    void function()
//    {
//        for (i = 0; i <=1; i++)//处理a数组中的一行中各元素
//        {
//            for (j = 0; j <= 2; j++)//处理a数组中的某一列元素
//            {
//                printf("%5d", a[i][j]);
//                b[j][i] = a[i][j];
//            }
//            printf("\n");
//        }
//    }
//
//    int main()
//    {
//        int i, j, a[2][3] = { {1,2,3},{4,5,6} },b[3][2];
//        printf("array b:\n");
//        for (i = 0; i<=2; i++)
//        {
//            for (j = 0; j <=1; j++)
//            {
//                printf("%5d", b[i][j]);
//            }
//            printf("\n");
//        }
//        return 0;
//    }
}



