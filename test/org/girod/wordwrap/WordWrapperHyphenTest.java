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
 * @since 0.1
 */
public class WordWrapperHyphenTest {

   public WordWrapperHyphenTest() {
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
      System.out.println("WordWrapperHyphenTest : testWrap");
      String sentence = "the test";

      List<String> result = WordWrapper.wrap(sentence, 10, true);
      assertNotNull("Result should not be null", result);
      assertEquals("Result should have one line", 1, result.size());
      assertEquals("Result should be identical", sentence, result.get(0));
   }

   /**
    * Test of wrap method, of class WordWrapper.
    */
   @Test
   public void testWrap2() {
      System.out.println("WordWrapperTest : testWrap2");
      String sentence = "bababa bibibi";

      List<String> result = WordWrapper.wrap(sentence, 8, true);
      assertNotNull("Result should not be null", result);
      assertEquals("Result should have 2 lines", 2, result.size());
      assertEquals("Result", "bababa", result.get(0));
      assertEquals("Result", "bibibi", result.get(1));
   }

   /**
    * Test of wrap method, of class WordWrapper.
    */
   @Test
   public void testWrap3() {
      System.out.println("WordWrapperTest : testWrap3");
      String sentence = "bababa bibobu";

      List<String> result = WordWrapper.wrap(sentence, 10, true);
      assertNotNull("Result should not be null", result);
      assertEquals("Result should have 2 lines", 2, result.size());
      assertEquals("Result", "bababa bi-", result.get(0));
      assertEquals("Result", "bobu", result.get(1));
   }

   /**
    * Test of wrap method, of class WordWrapper.
    */
   @Test
   public void testWrap4() {
      System.out.println("WordWrapperTest : testWrap4");
      String sentence = "the sentence";

      List<String> result = WordWrapper.wrap(sentence, 10, true);
      assertNotNull("Result should not be null", result);
      assertEquals("Result should have 2 lines", 2, result.size());
      assertEquals("Result", "the se-", result.get(0));
      assertEquals("Result", "ntence", result.get(1));
   }
}
