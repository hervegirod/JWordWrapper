# JWordWrapper
A small Java library which is able to wrap sentences around words

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
  List<String> wrappedText = WordWrapper("the sentence", 10);
  ```
