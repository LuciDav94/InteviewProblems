There is a forum that has a limit of K characters per entry.In this tak your job is to implement an
algorithm for cropping messages that are too long. You are give a message, consisting of English
alphabet letters and spaces, that might be longer than the limit. Your algorithm should crop a 
number of words from the end of the message, keeping in mind that:

  - it may not crop away part of a word;
  - the output message may not end with a space;
  - the output message may not exceed the K-character limit;
  - the output message should be as long as possible;

For example: 
 
   "Codility We test coders"
   K = 14 -> 
   "Codility We"
   
Note that: 
  
  - the output "Codility We te" would be incorrect, because the original message is
cropped through the middle of a word;
  - the output "Codility We " would be incorrect, because it ends with a space;
  - the output "Codility We test coders" would be incorrect, because it exceeds the
K-character limit;
  - the output "Codility" would be incorrect, because it is shorter than the correct 
output.
    
Assume that:
   
  - K is an integer withing the range [1...500];
  - message is a non-empty string containing at most 500 Engligh alphabet letters and
spaces. There are no spaces at the beginning or at the end of message; also there
can't be two or more consecutive spaces in message.    