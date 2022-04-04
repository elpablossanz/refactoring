package com.kreitek.refactor.mal;

import java.util.Date;


public class NIE implements Documentation {
    public String numNIE;
    public Date fchValidez;
    boolean esValido = false;


    public NIE(String numNIE,Date fchValidez) {
        this.numNIE=numNIE;
        this.fchValidez=fchValidez;
    }
    @Override
    public int getValidation() {
        loopValidation();
        changefistletter();
        calculateRest();

        if (esValido) {
            return 1;
        } else {
            return 0;
        }

    }
    public boolean isNIEFormat(String nie){
        if(nie.length() == 9 && Character.isLetter(nie.charAt(8)) && nie.substring(0,1).toUpperCase().equals("X") || nie.substring(0,1).toUpperCase().equals("Y")
                || nie.substring(0,1).toUpperCase().equals("Z")){
            return true;
        }else {
            return false;
        }
    }
    public boolean isX(String numNIE){
        if (numNIE.substring(0,1).toUpperCase().equals("X")){
            return true;
        }else{
            return false;
        }

    }
    public boolean isY(String numNIE){
        if (numNIE.substring(0,1).toUpperCase().equals("Y")){
            return true;
        }else{
            return false;
        }

    }
    public boolean isZ(String numNIE){
        if (numNIE.substring(0,1).toUpperCase().equals("X")){
            return true;
        }else{
            return false;
        }

    }

    public void loopValidation(){
        int i = 1;
        int caracterASCII = 0;
        if(isNIEFormat(numNIE)) {
            do {
                caracterASCII = numNIE.codePointAt(i);
                esValido = (caracterASCII > 47 && caracterASCII < 58);
                i++;
            } while(i < numNIE.length() - 1 && esValido);
        }
    }
    public void changefistletter(){
        if(esValido && isX(numNIE)) {
            numNIE = "0" + numNIE.substring(1,9);
        } else if(esValido && isY(numNIE)) {
            numNIE = "1" + numNIE.substring(1,9);
        } else if(esValido && isZ(numNIE)) {
            numNIE = "2" + numNIE.substring(1,9);
        }
    }

    public void calculateRest(){
        char letra = ' ';
        int miNIE = 0;
        int resto = 0;
        char[] asignacionLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X','B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        if(esValido) {
            letra = Character.toUpperCase(numNIE.charAt(8));
            miNIE = Integer.parseInt(numNIE.substring(1,8));
            resto = miNIE % 23;
            esValido = (letra == asignacionLetra[resto]);
        }
    }

}
