package com.hps.july.rts.mail.template;

import com.hps.july.mail.template.MailTemplate;

import java.io.InputStream;
import java.util.StringTokenizer;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 29.05.2006
 * Time: 11:55:44
 */
final class MailTemplateFactory {

    static InputStream findTemplate(MailTemplate template) {
        StringBuffer templateName = new StringBuffer();
        //TODO: simple to do, but WSAD Не нашел где подключить jre 1.4.2!
        //templateName.append(template.getClass().getName().replaceAll("[\\.]", "/"));
        StringTokenizer st = new StringTokenizer(template.getClass().getName(), ".");
        while(st.hasMoreTokens()) {
            templateName.append(st.nextToken());
            if(st.hasMoreTokens()) templateName.append("/");
        }
        templateName.append(".message");
        return findTemplate(templateName.toString());
    }

    static InputStream findTemplate(String templateName) {
        SecuritySupport ss = SecuritySupport.getInstance();
        InputStream is = null;

        // First try the Context ClassLoader
        ClassLoader cl = findClassLoader();

        is = ss.getResourceAsStream(cl, templateName);

        // If no provider found then try the current ClassLoader
        if (is == null) {
            ClassLoader current = MailTemplateFactory.class.getClassLoader();
            if (cl != current) {
                cl = current;
                is = ss.getResourceAsStream(cl, templateName);
            }
        }
        return is;
    }

    /**
     * Figure out which ClassLoader to use.  For JDK 1.2 and later use
     * the context ClassLoader.
     */
    static ClassLoader findClassLoader() {
        SecuritySupport ss = SecuritySupport.getInstance();

        // Figure out which ClassLoader to use for loading the provider
        // class.  If there is a Context ClassLoader then use it.
        ClassLoader context = ss.getContextClassLoader();
        ClassLoader system = ss.getSystemClassLoader();

        ClassLoader chain = system;
        while (true) {
            if (context == chain) {
                // Assert: we are on JDK 1.1 or we have no Context ClassLoader
                // or any Context ClassLoader in chain of system classloader
                // (including extension ClassLoader) so extend to widest
                // ClassLoader (always look in system ClassLoader if template
                // is in boot/extension/system classpath and in current
                // ClassLoader otherwise); normal classloaders delegate
                // back to system ClassLoader first so this widening doesn't
                // change the fact that context ClassLoader will be consulted
                ClassLoader current = MailTemplateFactory.class.getClassLoader();

                chain = system;
                while (true) {
                    if (current == chain) {
                        // Assert: Current ClassLoader in chain of
                        // boot/extension/system ClassLoaders
                        return system;
                    }
                    if (chain == null) {
                        break;
                    }
                    chain = ss.getParentClassLoader(chain);
                }

                // Assert: Current ClassLoader not in chain of
                // boot/extension/system ClassLoaders
                return current;
            }

            if (chain == null) {
                // boot ClassLoader reached
                break;
            }

            // Check for any extension ClassLoaders in chain up to
            // boot ClassLoader
            chain = ss.getParentClassLoader(chain);
        };

        // Assert: Context ClassLoader not in chain of
        // boot/extension/system ClassLoaders
        return context;
    } // findClassLoader():ClassLoader

} // class MailTemplateFactory
