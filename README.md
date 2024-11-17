## Overview

  

This Java program fetches the Java code snippet for a specific problem from the __LeetCode__ website using its API and saves it as a `.java` file in the specified directory. It automates the process of retrieving and storing code snippets for LeetCode problems by simply providing the problem name.



## Features


1. __LeetCode API Integration:__ Fetches Java code snippets using LeetCode's GraphQL API.

2. __Dynamic File Creation:__ Creates a .java file for the specified problem name in the chosen directory.

3. __Error Handling:__ Handles common errors, such as invalid problem names or nonexistent file paths, gracefully.

  

## Usage

### Prerequisites

  

- Java Development Kit (JDK) 11 or above.

- Internet connection to access the LeetCode API.

- Valid problem name from LeetCode.

  

### Steps to Run

  

1. __Compile the Program:__

  
```
javac Main.java
```

  

2. __Run the Program:__

```
java Main
```

  

3. __Provide Inputs:__

Enter the name of the LeetCode problem (e.g., `Two Sum`).

Specify the path to save the `.java` file.

  

4. The program will:

Fetch the Java code snippet for the specified problem.

Save it in the specified path with the problem name as the file name.

  

## Example Workflow

#### Input:

  

__Problem Name:__ `Two Sum`

__File Path:__ `/home/user/projects/`

  

#### Output:

  

The program fetches the Java code snippet for Two Sum and creates a file named `TwoSum.java` in `/home/user/projects/`.

#### Error Handling

  

__Invalid Problem Name:__ Displays an error message and prompts the user to re-enter the problem name.

__Invalid File Path:__ Alerts the user about the invalid path and prompts for a new one.

__API Issues:__ If the API request fails, the program displays the exception and exits.

  

## Notes

  

Ensure the problem name is valid and matches the title used on LeetCode.

If the API structure changes, this program might require updates.
