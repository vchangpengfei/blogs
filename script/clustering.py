#!/usr/bin/python
# -*- coding: utf-8 -*-
import unittest
import itertools

class CHC(object):
	"""docstring for CHC"""
	def __init__(self):
		super(CHC, self).__init__()
		self.globalMax = None
		self.dictMax = {}
		
	def __del__(self):
		pass

	def Init( self , dictClusterSim ):
		listKeys = []
		for k1 , dictInner in dictClusterSim.items():
			listKeys.append(k1)
			for k2 in dictInner:
				listKeys.append(k2)
		setKeys = set(listKeys)
		for k1,k2 in itertools.product( setKeys , setKeys ):
			if k1 == k2:
				pass
			else:
				if k1 in dictClusterSim:
					if k2 in dictClusterSim[k1]:
						pass
					else:
						dictClusterSim[k1][k2] = 0.0
				else:
					dictClusterSim[k1] = { k2: 0.0 }
		if len(self.dictMax) != len(dictClusterSim):
			for k1 , dictInner in dictClusterSim.items():
				k2 , sim = max( dictInner.items() , key=lambda x:x[1] )
				self.dictMax[k1] = (k2,sim)
			k1,(k2,sim) = max( self.dictMax.items() , key=lambda x:x[1][1] )
			self.globalMax = (k1,k2,sim)

		return dictClusterSim

	def FindMax( self , dictClusterSim ):
		maxSim = 0
		clusterA = None
		clusterB = None
		for clusterI,dictInner in dictClusterSim.items():
			for clusterJ,similarity in dictInner.items():
				if similarity > maxSim:
					maxSim = similarity
					clusterA = clusterI
					clusterB = clusterJ
		return ( maxSim , clusterA , clusterB )

	def _FindMax( self , dictClusterSim ):
		k1,(k2,sim) = max( self.dictMax.items() , key=lambda x:x[1][1] )
		self.globalMax = (k1,k2,sim)
		return ( sim , k1 , k2 )

	def MergeCluster( self , dictClusterSim , clusterA , clusterB ):
		clusterC = clusterA + clusterB
		del dictClusterSim[clusterA]
		del dictClusterSim[clusterB]
		dictC = {}
		for clusterI in dictClusterSim:
			simA = dictClusterSim[clusterI][clusterA]
			simB = dictClusterSim[clusterI][clusterB]
			del dictClusterSim[clusterI][clusterA]
			del dictClusterSim[clusterI][clusterB]
			simC = ( simA*(len(clusterI)*len(clusterA)) + simB*(len(clusterI)*len(clusterB)) ) / \
			       ( len(clusterI)*(len(clusterA)+len(clusterB)) )
			dictClusterSim[clusterI][clusterC] = simC
			dictC[clusterI] = simC
		dictClusterSim[clusterC] = dictC
		return dictClusterSim

	def _MergeCluster( self , dictClusterSim , clusterA , clusterB , mergeSim='weightedAverage' ):
		clusterC = clusterA + clusterB
		del dictClusterSim[clusterA]
		del dictClusterSim[clusterB]
		del self.dictMax[clusterA]
		del self.dictMax[clusterB]
		dictC = {}
		for clusterI in dictClusterSim:
			simA = dictClusterSim[clusterI][clusterA]
			simB = dictClusterSim[clusterI][clusterB]
			del dictClusterSim[clusterI][clusterA]
			del dictClusterSim[clusterI][clusterB]
			if mergeSim == 'weightedAverage':
				simC = ( simA*(len(clusterI)*len(clusterA)) + simB*(len(clusterI)*len(clusterB)) ) / \
				       ( len(clusterI)*(len(clusterA)+len(clusterB)) )
			elif mergeSim == 'singleLink':
				simC = max( simA , simB )
			else:
				raise Exception('Invalid mergeSim')
			dictClusterSim[clusterI][clusterC] = simC
			dictC[clusterI] = simC
			k2 , simMax = self.dictMax[clusterI]
			if k2 == clusterA or k2 == clusterB:
				k , simMax = max( dictClusterSim[clusterI].items() , key=lambda x:x[1] )
				self.dictMax[clusterI] = (k,simMax)
		dictClusterSim[clusterC] = dictC
		k2 , simMax = max( dictClusterSim[clusterC].items() , key=lambda x:x[1] )
		self.dictMax[clusterC] = (k2,simMax)
		return dictClusterSim

	def _MergeClusterSingleLink( self , dictClusterSim , clusterA , clusterB ):
		clusterC = clusterA + clusterB
		del dictClusterSim[clusterA]
		del dictClusterSim[clusterB]
		del self.dictMax[clusterA]
		del self.dictMax[clusterB]
		dictC = {}
		for clusterI in dictClusterSim:
			simA = dictClusterSim[clusterI][clusterA]
			simB = dictClusterSim[clusterI][clusterB]
			del dictClusterSim[clusterI][clusterA]
			del dictClusterSim[clusterI][clusterB]
			simC = max( simA , simB )
			dictClusterSim[clusterI][clusterC] = simC
			dictC[clusterI] = simC
			k2 , simMax = self.dictMax[clusterI]
			if k2 == clusterA or k2 == clusterB:
				k , simMax = max( dictClusterSim[clusterI].items() , key=lambda x:x[1] )
				self.dictMax[clusterI] = (k,simMax)
		dictClusterSim[clusterC] = dictC
		k2 , simMax = max( dictClusterSim[clusterC].items() , key=lambda x:x[1] )
		self.dictMax[clusterC] = (k2,simMax)
		return dictClusterSim
		
	def Hierarchical( self , minThreshold , dictClusterSim , enum ):
		dictClusterSim = self.Init( dictClusterSim )
		while True:
			maxSim , clusterA , clusterB = self._FindMax( dictClusterSim )
			if maxSim < minThreshold:
				break
			# print( str(maxSim) )
			# print( (','.join([enum.GetKey(i) for i in [ int(k.strip()) for k in clusterA.split('|') if len(k.strip()) > 0 ]])).encode('utf-8') )
			# print( (','.join([enum.GetKey(i) for i in [ int(k.strip()) for k in clusterB.split('|') if len(k.strip()) > 0 ]])).encode('utf-8') )
			print( (clusterA+'+'+clusterB).encode('utf-8') )
			dictClusterSim = self._MergeCluster( dictClusterSim , clusterA , clusterB )
		return dictClusterSim

class CTest(unittest.TestCase):
	"""docstring for CTest"""
	def setUp( self ):
		self.dictSim = {
		    ('a',)  :   {
		        ('b',)  :   1.0,
		        ('c',)  :   0.5,
		    },
		    ('b',)  :   {
		        ('a',)  :   1.0,
		        ('c',)  :   0.5,
		    },
		    ('c',)  :   {
		        ('a',)  :   0.5,
		        ('b',)  :   0.5,
		    },
		}
		self.hc = CHC()

	def tearDown( self ):
		pass

	# def test_Init( self ):
	# 	listArgs = [
	# 		({
	# 		    ('a',)  :   {
	# 		        ('b',)  :   1.0,
	# 		    },
	# 		    ('b',)  :   {
	# 		        ('a',)  :   1.0,
	# 		        ('c',)  :   0.5,
	# 		    },
	# 		},),
	# 	]
	# 	listAnswers = [
	# 		{
	# 		    ('a',)  :   {
	# 		        ('b',)  :   1.0,
	# 		        ('c',)  :   0.0,
	# 		    },
	# 		    ('b',)  :   {
	# 		        ('a',)  :   1.0,
	# 		        ('c',)  :   0.5,
	# 		    },
	# 		    ('c',)  :   {
	# 		        ('a',)  :   0.0,
	# 		        ('b',)  :   0.0,
	# 		    },
	# 		},
	# 	]
	# 	for arg,answer in zip(listArgs,listAnswers):
	# 		self.assertEqual( self.hc.Init(*arg) , answer )

	def test_FindMax( self ):
		self.assertEqual( (1.0,('a',),('b',)) , self.hc.FindMax(self.dictSim) )

	def test_MergeCluster( self ):
		dictAnswer = {
		    ('a','b')   :   {
		        ('c',)  :   0.5,
		    },
		    ('c',)  :   {
		        ('a','b')   :   0.5,
		    },
		}
		self.assertEqual( dictAnswer , self.hc.MergeCluster(self.dictSim,('a',),('b',)) )

	def test_Hierarchical( self ):
		listArgs = [
			( 0.6 , self.dictSim ),
		]
		listAnswers = [
			{
				('a','b') : { ('c',) : 0.5 },
				('c',) : { ('a','b') : 0.5 },
			},
		]
		for arg,answer in zip(listArgs,listAnswers):
			self.assertEqual( self.hc.Hierarchical(*arg) , answer )

def main():
	pass

if __name__ == '__main__':
	# main()
	unittest.main()