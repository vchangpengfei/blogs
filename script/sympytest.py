# -*- coding: utf-8 -*
#!/usr/bin/env python

from sympy import *

x = symbols('x')
X = solve(x**2-5*x+6,x)
print('一元二次方的两个根为:',X)

A=set('12345')
B=set('23')
print('集合A与B的并:',A|B)
print('集合A与B的交:',A&B)
print('集合A与B的差:',A-B)
print('集合A与B的差:',B-A)