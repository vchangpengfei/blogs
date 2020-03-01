# -*- coding: utf-8 -*-

file = open("./(English)Bloomberg and The Legacy of Stop-and-Frisk - Between the Scenes _ The Daily Show - YouTube.srt")

# for line in file:
#     sst=line.split()[1].replace(',','","')
#     print (',{"id": '+line.split()[0]+',"words": ["'+sst+'"]}')

# print (file.read())

res=''
for item in file.read().split('\n\n'):
    # print(len(item.split('\n')))
    for index in range(len(item.split('\n'))):
        if(index!=0 and index!=1):
            # print (item.split('\n')[index])
            res+=(item.split('\n')[index]+' ')
print (res)
exit()

# for line in file:
#     sst=''
#     for t in line.strip('\n').strip().split('\t')[1].split(','):
#         sst=sst+'"'+t.strip()+'",';
#     print (',{"id": '+line.strip('\n').split()[0]+',"words": ['+sst[:-1]+']}')