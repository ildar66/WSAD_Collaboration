package com.hps.april.common.utils;

public class StringUtils {

    public static final char STAR_DELIMITER = '*';
    public static final char PERCENT_DELIMITER = '%';

    public static String prepareFindString(String find) {
        StringBuffer search = new StringBuffer();
        if(find != null) {
            find = find.toUpperCase().replace(STAR_DELIMITER, PERCENT_DELIMITER);
            search.append(find);
            int percent = find.indexOf(PERCENT_DELIMITER);
            if(percent == -1 && find.length() > 0) {
                search.insert(0, PERCENT_DELIMITER);
                search.append(PERCENT_DELIMITER);
            }
        } else  {
            search.append(PERCENT_DELIMITER).append(PERCENT_DELIMITER);
        }
		return search.toString();
    }

    /**
     * Удаление некорректных символов в строке ("\n" -> " ","\t" -> " ","'"->"\"")
     */
	public static String correctCharacters(String string){
		if (string == null) return null;
		String result = string.replaceAll("\n|\t", " ");
		result = result.replaceAll("'", "\"");
		return result;
	}
	
    public static boolean isValid(String s) {
        return (s != null && s.trim().length() > 0);
    }

    /**
     * Метод генерирует случайную строку заданного размера
     * @return один символ идентификатора контекста
     */
    public static String generateId(int count) {
        String id = "" ;
        for (int i = 0; i < count; i ++) id += generateChar();
        return id ;
    }

    /**
     * Метод генерирует один символ идентификатора
     * @return один символ идентификатора контекста
     */
    private static char generateChar() {
        char ch ;
        do {
            ch = (char)Math.round(Math.random() * 56 + 64);
        } while (!Character.isLetterOrDigit(ch));
        return ch ;
    }

    public static String getClassName(Class clazz){
    	String className = clazz.getName();
    	className = className.replaceFirst(clazz.getPackage().getName() + ".", "");
    	return className;
    }
    
    public static String valueOf(Object object, String message){
    	return object != null ? object.toString() : message; 
    }
    
}
