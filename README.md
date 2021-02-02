# JWordWrapper
A small Java library which is able to wrap sentences around words

# API
The class allows to:
 * Specify the maximum number of columns for each line
 * Hyphenate the words
 * Allow to keep long URLs even with hyphenation
 * Limit the maximum number of lines

# History
## 0.1
 * First version

## 0.2
 * Fix a possible NPE when getting the syllables from a pure numeric value, take accented characters into account
 * Fix a possible case where some spaces would not be kept in the result

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
