package com.kreitek.refactor.mal.documents;

import com.kreitek.refactor.mal.Documentation;
import com.kreitek.refactor.mal.TipoUltCaracter;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CIF implements Documentation {
    public String numCIF;
    public Date fchValidez;
    TipoUltCaracter tipUltCar;

    public CIF(String numCIF,Date fchValidez){
            this.numCIF=numCIF;
            this.fchValidez=fchValidez;

        }
        @Override
        public int getValidation() {
            if (isNull(this.numCIF)) {
                final String CIF = this.numCIF.toUpperCase();
                final char ultimoCar = CIF.charAt(CIF.length() - 1);

                if (isFirstLetter(CIF)) {
                    return 0;
                }

                if (regexValidation(CIF)) {
                    return 0;
                }


                if (isPQSkW(CIF)) {
                    tipUltCar = TipoUltCaracter.LETRA;
                    if (!(isChar(ultimoCar))) {
                        return 0;
                    }
                } else if (isABEH(CIF)) {
                    tipUltCar = TipoUltCaracter.NUMERO;
                    if (!(isNumber(ultimoCar))) {
                        return 0;
                    }
                } else {
                    tipUltCar = TipoUltCaracter.AMBOS;
                }


                if (tipUltCar == TipoUltCaracter.NUMERO) {

                    final Integer ultCar = Integer.parseInt(Character.toString(ultimoCar));
                    if (pos(CIF) != ultCar.intValue()) {

                        return 0;
                    }

                } else if (tipUltCar == TipoUltCaracter.LETRA) {
                    if (carControl(CIF) != ultimoCar) {
                        return 0;
                    }

                } else {
                    // find all occurrences forward
                    Integer ultCar = -1;
                    ultCar = "JABCDEFGHI".indexOf(ultimoCar);

                    if (differentChar(ultCar,CIF)) {
                        return 0;
                    }
                }
                return 1;
            }
            return 0;

    }
    public boolean differentChar(Integer ultCar, String CIF){
        final char ultimoCar = CIF.charAt(CIF.length() - 1);
        if(pos(CIF) != ultCar.intValue() && (carControl(CIF) != ultimoCar)){
            return true;
        }else{
            return false;
        }
    }
    public boolean lastChar(Integer ultCar){
        if(ultCar < 0){
            return true;
        }else{
            return false;
        }
    }
    public int numControl(String CIF){
        int numControl = 10 - (sumEvensOdds(CIF) % 10);
        return numControl;
    }
    public char carControl(String CIF){
        final char carControl = "JABCDEFGHI".charAt(pos(CIF));
        return carControl;
    }
    public int pos(String CIF){
        int pos = numControl(CIF) == 10? 0:numControl(CIF);
        return pos;
    }
    public boolean isFirstLetter(String CIF){

        if ("ABCDEFGHJKLMNPQRSUVW".indexOf(CIF.charAt(0)) == -1){
            return  true;
        }else{
            return false;
        }

    }
    public boolean isNull(String CIF){
        if(this.numCIF != null){
           return true;
        }else{
            return false;
        }
    }

    public boolean regexValidation(String CIF){

        final Pattern mask = Pattern.compile("[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]{1}");
        final Matcher matcher = mask.matcher(CIF);
        if(!matcher.matches()){
            return true;
        }else {
            return false;
        }
    }

    public int sumEvensOdds(String CIF){
        final String digitos = CIF.substring(1, CIF.length() - 1);

        // sumo los pares
        Integer sumaPares = 0;
        for (int i = 1; i <= digitos.length() - 1; i = i + 2) {
            sumaPares += Integer.parseInt(digitos.substring(i, i + 1));
        }

        // sumo los impares
        Integer sumaImpares = 0;
        for (int i = 0; i <= digitos.length() - 1; i = i + 2) {
            Integer cal = Integer.parseInt(digitos.substring(i, i + 1)) * 2;
            if (cal.toString().length() > 1) {
                cal = Integer.parseInt(cal.toString().substring(0, 1))
                        + Integer.parseInt(cal.toString().substring(1, 2));
            }
            sumaImpares += cal;
        }


        final int total = sumaPares + sumaImpares;
        return total;
    }
    public boolean isPQSkW(String CIF){
        final char primerCar = CIF.charAt(0);
        if(primerCar == 'P' || primerCar == 'Q' || primerCar == 'S' || primerCar == 'K' || primerCar == 'W'){
            return true;
        }else {
            return false;
        }

    }
    public boolean isABEH(String CIF){
        final char primerCar = CIF.charAt(0);
        if(primerCar == 'A' || primerCar == 'B' || primerCar == 'E' || primerCar == 'H'){
            return true;
        }else {
            return false;
        }

    }

    public boolean isChar(char ultimoCar){
        if(ultimoCar >= 'A' && ultimoCar <= 'Z'){
            return true;
        }else{
            return false;
        }
    }
    public boolean isNumber(char ultimoCar){
        if(ultimoCar >= '0' && ultimoCar <= '9'){
            return true;
        }else{
            return false;
        }

    }


}
