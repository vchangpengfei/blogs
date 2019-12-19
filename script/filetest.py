#!/usr/bin/env python

# -*- coding: utf-8 -*-

file = open("./sensitive.txt",encoding='utf-8', errors='ignore')

# for line in file:
#     sst=line.split()[1].replace(',','","')
#     print (',{"id": '+line.split()[0]+',"words": ["'+sst+'"]}')



for line in file:
    sst=''
    for t in line.strip('\n').strip().split('\t')[1].split(','):
        sst=sst+'"'+t.strip()+'",';
    print (',{"id": '+line.strip('\n').split()[0]+',"words": ['+sst[:-1]+']}')