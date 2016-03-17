package servlet;

import java.io.UnsupportedEncodingException;

public class fanyi {
	public static void main(String[] args) {
		String one = "\u82f9\u679c";
		String name = null;
		name = decodeUnicode(one);
		System.out.println(name);
	}
	//Unicode编码转中文 \u6881\u6587\u7855
	//\u6211\u7231\u4f60\u9648\u5a77
	//92 117 54 56  56 49 梁
	//92 117 54 53  56 55 文
	//92 117 55 56  53 53 硕
    public static String decodeUnicode(String theString) {      
        
        char aChar;      
       
         int len = theString.length();      
       
        StringBuffer outBuffer = new StringBuffer(len);      
       
        for (int x = 0; x < len;) {      
       
         aChar = theString.charAt(x++);      
       
         if (aChar == '\\') {      
       
          aChar = theString.charAt(x++);      
       
          if (aChar == 'u') {      
       
           // Read the xxxx      
       
           int value = 0;      
       
           for (int i = 0; i < 4; i++) {      
       
            aChar = theString.charAt(x++);      
       
            switch (aChar) {      
       
            case '0':      
       
            case '1':      
       
            case '2':      
       
            case '3':      
       
           case '4':      
       
            case '5':      
       
             case '6':      
              case '7':      
              case '8':      
              case '9':      
               value = (value << 4) + aChar - '0';      
               break;      
              case 'a':      
              case 'b':      
              case 'c':      
              case 'd':      
              case 'e':      
              case 'f':      
               value = (value << 4) + 10 + aChar - 'a';      
              break;      
              case 'A':      
              case 'B':      
              case 'C':      
              case 'D':      
              case 'E':      
              case 'F':      
               value = (value << 4) + 10 + aChar - 'A';      
               break;      
              default:      
               throw new IllegalArgumentException(      
                 "Malformed   \\uxxxx   encoding.");      
              }      
       
            }      
             outBuffer.append((char) value);      
            } else {      
             if (aChar == 't')      
              aChar = '\t';      
             else if (aChar == 'r')      
              aChar = '\r';      
       
             else if (aChar == 'n')      
       
              aChar = '\n';      
       
             else if (aChar == 'f')      
       
              aChar = '\f';      
       
             outBuffer.append(aChar);      
       
            }      
       
           } else     
       
           outBuffer.append(aChar);      
       
          }      
       
          return outBuffer.toString();      
       
         }     
}
