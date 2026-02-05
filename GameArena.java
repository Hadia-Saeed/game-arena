import java.io.*;
import java.util.*;
public class GameArena {
    static Scanner sc = new Scanner(System.in);

    public static boolean checkWin(String[][] symbol, String current_symbol, String current_player, int count){
        //checking rows
        if(symbol[0][0].equals(current_symbol) && symbol[0][1].equals(current_symbol) && symbol[0][2].equals(current_symbol)){
                System.out.println("\n" + current_player + " wins!");
                return true;
            }
            if(symbol[1][0].equals(current_symbol) && symbol[1][1].equals(current_symbol) && symbol[1][2].equals(current_symbol)){
                System.out.println("\n" + current_player + " wins!");
                return true;
            }
            if(symbol[2][0].equals(current_symbol) && symbol[2][1].equals(current_symbol) && symbol[2][2].equals(current_symbol)){
                System.out.println("\n" + current_player + " wins!");
                return true;
            }
                //checking coloumns
            if(symbol[0][0].equals(current_symbol) && symbol[1][0].equals(current_symbol) && symbol[2][0].equals(current_symbol)){
                    System.out.println("\n" + current_player + " wins!");
                    return true;
                }
            if(symbol[0][1].equals(current_symbol) && symbol[1][1].equals(current_symbol) && symbol[2][1].equals(current_symbol)){
                    System.out.println("\n" + current_player + " wins!");
                    return true;
                }
            if(symbol[0][2].equals(current_symbol) && symbol[1][2].equals(current_symbol) && symbol[2][2].equals(current_symbol)){
                    System.out.println("\n" + current_player + " wins!");
                    return true;
                }
            //checking diagnols
             if(symbol[0][0].equals(current_symbol) && symbol[1][1].equals(current_symbol) && symbol[2][2].equals(current_symbol)){
                    System.out.println("\n" + current_player + " wins!");
                    return true;
                }
             if(symbol[0][2].equals(current_symbol) && symbol[1][1].equals(current_symbol) && symbol[2][0].equals(current_symbol)){
                    System.out.println("\n"+ current_player + " wins!");
                    return true;
                }
            //checking for a draw
            if(count == 9){
                System.out.println("\nThe game is a draw!");
                return true;
            }
            return false;
    }

    public static void playerMove(String current_player, String current_symbol, int[][] grid, String[][] symbol, boolean[][] found){

            while(true){
               int row = -1, col = -1;
            try{
            System.out.print(current_player + ",select positions 1-9: "); 
            int num1 = sc.nextInt();
             if(num1>9 || num1<1){
                System.out.println("\nSelected number outside grid range. Please try again!");
                continue;
            }
            for (int i = 0; i < 3; i++){ 
                for (int j = 0; j < 3; j++){ 
                    if(grid[i][j] == num1){
                        row = i;
                        col = j;
                    }           
                }
            }
            if(found[row][col]){
                System.out.println("This position has already been taken! Try again");
                continue;
            }
            symbol[row][col] = current_symbol;
            found[row][col] = true;
            break;
        }catch(InputMismatchException e){
            System.out.println("\nYou have entered an invalid input type");
            System.out.println("Try Again");
            sc.nextLine();
        }
    }
}

     public static void compMove(String[][] symbol, boolean[][] found, String symbol1, String symbol2, int[][] grid){

         int row = -1, col = -1, row2 = -1, col2 = -1, count2 = 0, diag = -1;

         //for comp to make a winning move
         //rows
         for(int i = 0; i<3 && row==-1; i++){  //i = row
            count2 = 0; col2 = -1;
            for(int j = 0; j<3; j++){      //j = col
                if(symbol[i][j].equals(symbol2)){  
                    count2++;
                }else if(!found[i][j]){
                    col2 = j;
                }
            }
            if(count2 == 2 && col2 != -1 ){
                row = i;
                col = col2;
            }
         }
         //coloumns
         for(int i = 0; i<3 && row==-1; i++){ //i = col
            count2 = 0; row2 = -1;
            for(int j = 0; j<3; j++){   //j = row
                if(symbol[j][i].equals(symbol2)){
                    count2++;
                }else if(!found[j][i]){
                    row2 = j;
                }
            }
            if(count2 == 2 && row2 != -1 ){
                row = row2;
                col = i;
            }
         }
         //diagonal 1
         count2 = 0; diag = -1;
         for(int i = 0; i<3 && row==-1; i++){
            if(symbol[i][i].equals(symbol2)){
                    count2++;
               }else if(!found[i][i]){
                    diag = i;
                }
         }
           if(count2 == 2 && diag != -1 ){
                row = diag;
                col = diag;
            }
         // diagonal 2
         count2 = 0; diag = -1;
         for(int i = 0; i<3 && row==-1; i++){
            if(symbol[i][2-i].equals(symbol2)){
                    count2++;
               }else if(!found[i][2-i]){
                    diag = i;
                }
         }
         if(count2 == 2 && diag != -1 ){
                row = diag;
                col = 2-diag;
            }
         //for comp to make a blocking move, preventing player win
         //rows
         for(int i = 0; i<3 && row==-1; i++){  //i = row
            count2 = 0; col2 = -1;
            for(int j = 0; j<3; j++){      //j = col
                if(symbol[i][j].equals(symbol1)){  
                    count2++;
                }else if(!found[i][j]){
                    col2 = j;
                }
            }
            if(count2 == 2 && col2 != -1 ){
                row = i;
                col = col2;
            }
         }
         //coloumns
         for(int i = 0; i<3 && row==-1; i++){ //i = col
            count2 = 0; row2 = -1;
            for(int j = 0; j<3; j++){   //j = row
                if(symbol[j][i].equals(symbol1)){
                    count2++;
                }else if(!found[j][i]){
                    row2 = j;
                }
            }
            if(count2 == 2 && row2 != -1 ){
                row = row2;
                col = i;
            }
         }
         //diagonal 1
         count2 = 0; diag = -1;
         for(int i = 0; i<3 && row==-1; i++){
            if(symbol[i][i].equals(symbol1)){
                    count2++;
               }else if(!found[i][i]){
                    diag = i;
                }
         }
         if(count2 == 2 && diag != -1 ){
                row = diag;
                col = diag;
            }
         // diagonal 2
         count2 = 0; diag = -1;
         for(int i = 0; i<3 && row==-1; i++){
            if(symbol[i][2-i].equals(symbol1)){
                    count2++;
               }else if(!found[i][2-i]){
                    diag = i;
                }
         }
         if(count2 == 2 && diag != -1 ){
                row = diag;
                col = 2-diag;
            }
        //for comp to prevent forks and threat win
        //if user has two corners, comp should take an edge
        if(row == -1 && found[1][1] && symbol[1][1].equals(symbol2)){ 
            if(symbol[0][0].equals(symbol1) && symbol[2][2].equals(symbol1) || symbol[0][2].equals(symbol1) && symbol[2][0].equals(symbol1)){
                 if(!found[0][1]){
                    row  = 0; 
                    col = 1;
               }else if(!found[1][0]){
                    row = 1; 
                    col = 0;
                }else if(!found[1][2]){
                    row = 1; 
                    col = 2;
                }else if(!found[2][1]){
                    row = 2; 
                   col = 1;
                }
          }
          //if user has one corner, comp should take opposite corner
          //corner 1
          if(symbol[0][0].equals(symbol1)){  
            if(!found[2][2]){
                    row  = 2; 
                    col = 2;
               }
          }else if(symbol[2][2].equals(symbol1)){
            if(!found[0][0]){
                    row  = 0; 
                    col = 0;
               }
          }
          //corner 2
          if(symbol[0][2].equals(symbol1)){  
            if(!found[2][0]){
                    row  = 2; 
                    col = 0;
               }
          }else if(symbol[2][0].equals(symbol1)){
            if(!found[0][2]){
                    row  = 0; 
                    col = 2;
               }
          }
          // one fork condition
        if(symbol[2][1].equals(symbol1) && symbol[1][2].equals(symbol1)){
                if(!found[2][2]){
                row = 2;
                col = 2;
            }
            }   
       }
        //for comp to take the centre position if available
        if(row == -1 && !found[1][1]){
            row = 1;
            col = 1;
        }
        //for comp to take corners if available
        if(row == -1 && !found[0][0]){
            row  = 0;
            col = 0;
        }else if(row == -1 && !found[0][2]){
            row = 0;
            col = 2;
        }else if(row == -1 && !found[2][0]){
            row = 2;
            col = 0;
        }else if(row == -1 && !found[2][2]){
            row = 2;
            col = 2;
        }
        //for comp to take edges if available
       if(row == -1 && !found[0][1]){
           row  = 0;
           col = 1;
       }else if(row == -1 && !found[1][0]){
            row = 1;
            col = 0;
       }else if(row == -1 && !found[1][2]){
            row = 1;
            col = 2;
        }else if(row == -1 && !found[2][1]){
            row = 2;
            col = 1;
        }
       //comp final position
        if(row != -1 && col != -1){
        symbol[row][col] = symbol2;
        found[row][col] = true;
        System.out.println("\nComputer plays on " + grid[row][col]);
        }
    }

     public static void printGrid(int[][] grid,String[][] symbol, boolean[][] found){
        System.out.println("\n====== GAME GRID ======");
         //top border
        for (int i = 0; i<grid.length; i++){
            System.out.print("-------");
        }
        System.out.println();

        // grid 
        for (int i = 0; i<grid.length; i++){
            for (int j = 0; j<grid[i].length; j++){
                 if (found[i][j]){
                System.out.printf(" | %s  ", symbol[i][j]);  
            }else{
                System.out.printf(" | %2d ", grid[i][j]);
            } 
        }
        System.out.println("|");

        //bottom border
        for (int j = 0; j<grid.length; j++){
            System.out.print("-------");
        }
        System.out.println();
        }
    }

    public static void tictac(){
         System.out.print("\n\t====== WELCOME TO TIC TAC GAME ======\n");

        while(true){
        System.out.println("\n Choose from the following game modes:-\n i.For multiplayer player mode press 1\n ii.For single player mode press 2\n iii.To exit press any other key");
        System.out.print(" Your choice: ");
        char choice = sc.next().charAt(0);
        sc.nextLine();
       
        int count = 0;
        String name1, name2, symbol1, symbol2, current_player = "", current_symbol = "";
        int grid[][] = new int[3][3]; //array to display numbers
        String symbol[][] = new String[3][3]; //array to manipulate symbols
        boolean found[][] = new boolean[3][3]; //array to keep track of revealed boxes

        //loop to store numbers in grid array
        int k = 1; 
        for(int i = 0; i<grid.length; i++){ 
            for(int j = 0; j<grid[i].length; j++){
                grid[i][j] = k++;
            }
        }
        //intializing symbol array
        for(int i = 0; i<symbol.length; i++){
            for(int j = 0; j<symbol[i].length; j++){
                symbol[i][j] = "";
            }
        }

        if(choice=='1'){
        // for option 1
        System.out.print("Player 1, please enter your name: "); 
        name1 = sc.nextLine();
        while(true){
        System.out.print("\nPlayer 1, choose X or O: "); 
        symbol1 = sc.nextLine();
        symbol1 = symbol1.toUpperCase();
        if(!symbol1.equals("X") && !symbol1.equals("O")){
            System.out.println("Please choose only X or O");
            continue;
        }else{
            break;
        }
    }
         if(symbol1.equals("X")){
            symbol2 = "O";
        }else{
            symbol2 = "X";
        }
        System.out.print("\nPlayer 2, please enter your name: "); 
        name2 = sc.nextLine();
        System.out.print("Player 2, your symbol is " + symbol2);

        char choice2;
        while(true){
            //resetting arrays
            k = 1;
            count = 0;
             for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++){
                        grid[i][j] = k++;
                        symbol[i][j] = "";
                        found[i][j] = false;
                    }
        //the toss
        int toss = (int)(Math.random()*2); //0-heads, 1-tails
        int toss2;
        while(true){
        try{
        System.out.print("\nPlayer 2, select Heads(0) or Tails(1): ");
        toss2 = sc.nextInt();  
        sc.nextLine();
        if(toss2 != 1 && toss2 != 0){
            System.out.println("Please choose only 0 (for heads) or 1 (for tails)");
            continue;
        }else{
            break;
        }
        }catch(InputMismatchException e) {
            System.out.println("Invalid input! Please try again");
            sc.nextLine(); 
            continue;
        }
    }
        if(toss2 == toss){
            System.out.println("Player 2 has won the toss! Player 2 goes first");
            current_player = name2;
            current_symbol = symbol2;

        }else{
            System.out.println("Player 1 has won the toss! Player 1 goes first");
            current_player = name1;
            current_symbol = symbol1;
        }
        //game loop
        while(true){
            playerMove(current_player, current_symbol, grid, symbol, found);
            count++;
            printGrid(grid, symbol, found);

            //checking wins or draws
            if(checkWin(symbol, current_symbol, current_player, count)){
                break;
            }

            //switching players
            if(current_player.equals(name1) && current_symbol.equals(symbol1)){
                current_player = name2;
                current_symbol = symbol2;
            }else{
                current_player = name1;
                current_symbol = symbol1;
            }
        }
        System.out.print("Do you want to play again? (Y/N):");
        choice2 = sc.next().charAt(0);
        if(choice2 == 'Y' || choice2 == 'y'){
            continue;
        }else{
            break;
        }
    }
    continue;
    }else if(choice == '2'){
        //for option 2
        System.out.print("Player, please enter your name: "); 
        name1 = sc.nextLine();
        while(true){
        System.out.print("Player, choose X or O: "); 
        symbol1 = sc.nextLine();
        symbol1 = symbol1.toUpperCase();
         if(!symbol1.equals("X") && !symbol1.equals("O")){
            System.out.println("Please choose only X or O");
            continue;
        }else{
            break;
        }
    }
        name2 = "Computer";
         if(symbol1.equals("X")){
            symbol2 = "O";
        }else{
            symbol2 = "X";
        }
        System.out.print("Computer's symbol is " + symbol2);

        char choice2;
        while(true){
             //resetting arrays
            k = 1;
            count = 0;
             for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++){
                        grid[i][j] = k++;
                        symbol[i][j] = "";
                        found[i][j] = false;
                    }

        //the toss
        int toss3 = (int)(Math.random()*2); //0-heads, 1-tails
        int toss4;
        while(true){
        try{
        System.out.print("\nPlayer, select Heads(0) or Tails(1): ");
        toss4 = sc.nextInt(); 
        sc.nextLine();
        if(toss4 != 1 && toss4 != 0){
            System.out.println("Please choose only 0 (for heads) or 1 (for tails)");
            continue;
        }else{
            break;
        }
        }catch(InputMismatchException e) {
            System.out.println("Invalid input! Please try again");
            sc.nextLine(); 
            continue;
        }
    }
        if(toss4 == toss3){
            System.out.println("\nPlayer has won the toss! Player goes first");
            current_player = name1;
            current_symbol = symbol1;
        }else{
            System.out.println("\nComputer has won the toss! Computer goes first");
            current_player = name2;
            current_symbol = symbol2;
        }

        //game loop
        while(true){

            if(current_player.equals(name2)){
                compMove(symbol, found, symbol1, symbol2, grid);
                printGrid(grid, symbol, found);
            }else{
                playerMove(current_player, current_symbol, grid, symbol, found);
                printGrid(grid, symbol, found);
            }
            count++;
            
            //check wins
            if(checkWin(symbol, current_symbol, current_player, count)){
                break;
            }

             //switching players
            if(current_player.equals(name1) && current_symbol.equals(symbol1)){
                current_player = name2;
                current_symbol = symbol2;
            }else{
                current_player = name1;
                current_symbol = symbol1;
            }
        }
        System.out.print("Do you want to play again? (Y/N):");
        choice2 = sc.next().charAt(0);
        if(choice2 == 'Y' || choice2 == 'y'){
            continue;
        }else{
            break;
        }
    }
    continue;
    }else{
        System.out.println("Thank you for playing!\nExiting. Goodbye!");
        break;
    }
}
      }

    public static void wordscramble(){
         System.out.print("\n\t====== WELCOME TO WORD SCRAMBLE ======\n");
        //choosing difficulty level
        int time = 0;
       while(true){
        try{
        System.out.print("\n Choose difficulty level from the following:-\n 1.Easy (9s)\n 2.Medium (6s)\n 3.Hard (3s) ");
        System.out.print("\n Your choice: ");
        int choice2 = sc.nextInt();
        sc.nextLine();
        switch (choice2){
            case 1:
                time = 9;
                break;
            case 2:
                time = 6;
                break;
            case 3:
                time = 3;
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
        break;
    } catch(InputMismatchException e){
        System.out.println("Invalid input! Please enter an integer from the given options");
        sc.nextLine();
    }
}
//choosing category
    int choice;
    String file = "";
        while(true){
        try{
        System.out.print("\n Choose from the following categories:-\n 1.Country\n 2.Fruit\n 3.City\n 4.Mixed");
        System.out.print("\n Your choice: ");
        choice = sc.nextInt();
        sc.nextLine();
        switch (choice){
            case 1:
                file = "country.txt";
                break;
            case 2:
                file = "fruit.txt";
                break;
            case 3:
                file = "city.txt";
                break;
            case 4:
                break;
            default:
                System.out.print("Invalid input!");
                break;
        }
        break;
    } catch(InputMismatchException e){
        System.out.println("Invalid input! Please enter an integer from the given options");
        sc.nextLine();
    }
}
        //game loop
        int score = 0;
        for(int i = 1; i<=5; i++){  
            if(choice==4){
                int rand_file = (int)((Math.random()*3)+1); //for random file
                switch(rand_file){
                    case 1:
                        file = "country.txt";
                        break;
                    case 2:
                        file = "fruit.txt";
                        break;
                    case 3:
                        file = "city.txt";
                        break;
                    }

            }
        //reading random word based on chosen file
        String word = "";
       while(true){
        try{
        FileInputStream fis = new FileInputStream(file);
        Scanner inp = new Scanner(fis);
        int rand = (int)((Math.random()*10)+1);
        for(int k = 1; k<=rand; k++){
            word = inp.nextLine();
        }
        inp.close();
        fis.close();
        break;
        } catch(FileNotFoundException e){
        System.out.println("An error has occurred");
    } catch(IOException e){
        System.out.println("An error has occurred");
      }
   }
        //shuffling for scrambled word
        String scramble = "";
        while(true){
        scramble = "";
        String word2 = word;
        String temp = "";
        for(int k = 0; k<word.length(); k++){
            int rand_index = (int)(Math.random()*word2.length());
            scramble += word2.charAt(rand_index);
            temp = "";
            for(int j = 0; j<word2.length(); j++){
                if(j != rand_index){
                    temp += word2.charAt(j);
                }
            }
            word2 = temp;
        }
        if(scramble.equals(word)){
            continue;
        }else{
            break;
        }
    }
        //game input from user
        System.out.printf("\n\n=== ROUND %d === \n",i);
        System.out.print("Scrambled Word: " + scramble); 
        System.out.print("\nYour guess: ");
        long startTime = System.currentTimeMillis();
        String guess = sc.nextLine();
        guess = guess.toUpperCase();
        long endTime = System.currentTimeMillis();
        long execution_time = (endTime - startTime) / 1000;
        if(guess.equals(word) && execution_time <= time){
            System.out.print("Correct! +1 point");
            score++;
        }else if(execution_time > time){
            System.out.println("Time's up! The correct word was: " + word);
        }
        else{
            System.out.println("Incorrect! The correct word was: " + word);
        }
    }
    System.out.println("\n\nFinal Score: " + score + " out of 5" +"\nThanks for playing!");
}

    public static void printGrid(int[][] grid, String[][] emojis, boolean[][] found, int size){

    System.out.println("\n====== MEMORY GRID ======");

    //top border
    for (int i = 0; i<size; i++){
        System.out.print("-------");
    }
    System.out.println();

    // grid 
    for (int i = 0; i<size; i++){
        for (int j = 0; j<size; j++){
            if (found[i][j]){
                System.out.printf(" | %s  ", emojis[i][j]);  
            } else{
                System.out.printf(" | %2d ", grid[i][j]);   
            }
        }
        System.out.println("|");
        //bottom border
         for (int j = 0; j<size; j++){
            System.out.print("-------");
        }
        System.out.println();
    }
}

    public static void memorymatch(){
        System.out.print("\n====== WELCOME TO MEMORY MATCH GAME ======");
        String emojis[] = { "\uD83D\uDE00","\uD83D\uDE01","\uD83D\uDE02","\uD83D\uDE03","\uD83D\uDE04",
            "\uD83D\uDE05","\uD83D\uDE06","\uD83D\uDE07","\uD83D\uDE08","\uD83D\uDE09",
            "\uD83D\uDE0A","\uD83D\uDE0B","\uD83D\uDE0C","\uD83D\uDE0D","\uD83D\uDE0E",
            "\uD83D\uDE0F","\uD83D\uDE10","\uD83D\uDE11","\uD83D\uDE12","\uD83D\uDE13",
            "\uD83D\uDE14","\uD83D\uDE15","\uD83D\uDE16","\uD83D\uDE17","\uD83D\uDE18",
            "\uD83D\uDE19","\uD83D\uDE1A","\uD83D\uDE1B","\uD83D\uDE1C","\uD83D\uDE1D",
            "\uD83D\uDE1E","\uD83D\uDE1F","\uD83D\uDE20","\uD83D\uDE21","\uD83D\uDE22",
            "\uD83D\uDE23","\uD83D\uDE24","\uD83D\uDE25","\uD83D\uDE26","\uD83D\uDE27",
            "\uD83D\uDE28","\uD83D\uDE29","\uD83D\uDE2A","\uD83D\uDE2B","\uD83D\uDE2C",
            "\uD83D\uDE2D","\uD83D\uDE2E","\uD83D\uDE2F","\uD83D\uDE30","\uD83D\uDE31",
            "\uD83D\uDE32","\uD83D\uDE33"};

        //taking size input from user
        int size;
        while(true){
            System.out.print("\nEnter grid size for your play:");
            try{
                size = sc.nextInt();
                if(size <= 0){
                    System.out.println("\nGrid size must be greater than 0. Try again.");
                }else if(size % 2 != 0){
                    System.out.println("\nGrid size must be even. Try again.");
                } else if(size*size > 100){
                    System.out.println("\nGrid size too large. Max limit for size is 10");
                } else {
                    break; 
                    }
                }
                catch(InputMismatchException ex){
                System.out.println("\nYou have entered an invalid input type");
                System.out.println("Try Again");
                sc.nextLine();
            }
        }

        int total_tiles = size*size;
        int total_pairs = total_tiles/2;
        int found_count = 0;
        int inc_count = 0;

        int grid[][] = new int[size][size]; //array to display numbers
        int grid2[][] = new int[size][size]; //array jismein tiles flip hone wali experiments hongi
        String emoji3[][] = new String[size][size]; //array to manipulate emojis
        boolean found[][] = new boolean[size][size]; //array to keep track of revealed tiles

        //loop to store numbers in grid array
        int k = 1; 
        for(int i = 0; i<size; i++){ 
            for(int j = 0; j<size; j++){
                grid[i][j] = k++;
            }
        }
        //populating emoji3 array
            int count = 0;
            for(int i = 0; i<size; i++){
                for(int j =0 ; j<size; j++){
                emoji3[i][j] = emojis[count];
                count++;
                 if(count==total_pairs){
                    count = 0;
            }
        }
    }
    //shuffling emoji3 array
        for (int i = 0; i < emoji3.length; i++) {
           for (int j = 0; j < emoji3[i].length; j++) {
            int i1 = (int)(Math.random()*emoji3.length);
            int j1 = (int)(Math.random()*emoji3[i].length);
            String temp = emoji3[i][j];
            emoji3[i][j] = emoji3[i1][j1];
            emoji3[i1][j1] = temp;
        }
    }
    //populating grid2 with numbers first
          for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                grid2[i][j] = grid[i][j];
                found[i][j] = false;
            }
        }
    //game loop
        while(true){
            int row1 = -1, col1 = -1, row2 = -1, col2 = -1;
            //display grid
            printGrid(grid2, emoji3, found, size);
            //FLIP 1
            while(true){
            try{
                System.out.print("\nSelect number to flip: "); 
            int num1 = sc.nextInt();
            if(num1>total_tiles){
                System.out.println("\nSelected number outside grid range. Please try again!");
                continue;
            }
            for (int i = 0; i < size; i++){ 
                for (int j = 0; j < size; j++){ 
                    if(grid2[i][j] == num1){
                        row1 = i;
                        col1 = j;
                    }           
                }
            }
            if(found[row1][col1]){
                System.out.println("This tile has already been picked! Try again");
                continue;
            }else{
                break;
            }
        }
            catch(InputMismatchException ex){
                System.out.println("\nYou have entered an invalid input type");
                System.out.println("Try Again");
                sc.nextLine();
            }
        }
            found[row1][col1] = true;

            //display after 1st flip
            printGrid(grid2, emoji3, found, size);

            //FLIP 2
            while(true){
            try{
                System.out.print("\nSelect number to flip and match: "); 
            int num2 = sc.nextInt();
            if(num2>total_tiles){
                System.out.println("\nSelected number outside grid range. Please try again!");
                continue;
            }
            for (int i = 0; i < size; i++){ 
                for (int j = 0; j < size; j++){ 
                    if(grid2[i][j] == num2){
                        row2 = i;
                        col2 = j;
                    }           
                }
            }
            if(found[row2][col2]){
                System.out.println("This tile has already been picked! Try again");
                continue;
            }else{
                break;
            }
        }
        catch(InputMismatchException ex){
                System.out.println("\nYou have entered an invalid input type");
                System.out.println("Try Again");
                sc.nextLine();
            }
        }
            found[row2][col2] = true;
            //display after 2nd flip
            printGrid(grid2, emoji3, found, size);

            if(emoji3[row1][col1].equals(emoji3[row2][col2])){
                System.out.println("\nPair matched!");
                found_count++;
            } else {
                System.out.println("\nPair NOT matched");
                found[row1][col1] = false;
                found[row2][col2] = false;
                inc_count++;
            }
            //to end game after all tiles are revealed
           if(found_count == total_pairs){
            System.out.println("\nCongratulations! All tiles have been matched!");
            int score = (int)(((total_pairs*10-inc_count*3)/(double)(total_pairs*10))*100);
            System.out.println("Final score: " + score +"/100");
            break;
           }
        }
    }

     public static void hangingman(int incorrect) {
        switch (incorrect){
            case 0:
                System.out.println("  -------\n" + 
                                   " |/      |\n" + 
                                   " |      \n" + 
                                   " |      \n" + 
                                   " |      \n" + 
                                   " |      \n" + 
                                   " |      \n" + 
                                   "_|______" );
                break;
            case 1:
                System.out.println("  -------\n" + 
                                   " |/      |\n" + 
                                   " |      (_) \n" + 
                                   " |      \n" + 
                                   " |      \n" + 
                                   " |      \n" + 
                                   " |      \n" + 
                                   "_|______" );
                break;
            case 2:
                System.out.println("  -------\n" + 
                                   " |/      |\n" + 
                                   " |      (_) \n" + 
                                   " |       |\n" + 
                                   " |       |\n" + 
                                   " |      \n" + 
                                   " |      \n" + 
                                   "_|______" );
                break;
            case 3:
                System.out.println("  -------\n" + 
                                   " |/      |\n" + 
                                   " |      (_)\n" + 
                                   " |      \\|\n" + 
                                   " |       |\n" + 
                                   " |      \n" + 
                                   " |      \n" + 
                                   "_|______" );
                break;
            case 4:
                System.out.println("  -------\n" + 
                                   " |/      |\n" + 
                                   " |      (_)\n" + 
                                   " |      \\|/\n" + 
                                   " |       |\n" + 
                                   " |      \n" + 
                                   " |      \n" + 
                                   "_|______" );
                break;
            case 5:
                System.out.println("  -------\n" + 
                                   " |/      |\n" + 
                                   " |      (_)\n" + 
                                   " |      \\|/\n" + 
                                   " |       |\n" + 
                                   " |      /\n" + 
                                   " |      \n" + 
                                   "_|______" );
                break;
            case 6:
                System.out.println("  -------\n" + 
                                   " |/      |\n" + 
                                   " |      (_)\n" + 
                                   " |      \\|/\n" + 
                                   " |       |\n" + 
                                   " |      / \\\n" + 
                                   " |      \n" + 
                                   "_|______" );
                break;
            default:
                break;
        }
        
    }

    public static void hangman(){
        System.out.println("\t====== WELCOME TO HANGMAN ======");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        int score = 0;
        while(true){

         //reading random word based on chosen file
        String word = "";
       while(true){
        try{
        FileInputStream fis = new FileInputStream("input.txt");
        Scanner inp = new Scanner(fis);
        int rand = (int)((Math.random()*10)+1);
        for(int k = 1; k<=rand; k++){
            word = inp.nextLine();
        }
        inp.close();
        fis.close();
        break;
        }catch(FileNotFoundException e){
        System.out.println("An error has occurred");
    } catch(IOException e){
        System.out.println("An error has occurred");
      }
   }

        System.out.println("\nThe Mystery Word has " + word.length() + " letters\n");
        char blanks[] = new char[word.length()];

        //for-loop to replace the chosen word with blanks
        for(int i = 0; i<blanks.length; i++){
            blanks[i] = '_';
        }

        int guess_left = 6, incorrect = 0;
        String letter = "", letters_guessed = "", blanks_str ="";
        char guess;
        while(true){
            System.out.println();
            hangingman(incorrect);
             System.out.print("\nMystery Word: ");
                for(char i: blanks){    //printing blanks
                    System.out.print(i + " ");
                }
                 
             System.out.print("\nLetters Guessed: ["); //printing letters guessed array
                  for(int i = 0; i<letters_guessed.length(); i++){
                    System.out.print("'" + letters_guessed.charAt(i) + "'");
                    if(i < letters_guessed.length() - 1) System.out.print(", ");
                  }
                System.out.print("]");

             System.out.println("\nGuesses Remaining: " + guess_left);

            while(true){   //loop to make sure user enters ONE letter
            System.out.print("Guess a letter: ");
            letter = sc.next(); 
            letter = letter.toLowerCase();

            if(letter.length() != 1){
                System.out.println("Invalid Input! Enter a single character.");
                continue;
            }

            if(!Character.isLetter(letter.charAt(0))){
                System.out.println("Please guess a letter");
                continue;
            }
            guess = letter.charAt(0);
            break;
        }

        if(!letters_guessed.contains(letter)){  //guessed/already guessed
            letters_guessed += letter;
        }else{
            System.out.print("\nYou have already guessed this letter!");
            continue;
        }
            if(word.indexOf(guess)!= -1){
                System.out.print("\nThis guess appears in the mystery word!");
                for(int i = 0; i<blanks.length; i++){
                    if(word.charAt(i) == guess){
                        blanks[i] = guess;
                    }
                }
            }else{
                incorrect++;
                guess_left--;
                System.out.print("\nThis guess does NOT appear in the mystery word!");
            }
            blanks_str = "";
            for(int i = 0; i<blanks.length; i++ ){
                blanks_str += blanks[i];
            }
            if(blanks_str.equals(word)){
                System.out.print("\nYou win!");
                score += 10;
                break;
            }else if(incorrect == 6 || guess_left == 0){
                System.out.println();
                hangingman(incorrect);
                System.out.print("\nGame Lost!");
                System.out.print("\nThe Mystery Word was " + "'" + word + "'");
                break;
            }
        }
        System.out.print("\nDo you want to play again?");
        System.out.print("\nYes(y)/No(n): ");
        char choice = sc.next().charAt(0);

        //writing score in file
        try {
            FileOutputStream fos = new FileOutputStream("record.txt",true);
            PrintWriter pw = new PrintWriter(fos);
            if(choice == 'y' || choice == 'Y'){
                pw.close();
                continue;
            }else{
            System.out.println("Thank you for playing Hangman. Goodbye!");
            pw.println(name + ", your final score is " + score + "!");
            pw.close();
            break;
        }
    }catch (FileNotFoundException e) {
             System.out.println("Error writing to file.");
        }
    }
}
    public static void main(String[] args) {
        System.out.println("\n\t\t====== WELCOME TO GAME ARENA ======");
        System.out.println("\t\t\t~ Gaming Paradise ~");
        System.out.println(" === MENU ===");
        System.out.println(" 1. Hangman\n 2. Memory Match\n 3. Word Scramble\n 4. Tic Tac Toe\n 5. Exit");
       while(true){
        try{
            System.out.print(" Your Choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice){
            case 1:
                hangman();
                break;
            case 2:
                memorymatch();
                break;
            case 3:
                wordscramble();
                break;
            case 4:
                tictac();
                break;
            case 5:
                System.out.println("\tThank you for playing!\n\t\t\t --- Goodbye!---");
                break;
            default:
                break;
        }
        if(choice < 1 || choice > 5){
            System.out.println(" Please choose a number from the given options");
            continue;
        }
        break;
    }catch(InputMismatchException e){
        System.out.println(" Please choose a number from the given options");
        sc.nextLine();
        continue;
    }
}
    }
}
