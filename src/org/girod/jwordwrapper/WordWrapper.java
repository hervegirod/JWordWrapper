/*
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies,
either expressed or implied, of the FreeBSD Project.

Alternatively if you have any questions about this project, you can visit
the project website at the project page on https://github.com/hervegirod/JWordWrap
 */
package org.girod.jwordwrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @since 0.1
 */
public class WordWrapper {

   public static List<String> wrap(String sentence, int maxLength) {
      return wrap(sentence, maxLength, -1, false);
   }

   public static List<String> wrap(String sentence, int maxLength, boolean hyphenate) {
      return wrap(sentence, maxLength, -1, hyphenate);
   }

   public static List<String> wrap(String sentence, int maxLength, int maxLines, boolean hyphenate) {
      List<String> list = new ArrayList<>();
      if (sentence.length() <= maxLength) {
         list.add(sentence);
      } else {
         doWrapImpl(list, sentence, maxLength, maxLines, hyphenate);
      }

      return list;
   }

   private static void doWrapImpl(List<String> list, String sentence, int maxLength, int maxLines, boolean hyphenate) {
      int currentLength = 0;
      StringBuilder currentBuf = new StringBuilder();
      StringTokenizer tok = new StringTokenizer(sentence, " ");
      while (tok.hasMoreTokens()) {
         String tk = tok.nextToken();
         int _length = tk.length();
         boolean hasNext = tok.hasMoreTokens();
         int lengthToAdd = hasNext ? _length + 1 : _length;
         if (currentLength + lengthToAdd <= maxLength) {
            currentBuf.append(tk);
            if (hasNext) {
               currentBuf.append(" ");
            }
            currentLength += lengthToAdd;
         } else if (currentLength + lengthToAdd == maxLength + 1) {
            currentBuf.append(tk);
            if (maxLines == -1 || list.size() < maxLines) {
               if (maxLines > 0 && list.size() == maxLines - 1) {
                  String str = currentBuf.toString();
                  str = str.substring(0, maxLength - 3) + "...";
                  list.add(str);
                  break;
               } else {
                  list.add(currentBuf.toString().trim());
                  currentBuf = new StringBuilder();
                  currentLength = 0;
               }
            }
         } else if (!hyphenate) {
            if (maxLines == -1 || list.size() < maxLines) {
               String str = currentBuf.toString();
               list.add(str.trim());
               currentBuf = new StringBuilder();
               currentBuf.append(tk);
               currentLength = tk.length();
            } else {
               String str = list.remove(list.size());
               if (str.length() < maxLength) {
                  str = str.substring(0, maxLength - 3) + "...";
                  list.add(str);
               }
               break;
            }
         } else {
            if (maxLines == -1 || list.size() < maxLines) {
               String str = currentBuf.toString();
               list.add(str);
               currentBuf = new StringBuilder();
               currentBuf.append(tk);
               currentLength = tk.length();
            } else {
               String str = list.remove(list.size());
               if (str.length() < maxLength) {
                  str = str.substring(0, maxLength - 3) + "...";
                  list.add(str);
               }
               break;
            }
         }
      }
      if (currentBuf.length() != 0) {
         int _length = currentBuf.length();
         if (currentLength + _length <= maxLength) {
            currentBuf.append(currentBuf.toString().trim());
         } else if (maxLines == -1 || list.size() < maxLines) {
            if (maxLines > 0 && list.size() == maxLines - 1) {
               String str = currentBuf.toString();
               str = str.substring(0, maxLength - 3) + "...";
               list.add(str);
            } else {
               list.add(currentBuf.toString().trim());
            }
         } else {
            String str = list.remove(list.size());
            if (str.length() < maxLength) {
               str = str.substring(0, maxLength - 3) + "...";
               list.add(str);
            }
         }
      }
   }
}
