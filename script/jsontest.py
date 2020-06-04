import json

weird_json = '{"x": 1, "y": 2, "z": 3}'
tt=json.loads(weird_json)

for i in tt:
    print(i+str(tt[i]))

weird_json_arr = '[1,2,3]'
tt=json.loads(weird_json_arr)
for i in tt:
    print(i)