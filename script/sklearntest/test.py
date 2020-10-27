from sklearn import linear_model
reg = linear_model.LinearRegression()
print(reg)
reg.fit ([[0, 0], [1, 1], [2, 2]], [0, 1, 2])
reg.coef_
print(reg.coef_)
