package com.hps.july.mail.text;

import java.util.StringTokenizer;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 23.05.2006
 * Time: 13:52:19
 */
public class TextFormatterTest {

    public static void main(String[] args) {

        try {
            TextFormatter formatter = new TextFormatter();

/*
            HashMap param = new HashMap();
            param.put("hello", "Hello WHere String");
            param.put("1", new Integer(255));
            ExtensibleObject object = new ExtensibleObject();
            object.setId(new Integer(34));
            object.setName("objectName");
            param.put("object", object);
            InputSource source = new InputSource(new FileInputStream("c:\\test"));
            String rezult = formatter.format(source, param);
*/

            //InitiatorRequestOnConsideration2KMTemplate t = new InitiatorRequestOnConsideration2KMTemplate();
            //String rezult = t.getBody();
            String rezult = formatter.getClass().getName();
            System.out.println(rezult);
//            System.out.println(rezult.replaceAll("[\\.]", "/"));
            StringBuffer templateName = new StringBuffer();
            StringTokenizer st = new StringTokenizer(formatter.getClass().getName(), ".");
            while(st.hasMoreTokens()) {
                templateName.append(st.nextToken());
                if(st.hasMoreTokens()) templateName.append("/");
            }
            System.out.println(templateName);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
