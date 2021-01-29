# JWordWrapper
A small Java library which is able to wrap sentences around words

# API
The class allows to:
 * Specify the maximum number of columns for each line
 * Hyphenate the words
 * Allow to keep long URLs even with hyphenation
 * Limit the maximum number of lines

# Examples
  ```
  // return one line with "the sentence"
  List<String> wrappedText = WordWrapper("the sentence", 20);
  ```

  ```
  // return two lines with "the" and "sentence"
  List<String> wrappedText = WordWrapper("the sentence", 10);
  ```
  
  ```
  // return two lines with "the se-" and "ntence"
  List<String> wrappedText = WordWrapper("the sentence", 10, true);
  ```
  
  ```
  // return two lines with "the se-" and "ntence". In this case we limit the maximum number of lines to 2
  List<String> wrappedText = WordWrapper("the sentence", 10, 2, true);
  ```
