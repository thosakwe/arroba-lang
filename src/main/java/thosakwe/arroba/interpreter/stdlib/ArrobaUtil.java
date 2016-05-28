package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.data.ArrobaDatum;

class ArrobaUtil extends ArrobaDatum {
    ArrobaUtil() {
        super();

        members.put("String", new StringFunction());
    }
}
