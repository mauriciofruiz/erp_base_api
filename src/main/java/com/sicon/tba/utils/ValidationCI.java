package com.sicon.tba.utils;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;

public class ValidationCI {


    /**
     * Validacion del valor Inicial
     */

    public static boolean validateInitial(String numero, int caracteres) throws Exception {
        if (StringUtils.isEmpty(numero)) {
            throw new CustomException(HttpStatus.NO_CONTENT, ErrorMessage.CI_ERROR);
        }
        if (!NumberUtils.isDigits(numero)) {
            throw new Exception("Valor ingresado sslo puede tener digitos");
        }

        if (numero.length() != caracteres) {
            throw new Exception(("Valor ingresado debe tener " + caracteres));
        }
        return true;
    }

    /**
     * Validacion cod de provincia
     */

    public static boolean validateCodProvince(String numero) throws Exception {
        if (Integer.parseInt(numero) < 0 || Integer.parseInt(numero) > 24) {
            throw new CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_ERROR);
        }
        return true;
    }

    /**
     * Validación tercer digito
     */

    public static boolean validateThirdDigit(String numero, Integer tipo) throws Exception {
        switch (tipo) {
            case 1:
            case 2:
                if (Integer.parseInt(numero) < 0 || Integer.parseInt(numero) > 5) {
                    throw new CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_ERROR);
                }
                break;
            case 3:
                if (Integer.parseInt(numero) != 9) {
                    throw new CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_ERROR);
                }
                break;

            case 4:
                if (Integer.parseInt(numero) != 6) {
                    throw new CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_ERROR);
                }
                break;
            default:
                throw new CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_NOT_EXISTS);
        }

        return true;
    }


}
