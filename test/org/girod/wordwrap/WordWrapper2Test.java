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
package org.girod.wordwrap;

import org.girod.jwordwrapper.WordWrapper;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @since 0.2
 */
public class WordWrapper2Test {

   public WordWrapper2Test() {
   }

   @BeforeClass
   public static void setUpClass() {
   }

   @AfterClass
   public static void tearDownClass() {
   }

   @Before
   public void setUp() {
   }

   @After
   public void tearDown() {
   }

   /**
    * Test of wrap method, of class WordWrapper.
    */
   @Test
   public void testWrap() {
      System.out.println("WordWrapper2Test : testWrap");
      String sentence = "Nationalité du JTAC elbow1 : États Unis . Plan de frequence : diamond 12 . ";

      List<String> result = WordWrapper.wrap(sentence, 70, 3, true);
      assertNotNull("Result should not be null", result);
      assertEquals("Result should have 2 lines", 2, result.size());
      assertEquals("First line", "Nationalité du JTAC elbow1 : États Unis . Plan de frequence : diamond", result.get(0));
      assertEquals("Second line", "12 .", result.get(1));
   }
   
   /**
    * Test of wrap method, of class WordWrapper.
    */
   @Test
   public void testWrap2() {
      System.out.println("WordWrapper2Test : testWrap2");
      String sentence = "Plan de fréquence du JTAC elbow1 : diamond 12 .  : fréquence  VHF :  150.150  Mhz. ";

      List<String> result = WordWrapper.wrap(sentence, 70, 3, true);
      assertNotNull("Result should not be null", result);
      assertEquals("Result should have 2 lines", 2, result.size());
      assertEquals("First line", "Plan de fréquence du JTAC elbow1 : diamond 12 . : fréquence VHF :", result.get(0));
      assertEquals("Second line", "150.150 Mhz.", result.get(1));
   }   
   
   
}
