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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class allows to wrap a long sentence on several lines. The class allows to:
 * <ul>
 * <li>Specify tyhe maximum number of columns for each line</li>
 * <li>Hyphenate the words</li>
 * <li>Limit the maximum number of lines</li>
 * </ul>
 *
 * @since 0.1
 */
public class WordWrapper {
   private static final Pattern HYPHEN = Pattern.compile("(?i)[aiou][aeiou]*|e[aeiou]*(?!d?\\\\b)");

   /**
    * Wrap a sentence without limiting the number of lines, and without hyphenation.
    *
    * @param sentence the sentence
    * @param maxLength the maximum length of each line
    * @return the wrapped sentence
    */
   public static List<String> wrap(String sentence, int maxLength) {
      return wrap(sentence, maxLength, -1, false);
   }

   /**
    * Wrap a sentence without limiting the number of lines, and with or without hyphenation.
    *
    * @param sentence the sentence
    * @param maxLength the maximum length of each line
    * @param hyphenate true to hyphenate words
    * @return the wrapped sentence
    */
   public static List<String> wrap(String sentence, int maxLength, boolean hyphenate) {
      return wrap(sentence, maxLength, -1, hyphenate);
   }

   /**
    * Wrap a sentence, with or without hyphenation.
    *
    * @param sentence the sentence
    * @param maxLength the maximum length of each line
    * @param maxLines the maximum number of lines (set -1 to not limit the number of lines)
    * @param hyphenate true to hyphenate words
    * @return the wrapped sentence
    */
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
         } else if (!hyphenate || tk.contains("-")) {
            String str = currentBuf.toString();
            list.add(str.trim());
            currentBuf = new StringBuilder();
            currentBuf.append(tk);
            currentLength = tk.length();
         } else {
            Matcher m = HYPHEN.matcher(tk);
            int end = 0;
            boolean isFirst = true;
            while (m.find()) {
               int start = end;
               end = m.end();
               String syllable = tk.substring(start, end);
               int len = end - start;
               if (currentLength + len + 1 <= maxLength) {
                  currentBuf.append(syllable);
                  currentLength += len + 1;
               } else {
                  if (!isFirst) {
                     currentBuf.append("-");
                  }
                  String str = currentBuf.toString().trim();
                  list.add(str);
                  currentBuf = new StringBuilder();
                  currentBuf.append(syllable);
                  currentLength = syllable.length();
               }
               if (isFirst) {
                  isFirst = false;
               }
            }
         }
      }
      if (currentBuf.length() != 0) {
         list.add(currentBuf.toString().trim());
      }
      if (maxLines > 0 && list.size() > maxLines) {
         for (int i = 0; i < maxLines; i++) {
            list.remove(list.size() - 1);
         }
         String str = list.get(list.size() - 1);
         if (str.length() > 3) {
            list.remove(list.size() - 1);
            str = str.substring(0, str.length() - 3) + "...";
            list.add(str);
         }
      }
   }
}
