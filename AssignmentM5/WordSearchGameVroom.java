import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Math;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.FileNotFoundException;

/**
 * WordSearch game uses txt files for possible words
 * that can be found in the word search
 * @author Hendrix (source code files, txt files)
 * @author Ro-main-Lettuce (methods implemmented)
 */





public class WordSearchGameVroom implements WordSearchGame{ 
   private TreeSet<String> lexicon;
   private boolean isLexiconLoaded;
   public String[] letterArray;
   private static final int MAX_NEIGHBORS = 8;

   private int length;
   private int width;
   private String[][] boardArray;
   private int methodCount;
   private String word;
   private boolean[][] past;
   private String wordTempt;
   private SortedSet<String> allWords;
   private ArrayList<Integer> road;
   private ArrayList<Position> road2;
   public WordSearchGameVroom(){
      lexicon = null;
      methodCount = 0;
      isLexiconLoaded = false;
   }
/**
    * Loads the lexicon into a data structure for later use. 
    * The loadLexicon method reads a list of words from a 
    * text file and stores each unique word in the data 
    * structure or collection that you select to represent
    * the lexicon. You will notice that many of the words
    *  in the provided lexicon files are in lowercase while
    * the game board is in uppercase. Be sure that the lexicon
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
    
   public void loadLexicon(String fileName) {
      lexicon = new TreeSet<>();
      methodCount++;
   
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      methodCount++;
   
      try (Scanner buffy = new Scanner(new File(fileName))) {
         while (buffy.hasNext()) {
            String string = buffy.next();
            string = string.toUpperCase();
            lexicon.add(string);
         }
         isLexiconLoaded = true;
      
      } catch (FileNotFoundException e) {
         throw new IllegalArgumentException();
      }
   }



    /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray){
      if(letterArray == null){
         throw new IllegalArgumentException();
      }
      int n = (int)Math.sqrt(letterArray.length);
      if((n*n) != letterArray.length){
         throw new IllegalArgumentException();
      }
      width = n;
      length = n;
      boardArray = new String[n][n];
      int temp = 0;
      for(int i = 0; i < length; i++){
         for(int p = 0; p < width; p++){
            boardArray[i][p] = letterArray[temp];
            temp++;
         }
      }
      
   }
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard(){
      String board = "";
      for (int i = 0; i < length; i++){
         if(i > 0){
            board += "\n";
         }
         for(int p = 0; p < width; p++){
            board += boardArray[i][p] + " ";
         }
      }
      return board;
   }
    /**
    * Retrieves all scorable words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllScorableWords(int minimumWordLength){
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      road2 = new ArrayList<Position>(); //Keeps track of path
      allWords = new TreeSet<String>();
      wordTempt = "";
      for (int i = 0; i < length; i++) {
         for (int j = 0; j < width; j ++) {
            wordTempt = boardArray[i][j];
            if (isValidWord(wordTempt) && wordTempt.length() >= minimumWordLength) {
               allWords.add(wordTempt);
            }
            if (isValidPrefix(wordTempt)) {
               Position temp = new Position(i,j);
               road2.add(temp);
               //Starts DFS of Board
               dfs2(i, j, minimumWordLength); 
               //When fails, removes last part from temp.
               road2.remove(temp);
            }
         }
      }
      return allWords;
   }
   
   private void dfs2(int x, int y, int min) {
      Position start = new Position(x, y);
      markAllUnvisited(); //Marks everything unvisited
      markRoadVisited(); //Marks path of current word visited
      for (Position p : start.neighbors()) {
         if (!isVisited(p)) {
            visit(p);
            if (isValidPrefix(wordTempt + boardArray[p.x][p.y])) {
               wordTempt += boardArray[p.x][p.y];
               road2.add(p);
               if (isValidWord(wordTempt) && wordTempt.length() >= min) {
                  allWords.add(wordTempt);
               }
               dfs2(p.x, p.y, min);
               //Backtracking, remove last part added to wordSoFar.
               road2.remove(p);
               int endIndex = wordTempt.length() - boardArray[p.x][p.y].length();
               wordTempt = wordTempt.substring(0, endIndex);
            }
         }
      }
      markAllUnvisited(); //Marks everything unvisited.
      markRoadVisited(); //Marks path of current word visited.
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck){
      if(lexicon == null){
         throw new IllegalStateException();
      }
      if(wordToCheck == null){
         throw new IllegalArgumentException();
      }
      
      return lexicon.contains(wordToCheck.toUpperCase());
   
   }
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
   
      prefixToCheck = prefixToCheck.toUpperCase();
      String ceilingWord = lexicon.ceiling(prefixToCheck);
      return ceilingWord != null && ceilingWord.startsWith(prefixToCheck);
   }
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      road2 = new ArrayList<Position>();
      wordToCheck = wordToCheck.toUpperCase();
      wordTempt = "";
      road = new ArrayList<Integer>();
      for (int i = 0; i < length; i++) {
         for (int j = 0; j < width; j ++) {
            if (wordToCheck.equals(boardArray[i][j])) {
               road.add(i * width + j);
               return road;
            }
            if (wordToCheck.startsWith(boardArray[i][j])) {
               Position pos = new Position(i, j);
               road2.add(pos); //Adds regular position
               wordTempt = boardArray[i][j]; 
               dfs(i, j, wordToCheck); 
               //If search fails, removes from path.
               if (!wordToCheck.equals(wordTempt)) {
                  road2.remove(pos);
               }
               else {
               //Adds row-major position
                  for (Position p: road2) {
                     road.add((p.x * width) + p.y);
                  } 
                  return road;
               }
            }
         }
      }
      return road;
   }
   
   /**
   * Depth-First Search for isOnBoard.
   * @param x the x cordiniate value
   * @param y the y cordinate value
   * @param wordToCheck the word to check for.
   */
   private void dfs(int x, int y, String wordToCheck) {
      Position start = new Position(x, y);
      markAllUnvisited(); //Marks everything unvisited
      markRoadVisited(); //Marks path of current word visited
      for (Position p: start.neighbors()) {
         if (!isVisited(p)) {
            visit(p);
            if (wordToCheck.startsWith(wordTempt + boardArray[p.x][p.y])) {
               wordTempt += boardArray[p.x][p.y]; //Adds string on to wordSoFar.
               road2.add(p);
               dfs(p.x, p.y, wordToCheck);
               if (wordToCheck.equals(wordTempt)) {
                  return;
               }
               else {
                  road2.remove(p);
               //Removes last added part of word, since we are backtracking.
                  int endIndex = wordTempt.length() - boardArray[p.x][p.y].length();
                  wordTempt = wordTempt.substring(0, endIndex);
               }
            }
         }
      }
      markAllUnvisited(); //Marks everything unvisited
      markRoadVisited(); //Marks path of current word visited
   }

   /**
   * Marks all positions unvisited.
   */
   private void markAllUnvisited() {
      past = new boolean[width][length];
      for (boolean[] row : past) {
         Arrays.fill(row, false);
      }
   }
   
   /**
   * Marks path as visited.
   */
   private void markRoadVisited() {
      for (int i = 0; i < road2.size(); i ++) {
         visit(road2.get(i));
      }
   }
   /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength){
      if(minimumWordLength < 1){
         throw new IllegalArgumentException();
      }
      if(methodCount == 0){
         throw new IllegalStateException();
      }
      int total = 0;
      for(String word :words){
         if(word.length() >= minimumWordLength&& lexicon.contains(word) && isOnBoard(word).size() > 0) {
            total += minimumWordLength - 1 + (word.length() - minimumWordLength + 1);
         }
      }
      return total;
   }

   
   private class Position {
      int x;
      int y;
   
      /** Constructs a Position with coordinates (x,y). */
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }
   
      /** Returns a string representation of this Position. */
      @Override
      public String toString() {
         return "(" + x + ", " + y + ")";
      }
   
      /** Returns all the neighbors of this Position. */
      public Position[] neighbors() {
         Position[] nbrs = new Position[MAX_NEIGHBORS];
         int count = 0;
         Position p;
         // generate all eight neighbor positions
         // add to return value if valid
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nbrs, count);
      }
   }

   /**
    * checks if a position is valid.
    * @param p the position
    */
   private boolean isValid(Position p) {
      return (p.x >= 0) && (p.x < width) && (p.y >= 0) && (p.y < length);
   }

   /**
    * Checks if a position has been visited.
    * @param p the position
    */
   private boolean isVisited(Position p) {
      return past[p.x][p.y];
   }

   /**
    * Mark this valid position as having been visited.
    */
   private void visit(Position p) {
      past[p.x][p.y] = true;
   }

}
