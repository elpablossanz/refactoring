package com.kreitek.refactor.mal;

import static com.kreitek.refactor.mal.PrintHeader.header;

public class  Main {
    public static void main(String args[]) {

        header();
        rightDNI();
        rightCIF();
        rightNIE();
        wrongDocumentation();

    }


    public static void rightDNI(){
        DNI dniCorrecto = new DNI("11111111H", null);
        Boolean esValido = (dniCorrecto.getValidation() == 1);
        System.out.println( "DNI " + dniCorrecto.numDNI + " es: " + esValido.toString());
    }

    public static void rightNIE(){
        NIE nieCorrecto = new NIE( "X0932707B", null);
        Boolean esValidoNie = (nieCorrecto.getValidation() == 1);
        System.out.println( "NIE " + nieCorrecto.numNIE + " es: " + esValidoNie.toString());
    }
    public static void rightCIF(){
        CIF cifCorrecto = new CIF( "W9696294I", null);
        Boolean esValidoCIF = (cifCorrecto.getValidation()== 1);
        System.out.println( "CIF " + cifCorrecto.numCIF + " es: " + esValidoCIF.toString());
    }
    public static void wrongDocumentation(){
        DNI dniIncorrecto01 = new DNI( "24324356A", null);
        Boolean esValido01 = (dniIncorrecto01.getValidation() == 1);
        System.out.println( "DNI " + dniIncorrecto01.numDNI + " es: " + esValido01.toString());

        NIE nieIncorrecto = new NIE( "Z2691139Z", null);
        Boolean esValidoNieIncorrecto = (nieIncorrecto.getValidation() == 1);
        System.out.println( "NIE " + nieIncorrecto.numNIE + " es: " + esValidoNieIncorrecto.toString());

        CIF cifIncorrecto = new CIF("W9696294A", null);
        Boolean esValidoCifIncorrecto = (cifIncorrecto.getValidation() == 1);
        System.out.println( "CIF " + cifIncorrecto.numCIF + " es: " + esValidoCifIncorrecto.toString());
    }
}