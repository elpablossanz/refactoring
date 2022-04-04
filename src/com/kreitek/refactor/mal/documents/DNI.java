package com.kreitek.refactor.mal.documents;

import com.kreitek.refactor.mal.Documentation;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DNI implements Documentation {


    public String numDNI;
    public Date fchValidez;

    // construye un DNI
    public DNI(String numDNI, Date fchValidez) {
        this.numDNI = numDNI;
        this.fchValidez = fchValidez;
    }

    @Override
    public int getValidation() {
        if (isDNI()) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isDNI(){
        String dniChars="TRWAGMYFPDXBNJZSQVHLCKE";
        String intPartDNI = this.numDNI.trim().replaceAll(" ", "").substring(0, 8);
        char ltrDNI = this.numDNI.charAt(8);
        int valNumDni = Integer.parseInt(intPartDNI) % 23;

        if (this.numDNI.length()!= 9 || dniChars.charAt(valNumDni)!= ltrDNI || isNumeric(intPartDNI) == false){
            return  true;
        }else{
            return false;
        }

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


}
