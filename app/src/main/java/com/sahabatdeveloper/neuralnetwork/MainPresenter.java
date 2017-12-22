package com.sahabatdeveloper.neuralnetwork;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sahabat Developer on 21/12/2017.
 */

public class MainPresenter {
    MainView mainView;
    Context context;
    List<Model> listData = new ArrayList<>();
    float wi,w1,w2, wiBaru,w1Baru,w2Baru, li,l1,l2;
    int posit=0;

    public MainPresenter(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
    }

    public void tampilData(int position){
        posit=0;
        listData.clear();
        if(position==0){
            listData.add(new Model(0,0,0));
            listData.add(new Model(0,1,0));
            listData.add(new Model(1,0,0));
            listData.add(new Model(1,1,1));
        }else if(position==1){
            listData.add(new Model(0,0,0));
            listData.add(new Model(0,1,1));
            listData.add(new Model(1,0,1));
            listData.add(new Model(1,1,1));
        }else if(position==2){
            listData.add(new Model(0,0,0));
            listData.add(new Model(0,1,0));
            listData.add(new Model(1,0,1));
            listData.add(new Model(1,1,0));
        }else if(position==3){
            listData.add(new Model(0,0,0));
            listData.add(new Model(0,1,1));
            listData.add(new Model(1,0,1));
            listData.add(new Model(1,1,0));
        }
        String data = "";
        data = "| X1 | X2 | Y |\n";
        for (Model m:listData){
            data+="| "+m.getX1()+" | "+m.getX2()+" | "+m.getY()+" |\n";
        }
        mainView.showData(data);
    }

    public void prosesData(){
        if(posit<=1000) {
            mainView.onShowProses("\nEPOK "+posit+"\n| X1 | X2 | Summation | Output |");
            for (int i = 0; i < 4; i++) {
                Model m = listData.get(i);
                float summation = (1 * wi) + (m.getX1() * w1) + (m.getX2() * w2);
                if (summation < 0) {
                    if (m.getY() == 0) {
                        Log.d("CEK W",wi+" : "+w1+" : "+w2);
                        mainView.onShowProses("| " + m.getX1() + " | " + m.getX2() + " |   " + summation + "   |   0   |");
                    } else {
                        mainView.onShowProses("| " + m.getX1() + " | " + m.getX2() + " |   " + summation + "   |   1   | ERRORR");
                        buatWBaru(m, (m.getY() - 0));
                        Log.d("CEK W ELSE",wi+" : "+w1+" : "+w2);
                        prosesData();
                        break;
                    }
                } else if (summation > 0) {
                    if (m.getY() == 1) {
                        Log.d("CEK W",wi+" : "+w1+" : "+w2);
                        mainView.onShowProses("| " + m.getX1() + " | " + m.getX2() + " |   " + summation + "   |   1  |");
                    } else {
                        mainView.onShowProses("| " + m.getX1() + " | " + m.getX2() + " |   " + summation + "   |   1   | ERRORR");
                        buatWBaru(m, (m.getY() - 1));
                        Log.d("CEK W ELSE",wi+" : "+w1+" : "+w2);
                        prosesData();
                        break;
                    }
                }
            }
        }
    }

    public void menampilkanBerat(){
        wiBaru = wi;
        w1Baru = w1;
        w2Baru = w2;
        mainView.onShowProses("\nMAKA \nWbias = "+wi+"\nW1 = "+w1+"\nW2 = "+w2+"\n");
    }

    public void prosesTesting(){
        mainView.onShowInputTesting();
    }

    public void menghitungTesting(String input){
        int hasil;
        float summation = (1 * wi) + (Float.parseFloat(input.split(",")[0]) * w1) + (Float.parseFloat(input.split(",")[1]) * w2);
        if(summation<0){
            hasil = 0;
        }else{
            hasil = 1;
        }
        String hasilKalimat = "Hasil dari Data Testing yang anda Masukkan ialah Memiliki LABEL "+hasil;
        mainView.onShowHasilTesting(hasilKalimat);
    }

    public void buatWBaru(Model m,int error) {
        wi = wi + (li*1*error);
        w1 = w1 + (l1*m.getX1()*error);
        w2 = w2 + (l2*m.getX2()*error);
        posit++;
        Log.d("random",posit+": "+String.valueOf(wi)+" : "+String.valueOf(w1)+" : "+String.valueOf(w2));
    }

    public void generateBerat() {
        Random r = new Random();
        wi = -1 + r.nextFloat() * (1 - (-1));
        w1 = -1 + r.nextFloat() * (1 - (-1));
        w2 = -1 + r.nextFloat() * (1 - (-1));
        li = wi;
        l1 = w1;
        l2 = w2;
        posit++;
        Log.d("randomAwal",posit+": "+String.valueOf(wi)+" : "+String.valueOf(w1)+" : "+String.valueOf(w2));
    }
}
