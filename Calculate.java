import javax.swing.*;

public class Calculate extends JPanel {
    //Створення та ініціалізація змінних
    int G = 80000, Ix = 250000, Iy = 900000, TF = 30, len = 0, mzw_ = -13, H = 500;
    double a1, a2, a3, a4, a5, a6, a7, b1, b2, b3, b4, b5, b6, b7, C6, DV_bal, a_bal, cy_bal, m, Vpr;
    public static double Kw0 = 0.112, KWX = 1.5, KWY = 2.5, DE, TWX = 1.6, TWY = 2.5,
            DES = 0, DNP = 10, KSHE, XSH, KSHP, XP, DED, DN, DND; // -1/0, 10/0

    double Xt = 0.24, S = 201.45, l = 37.55, ba = 5.285, V = 97.2, p = 0.1190, g = 9.81,
            cy0 = -0.255, cya = 5.78, cy_dv = 0.2865, cx = 0.046, mz0 = 0.2,
            mza_ = -3.8, mza = -1.83, mz_dv = -0.96, DD = 0.5, DT = 0.01,
            TD=0, T = 0, an = 338.36, cy_a = 5.78, my_wy =-0.141, my_b =-0.1518,
            my_dn =-0.071, cz_b =-0.8136, mx_dn =-0.02, mx_wy = -0.151, cz_dn =-0.16, mx_wx =-0.56,
            mx_b =-0.1146, mx_de =-0.07, my_de = 0, my_wx =-0.026;
    double[] X = new double[7];
    double[] Y = new double[7];
    int roof = 2*TF+1; // TF - час польоту
    //Масиви таблиці
    double[] Time = new double[roof];
    double[] massY1 = new double[roof]; double[] massY3 = new double[roof]; double[] massY4 = new double[roof];
    double[] massX1 = new double[roof]; double[] massX3 = new double[roof]; double[] massX4 = new double[roof];
    double[] massDND = new double[roof];
    double[] massDED = new double[roof];
    double[] massDNP = new double[roof];
    public void Calc() {
// Вираховуємо змінні
        m = G / g;

        cy_bal = (2*G/(S*p*Math.pow(V,2)));
        a_bal =57.3*((cy_bal-cy0)/cy_a);
        a1 = -(my_wy/Iy)*S*Math.pow(l,2)*(p*V/4);
        a2 = -(my_b/Iy)*S*l*(p*Math.pow(V,2)/2);
        a3 = -(my_dn/Iy)*S*l*(p*Math.pow(V,2)/2);
        a4 = -(cz_b/m)*S*(p*V/2);
        a5 = -(mx_dn/Ix)*S*l*p*Math.pow(V,2)/2;
        a6 = -(mx_wy/Ix)*S*Math.pow(l,2)*p*V/4;
        a7 = -(cz_dn/m)*S*p*V/2;
        C6 = V/57.3;
        b1 = -(mx_wx/Ix)*S*Math.pow(l,2)*(p*V/4);
        b2 = -(mx_b/Ix)*S*l*(p*Math.pow(V,2)/2);
        b3 = -(mx_de/Ix)*S*l*(p*Math.pow(V,2)/2);
        b4 = (g/V)*Math.cos(a_bal);
        b5 = -(my_de/Iy)*S*l*p*Math.pow(V,2)/2;
        b6 = -(my_wx/Iy)*S*Math.pow(l,2)*p*V/4;
        b7 = 0.16557252416272056; //Math.sin(a_bal)

        //DES = KSHE * XSH; // KSHE, KSHP - коеф. передачі
        //DNP = KSHP * XP; // XSH, XP - переміщ. штурвал та педалей
        DE = DES + DED;
        DN = DNP + DND;
        X[0]=0;Y[0]=0;
        X[1]=0;Y[1]=0;
        X[2]=0;Y[2]=0;
        X[3]=0;Y[3]=0;
        X[4]=0;Y[4]=0;
        X[5]=0;Y[5]=0;
    }

    public void Calculate1() {
        while(T<=TF){
            DED = 0; //Демпфер вимкнено
            DND = 0;

            X[0] = Y[1]; // W* = wx
            X[1] = -a1 * X[0] - b6 * X[3] - a2 * Y[4] - a3 * DN - b5 * DE; //wz*(W**)
            X[2] = Y[3]; // y* = wy
            X[3] = -a6 * X[1] - b1 * X[3] - b2 * Y[4] - a5 * DN - b3 * DE; // wy*(y**)
            X[4] = X[1] + b7 * X[3] + b4 * Y[2] - a4 * Y[4] - a7 * DN; //beta*
            X[5] = -C6 * (Y[0] - Y[4]); //Z*

            //Інтегрування диференціальних рівнянь методом Ейлера

            for (int j = 0; j < 5; j++) {
                Y[j] = Y[j] + X[j] * DT;
            }
            if (T >= TD) {
                Time[len] = T;
                massY1[len] = Y[0]; massY3[len] = Y[2]; massY4[len] = Y[4];
                massX1[len] = X[0]; massX3[len] = X[2]; massX4[len] = X[4];
                massDND[len] = DND;
                massDED[len] = DED;
                massDNP[len] = DNP;
                TD += DD;
                len++;
            }
            T += DT;
        }
    }
    public void Calculate2 () {
        while (T <= TF) {
            DED = KWX * Y[1]; //Демпфер увімкнено
            DND = KWY * Y[3];

            X[0] = Y[1]; // W* = wz
            X[1] = -a1 * X[0] - b6 * X[3] - a2 * Y[4] - a3 * DN - b5 * DE; //wz*(W**)
            X[2] = Y[3]; // y* = wy
            X[3] = -a6 * X[1] - b1 * X[3] - b2 * Y[4] - a5 * DN - b3 * DE; // wy*(y**)
            X[4] = X[1] + b7 * X[3] + b4 * Y[2] - a4 * Y[4] - a7 * DN; //beta*
            X[5] = -C6 * (Y[0] - Y[4]); //Z*

            //Інтегрування диференціальних рівнянь методом Ейлера

            for (int j = 0; j < 7; j++) {
                Y[j] = Y[j] + X[j] * DT;
            }
            if (T >= TD) {
                Time[len] = T;
                massY1[len] = Y[0]; massY3[len] = Y[2]; massY4[len] = Y[4];
                massX1[len] = X[0]; massX3[len] = X[2]; massX4[len] = X[4];
                massDND[len] = DND;
                massDED[len] = DED;
                massDNP[len] = DNP;
                TD += DD;
                len++;
            }
            T += DT;
        }
    }
    public void Calculate3 () {
        while (T <= TF) {
            X[6] = DND;
            DED = KWX * Y[1] - Y[6] / TWX; //Демпфер увімкнено(2)
            DND = KWY * Y[3] - Y[6] / TWY;

            X[0] = Y[1]; // W* = wz
            X[1] = -a1 * X[0] - b6 * X[3] - a2 * Y[4] - a3 * DN - b5 * DE; //wz*(W**)
            X[2] = Y[3]; // y* = wy
            X[3] = -a6 * X[1] - b1 * X[3] - b2 * Y[4] - a5 * DN - b3 * DE; // wy*(y**)
            X[4] = X[1] + b7 * X[3] + b4 * Y[2] - a4 * Y[4] - a7 * DN; //beta*
            X[5] = -C6 * (Y[0] - Y[4]); //Z*
            X[6] = DND;
            //Інтегрування диференціальних рівнянь методом Ейлера

            for (int j = 0; j < 7; j++) {
                Y[j] = Y[j] + X[j] * DT;
            }
            if (T >= TD) {
                Time[len] = T;
                massY1[len] = Y[0]; massY3[len] = Y[2]; massY4[len] = Y[4];
                massX1[len] = X[0]; massX3[len] = X[2]; massX4[len] = X[4];
                massDND[len] = DND;
                massDED[len] = DED;
                massDNP[len] = DNP;
                TD += DD;
                len++;
            }
            T += DT;
        }
    }
}