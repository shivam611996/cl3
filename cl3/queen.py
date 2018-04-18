'''

NOTE: there are two codes in this one for takeing input from the matrix and one for simple input like columm no where to place the 1at queen.
you can run any one you want
but make sure you have created "input.json" for 1st code and "input1.json" for 2nd code.

how to execute this code

1) go to that folder in which the program is stored using cd folder_name

2) open terminal ("ctrl+alt+t")

3) type the folloing commands to run this program
    "python B1_8_queens.py"
    and hit enter
    

'''


import json     # for taking json input.

def isattack(board,r,c):
    for i in range(r):      #checks the column c for all rows r present
        if(board[i][c]==1):
            return True
    
    i=r-1
    j=c-1
    while((i>=0) and (j>=0)):       #checks upper left diagonal
        if(board[i][j]==1):
            return True
        i=i-1
        j=j-1
    
    i=r-1
    j=c+1
    while((i>=0) and (j<8)):    # checks upper right diagonal
        if(board[i][j]==1):
            return True
        i=i-1
        j=j+1
    return False
    
def solve(board,row):
    i=0
    while(i<8):
        if(not isattack(board, row, i)):
            board[row][i]=1
            if(row==7):     # checks all 8 row are completed or not
                return True
            else:
                if(solve(board, row+1)):         #if board is partially filled then row=row+1
                    return True
                else:
                    board[row][i]=0     #backtracking and reseting board=0
                    
        i=i+1
    
    if(i==8):
       return False
    
def printboard(board):
    for i in range(8):
        for j in range(8):
            print "\t"+str(board[i][j])+"  ",
        print "\n"
        
board = [[0 for x in range(8)] for x in range(8)]   #8 by 8 zero matrix
tc=0
filename=""
if __name__ == '__main__':  #main function started.
  while(tc!=3):
    data=[]
    
    if(tc==0):
	print"POSITIVE TESTING..\n TEST CASE 1: OUTPUT"
	filename="inp1.json"
    else:
		if(tc==1):
			print"\nNEGATIVE TESTING..\n TEST CASE 2: OUTPUT"
			filename="inp2.json"
		else:
			print"\nNEGATIVE TESTING..\n TEST CASE 3: OUTPUT"
			filename="inp3.json"
    tc=tc+1 
  
    with open(filename) as f:
        data=json.load(f)   # loads will load the file as string. but load will load the file as it is 
    
    if(data["start"]<0 or data["start"]>7):
        print "\tInvalid JSON input"
        exit()
    
    board[0][data["start"]]=1   #Always fixing the queen in first row only
    if(solve(board, 1)):
        print "\tQueens problem solved!!!"
        print "\tBoard Configuration:"
        printboard(board)
    else:  #dead block of code
        print "\tQueens problem not solved!!!"
 
   
'''
filename: input.json
{"start":4}


OUTPUT

Amols-Air:b1 Darwin$ python solve.py
POSITIVE TESTING..
 TEST CASE 1: OUTPUT
	Queens problem solved!!!
	Board Configuration:
	0   	0   	0   	0   	1   	0   	0   	0   

	1   	0   	0   	0   	0   	0   	0   	0   

	0   	0   	0   	1   	0   	0   	0   	0   

	0   	0   	0   	0   	0   	1   	0   	0   

	0   	0   	0   	0   	0   	0   	0   	1   

	0   	1   	0   	0   	0   	0   	0   	0   

	0   	0   	0   	0   	0   	0   	1   	0   

	0   	0   	1   	0   	0   	0   	0   	0   


NEGATIVE TESTING..
 TEST CASE 2: OUTPUT
	Queens problem not solved!!!

NEGATIVE TESTING..
 TEST CASE 3: OUTPUT
	Invalid JSON input

 
inp1.json:{"start":2}
inp1.json:{"start":5}
inp1.json:{"start":9}


#New program:
'''


import json         # for taking json input.
inf=open("input1.json")
board=json.loads(inf.read())
board=board["matrix"]
print (board)

def issafe(row,col):
	for i in range (8):
		for j in range (8):
			if(board[i][j]==1):    
				if(row==i):					# checking of rows.
					return False
				if(col==j):   				# checking of cols.
					return False
				if(abs(row-i)==abs(col-j)): # checking of diagonal.
					return False
	return True

def place(col):
	if(col>=8):
		return True
		print("completed")
	for i in range (8):
		if(issafe(i,col)):
			board[i][col]=1
			if(place(col+1)==True):
				return True
			board[i][col]=0
	return False

if(place(1)==True):
	print("solution found")
else:
	print("solution not found")
print (board)


'''
input file:

{ "matrix": [

		[0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0],
		[1,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0] ]}
		
output:

[[0, 0, 0, 0, 0, 0, 0, 0], 
 [0, 0, 0, 0, 0, 0, 0, 0], 
 [0, 0, 0, 0, 0, 0, 0, 0], 
 [1, 0, 0, 0, 0, 0, 0, 0], 
 [0, 0, 0, 0, 0, 0, 0, 0], 
 [0, 0, 0, 0, 0, 0, 0, 0], 
 [0, 0, 0, 0, 0, 0, 0, 0], 
 [0, 0, 0, 0, 0, 0, 0, 0]]
solution found
[[0, 1, 0, 0, 0, 0, 0, 0], 
 [0, 0, 0, 0, 1, 0, 0, 0], 
 [0, 0, 0, 0, 0, 0, 1, 0], 
 [1, 0, 0, 0, 0, 0, 0, 0], 
 [0, 0, 1, 0, 0, 0, 0, 0], 
 [0, 0, 0, 0, 0, 0, 0, 1], 
 [0, 0, 0, 0, 0, 1, 0, 0], 
 [0, 0, 0, 1, 0, 0, 0, 0]]



'''


