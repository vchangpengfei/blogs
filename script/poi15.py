#!/usr/bin/python
# -*- coding: utf-8 -*-
import sys
import clustering

class CEnum(object):
	"""docstring for CEnum"""
	def __init__(self):
		super(CEnum, self).__init__()
		self.inc = 0
		self.key2Num = {}
		self.num2Key = {}

	def __del__(self):
		pass
		
	def GetNum( self , key ):
		if key in self.key2Num:
			return self.key2Num[key]
		else:
			i = self.inc
			self.inc += 1
			self.key2Num[key] = i
			self.num2Key[i] = key
			return i

	def GetKey( self , num ):
		if num in self.num2Key:
			return self.num2Key[num]

def main():
	dictSim = {}
	enum = CEnum()
	for line in sys.stdin:
		line = line.decode('utf-8')
		pA , pB , sim = line.split('\t')  # patternA, patternB, similarity
		pA = enum.GetNum(pA)
		pB = enum.GetNum(pB)
		pA = '|%d|'%(pA)
		pB = '|%d|'%(pB)
		sim = float(sim)
		if pA not in dictSim:
			dictSim[pA] = {}
		dictSim[pA][pB] = sim
	# for i in range(enum.inc):
		# print( ('\t'.join([str(i),enum.num2Key[i]])).encode('utf-8') )
	hc = clustering.CHC()
	threshold = 0.2
	dictOut = hc.Hierarchical( threshold , dictSim , enum )
	for i,tpl in enumerate(dictOut):
		tpl = [ int(k.strip()) for k in tpl.split('|') if len(k.strip()) > 0 ]
		for pat in tpl:
			pat = enum.GetKey(pat)
			print( ('\t'.join([str(i),pat])).encode('utf-8') )

if __name__ == '__main__':
	main()