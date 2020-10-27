import codecs
import pickle
words=[]
input_data=codecs.open('freestype.txt','r','utf-8')
for line in input_data.readlines():
    print(line.strip())
    line.split()
    words.append(line.strip())


import pandas as pd

seriesWords=pd.Series(words)
seriesWords=seriesWords.value_counts()
set_words=seriesWords.index
set_ids=range(1,len(set_words)+1)

word2id=pd.Series(set_ids,index=set_words)
id2word=pd.Series(set_words,index=set_ids)

output=open('pb.dump','wb')
pickle.dump(word2id,output)
pickle.dump(id2word,output)
output.close()

input=open('pb.dump','rb')


id2word=pickle.load(input)#可以颠倒顺序
word2id=pickle.load(input)



for word in word2id:
    print(word)

for id in id2word:
    print(id)



exit()

