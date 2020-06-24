package com.cquptkiller.a_guarder.utils;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;

/**
 * Created by yao on 2018/12/22.
 */

public class GetModel {
    /**
     * 通过输入返回未知样本的预测类型
     * @param PemNum 应用声明的权限数量
     * @param InterNet 是否有联网权限 有则传入1，无则传入0，以下同
     * @param TextMsg  是否有发短信的权限
     * @param Tel      是否有拨打电话的权限
     * @param Location 是否有获取位置信息的权限
     * @param Contacts 是否有读取联系人信息的权限
     * @return 未知点
     */
    public svm_node[] getTestData(int PemNum,int InterNet, int TextMsg,int Tel, int Location, int Contacts){
        //定义未知点
        svm_node pa0 = new svm_node();
        pa0.index = 0;
        pa0.value = PemNum;
        svm_node pa1 = new svm_node();
        pa1.index = 1;
        pa1.value = InterNet;
        svm_node pa2 = new svm_node();
        pa2.index = 2;
        pa2.value = TextMsg;
        svm_node pa3 = new svm_node();
        pa3.index = 3;
        pa3.value = Tel;
        svm_node pa4 = new svm_node();
        pa4.index = 4;
        pa4.value = Location;
        svm_node pa5 = new svm_node();
        pa5.index = -1;
        pa5.value = Contacts;
        svm_node[] pa = {pa0, pa1, pa2, pa3, pa4, pa5};
        return pa;
    }



    public svm_model main() {
        //定义训练集点
        //正常点pa
        svm_node pa0 = new svm_node();
        pa0.index = 0;
        pa0.value = 30;
        svm_node pa1 = new svm_node();
        pa1.index = 1;
        pa1.value = 1;
        svm_node pa2 = new svm_node();
        pa2.index = 2;
        pa2.value = 1;
        svm_node pa3 = new svm_node();
        pa3.index = 3;
        pa3.value = 1;
        svm_node pa4 = new svm_node();
        pa4.index = 4;
        pa4.value = 1;
        svm_node pa5 = new svm_node();
        pa5.index = -1;
        pa5.value = 1;
        svm_node[] pa = {pa0, pa1, pa2, pa3, pa4, pa5};

        //正常点pb
        svm_node pb0 = new svm_node();
        pb0.index = 0;
        pb0.value = 120;
        svm_node pb1 = new svm_node();
        pb1.index = 1;
        pb1.value = 1;
        svm_node pb2 = new svm_node();
        pb1.index = 2;
        pb2.value = 1;
        svm_node pb3 = new svm_node();
        pb3.index = 3;
        pb3.value = 1;
        svm_node pb4 = new svm_node();
        pb4.index = 4;
        pb4.value = 1;
        svm_node pb5 = new svm_node();
        pb5.index = 5;
        pb5.value = 1;
        svm_node[] pb = {pb0, pb1, pb2, pb3, pb4, pb5};

        //正常点pd
        svm_node pd0 = new svm_node();
        pd0.index = 0;
        pd0.value = 35;
        svm_node pd1 = new svm_node();
        pd1.index = 1;
        pd1.value = 1;
        svm_node pd2 = new svm_node();
        pd1.index = 2;
        pd2.value = 1;
        svm_node pd3 = new svm_node();
        pd3.index = 3;
        pd3.value = 1;
        svm_node pd4 = new svm_node();
        pd4.index = 4;
        pd4.value = 1;
        svm_node pd5 = new svm_node();
        pd5.index = 5;
        pd5.value = 1;
        svm_node[] pd = {pd0, pd1, pd2, pd3, pd4, pd5};

        //定义恶意点pe
        svm_node pe0 = new svm_node();
        pe0.index = 0;
        pe0.value = 120;
        svm_node pe1 = new svm_node();
        pe1.index = 1;
        pe1.value = 1;
        svm_node pe2 = new svm_node();
        pe1.index = 2;
        pe2.value = 0;
        svm_node pe3 = new svm_node();
        pe3.index = 3;
        pe3.value = 0;
        svm_node pe4 = new svm_node();
        pe4.index = 4;
        pe4.value = 1;
        svm_node pe5 = new svm_node();
        pe5.index = 5;
        pe5.value = 1;
        svm_node[] pe = {pe0, pe1, pe2, pe3, pe4, pe5};

        //恶意点pf
        svm_node pf0 = new svm_node();
        pf0.index = 0;
        pf0.value = 150;
        svm_node pf1 = new svm_node();
        pf1.index = 1;
        pf1.value = 1;
        svm_node pf2 = new svm_node();
        pf1.index = 2;
        pf2.value = 1;
        svm_node pf3 = new svm_node();
        pf3.index = 3;
        pf3.value = 0;
        svm_node pf4 = new svm_node();
        pf4.index = 4;
        pf4.value = 0;
        svm_node pf5 = new svm_node();
        pf5.index = 5;
        pf5.value = 0;
        svm_node[] pf = {pf0, pf1, pf2, pf3, pf4, pf5};

        //恶意点pg
        svm_node pg0 = new svm_node();
        pg0.index = 0;
        pg0.value = 250;
        svm_node pg1 = new svm_node();
        pg1.index = 1;
        pg1.value = 0;
        svm_node pg2 = new svm_node();
        pg1.index = 2;
        pg2.value = 0;
        svm_node pg3 = new svm_node();
        pg3.index = 3;
        pg3.value = 0;
        svm_node pg4 = new svm_node();
        pg4.index = 4;
        pg4.value = 1;
        svm_node pg5 = new svm_node();
        pg5.index = 5;
        pg5.value = 1;
        svm_node[] pg = {pg0, pg1, pg2, pg3, pg4, pg5};

        //恶意点ph
        svm_node ph0 = new svm_node();
        ph0.index = 0;
        ph0.value = 120;
        svm_node ph1 = new svm_node();
        ph1.index = 1;
        ph1.value = 0;
        svm_node ph2 = new svm_node();
        ph1.index = 2;
        ph2.value = 0;
        svm_node ph3 = new svm_node();
        ph3.index = 3;
        ph3.value = 1;
        svm_node ph4 = new svm_node();
        ph4.index = 4;
        ph4.value = 1;
        svm_node ph5 = new svm_node();
        ph5.index = 5;
        ph5.value = 0;
        svm_node[] ph = {ph0, ph1, ph2, ph3, ph4, ph5};
        svm_node[][] datas = {pa, pb, pd, pe, pf, pg, ph}; //训练集的向量表
        double[] lables = {1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0}; //a,b 对应的lable
        //定义svm_problem对象
        svm_problem problem = new svm_problem();
        problem.l = 7; //向量个数
        problem.x = datas; //训练集向量表
        problem.y = lables; //对应的lable数组

        //定义svm_parameter对象
        svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.LINEAR;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1;




        //训练SVM分类模型
        System.out.println(svm.svm_check_parameter(problem, param)); //如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。
        svm_model model = svm.svm_train(problem, param);
        return model;
    }
}
