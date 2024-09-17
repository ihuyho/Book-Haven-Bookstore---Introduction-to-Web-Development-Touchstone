def findCoordinate():
  col = int(input('Choose your column from 1-3: '))
  while col < 1 or col > 3:
    print('Please pick a number between 1 and 3. Try again below.')
    col = int(input('Choose your column from 1-3: '))
  row = int(input('Choose your row from 1-3: '))
  while row < 1 or row > 3:
    print('Please pick a number between 1 and 3. Try again below.')
    row = int(input('Choose your row from 1-3: '))
  col -= 1
  row -= 1
  while board[row][col] == 'x' or board[row][col] == 'o':
    print('Try again!')
    col = int(input('Choose your column from 1-3: '))
    while col < 1 or col > 3:
      print('Please pick a number between 1 and 3. Try again below.')
      col = int(input('Choose your column from 1-3: '))
    row = int(input('Choose your row from 1-3: '))
    while row < 1 or row > 3:
      print('Please pick a number between 1 and 3. Try again below.')
      row = int(input('Choose your row from 1-3: '))
    col -= 1
    row -= 1
  return col, row

def playTurn(row, col, squares_left, player_turn):
  if board[row][col] == '-':
    board[row][col] = player_turn
    squares_left -= 1
  return squares_left

def swap(player_turn):
  if player_turn == 'x':
    player_turn = 'o'
  else:
    player_turn = 'x' 
  return player_turn

def printBoard(board):
  print('\n')
  print('\t     |     |')
  print('\t  {}  |  {}  |  {}'.format(board[0][0], board[0][1], board[0][2]))
  print('\t_____|_____|_____')

  print('\t     |     |')
  print('\t  {}  |  {}  |  {}'.format(board[1][0], board[1][1], board[1][2]))
  print('\t_____|_____|_____')

  print('\t     |     |')
  print('\t  {}  |  {}  |  {}'.format(board[2][0], board[2][1], board[2][2]))
  print('\t     |     |')
  print('\n')
  
def checkWinner(player_turn):
  for row in range(0,3):
    if board[row][0] == player_turn and board[row][1] == player_turn and board[row][2] == player_turn:
      print('{} is the winner.'.format(player_turn.capitalize()))
      player_turn = 'winner'
      return player_turn
    
  for col in range(0,3):
    if board[0][col] == player_turn and board[1][col] == player_turn and board[2][col] == player_turn:
      print('{} is the winner.'.format(player_turn.capitalize()))
      player_turn = 'winner'
      return player_turn
  
  if board[0][0] == player_turn and board[1][1] == player_turn and board[2][2] == player_turn:
    print('{} is the winner.'.format(player_turn.capitalize()))
    player_turn = 'winner'
    return player_turn
  elif board[0][2] == player_turn and board[1][1] == player_turn and board[2][0] == player_turn:
    print('{} is the winner.'.format(player_turn.capitalize()))
    player_turn = 'winner'
    return player_turn
  return player_turn

squares_left = 9
player_turn = 'x'
board = [['-','-','-'],
         ['-','-','-'],
         ['-','-','-']]
x = ''
o = ''
col = 0
row = 0

printBoard(board)

while squares_left > 0:

  coordinates = findCoordinate()

  col = coordinates[0]
  row = coordinates[1]

  squares_left = playTurn(row, col, squares_left, player_turn)

  
  printBoard(board)

  #check for winner
  player_turn = checkWinner(player_turn)

  if player_turn == 'winner':
    break

  #swapping the players
  player_turn = swap(player_turn)


  print('Next round.')
print('Game over!')

