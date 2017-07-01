package com.hps.july.mail.template;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.security.PrivilegedActionException;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 29.05.2006
 * Time: 11:48:56
 */
final class SecuritySupport {

    private static final SecuritySupport securitySupport = new SecuritySupport();

    /**
     * Return an instance of this class.
     */
    static SecuritySupport getInstance() {
        return SecuritySupport.securitySupport;
    }

    ClassLoader getContextClassLoader() {
        return (ClassLoader)
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ClassLoader cl = null;
                try {
                    cl = Thread.currentThread().getContextClassLoader();
                } catch (SecurityException ex) { }
                return cl;
            }
        });
    }

    ClassLoader getSystemClassLoader() {
        return (ClassLoader)
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ClassLoader cl = null;
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (SecurityException ex) {}
                return cl;
            }
        });
    }

    ClassLoader getParentClassLoader(final ClassLoader cl) {
        return (ClassLoader)
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ClassLoader parent = null;
                try {
                    parent = cl.getParent();
                } catch (SecurityException ex) {}

                // eliminate loops in case of the boot
                // ClassLoader returning itself as a parent
                return (parent == cl) ? null : parent;
            }
        });
    }

    String getSystemProperty(final String propName) {
        return (String)
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return System.getProperty(propName);
            }
        });
    }

    FileInputStream getFileInputStream(final File file)
    throws FileNotFoundException
    {
        try {
            return (FileInputStream)
            AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws FileNotFoundException {
                    return new FileInputStream(file);
                }
            });
        } catch (PrivilegedActionException e) {
            throw (FileNotFoundException)e.getException();
        }
    }

    InputStream getResourceAsStream(final ClassLoader cl,
                                            final String name) {
        return (InputStream)
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                InputStream ris;
                if (cl == null) {
                    ris = ClassLoader.getSystemResourceAsStream(name);
                } else {
                    ris = cl.getResourceAsStream(name);
                }
                return ris;
            }
        });
    }

    boolean getFileExists(final File f) {
        return ((Boolean)
                AccessController.doPrivileged(new PrivilegedAction() {
                    public Object run() {
                        return new Boolean(f.exists());
                    }
                })).booleanValue();
    }

    long getLastModified(final File f) {
        return ((Long)
                AccessController.doPrivileged(new PrivilegedAction() {
                    public Object run() {
                        return new Long(f.lastModified());
                    }
                })).longValue();
    }

    private SecuritySupport () {}

}
