# Isengard

This is an old classroom capstone project for the University of Montana's Computer Science department. 

To run this software, you will need the following set-up on your system:

An [SDK manager](https://sdkman.io/), to install run: 
```bash
curl -s "https://get.sdkman.io" | bash
```
Then restart your terminal.

Install the correct Java version:
```bash
sdk install java 21.0.12.fx-librca
```

Once installed, run the `use` command to set it as the current SDK
```bash
sdk use java 21.0.12.fx-librca
```

Compile the program from `isengard_source_code/src/application`:
```bash
javac *.java controller/*.java -cp "../../Libraries/algs4.jar:../../Libraries/stdlib.jar"
```

Launch "The Four Horsemen" from `isengard_source_code/src`:
```bash
java -cp ".:../Libraries/algs4.jar:../Libraries/stdlib.jar" application.Main
```

To login enter the following credentials:
Username: `testuser`
Password: `12345`

## Images

| | |
|---|---|
| ![Login](images/login.png) Login | ![Password recovery](images/recovery.png) Password recovery |
| ![Reset password](images/reset.png) Reset password | ![Transactions](images/transactions.png) Transactions |
| ![Add transaction](images/new_trasaction.png) Add transaction | ![Selecting a transaction](images/selecting_trasaction.png) Selecting a transaction |
| ![Administrator panel](images/admin.png) Administrator panel | |
