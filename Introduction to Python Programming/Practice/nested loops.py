multiples = []
for outerList in range(1,11): #do this 10 times basically
    multiples.append([]) #append the multiples list with an inner list
    for innerList in range (1,11): #do that 10 times, so 10 list, within those 10 list are 10 values
        multiples[outerList - 1].append(outerList * innerList)

for outerList in multiples:
    for values in outerList:
        print(values,' ', end = '')
    print()
 
