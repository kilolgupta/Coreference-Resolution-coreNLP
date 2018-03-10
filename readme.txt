Summary of the task-
1. Writing JAVA program using Stanford CoreNLP library to perform coreference resolution between entities and their subsequent mentions in the text. I did this task for true-case and caseless text using the separate models from Stanford's CoreNLP library.
2. After applying the above program to documents from conll data corpus (data from web, newswire, broadcast etc), I obtained their results for 2 versions of the same text- truecase and caseless using the corresponding JAVA program.
3. Using the coreference provided by the conll as the golden truth, I transformed both of the coreference results in the same format as one was in XML format and another was in line-based text format, using Python libraries for text manipulation. This was done to establish the provenance of Stanford's truecase model over casless by comparing the F1 score, recall and precision metrics. But our observation was that there wasn't a significant difference between the results obtained for truecase and caseless models and decided to not invest time in converting our documents into truecase as all of them are in caseless format originally.



Writing the JAVA program using Stanford's coreNLP library was quite straight forward, but the main challenge was to convert the conll coreference results which were in XML format and the stanford output format into some common format for the sake of comparison. 
Following were some pre-processing that had to be done before calculating the F1 score:

1. removed the space in front of inverted comma, for eg. here 's --> here's  from the xml file, caseless and upper case file
2. removed extra '' `` characters in the text
3. Known differences, there is a space between comma and full stop
4. Removed the puntuation from end of the entities and mentions from the final output files.
5. Considered the entity and mention in reverse order while trying to find a match.


To calculate recall and precision, I did case insensitive matching while comparing the lines in conn and stanford output
recall = no. of matching lines in stanford / total no. of lines in conn
precision = no. of matching lines in stanford output/ total no. of lines in stanford output
F1 score = (Precision*Recall)/(Precision + Recall)



Before Removing the lines where entity and mention are exactly the same:

Broadcast News:
f1 score for caseless- 0.1283422459893048
f1 score for truecase- 0.14659685863874344
total lines in conn- 62

count of matching lines in caseless- 12
total lines in caseless stanford output- 125

count of matching lines in truecase- 14
total lines in truecase stanford output- 129


Newswire:
f1 score for caseless- 0.25882352941176473
f1 score for truecase- 0.3070866141732283
total lines in conn- 74
count of matching lines in caseless- 33
total lines in caseless stanford output- 181

count of matching lines in truecase- 39
total lines in truecase stanford output- 180


Web:
f1 score for caseless- 0.1515812431842966
f1 score for truecase- 0.16417910447761194
total lines in conn- 429

count of matching lines in caseless- 139
total lines in caseless stanford output- 1405

count of matching lines in truecase- 154
total lines in truecase stanford output- 1447

---------------------------------------------------------------------------------------------------------------------

After removing the lines where entity and mention are exactly the same:
Broadcast News:
f1 score for caseless- 0.08450704225352113
f1 score for truecase- 0.1111111111111111
total lines in conn- 43

count of matching lines in caseless- 3
total lines in caseless stanford output- 28

count of matching lines in truecase- 4
total lines in truecase stanford output- 29


Newswire:
f1 score for caseless- 0.28205128205128205
f1 score for truecase- 0.4285714285714286
total lines in conn- 43

count of matching lines in caseless- 11
total lines in caseless stanford output- 35

count of matching lines in truecase- 18
total lines in truecase stanford output- 41


Web:
f1 score for caseless- 0.1513157894736842
f1 score for truecase- 0.14826498422712933
total lines in conn- 265

count of matching lines in caseless- 46
total lines in caseless stanford output- 343

count of matching lines in truecase- 47
total lines in truecase stanford output- 369



Challenges:
nested xml tag structure, had to find the tag's text, nested children' tail, and in between text
stanford library considered one sentence only upon encountering a full stop
I manually added the sentence numbers to tags, and hence did line wise coversion, not while keeping a track of the full stop which was very difficult
Had to add a full stop at the end of the sentence in place of , or :
In one instance, there was a . in between the same sentence, not separated by \n and hence the sentence ordering was different
