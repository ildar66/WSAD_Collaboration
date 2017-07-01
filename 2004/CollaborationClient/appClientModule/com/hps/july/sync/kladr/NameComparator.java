package com.hps.july.sync.kladr;

import org.apache.regexp.RE;

import java.util.Comparator;

/**
 * Date: 09.04.2007
 * Time: 16:00:33
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class NameComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        String nriName = ((String)o1).toLowerCase();
        String kladrName = ((String)o2).toLowerCase();

        nriName = transformName(nriName);
        kladrName = transformName(kladrName);

        int comparsionResult = nriName.compareTo(kladrName);
        return comparsionResult;
    }

    public String transformName(String name) {
        name = name.toLowerCase();

        RE re1 = new RE(" область");
        RE re2 = new RE("г. ");
        RE re3 = new RE("республика");
        RE re4 = new RE(" край");
        RE re5 = new RE(" ао");
        RE re6 = new RE(" р-н");
        RE re7 = new RE(" р-он");
        RE re8 = new RE("сдт ");
        RE re9 = new RE("\\([0-9]*\\)");

        name = re1.subst(name, "");
        name = re2.subst(name, "");
        name = re3.subst(name, "");
        name = re4.subst(name, "");
        name = re5.subst(name, "");
        name = re6.subst(name, "");
        name = re7.subst(name, "");
        name = re8.subst(name, "");
        name = re9.subst(name, "");

        return name.trim();
    }
}
