
import java.util.Random;
import java.util.Scanner;

public class TicTac_CoreCode {

	 public static void printBoad(char[][] board) 
     {
             System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
             System.out.println("-+-+-");
             System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
             System.out.println("-+-+-");
             System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
     }
	 
	 private static void playerTurn(char [][] board, Scanner scanner)
     {
             String userInput;
             while(true)
             {
             System.out.println("! Would you like to paly (1-9)");
             userInput = scanner.nextLine(); // String value
             if(isValidMove(board, Integer.parseInt(userInput)))
             {
                     break;
             }
             else{
                     System.out.println(userInput+" is not valid move ");
             }
     }
             placeMove(board, userInput, 'X');
             
     }
	 
	 private static void placeMove(char[][] board, String position, char symbol) 
     {
             
             switch (position) 
             {
                     case "1":
                             board[0][0] = symbol;
                             break;
                     case "2":
                             board[0][1] = symbol;
                             break;
                     case "3":
                             board[0][2] = symbol;
                             break;
                     case "4":
                             board[1][0] = symbol;
                             break;
                     case "5":
                             board[1][1] = symbol;
                             break;
                     case "6":
                             board[1][2] = symbol;
                             break;
                     case "7":
                             board[2][0] = symbol;
                             break;
                     case "8":
                             board[2][1] = symbol;
                             break;
                     case "9":
                             board[2][2] = symbol;
                             break;

             }
     }
	 
	 private static boolean isValidMove(char [][] board, int position)
     {
             switch (position) 
             {
                     case 1: return(board[0][0] == ' ');
                             // if(board[0][0] == ' ')
                             // {
                             //         return true;
                             // }
                             // else
                             // {
                             //         return false;
                             // }
                             
                     case 2:
                             return(board[0][1] == ' '); // it will return true if value of statement is true
                     case 3:
                             return(board[0][2] == ' ');
                     case 4:
                             return(board[1][0] == ' ');
                     case 5:
                             return(board[1][1] == ' ');
                     case 6:
                             return(board[1][2] == ' ');
                     case 7:
                             return(board[2][0] == ' ');
                     case 8:
                             return(board[2][1] == ' ');
                     case 9:
                             return(board[2][2] == ' ');
                     default: 
                             return false;
             }

     }
	 
	 private static void computerTurn(char[][] board) {
         Random rand = new Random(); // For the random number use Random() 
         int computerMove;

         while(true)
         {
                 computerMove = rand.nextInt(9)+1; // rand.netInt(9) gives 1-8 num. so add +1
                 if(isValidMove(board, computerMove))
                 {
                         break;
                 }
         }
         
         System.out.println("computer chose"+ computerMove);
         placeMove(board, Integer.toString(computerMove), 'O'); // Interger.toString -> typecasting int to string
 }

	 private static boolean isGameFinished(char [][]board)
     {
             if(hasConstantWon(board, 'X'))
             {
                     printBoad(board);
                     System.out.println("Plyer Wins!");
                     return true;
             }
             
             if(hasConstantWon(board, 'O'))
             {
                     printBoad(board);
                     System.out.println("Computer Wins!");
                     return true; // Game end
             }
             
             for(int i=0;i<board.length;i++)
             {
                     for(int j=0;j<board.length;j++)
                     {
                             if(board[i][j] == ' ')
                             {
                                     return false;
                             }
                     }
             }
             printBoad(board);
             System.out.println("The game endded in a tie !");
             return true;
     }

     private static boolean hasConstantWon(char [][] board, char symbol)
     {
             if((board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
             (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
             (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||
             
             (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
             (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
             (board[2][0] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||
             
             (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
             (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) )
             {
                     
                     return true; // Game end
             }
             return false;
     }

	 
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        char[][] board = { { ' ', ' ', ' ', },
                        { ' ', ' ', ' ', },
                        { ' ', ' ', ' ', } };

        printBoad(board);
        
        while(true)
        {
                playerTurn(board, scanner);
                if(isGameFinished(board))
                {
                        break;
                }
                printBoad(board);
                computerTurn(board);
                printBoad(board);
        }
	}

}
